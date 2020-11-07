/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.torrentz.bll;

import br.com.torrentz.dal.DalCategoria;
import br.com.torrentz.model.Categoria;

/**
 *
 * @author marcos
 */
public class BllCategoria extends DalCategoria{
    
    public BllCategoria() throws Exception{super();}
    /**
     * 
     * @param categoria
     * @throws Exception 
     */
    public void add(Categoria categoria) throws Exception {
        super.add(categoria); //To change body of generated methods, choose Tools | Templates.
        
    }    
    
    /**
     * 
     * @param categoria
     * @throws Exception 
     */
    public void update(Categoria categoria) throws Exception {
        super.update(categoria); //To change body of generated methods, choose Tools | Templates.
    }
}
