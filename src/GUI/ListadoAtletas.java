package GUI;

import Aux.AtletaListado;
import DAO.AtletaDAO;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

public class ListadoAtletas extends javax.swing.JDialog {

    private AtletaDAO atletaDAO;
    private List<AtletaListado> listaOriginal;
    private JTable tablaAtletas;

    public ListadoAtletas(java.awt.Frame parent, boolean modal, boolean edicion) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
        setTitle(edicion ? "Gestión de Atletas" : "Listado de Atletas");

        txtBuscar.setLimit(11);

        atletaDAO = new AtletaDAO();
        crearTabla();
        cargarDatos();
        comboPaises.addActionListener(e -> btnBuscarActionPerformed(null));

        LocalDate hoy = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        lblFecha.setText("Fecha del reporte: " + hoy.format(formatter));

        btnModificar.setVisible(edicion);
        btnEliminar.setVisible(edicion);
    }

    private void crearTabla() {
        //Crear la tabla con columnas
        String[] columnas = {"ID", "Atleta", "País", "Competencias", "Medallas"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaAtletas = new JTable(model);
        tablaAtletas.setFont(new Font("Arial", Font.PLAIN, 12));
        tablaAtletas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tablaAtletas.getTableHeader().setBackground(new Color(240, 248, 255));
        tablaAtletas.setRowHeight(25);
        tablaAtletas.setShowGrid(true);
        tablaAtletas.setGridColor(new Color(220, 220, 220));

        //Configurar ancho de columnas
        tablaAtletas.getColumnModel().getColumn(0).setPreferredWidth(120);
        tablaAtletas.getColumnModel().getColumn(1).setPreferredWidth(250);
        tablaAtletas.getColumnModel().getColumn(2).setPreferredWidth(100);
        tablaAtletas.getColumnModel().getColumn(3).setPreferredWidth(100);
        tablaAtletas.getColumnModel().getColumn(4).setPreferredWidth(80);

        //Evitar mover filas/columnas
        tablaAtletas.getTableHeader().setReorderingAllowed(false);
        tablaAtletas.getTableHeader().setResizingAllowed(false);

        //Agregar la tabla al scrollPane
        scrollPane.setViewportView(tablaAtletas);
    }

    private void cargarDatos() {
        try {
            listaOriginal = atletaDAO.listarConMedallas();
            if (listaOriginal == null) {
                listaOriginal = new ArrayList<>();
            }

            //Cargar combo de países
            comboPaises.removeAllItems();
            comboPaises.addItem("-- Todos los países --");

            List<String> paisesTemp = new ArrayList<>();
            for (AtletaListado a : listaOriginal) {
                String pais = a.getPais();
                if (pais != null && !paisesTemp.contains(pais)) {
                    paisesTemp.add(pais);
                    comboPaises.addItem(pais);
                }
            }

            //Mostrar todos los atletas inicialmente
            mostrarEnTabla(listaOriginal);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    

    private void mostrarEnTabla(List<AtletaListado> lista) {
        DefaultTableModel model = (DefaultTableModel) tablaAtletas.getModel();
        model.setRowCount(0);  //Limpiar tabla

        for (AtletaListado a : lista) {
            model.addRow(new Object[]{
                a.getNumIdentificacion(),
                a.getNombreAtleta(),
                a.getPais(),
                a.getCantCompetencias(),
                a.getTotalMedallas()
            });
        }

        //Si no hay resultados
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron atletas con los filtros seleccionados.", "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTablas = new javax.swing.JPanel();
        scrollPane = new javax.swing.JScrollPane();
        btnCerrar = new javax.swing.JButton();
        lblFecha = new javax.swing.JLabel();
        lblBuscar = new javax.swing.JLabel();
        lblFiltrar = new javax.swing.JLabel();
        txtBuscar = new Utils.JTextFieldEdited();
        comboPaises = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Listado de Atletas");
        setIconImage(null);
        setResizable(false);

        panelTablas.setLayout(new java.awt.BorderLayout());
        panelTablas.add(scrollPane, java.awt.BorderLayout.CENTER);

        btnCerrar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        lblFecha.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        lblBuscar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblBuscar.setText("Buscar por No. ID:");

        lblFiltrar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblFiltrar.setText("Filtrar por país:");

        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });

        comboPaises.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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

        btnModificar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jButton1.setText("Reiniciar filtros");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
                        .addComponent(lblFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(284, 284, 284)
                        .addComponent(lblBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(panelTablas, javax.swing.GroupLayout.PREFERRED_SIZE, 722, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(140, 140, 140)
                        .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(130, 130, 130)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboPaises, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(174, 174, 174)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(3, 3, 3)))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboPaises, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
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

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        btnBuscarActionPerformed(evt);
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        String idBuscado = txtBuscar.getText().trim();
        String paisSeleccionado = (String) comboPaises.getSelectedItem();

        List<AtletaListado> filtrados = new ArrayList<>();

        for (AtletaListado a : listaOriginal) {
            boolean coincidePais = true;
            boolean coincideId = true;

            //Filtro por país (si hay selección)
            if (paisSeleccionado != null && !paisSeleccionado.equals("-- Todos los países --")) {
                coincidePais = a.getPais().equals(paisSeleccionado);
            }

            //Filtro por ID (si hay texto)
            if (!idBuscado.isEmpty()) {
                coincideId = a.getNumIdentificacion().contains(idBuscado);
            }

            if (coincidePais && coincideId) {
                filtrados.add(a);
            }
        }

        mostrarEnTabla(filtrados);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        String idAtleta = getIdAtletaSeleccionado();
        boolean out = false;

        if (idAtleta == null) {
            JOptionPane.showMessageDialog(this,
                    "Debe seleccionar un atleta de la tabla para modificarlo.",
                    "Sin selección",
                    JOptionPane.WARNING_MESSAGE);
            out = true;
        }

        if (!out) {
            EditarAtleta editar = new EditarAtleta(null, true, idAtleta);
            editar.setVisible(true);

            refreshTable();
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        String idAtleta = getIdAtletaSeleccionado();
        boolean salir = false;

        if (idAtleta == null) {
            JOptionPane.showMessageDialog(this,
                    "Debe seleccionar un atleta de la tabla para eliminarlo.",
                    "Sin selección",
                    JOptionPane.WARNING_MESSAGE);
            salir = true;
        }

        if (!salir) {
            int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro que desea eliminar al atleta seleccionado?\n\n"
                    + "Se eliminarán también sus inscripciones y marcas asociadas.",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (confirmacion == JOptionPane.YES_OPTION) {
                try {
                    atletaDAO.eliminar(idAtleta);
                    JOptionPane.showMessageDialog(this,
                            "Atleta eliminado correctamente.",
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        comboPaises.setSelectedIndex(0);
        txtBuscar.setText("");

        mostrarEnTabla(listaOriginal);
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(ListadoAtletas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListadoAtletas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListadoAtletas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListadoAtletas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ListadoAtletas dialog = new ListadoAtletas(new javax.swing.JFrame(), true, false);
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

    private String getIdAtletaSeleccionado() {
        int fila = tablaAtletas.getSelectedRow();
        if (fila == -1) {
            return null;
        }
        return tablaAtletas.getValueAt(fila, 0).toString();
    }

    public void refreshTable() {
        cargarDatos();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> comboPaises;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblFiltrar;
    private javax.swing.JPanel panelTablas;
    private javax.swing.JScrollPane scrollPane;
    private Utils.JTextFieldEdited txtBuscar;
    // End of variables declaration//GEN-END:variables
}
