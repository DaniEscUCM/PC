package Oyentes;

import Mensajes.*;
import java.net.ServerSocket;
import java.net.Socket;

import Cliente.LockBakery;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class Emisor extends Thread {

    private int puerto;
    private Fichero f;
    private String clienteID;
   // private LockBakery cerrojo;

    public Emisor(int puerto, Fichero f, String id) {// recibir lock
        this.puerto = puerto;
        this.f = f;
        this.clienteID = id;
   //     this.cerrojo = cerrojo;
    }

    @Override
    public void run() {
        try {
            ServerSocket listen = new ServerSocket(puerto);
            Socket s = listen.accept();
            ObjectOutputStream fout = new ObjectOutputStream(s.getOutputStream());
            // ObjectInputStream fin = new ObjectInputStream(s.getInputStream());

            fout.writeObject(f);
        //    cerrojo.takeLock(0);
          //  s.close();
           // listen.close();
        } catch (Exception e) {
            System.err.println(e + " Error al mandar " + f.getName());
        }

    }

}