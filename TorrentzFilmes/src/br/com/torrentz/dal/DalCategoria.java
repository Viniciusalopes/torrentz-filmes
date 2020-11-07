/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
<<<<<<< HEAD

package br.com.torrentz.dal;

import br.com.torrentz.generic.DalGeneric;
import br.com.torrentz.generic.Where;
import br.com.torrentz.model.Categoria;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marcos
 */
public abstract class DalCategoria extends DalGeneric<Categoria>{
    
/* Métodos */
    /**
     * 
     * @throws Exception 
     */
    public DalCategoria() throws Exception{
        super("categorias","cat_id");
        
        sqlInsert = "INSERT INTO " + table
                + " (cat_nome) "
                + "VALUES (?) ";

        sqlSelect = "SELECT * FROM " + table + " ";

        sqlUpdate = "UPDATE " + table + " SET "
                + "cat_nome = ? ";
        
        orderBy = " ORDER BY cat_nome";
    }
    
    /**
     * 
     * @param rs
     * @return
     * @throws Exception 
     */
    @Override
    protected ArrayList<Categoria> build(ResultSet rs) throws Exception {
        ArrayList<Categoria> ret = new ArrayList<>();
        while (rs.next()) {
            ret.add(new Categoria(
                    rs.getInt(fieldPK),
                    rs.getString("cat_nome")
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
    public Categoria getById(int id) throws Exception {
        sql = sqlSelect + sqlWhere;
        args = new Object[]{id};
        ArrayList<Categoria> ret = select();
        if (ret.isEmpty()) {
            throw new Exception("Nenhum categoria cadastrado com o id [" + id + "] !");
        }
        return ret.get(0);
    }
    
    /**
     * 
     * @return
     * @throws Exception 
     */
    public ArrayList<Categoria> getAll() throws Exception{
        try {
            
            sql = sqlSelect + orderBy;
            args = new Object[]{};
            ArrayList<Categoria> list = select();
            
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
    protected ArrayList<Categoria> getBy(Where[] where) throws Exception {
        sql = sqlSelect;
        return getByFields(where);
    }

    /**
     * 
     * @param text
     * @return
     * @throws Exception 
     */
    public ArrayList<Categoria> search(String text) throws Exception {
        String t = "%" + text.toLowerCase().trim() + "%";
        sql = sqlSelect
                + " WHERE LOWER(cat_nome) LIKE ? "
                + orderBy;
        args = new Object[]{t};
        return select();
    }
    /**
     * 
     * @param nome
     * @return 
     */
    public Categoria getByNome(String nome) throws Exception{
        try {
            for (Categoria categoria : search(nome)) {
                if(categoria.getNome().toLowerCase().equals(categoria.getNome().toLowerCase())){
                    return categoria;
                }
            }
            throw new Exception("Nenhum categoria cadastrado com o nome [" + nome + "] !");
        } catch (Exception error) {
           throw error;
        }
    }
    /**
     * 
     * @param categoria
     * @throws Exception 
     */
    protected void add(Categoria categoria) throws Exception {
        args = new Object[]{
            categoria.getNome()
        };
        sql = sqlInsert;
        execute();
    }
    
    /**
     * 
     * @param categoria
     * @throws Exception 
     */
    protected void update(Categoria categoria) throws Exception {
         args = new Object[]{
            categoria.getNome(),
            categoria.getId()
        };
        sql = sqlUpdate + sqlWhere;
        execute();
    }
}
=======
package br.com.torrentz.dal;

import br.com.torrentz.model.Categoria;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author calebaum
 */
public class DalCategoria {
    public List<Categoria> getall() {
        List<Categoria> categorias = new ArrayList<Categoria>();
        return categorias;
}
    //getById
      public Categoria getByid(int cat_id) {
        Categoria categoria = new Categoria();
        return categoria;
              
      }
      
}

>>>>>>> main
