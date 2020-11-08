/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.torrentz.app;

import br.com.torrentz.model.Contrato;
import br.com.torrentz.model.Usuario;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vovolinux
 */
public class AppGridPrincipal<T> {

    Object object = null;
    String[] colunas = new String[]{};
    int[] larguras = new int[]{};
    Object[][] linhas = new Object[][]{};

    public void preencherGrid(Iterable<T> colecao, JTable grid) throws Exception {

        DefaultTableModel model = (DefaultTableModel) grid.getModel();
        ArrayList<T> lista = (ArrayList<T>) colecao;
        if (colecao == null) {
            model = new DefaultTableModel();
        } else if (lista.size() == 0) {
            model = new DefaultTableModel();
        } else {

            switch (lista.get(0).getClass().getSimpleName()) {

                case "Plano":
                    throw new Exception("Pergunte ao Lucas!");

                case "Categoria":
                case "Filme":
                    throw new Exception("Pergunte ao Calebe!");

                case "Visualizacoes":
                    throw new Exception("Pergunte ao Marcos Paulo!");

                case "Usuario":
                    colunas = new String[]{"ID", "Nome", "CPF", "E-mail"};
                    larguras = new int[]{80, -1, -1, -1};
                    linhas = new Object[lista.size()][colunas.length];

                    for (int l = 0; l < lista.size(); l++) {
                        Usuario u = (Usuario) lista.get(l);
                        Object[] linha = new Object[]{
                            u.getId(),
                            u.getNome(),
                            u.getCpf(),
                            u.getEmail()
                        };
                        linhas[l] = linha;
                    }
                    break;

                case "Contrato":
                    colunas = new String[]{"ID", "Cliente", "Plano", "Início", "Fim",};
                    larguras = new int[]{80, -1, -1, -1, -1};
                    linhas = new Object[lista.size()][colunas.length];

                    for (int l = 0; l < lista.size(); l++) {
                        Contrato c = (Contrato) lista.get(l);
                        Object[] linha = new Object[]{
                            "",
                            "",
                            "",
                            "",
                            ""
                        };
                        linhas[l] = linha;
                    }
                    break;
            }
        }
        setModel(grid, model);
    }

    private void setModel(JTable grid, DefaultTableModel model) {
        boolean[] edit = new boolean[colunas.length];
        for (boolean b : edit) {
            b = false;
        }

        grid.setModel(new javax.swing.table.DefaultTableModel(linhas, colunas
        ) {
            boolean[] canEdit = edit;

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        if (model.getColumnCount() > 0) {
            for (int i = 0; i < colunas.length; i++) {
                if (larguras[i] > -1) { // -1 == AUTO
                    grid.getColumnModel().getColumn(i).setMinWidth(larguras[i]);
                    grid.getColumnModel().getColumn(i).setPreferredWidth(larguras[i]);
                    grid.getColumnModel().getColumn(i).setMaxWidth(larguras[i]);
                }
            }
        }
    }
}