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

public class EditarComp extends javax.swing.JDialog {

    private final CompetenciaDAO competenciaDAO;
    private final SedeDAO sedeDAO;
    private ArrayList<Sede> listaSedes;
    private final String idComp;

    public EditarComp(java.awt.Frame parent, boolean modal, String idComp) {
        super(parent, modal);
        this.idComp = idComp;
        initComponents();
        setLocationRelativeTo(parent);
        setTitle("Modificar Competencia - ID: " + idComp);

        competenciaDAO = new CompetenciaDAO();
        sedeDAO = new SedeDAO();

        cargarSedes();
        cargarDatosCompetencia();
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

    private void cargarDatosCompetencia() {
        try {
            Competencia c = competenciaDAO.obtenerPorId(idComp);
            if (c != null) {
                txtNombre.setText(c.getNombre());

                // Convertir LocalDate a Date para JDateChooser
                if (c.getFechaInicioPrevista() != null) {
                    Date fechaInicio = java.sql.Date.valueOf(c.getFechaInicioPrevista());
                    jDateInicio.setDate(fechaInicio);
                }
                if (c.getFechaFinPrevista() != null) {
                    Date fechaFin = java.sql.Date.valueOf(c.getFechaFinPrevista());
                    jDateFin.setDate(fechaFin);
                }

                // Seleccionar la sede en el combo
                for (int i = 0; i < listaSedes.size(); i++) {
                    if (listaSedes.get(i).getId().equals(c.getIdSede())) {
                        comboSedes.setSelectedIndex(i);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos de la competencia: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        comboSedes = new javax.swing.JComboBox<>();
        lblSede = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblFecFin = new javax.swing.JLabel();
        txtNombre = new Utils.JTextFieldEdited();
        jDateInicio = new com.toedter.calendar.JDateChooser();
        lblFecInicio = new javax.swing.JLabel();
        jDateFin = new com.toedter.calendar.JDateChooser();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        comboSedes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboSedes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSedesActionPerformed(evt);
            }
        });

        lblSede.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblSede.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSede.setText("Sede:");

        lblNombre.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblNombre.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNombre.setText("Nombre:");

        lblFecFin.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblFecFin.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFecFin.setText("Fecha de Fin:");

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        lblFecInicio.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblFecInicio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFecInicio.setText("Fecha de Inicio:");

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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lblSede, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(comboSedes, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addComponent(lblFecFin, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblFecInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateFin, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(232, 232, 232)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(144, 144, 144))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lblNombre))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblFecInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSede, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboSedes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblFecFin, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateFin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboSedesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSedesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboSedesActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // Obtener valores de los campos
        String nombre = txtNombre.getText().trim();
        Date fechaInicioDate = jDateInicio.getDate();
        Date fechaFinDate = jDateFin.getDate();

        boolean esValido = true;

        // Validación de nombre
        if (nombre.isEmpty()) {
            esValido = false;
            JOptionPane.showMessageDialog(this, "El nombre de la competencia es obligatorio.", "Campo inválido", JOptionPane.WARNING_MESSAGE);
            txtNombre.requestFocus();
        }

        // Validación de fecha de inicio
        if (esValido && fechaInicioDate == null) {
            esValido = false;
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha de inicio.", "Campo inválido", JOptionPane.WARNING_MESSAGE);
        }

        // Validación de fecha de fin
        if (esValido && fechaFinDate == null) {
            esValido = false;
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha de fin.", "Campo inválido", JOptionPane.WARNING_MESSAGE);
        }

        // Validación de fechas (que la fecha fin no sea anterior a la fecha inicio)
        if (esValido && fechaInicioDate != null && fechaFinDate != null) {
            LocalDate fechaInicio = fechaInicioDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate fechaFin = fechaFinDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (fechaFin.isBefore(fechaInicio)) {
                esValido = false;
                JOptionPane.showMessageDialog(this, "La fecha de fin no puede ser anterior a la fecha de inicio.", "Campo inválido", JOptionPane.WARNING_MESSAGE);
            }
        }

        // Validación de sede
        if (esValido && (comboSedes.getSelectedIndex() == -1 || comboSedes.getItemCount() == 0 || !comboSedes.isEnabled())) {
            esValido = false;
            JOptionPane.showMessageDialog(this, "Debe seleccionar una sede válida.", "Campo inválido", JOptionPane.WARNING_MESSAGE);
        }

        // Confirmación si todo está válido
        if (esValido) {
            LocalDate fechaInicio = fechaInicioDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate fechaFin = fechaFinDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            String idSede = listaSedes.get(comboSedes.getSelectedIndex()).getId();

            int respuesta = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro que desea MODIFICAR la siguiente competencia?\n\n"
                    + "ID: " + idComp + "\n"
                    + "Nombre: " + nombre + "\n"
                    + "Fecha Inicio: " + fechaInicio + "\n"
                    + "Fecha Fin: " + fechaFin + "\n"
                    + "Sede: " + comboSedes.getSelectedItem(),
                    "Confirmar modificación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (respuesta != JOptionPane.YES_OPTION) {
                esValido = false;
            }
        }

        // Guardar si todo es correcto
        if (esValido) {
            LocalDate fechaInicio = jDateInicio.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate fechaFin = jDateFin.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            String idSede = listaSedes.get(comboSedes.getSelectedIndex()).getId();

            Competencia competencia = new Competencia(idComp, nombre, "", fechaInicio, fechaFin, idSede);
            try {
                competenciaDAO.modificar(competencia);
                JOptionPane.showMessageDialog(this, "Competencia modificada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al modificar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> comboSedes;
    private com.toedter.calendar.JDateChooser jDateFin;
    private com.toedter.calendar.JDateChooser jDateInicio;
    private javax.swing.JLabel lblFecFin;
    private javax.swing.JLabel lblFecInicio;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblSede;
    private Utils.JTextFieldEdited txtNombre;
    // End of variables declaration//GEN-END:variables
}
