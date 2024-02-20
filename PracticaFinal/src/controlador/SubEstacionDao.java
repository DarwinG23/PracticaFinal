/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import controlador.TDA.DaoImplement;
import controlador.TDA.grafos.Adyacencia;
import controlador.TDA.grafos.GrafoEtiquetadoNoDirigido;
import controlador.TDA.listas.DynamicList;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Random;
import modelo.SubEstacion;

/**
 *
 * @author darwin
 */
public class SubEstacionDao extends DaoImplement<SubEstacion> {

    private DynamicList<SubEstacion> lista = new DynamicList<>();
    private GrafoEtiquetadoNoDirigido<SubEstacion> grafo;
    private SubEstacion subEstacion;

    public SubEstacionDao() {
        super(SubEstacion.class);
    }

    public void setLista(DynamicList<SubEstacion> lista) {
        this.lista = lista;
    }

    public GrafoEtiquetadoNoDirigido<SubEstacion> getGrafo() throws Exception {
        if (grafo == null) {
            DynamicList<SubEstacion> list = getLista();
            if (!list.isEmpty()) {
                grafo = new GrafoEtiquetadoNoDirigido<>(list.getLength(), SubEstacion.class);
                for (int i = 0; i < list.getLength(); i++) {
                    grafo.labelVertice((i + 1), list.getInfo(i));
                }
            }
        }

        return grafo;
    }

    public DynamicList<SubEstacion> getLista() {
        if (lista.isEmpty()) {
            lista = all();
        }
        return lista;
    }

    public void setGrafo(GrafoEtiquetadoNoDirigido grafo) {
        this.grafo = grafo;
    }

    public SubEstacion getSubEstacion() {
        if (subEstacion == null) {
            subEstacion = new SubEstacion();
        }
        return subEstacion;
    }

    public void setSubEstacion(SubEstacion subEstacion) {
        this.subEstacion = subEstacion;
    }

    public Boolean persist() {
        subEstacion.setId(all().getLength() + 1);
        return persist(subEstacion);
    }

    public void guardarGrafo() throws Exception {
        getConection().toXML(grafo, new FileWriter("files/grafo.json"));
    }

    public void loadGrapg() throws Exception {

        grafo = (GrafoEtiquetadoNoDirigido<SubEstacion>) getConection().fromXML(new FileReader("files/grafo.json"));
        lista.reset();
        for (int i = 1; i <= grafo.num_vertice(); i++) {
            lista.add(grafo.getLabelE(i));
        }
    }

    public DynamicList<Integer> Buscar(GrafoEtiquetadoNoDirigido<SubEstacion> grafo, SubEstacion subEstacion, String metodo) throws Exception {
        Boolean tieneAdy = true;
        while(tieneAdy){
            if(grafo.adjacents(subEstacion).getLength() > 0){
                tieneAdy = false;
            }else{
                subEstacion = getLista().getInfo(new Random().nextInt(getLista().getLength()));        
            }
            
        }
        
        boolean[] nodos = new boolean[grafo.getNum_vertice()];
        if (metodo.equalsIgnoreCase("Profundidad")) {
            nodos = DFS(grafo, subEstacion.getId(), nodos);
        } else {
            nodos = BFS(grafo, subEstacion.getId(), nodos);
        }

        DynamicList<Integer> nodosNoVisitados = new DynamicList<>();

        for (int i = 0; i < nodos.length; i++) {
            if (!nodos[i]) {
                nodosNoVisitados.add(i + 1);
            }
        }

        return nodosNoVisitados;
    }

    private boolean[] DFS(GrafoEtiquetadoNoDirigido<SubEstacion> grafo, Integer id, boolean[] visitados) throws Exception {

        DynamicList<Adyacencia> listaAdy = grafo.adyacentes(id);
        if (listaAdy.getLength() > 0) {
            visitados[grafo.getLabelE(id).getId() - 1] = true;
            for (int i = 0; i < listaAdy.getLength(); i++) {
                if (!visitados[listaAdy.getInfo(i).getDestino() - 1]) {
                    DFS(grafo, listaAdy.getInfo(i).getDestino(), visitados);
                }
            }

        } else {
            DFS(grafo, getLista().getInfo(new Random().nextInt(getLista().getLength())).getId(), visitados);
        }
        return visitados;
    }

