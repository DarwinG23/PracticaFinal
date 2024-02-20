/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author darwin
 */
public class Coordenada {
    private Integer id;
    private Double longitud;
    private Double latitud;

    public Coordenada(Integer id, Double longitud, Double latitud) {
        this.id = id;
        this.longitud = longitud;
        this.latitud = latitud;
    }

    public Coordenada() {
        this.id = null;
        this.longitud = null;
        this.latitud = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    @Override
    public String toString() {
        return  longitud + " " + latitud;
    }    
}
