/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.torrentz.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author vovolinux
 */
public class UtilEmail {

    /**
     * Valida o preenchimento do campo E-Mail.
     *
     * @param email Endereço de e-mail válido.
     * @throws Exception
     */
    public static void validarEmail(String email) throws Exception {
        // FONTE: https://receitasdecodigo.com.br/java/validar-email-em-java
        boolean emailValido = false;
        email = email.trim();
        
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                emailValido = true;
            }
        }

        if (!emailValido) {
            throw new Exception("E-mail inválido!");
        }
    }
}
