/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.TDA.grafos;

import controlador.TDA.listas.DynamicList;

/**
 *
 * @author sebastian
 */
public abstract class Grafo {

    //---G={v,e}
    public abstract Integer num_vertice();

    public abstract Integer num_aristas();

    //v1--v1
    public abstract Boolean existe_arista(Integer v1, Integer v2) throws Exception;

    public abstract Double peso_arista(Integer v1, Integer v2) throws Exception;

    public abstract void insertar_arista(Integer v1, Integer v2, Double peso) throws Exception;

    //arista sin peso
    public abstract void insertar_arista(Integer v1, Integer v2) throws Exception;//adyacencia

    public abstract void adyacencia(Integer v1, Integer v2);

    public abstract DynamicList<Adyacencia> adyacentes(Integer v1);
    //matriz si grafo tiene 20 vertices matriz igual 20x20 solo grafos randes
    //listas adyacentes para vertices pocos
    //adyacente=v1--- relacionesque tiene cercanas por ejemplo v1 esta a lado del v3 y el v4

    @Override
    public String toString() {
        StringBuilder grafo = new StringBuilder("Grafo").append("\n");
        try {
            for (int i = 1; i <= num_vertice(); i++) {
                grafo.append("V").append(i).append("\n");
                DynamicList<Adyacencia> list = adyacentes(i);
                for (int j = 0; j < list.getLength(); j++) {
                    Adyacencia a = list.getInfo(j);
                    grafo.append("ady").append(a.getDestino()).append("peso").append(a.getPeso()).append("\n");
                    //append agregar string 
                }

            }
        } catch (Exception e) {
        }
        return grafo.toString(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
}
