/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.torrentz.model;

/**
 *
 * @author calebaum
 */
public class Categoria {
    
    //----------------------------------------------------------------------------------------------
    //atributos 
    //atributos com valores em String
    private String cat_nome;
    //Atributos com valores em int
    private int cat_id;
    
    //construtor vazio
    public  Categoria(){
    }
    
    //----------------------------------------------------------------------------------------------
    //contrutor com parametros
    public Categoria(int cat_id, String cat_nome) {
        this.cat_id = cat_id;
        this.cat_nome = cat_nome;
        
    }
    //get e set

    /**
     * @return the cat_nome
     */
    public String getNome() {
        return cat_nome;
    }

    /**
     * @param cat_nome the cat_nome to set
     */
    public void setNome(String cat_nome) {
        this.cat_nome = cat_nome;
    }

    /**
     * @return the cat_id
     */
    public int getId() {
        return cat_id;
    }

    /**
     * @param cat_id the cat_id to set
     */
    public void setId(int cat_id) {
        this.cat_id = cat_id;
    }
    
}
