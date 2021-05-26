package Mensajes;

import java.util.*;

public class Mensaje_Conexion extends Mensaje {

    private static final long serialVersionUID = -2966443059131992737L;
    static private String tipo = "Mensaje_Conexion";
    private String id_cliente;
    private String ip_cliente;
    private ArrayList<String> shared_info;

    public Mensaje_Conexion() {
        super(tipo);
    }

    public Mensaje_Conexion(String origen, String destino, String id_cliente, String ip_cliente,
            ArrayList<String> shared_info) {
        super(tipo, origen, destino);
        this.id_cliente = id_cliente;
        this.ip_cliente = ip_cliente;
        this.shared_info = shared_info;
    }

    public String getId_cliente() {
        return this.id_cliente;
    }

    public ArrayList<String> getShared_info() {
        return this.shared_info;
    }

    public String getIp_cliente() {
        return this.ip_cliente;
    }

}