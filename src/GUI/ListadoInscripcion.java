package GUI;

import Aux.InscripcionListado;
import DAO.InscripcionDAO;
import DAO.CompetenciaDAO;
import Logic.Competencia;
import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ListadoInscripcion extends javax.swing.JDialog {

    private InscripcionDAO inscripcionDAO;
    private CompetenciaDAO competenciaDAO;
    private List<InscripcionListado> listaOriginal;
    private List<Competencia> listaCompetencias;
    private JTable tablaInscripcion;

    public ListadoInscripcion(java.awt.Frame parent, boolean modal, boolean edicion) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
        setTitle(edicion ? "Gestión de Inscripciones" : "Listado de Inscripciones");

        inscripcionDAO = new InscripcionDAO();
        competenciaDAO = new CompetenciaDAO();

        crearTabla();
        cargarCompetencias();
        cargarDatos();
        comboComoetencias.addActionListener(e -> btnBuscarActionPerformed(null));

        LocalDate hoy = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        lblFecha.setText("Fecha del reporte: " + hoy.format(formatter));

        btnModificar.setVisible(edicion);
        btnEliminar.setVisible(edicion);
    }

    private void crearTabla() {
        // Columnas visibles + ocultas
        String[] columnas = {"ID Atleta", "ID Comp", "Atleta", "Competencia", "Ciudad", "Sede",
            "Fecha Inicio", "Fecha Fin", "Resultado", "Medallas", "Entrenador"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaInscripcion = new JTable(model);

        // Ocultar las columnas de IDs (índices 0 y 1)
        tablaInscripcion.getColumnModel().getColumn(0).setMinWidth(0);
        tablaInscripcion.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaInscripcion.getColumnModel().getColumn(1).setMinWidth(0);
        tablaInscripcion.getColumnModel().getColumn(1).setMaxWidth(0);

        // Configurar ancho de columnas visibles (ahora los índices se corren)
        // Índice 2 = Atleta, 3 = Competencia, 4 = Ciudad, etc.
        tablaInscripcion.getColumnModel().getColumn(2).setPreferredWidth(150);
        tablaInscripcion.getColumnModel().getColumn(3).setPreferredWidth(150);
        tablaInscripcion.getColumnModel().getColumn(4).setPreferredWidth(100);
        tablaInscripcion.getColumnModel().getColumn(5).setPreferredWidth(100);
        tablaInscripcion.getColumnModel().getColumn(6).setPreferredWidth(90);
        tablaInscripcion.getColumnModel().getColumn(7).setPreferredWidth(90);
        tablaInscripcion.getColumnModel().getColumn(8).setPreferredWidth(100);
        tablaInscripcion.getColumnModel().getColumn(9).setPreferredWidth(80);
        tablaInscripcion.getColumnModel().getColumn(10).setPreferredWidth(100);

        scrollPane.setViewportView(tablaInscripcion);
    }

    private void cargarCompetencias() {
        try {
            listaCompetencias = competenciaDAO.listar();
            comboComoetencias.removeAllItems();
            comboComoetencias.addItem("-- Todas las competencias --");
            if (listaCompetencias != null) {
                for (Competencia c : listaCompetencias) {
                    comboComoetencias.addItem(c.getId() + " - " + c.getNombre());
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar competencias: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarDatos() {
        try {
            listaOriginal = inscripcionDAO.listarInscripciones();
            if (listaOriginal == null) {
                listaOriginal = new ArrayList<>();
            }
            mostrarEnTabla(listaOriginal);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarEnTabla(List<InscripcionListado> lista) {
        DefaultTableModel model = (DefaultTableModel) tablaInscripcion.getModel();
        model.setRowCount(0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (InscripcionListado i : lista) {
            String fechaFin = i.getFechaFin() != null ? i.getFechaFin().format(formatter) : "En curso";

            
            model.addRow(new Object[]{
                i.getIdAtleta(), 
                i.getIdComp(), 
                i.getNombreAtleta(), 
                i.getNombreCompetencia(), 
                i.getCiudad(), 
                i.getSede(), 
                i.getFechaInicio() != null ? i.getFechaInicio().format(formatter) : "",
                fechaFin, 
                i.getResultado(), 
                i.getNumeroMedallas(), 
                i.getEntrenadorAsignado() 
            });
        }

        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron inscripciones.", "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBuscar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        panelTablas = new javax.swing.JPanel();
        scrollPane = new javax.swing.JScrollPane();
        btnModificar = new javax.swing.JButton();
        btnReiniciar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        lblFecha = new javax.swing.JLabel();
        lblBuscar = new javax.swing.JLabel();
        txtBuscar = new Utils.JTextFieldEdited();
        lblFiltrar = new javax.swing.JLabel();
        comboComoetencias = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        btnBuscar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        panelTablas.setLayout(new java.awt.BorderLayout());
        panelTablas.add(scrollPane, java.awt.BorderLayout.CENTER);

        btnModificar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnReiniciar.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnReiniciar.setText("Reiniciar filtros");
        btnReiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReiniciarActionPerformed(evt);
            }
        });

        btnCerrar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        lblFecha.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        lblBuscar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblBuscar.setText("Buscar atleta por No. ID:");

        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });

        lblFiltrar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblFiltrar.setText("Filtrar por competencia:");

        comboComoetencias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboComoetencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboComoetenciasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(140, 140, 140)
                        .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(130, 130, 130)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(comboComoetencias, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(174, 174, 174)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(panelTablas, javax.swing.GroupLayout.PREFERRED_SIZE, 722, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(btnReiniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(520, 520, 520)
                            .addComponent(lblBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReiniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(comboComoetencias, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(panelTablas, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        String idBuscado = txtBuscar.getText().trim();
        String competenciaSeleccionada = (String) comboComoetencias.getSelectedItem();

        boolean hayFiltroCompetencia = (competenciaSeleccionada != null
                && !competenciaSeleccionada.equals("-- Todas las competencias --"));

        String nombreCompetenciaFiltro = null;
        if (hayFiltroCompetencia) {
            int guionPos = competenciaSeleccionada.indexOf(" - ");
            if (guionPos != -1) {
                nombreCompetenciaFiltro = competenciaSeleccionada.substring(guionPos + 3);
            } else {
                nombreCompetenciaFiltro = competenciaSeleccionada;
            }
        }

        List<InscripcionListado> filtrados = new ArrayList<>();

        for (InscripcionListado i : listaOriginal) {
            boolean coincideId = true;
            boolean coincideCompetencia = true;

            if (!idBuscado.isEmpty()) {
                coincideId = i.getNombreAtleta().toLowerCase().contains(idBuscado.toLowerCase());
            }

            if (hayFiltroCompetencia) {
                coincideCompetencia = i.getNombreCompetencia().equals(nombreCompetenciaFiltro);
            }

            if (coincideId && coincideCompetencia) {
                filtrados.add(i);
            }
        }

        mostrarEnTabla(filtrados);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        String idInscripcion = getIdInscripcionSeleccionada();
        boolean out = false;

        if (idInscripcion == null) {
            JOptionPane.showMessageDialog(this,
                    "Debe seleccionar una inscripción de la tabla para eliminarla.",
                    "Sin selección",
                    JOptionPane.WARNING_MESSAGE);
            out = true;
        }

        if (!out) {
            int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro que desea eliminar la inscripción seleccionada?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (confirmacion == JOptionPane.YES_OPTION) {
                // TODO: Implementar eliminación cuando esté disponible
                JOptionPane.showMessageDialog(this, "Funcionalidad en desarrollo: Eliminar inscripción", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        int fila = tablaInscripcion.getSelectedRow();
        boolean out = false;

        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una inscripción.", "Sin selección", JOptionPane.WARNING_MESSAGE);
            out = true;
        }

        if (!out) {
            String idAtleta = tablaInscripcion.getValueAt(fila, 0).toString();
            String idComp = tablaInscripcion.getValueAt(fila, 1).toString();

            EditarInscripcion editar = new EditarInscripcion(null, true, idAtleta, idComp);
            editar.setVisible(true);
            refreshTable();
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnReiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReiniciarActionPerformed
        // TODO add your handling code here:
        txtBuscar.setText("");
        comboComoetencias.setSelectedIndex(0);
        mostrarEnTabla(listaOriginal);
    }//GEN-LAST:event_btnReiniciarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        btnBuscarActionPerformed(evt);
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void comboComoetenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboComoetenciasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboComoetenciasActionPerformed

    private String getIdInscripcionSeleccionada() {
        int fila = tablaInscripcion.getSelectedRow();
        if (fila == -1) {
            return null;
        }
        // Retorna el nombre del atleta + competencia como identificador temporal
        String atleta = tablaInscripcion.getValueAt(fila, 0).toString();
        String competencia = tablaInscripcion.getValueAt(fila, 1).toString();
        return atleta + " - " + competencia;
    }

    public void refreshTable() {
        cargarDatos();
    }

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
            java.util.logging.Logger.getLogger(ListadoInscripcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListadoInscripcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListadoInscripcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListadoInscripcion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ListadoInscripcion dialog = new ListadoInscripcion(new javax.swing.JFrame(), true, false);
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
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnReiniciar;
    private javax.swing.JComboBox<String> comboComoetencias;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblFiltrar;
    private javax.swing.JPanel panelTablas;
    private javax.swing.JScrollPane scrollPane;
    private Utils.JTextFieldEdited txtBuscar;
    // End of variables declaration//GEN-END:variables
}
