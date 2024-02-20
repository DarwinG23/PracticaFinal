/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.TDA.grafos;



/**
 *
 * @author darwi
 */
public class GrafoEtiquetadoNoDirigido<E> extends GrafoEtiquetadoDirigido<E>{
    
    public GrafoEtiquetadoNoDirigido(Integer num_vertice, Class<E> clazz) {
        super(num_vertice, clazz);
    }
    
    @Override
    public void insertar_arista(Integer v1,Integer v2,Double peso) throws Exception{
        if(v1.intValue()<=num_vertice()&& v2.intValue()<=num_vertice()){
            if(!existe_arista(v1, v2)){
                setNum_aristas(num_aristas()+1);
                getListaAdyacencias()[v1].add(new Adyacencia(v2,peso));
                getListaAdyacencias()[v2].add(new Adyacencia(v1,peso));
            }
        }
            
    }
    public static void main(String[] args) {
        try {
            GrafoEtiquetadoNoDirigido<String> gend = new GrafoEtiquetadoNoDirigido(6, String.class);
            gend.labelVertice(1, "Estefania");
            gend.labelVertice(2, "Luna");
            gend.labelVertice(3, "Jimenez");
            gend.labelVertice(4, "Criollo");
            gend.labelVertice(5, "Maritza");
            gend.labelVertice(6, "Nivelo");
            gend.insertEdgeE("Estefania", "Nivelo", 100.0);
            System.out.println(gend.toString());
            PaintGraph p = new PaintGraph();
            p.updateFile(gend);
        } catch (Exception e) {
            System.out.println("Error main " + e);
        }
    }
   
}
