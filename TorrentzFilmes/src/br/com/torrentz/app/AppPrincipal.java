/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.torrentz.app;

import br.com.torrentz.bll.BllCategoria;
import br.com.torrentz.bll.BllFilme;
import br.com.torrentz.bll.BllPlano;
import br.com.torrentz.bll.BllUsuario;
import br.com.torrentz.bll.BllVisualizacao;
import static br.com.torrentz.generic.GenMensagem.*;
import br.com.torrentz.model.Categoria;
import br.com.torrentz.model.Contrato;
import br.com.torrentz.model.Filme;
import br.com.torrentz.model.Plano;
import br.com.torrentz.model.Usuario;
import br.com.torrentz.model.Visualizacao;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.Collection;

/**
 *
 * @author vovolinux
 */
public class AppPrincipal extends javax.swing.JFrame {

    private Usuario usuario = null; // Usuário da sessão
    private Iterable<Object> colecao = null;
    private Iterable<Usuario> usuarios = null;
    private Iterable<Contrato> contratos = null;
    private Iterable<Plano> planos = null;
    private Iterable<Filme> filmes = null;
    private Iterable<Categoria> categoria = null;
    private Iterable<Visualizacao> visualizacoes = null;

    private String cadastro = "";

    private void setUsuario(Usuario usuario) throws Exception {
        this.usuario = usuario;
        jLabelPerfil.setText(usuario.getPerfil() == 'U' ? "USUÁRIO:" : "ADMINISTRADOR:");
        jLabelUsuario.setText(usuario.getNome());
    }

    public void atualizarColecoes() throws Exception {
        usuarios = (Iterable) new BllUsuario().getAll();
        planos = (Iterable) new BllPlano().getAll();
        filmes = (Iterable) new BllFilme().getAll();
        categoria = (Iterable) new BllCategoria().getAll();
        visualizacoes = (Iterable) new BllVisualizacao().getAll();
    }

    private void jRadioButtonActionPerformed(ActionEvent evt) {
        try {
            jButtonAssistir.setVisible(evt.getActionCommand().equals("Filme"));
            jButtonAssistir.setEnabled(false);
            atualizarGrid(evt.getActionCommand());
            jButtonIncluir.setEnabled(usuario.getPerfil() == 'A');
            cadastro = evt.getActionCommand();
        } catch (Exception e) {
            mensagemErro(e);
        }
    }

    private void atualizarGrid(String objectName) throws Exception {
        colecao = new ArrayList<>();

        switch (objectName) {
            case "Plano":
                colecao = (Iterable) planos;
                break;

            case "Categoria":
                colecao = (Iterable) categoria;
                break;
//                jTablePrincipal.setModel(new DefaultTableModel());
//                throw new Exception("Pergunte ao Calebison!");

            case "Filme":
                colecao = (Iterable) filmes;
                break;

            case "Visualizacoes":

                if (usuario.getPerfil() == 'U') {
                    colecao = (Iterable) new ArrayList(new BllVisualizacao().buscaPorUsuario(usuario));
                } else {
                    colecao = (Iterable) visualizacoes;
                }
                break;

            case "Usuario":
                if (usuario.getPerfil() == 'U') {
                    colecao = (Iterable) new ArrayList(asList(usuario));
                } else {
                    colecao = (Iterable) usuarios;
                }
                break;

            case "Contrato":
                colecao = (Iterable) contratos;
                break;

        }
        new AppGridPrincipal<>().preencherGrid(colecao, jTablePrincipal);
    }

    private void incluirCadastro() {
        try {

            switch (cadastro) {
                case "Usuario":
                    AppUsuarioIncluir modalUsuario = new AppUsuarioIncluir(this, true);
                    modalUsuario.setTitle("Incluir cadastro de Usuário");
                    modalUsuario.usuarios = usuarios;
                    modalUsuario.planos = planos;
                    modalUsuario.setVisible(true);
                    break;

                case "Plano":
                    AppPlano modalPlano = new AppPlano(this, true);
                    modalPlano.setTitle("incluir cadastro de plano");
                    modalPlano.setVisible(true);

                    break;

//                case "Categoria":
//                    AppCategoria modalCategoria = new AppCategoria();
//                    modalCategoria.setTitle("incluir cadastro de categoria");
//                    modalCategoria.setVisible(true);
//                    break;
            }
            atualizarColecoes();

            atualizarGrid("");
        } catch (Exception e) {
            mensagemErro(e);
        }
    }

