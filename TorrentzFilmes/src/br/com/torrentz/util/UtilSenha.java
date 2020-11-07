/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.torrentz.util;

import java.security.MessageDigest;

/**
 *
 * @author vovolinux
 */
public class UtilSenha {

    /**
     * Criptografa texto com SHA256.
     *
     * @param senha
     * @return
     * @throws Exception
     * @see https://www.devmedia.com.br/como-funciona-a-criptografia-hash-em-java/31139
     */
    public static String getHexStringSha256(String senha) throws Exception {

        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8"));

        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            hexString.append(String.format("%02X", 0xFF & b));
        }
        return hexString.toString();
    }
}
