/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.torrentz.bll;

import br.com.torrentz.dal.DalPlano;
import br.com.torrentz.model.Plano;
import java.util.ArrayList;

/**
 *
 * @author vovolinux
 */
public class BllPlano extends DalPlano {

    public BllPlano() throws Exception {
        super();
    }

    @Override
    protected void update(Plano plano) throws Exception {
        
        super.update(plano); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void add(Plano plano) throws Exception {
       
        super.add(plano); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Plano> search(String text) throws Exception {
        return super.search(text); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
