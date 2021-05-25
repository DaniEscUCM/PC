package Oyentes;

import Mensajes.*;
import java.net.Socket;
import java.util.concurrent.Semaphore;

import Cliente.LockBakery;

import java.io.ObjectInputStream;

class Receptor extends Thread {

    private int puerto;
    private String clienteID;
    private LockBakery cerrojo;
    private String ip;
    private Semaphore s;

    public Receptor(int puerto, String id, String ip, LockBakery cerrojo, Semaphore s) {
        this.puerto = puerto;
        this.clienteID = id;
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