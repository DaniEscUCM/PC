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
 * La clase Servidor contiene toda la informacion necesaria para realizar las distintas conexiones.
 * Trabaja como un monitor para proteger los datos que se modifican.
 *
 */
public class Servidor {// monitor?? synchronized??

    // tabla de usuarios,con flujo de entrada[0] y flujo de salida[1]
    private Map<String, Object[]> tabla_usuarios = new HashMap<String, Object[]>();// String Id_usuario

    // tabla de informacion son los ficheros que se quieren compartir con los demas
    private Map<String, ArrayList<Fichero>> tabla_informacion = new HashMap<String, ArrayList<Fichero>>();
    //CREO QUE SERA UNA LISTA DE NOMBRE DE LOS FICHEROS, LOS FICHEROS SOLO LOS TIENEN LOS CLIENTE.
    
    private String direccion_ip = "localhost";
    private final int PUERTO = 1234;
    
    public Servidor() {
        loadClients();
    }
    
    public Servidor(String ip) {
    	direccion_ip=ip;
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
        	String[] words =  filin.nextLine().toLowerCase().trim().split("\\s+");
        	while(words.length!=0) {        		
        		MainCliente.main(words);
        		words =  filin.nextLine().toLowerCase().trim().split("\\s+");
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

    //Metodos para los mensajes 
    
	public void cerrarConexion(String origen, String destino) {
		
		String id = fin.readLine();// pedir leer al cliente
        server.deleteUser(id);
        fout.writeBytes("Conexion Cerrada");
	}

	public void establecerConexion(String origen, String destino) {
		// TODO Auto-generated method stub
		
	}

	public void lista_usuarios(String origen, String destino) {
		// TODO Auto-generated method stub
		
	}

	public void pedir_fichero(String origen, String destino) {
		// TODO Auto-generated method stub
		
	}

	public void preparado_ciliente_servidor(String origen, String destino) {
		// TODO Auto-generated method stub
		
	}

 

}
