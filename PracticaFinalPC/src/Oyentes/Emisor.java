package Oyentes;

import Mensajes.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class Emisor extends Thread {

    private int puerto;
    private Fichero f;
    private String clienteID;
    private Lock cerrojo;

    public Emisor(int puerto, Fichero f, String id, Lock cerrojo) {// recibir lock
        this.puerto = puerto;
        this.f = f;
        this.clienteID = id;
        this.cerrojo = cerrojo;
    }

    @Override
    public void run() {
        try {
            ServerSocket listen = new ServerSocket(puerto);
            Socket s = listen.accept();
            ObjectOutputStream fout = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream fin = new ObjectInputStream(s.getInputStream());

            fout.writeObject(f);

            // lock -> unlock
            cerrojo.lock();
            s.close();
            listen.close();
        } catch (Exception e) {
            System.err.println(e + " Error al mandar " + f.getName());
        }

    }

}