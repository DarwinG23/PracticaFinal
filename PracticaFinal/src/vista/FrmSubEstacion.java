/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import controlador.SubEstacionDao;
import controlador.TDA.listas.Exception.EmptyException;
import java.io.File;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import vista.tablas.ModeloTablaSubEstacion;
import vista.util.UtilVista;

/**
 *
 * @author darwin
 */
public class FrmSubEstacion extends javax.swing.JFrame {

    private File fportadaUno;
    private File fportadaDos;
    private File fportadaTres;
    private SubEstacionDao subEstacionDao = new SubEstacionDao();
    ModeloTablaSubEstacion mte = new ModeloTablaSubEstacion();

    public FrmSubEstacion() {
        initComponents();
        panelImageFondo.setIcon(new ImageIcon("foto/fondoSubEstaciones.jpg"));
        setLocationRelativeTo(null);
        cargarTabla();
    }

    public Boolean verificar() {
        return (!txtNombre.getText().trim().isEmpty()
                && !txtLongitud.getText().trim().isEmpty()
                && !txtLatitud.getText().trim().isEmpty()
                && !txtNombre.getText().trim().isEmpty()
                && !txtPortadaUno.getText().trim().isEmpty()
                && !txtPortadaDos.getText().trim().isEmpty());
    }

    private File cargarFoto() throws Exception {
        File obj = null;
        JFileChooser chooser = new JFileChooser();
        chooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagenes", "jpg", "png", "jpeg");
        chooser.addChoosableFileFilter(filter);
        Integer resp = chooser.showOpenDialog(this);
        if (resp == JFileChooser.APPROVE_OPTION) {
            obj = chooser.getSelectedFile();
            System.out.println("hizo ok" + obj.getName());
        }
        return obj;
    }

    private void guardar() throws Exception {
        if (verificar()) {
            subEstacionDao.getSubEstacion().setNombre(txtNombre.getText());
            subEstacionDao.getSubEstacion().setCapacidad(txtCapacidad.getText());
            subEstacionDao.getSubEstacion().getUbicacion().setLatitud(Double.parseDouble(txtLatitud.getText()));

            subEstacionDao.getSubEstacion().getUbicacion().setLongitud(Double.parseDouble(txtLongitud.getText()));
            subEstacionDao.getSubEstacion().setUbicacion(subEstacionDao.getSubEstacion().getUbicacion());

            String nuevoNombre = generarCodigo(fportadaUno);
            UtilVista.copiarArchivo(fportadaUno, new File("foto/" + nuevoNombre));
            subEstacionDao.getSubEstacion().setPortadaUno(nuevoNombre);

            nuevoNombre = generarCodigo(fportadaDos);
            UtilVista.copiarArchivo(fportadaDos, new File("foto/" + nuevoNombre));
            subEstacionDao.getSubEstacion().setPortadaDos(nuevoNombre);

            if (txtPortadaTres.getText().equalsIgnoreCase("(opcional)")) {
                nuevoNombre = "SIN IMAGEN";
                System.out.println("hola");
            } else {
                System.out.println("adios");
                nuevoNombre = generarCodigo(fportadaTres);
                UtilVista.copiarArchivo(fportadaTres, new File("foto/" + nuevoNombre));
            }

           
            subEstacionDao.getSubEstacion().setPortadaOpcional(nuevoNombre);
            if (subEstacionDao.persist()) {
                JOptionPane.showMessageDialog(null, "Datos guardados");
                cargarTabla();
                limpiar();
                subEstacionDao.setSubEstacion(null);
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo guardar, hubo un error");
            }
        }

    }

    private String generarCodigo(File file) {
        String aux1 = UUID.randomUUID().toString();
        aux1 += "." + UtilVista.extension(file.getName());
        return aux1;
    }

    private void limpiar() {
        txtNombre.setText("");
        txtLongitud.setText("");
        txtLatitud.setText("");
        txtPortadaUno.setText("");
        txtPortadaDos.setText("");
        txtPortadaTres.setText("(opcional)");
        txtCapacidad.setText(" ");
    }

