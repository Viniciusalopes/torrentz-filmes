/*
 *  ----------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Viniciusalopes (Vovolinux) <suporte@vovolinux.com.br>
 *  Criado em  : 21/10/2020 23:48:04 
 *  Projeto    : API Acervo Musical
 *  Versão     : 0.1
 *  ------------------------------------------------------------------------------------------------
 *  Propósito  : Conexão com o banco de dados PostgreSql (SINGLETON).
 *  ------------------------------------------------------------------------------------------------
 *  Changelog:
 *  Autor      : NOME DO AUTOR
 *  Data       : YYYY-mm-dd
 *  Versão     : n.n.n
 *  Alterações : DUPLICAR ESTE BLOCO E DESCREVER A(s) ALTERAÇÃO(ões) RELEVANTES PARA A VERSÃO.
 *               MANTER INDENTAÇÃO DE LINHAS ABAIXO DA PRIMEIRA LINHA E O LIMITE DE COLUNAS AQUI -->
 *  -----------------------------------------------------------------------------------------------| 
 */
package br.com.torrentz.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 *
 * @author vovostudio
 */
public class BDConn {

    //--- ATRIBUTOS ------------------------------------------------------------------------------->
    //
    private static BDConn bdConn = null;
    private static BDConfig config = null;
    private static String url = "";
    private static Properties props = null;
    private Connection connection = null;

    //--- FIM ATRIBUTOS ---------------------------------------------------------------------------|
    //
    //--- CONSTRUTORES ---------------------------------------------------------------------------->
    //
    private BDConn() {
        config = BDConfig.getInstance();
    }

    //--- FIM CONSTRUTORES ------------------------------------------------------------------------|
    //
    //--- GET ------------------------------------------------------------------------------------->
    //
    public static BDConn getInstance() throws Exception {
        try {
            if (bdConn == null) {
                bdConn = new BDConn();
                config = BDConfig.getInstance();
            }
            url = "jdbc:postgresql:" + config.getServer() + ":" + config.getPort() + "/" + config.getDatabase();
            props = new Properties();
            props.setProperty("user", config.getUser());
            props.setProperty("password", config.getPassword());

            return bdConn;
        } catch (Exception e) {
            throw new Exception("Não foi possível localizar o banco de dados!\n" + e.getLocalizedMessage());
        }
    }

    public Connection getConnection() throws Exception {
        try {
            Class.forName(config.getDriver());
            connection = DriverManager.getConnection(url, props);
            return connection;
        } catch (Exception e) {
            throw new Exception("Não foi possível obter a conexão com o banco de dados!\n" + e.getLocalizedMessage());
        }
    }
    //--- FIM GET ---------------------------------------------------------------------------------|
    //
}
