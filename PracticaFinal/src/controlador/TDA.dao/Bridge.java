/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.TDA;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;
import java.io.File;

/**
 *
 * @author darwin
 */
public class Bridge {
    public static String URL ="files"+File.separatorChar;
    private static XStream conection;
    
    
    public static XStream getConnection(){
        if(conection == null){
            conection = new XStream(new JettisonMappedXmlDriver());
            conection.addPermission(AnyTypePermission.ANY);
        }
        
        return conection;
    }
    
    public static void setConection (XStream connection){
        Bridge.conection = connection;
    }
    
}
