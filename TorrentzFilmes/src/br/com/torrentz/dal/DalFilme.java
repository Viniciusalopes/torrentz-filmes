/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.torrentz.dal;

import br.com.torrentz.generic.DalGeneric;
import br.com.torrentz.generic.Where;
import br.com.torrentz.model.Categoria;
import br.com.torrentz.model.Filme;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author marcos
 */
public abstract class DalFilme extends DalGeneric<Filme> {
    
    public DalFilme() throws Exception{
        super("filmes","fil_id");
        
        sqlInsert = "INSERT INTO " + table
                + " (fil_sinopse, fil_titulo, fil_ano, fil_cat_id) "
                + "VALUES (?,?,?,?) ";

        sqlSelect = "SELECT * FROM " + table + " "
                  + "JOIN categorias ON filmes.fil_cat_id = categorias.cat_id ";

        sqlUpdate = "UPDATE " + table + " SET "
                + "fil_sinopse = ?, fil_titulo = ?, fil_ano = ?, fil_cat_id = ?";
        
        orderBy = " ORDER BY fil_titulo";   
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
    
    /**
     * 
     * @param id
     * @return
     * @throws Exception 
     */
    public Filme getById(int id) throws Exception {
        sql = sqlSelect + sqlWhere;
        args = new Object[]{id};
        ArrayList<Filme> ret = select();
        if (ret.isEmpty()) {
            throw new Exception("Nenhum Filme cadastrado com o id [" + id + "] !");
        }
        return ret.get(0);
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
     * @param where
     * @return
     * @throws Exception 
     */
    protected ArrayList<Filme> getBy(Where[] where) throws Exception {
        sql = sqlSelect;
        return getByFields(where);
    }

    /**
     * 
     * @param text
     * @return
     * @throws Exception 
     */
    public ArrayList<Filme> search(String text) throws Exception {
        String t = "%" + text.toLowerCase().trim() + "%";
        sql = sqlSelect
                + " WHERE LOWER(fil_titulo) LIKE ? "
                + "   OR LOWER(fil_sinopse) LIKE ? "
                + orderBy;
        args = new Object[]{t, t};
        return select();
    }
    
    /**
     * 
     * @param filme
     * @throws Exception 
     */
    protected void add(Filme filme) throws Exception {
        args = new Object[]{
            filme.getSinopse(),
            filme.getTitulo(),
            filme.getAno(),
            filme.getCategoria().getId(),
        };
        sql = sqlInsert;
        execute();
    }
    
    /**
     * 
     * @param filme
     * @throws Exception 
     */
    protected void update(Filme filme) throws Exception {
         args = new Object[]{
            filme.getSinopse(),
            filme.getTitulo(),
            filme.getAno(),
            filme.getCategoria().getId(),
            filme.getId()
        };
        sql = sqlUpdate + sqlWhere;
        execute();
    }

}