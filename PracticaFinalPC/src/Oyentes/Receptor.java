package Oyentes;

import Mensajes.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Semaphore;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class Receptor extends Thread {

    private int puerto;
    private String clienteID;
    private Lock cerrojo;
    private String ip;
    private Semaphore s;

    public Receptor(int puerto, String id, String ip, Lock cerrojo, Semaphore s) {// recibir lock
        this.puerto = puerto;
        this.clienteID = id;
        this.cerrojo = cerrojo;
        this.ip = ip;
        this.s = s;
    }

    @Override
    public void run() {
        Socket socket = new Socket(ip, puerto);
        ObjectInputStream fin = new ObjectInputStream(socket.getInputStream());
        Fichero f = (Fichero) fin.readObject();
        // muestro fichero//hacer algo
        s.release();// liberar c1
        cerrojo.unlock();// liberar c2
        socket.close();
    }
}