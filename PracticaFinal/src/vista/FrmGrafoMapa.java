/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import controlador.SubEstacionDao;
import controlador.TDA.grafos.PaintGraph;
import controlador.TDA.listas.DynamicList;
import controlador.TDA.listas.Exception.EmptyException;
import java.io.File;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.SubEstacion;
import vista.tablas.ModeloTablaAdyacencias;
import vista.tablas.ModeloTablaRecorrido;
import vista.util.UtilVista;

/**
 *
 * @author darwin
 */
public class FrmGrafoMapa extends javax.swing.JFrame {

    SubEstacionDao subEstacionDao = new SubEstacionDao();
    ModeloTablaAdyacencias mta = new ModeloTablaAdyacencias();
    ModeloTablaRecorrido mtr = new ModeloTablaRecorrido();

    /**
     * Creates new form FrmGrafoMapa
     */
    public FrmGrafoMapa() throws Exception {
        initComponents();
        panelImage1.setIcon(new ImageIcon("foto/GyM.jpg"));
        setLocationRelativeTo(null);
        limpiar();
    }

    private void limpiar() {
        try {
            cargarTabla();
            UtilVista.cargarComboBox(cbxOrigen);
            UtilVista.cargarComboBox(cbxDestino);
        } catch (Exception e) {
        }

    }

    private void cargarTabla() throws Exception {
        mta.setGrafo(subEstacionDao.getGrafo());
        mta.fireTableDataChanged();
        mtr.setGrafo(subEstacionDao.getGrafo());
        mtr.fireTableDataChanged();
        //tbRecorrido.setModel(mtr);
        tbAdyacencias.setModel(mta);
        //tbRecorrido.updateUI();
        tbAdyacencias.updateUI();
    }

    private void mostrarMapa() throws Exception {

        UtilVista.crearMapaEscuela(subEstacionDao.getGrafo());
        File nav = new File("mapas/index.html");
        java.awt.Desktop.getDesktop().open(nav);
    }

    private void mostrarGrafo() throws Exception {
        PaintGraph p = new PaintGraph();
        p.update(subEstacionDao.getGrafo());
        File nav = new File("d3/grafo.html");
        java.awt.Desktop.getDesktop().open(nav);
    }

    private void load() throws Exception {
        try {
            int i = JOptionPane.showConfirmDialog(null, "Esta seguro de cargar el grafo?");
            if (i == JOptionPane.OK_OPTION) {
                subEstacionDao.loadGrapg();
                limpiar();
                JOptionPane.showMessageDialog(null, "Grafo cargado con exito");
            }
        } catch (Exception e) {
        }
    }

