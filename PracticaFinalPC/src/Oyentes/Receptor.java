package Oyentes;

import Mensajes.*;
import java.net.Socket;
import Cliente.LockBakery;
import java.io.ObjectInputStream;

/**
 * 
 * @author Daniela Escobar & Alessandro de Armas
 * 
 *         Recibe el fichero que había solicitado antes.
 *
 */
class Receptor extends Thread {

    private int puerto;
    private String ip;
    // private Semaphore cerrojo;
    private LockBakery cerrojo;

    public Receptor(int puerto, String ip, LockBakery cerrojo) {
        this.puerto = puerto;
        this.ip = ip;
        this.cerrojo = cerrojo;
    }

    @Override
    public void run() {
        Socket socket;
        try {
            socket = new Socket(ip, puerto);
            ObjectInputStream fin = new ObjectInputStream(socket.getInputStream());
            Fichero f = (Fichero) fin.readObject();
            System.out.println(f.getData());
            cerrojo.releaseLock(0);// liberar el teclado del cliente que solicita.
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}