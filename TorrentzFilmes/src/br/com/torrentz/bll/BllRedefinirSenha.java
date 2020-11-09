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

    private static String getCodigo() {
        int minValor = 1000;
        int maxValor = 9999;
        return String.format("%d", (int) (Math.random() * (maxValor - minValor) + minValor));
    }

    public static String enviarCodigo(String nome, String email, String finalidade) throws Exception {
        // Enviar e-mail com código de verificação 
        String codigo = getCodigo();
        System.out.println(codigo);
        String assunto = "Torrentz Filmes - ";
        String[] linhas = new String[3];
        linhas[0] = "Olá, " + nome + "\nO código de verificação para ";
        linhas[1] = "\n\nCaso você não tenha ";
        linhas[2] = "\n\nAtenciosamente,\nTorrentz Filmes";

        switch (finalidade) {
            case "recuperar":
                assunto += "Recuperação de Senha";
                linhas[0] += "redefinir sua de senha é : " + codigo;
                linhas[1] += "solicitado a recuperação de senha no sistema da Torrentz Filmes, "
                        + "apenas ignore este mensagem que seu cadastro não será afetado.";
                break;
            case "validar":
                assunto += "Verificação de Cadastro";
                linhas[0] += "validar seu cadastro é : " + codigo;
                linhas[1] += "realizado um cadastro no sistema da Torrentz Filmes, "
                        + "você pode ignorar essa mensagem.";
                break;
        }
        String mensagem = "";
        for (String linha : linhas) {
            mensagem += linha;
        }

        UtilEmail.sendEmail(email, assunto, mensagem);
        return codigo;
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
                return enviarCodigo(usuario.getNome().split(" ")[0], usuario.getEmail(), "recuperar");
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
