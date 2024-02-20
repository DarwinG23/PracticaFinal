/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.tablas;

import controlador.TDA.listas.DynamicList;
import javax.swing.table.AbstractTableModel;
import modelo.SubEstacion;

/**
 *
 * @author darwin
 */
public class ModeloTablaSubEstacion extends AbstractTableModel {

    private DynamicList<SubEstacion> lista;

    @Override
    public int getRowCount() {
        return lista.getLength();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        SubEstacion p;
        try {
            p = (SubEstacion) lista.getInfo(rowIndex);
            switch (columnIndex) {
                case 0:
                    return (p != null) ? p.getNombre() : " ";
                case 1:
                    return (p != null) ? p.getCapacidad() : " ";
                case 2:
                    return (p != null) ? p.getUbicacion().getLatitud() : "";
                case 3:
                    return (p != null) ? p.getUbicacion().getLongitud() : "";
                case 4:
                    return (p != null) ? p.getPortadaUno() : "";
                case 5:
                    return (p != null) ? p.getPortadaDos() : "";
                case 6:
                    return (p != null) ? p.getPortadaOpcional() : "";
                default:
                    return null;
            }
        } catch (controlador.TDA.listas.Exception.EmptyException ex) {
            System.out.println("error");
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "NOMBRE";
            case 1:
                return "CAPACIDAD(kw)";
            case 2:
                return "LATITUD";
            case 3:
                return "LONGITUD";
            case 4:
                return "PORTADA UNO";
            case 5:
                return "PORTADA DOS";
            case 6:
                return "PORTADA TRES";
            default:
                return null;
        }
    }

    /**
     * @return the personas
     */
    public DynamicList getLista() {
        return lista;
    }

    /**
     * @param lista
     */
    public void setLista(DynamicList lista) {
        this.lista = lista;
    }

}
