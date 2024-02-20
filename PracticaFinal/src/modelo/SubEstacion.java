/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author darwin
 */
public class SubEstacion {
    private Integer id;
    private String nombre;
    private String capacidad;
    private Coordenada ubicacion;
    private String descripcion;
    private String portadaUno;
    private String portadaDos;
    private String portadaOpcional;

    public SubEstacion(Integer id, String nombre, String capacidad, String descripcion, String portadaUno, String portadaDos, String portadaOpcional) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.descripcion = descripcion;
        this.portadaUno = portadaUno;
        this.portadaDos = portadaDos;
        this.portadaOpcional = portadaOpcional;
    }

    public SubEstacion() {
        this.id = null;
        this.nombre = null;
        this.capacidad = null;
        this.descripcion = null;
        this.portadaUno = null;
        this.portadaDos = null;
        this.portadaOpcional = null;       
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPortadaUno() {
        return portadaUno;
    }

    public void setPortadaUno(String portadaUno) {
        this.portadaUno = portadaUno;
    }

    public String getPortadaDos() {
        return portadaDos;
    }

    public void setPortadaDos(String portadaDos) {
        this.portadaDos = portadaDos;
    }

    public String getPortadaOpcional() {
        return portadaOpcional;
    }

    public void setPortadaOpcional(String portadaOpcional) {
        this.portadaOpcional = portadaOpcional;
    }

    public Coordenada getUbicacion() {
        if(ubicacion == null){
            ubicacion = new Coordenada();
        }
        return ubicacion;
    }

    public void setUbicacion(Coordenada ubicacion) {
        if(ubicacion == null){
            ubicacion = new Coordenada();
        }
        this.ubicacion = ubicacion;
    }
    
    

    @Override
    public String toString() {
        return nombre;
    }
}
