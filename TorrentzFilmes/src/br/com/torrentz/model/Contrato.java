/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.torrentz.model;

import java.util.Date;

/**
 *
 * @author vovolinux
 */
public class Contrato {

    private char status = 'A';
    private Date inicio = new Date();
    private Date fim = null;
    private int usuarioId = 0;
    private int planoId = 0;

    public Contrato() {

    }

    public Contrato(char status, Date inicio, Date fim, int usuarioId, int planoId) {
        this.status = status;
        this.inicio = inicio;
        this.fim = fim;
        this.usuarioId = usuarioId;
        this.planoId = planoId;
    }

    public char getStatus() {
        return status;
    }

    public Date getInicio() {
        return inicio;
    }

    public Date getFim() {
        return fim;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public int getPlanoId() {
        return planoId;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setPlanoId(int planoId) {
        this.planoId = planoId;
    }
    
    
}
