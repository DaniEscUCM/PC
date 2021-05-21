package Servidor;

import java.util.*;
import Cliente.MainCliente;
import Mensajes.*;
import java.io.*;

/**
 * 
 * 
 * @author Daniela Escobar y Alessandro de Armas
 * 
 *         La clase Servidor contiene toda la informacion necesaria para
 *         realizar las distintas conexiones. Trabaja como un monitor para
 *         proteger los datos que se modifican.
 *
 */
public class Servidor {// monitor?? synchronized??

    // tabla de usuarios,con flujo de entrada[0] y flujo de salida[1] --> no sería
    // mejor solo IPs??
    private Map<String, Object[]> tabla_usuarios = new HashMap<String, Object[]>();// String Id_usuario

    // tabla de informacion son los ficheros que se quieren compartir con los demas
    private Map<String, ArrayList<Fichero>> tabla_informacion = new HashMap<String, ArrayList<Fichero>>();
    // CREO QUE SERA UNA LISTA DE NOMBRE DE LOS FICHEROS, LOS FICHEROS SOLO LOS
    // TIENEN LOS CLIENTE.

    // Mapa con las direcciones ip de los usuarios -> para poder hacer que se
    // conectan los clientes entre sí

    // lista de oyentes con los clientes
    // oyente[i].mensaje_de_cerrarConexion()

    private String direccion_ip = "localhost";
    private final int PUERTO = 1234;

    public Servidor() {
        loadClients();
    }

    public Servidor(String ip) {
        direccion_ip = ip;
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

    private synchronized void loadClients() {
        try {
            Scanner filin = new Scanner("users.txt");
            String[] words = filin.nextLine().toLowerCase().trim().split("\\s+");
            while (words.length != 0) {
                MainCliente.main(words);
                words = filin.nextLine().toLowerCase().trim().split("\\s+");
            }
            filin.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public synchronized boolean addUser(String id, ObjectInputStream in, ObjectOutputStream out) {
        // lock
        // unlock
        return false;
    }

    public synchronized boolean deleteUser(String id) {
        // eliminar la informacion del cliente de las tablas

        // lock
        // unlock
        return false;
    }

    public synchronized boolean getFile(String origin, String destination, String nameOfFile) {
        return false;
    }

    // Metodos para los mensajes

    public void cerrarConexion(String origen, String destino) {
        /*
         * String id = fin.readLine();// pedir leer al cliente server.deleteUser(id);
         * fout.writeBytes("Conexion Cerrada");
         */
    }

    public void establecerConexion(String origen, String destino) {
        // necesita la informacion del usuario para guardarla en las tablas fin

        // se guarda la informacion del cliente en las tablas compartir fin
        System.out.println("Se ha conectado el usuario: ");// se supone que también debemos tener las conexiones en un
                                                           // array
        // se pasa un mensaje de confirmacion fout
    }

    public void lista_usuarios(String origen, String destino) {
        // TODO Auto-generated method stub
        /*
         * claro qe va a hacer falta mas info , lo vamos cambiando , vamos a hacer
         * establecer conexion a ver si va exacto
         */

    }

    public void pedir_fichero(String origen, String destino) {
        // TODO Auto-generated method stub

    }

    public void preparado_ciliente_servidor(String origen, String destino) {
        // TODO Auto-generated method stub

    }

}
