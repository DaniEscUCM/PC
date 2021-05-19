package Cliente;

import java.util.ArrayList;

/**
 * Guarda informacion para un usuario registrado en el sistema. Tendra al menos
 * los siguientes atributos: -identificador de usuario, -direccion ip -lista de
 * informacion compartida. El servidor almacenara informacion sobre todos los
 * usuarios registrados en el sistema (instancias de la clase Usuario)
 */

public class Usuario {

    private String direccion_ip;
    private String id_usuario;

    // Lista de informacion compartida , podiria ser un map??
    ArrayList<Fichero> shared_info;

    public Usuario() {

    }

    public String getDireccion_ip() {
        return direccion_ip;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public ArrayList getShared_info() {
        return shared_info;
    }

}