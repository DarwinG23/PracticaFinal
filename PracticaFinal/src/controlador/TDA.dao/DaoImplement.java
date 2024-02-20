package controlador.TDA;

import com.thoughtworks.xstream.XStream;
import java.io.FileReader;
import java.io.FileWriter;
import controlador.TDA.listas.DynamicList;

/**
 *
 * @author darwi
 * @param <T>
 */
public class DaoImplement<T> implements DaoInterface<T> {

    private Class<T> clazz;
    private XStream conection;
    private String URL;

    public DaoImplement(Class<T> clazz) {
        this.clazz = clazz;
        conection = Bridge.getConnection();
        URL = Bridge.URL + clazz.getSimpleName() + ".json";
    }

    @Override
    public Boolean persist(T data) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        DynamicList<T> ld = all();
        ld.add(data);
        try {
            conection.toXML(ld, new FileWriter(URL));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    //Actualizar lista
    public Boolean marge(T data, Integer index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public DynamicList<T> all() {
        DynamicList<T> dl = new DynamicList<>();
        try {
            dl = (DynamicList<T>) conection.fromXML(new FileReader(URL));
        } catch (Exception e) {
        }
        return dl;
    }

    @Override
    public T get(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public XStream getConection() {
        return conection;
    }

}
