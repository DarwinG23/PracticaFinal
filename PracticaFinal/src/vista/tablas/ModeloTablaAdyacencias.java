/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.tablas;

import modelo.SubEstacion;
import javax.swing.table.AbstractTableModel;
import controlador.TDA.grafos.GrafoEtiquetadoDirigido;
import vista.util.UtilVista;

/**
 *
 * @author darwi
 */
public class ModeloTablaAdyacencias extends AbstractTableModel {

    private GrafoEtiquetadoDirigido<SubEstacion> grafo;


    @Override
    public int getRowCount() {
        return grafo.num_vertice();
    }

    @Override
    public int getColumnCount() {
        return grafo.num_vertice() + 1;
    }

    @Override
    public String getColumnName(int column) {
        if (column == 0) {
            return "Sub-Estacion";
        } else {
            try {
                return grafo.getLabelE(column).toString();
            } catch (Exception e) {
                return "";
            }
        }
    }

    public GrafoEtiquetadoDirigido getGrafo() {
        return grafo;
    }

    public void setGrafo(GrafoEtiquetadoDirigido grafo) {
        this.grafo = grafo;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        try {
            if (i1 == 0) {
                return grafo.getLabelE(i + 1).toString();
            } else {
                SubEstacion o = grafo.getLabelE(i + 1);
                SubEstacion d = grafo.getLabelE(i1);
                if (grafo.isEdge(o, d)) {
                    return UtilVista.redondear(grafo.peso_arista(i + 1, i1)).toString();
                } else {
                    return "--";
                }
            }
        } catch (Exception e) {
            return "";
        }
    }
}