    public void cargarTabla() {
        mte.setLista(subEstacionDao.getLista());
        tbSubEstacion.setModel(mte);
        tbSubEstacion.updateUI();
        txtPortadaTres.setText("(opcional)");
    }

    private void adycencia() throws EmptyException, Exception {

        Integer o = subEstacionDao.getLista().getInfo(subEstacionDao.getLista().getLength() - 1).getId();
        Integer d = 0;
        Integer contadorAdyacencia = 0;
        System.out.println("o: " + o);
//        for (int i = 0; i < subEstacionDao.getLista().getLength(); i++) {
//            d = subEstacionDao.getLista().getInfo(i).getId();
//
//            if (o.intValue() != d.intValue()) {
//                for (int j = 0; j < subEstacionDao.getLista().getLength(); j++) {
//                    if (subEstacionDao.getGrafo().isEdge(subEstacionDao.getLista().getInfo(d), subEstacionDao.getLista().getInfo(j))) {
//                        contadorAdyacencia++;
//                        System.out.println("contador: " + contadorAdyacencia);
//                    }
//                }
//
//                if (!subEstacionDao.getGrafo().isEdge(subEstacionDao.getLista().getInfo(o), subEstacionDao.getLista().getInfo(d)) && contadorAdyacencia <= 3) {
//                    Double dist = UtilVista.calcularDistaciaEscuelas(subEstacionDao.getLista().getInfo(o), subEstacionDao.getLista().getInfo(d));
//                    dist = UtilVista.redondear(dist);
//                    subEstacionDao.getGrafo().insertEdgeE(subEstacionDao.getLista().getInfo(o), subEstacionDao.getLista().getInfo(d), dist);
//                    limpiar();
//                    System.out.println("se creo una ady");
//                }
//
//            }
//        }
//
//        JOptionPane.showMessageDialog(null, "Adyacencias Generadas");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelImageFondo = new org.edisoncor.gui.panel.PanelImage();
        panelRect1 = new org.edisoncor.gui.panel.PanelRect();
        labelCustom1 = new org.edisoncor.gui.label.LabelCustom();
        labelCustom2 = new org.edisoncor.gui.label.LabelCustom();
        txtPortadaTres = new org.edisoncor.gui.textField.TextFieldRound();
        txtLatitud = new org.edisoncor.gui.textField.TextFieldRound();
        labelCustom3 = new org.edisoncor.gui.label.LabelCustom();
        txtCapacidad = new org.edisoncor.gui.textField.TextFieldRound();
        labelCustom4 = new org.edisoncor.gui.label.LabelCustom();
        txtPortadaUno = new org.edisoncor.gui.textField.TextFieldRound();
        labelCustom5 = new org.edisoncor.gui.label.LabelCustom();
        txtPortadaDos = new org.edisoncor.gui.textField.TextFieldRound();
        labelCustom6 = new org.edisoncor.gui.label.LabelCustom();
        labelCustom7 = new org.edisoncor.gui.label.LabelCustom();
        txtNombre = new org.edisoncor.gui.textField.TextFieldRound();
        txtLongitud = new org.edisoncor.gui.textField.TextFieldRound();
        btnCargarPTres = new org.edisoncor.gui.button.ButtonRound();
        btnCargarPUno = new org.edisoncor.gui.button.ButtonRound();
        btnCargarPDos = new org.edisoncor.gui.button.ButtonRound();
        buttonRound6 = new org.edisoncor.gui.button.ButtonRound();
        panelRect2 = new org.edisoncor.gui.panel.PanelRect();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSubEstacion = new javax.swing.JTable();
        btnGuardar = new org.edisoncor.gui.button.ButtonRound();
        jMenuBar2 = new javax.swing.JMenuBar();
        Salir = new javax.swing.JMenu();
        btnInicio = new javax.swing.JMenuItem();
        btnGrafoMapa = new javax.swing.JMenuItem();
        btnRecorrido = new javax.swing.JMenuItem();
        btnSalir = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelImageFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRect1.setBackground(new java.awt.Color(0, 102, 102));
        panelRect1.setForeground(new java.awt.Color(0, 102, 102));
        panelRect1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelCustom1.setText("Portada Tres:");
        panelRect1.add(labelCustom1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 120, 30));

