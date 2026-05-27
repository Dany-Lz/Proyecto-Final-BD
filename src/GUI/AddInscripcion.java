package GUI;

import DAO.AtletaDAO;
import DAO.CompetenciaDAO;
import DAO.EntrenadorDAO;
import DAO.InscripcionDAO;
import Logic.Atleta;
import Logic.Competencia;
import Logic.Entrenador;
import Logic.Inscripcion;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public class AddInscripcion extends javax.swing.JDialog {

    private AtletaDAO atletaDAO;
    private CompetenciaDAO competenciaDAO;
    private EntrenadorDAO entrenadorDAO;
    private InscripcionDAO inscripcionDAO;
    private ArrayList<Atleta> listaAtletas;
    private ArrayList<Competencia> listaCompetencias;
    private ArrayList<Entrenador> listaEntrenadores;

    public AddInscripcion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);

        atletaDAO = new AtletaDAO();
        competenciaDAO = new CompetenciaDAO();
        entrenadorDAO = new EntrenadorDAO();
        inscripcionDAO = new InscripcionDAO();

        boolean hayAtletas = cargarAtletas();
        boolean hayCompetencias = cargarCompetenciasProgramadas();
        cargarEntrenadores();

        if (!hayAtletas || !hayCompetencias) {
            JOptionPane.showMessageDialog(this,
                    "No hay atletas o competencias PROGRAMADAS disponibles para realizar una inscripción.\n"
                    + "Debe registrar al menos un atleta y una competencia en estado PROGRAMADA.",
                    "Datos insuficientes",
                    JOptionPane.WARNING_MESSAGE);
            dispose();
            return;
        }

        jDateInicio.setDate(new Date());
    }

    private boolean cargarAtletas() {
        try {
            listaAtletas = atletaDAO.listar();
            comboAtleta.removeAllItems();
            if (listaAtletas == null || listaAtletas.isEmpty()) {
                comboAtleta.addItem("-- No hay atletas registrados --");
                comboAtleta.setEnabled(false);
                return false;
            }
            for (Atleta a : listaAtletas) {
                comboAtleta.addItem(a.getNumId() + " - " + a.getNombre() + " " + a.getApellidos());
            }
            comboAtleta.setEnabled(true);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar atletas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            comboAtleta.addItem("-- Error al cargar --");
            comboAtleta.setEnabled(false);
            return false;
        }
    }

    private boolean cargarCompetenciasProgramadas() {
        try {
            listaCompetencias = competenciaDAO.listarProgramadas();
            comboComp.removeAllItems();
            if (listaCompetencias == null || listaCompetencias.isEmpty()) {
                comboComp.addItem("-- No hay competencias PROGRAMADAS --");
                comboComp.setEnabled(false);
                return false;
            }
            for (Competencia c : listaCompetencias) {
                comboComp.addItem(c.getId() + " - " + c.getNombre());
            }
            comboComp.setEnabled(true);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar competencias: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            comboComp.addItem("-- Error al cargar --");
            comboComp.setEnabled(false);
            return false;
        }
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
            comboEntrenador.addItem("-- Error al cargar --");
            comboEntrenador.setEnabled(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblSede = new javax.swing.JLabel();
        comboAtleta = new javax.swing.JComboBox<>();
        lblAtleta = new javax.swing.JLabel();
        comboEntrenador = new javax.swing.JComboBox<>();
        comboComp = new javax.swing.JComboBox<>();
        lblSede2 = new javax.swing.JLabel();
        lblSede3 = new javax.swing.JLabel();
        jDateInicio = new com.toedter.calendar.JDateChooser();
        btnGuardar = new javax.swing.JButton();
        btnCancelar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nueva Inscripción");
        setResizable(false);

        lblSede.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblSede.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSede.setText("Competencia:");

        comboAtleta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboAtleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboAtletaActionPerformed(evt);
            }
        });

        lblAtleta.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblAtleta.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblAtleta.setText("Atleta:");

        comboEntrenador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboEntrenador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboEntrenadorActionPerformed(evt);
            }
        });

        comboComp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboComp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCompActionPerformed(evt);
            }
        });

        lblSede2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblSede2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSede2.setText("Entrenador:");

        lblSede3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblSede3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSede3.setText("Fecha de Inicio:");

        btnGuardar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(51, 51, 51));
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnCancelar1.setForeground(new java.awt.Color(51, 51, 51));
        btnCancelar1.setText("Cancelar");
        btnCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblAtleta, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSede))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(comboAtleta, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblSede2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboEntrenador, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(comboComp, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                        .addComponent(lblSede3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(135, 135, 135)))
                .addGap(54, 54, 54))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboAtleta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboEntrenador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSede2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAtleta, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSede, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSede3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboAtletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboAtletaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboAtletaActionPerformed

    private void comboEntrenadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboEntrenadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboEntrenadorActionPerformed

    private void comboCompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCompActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboCompActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        Date fechaInicioDate = jDateInicio.getDate();
        LocalDate fechaInicio = null;

        boolean esValido = true;

        // Validar que haya atletas cargados
        if (comboAtleta.getSelectedIndex() == -1 || !comboAtleta.isEnabled()) {
            esValido = false;
            JOptionPane.showMessageDialog(this, "No hay atletas disponibles para inscribir.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Validar que haya competencias cargadas
        if (esValido && (comboComp.getSelectedIndex() == -1 || !comboComp.isEnabled())) {
            esValido = false;
            JOptionPane.showMessageDialog(this, "No hay competencias PROGRAMADAS disponibles.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Validar fecha de inicio
        if (esValido && fechaInicioDate == null) {
            esValido = false;
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha de inicio.", "Campo inválido", JOptionPane.WARNING_MESSAGE);
        }

        // Validar que la fecha de inicio no sea anterior a la fecha actual (opcional)
        if (esValido && fechaInicioDate != null) {
            fechaInicio = fechaInicioDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (fechaInicio.isBefore(LocalDate.now())) {
                esValido = false;
                JOptionPane.showMessageDialog(this, "La fecha de inicio no puede ser anterior a la fecha actual.", "Fecha inválida", JOptionPane.WARNING_MESSAGE);
            }
        }

        // Obtener IDs seleccionados
        String idAtleta = null;
        String idComp = null;
        String ciEntren = null;

        if (esValido) {
            idAtleta = listaAtletas.get(comboAtleta.getSelectedIndex()).getNumId();
            idComp = listaCompetencias.get(comboComp.getSelectedIndex()).getId();

            if (comboEntrenador.getSelectedIndex() > 0) {
                ciEntren = listaEntrenadores.get(comboEntrenador.getSelectedIndex() - 1).getCi();
            }
        }

        // Confirmación
        if (esValido) {
            String entrenadorTexto = (ciEntren == null) ? "Ninguno" : ciEntren;
            int respuesta = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro que desea inscribir al atleta?\n\n"
                    + "Atleta: " + comboAtleta.getSelectedItem() + "\n"
                    + "Competencia: " + comboComp.getSelectedItem() + "\n"
                    + "Fecha de inicio: " + fechaInicio + "\n"
                    + "Entrenador: " + entrenadorTexto,
                    "Confirmar inscripción",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (respuesta != JOptionPane.YES_OPTION) {
                esValido = false;
            }
        }

        // Guardar
        if (esValido) {
            Inscripcion inscripcion = new Inscripcion(
                    idAtleta,
                    idComp,
                    fechaInicio,
                    null, // fecha_fin_real (NULL al crear)
                    "Participante", //resultado por defecto
                    false, //completa por defecto
                    0, // cant_medallas por defecto
                    ciEntren
            );

            try {
                inscripcionDAO.insertar(inscripcion);
                JOptionPane.showMessageDialog(this, "Inscripción guardada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnCancelar1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar1;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> comboAtleta;
    private javax.swing.JComboBox<String> comboComp;
    private javax.swing.JComboBox<String> comboEntrenador;
    private com.toedter.calendar.JDateChooser jDateInicio;
    private javax.swing.JLabel lblAtleta;
    private javax.swing.JLabel lblSede;
    private javax.swing.JLabel lblSede2;
    private javax.swing.JLabel lblSede3;
    // End of variables declaration//GEN-END:variables
}
