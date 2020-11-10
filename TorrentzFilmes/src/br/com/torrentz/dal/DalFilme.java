/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.torrentz.dal;

import br.com.torrentz.generic.DalGeneric;
import br.com.torrentz.model.Categoria;
import br.com.torrentz.model.Filme;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author marcos
 */
public abstract class DalFilme extends DalGeneric<Filme>{
    
    public DalFilme() throws Exception{
        super("filmes","fil_id");
        
        sqlInsert = "INSERT INTO " + table
                + " (fil_sinopse, fil_titulo, fil_ano, fil_cat_id) "
                + "VALUES (?,?,?,?) ";

        sqlSelect = "SELECT * FROM " + table + " "
                  + "JOIN categorias ON filmes.fil_cat_id = categorias.cat_id ";

        sqlUpdate = "UPDATE " + table + " SET "
                + "fil_sinopse = ?, fil_titulo = ?, fil_ano = ?, fil_cat_id = ? " + sqlWhere;
        
        orderBy = " ORDER BY fil_titulo";   
    }
  /**
     * 
     * @return
     * @throws Exception 
     */
    public ArrayList<Filme> getAll() throws Exception{
        try {
            
            sql = sqlSelect + orderBy;
            args = new Object[]{};
            ArrayList<Filme> list = select();
            
            return list;
        } catch (RuntimeException e) {
             throw e;
        }
    }
     
    /**
     * 
     * @param rs
     * @return
     * @throws Exception 
     */
    @Override
    protected ArrayList<Filme> build(ResultSet rs) throws Exception {
        ArrayList<Filme> ret = new ArrayList<>();
        while (rs.next()) {
            ret.add(new Filme(
                    rs.getInt(fieldPK),
                    rs.getString("fil_titulo"),
                    rs.getString("fil_sinopse"),
                    rs.getInt("fil_ano"),
                    new Categoria(rs.getInt("cat_id"),rs.getString("cat_nome"))
                    
            ));
        }
        return ret;
    }
    
}
