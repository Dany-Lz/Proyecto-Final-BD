package GUI;

import Aux.CompetenciaListado;
import DAO.CompetenciaDAO;
import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ListadoComp extends javax.swing.JDialog {

    private CompetenciaDAO competenciaDAO;
    private List<CompetenciaListado> listaOriginal;
    private JTable tablaComp;

    public ListadoComp(java.awt.Frame parent, boolean modal, boolean edicion) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
        setTitle(edicion ? "Gestión de Entrenadores" : "Listado de Entrenadores");

        txtBuscar.setLimit(5);

        competenciaDAO = new CompetenciaDAO();
        crearTabla();
        cargarDatos();

        LocalDate hoy = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        lblFecha.setText("Fecha del reporte: " + hoy.format(formatter));

        btnModificar.setVisible(edicion);
        btnEliminar.setVisible(edicion);

    }

    private void crearTabla() {
        String[] columnas = {"Código", "Competencia", "Ciudad", "Sede", "Estado", "Participantes"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaComp = new JTable(model);
        tablaComp.setFont(new Font("Arial", Font.PLAIN, 12));
        tablaComp.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tablaComp.getTableHeader().setBackground(new Color(240, 248, 255));
        tablaComp.setRowHeight(25);
        tablaComp.setShowGrid(true);
        tablaComp.setGridColor(new Color(220, 220, 220));
        tablaComp.getTableHeader().setReorderingAllowed(false);
        tablaComp.getTableHeader().setResizingAllowed(false);

        tablaComp.getColumnModel().getColumn(0).setPreferredWidth(80);
        tablaComp.getColumnModel().getColumn(1).setPreferredWidth(200);
        tablaComp.getColumnModel().getColumn(2).setPreferredWidth(120);
        tablaComp.getColumnModel().getColumn(3).setPreferredWidth(120);
        tablaComp.getColumnModel().getColumn(4).setPreferredWidth(100);
        tablaComp.getColumnModel().getColumn(5).setPreferredWidth(100);

        scrollPane.setViewportView(tablaComp);
    }

    private void mostrarEnTabla(List<CompetenciaListado> lista) {
        DefaultTableModel model = (DefaultTableModel) tablaComp.getModel();
        model.setRowCount(0);

        for (CompetenciaListado c : lista) {
            model.addRow(new Object[]{
                c.getIdComp(),
                c.getNombreCompetencia(),
                c.getCiudad(),
                c.getSede(),
                c.getEstado(),
                c.getCantidadParticipantes()
            });
        }

        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron competencias.", "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void cargarDatos() {
        try {
            listaOriginal = competenciaDAO.listarConParticipantes();
            if (listaOriginal == null) {
                listaOriginal = new ArrayList<>();
            }
            mostrarEnTabla(listaOriginal);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        lblFecha = new javax.swing.JLabel();
        lblBuscar = new javax.swing.JLabel();
        txtBuscar = new Utils.JTextFieldEdited();
        btnBuscar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        panelTablas = new javax.swing.JPanel();
        scrollPane = new javax.swing.JScrollPane();
        btnModificar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jButton1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButton1.setText("Reiniciar filtros");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
        lblBuscar.setText("Buscar por Código:");

        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(520, 520, 520)
                        .addComponent(lblBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(174, 174, 174)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(panelTablas, javax.swing.GroupLayout.PREFERRED_SIZE, 722, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(lblBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        txtBuscar.setText("");

        mostrarEnTabla(listaOriginal);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        btnBuscarActionPerformed(evt);
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        String idBuscado = txtBuscar.getText().trim();
        List<CompetenciaListado> filtrados = new ArrayList<>();

        for (CompetenciaListado c : listaOriginal) {
            if (idBuscado.isEmpty()) {
                filtrados.add(c);
            } else if (c.getIdComp().contains(idBuscado)) {
                filtrados.add(c);
            }
        }

        mostrarEnTabla(filtrados);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        String idComp = getIdCompSeleccionado();
        String nombreComp = getNombreCompSeleccionado();
        boolean out = false;

        if (idComp == null) {
            JOptionPane.showMessageDialog(this,
                    "Debe seleccionar una competencia de la tabla para eliminarla.",
                    "Sin selección",
                    JOptionPane.WARNING_MESSAGE);
            out = true;
        }

        if (!out) {
            int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro que desea eliminar la competencia " + nombreComp + "?\n\n"
                    + "Se eliminarán también las inscripciones asociadas.",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (confirmacion == JOptionPane.YES_OPTION) {
                try {
                    competenciaDAO.eliminar(idComp);
                    JOptionPane.showMessageDialog(this,
                            "Competencia eliminada correctamente.",
                            "Éxito",
                            JOptionPane.INFORMATION_MESSAGE);
                    refreshTable();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this,
                            "Error al eliminar: " + e.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        String idComp = getIdCompSeleccionado();
        boolean out = false;

        if (idComp == null) {
            JOptionPane.showMessageDialog(this,
                    "Debe seleccionar una competencia de la tabla para modificarla.",
                    "Sin selección",
                    JOptionPane.WARNING_MESSAGE);
            out = true;
        }

        if (!out) {
            EditarComp editar = new EditarComp(null, true, idComp);
            editar.setVisible(true);
            refreshTable();
        }
    }//GEN-LAST:event_btnModificarActionPerformed

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
            java.util.logging.Logger.getLogger(ListadoComp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListadoComp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListadoComp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListadoComp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ListadoComp dialog = new ListadoComp(new javax.swing.JFrame(), true, false);
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

    private String getIdCompSeleccionado() {
        int fila = tablaComp.getSelectedRow();
        if (fila == -1) {
            return null;
        }
        return tablaComp.getValueAt(fila, 0).toString();
    }

    private String getNombreCompSeleccionado() {
        int fila = tablaComp.getSelectedRow();
        if (fila == -1) {
            return null;
        }
        return tablaComp.getValueAt(fila, 1).toString();
    }

    public void refreshTable() {
        cargarDatos();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JPanel panelTablas;
    private javax.swing.JScrollPane scrollPane;
    private Utils.JTextFieldEdited txtBuscar;
    // End of variables declaration//GEN-END:variables
}
