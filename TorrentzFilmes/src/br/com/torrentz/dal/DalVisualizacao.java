/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.torrentz.dal;

import br.com.torrentz.generic.DalGeneric;
import br.com.torrentz.model.Visualizacao;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author marcos
 */
public class DalVisualizacao extends DalGeneric<Visualizacao>{
    
    public DalVisualizacao() throws Exception{
        super("visalizacoes","fil_id");
  
    }
    @Override
    protected ArrayList<Visualizacao> build(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
