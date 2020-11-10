/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.torrentz.generic;

import br.com.torrentz.generic.BDConn;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author marcos
 */
public class DalIdGeneric {
    /* Atributos */
    private Connection conexao = null;
    private String table = "";
    
    /* Métodos */
    /**
     * 
     * @param table
     * @throws Exception 
     */
    public DalIdGeneric(String table) throws Exception{
        this.table = table+"_"+table.substring(0,3)+"_id_seq";
        BDConn dbConn =  BDConn.getInstance();
        conexao =dbConn.getConnection();
    }
    
    /**
     * 
     * @return
     * @throws Exception 
     */
    public int lastValue() throws Exception{
        String sql = "SELECT last_value FROM "+table+" ";
        try {
            Statement stm = conexao.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            int id = 0;
            if(rs.next()){
               return id = rs.getInt("last_value")+1;
            }
        } catch (Exception e) {
            throw e;
        }
        return 0;
    }
}
