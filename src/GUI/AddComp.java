package GUI;

import DAO.CompetenciaDAO;
import DAO.SedeDAO;
import Logic.Competencia;
import Logic.Sede;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public class AddComp extends javax.swing.JDialog {

    private CompetenciaDAO competenciaDAO;
    private SedeDAO sedeDAO;
    private ArrayList<Sede> listaSedes;

    public AddComp(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);

        txtId.setLimit(5);

        competenciaDAO = new CompetenciaDAO();
        sedeDAO = new SedeDAO();

        cargarSedes();
    }

    private void cargarSedes() {
        try {
            listaSedes = sedeDAO.listar();
            comboSedes.removeAllItems();
            for (Sede s : listaSedes) {
                comboSedes.addItem(s.getNombre());
            }
            if (comboSedes.getItemCount() == 0) {
                comboSedes.addItem("-- No hay sedes registradas --");
                comboSedes.setEnabled(false);
            } else {
                comboSedes.setEnabled(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar sedes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDateInicio = new com.toedter.calendar.JDateChooser();
        lblNombre = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        lblFecFin = new javax.swing.JLabel();
        lblFecInicio = new javax.swing.JLabel();
        txtId = new Utils.JTextFieldEdited();
        txtNombre = new Utils.JTextFieldEdited();
        jDateFin = new com.toedter.calendar.JDateChooser();
        comboSedes = new javax.swing.JComboBox<>();
        lblSede = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nueva Competencia");

        lblNombre.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNombre.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNombre.setText("Nombre:");

        lblId.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblId.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblId.setText("Cod. Competencia:");

        lblFecFin.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblFecFin.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFecFin.setText("Fecha de Fin:");

        lblFecInicio.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblFecInicio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFecInicio.setText("Fecha de Inicio:");

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        comboSedes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboSedes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSedesActionPerformed(evt);
            }
        });

        lblSede.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblSede.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSede.setText("Sede:");

        btnGuardar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(51, 51, 51));
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(51, 51, 51));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblId)
                        .addGap(12, 12, 12)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblFecInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jDateInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblFecFin, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jDateFin, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(lblSede, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(232, 232, 232)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(comboSedes, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lblId))
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFecInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jDateInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lblNombre))
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFecFin, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateFin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSede, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboSedes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void comboSedesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSedesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboSedesActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        String idComp = txtId.getText().trim();
        String nombre = txtNombre.getText().trim();
        String estado = "Programada";
        Date fechaInicioDate = jDateInicio.getDate();
        Date fechaFinDate = jDateFin.getDate();

        boolean idValido = true;
        boolean nombreValido = true;
        boolean fechaInicioValida = true;
        boolean fechaFinValida = true;
        boolean sedeValida = true;
        boolean confirmacionOk = true;
        boolean guardadoExitoso = true;
        String mensajeError = "";

        //Validación de ID
        if (idComp.isEmpty()) {
            idValido = false;
            mensajeError = "El código de competencia es obligatorio.";
            txtId.requestFocus();
        }

        //Validación de nombre
        if (nombre.isEmpty()) {
            nombreValido = false;
            mensajeError = "El nombre de la competencia es obligatorio.";
            txtNombre.requestFocus();
        }

        //Validación de fecha de inicio
        if (fechaInicioDate == null) {
            fechaInicioValida = false;
            mensajeError = "Debe seleccionar una fecha de inicio.";
        }

        //Validación de fecha de fin
        if (fechaFinDate == null) {
            fechaFinValida = false;
            mensajeError = "Debe seleccionar una fecha de fin.";
        }

        //Validación de fechas (que la fecha fin no sea anterior a la fecha inicio)
        if (fechaInicioDate != null && fechaFinDate != null) {
            LocalDate fechaInicio = fechaInicioDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate fechaFin = fechaFinDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (fechaFin.isBefore(fechaInicio)) {
                fechaFinValida = false;
                mensajeError = "La fecha de fin no puede ser anterior a la fecha de inicio.";
            }
        }

        //Validación de sede
        if (comboSedes.getSelectedIndex() == -1 || comboSedes.getItemCount() == 0 || !comboSedes.isEnabled()) {
            sedeValida = false;
            mensajeError = "Debe seleccionar una sede válida.";
        }

        //Mostrar error si hay campos inválidos
        if (!idValido || !nombreValido || !fechaInicioValida || !fechaFinValida || !sedeValida) {
            JOptionPane.showMessageDialog(this, mensajeError, "Campo inválido", JOptionPane.WARNING_MESSAGE);
        }

        //Confirmación si todo está válido
        if (idValido && nombreValido && fechaInicioValida && fechaFinValida && sedeValida) {
            LocalDate fechaInicio = fechaInicioDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate fechaFin = fechaFinDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            String idSede = listaSedes.get(comboSedes.getSelectedIndex()).getId();

            int respuesta = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro que desea agregar la siguiente competencia?\n\n"
                    + "ID: " + idComp + "\n"
                    + "Nombre: " + nombre + "\n"
                    + "Estado: " + estado + "\n"
                    + "Fecha Inicio: " + fechaInicio + "\n"
                    + "Fecha Fin: " + fechaFin + "\n"
                    + "Sede: " + comboSedes.getSelectedItem(),
                    "Confirmar inserción",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (respuesta != JOptionPane.YES_OPTION) {
                confirmacionOk = false;
            }
        }

        // Guardar si todo es correcto
        if (idValido && nombreValido && fechaInicioValida && fechaFinValida && sedeValida && confirmacionOk) {
            LocalDate fechaInicio = jDateInicio.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate fechaFin = jDateFin.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            String idSede = listaSedes.get(comboSedes.getSelectedIndex()).getId();

            Competencia competencia = new Competencia(idComp, nombre, estado, fechaInicio, fechaFin, idSede);
            try {
                competenciaDAO.insertar(competencia);
                JOptionPane.showMessageDialog(this, "Competencia guardada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                guardadoExitoso = true;
                limpiarCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                guardadoExitoso = false;
            }
        }

        // Cerrar solo si se guardó correctamente
        if (idValido && nombreValido && fechaInicioValida && fechaFinValida && sedeValida && confirmacionOk && guardadoExitoso) {
            dispose();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        jDateInicio.setDate(null);
        jDateFin.setDate(null);
        if (comboSedes.getItemCount() > 0 && comboSedes.isEnabled()) {
            comboSedes.setSelectedIndex(0);
        }
        txtId.requestFocus();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> comboSedes;
    private com.toedter.calendar.JDateChooser jDateFin;
    private com.toedter.calendar.JDateChooser jDateInicio;
    private javax.swing.JLabel lblFecFin;
    private javax.swing.JLabel lblFecInicio;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblSede;
    private Utils.JTextFieldEdited txtId;
    private Utils.JTextFieldEdited txtNombre;
    // End of variables declaration//GEN-END:variables
}
