/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.torrentz.model;

/**
 *
 * @author marcos
 */
public class Filme {
    
    /* Atributos */
    private int id = 0;
    private String titulo = "";
    private String sinopse = "";
    private int ano = 0;
    private Categoria categoria = null;
  
    /* Métodos */
    /**
     * @default
     */
    public Filme(){
        this(0,"","",0,null);
    }   
     
    /**
     * 
     * @param titulo
     * @param sinopse
     * @param ano
     * @param categoria 
     */
    public Filme(String titulo, String sinopse, int ano, Categoria categoria){
        this(0, titulo, sinopse, ano, categoria);
        
    }
    
    /**
     * 
     * @param id
     * @param titulo
     * @param sinopse
     * @param ano
     * @param categoria 
     */
    public Filme(int id, String titulo, String sinopse, int ano, Categoria categoria){
        this.id = id;
        this.titulo = titulo;
        this.sinopse = sinopse;
        this.ano = ano;
        this.categoria = categoria;
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
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the sinopse
     */
    public String getSinopse() {
        return sinopse;
    }

    /**
     * @param sinopse the sinopse to set
     */
    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    /**
     * @return the ano
     */
    public int getAno() {
        return ano;
    }

    /**
     * @param ano the ano to set
     */
    public void setAno(int ano) {
        this.ano = ano;
    }

    /**
     * @return the categoria
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
