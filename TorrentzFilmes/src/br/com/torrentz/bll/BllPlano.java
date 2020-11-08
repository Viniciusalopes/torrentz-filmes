/*
/*
---------------------------------------------------------------
Lucas
1. CRUD e tela de cadastro de planos - TELA CONCLUÍDA
- Métodos: 
  Dentro da pasta br.com.torrentz.model criar a classe "Plano" de acordo com o
     Modelo Conceitual

  DAL getAll, getById, getByName, search
  BLL add, update, validate
	
  getAll = Buscar todos
  getById = Buscar pelo ID do plano
  getByName = Buscar pelo nome do plano
  search = pesquisar um texto qualquer nos nomes dos planos
  BLL add, update, validate

   Pesquisa
   SELECT * FROM planos WHERE LOWER(pla_nome) LIKE %texto%;

2. Cronograma Resumido
3. Riscos
---------------------------------------------------------------
 */
 
package br.com.torrentz.bll;

import br.com.torrentz.dal.DalPlano;
import br.com.torrentz.model.Plano;
import java.util.ArrayList;

/**
 *
 * @author lucas 
 */
public class BllPlano extends DalPlano {

    public BllPlano() throws Exception {
        super();
    }
    
    
    //metoddo herdado da classe DalPlano 
    
    //_______________________________________________________________________________________________
    //add
    @Override
    protected void add(Plano plano) throws Exception {
       //metodo herdado 
       //metodo prtegido 
        super.add(plano); 
        //To change body of generated methods, choose Tools | Templates.
        //To change body of generated methods, choose Tools | Templates.
    }
    //-----------------------------------------------------------------------------------------------
    
    
    //_______________________________________________________________________________________________
    //update 
    @Override
    protected void update(Plano plano) throws Exception {

        super.update(plano); //To change body of generated methods, choose Tools | Templates.
    }
    //______________________________________________________________________________________________+
    
    //_______________________________________________________________________________________________
    //search
    @Override
    public ArrayList<Plano> search(String text) throws Exception {
        return super.search(text); //To change body of generated methods, choose Tools | Templates.
    }
    //_______________________________________________________________________________________________
    
}
