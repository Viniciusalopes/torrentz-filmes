/*
 *  ----------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Viniciusalopes (Vovolinux) <suporte@vovolinux.com.br>
 *  Criado em  : 22/10/2020 21:27:58 
 *  Projeto    : API Acervo Musical
 *  Versão     : 0.1.0
 *  ------------------------------------------------------------------------------------------------
 *  Propósito  : Gerar mensagens de alerta, erro e de opções para camada app em desktop.
 *  ------------------------------------------------------------------------------------------------
 *  Changelog:
 *  Autor      : NOME DO AUTORNOME DO AUTOR
 *  Data       : YYYY-mm-dd
 *  Versão     : n.n.n
 *  Alterações : DUPLICAR ESTE BLOCO E DESCREVER A(s) ALTERAÇÃO(ões) RELEVANTES PARA A VERSÃO.
 *               MANTER INDENTAÇÃO DE LINHAS ABAIXO DA PRIMEIRA LINHA E O LIMITE DE COLUNAS AQUI -->
 *  -----------------------------------------------------------------------------------------------| 
 */
package br.com.torrentz.generic;

import javax.swing.JOptionPane;

/**
 *
 * @author vovostudio
 */
public class GenMensagem {

    // --- MENSAGENS ------------------------------------------------------------------------------>
    //
    /**
     * Exibe uma mensagem informativa na tela.
     *
     * @param titulo Título da mensagem.
     * @param mensagem Texto da mensagem.
     */
    public static void mensagem(String titulo, String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Exibe uma mensagem de erro na tela.
     *
     * @param e Exception para obter o texto da mensagem.
     */
    public static void mensagemErro(Exception e) {
        String mensagem = ((e.getMessage() == null)
                ? ((e.getCause() == null)
                ? e.getStackTrace().toString()
                : e.getCause().getMessage())
                : e.getMessage());

        JOptionPane.showMessageDialog(null, mensagem, "Opa!", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Exibe pergunta e retorna a resposta do usuário.
     * <p>
     * Exemplo:
     * <p>
     * String[] opcoes = {"Sim", "Não", "Talvez"};
     * <p>
     * 0 = Sim
     * <p>
     * 1 = Não
     * <p>
     * 2 = Talvez
     *
     * @param mensagem Texto com a pergunta.
     * @param opcoes Lista com os labels dos botões de opção.
     * @return Inteiro com o índice da opção escolhida pelo usuário.
     *
     */
    public static int mensagemEscolher(String mensagem, String[] opcoes) {
        return JOptionPane.showOptionDialog(null, mensagem, "Escolha uma opção:",
                JOptionPane.CLOSED_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, 0);
    }

    // --- FIM MENSAGENS --------------------------------------------------------------------------|
}
