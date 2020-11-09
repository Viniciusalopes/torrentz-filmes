/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.torrentz.bll;

import br.com.principal.model.EmailServer;
import br.com.torrentz.model.Usuario;
import br.com.torrentz.util.UtilEmail;
import java.util.ArrayList;

/**
 *
 * @author vovolinux
 */
public class BllRedefinirSenha {

    private static Usuario usuario = null;

    public static Usuario getUsuario() {
        return usuario;
    }

    public static boolean emailValido(String email) throws Exception {
        return UtilEmail.isValid(email);
    }

    public static String redefinir(String email, Iterable<Usuario> usuarios) throws Exception {
        try {
            usuario = null;
            for (Usuario u : usuarios) {
                if (u.getEmail().equalsIgnoreCase(email)) {
                    usuario = u;
                }
            }

            if (usuario == null) {
                throw new Exception("Nenhum usuário cadastrado com este e-mail!");
            } else {
                // Enviar e-mail com código de verificação
                int minValor = 1000;
                int maxValor = 9999;
                String codigo = String.format("%d", (int) (Math.random() * (maxValor - minValor) + minValor));

                UtilEmail.sendEmail(email, "Torrentz Filmes - Recuperação de Senha",
                        "Olá, " + usuario.getNome().split(" ")[0] + "!\n"
                        + "O código de verificação para redefinir sua de senha é : " + codigo
                        + "\n\n"
                        + "Caso você não tenha solicitado a recuperação de senha no sistema da Torrentz Filmes, "
                        + "apenas ignore este mensagem que seu cadastro não será afetado."
                        + "\n\nAtenciosamente,\nTorrentz Filmes"
                );
                return codigo;
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
