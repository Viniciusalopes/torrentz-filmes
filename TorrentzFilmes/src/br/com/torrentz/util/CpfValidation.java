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
public class CpfValidation {
    // FONTE: https://gist.github.com/rdakar/bbcf2262db572ddffd65ba26ddd42e80

    public static boolean isValid(String value) {
        if (value == null) {
            return false;
        }
        try {
            return isCpf(value);
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isCpf(String cpf) {
        int[] multiplicador1 = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] multiplicador2 = new int[]{11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

        cpf = cpf.trim().replace(".", "").replace("-", "");

        if (cpf.length() != 11) {
            return false;
        }

        for (int j = 0; j < 10; j++) {
            if ((String.format("%011d", 0).replace("0", (j + ""))).equals(cpf)) {
                return false;
            }
        }

        if (cpf == "12345678909") {
            return false;
        }

        String tempCpf = cpf.substring(0, 9);
        int soma = 0;

        for (int i = 0; i < 9; i++) {
            soma += Integer.parseInt(tempCpf.charAt(i) + "") * multiplicador1[i];
        }

        int resto = soma % 11;

        if (resto < 2) {
            resto = 0;
        } else {
            resto = 11 - resto;
        }

        String digito = resto + "";
        tempCpf += digito;
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Integer.parseInt(tempCpf.charAt(i) + "") * multiplicador2[i];
        }

        resto = soma % 11;
        if (resto < 2) {
            resto = 0;
        } else {
            resto = 11 - resto;
        }

        digito += resto + "";

        return cpf.endsWith(digito);
    }
}
