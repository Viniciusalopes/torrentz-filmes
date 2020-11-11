/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.torrentz.util;

import br.com.torrentz.bll.BllVisualizacao;
import br.com.torrentz.model.Visualizacao;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;

/**
 *
 * @author marcos
 */
public class TemporizadorJProgressBar extends Thread{
    /* Atributos */
    private JProgressBar jProgressBar = null;
    private boolean paused = false;
    private boolean allDone = false;
    private Visualizacao visualizacao = null;
    
    /* Métodos */
    /**
     * @default
     */
    private TemporizadorJProgressBar(){
    }
    
    /**
     * Construtor
     * @param jProgressBar 
     */
    public TemporizadorJProgressBar(JProgressBar jProgressBar){
        this.jProgressBar = jProgressBar;
    }
    
    public void salvarVisuzalizacao(Visualizacao visualizacao) throws Exception{
        try {
            this.visualizacao = visualizacao;
            this.visualizacao.setCompleto(false);
            new BllVisualizacao().configura(visualizacao);
           
        } catch (Exception error) {
            throw error;
        }
    }
    /**
     * Método para incrementar valor do progressbar a cada 1 segundo
     * @void
     */
    @Override
    public void run(){
        while(jProgressBar.getValue() < 100){
            try {
                
                super.sleep(1000);
                jProgressBar.setValue(jProgressBar.getValue() +1); 
                if(jProgressBar.getValue() == 100){
                    visualizacao.setCompleto(true);
                    System.out.println(true);
                    
                    try {new BllVisualizacao().configura(visualizacao);} 
                    catch (Exception error) {
                         System.out.println(error.getMessage());
                    }
                }
            } catch (InterruptedException ex) {
               ex.printStackTrace();
            }
        }
    }
    
    /**
     * Método inicia a thered ou reinicia
     * obs: a Thread tem um ciclo de vida infinito
     * @void
     */
    public void start(){
        if(!allDone){
            allDone = true;
            super.start();
        }else{
            resumir();
        }
    }
    
    /**
     * Verifica se thered está pausada
     * @return 
     */
    public boolean isPausa(){
        return paused;
    }
    /**
     * Se a thered estiver pausada ela retorna ao ciclo
     * @void
     */
    public  void resumir(){
        if(paused){
            super.resume();
            this.paused = false;
        }

    }
    /**
     * Se a thered estiver sendo executa ela é pausada
     * @void
     */
    public void suspender(){
        if(!paused){
            super.suspend();
            this.paused = true;
        }
    }
    /**
     * 
     * @void
     */
    public void parar(){
        if(jProgressBar.getValue() != 0){
            suspender();
            this.jProgressBar.setValue(0);
        }
    }
}
