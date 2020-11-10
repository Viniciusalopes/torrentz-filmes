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
import static br.com.torrentz.util.UtilString.soTemLetras;
import static br.com.torrentz.util.UtilString.removeEspacos;
import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public class BllPlano extends DalPlano {

    public BllPlano() throws Exception {
        super();
    }
    //metodo validate

    public void validate(Plano plano) throws Exception {

        if (!soTemLetras(plano.getPla_nome())) {
            throw new Exception("O nome do plano possui caracteres invalidos!");
        }
        if (plano.getPla_nome().length() < 2) {
            throw new Exception("o nome deve ter mais que uma letra ");
        }
        if (plano.getPla_nome().length() > getMaxLength("pla_nome")) {
            throw new Exception("nome muito grande");
        }
        
    }

    //metoddo herdado da classe DalPlano 
    //_______________________________________________________________________________________________
    //add
    @Override
    public void add(Plano plano) throws Exception {
        plano.setPla_nome(tratarEspacosNome(plano.getPla_nome()));

        validate(plano);
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
    public void update(Plano plano) throws Exception {
        plano.setPla_nome(tratarEspacosNome(plano.getPla_nome()));
        validate(plano);
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

    private String tratarEspacosNome(String nome) {
        nome.trim();
        removeEspacos(nome);

        return nome;
    }

}