    private void editarCadastro() {
        try {
            int id = 0;

            switch (cadastro) {
                case "Usuario":
                    id = Integer.parseInt(jTablePrincipal.getValueAt(jTablePrincipal.getSelectedRow(), 0).toString());
                    Usuario usuarioEditar = new Usuario();
                    for (Usuario u : usuarios) {
                        if (u.getId() == id) {
                            usuarioEditar = u;
                            break;
                        }
                    }
                    AppUsuarioEditar modalUsuario = new AppUsuarioEditar(this, true, usuarioEditar);
                    modalUsuario.setTitle("Editar cadastro de Usuário");
                    modalUsuario.usuarios = usuarios;
                    modalUsuario.planos = planos;
                    modalUsuario.setVisible(true);
                    break;

                case "Plano":
                    AppPlano modalPlano = new AppPlano(this, true);
                    modalPlano.setTitle("incluir cadastro de plano");
                    modalPlano.setVisible(true);

                    break;

//                case "Categoria":
//                    AppCategoria modalCategoria = new AppCategoria();
//                    modalCategoria.setTitle("incluir cadastro de categoria");
//                    modalCategoria.setVisible(true);
//                    break;
            }
            atualizarColecoes();

            atualizarGrid("");
        } catch (Exception e) {
            mensagemErro(e);
        }
    }

    /**
     * Creates new form AppTelaPrincipal
     */
    private AppPrincipal() {
        initComponents();
        this.setLocationRelativeTo(null);
        try {
            atualizarColecoes();
        } catch (Exception e) {
            mensagemErro(e);
        }
    }

