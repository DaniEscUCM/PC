package Cliente;

import Mensajes.Fichero;
import java.util.*;
import java.util.concurrent.Semaphore;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Daniela Escobar & Alessandro de Armas
 * 
 *         Clase cliente que guarda al usuario, sus conexiones y realiza las
 *         acciones que tienen que ver con lo que solicita acceder otro cliente.
 *         Similar a la clase Servidor, actua como un monitor de Escritores-lectores.
 */

public class Cliente {

    private String direccion_ip;
    private String id_usuario;
    private Semaphore sem;
    
    //lista de los nombres de los ficheros que se tienen cargados
    ArrayList<String> shared_info = new ArrayList<String>();
    
    //mapa de los ficheros cargados
    Map<String, Fichero> info = new HashMap<String, Fichero>();
    
    //Variables condición de monitor
    private int nr = 0, nw = 0, waitw = 0;
    private Lock l = new ReentrantLock(true);
    private final Condition oktoread = l.newCondition(), oktowrite = l.newCondition();

    public Cliente(String id_usuario, String direccion_ip, Semaphore sem) {
        this.id_usuario = id_usuario;
        this.direccion_ip = direccion_ip;
        this.sem = sem;
    }

    public String getIp() {
       take_reader();
       
       String resul=direccion_ip;

       release_reader();
       return resul;
    }

    public String getId() {
        take_reader();
        
        String resul=id_usuario;
        
        release_reader();
        return resul;
    }

    public ArrayList<String> getShared_info() {
        take_reader();

        ArrayList<String> resul = shared_info;

        release_reader();

        return resul;
    }

    public void addShared_info(Fichero f) {
        take_writer();

        shared_info.add(f.getName());
        info.put(f.getName(), f);
        sem.release();

        release_writer();
    }

    public Fichero getFile(String name) {
        take_reader();

        Fichero resul = info.get(name);

        release_reader();

        return resul;
    }

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
