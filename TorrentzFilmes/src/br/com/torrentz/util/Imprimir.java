/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.torrentz.util;
import javax.swing.JOptionPane;

/**
 *
 * @author marcos
 */
public class Imprimir {
   
    /* Atributos */

    /* Métodos */
    public static void mensagemDeErro(String error){
        JOptionPane.showMessageDialog(null, error,"Error",JOptionPane.ERROR_MESSAGE);    
    }
    
    public static void mensagemDeSucesso(String message){
        JOptionPane.showMessageDialog(null, message, "Sucesso!", JOptionPane.PLAIN_MESSAGE);
    } 
    
    public static boolean mensagemDePergunta(String message, String title){
            return JOptionPane.showConfirmDialog(
                null,
                message,
                title,
                JOptionPane.YES_NO_OPTION) == 
                JOptionPane.YES_OPTION;
    }

}
