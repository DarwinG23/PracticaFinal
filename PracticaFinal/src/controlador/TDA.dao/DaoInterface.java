/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.TDA;

import controlador.TDA.listas.DynamicList;

/**
 *
 * @author darwin
 * @param <T>
 */
public interface DaoInterface <T> {
    
    public Boolean persist(T data);
    public Boolean marge(T data, Integer index);
    public DynamicList<T> all();
    public T get(Integer id);
    
    
}
