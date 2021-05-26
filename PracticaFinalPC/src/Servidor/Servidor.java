package Servidor;

import java.util.*;
import java.io.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import Mensajes.Mensaje_Preparado_ServidorCliente;

/**
 * @author Daniela Escobar y Alessandro de Armas
 * 
 *         La clase Servidor contiene toda la informacion necesaria para
 *         realizar las distintas conexiones. Trabaja como un monitor para
 *         proteger los datos que se modifican.
 *
 */
public class Servidor {

    // String Id_usuario , array con sus conexiones
    private Map<String, Object[]> tabla_usuarios = new HashMap<String, Object[]>();

    // tabla de informacion son los ficheros que se quieren compartir con los demas
    private Map<String, ArrayList<String>> tabla_informacion = new HashMap<String, ArrayList<String>>();

    // Tabla de cada id con su ip
    private Map<String, String> tabla_ip = new HashMap<String, String>();

    //lista de todos los usuarios
    private ArrayList<String> users_names = new ArrayList<String>();
    
    //puerto para asignar a cada cliente y así  cada cliente tiene un puerto distinto
    private int puerto;

    //Variables condición del monitor
    private int nr = 0, nw = 0, waitw = 0;
    private Lock l = new ReentrantLock(true);
    private final Condition oktoread = l.newCondition(), oktowrite = l.newCondition();
    
    
    public Servidor(int puerto) {
		this.puerto=puerto;
	}


	public boolean addUser(String id, String ip, ArrayList<String> shared, ObjectInputStream in,
            ObjectOutputStream out) {

        take_writer();

        if (tabla_usuarios.containsKey(id)) {
            return false;
        }
        tabla_informacion.put(id, shared);
        users_names.add(id);
        tabla_usuarios.put(id, new Object[] { (Object) in, (Object) out });
        tabla_ip.put(id, ip);

        release_writer();

        return true;
    }

   
    public boolean cerrarConexion(String origen, String destino) {
        take_writer();

        if (!tabla_usuarios.containsKey(origen)) {
            return false;
        }
        tabla_usuarios.remove(origen);
        tabla_informacion.remove(origen);
        users_names.remove(origen);
        tabla_ip.remove(origen);

        release_writer();
        return true;

    }

    public ArrayList<String> lista_usuarios() {

        take_reader();

        ArrayList<String> lista = users_names;

        release_reader();

        return lista;
    }

    public String getUsuario_from_file(String nombreFichero) {
        take_reader();
        
        String user2 = "none";
        for (String user : users_names) {
            if (tabla_informacion.get(user).contains(nombreFichero)) {
                user2 = user;
                break;
            }
        }
        release_reader();
        return user2;
    }

    public ObjectOutputStream getFlujo_from_user(String user) {
        take_reader();
        
        ObjectOutputStream fout = null;
        if (user != "none") {
            fout = (ObjectOutputStream) tabla_usuarios.get(user)[1];
        }

        release_reader();
        return fout;
    }

    public void mandarMensaje(Mensaje_Preparado_ServidorCliente mensaje, String destinoFinal) {
    	take_reader();


        try {
            ((ObjectOutputStream) tabla_usuarios.get(destinoFinal)[1]).writeObject(mensaje);
        } catch (Exception e) {
            System.err.println(e);
        }

        release_reader();
    }

    public void addFileTo(String name_file, String origen) {
    	take_writer();
        tabla_informacion.get(origen).add(name_file);
        release_writer();
    }

    public ArrayList<String> lista_Ficheros() {

        take_reader();

        ArrayList<String> ficheros_disponibles = new ArrayList<String>();
        for (String user : users_names) {
            
        	ficheros_disponibles.addAll(tabla_informacion.get(user));
        }

        release_reader();

        return ficheros_disponibles;
    }
    
    
    
    public int getPuerto() {
    	take_writer();
    	int resul=this.puerto;
    	puerto++;
    	release_writer();
		return resul;
	}

    //Funciones para poder escribir/leer

	private void take_writer() {
    	l.lock();
        while (nw > 0 || nr > 0) {
            try {
                waitw++;
                oktowrite.wait();
                waitw--;
            } catch (InterruptedException e) {
                System.out.println("dio un error en el oktowrite");
            }
        }        
        nw++;
        l.unlock();
    }
    
    private void take_reader() {
    	l.lock();
        while (nw > 0 || waitw > 0) {
            try {
                oktoread.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        nr++;
        l.unlock();
    }
    
    private void release_writer() {
    	l.lock();
        nw--;
        oktowrite.signal();
        oktoread.signalAll();
        l.unlock();
    }
    
    private void release_reader(){
    	l.lock();
        nr--;
        if (nr == 0 && waitw > 0)
            oktowrite.signal();
        l.unlock();
    }

}
