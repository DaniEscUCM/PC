package Cliente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Mensajes.Fichero;

/**
 * Guarda informacion para un usuario registrado en el sistema. Tendra al menos
 * los siguientes atributos: -identificador de usuario, -direccion ip -lista de
 * informacion compartida. El servidor almacenara informacion sobre todos los
 * usuarios registrados en el sistema (instancias de la clase Usuario)
 */

public class Usuario {

    private String direccion_ip;
    private String id_usuario;


    ArrayList<String> shared_info = new ArrayList<String>();
    Map<String,Fichero> info = new HashMap<String,Fichero>();
    
    public Usuario(String id_usuario, String direccion_ip,ArrayList<String> lis,Map<String,Fichero> m) {
        this.id_usuario = id_usuario;
        this.direccion_ip = direccion_ip;
        this.shared_info=lis;
        this.info=m;
    }
    
    public Usuario(String id_usuario, String direccion_ip) {
        this.id_usuario = id_usuario;
        this.direccion_ip = direccion_ip;
    }

    public String getDireccion_ip() {
        return direccion_ip;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public ArrayList<String> getShared_info() {
        return shared_info;
    }
    
    public void addShared_info(Fichero f) {
    	shared_info.add(f.getName());
    	info.put(f.getName(),f);
    }
    
    public Fichero getFile(String name) {
    	return new Fichero(name,info.get(name).getData());
    }

}