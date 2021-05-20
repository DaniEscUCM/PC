package Servidor;

import java.util.*;
import Mensajes.*;
import java.io.*;
import java.net.InetAddress;
import java.util.HashMap;

public class Servidor {// monitor?? synchronized??

    // tabla de usuarios,con flujo de entrada[0] y flujo de salida[1]
    private Map<String, Object[]> tabla_usuarios = new HashMap<String, Object[]>();// String Id_usuario

    // tabla de informacion son los ficheros que se quieren compartir con los demas
    private Map<String, ArrayList<Fichero>> tabla_informacion = new HashMap<String, ArrayList<Fichero>>();

    private String direccion_ip = "1.1.1.1";
    private final int PUERTO = 1234;

    public Servidor() {
    	//InetAddress a=new InetAddress;
        loadClients();
    }

    /*
     * ObjectOutputStream objectOutput = new
     * ObjectOutputStream(s.getOutputStream());
     * 
     * List<Fichero> listF = new ArrayList<Fichero>();
     * 
     * listF.add(new Fichero()); for (Fichero f : listF) {
     * objectOutput.writeObject(f); objectOutput.flush(); }
     */

    private static void loadClients() {
        try {
            FileInputStream filin = new FileInputStream("users.txt");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean addUser(String id, ObjectInputStream in, ObjectOutputStream out) {
        // lock
        // unlock
        return false;
    }

    public boolean deleteUser(String id) {
        // eliminar la informacion del cliente de las tablas

        // lock
        // unlock
        return false;
    }

    public boolean getFile(String origin, String destination, String nameOfFile) {
        return false;
    }

    public 

}
