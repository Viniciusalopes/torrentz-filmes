/*
 *  ----------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Viniciusalopes (Vovolinux) <suporte@vovolinux.com.br>
 *  Criado em  : 22/10/2020 00:01:15 
 *  Projeto    : API Acervo Musical
 *  Versão     : 0.1.0
 *  ------------------------------------------------------------------------------------------------
 *  Propósito  : Classe Dal genérica. 
 *               Padrão TEMPLATE com método build() onde cada Dal implementa seu build de 
 *               acordo com o objeto manipulado.
 *  ------------------------------------------------------------------------------------------------
 *  Changelog:
 *  Autor      : NOME DO AUTORNOME DO AUTOR
 *  Data       : YYYY-mm-dd
 *  Versão     : n.n.n
 *  Alterações : DUPLICAR ESTE BLOCO E DESCREVER A(s) ALTERAÇÃO(ões) RELEVANTES PARA A VERSÃO.
 *               MANTER INDENTAÇÃO DE LINHAS ABAIXO DA PRIMEIRA LINHA E O LIMITE DE COLUNAS AQUI -->
 *  -----------------------------------------------------------------------------------------------| 
 */
package br.com.torrentz.generic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author vovostudio
 */
public abstract class DalGeneric<T> {

    //--- ATRIBUTOS ------------------------------------------------------------------------------->
    //
    private BDConn bdConn = null;                   // Objeto para conexão com o banco de dados.
    protected Connection conn = null;               // Conexão.
    protected PreparedStatement pstm = null;        // Statement.
    protected String table = "";                    // Nome da tabela filha.
    protected String sql = "";                      // Instrução SQL.
    protected String fieldPK = "";                  // Nome da coluna da chave primária da tabela.
    protected String sqlInsert = "";                // Instrução SQL padrão para INSERT.
    protected String sqlSelect = "";                // Instrução SQL padrão para SELECT
    protected String sqlWhere = "";                 // Instrução SQL padrão para WHERE id = ?
    protected String sqlUpdate = "";                // Instrução SQL padrão para UPDATE
    protected String sqlDelete = "";                // Instrução SQL padrão para DELETE
    protected String orderBy = "";                  // Ordem padrão do resultado da consulta
    protected Object[] args = new Object[]{};       // Argumentos (parâmetros) para a instrução SQL.
    protected ArrayList<T> ret = new ArrayList<>(); // Lista para retorno das consultas.
    protected int pk = 0;                           // PK para uso como FK.

    //--- FIM ATRIBUTOS ---------------------------------------------------------------------------|
    //
    //--- CONSTRUTORES ---------------------------------------------------------------------------->
    //
    /**
     * Construtor para herança.
     *
     * @param table Nome da tabela filha.
     * @throws Exception
     */
    protected DalGeneric(String table, String fieldPK) throws Exception {
        bdConn = BDConn.getInstance();
        this.table = table;
        this.fieldPK = fieldPK;
        this.sqlWhere = " WHERE " + fieldPK + " = ? ";
        this.sqlDelete = "DELETE FROM " + table + sqlWhere;
    }

    //--- FIM CONSTRUTORES ------------------------------------------------------------------------|
    //
    //--- GET ------------------------------------------------------------------------------------->
    //
    /**
     *
     * @return Nome da coluna da chave primária da tabela.
     */
    protected String getFieldPK() {
        return fieldPK;
    }

    /**
     *
     * @return Nome da tabela no banco de dados;
     */
    public String getTable() {
        return table;
    }

    /**
     * Executa uma consulta e converte os objetos do resultado para objetos da classe filha.
     *
     * @return
     * @throws Exception
     */
    protected ArrayList<T> select() throws Exception {
        return build(executeQuery());
    }

    /**
     * Executa uma consulta de todos os registros de uma tabela e converte para uma lista de <br>
     * objetos da classe filha.
     *
     * @return
     * @throws Exception
     */
    public ArrayList<T> getAll() throws Exception {
        sql = "SELECT * FROM " + table;
        args = new Object[]{};
        return select();
    }

    /**
     * Executa uma consulta em uma tabela.
     *
     * @return
     * @throws Exception
     */
    protected ResultSet executeQuery() throws Exception {
        return (ResultSet) execute(true);
    }

    /**
     * Executa uma instrução SQL em um tabela.
     *
     * @return
     * @throws Exception
     */
    protected Object execute() throws Exception {
        return execute(false);
    }

