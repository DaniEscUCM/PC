package Cliente;

import Mensajes.Fichero;
import java.util.*;
import java.util.concurrent.Semaphore;

/**
 * @author Daniela Escobar & Alessandro de Armas
 * 
 *         Clase cliente que guarda al usuario, sus conexiones y realiza las
 *         acciones que tienen que ver con lo que solicita acceder otro cliente
 *         y su usuario. Similar a la clase Servidor.
 */
public class Cliente {

    private String direccion_ip;
    private String id_usuario;
    private Semaphore sem;

    ArrayList<String> shared_info = new ArrayList<String>();
    Map<String, Fichero> info = new HashMap<String, Fichero>();

    private int puerto = 6543;

    public Cliente(String id_usuario, String direccion_ip, ArrayList<String> lis, Map<String, Fichero> m,Semaphore sem) {
        this.id_usuario = id_usuario;
        this.direccion_ip = direccion_ip;
        this.shared_info = lis;
        this.info = m;
        this.sem = sem;
    }

    public Cliente(String id_usuario, String direccion_ip,Semaphore sem) {
        this.id_usuario = id_usuario;
        this.direccion_ip = direccion_ip;
        this.sem = sem;
    }

    public void mensajeConexionServidor() {
        System.out.println("Conexion establecida con el servidor");
    }

    public String getIp() {// proteger para leer
        return direccion_ip;
    }

    public String getId() {
        return id_usuario;
    }

    public ArrayList<String> getShared_info() {
        return shared_info;
    }

    public void addShared_info(Fichero f) {// proteger
        shared_info.add(f.getName());
        info.put(f.getName(), f);
        sem.release();
        
    }

    public Fichero getFile(String name) {
        return  info.get(name);
    }

    public int getPuerto() {// proteger
        int resul = puerto;
        puerto++;
        return resul;
    }
}
