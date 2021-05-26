package Oyentes;

import Mensajes.*;
import java.net.Socket;
import java.util.concurrent.Semaphore;
import java.io.ObjectInputStream;
import Cliente.LockBakery;

/**
 * 
 * @author Daniela Escobar & Alessandro de Armas
 * 
 * 			Manda el fichero solicitado y espera a que le desbloqueen para cerrar la comunición con el Receptor.
 *
 */
class Receptor extends Thread {

    private int puerto;
    private LockBakery cerrojo;
    private String ip;
    private Semaphore s;

    public Receptor(int puerto, String ip, Semaphore s, LockBakery cerrojo) {
        this.puerto = puerto;
        this.cerrojo = cerrojo;
        this.ip = ip;
        this.s = s;
    }

    @Override
    public void run() {
        Socket socket;
        try {
            socket = new Socket(ip, puerto);
            ObjectInputStream fin = new ObjectInputStream(socket.getInputStream());
            Fichero f = (Fichero) fin.readObject();

            System.out.println(f.getData());
            // muestro fichero//hacer algo
            s.release();// liberar c1

            cerrojo.releaseLock(0);// liberar c2
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}