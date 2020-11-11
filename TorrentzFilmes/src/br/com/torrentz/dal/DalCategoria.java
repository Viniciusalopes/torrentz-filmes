/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.torrentz.dal;

import br.com.torrentz.generic.DalGeneric;
import br.com.torrentz.generic.Where;
import br.com.torrentz.model.Categoria;
import br.com.torrentz.model.Usuario;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author calebaum
 */
public class DalCategoria extends DalGeneric<Categoria> {

    protected DalCategoria() throws Exception {
        super("Categorias", "cat_id");

        sqlInsert = "INSERT INTO " + table
                + " (cat_nome) "
                + "VALUES (?) ";

        sqlSelect = "SELECT * FROM " + table + " ";

        sqlUpdate = " UPDATE " + table + " SET "
                + "cat_nome = ?, " + sqlWhere;

        orderBy = " ORDER BY cat_nome";

    }

    public List<Categoria> getall() throws Exception {
        sql = sqlSelect + orderBy;
        args = new Object[]{};
        return select();

    }

    //getById
    public Categoria getByid(int cat_id) throws Exception {
        sql = sqlSelect + sqlWhere;
        args = new Object[]{cat_id};
        ArrayList<Categoria> ret = select();
        if (ret.size() == 0) {
            throw new Exception("Nenhum Categoria cadastrada com o id [" + cat_id + "] !");
        }
        return ret.get(0);

    }

    //getByNamenew
    public Categoria getByname(String cat_nome) throws Exception {
        sql = sqlSelect;
        Where[] where = new Where[]{new Where("", "cat_nome", "=", cat_nome)};
        ArrayList<Categoria> ret = getByFields(where);
        if (ret.size() == 0) {
            throw new Exception("Nenhum Categoria cadastrada com o Nome [" + cat_nome + "] !");
        }
        return ret.get(0);
    }

    //getSearch
    public ArrayList<Categoria> BySearch(String cat_nome) throws Exception {
        sql = sqlSelect
                + " WHERE LOWER (cat_nome) LIKE ? "
                + orderBy;
        args = new Object[]{"%" + cat_nome.toLowerCase() + "%"};

        return select();
    }

    protected void add(Categoria categoria) throws Exception {
        sql = sqlInsert;
        args = new Object[]{categoria.getNome()};
        execute();
    }

    protected void update(Categoria categoria) throws Exception {
        sql = sqlUpdate;
        args = new Object[]{categoria.getNome()};
        execute();
    }

    protected boolean alreadyExists (String cat_nome, int cat_id) throws Exception{
     Where where = new Where ("", "cat_nome", "=", cat_nome);
      return super.alreadyExists(where, cat_id);
    }
    
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

}
