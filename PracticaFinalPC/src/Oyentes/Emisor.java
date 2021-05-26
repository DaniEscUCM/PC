package Oyentes;

import Mensajes.*;
import java.net.ServerSocket;
import java.net.Socket;

import Cliente.LockBakery;

import java.io.ObjectOutputStream;

class Emisor extends Thread {

    private int puerto;
    private Fichero f;
    private LockBakery cerrojo;

    public Emisor(int puerto, Fichero f, LockBakery cerrojo) {// recibir lock
        this.puerto = puerto;
        this.f = f;
        this.cerrojo = cerrojo;
    }

    @Override
    public void run() {
        try {
            ServerSocket listen = new ServerSocket(puerto);
            Socket s = listen.accept();
            ObjectOutputStream fout = new ObjectOutputStream(s.getOutputStream());

            fout.writeObject(f);

            cerrojo.takeLock(1);
            s.close();
            listen.close();
        } catch (Exception e) {
            System.err.println(e + " Error al mandar " + f.getName());
        }

    }

}