/*
 *  ----------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Viniciusalopes (Vovolinux) <suporte@vovolinux.com.br>
 *  Criado em  : 21/10/2020 23:34:45 
 *  Projeto    : API Acervo Musical
 *  Versão     : 0.1.0
 *  ------------------------------------------------------------------------------------------------
 *  Propósito  : Configurações para conexão com banco de dados PostgreSQL (SINGLETON).
 *  ------------------------------------------------------------------------------------------------
 *  Changelog:
 *  Autor      : NOME DO AUTOR
 *  Data       : YYYY-mm-dd
 *  Versão     : n.n.n
 *  Alterações : DUPLICAR ESTE BLOCO E DESCREVER A(s) ALTERAÇÃO(ões) RELEVANTES PARA A VERSÃO.
 *               MANTER INDENTAÇÃO DE LINHAS ABAIXO DA PRIMEIRA LINHA E O LIMITE DE COLUNAS AQUI -->
 *  -----------------------------------------------------------------------------------------------| 
 */
package br.com.torrentz.generic;

/**
 *
 * @author vovostudio
 */
public class BDConfig {

    //--- ATRIBUTOS ------------------------------------------------------------------------------->
    //
    //Conexão Remota                
    private static BDConfig config = null;
    private final String server = "//18.224.5.197";
    private final String port = "5432";
    private final String database = "bdtorrentz01";
    private final String user = "torrentz";
    private final String password = "t0rr3ntz"; // Bem secret mesmo...
    private final String driver = "org.postgresql.Driver";
        //Conexão Local                
<<<<<<< HEAD
=======

>>>>>>> e085699d7e1e511fe37da7fc37f444cd65b04241
//    private static BDConfig config = null;
//    private final String server = "//127.0.0.1";
//    private final String port = "5432";
//    private final String database = "bdtorrentz01";
//    private final String user = "admin";
//    private final String password = "secret"; // Bem secret mesmo...
//    private final String driver = "org.postgresql.Driver";
    //--- FIM ATRIBUTOS ---------------------------------------------------------------------------|
    //
    //--- CONSTRUTORES ---------------------------------------------------------------------------->
    //
    private BDConfig() {

    }

    //--- FIM CONSTRUTORES ------------------------------------------------------------------------|
    //
    //--- GET ------------------------------------------------------------------------------------->
    //
    public static BDConfig getInstance() {
        if (config == null) {
            config = new BDConfig();
        }
        return config;
    }

    public String getServer() {
        return server;
    }

    public String getPort() {
        return port;
    }

    public String getDatabase() {
        return database;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getDriver() {
        return driver;
    }
    //--- FIM GET ---------------------------------------------------------------------------------|
    //
}
