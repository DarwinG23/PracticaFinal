/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.TDA.grafos;

import java.io.FileWriter;
import controlador.TDA.listas.DynamicList;

/**
 *
 * @author darwi
 */
public class PaintGraph {
    String URL = "d3/grafo.js";

    public void updateFile(Grafo graph) throws Exception {
        String nodes = "";
        String edges = "";
        String paint = "";
        
        nodes += "var nodes = new vis.DataSet([\n";
        for (int i = 1; i <= graph.num_vertice(); i++) {
            nodes += "{id: " + i + ", label: \"Node " + i + "\"},\n";
        }
        nodes += "]);\n\n";

        edges += "var edges = new vis.DataSet([\n";
        for (int i = 1; i <= graph.num_vertice(); i++) {
            DynamicList<Adyacencia> links = graph.adyacentes(i);
            for (int j = 0; j < links.getLength(); j++) {
                Adyacencia ady = links.getInfo(j);
                edges += "{from: " + i + ", to: " + ady.getDestino() + ", label: \"" + ady.getPeso() + "\"},\n";
            }
        }
        edges += "]);\n\n";

        paint += nodes + edges
                +"var container = document.getElementById(\"mynetwork\");\n"
                + "      var data = {\n"
                + "        nodes: nodes,\n"
                + "        edges: edges,\n"
                + "      };\n"
                + "      var options = {};\n"
                + "      var network = new vis.Network(container, data, options);";

        FileWriter load = new FileWriter(URL);
        load.write(paint);
        load.close();
    }
    
        public void update(GrafoEtiquetadoDirigido grp) throws Exception {
        String nodes = "";
        String edges = "";
        String paint = "";
        
        nodes += "var nodes = new vis.DataSet([\n";
        for (int i = 1; i <= grp.num_vertice(); i++) {
            nodes += "{id: " + i + ", label: \"" + grp.getLabelE(i) + "\"},\n";
        }
        nodes += "]);\n\n";

        edges += "var edges = new vis.DataSet([\n";
        for (int i = 1; i <= grp.num_vertice(); i++) {
            DynamicList<Adyacencia> links = grp.adyacentes(i);
            for (int j = 0; j < links.getLength(); j++) {
                Adyacencia ady = links.getInfo(j);
                edges += "{from: " + i + ", to: " + ady.getDestino() + ", label: \"" + ady.getPeso() + "\"},\n";
            }
        }
        edges += "]);\n\n";

        paint += nodes + edges
                +"var container = document.getElementById(\"mynetwork\");\n"
                + "      var data = {\n"
                + "        nodes: nodes,\n"
                + "        edges: edges,\n"
                + "      };\n"
                + "      var options = {};\n"
                + "      var network = new vis.Network(container, data, options);";

        FileWriter load = new FileWriter(URL);
        load.write(paint);
        load.close();
    }
    
}
