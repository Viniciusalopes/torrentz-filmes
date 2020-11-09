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
public class UtilData {

    public static void validateFormatDate(String date) throws Exception {
        String[] split = date.split("/");
        String pattern = "Formato aceito: dd/mm/yyyy";
        
        if (split.length != 3) {
            throw new Exception("Data inválida!\n" + pattern);
        }
        if (split[0].trim().length() < 2) {
            throw new Exception("Dia inválido!\n" + pattern);
        }
        if (split[1].trim().length() < 2) {
            throw new Exception("Mês inválido!\n" + pattern);
        }
        if (split[2].trim().length() < 4) {
            throw new Exception("Ano inválido!\n" + pattern);
        }
    }
}
