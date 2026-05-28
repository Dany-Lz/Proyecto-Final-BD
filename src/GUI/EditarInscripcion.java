package GUI;

import DAO.EntrenadorDAO;
import DAO.InscripcionDAO;
import DAO.CompetenciaDAO;
import Logic.Entrenador;
import Logic.Competencia;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public class EditarInscripcion extends javax.swing.JDialog {

    private InscripcionDAO inscripcionDAO;
    private EntrenadorDAO entrenadorDAO;
    private CompetenciaDAO competenciaDAO;
    private ArrayList<Entrenador> listaEntrenadores;

    private String idAtleta;
    private String idCompetencia;
    private String estadoCompetencia;

    public EditarInscripcion(java.awt.Frame parent, boolean modal, String idAtleta, String idCompetencia) {
        super(parent, modal);
        initComponents();
        this.idAtleta = idAtleta;
        this.idCompetencia = idCompetencia;
        setLocationRelativeTo(parent);
        setTitle("Editar Inscripción - " + idAtleta + " / " + idCompetencia);

        inscripcionDAO = new InscripcionDAO();
        entrenadorDAO = new EntrenadorDAO();
        competenciaDAO = new CompetenciaDAO();

        cargarResultadosPosibles();
        cargarEntrenadores();
        cargarCompetencia();
        cargarDatosInscripcion();
        configurarPermisos();

        // Listener para cambios en resultado
        comboResultado.addActionListener(e -> onResultadoChanged());
    }

    private void cargarResultadosPosibles() {
        comboResultado.removeAllItems();
        comboResultado.addItem("Participante");
        comboResultado.addItem("Finalista");
        comboResultado.addItem("Ganador");
        comboResultado.setEnabled(true);
    }

    private void cargarEntrenadores() {
        try {
            listaEntrenadores = entrenadorDAO.listar();
            comboEntrenador.removeAllItems();
            comboEntrenador.addItem("-- Ninguno --");
            if (listaEntrenadores != null && !listaEntrenadores.isEmpty()) {
                for (Entrenador e : listaEntrenadores) {
                    comboEntrenador.addItem(e.getCi() + " - " + e.getNombre() + " " + e.getApellidos());
                }
            }
            comboEntrenador.setEnabled(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar entrenadores: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            comboEntrenador.setEnabled(false);
        }
    }

    private void cargarCompetencia() {
        try {
            Competencia competencia = competenciaDAO.obtenerPorId(idCompetencia);
            if (competencia != null) {
                estadoCompetencia = competencia.getEstado();
                System.out.println("Estado competencia: " + estadoCompetencia);
            } else {
                System.out.println("Competencia no encontrada");
                estadoCompetencia = "Desconocido";
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar competencia: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            estadoCompetencia = "Error";
        }
    }

    private void cargarDatosInscripcion() {
        try {
            Object[] datos = inscripcionDAO.obtenerDatosInscripcion(idAtleta, idCompetencia);
            if (datos != null && datos.length >= 4) {
                String ciEntren = (String) datos[0];
                String resultado = (String) datos[1];
                int medallas = (int) datos[2];
                LocalDate fechaFinReal = (LocalDate) datos[3];

                // Seleccionar entrenador
                if (ciEntren != null && !ciEntren.isEmpty()) {
                    for (int i = 0; i < comboEntrenador.getItemCount(); i++) {
                        String item = comboEntrenador.getItemAt(i);
                        if (item.startsWith(ciEntren)) {
                            comboEntrenador.setSelectedIndex(i);
                            break;
                        }
                    }
                } else {
                    comboEntrenador.setSelectedIndex(0);
                }

                // Seleccionar resultado
                if (resultado != null) {
                    comboResultado.setSelectedItem(resultado);
                }

                spinnerMedallas.setValue(medallas);

                if (fechaFinReal != null) {
                    Date fecha = java.sql.Date.valueOf(fechaFinReal);
                    jDateFin.setDate(fecha);
                } else {
                    jDateFin.setDate(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos de la inscripción: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void configurarPermisos() {
        boolean esFinalizada = "Finalizada".equals(estadoCompetencia);

        System.out.println("Configurando permisos - esFinalizada: " + esFinalizada);

        // Entrenador siempre editable
        comboEntrenador.setEnabled(true);

        // Resultado, medallas y fecha fin SOLO si competencia está finalizada
        comboResultado.setEnabled(esFinalizada);
        spinnerMedallas.setEnabled(esFinalizada);
        jDateFin.setEnabled(esFinalizada);

        // Si no está finalizada, limpiar la fecha
        if (!esFinalizada) {
            jDateFin.setDate(null);
        }
    }

    private void onResultadoChanged() {
        String resultado = (String) comboResultado.getSelectedItem();
        if ("Participante".equals(resultado)) {
            spinnerMedallas.setValue(0);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        comboEntrenador = new javax.swing.JComboBox<>();
        lblEntrenador = new javax.swing.JLabel();
        comboResultado = new javax.swing.JComboBox<>();
        lblResultado = new javax.swing.JLabel();
        lblMedallas = new javax.swing.JLabel();
        spinnerMedallas = new javax.swing.JSpinner();
        lblFechaFin = new javax.swing.JLabel();
        jDateFin = new com.toedter.calendar.JDateChooser();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblInfo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        comboEntrenador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboEntrenador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboEntrenadorActionPerformed(evt);
            }
        });

        lblEntrenador.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblEntrenador.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEntrenador.setText("Entrenador:");

        comboResultado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboResultado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboResultadoActionPerformed(evt);
            }
        });

        lblResultado.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblResultado.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblResultado.setText("Resultado:");

        lblMedallas.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblMedallas.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMedallas.setText("Cant. medallas obtenidas:");

        spinnerMedallas.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));

        lblFechaFin.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblFechaFin.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFechaFin.setText("Fecha de Fin:");

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

        lblInfo.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        lblInfo.setForeground(new java.awt.Color(204, 204, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblResultado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblEntrenador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboEntrenador, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblFechaFin)
                    .addComponent(lblMedallas, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jDateFin, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(spinnerMedallas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(218, 218, 218)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(34, 34, 34))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboEntrenador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEntrenador, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMedallas, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerMedallas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboResultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateFin, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(lblInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboEntrenadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboEntrenadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboEntrenadorActionPerformed

    private void comboResultadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboResultadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboResultadoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        boolean esValido = true;

        int indexEntrenador = comboEntrenador.getSelectedIndex();
        String ciEntren = (indexEntrenador > 0) ? listaEntrenadores.get(indexEntrenador - 1).getCi() : null;

        String resultado = (String) comboResultado.getSelectedItem();
        int medallas = (int) spinnerMedallas.getValue();
        Date fechaFinDate = jDateFin.getDate();
        LocalDate fechaFinReal = null;

        if (fechaFinDate != null) {
            fechaFinReal = fechaFinDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }

        if ("Finalizada".equals(estadoCompetencia)) {
            if (fechaFinReal == null && ("Ganador".equals(resultado) || "Finalista".equals(resultado))) {
                esValido = false;
                JOptionPane.showMessageDialog(this,
                        "Para asignar resultado '" + resultado + "' debe registrar la fecha de fin real.",
                        "Validación", JOptionPane.WARNING_MESSAGE);
            }

            if (medallas < 0) {
                esValido = false;
                JOptionPane.showMessageDialog(this,
                        "La cantidad de medallas no puede ser negativa.",
                        "Validación", JOptionPane.WARNING_MESSAGE);
            }
        }

        if (esValido) {
            try {
                inscripcionDAO.actualizarInscripcion(idAtleta, idCompetencia,
                        ciEntren, resultado, medallas, fechaFinReal);
                JOptionPane.showMessageDialog(this, "Inscripción actualizada correctamente.",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al guardar: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
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
    private javax.swing.JComboBox<String> comboEntrenador;
    private javax.swing.JComboBox<String> comboResultado;
    private com.toedter.calendar.JDateChooser jDateFin;
    private javax.swing.JLabel lblEntrenador;
    private javax.swing.JLabel lblFechaFin;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JLabel lblMedallas;
    private javax.swing.JLabel lblResultado;
    private javax.swing.JSpinner spinnerMedallas;
    // End of variables declaration//GEN-END:variables
}