    private void guardarGrafo() {
        try {
            int i = JOptionPane.showConfirmDialog(null, "Esta seguro de guardar?",
                    "Advertencia", JOptionPane.OK_CANCEL_OPTION);

            if (i == JOptionPane.OK_OPTION) {
                if (subEstacionDao.getGrafo() != null) {
                    subEstacionDao.guardarGrafo();
                    JOptionPane.showMessageDialog(null, "Grafo guardado");
                } else {
                    JOptionPane.showMessageDialog(null, "No se puede guardar un grafo vacio");
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void adycencia(Integer contMinAdy, Integer o, Boolean reconectar) throws EmptyException, Exception {
        if (contMinAdy == 0 && !reconectar) {
            subEstacionDao.setGrafo(null);
        }

        Integer d = 0;
        Integer contadorAdyacencia = 0;
        Boolean conectado = false;

        while (!conectado && (contMinAdy < 3)) {
            d = new Random().nextInt(subEstacionDao.getLista().getLength());

            if (o.intValue() != d.intValue()) {
                for (int j = 0; j < subEstacionDao.getLista().getLength(); j++) {
                    if (subEstacionDao.getGrafo().isEdge(subEstacionDao.getLista().getInfo(d), subEstacionDao.getLista().getInfo(j))) {
                        contadorAdyacencia++;
                    }
                }

                if (!subEstacionDao.getGrafo().isEdge(subEstacionDao.getLista().getInfo(o), subEstacionDao.getLista().getInfo(d)) && contadorAdyacencia < 3 && contMinAdy < 3) {
                    Double dist = UtilVista.calcularDistaciaEscuelas(subEstacionDao.getLista().getInfo(o), subEstacionDao.getLista().getInfo(d));
                    dist = UtilVista.redondear(dist);
                    subEstacionDao.getGrafo().insertEdgeE(subEstacionDao.getLista().getInfo(o), subEstacionDao.getLista().getInfo(d), dist);
                    limpiar();
                    contMinAdy++;

                    contadorAdyacencia = 0;
//                    for (int j = 0; j < subEstacionDao.getLista().getLength(); j++) {
//                        if (subEstacionDao.getGrafo().isEdge(subEstacionDao.getLista().getInfo(d), subEstacionDao.getLista().getInfo(j))) {
//                            contadorAdyacencia++;
//                        }
//                    }

                }
                contadorAdyacencia = 0;
            }

            if (contMinAdy >= 2) {
                conectado = true;
                contMinAdy = 0;
                Boolean existeNodo = false;
                DynamicList<Integer> nodos = subEstacionDao.Buscar(subEstacionDao.getGrafo(), subEstacionDao.getLista().getInfo(new Random().nextInt(subEstacionDao.getLista().getLength())), "Profundidad");

                o = new Random().nextInt(subEstacionDao.getLista().getLength());

                for (int i = 0; i < subEstacionDao.getLista().getLength(); i++) {
                    if (subEstacionDao.getGrafo().isEdge(subEstacionDao.getLista().getInfo(o), subEstacionDao.getLista().getInfo(i))) {
                        contMinAdy++;
                    }
                }

                if (contMinAdy <= 2 || nodos.getLength() != 0) {
                    if (nodos.getLength() == 0) {
                        adycencia(contMinAdy, o, true);
                    } else if (contMinAdy <= 2) {
                        adycencia(contMinAdy, o, true);
                    } else {
                        o = nodos.getInfo(0) - 1;
                        adycencia(0, o, true);
                    }
                } else {
                    for (int i = 0; i < subEstacionDao.getLista().getLength(); i++) {
                        for (int j = 0; j < subEstacionDao.getLista().getLength(); j++) {
                            if (subEstacionDao.getGrafo().isEdge(subEstacionDao.getLista().getInfo(i), subEstacionDao.getLista().getInfo(j))) {
                                contMinAdy++;
                            }
                        }
                        if (contMinAdy < 2) {
                            o = i;
                            existeNodo = true;
                            break;
                        }
                        contMinAdy = 0;
                    }
                    if (existeNodo) {
                        adycencia(contMinAdy, o, true);

                    }
                }

            }
        }
    }

    private void buscar() throws Exception {
        DynamicList<Integer> nodos = new DynamicList<>();
        String metodo = cbxComprobar.getSelectedItem().toString();
        long inicio = System.nanoTime();
        nodos = subEstacionDao.Buscar(subEstacionDao.getGrafo(), subEstacionDao.getLista().getInfo(new Random().nextInt(subEstacionDao.getLista().getLength())), metodo);
        long fin = System.nanoTime();
        long tiempoTranscurrido = fin - inicio;
        System.out.println(nodos.getLength());
        if (nodos.getLength() == 0) {
            JOptionPane.showMessageDialog(null, "El grafo esta conectado");

        } else {
            JOptionPane.showMessageDialog(null, "El grafo  no esta conectado completamente, faltan conectar los nodos:" + nodos.toString());
            int i = JOptionPane.showConfirmDialog(null, "¿Desea conectar los nodos faltantes?", "CONECTAR", JOptionPane.OK_CANCEL_OPTION);
            if (i == JOptionPane.OK_OPTION) {
                for (int j = 0; j < nodos.getLength(); j++) {
                    adycencia(0, nodos.getInfo(j), true);
                }
            }
        }
        
       JOptionPane.showMessageDialog(null, tiempoTranscurrido, "Tiempo", JOptionPane.PLAIN_MESSAGE);
        
    }

    public void Imprimir(double[][] dist) throws Exception {

        // Imprimir encabezados de columnas
        txtMatriz.append(String.format("%-40s", ""));
        //System.out.printf("%-40s", ""); // Espacios adicionales para ajustar la alineación
        for (int i = 1; i <= subEstacionDao.getGrafo().getNum_vertice(); i++) {
            txtMatriz.append(String.format("%-30s", subEstacionDao.getGrafo().getLabelE(i)));
            //System.out.printf("%-30s", subEstacionDao.getGrafo().getLabelE(i));
        }
        txtMatriz.append("\n");
       // System.out.println();

        for (int i = 1; i <= subEstacionDao.getGrafo().getNum_vertice(); i++) {
            // Imprimir etiqueta de fila
            txtMatriz.append(String.format("%-40s", subEstacionDao.getGrafo().getLabelE(i)));
           // System.out.printf("%-40s", subEstacionDao.getGrafo().getLabelE(i));

            for (int j = 1; j <= subEstacionDao.getGrafo().getNum_vertice(); j++) {
                // Imprimir valor de la matriz de distancias
                if (dist[i][j] == Double.POSITIVE_INFINITY) {
                    txtMatriz.append(String.format("%-30s", "INF"));
                    //System.out.printf("%-30s", "INF");
                } else {
                    txtMatriz.append(String.format("%-30.2f", dist[i][j]));
                   // System.out.printf("%-30.2f", dist[i][j]);
                }
            }
            txtMatriz.append("\n");
            //System.out.println();
        }

    }

    public String buscarCamino(double[][] dist) throws Exception {
        if (dist[cbxOrigen.getSelectedIndex() + 1][cbxDestino.getSelectedIndex() + 1] == Double.POSITIVE_INFINITY) {
            return "No hay ruta entre " + subEstacionDao.getGrafo().getLabelE(cbxOrigen.getSelectedIndex() + 1) + " y " + subEstacionDao.getGrafo().getLabelE(cbxDestino.getSelectedIndex() + 1);
        }

        StringBuilder camino = new StringBuilder();

        int estacionActual = cbxOrigen.getSelectedIndex() + 1;
        double sumaPesos = 0.0;

        while (estacionActual  != cbxDestino.getSelectedIndex() + 1) {

            int siguienteNodo = VerticeVecino(estacionActual, cbxDestino.getSelectedIndex() + 1, dist);
            //System.out.println("sigNodo: " + siguienteNodo);
            if (siguienteNodo == -1) {
                break; // No hay vecinos o no se encontró un camino, salir del bucle
            }

            
            camino.append(subEstacionDao.getGrafo().getLabelE(estacionActual));
            sumaPesos += dist[estacionActual][siguienteNodo];
            camino.append(" -> ");
            estacionActual = siguienteNodo;
        }

        camino.append(subEstacionDao.getGrafo().getLabelE(cbxDestino.getSelectedIndex() + 1));
        txtDistancia.setText(UtilVista.redondear(sumaPesos)+ "");

        return camino.toString();

    }
    
    private int VerticeVecino(int actual, int destino, double[][] dist) throws Exception {
        int vecinoMasCercano = -1;
        double distanciaMasCorta = Double.POSITIVE_INFINITY;
        SubEstacion estacionActual = subEstacionDao.getGrafo().getLabelE(actual);
        
        for (int k = 1; k <= subEstacionDao.getGrafo().getNum_vertice(); k++) {

            SubEstacion estacionDestino = subEstacionDao.getGrafo().getLabelE(k);

            if (actual != k && dist[k][destino] < distanciaMasCorta && subEstacionDao.getGrafo().isEdge(estacionActual, estacionDestino)) {
                vecinoMasCercano = k;
                distanciaMasCorta = dist[k][destino];
            }
        }
        
        return vecinoMasCercano;
    }
    
    private void ruta() throws Exception {
        String camino;
        String metodo = cbxMetodo.getSelectedItem().toString();
        long tiempoTranscurrido;
        if (metodo.equalsIgnoreCase("Floyd")) {
            long inicio = System.nanoTime();
            double[][] dist = subEstacionDao.floyd(subEstacionDao.getGrafo());
            long fin = System.nanoTime();
            tiempoTranscurrido = fin - inicio/ 1000000;
            Imprimir(dist);
            camino = buscarCamino(dist);
        } else {
            long inicio = System.nanoTime();
            double[][] dist = subEstacionDao.Bellman(subEstacionDao.getGrafo(), (SubEstacion) cbxOrigen.getSelectedItem());
            long fin = System.nanoTime();
            tiempoTranscurrido = (fin - inicio)/ 1000000;
            Imprimir(dist);
            camino = buscarCamino(dist);
            
        }
        txtCamino.setText(camino);
        JOptionPane.showMessageDialog(null, tiempoTranscurrido, "Tiempo", JOptionPane.PLAIN_MESSAGE);
        
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbAdyacencias = new javax.swing.JTable();
        btnGrafo = new org.edisoncor.gui.button.ButtonRound();
        btnMapa = new org.edisoncor.gui.button.ButtonRound();
        labelCustom7 = new org.edisoncor.gui.label.LabelCustom();
        cbxComprobar = new javax.swing.JComboBox<>();
        btnCargar = new org.edisoncor.gui.button.ButtonRound();
        btnGuardar = new org.edisoncor.gui.button.ButtonRound();
        btnAdyacencia = new org.edisoncor.gui.button.ButtonRound();
        btnBusqueda = new org.edisoncor.gui.button.ButtonRound();
        labelRect2 = new org.edisoncor.gui.label.LabelRect();
        btnBuscarRuta = new org.edisoncor.gui.button.ButtonRound();
        labelCustom9 = new org.edisoncor.gui.label.LabelCustom();
        cbxMetodo = new javax.swing.JComboBox<>();
        labelCustom8 = new org.edisoncor.gui.label.LabelCustom();
        labelCustom6 = new org.edisoncor.gui.label.LabelCustom();
        cbxDestino = new javax.swing.JComboBox<>();
        cbxOrigen = new javax.swing.JComboBox<>();
        labelRect3 = new org.edisoncor.gui.label.LabelRect();
        txtCamino = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMatriz = new javax.swing.JTextArea();
        labelRect4 = new org.edisoncor.gui.label.LabelRect();
        txtDistancia = new javax.swing.JTextField();
        jMenuBar2 = new javax.swing.JMenuBar();
        Salir = new javax.swing.JMenu();
        btnInicio = new javax.swing.JMenuItem();
        btnSubEstaciones = new javax.swing.JMenuItem();
        btnRecorrido = new javax.swing.JMenuItem();
        btnSalir = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelImage1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbAdyacencias.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbAdyacencias);

        panelImage1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 730, 130));

        btnGrafo.setBackground(new java.awt.Color(255, 153, 153));
        btnGrafo.setText("Ver Grafo");
        btnGrafo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrafoActionPerformed(evt);
            }
        });
        panelImage1.add(btnGrafo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 90, -1));

        btnMapa.setBackground(new java.awt.Color(204, 153, 255));
        btnMapa.setText("Ver Mapa");
        btnMapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMapaActionPerformed(evt);
            }
        });
        panelImage1.add(btnMapa, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 90, -1));

        labelCustom7.setBackground(new java.awt.Color(51, 51, 51));
        labelCustom7.setText("Comprobar:");
        panelImage1.add(labelCustom7, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 60, 120, 30));

        cbxComprobar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Anchura", "Profundidad" }));
        panelImage1.add(cbxComprobar, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 60, 210, -1));

        btnCargar.setBackground(new java.awt.Color(255, 255, 153));
        btnCargar.setText("Cargar");
        btnCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarActionPerformed(evt);
            }
        });
        panelImage1.add(btnCargar, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, 80, -1));

        btnGuardar.setBackground(new java.awt.Color(153, 153, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        panelImage1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 100, 90, -1));

        btnAdyacencia.setBackground(new java.awt.Color(255, 153, 255));
        btnAdyacencia.setText("Adyacencia");
        btnAdyacencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdyacenciaActionPerformed(evt);
            }
        });
        panelImage1.add(btnAdyacencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 100, -1));

        btnBusqueda.setBackground(new java.awt.Color(204, 204, 204));
        btnBusqueda.setText("Comprobar");
        btnBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusquedaActionPerformed(evt);
            }
        });
        panelImage1.add(btnBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 100, 90, -1));

        labelRect2.setBackground(new java.awt.Color(51, 51, 51));
        labelRect2.setText("Destancia:");
        panelImage1.add(labelRect2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 320, 120, 30));

        btnBuscarRuta.setBackground(new java.awt.Color(153, 255, 153));
        btnBuscarRuta.setText("Buscar Ruta");
        btnBuscarRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarRutaActionPerformed(evt);
            }
        });
        panelImage1.add(btnBuscarRuta, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 100, 100, -1));

        labelCustom9.setBackground(new java.awt.Color(51, 51, 51));
        labelCustom9.setText("Metodo:");
        panelImage1.add(labelCustom9, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 120, 30));

        cbxMetodo.setBackground(new java.awt.Color(51, 51, 51));
        cbxMetodo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Floyd", "Bellman" }));
        panelImage1.add(cbxMetodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 210, -1));

        labelCustom8.setBackground(new java.awt.Color(51, 51, 51));
        labelCustom8.setText("Origen:");
        panelImage1.add(labelCustom8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 120, 30));

        labelCustom6.setBackground(new java.awt.Color(51, 51, 51));
        labelCustom6.setText("Destino:");
        panelImage1.add(labelCustom6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 120, 30));

        cbxDestino.setBackground(new java.awt.Color(51, 51, 51));
        panelImage1.add(cbxDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 210, -1));

        cbxOrigen.setBackground(new java.awt.Color(51, 51, 51));
        panelImage1.add(cbxOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 210, -1));

        labelRect3.setBackground(new java.awt.Color(51, 51, 51));
        labelRect3.setText("ADYACENCIAS;");
        panelImage1.add(labelRect3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 180, 30));
        panelImage1.add(txtCamino, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 320, 290, 30));

        txtMatriz.setColumns(20);
        txtMatriz.setRows(5);
        txtMatriz.setEnabled(false);
        jScrollPane2.setViewportView(txtMatriz);

        panelImage1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 730, 130));

        labelRect4.setBackground(new java.awt.Color(51, 51, 51));
        labelRect4.setText("Recorrido:");
        panelImage1.add(labelRect4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 180, 30));

        txtDistancia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDistanciaActionPerformed(evt);
            }
        });
        panelImage1.add(txtDistancia, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 320, 110, 30));

        getContentPane().add(panelImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 500));

        Salir.setText("Menu");

        btnInicio.setText("Inicio");
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });
        Salir.add(btnInicio);

        btnSubEstaciones.setText("SubEstaciones");
        btnSubEstaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubEstacionesActionPerformed(evt);
            }
        });
        Salir.add(btnSubEstaciones);

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

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        new FrmPrincipal().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnSubEstacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubEstacionesActionPerformed
        new FrmSubEstacion().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSubEstacionesActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnGrafoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrafoActionPerformed
        try {
            mostrarGrafo();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error no se puede mostrar el grafo");
        }
    }//GEN-LAST:event_btnGrafoActionPerformed

    private void btnMapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMapaActionPerformed
        try {
            mostrarMapa();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error no se puede mostrar mapa");
        }
    }//GEN-LAST:event_btnMapaActionPerformed

    private void btnCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarActionPerformed
        try {
            load();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error, no se puede cargar");
        }
    }//GEN-LAST:event_btnCargarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardarGrafo();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnAdyacenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdyacenciaActionPerformed
        try {
            Integer o = new Random().nextInt(subEstacionDao.getLista().getLength());
            adycencia(0, o, false);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error, no se puede crear adycencias");
        }
    }//GEN-LAST:event_btnAdyacenciaActionPerformed

    private void btnBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusquedaActionPerformed
        try {
            
            buscar();

        } catch (Exception ex) {
            Logger.getLogger(FrmGrafoMapa.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBusquedaActionPerformed

    private void btnRecorridoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecorridoActionPerformed

        this.dispose();
    }//GEN-LAST:event_btnRecorridoActionPerformed

    private void btnBuscarRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarRutaActionPerformed
        try {
            ruta();

        } catch (Exception ex) {
            Logger.getLogger(FrmGrafoMapa.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBuscarRutaActionPerformed

    private void txtDistanciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDistanciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDistanciaActionPerformed

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
            java.util.logging.Logger.getLogger(FrmGrafoMapa.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmGrafoMapa.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmGrafoMapa.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmGrafoMapa.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FrmGrafoMapa().setVisible(true);

                } catch (Exception ex) {
                    Logger.getLogger(FrmGrafoMapa.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Salir;
    private org.edisoncor.gui.button.ButtonRound btnAdyacencia;
    private org.edisoncor.gui.button.ButtonRound btnBuscarRuta;
    private org.edisoncor.gui.button.ButtonRound btnBusqueda;
    private org.edisoncor.gui.button.ButtonRound btnCargar;
    private org.edisoncor.gui.button.ButtonRound btnGrafo;
    private org.edisoncor.gui.button.ButtonRound btnGuardar;
    private javax.swing.JMenuItem btnInicio;
    private org.edisoncor.gui.button.ButtonRound btnMapa;
    private javax.swing.JMenuItem btnRecorrido;
    private javax.swing.JMenuItem btnSalir;
    private javax.swing.JMenuItem btnSubEstaciones;
    private javax.swing.JComboBox<String> cbxComprobar;
    private javax.swing.JComboBox<String> cbxDestino;
    private javax.swing.JComboBox<String> cbxMetodo;
    private javax.swing.JComboBox<String> cbxOrigen;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private org.edisoncor.gui.label.LabelCustom labelCustom6;
    private org.edisoncor.gui.label.LabelCustom labelCustom7;
    private org.edisoncor.gui.label.LabelCustom labelCustom8;
    private org.edisoncor.gui.label.LabelCustom labelCustom9;
    private org.edisoncor.gui.label.LabelRect labelRect2;
    private org.edisoncor.gui.label.LabelRect labelRect3;
    private org.edisoncor.gui.label.LabelRect labelRect4;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private javax.swing.JTable tbAdyacencias;
    private javax.swing.JTextField txtCamino;
    private javax.swing.JTextField txtDistancia;
    private javax.swing.JTextArea txtMatriz;
    // End of variables declaration//GEN-END:variables
}
