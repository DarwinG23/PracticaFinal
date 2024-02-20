/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.util;

import controlador.SubEstacionDao;
import controlador.TDA.grafos.GrafoEtiquetadoDirigido;
import controlador.TDA.grafos.exception.VerticeException;
import controlador.TDA.listas.DynamicList;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.swing.JComboBox;
import modelo.SubEstacion;

/**
 *
 * @author darwin
 */
public class UtilVista {
    

    public static String extension(String fileName) {
        String extension = "";

        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i + 1);
        }
        return extension;
    }

    public static void copiarArchivo(File origen, File destino) throws Exception {
        Files.copy(origen.toPath(),
                (destino).toPath(),
                StandardCopyOption.REPLACE_EXISTING);
    }

    public static double coordGpsToKm(double lat1, double lon1, double lat2, double lon2) {
        double lat1rad = Math.toRadians(lat1);
        double lon1rad = Math.toRadians(lon1);
        double lat2rad = Math.toRadians(lat2);
        double lon2rad = Math.toRadians(lon2);

        double difLatitud = lat1rad - lat2rad;
        double difLongitud = lon1rad - lon2rad;

        double a = Math.pow(Math.sin(difLatitud / 2), 2)
                + Math.cos(lat1rad)
                * Math.cos(lat2rad)
                * Math.pow(Math.sin(difLongitud / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double radioTierraKm = 6378.0;
        double distancia = radioTierraKm * c;

        return distancia;
    }

    public static Double calcularDistaciaEscuelas(SubEstacion o, SubEstacion d) {

        Double dist = coordGpsToKm(o.getUbicacion().getLatitud(),
                o.getUbicacion().getLongitud(), d.getUbicacion().getLatitud(),
                d.getUbicacion().getLongitud());
        return dist;
    }

    public static Double redondear(Double x) {
        return Math.round(x * 100.0) / 100.0;
    }

    public static void crearMapaEscuela(GrafoEtiquetadoDirigido<SubEstacion> ge) throws VerticeException, Exception {
        String maps
                = "var osmUrl = 'https://tile.openstreetmap.org/{z}/{x}/{y}.png',\n"
                + "                    osmAttrib = '&copy; <a href=\"https://www.openstreetmap.org/copyright\">OpenStreetMap</a> contributors',\n"
                + "                    osm = L.tileLayer(osmUrl, {maxZoom: 15, attribution: osmAttrib});\n"
                + "\n"
                + "            var map = L.map('map').setView([-4.036, -79.201], 15);\n"
                + "\n"
                + "            L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {\n"
                + "                attribution: '&copy; <a href=\"https://www.openstreetmap.org/copyright\">OpenStreetMap</a> contributors'\n"
                + "            }).addTo(map);" + "\n";
        for (int i = 1; i <= ge.num_vertice(); i++) {
            SubEstacion ec = ge.getLabelE(i);
            maps += "L.marker([" + ec.getUbicacion().getLatitud() + ", " + ec.getUbicacion().getLongitud() + "]).addTo(map)" + "\n";
            maps += ".bindPopup(\"" + ec.toString() + "\")" + "\n";
            maps += ".openPopup();" + "\n";
        }
        FileWriter file = new FileWriter("mapas/mapa.js");
        file.write(maps);
        file.close();

    }

    public static void cargarComboBox(JComboBox cbx) throws Exception {
        cbx.removeAllItems();
        DynamicList<SubEstacion> list = new SubEstacionDao().getLista();
        for (int i = 0; i < list.getLength(); i++) {
            cbx.addItem(list.getInfo(i));
        }
    }

}
