/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.torrentz.bll;

import br.com.torrentz.dal.DalContrato;
import br.com.torrentz.model.Contrato;

/**
 *
 * @author vovolinux
 */
public class BllContrato extends DalContrato {

    public BllContrato() throws Exception {
        super();
    }

    public void validate(Contrato contrato) throws Exception {
        
    }

    public void validateDataInicio(String data) throws Exception{
        
        
    }
    public void add(Contrato contrato) throws Exception {
        validate(contrato);
        super.add(contrato);
    }
    
    

}