    /**
     * Obtém uma conexão, executa uma instrução SQL ou uma consulta, e retorna o resultado.
     *
     * @param isQuery TRUE: retorna o ResultSet com o resultado da query.<br>
     * FALSE: Retorna um objeto com o resultado da execução da instrução SQL.
     * @return
     * @throws Exception
     */
    private Object execute(boolean isQuery) throws Exception {
        Object objRet = null;
        try {

            prepare();

            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof Date) {
                    pstm.setDate((i + 1), new java.sql.Date(((Date) args[i]).getTime()));
                } else {
                    pstm.setObject((i + 1), args[i]);
                }
            }
            if (isQuery) {
                return pstm.executeQuery();
            } else {
                objRet = pstm.execute();

                if(!fieldPK.contains(",")){ //se contém, é chave composta
                    // Armazena o ID gerado
                    ResultSet rs = pstm.getGeneratedKeys();
                    if (rs.next()) {
                        pk = rs.getInt(1);
                    }
                }
            }
        } catch (Exception e) {
            end(true);
            throw e;
        }
        end(false);
        return objRet;
    }

    //--- FIM GET ---------------------------------------------------------------------------------|
    //
    //--- SET ------------------------------------------------------------------------------------->
    //
    /**
     * Inicia uma conexão com o banco e configura o AutoCommit para TRUE.
     *
     * @throws Exception
     */
    protected void prepare() throws Exception {
        prepare(true);
    }

    /**
     * Inicia uma conexão com o banco e configura o AutoCommit.
     *
     * @param autoCommit False: utilizado para transações que necessitam de rollBack.
     * @throws Exception
     */
    protected void prepare(boolean autoCommit) throws Exception {
        conn = bdConn.getConnection();
        conn.setAutoCommit(autoCommit);
        pstm = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
    }

    /**
     * Encerra uma conexão e verifica se será necessário o rollBack.
     *
     * @param rollBack True: executa o rollBack na transação.
     * @throws Exception
     */
    private void end(boolean rollBack) throws Exception {
        if (conn != null) {
            if (!conn.getAutoCommit()) {
                if (rollBack) {
                    conn.rollback();
                }
            }
            conn.close();
        }
    }

    /**
     * Executa o commit da transação e encerra a conexão.
     *
     * @throws Exception
     */
    protected void commit() throws Exception {
        conn.commit();
        conn.close();
    }

    //--- FIM SET ---------------------------------------------------------------------------------|
    //
    //--- READ ------------------------------------------------------------------------------------>
    //
    /**
     * Métod abstrato tipo TemplateMethod, para implementação nos herdeiros.
     *
     * @param rs ResultSet com o resultado da consulta ao banco de dados.
     * @return
     * @throws Exception
     */
    protected abstract ArrayList<T> build(ResultSet rs) throws Exception;

    /**
     * Pesquisa registros em uma tabela que correspondam às condições da cláusula Where.
     *
     * @param where Objeto genérico com vetor de objetos Where que serão adicionados ao sql no
     * objeto pai (DalGeneric).
     * @return
     * @throws Exception
     */
    protected ArrayList<T> getByFields(Where[] where) throws Exception {
        sql += " WHERE ";
        args = new Object[where.length];
        for (int i = 0; i < where.length; i++) {
            sql += where[i].getPreField() + " "
                    + where[i].getFieldName() + " "
                    + where[i].getComparer() + " "
                    + "? ";
            args[i] = where[i].getObjValue();
        }
        sql += orderBy;
        return select();
    }

    /**
     * Verifica se já existe outro registro com o mesmo nome e com um id diferente.
     *
     * @param where Objeto Where para composição da sql.
     * @param id Id do objeto atual.
     * @return True: Já existe outro registro semelhante e com outro id.
     * @throws Exception
     */
    public boolean alreadyExists(Where where, int id) throws Exception {
        sql = "SELECT COUNT(*) FROM " + table + " WHERE LOWER (" + where.getFieldName() + ") = ? "
                + "AND " + fieldPK + " <> ? ";
        args = new Object[]{where.getObjValue().toString().toLowerCase(), id};
        ResultSet rs = executeQuery();
        if (rs.next()) {
            return rs.getInt("count") > 0;
        }
        return false;
    }

    /**
     * Verifica se uma tabela está vazia.
     *
     * @param table Tabela a ser verificada.
     * @return True = Vazia.
     * @throws Exception
     */
    public boolean isEmptyTable(String table) throws Exception {
        sql = "SELECT COUNT(*) FROM " + table;
        args = new Object[]{};
        ResultSet rs = executeQuery();
        if (rs.next()) {
            return rs.getInt("count") == 0;
        }
        return true;
    }

    /**
     * Retorna o tamanho máximo para uma coluna no banco de dados.
     *
     * @param column_name Nome da coluna a ser verificada.
     * @return Tamanho máximo da coluna.
     * @throws Exception
     */
    public int getMaxLength(String column_name) throws Exception {
        sql = "SELECT character_maximum_length AS maxLength "
                + "FROM information_schema.columns "
                + "WHERE table_name = ? AND column_name = ?";
        args = new Object[]{table, column_name};

        ResultSet rs = executeQuery();
        if (rs.next()) {
            return rs.getInt("maxLength");
        }
        return 0;
    }

    //--- FIM READ --------------------------------------------------------------------------------|
    //
    //--- DELETE ---------------------------------------------------------------------------------->
    //
    /**
     * Exclui um registro de uma tabela do banco a partir de um id.
     *
     * @param id Id do registro a ser excluído.
     * @throws Exception
     */
    public void delete(int id) throws Exception {
        sql = sqlDelete;
        args = new Object[]{id};
        execute();
    }
    //--- FIM DELETE ------------------------------------------------------------------------------|
    //
}
