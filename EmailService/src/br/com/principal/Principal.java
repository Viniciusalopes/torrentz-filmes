/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.principal;

import br.com.principal.model.EmailServer;
import java.util.Random;

/**
 *
 * @author vovolinux
 */
public class Principal {

    public static void main(String[] args) {
        try {
            String codigo = (new Random(9999).nextInt() + "").replace("-", "");

            String email = "suporte@viniciusalopes.com.br";

            EmailServer mail = new EmailServer();
            mail.enviar(email, "Recuperação de Senha",
                    "Seu código de verificação é: " + codigo);

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
}
