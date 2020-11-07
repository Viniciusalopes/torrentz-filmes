/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.torrentz.bll;

import br.com.principal.model.EmailServer;
import br.com.torrentz.model.Usuario;
import br.com.torrentz.util.UtilEmail;
import java.util.Random;

/**
 *
 * @author vovolinux
 */
public class BllRedefinirSenha {

    
    public static boolean emailValido(String email) throws Exception {
        UtilEmail.validarEmail(email);
        return true;
    }

    public static String redefinir(String email) throws Exception {
        try {
            Usuario u = null;
            u = new BllUsuario().searchByLogin(email);
            if (u == null) {
                throw new Exception("Nenhum usuário cadastrado com este e-mail!");
            } else {
                // Enviar e-mail com código de verificação
                String codigo = new Random(9999).nextInt() + "";

                EmailServer mail = new EmailServer();
                mail.enviar(email, "Recuperação de Senha",
                        "Seu código de verificação é: " + codigo);

                return codigo;
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
