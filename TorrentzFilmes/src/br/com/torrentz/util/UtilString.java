/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.torrentz.util;

/**
 *
 * @author vovolinux
 */
public class UtilString {

    /**
     * Retorna o texto somente com números, retirando outros caracteres.
     *
     * @param texto Texto a ser tratado.
     * @return
     */
    public static String somenteNumeros(String texto) {
        String ret = "";
        String numeros = "1234567890";

        for (char c : texto.toCharArray()) {
            if (numeros.contains(c + "")) {
                ret += c;
            }
        }
        return ret;
    }
}
