package GUI;

import javax.swing.*;

public class Login extends javax.swing.JFrame {

    public Login() {
        JOptionPane.showMessageDialog(null, "Usuario: admin\nContraseña: 0000");

        initComponents();

        passContra.setLimit(4);
        passContra.setEchoChar('\u2022');

        setIconImage(new ImageIcon(getClass().getResource("/Images/podio.png")).getImage());

        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lblUsuario = new javax.swing.JLabel();
        lblContra = new javax.swing.JLabel();
        passContra = new Utils.JPasswordFieldEdited();
        textUsuario = new Utils.JTextFieldEdited();
        checkMostrar = new javax.swing.JCheckBox();
        buttonInicio = new javax.swing.JButton();
        lblFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login -- Gestión de Competencia");
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        lblUsuario.setBackground(new java.awt.Color(255, 255, 255));
        lblUsuario.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblUsuario.setText("Usuario");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(35, 80, 0, 0);
        getContentPane().add(lblUsuario, gridBagConstraints);

        lblContra.setBackground(new java.awt.Color(255, 255, 255));
        lblContra.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblContra.setForeground(new java.awt.Color(255, 255, 255));
        lblContra.setText("Contraseña");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 16;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(26, 80, 0, 0);
        getContentPane().add(lblContra, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 185;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 80, 0, 0);
        getContentPane().add(passContra, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 185;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 80, 0, 0);
        getContentPane().add(textUsuario, gridBagConstraints);

        checkMostrar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        checkMostrar.setForeground(new java.awt.Color(255, 255, 255));
        checkMostrar.setText("Mostrar contraseña");
        checkMostrar.setOpaque(false);
        checkMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkMostrarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(21, 80, 0, 0);
        getContentPane().add(checkMostrar, gridBagConstraints);

        buttonInicio.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        buttonInicio.setText("Iniciar Sesión");
        buttonInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonInicioActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 34;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(51, 76, 0, 0);
        getContentPane().add(buttonInicio, gridBagConstraints);

        lblFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fondologin2.jpg"))); // NOI18N
        lblFondo.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.gridheight = 7;
        gridBagConstraints.ipadx = -22;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        getContentPane().add(lblFondo, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonInicioActionPerformed
        // TODO add your handling code here:
        String contra = new String(passContra.getPassword());

        if (textUsuario.getText().equals("admin") && contra.equals("0000")) {
            this.dispose();
            MenuPrincipal mn = new MenuPrincipal();
            mn.setVisible(true);
        } else
            JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrectos.", "Error al iniciar sesión.", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_buttonInicioActionPerformed

    private void checkMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkMostrarActionPerformed
        if (checkMostrar.isSelected())
            passContra.setEchoChar((char) 0);
        else
            passContra.setEchoChar('\u2022');
    }//GEN-LAST:event_checkMostrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonInicio;
    private javax.swing.JCheckBox checkMostrar;
    private javax.swing.JLabel lblContra;
    private javax.swing.JLabel lblFondo;
    private javax.swing.JLabel lblUsuario;
    private Utils.JPasswordFieldEdited passContra;
    private Utils.JTextFieldEdited textUsuario;
    // End of variables declaration//GEN-END:variables
}
