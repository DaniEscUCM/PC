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

    private int nr = 0, nw = 0, waitw = 0;
    private Lock l = new ReentrantLock(true);
    private final Condition oktoread = l.newCondition(), oktowrite = l.newCondition();

    public Cliente(String id_usuario, String direccion_ip, ArrayList<String> lis, Map<String, Fichero> m,
            Semaphore sem) {
        this.id_usuario = id_usuario;
        this.direccion_ip = direccion_ip;
        this.shared_info = lis;
        this.info = m;
        this.sem = sem;
    }

    public Cliente(String id_usuario, String direccion_ip, Semaphore sem) {
        this.id_usuario = id_usuario;
        this.direccion_ip = direccion_ip;
        this.sem = sem;
    }

    /*
     * public void mensajeConexionServidor() {
     * System.out.println("Conexion establecida con el servidor"); }
     */

    public String getIp() {
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

        l.lock();
        nr--;
        if (nr == 0 && waitw > 0)
            oktowrite.signal();
        l.unlock();
        return direccion_ip;
    }

    public String getId() {
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

        l.lock();
        nr--;
        if (nr == 0 && waitw > 0)
            oktowrite.signal();
        l.unlock();
        return id_usuario;
    }

    public ArrayList<String> getShared_info() {
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

        ArrayList<String> resul = shared_info;

        l.lock();
        nr--;
        if (nr == 0 && waitw > 0)
            oktowrite.signal();
        l.unlock();

        return resul;
    }

    public void addShared_info(Fichero f) {// proteger
        l.lock();
        while (nw > 0 || nr > 0) {
            try {
                waitw++;
                oktowrite.wait();
            } catch (InterruptedException e) {
                System.out.println("dio un error en el oktowrite");
            }
        }
        waitw--;
        nw++;
        l.unlock();

        shared_info.add(f.getName());
        info.put(f.getName(), f);
        sem.release();

        l.lock();
        nw--;
        oktowrite.signal();
        oktoread.signalAll();
        l.unlock();
    }

    public Fichero getFile(String name) {
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

        Fichero resul = info.get(name);

        l.lock();
        nr--;
        if (nr == 0 && waitw > 0)
            oktowrite.signal();
        l.unlock();

        return resul;
    }

    public int getPuerto() {
        l.lock();
        while (nw > 0 || nr > 0) {
            try {
                waitw++;
                oktowrite.wait();
            } catch (InterruptedException e) {
                System.out.println("dio un error en el oktowrite");
            }
        }
        waitw--;
        nw++;
        l.unlock();

        int resul = puerto;
        puerto++;

        l.lock();
        nw--;
        oktowrite.signal();
        oktoread.signalAll();
        l.unlock();

        return resul;
    }
}
