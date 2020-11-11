/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.torrentz.app;

import br.com.torrentz.model.Categoria;
import br.com.torrentz.model.Contrato;
import br.com.torrentz.model.Filme;
import br.com.torrentz.model.Plano;
import br.com.torrentz.model.Usuario;
import br.com.torrentz.model.Visualizacao;
import static br.com.torrentz.util.UtilCpf.cpfMask;
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
        } else if (lista.isEmpty()) {
            model = new DefaultTableModel();
        } else {

            int l = 0;
            switch (lista.get(0).getClass().getSimpleName()) {
                case "Plano":
                    //_____________________________________________________________________________
                    colunas = new String[]{"ID", "Nome do Plano", "Acessos", "Preço Do Plano"};
                    //nomes das colunas 
                    larguras = new int[]{80, -1, -1, -1};
                    //largura da coluna

                    //matriz de linhas 
                    linhas = new Object[lista.size()][colunas.length];
                    //_____________________________________________________________________________

                    //ate esta parte do codigo a matriz esta vazia, sera criada a estrutura de repetiçao 
                    //para preenche-la
                    //inclusao das linhas dentro da matriz
                    //____________________________________________________________________________
                    for (l = 0; l < lista.size(); l++) {
                        Plano p = (Plano) lista.get(l);
                        Object[] linha = new Object[]{
                            p.getPla_id(),
                            p.getPla_nome(),
                            p.getPla_acesso_simultaneo(),
                            p.getPla_preco()
                        };
                        linhas[l] = linha;

                    }
                    break;
                //_________________________________________________________________________________

                case "Categoria":
//                    throw new Exception("Pergunte ao Calebson!");
                    
                     //_____________________________________________________________________________
                    colunas = new String[]{"ID", "Nome da Categoria"};
                    //nomes das colunas 
                    larguras = new int[]{80, -1};
                    //largura da coluna

                    //matriz de linhas 
                    linhas = new Object[lista.size()][colunas.length];
                    //_____________________________________________________________________________

                    //ate esta parte do codigo a matriz esta vazia, sera criada a estrutura de repetiçao 
                    //para preenche-la
                    //inclusao das linhas dentro da matriz
                    //____________________________________________________________________________
                    for (int i = 0; i < lista.size(); i++) {
                        Categoria c = (Categoria) lista.get(i);
                        Object[] linha = new Object[]{
                            c.getId(),
                            c.getNome(),
                            
                        };
                        linhas[i] = linha;

                    }
                    break;
                case "Filme":
                    colunas = new String[]{"ID","TITULO","ANO","CATEGORIA"};
                    larguras = new int[]{80, -1, -1, -1};
                    linhas = new Object[lista.size()][colunas.length];
                    l = 0;
                    
                    for (T f : lista) {                        
                        Filme filme = (Filme) f;
                        Object[] linha = new Object[]{
                           filme.getId(),
                            filme.getTitulo(),
                            filme.getAno(),
                            filme.getCategoria().getNome()
                        };
                        linhas[l] = linha;
                        l++;
                    }
                    break;
                    
                case "Visualizacao":
                    colunas = new String[]{"DATA","COMPLETO","USUARIO","FILME"};
                    larguras = new int[]{80, -1, -1, -1};
                    linhas = new Object[lista.size()][colunas.length];
                    l = 0;
                    
                    for (T v : lista) {                        
                        Visualizacao visualizacao = (Visualizacao) v;
                        Object[] linha = new Object[]{
                           visualizacao.getData().get(),
                            visualizacao.isCompleto(),
                            visualizacao.getUsuario().getNome(),
                            visualizacao.getFilme().getTitulo()
                        };
                        linhas[l] = linha;
                        l++;
                    }
                    break;
                case "Usuario":
                    colunas = new String[]{"ID", "Nome", "CPF", "E-mail"};
                    larguras = new int[]{80, -1, -1, -1};
                    linhas = new Object[lista.size()][colunas.length];
                    
                    
                    for (l = 0; l < lista.size(); l++) {
                        Usuario u = (Usuario) lista.get(l);
                        Object[] linha = new Object[]{
                            u.getId(),
                            u.getNome(),
                            cpfMask(u.getCpf()),
                            u.getEmail()
                        };
                        linhas[l] = linha;
                    }
                    break;

                case "Contrato":
                    colunas = new String[]{"ID", "Cliente", "Plano", "Início", "Fim",};
                    larguras = new int[]{80, -1, -1, -1, -1};
                    linhas = new Object[lista.size()][colunas.length];

                    for (l = 0; l < lista.size(); l++) {
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
