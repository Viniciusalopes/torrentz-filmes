/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.torrentz.model;

import br.com.torrentz.util.Data;

/**
 *
 * @author marcos
 */
public class Visualizacao {
    /* Atributos */
    private Data data = null;
    private boolean completo = false;
    private Usuario usuario = null;
    private Filme filme = null;
    
    /* Métodos */
    public Visualizacao(){
        this(null,false,null,null);
    }
    /**
     * 
     * @param data
     * @param completo
     * @param usuario
     * @param filme 
     */
    public Visualizacao(Data data,boolean completo,Usuario usuario,Filme filme){
        this.data = data;
        this.completo = completo;
        this.usuario = usuario;
        this.filme = filme;
    }

    /**
     * @return the data
     */
    public Data getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Data data) {
        this.data = data;
    }

    /**
     * @return the completo
     */
    public boolean isCompleto() {
        return completo;
    }

    /**
     * @param completo the completo to set
     */
    public void setCompleto(boolean completo) {
        this.completo = completo;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the filme
     */
    public Filme getFilme() {
        return filme;
    }

    /**
     * @param filme the filme to set
     */
    public void setFilme(Filme filme) {
        this.filme = filme;
    }    
}
