package br.com.torrentz.app;

import br.com.torrentz.bll.BllCategoria;
import br.com.torrentz.model.Categoria;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vovolinux
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new AppLogin().setVisible(true);
        
        try {
            BllCategoria bll = new BllCategoria();
            Categoria cat = new Categoria();
            bll.add(cat);
        } catch (Exception e) {
        }
        
       
    }
    
}
