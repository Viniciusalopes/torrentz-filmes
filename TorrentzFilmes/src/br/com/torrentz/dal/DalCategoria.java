/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

