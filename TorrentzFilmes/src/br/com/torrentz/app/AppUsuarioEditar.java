/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.torrentz.app;

import br.com.torrentz.bll.BllContrato;
import br.com.torrentz.bll.BllPlano;
import br.com.torrentz.bll.BllUsuario;
import static br.com.torrentz.generic.GenMensagem.mensagemErro;
import br.com.torrentz.model.Contrato;
import br.com.torrentz.model.Plano;
import br.com.torrentz.model.Usuario;
import static br.com.torrentz.util.UtilCpf.cpfMask;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vovostudio
 */
public class AppUsuarioEditar extends javax.swing.JDialog {

    public Iterable<Usuario> usuarios = null;
    public Iterable<Plano> planos = null;
    public Iterable<Contrato> contratos = null;
    private BllUsuario bllUsuario = null;
    private BllPlano bllPlano = null;
    private BllContrato bllContrato = null;
    private Usuario usuario = null;
    private Contrato contrato = null;
    private Plano plano = null;

    SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Creates new form AppUsuario
     */
    private AppUsuarioEditar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(parent);
    }

    public AppUsuarioEditar(java.awt.Frame parent, boolean modal, Usuario usuario) {
        this(parent, modal);
        try {
            this.usuario = usuario;
            bllContrato = new BllContrato();
            bllPlano = new BllPlano();

            contratos = bllContrato.getByUsuarioId(usuario.getId());

            preencherCampos();
            preencherGridContratos();
        } catch (Exception e) {
            mensagemErro(e);
        }
    }

    private void preencherCampos() throws Exception {
        jTextFieldNome.setText(usuario.getNome());
        jTextFieldCpf.setText(cpfMask(usuario.getCpf()));
        jTextFieldEmail.setText(usuario.getEmail());
        jComboBoxPerfil.setSelectedIndex((usuario.getPerfil() == 'U') ? 0 : 1);
        jButtonIncluirContrato.setEnabled(usuario.getPerfil() == 'U');
    }

    private void preencherGridContratos() throws Exception {
        DefaultTableModel model = (DefaultTableModel) jTableContratos.getModel();
        model.setRowCount(0);
        for (Contrato c : contratos) {
            plano = bllPlano.getById(contrato.getPlanoId());

            model.addRow(new Object[]{
                c.getUsuarioId() + "." + c.getPlanoId(),
                plano.getPla_nome(),
                plano.getPla_acesso_simultaneo(),
                plano.getPla_preco(),
                formatoData.format(c.getInicio()) + "." + c.getUsuarioId() + "." + c.getPlanoId(),
                String.format("%.0f%%", usuario.getPorcentagem()),
                String.format("%.2f", (plano.getPla_preco() / 100) * usuario.getPorcentagem()),
                String.format("R$ %.2f", plano.getPla_preco())
            });
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT
     * modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldCpf = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jPasswordField = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableContratos = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jButtonIncluirContrato = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();
        jComboBoxPerfil = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel2.setText("Nome");

        jLabel3.setText("CPF");

        jLabel4.setText("Email");

        jLabel5.setText("Senha");

        jTableContratos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome do Plano", "Acessos simultâneos", "Preço do Plano", "Nº Cupom", "% Desconto", "Desconto (R$)", "Valor Mensalidade"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableContratos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableContratos);
        if (jTableContratos.getColumnModel().getColumnCount() > 0) {
            jTableContratos.getColumnModel().getColumn(0).setMinWidth(60);
            jTableContratos.getColumnModel().getColumn(0).setPreferredWidth(60);
            jTableContratos.getColumnModel().getColumn(0).setMaxWidth(60);
            jTableContratos.getColumnModel().getColumn(2).setMinWidth(140);
            jTableContratos.getColumnModel().getColumn(2).setPreferredWidth(140);
            jTableContratos.getColumnModel().getColumn(2).setMaxWidth(140);
            jTableContratos.getColumnModel().getColumn(3).setMinWidth(100);
            jTableContratos.getColumnModel().getColumn(3).setPreferredWidth(100);
            jTableContratos.getColumnModel().getColumn(3).setMaxWidth(100);
            jTableContratos.getColumnModel().getColumn(4).setMinWidth(80);
            jTableContratos.getColumnModel().getColumn(4).setPreferredWidth(80);
            jTableContratos.getColumnModel().getColumn(4).setMaxWidth(80);
            jTableContratos.getColumnModel().getColumn(5).setMinWidth(130);
            jTableContratos.getColumnModel().getColumn(5).setPreferredWidth(130);
            jTableContratos.getColumnModel().getColumn(5).setMaxWidth(130);
            jTableContratos.getColumnModel().getColumn(6).setMinWidth(130);
            jTableContratos.getColumnModel().getColumn(6).setPreferredWidth(130);
            jTableContratos.getColumnModel().getColumn(6).setMaxWidth(130);
            jTableContratos.getColumnModel().getColumn(7).setMinWidth(130);
            jTableContratos.getColumnModel().getColumn(7).setPreferredWidth(130);
            jTableContratos.getColumnModel().getColumn(7).setMaxWidth(130);
        }

        jLabel6.setText("Planos Contratados");

        jButtonIncluirContrato.setText("Incluir Contrato");
        jButtonIncluirContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIncluirContratoActionPerformed(evt);
            }
        });

        jButtonSalvar.setText("Salvar");

        jComboBoxPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Usuário", "Administrador" }));
        jComboBoxPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPerfilActionPerformed(evt);
            }
        });

        jLabel1.setText("Perfil");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 922, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonSalvar)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addComponent(jLabel5))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonIncluirContrato, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBoxPerfil, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonIncluirContrato)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSalvar)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonIncluirContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIncluirContratoActionPerformed
        try {
            AppContrato modal = new AppContrato(null, true);
            modal.setVisible(true);

        } catch (Exception e) {
            mensagemErro(e);
        }
    }//GEN-LAST:event_jButtonIncluirContratoActionPerformed

    private void jComboBoxPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPerfilActionPerformed
         try {
            jButtonIncluirContrato.setEnabled(jComboBoxPerfil.getSelectedIndex() == 0);
        } catch (Exception e) {
            mensagemErro(e);
        }
    }//GEN-LAST:event_jComboBoxPerfilActionPerformed

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
            java.util.logging.Logger.getLogger(AppUsuarioEditar.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppUsuarioEditar.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppUsuarioEditar.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppUsuarioEditar.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AppUsuarioEditar dialog = new AppUsuarioEditar(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonIncluirContrato;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JComboBox<String> jComboBoxPerfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableContratos;
    private javax.swing.JTextField jTextFieldCpf;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldNome;
    // End of variables declaration//GEN-END:variables
}