        labelCustom2.setText("Latitud:");
        panelRect1.add(labelCustom2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 70, 100, 30));

        txtPortadaTres.setEnabled(false);
        txtPortadaTres.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPortadaTresMouseClicked(evt);
            }
        });
        panelRect1.add(txtPortadaTres, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 250, -1));
        panelRect1.add(txtLatitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 70, 140, -1));

        labelCustom3.setText("Capacidad(kw):");
        panelRect1.add(labelCustom3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 120, 30));
        panelRect1.add(txtCapacidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 250, -1));

        labelCustom4.setText("Portada Uno:");
        panelRect1.add(labelCustom4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 120, 30));

        txtPortadaUno.setEnabled(false);
        txtPortadaUno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPortadaUnoMouseClicked(evt);
            }
        });
        panelRect1.add(txtPortadaUno, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 250, -1));

        labelCustom5.setText("Portada Dos:");
        panelRect1.add(labelCustom5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 120, 30));

        txtPortadaDos.setEnabled(false);
        txtPortadaDos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPortadaDosMouseClicked(evt);
            }
        });
        panelRect1.add(txtPortadaDos, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 250, -1));

        labelCustom6.setText("Nombre:");
        panelRect1.add(labelCustom6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 120, 30));

        labelCustom7.setText("Longitud:");
        panelRect1.add(labelCustom7, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 30, 100, 30));
        panelRect1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 250, -1));
        panelRect1.add(txtLongitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 30, 140, -1));

        btnCargarPTres.setBackground(new java.awt.Color(102, 102, 255));
        btnCargarPTres.setText("Cargar");
        btnCargarPTres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarPTresActionPerformed(evt);
            }
        });
        panelRect1.add(btnCargarPTres, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 190, 80, -1));

        btnCargarPUno.setBackground(new java.awt.Color(102, 102, 255));
        btnCargarPUno.setText("Cargar");
        btnCargarPUno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarPUnoActionPerformed(evt);
            }
        });
        panelRect1.add(btnCargarPUno, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 110, 80, -1));

        btnCargarPDos.setBackground(new java.awt.Color(102, 102, 255));
        btnCargarPDos.setText("Cargar");
        btnCargarPDos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarPDosActionPerformed(evt);
            }
        });
        panelRect1.add(btnCargarPDos, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 150, 80, -1));

        buttonRound6.setBackground(new java.awt.Color(51, 255, 51));
        buttonRound6.setText("Cargar");
        panelRect1.add(buttonRound6, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 150, 80, -1));

        panelImageFondo.add(panelRect1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 750, 240));

        panelRect2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbSubEstacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbSubEstacion);

        panelRect2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 680, 100));

        panelImageFondo.add(panelRect2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 750, 190));

        btnGuardar.setBackground(new java.awt.Color(51, 255, 51));
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        panelImageFondo.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 110, -1));

        getContentPane().add(panelImageFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 530));

        Salir.setText("Menu");

        btnInicio.setText("Inicio");
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });
        Salir.add(btnInicio);

        btnGrafoMapa.setText("Grafo y Mapa");
        btnGrafoMapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrafoMapaActionPerformed(evt);
            }
        });
        Salir.add(btnGrafoMapa);

        btnRecorrido.setText("Recorrido");
        btnRecorrido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecorridoActionPerformed(evt);
            }
        });
        Salir.add(btnRecorrido);

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        Salir.add(btnSalir);

        jMenuBar2.add(Salir);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        setJMenuBar(jMenuBar2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnGrafoMapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrafoMapaActionPerformed
        try {
            new FrmGrafoMapa().setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(FrmSubEstacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }//GEN-LAST:event_btnGrafoMapaActionPerformed

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        new FrmPrincipal().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnCargarPUnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarPUnoActionPerformed
        try {
            fportadaUno = cargarFoto();
            if (fportadaUno != null) {
                txtPortadaUno.setText(fportadaUno.getAbsolutePath());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar imagen");
        }
    }//GEN-LAST:event_btnCargarPUnoActionPerformed

    private void btnCargarPDosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarPDosActionPerformed
        try {
            fportadaDos = cargarFoto();
            if (fportadaDos != null) {
                txtPortadaDos.setText(fportadaDos.getAbsolutePath());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar imagen");
        }
    }//GEN-LAST:event_btnCargarPDosActionPerformed

    private void btnCargarPTresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarPTresActionPerformed
        try {
            fportadaTres = cargarFoto();
            if (fportadaTres != null) {
                txtPortadaTres.setText(fportadaTres.getAbsolutePath());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar imagen");
        }
    }//GEN-LAST:event_btnCargarPTresActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            guardar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar Sub-Estacion");
        }
        
        try {
            adycencia();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al crear Adyacencias");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtPortadaUnoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPortadaUnoMouseClicked
        if (evt.getClickCount() >= 2) {
            new FrmFoto(fportadaUno).setVisible(true);
        }
    }//GEN-LAST:event_txtPortadaUnoMouseClicked

    private void txtPortadaDosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPortadaDosMouseClicked
        if (evt.getClickCount() >= 2) {
            new FrmFoto(fportadaDos).setVisible(true);
        }
    }//GEN-LAST:event_txtPortadaDosMouseClicked

    private void txtPortadaTresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPortadaTresMouseClicked
        if (evt.getClickCount() >= 2) {
            new FrmFoto(fportadaTres).setVisible(true);
        }
    }//GEN-LAST:event_txtPortadaTresMouseClicked

    private void btnRecorridoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecorridoActionPerformed

        this.dispose();
    }//GEN-LAST:event_btnRecorridoActionPerformed

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
            java.util.logging.Logger.getLogger(FrmSubEstacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmSubEstacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmSubEstacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmSubEstacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmSubEstacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Salir;
    private org.edisoncor.gui.button.ButtonRound btnCargarPDos;
    private org.edisoncor.gui.button.ButtonRound btnCargarPTres;
    private org.edisoncor.gui.button.ButtonRound btnCargarPUno;
    private javax.swing.JMenuItem btnGrafoMapa;
    private org.edisoncor.gui.button.ButtonRound btnGuardar;
    private javax.swing.JMenuItem btnInicio;
    private javax.swing.JMenuItem btnRecorrido;
    private javax.swing.JMenuItem btnSalir;
    private org.edisoncor.gui.button.ButtonRound buttonRound6;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JScrollPane jScrollPane1;
    private org.edisoncor.gui.label.LabelCustom labelCustom1;
    private org.edisoncor.gui.label.LabelCustom labelCustom2;
    private org.edisoncor.gui.label.LabelCustom labelCustom3;
    private org.edisoncor.gui.label.LabelCustom labelCustom4;
    private org.edisoncor.gui.label.LabelCustom labelCustom5;
    private org.edisoncor.gui.label.LabelCustom labelCustom6;
    private org.edisoncor.gui.label.LabelCustom labelCustom7;
    private org.edisoncor.gui.panel.PanelImage panelImageFondo;
    private org.edisoncor.gui.panel.PanelRect panelRect1;
    private org.edisoncor.gui.panel.PanelRect panelRect2;
    private javax.swing.JTable tbSubEstacion;
    private org.edisoncor.gui.textField.TextFieldRound txtCapacidad;
    private org.edisoncor.gui.textField.TextFieldRound txtLatitud;
    private org.edisoncor.gui.textField.TextFieldRound txtLongitud;
    private org.edisoncor.gui.textField.TextFieldRound txtNombre;
    private org.edisoncor.gui.textField.TextFieldRound txtPortadaDos;
    private org.edisoncor.gui.textField.TextFieldRound txtPortadaTres;
    private org.edisoncor.gui.textField.TextFieldRound txtPortadaUno;
    // End of variables declaration//GEN-END:variables
}
