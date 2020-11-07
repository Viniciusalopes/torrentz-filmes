/*
---------------------------------------------------------------
Lucas
1. CRUD e tela de cadastro de planos - TELA CONCLUÍDA
- Métodos: 
  Dentro da pasta br.com.torrentz.model criar a classe "Plano" de acordo com o
     Modelo Conceitual

  DAL getAll, getById, getByName, search
  BLL add, update, validate
	
  getAll = Buscar todos
  getById = Buscar pelo ID do plano
  getByName = Buscar pelo nome do plano
  search = pesquisar um texto qualquer nos nomes dos planos
  BLL add, update, validate

   Pesquisa
   SELECT * FROM planos WHERE LOWER(pla_nome) LIKE %texto%;

2. Cronograma Resumido
3. Riscos
---------------------------------------------------------------
 */
package br.com.torrentz.dal;

import br.com.torrentz.generic.DalGeneric;
import br.com.torrentz.model.Plano;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Computador
 */
public class DalPlano extends DalGeneric<Plano> {

    //contrutor protegido 
    /**
     *
     * O modificador protected torna o membro acessível às classes do mesmo
     * pacote ou através de herança, seus membros herdados não são acessíveis a
     * outras classes fora do pacote em que foram declarados.
     */
    protected DalPlano() throws Exception {
        super("Planos ", "pla_nome");

        sqlInsert = "INSERT INTO " + table
                + "(pla_id, pla_acesso_simultaneo, pla_nome, pla_preco"
                + "VALUES (?, ?, ?, ?)";

        sqlSelect = "SELECT * FROM " + table + " ";

        sqlUpdate = " UPDATE " + table + " SET "
                + "pla_acesso_simultaneo = ?, pla_nome = ?, pla_preco " + sqlWhere;

        orderBy = " ORDER BY pla_nome";

    }

    @Override
    protected ArrayList<Plano> build(ResultSet rs) throws Exception {
        ArrayList<Plano> ret = new ArrayList<>();
        while (rs.next()){
            ret.add(new Plano(
                    rs.getInt(fieldPK),
                    rs.getInt("pla_acesso_simultaneo"),
                    rs.getString("pla_nome"),
                    rs.getFloat("pla_preco")
            ));
        }
    



