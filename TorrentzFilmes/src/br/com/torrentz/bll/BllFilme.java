/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.torrentz.bll;

import br.com.torrentz.dal.DalFilme;
import br.com.torrentz.model.Filme;

/**
 *
 * @author marcos
 */
public class BllFilme extends DalFilme {

    public BllFilme() throws Exception{super();}
    /**
     * 
     * @param filme
     * @throws Exception 
     */
    public void add(Filme filme) throws Exception {
        super.add(filme); //To change body of generated methods, choose Tools | Templates.
        
    }    
    
    /**
     * 
     * @param filme
     * @throws Exception 
     */
    public void update(Filme filme) throws Exception {
        super.update(filme); //To change body of generated methods, choose Tools | Templates.
    }
}