    /**
     * Creates new form AppTelaPrincipal
     */
    public AppPrincipal(Usuario usuario) {
        this();
        try {
            this.setUsuario(usuario);

            if (usuario.getPerfil() == 'U') {
                jButtonAssistir.setVisible(true);
                jRadioButtonFilmes.setSelected(true);
                atualizarGrid("Filme");

            } else {
                jButtonAssistir.setVisible(false);
                atualizarGrid("");
            }
        } catch (Exception e) {
            mensagemErro(e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT
     * modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jMenuItem2 = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePrincipal = new javax.swing.JTable();
        jButtonIncluir = new javax.swing.JButton();
        jRadioButtonUsuarios = new javax.swing.JRadioButton();
        jRadioButtonCategorias = new javax.swing.JRadioButton();
        jRadioButtonFilmes = new javax.swing.JRadioButton();
        jRadioButtonPlanos = new javax.swing.JRadioButton();
        jRadioButtonContratos = new javax.swing.JRadioButton();
        jLabelUsuario = new javax.swing.JLabel();
        jRadioButtonVisualizacoes = new javax.swing.JRadioButton();
        jLabelPerfil = new javax.swing.JLabel();
        jButtonAssistir = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuSistema = new javax.swing.JMenu();
        jMenuItemNovoLogin = new javax.swing.JMenuItem();
        jMenuItemSair = new javax.swing.JMenuItem();
        jMenuFilmes = new javax.swing.JMenu();
        jMenuItemCategorias = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItemContratos = new javax.swing.JMenuItem();
        jMenuItemPlanos = new javax.swing.JMenuItem();
        jMenuItemUsuarios = new javax.swing.JMenuItem();

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTablePrincipal.setAutoCreateRowSorter(true);
        jTablePrincipal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTablePrincipal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTablePrincipalMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTablePrincipal);
        if (jTablePrincipal.getColumnModel().getColumnCount() > 0) {
            jTablePrincipal.getColumnModel().getColumn(0).setMinWidth(80);
            jTablePrincipal.getColumnModel().getColumn(0).setPreferredWidth(80);
            jTablePrincipal.getColumnModel().getColumn(0).setMaxWidth(80);
        }

        jButtonIncluir.setText("Incluir");
        jButtonIncluir.setEnabled(false);
        jButtonIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIncluirActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonUsuarios);
        jRadioButtonUsuarios.setText("Usuários");
        jRadioButtonUsuarios.setActionCommand("Usuario");
        jRadioButtonUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonUsuariosActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonCategorias);
        jRadioButtonCategorias.setText("Categorias");
        jRadioButtonCategorias.setActionCommand("Categoria");
        jRadioButtonCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonCategoriasActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonFilmes);
        jRadioButtonFilmes.setText("Filmes");
        jRadioButtonFilmes.setActionCommand("Filme");
        jRadioButtonFilmes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonFilmesActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonPlanos);
        jRadioButtonPlanos.setText("Planos");
        jRadioButtonPlanos.setActionCommand("Plano");
        jRadioButtonPlanos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonPlanosActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonContratos);
        jRadioButtonContratos.setText("Contratos");
        jRadioButtonContratos.setActionCommand("Contrato");
        jRadioButtonContratos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonContratosActionPerformed(evt);
            }
        });

        jLabelUsuario.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabelUsuario.setText("jLabelUsuario");

        buttonGroup1.add(jRadioButtonVisualizacoes);
        jRadioButtonVisualizacoes.setText("Visualizações");
        jRadioButtonVisualizacoes.setActionCommand("Visualizacoes");
        jRadioButtonVisualizacoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonVisualizacoesActionPerformed(evt);
            }
        });

        jLabelPerfil.setText("PERFIL:");

        jButtonAssistir.setText("Assistir");
        jButtonAssistir.setEnabled(false);
        jButtonAssistir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAssistirActionPerformed(evt);
            }
        });

        jMenuSistema.setText("Sistema");

        jMenuItemNovoLogin.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, 0));
        jMenuItemNovoLogin.setText("Novo Login");
        jMenuItemNovoLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNovoLoginActionPerformed(evt);
            }
        });
        jMenuSistema.add(jMenuItemNovoLogin);

        jMenuItemSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, 0));
        jMenuItemSair.setText("Sair");
        jMenuItemSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSairActionPerformed(evt);
            }
        });
        jMenuSistema.add(jMenuItemSair);

        jMenuBar1.add(jMenuSistema);

        jMenuFilmes.setText("Cadastro");

        jMenuItemCategorias.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, 0));
        jMenuItemCategorias.setText("Categorias");
        jMenuItemCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCategoriasActionPerformed(evt);
            }
        });
        jMenuFilmes.add(jMenuItemCategorias);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, 0));
        jMenuItem1.setText("Filmes");
        jMenuFilmes.add(jMenuItem1);

        jMenuItemContratos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, 0));
        jMenuItemContratos.setText("Contratos");
        jMenuItemContratos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemContratosActionPerformed(evt);
            }
        });
        jMenuFilmes.add(jMenuItemContratos);

        jMenuItemPlanos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, 0));
        jMenuItemPlanos.setText("Planos");
        jMenuItemPlanos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPlanosActionPerformed(evt);
            }
        });
        jMenuFilmes.add(jMenuItemPlanos);

        jMenuItemUsuarios.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, 0));
        jMenuItemUsuarios.setText("Usuários");
        jMenuItemUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemUsuariosActionPerformed(evt);
            }
        });
        jMenuFilmes.add(jMenuItemUsuarios);

        jMenuBar1.add(jMenuFilmes);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 901, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelPerfil)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelUsuario)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonIncluir)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButtonUsuarios)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButtonCategorias)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButtonFilmes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButtonPlanos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButtonContratos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButtonVisualizacoes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonAssistir)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonIncluir)
                    .addComponent(jRadioButtonCategorias)
                    .addComponent(jRadioButtonFilmes)
                    .addComponent(jRadioButtonPlanos)
                    .addComponent(jRadioButtonContratos)
                    .addComponent(jRadioButtonUsuarios)
                    .addComponent(jRadioButtonVisualizacoes)
                    .addComponent(jButtonAssistir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUsuario)
                    .addComponent(jLabelPerfil))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemUsuariosActionPerformed

    }//GEN-LAST:event_jMenuItemUsuariosActionPerformed

    private void jMenuItemCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCategoriasActionPerformed
        mensagem("Atenção< DEV!", "Implementar carregamento do grid com o cadastro selecionado!");
    }//GEN-LAST:event_jMenuItemCategoriasActionPerformed

    private void jMenuItemPlanosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPlanosActionPerformed
        mensagem("Atenção< DEV!", "Implementar carregamento do grid com os cadastros de Planos");
    }//GEN-LAST:event_jMenuItemPlanosActionPerformed

    private void jMenuItemContratosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemContratosActionPerformed
        mensagem("Atenção< DEV!", "Implementar carregamento do grid com os cadastros de Categorias");
    }//GEN-LAST:event_jMenuItemContratosActionPerformed

    private void jButtonIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIncluirActionPerformed
        incluirCadastro();
    }//GEN-LAST:event_jButtonIncluirActionPerformed

    private void jMenuItemNovoLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNovoLoginActionPerformed
        try {
            this.setVisible(false);
            new AppLogin().setVisible(true);
            this.dispose();
        } catch (Exception e) {
            mensagemErro(e);
        }
    }//GEN-LAST:event_jMenuItemNovoLoginActionPerformed

    private void jMenuItemSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSairActionPerformed
        this.dispose();
    }//GEN-LAST:event_jMenuItemSairActionPerformed

    private void jRadioButtonUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonUsuariosActionPerformed
        jRadioButtonActionPerformed(evt);
    }//GEN-LAST:event_jRadioButtonUsuariosActionPerformed

    private void jRadioButtonCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonCategoriasActionPerformed
        jRadioButtonActionPerformed(evt);
    }//GEN-LAST:event_jRadioButtonCategoriasActionPerformed

    private void jRadioButtonFilmesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonFilmesActionPerformed
        jRadioButtonActionPerformed(evt);
    }//GEN-LAST:event_jRadioButtonFilmesActionPerformed

    private void jRadioButtonPlanosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonPlanosActionPerformed
        jRadioButtonActionPerformed(evt);
    }//GEN-LAST:event_jRadioButtonPlanosActionPerformed

    private void jRadioButtonContratosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonContratosActionPerformed
        jRadioButtonActionPerformed(evt);
    }//GEN-LAST:event_jRadioButtonContratosActionPerformed

    private void jRadioButtonVisualizacoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonVisualizacoesActionPerformed
        jRadioButtonActionPerformed(evt);
    }//GEN-LAST:event_jRadioButtonVisualizacoesActionPerformed

    private void jButtonAssistirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAssistirActionPerformed
        // TODO add your handling code here:
        try {
            if (jTablePrincipal.getSelectedRow() == -1) {
                throw new RuntimeException("Selecione o filme que deseja assistir!");
            }
            new AppVisualizar(usuario, (Filme) new ArrayList((Collection) filmes).get(jTablePrincipal.getSelectedRow())).setVisible(true);
            this.dispose();
        } catch (RuntimeException error) {
            mensagemErro(error);
        }

    }//GEN-LAST:event_jButtonAssistirActionPerformed

    private void jTablePrincipalMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePrincipalMouseReleased
        if ((evt.getClickCount() == 2)) {
            editarCadastro();
        }
        jButtonAssistir.setEnabled(usuario.getPerfil() == 'U');
    }//GEN-LAST:event_jTablePrincipalMouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AppPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AppPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButtonAssistir;
    private javax.swing.JButton jButtonIncluir;
    private javax.swing.JLabel jLabelPerfil;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuFilmes;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItemCategorias;
    private javax.swing.JMenuItem jMenuItemContratos;
    private javax.swing.JMenuItem jMenuItemNovoLogin;
    private javax.swing.JMenuItem jMenuItemPlanos;
    private javax.swing.JMenuItem jMenuItemSair;
    private javax.swing.JMenuItem jMenuItemUsuarios;
    private javax.swing.JMenu jMenuSistema;
    private javax.swing.JRadioButton jRadioButtonCategorias;
    private javax.swing.JRadioButton jRadioButtonContratos;
    private javax.swing.JRadioButton jRadioButtonFilmes;
    private javax.swing.JRadioButton jRadioButtonPlanos;
    private javax.swing.JRadioButton jRadioButtonUsuarios;
    private javax.swing.JRadioButton jRadioButtonVisualizacoes;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablePrincipal;
    // End of variables declaration//GEN-END:variables
}
