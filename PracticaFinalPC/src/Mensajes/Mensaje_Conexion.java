package Mensajes;

import java.util.*;

public class Mensaje_Conexion extends Mensaje {

    static private String tipo = "Mensaje_Conexion";
    private String id_cliente;
    private String ip_cliente;
    private ArrayList<String> shared_info;

    public Mensaje_Conexion() {
        super(tipo);
    }

    // fout.writeObject(new
    // Mensaje_Conexion(client.getId(),"server",client.getId(),client.getId(),client.getShared_info());

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