    private boolean[] BFS(GrafoEtiquetadoNoDirigido<SubEstacion> grafo, Integer id, boolean[] visitados) throws Exception {

        int Inicio = id;
        DynamicList<Integer> lista = new DynamicList<>();
        lista.add(Inicio);
        visitados[Inicio - 1] = true;

        int i = 0;
        while (i < lista.getLength()) {
            int nodoActual = lista.getInfo(i++);
            DynamicList<Adyacencia> listaAdy = grafo.adyacentes(nodoActual);
            for (int j = 0; j < listaAdy.getLength(); j++) {
                Integer destinoId = listaAdy.getInfo(j).getDestino();
                if (!visitados[destinoId - 1]) {
                    visitados[destinoId - 1] = true;
                    lista.add(destinoId);
                }
            }
        }

        return visitados;
    }

    public double[][] floyd(GrafoEtiquetadoNoDirigido<SubEstacion> grafo) throws Exception {

        double[][] dist = new double[grafo.getNum_vertice() + 1][grafo.getNum_vertice() + 1];
        //String[][] recorrido = new String[grafo.getNum_vertice() + 1][grafo.getNum_vertice() + 1];

        for (int i = 1; i <= grafo.getNum_vertice(); i++) {
            SubEstacion o = grafo.getLabelE(i);
            for (int j = 1; j <= grafo.getNum_vertice(); j++) {
                SubEstacion d = grafo.getLabelE(j);
                if (i == j) {
                    dist[i][j] = 0.0;
                } else if (grafo.isEdge(o, d)) {
                    dist[i][j] = grafo.peso_arista(i, j);
                } else {
                    dist[i][j] = Double.POSITIVE_INFINITY;
                }
                //recorrido[i][j] = d.getNombre();
            }
        }

        for (int k = 1; k <= grafo.getNum_vertice(); k++) {
            for (int i = 1; i <= grafo.getNum_vertice(); i++) {
                for (int j = 1; j <= grafo.getNum_vertice(); j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        return dist;
    }

    public double[][] Bellman(GrafoEtiquetadoNoDirigido<SubEstacion> grafo, SubEstacion origen) throws Exception {
        double[][] dist = new double[grafo.getNum_vertice() + 1][grafo.getNum_vertice() + 1];

        for (int i = 1; i <= grafo.getNum_vertice(); i++) {
            SubEstacion o = grafo.getLabelE(i);
            for (int j = 1; j <= grafo.getNum_vertice(); j++) {
                SubEstacion d = grafo.getLabelE(j);
                if (i == j) {
                    dist[i][j] = 0.0;
                } else if (grafo.isEdge(o, d)) {
                    dist[i][j] = grafo.peso_arista(i, j);
                } else {
                    dist[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }

        for (int i = 1; i <= grafo.getNum_vertice(); i++) {
            if (i != origen.getId()) {
                dist[origen.getId()][i] = Double.POSITIVE_INFINITY;
            }
        }

        for (int i = 1; i <= grafo.getNum_vertice() - 1; i++) {
            SubEstacion o = grafo.getLabelE(i);
            for (int j = 1; j <= grafo.getNum_vertice(); j++) {
                SubEstacion d = grafo.getLabelE(j);
                for (int k = 1; k <= grafo.getNum_vertice(); k++) {
                    if (grafo.isEdge(o, d)) {
                        double pesoUV = grafo.peso_arista(j, k);

                        if (dist[origen.getId()][j] + pesoUV < dist[origen.getId()][k]) {
                            dist[origen.getId()][k] = dist[origen.getId()][j] + pesoUV;
                        }
                    }
                }
            }
        }

        for (int i = 1; i <= grafo.getNum_vertice(); i++) {
            SubEstacion o = grafo.getLabelE(i);
            System.out.println(o.toString());
            for (int j = 1; j <= grafo.getNum_vertice(); j++) {
                SubEstacion d = grafo.getLabelE(j);
                System.out.println(d.toString());
                if (grafo.isEdge(o, d)) {
                    double pesoUV = grafo.peso_arista(i, j);

                    if (dist[origen.getId()][i] + pesoUV < dist[origen.getId()][j]) {
                        throw new Exception("Hay ciclo negativo");
                    }
                }
            }
        }
        
        return dist;

    }
    
   

    @Override
    public String toString() {
        return subEstacion.getNombre();
    }

}
