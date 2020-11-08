/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.torrentz.model;

/**
 *
<<<<<<< HEAD
 * @author marcos
 */
public class Categoria {
    
    /* Atributos */
    private int id =0;
    private String nome = "";
    
    /* Métodos */
    /**
     * @default
     */
    public Categoria() {
        this(0,"");
    }
    
<<<<<<< HEAD
    /**
     * 
     * @param nome 
     */
    public Categoria(String nome){
        this(0,nome);
=======
    //----------------------------------------------------------------------------------------------
    //contrutor com parametros
    public Categoria(int cat_id, String cat_nome) {
        this.cat_id = cat_id;
        this.cat_nome = cat_nome;
        
>>>>>>> main
    }
    
    /**
     * 
     * @param id
     * @param nome 
     */
    public Categoria(int id, String nome){
        this.id = id;
        this.nome = nome;    
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }     

}
