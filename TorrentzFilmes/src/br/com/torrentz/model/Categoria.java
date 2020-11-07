/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
<<<<<<< HEAD

=======
>>>>>>> main
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
    
    /**
     * 
     * @param nome 
     */
    public Categoria(String nome){
        this(0,nome);
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
=======
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
    public Categoria(String cat_nome, int cat_id) {
        this.cat_nome = cat_nome;
        this.cat_id = cat_id;
    }
    //get e set

    /**
     * @return the cat_nome
     */
    public String getCat_nome() {
        return cat_nome;
    }

    /**
     * @param cat_nome the cat_nome to set
     */
    public void setCat_nome(String cat_nome) {
        this.cat_nome = cat_nome;
    }

    /**
     * @return the cat_id
     */
    public int getCat_id() {
        return cat_id;
    }

    /**
     * @param cat_id the cat_id to set
     */
    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }
    
>>>>>>> main
}
