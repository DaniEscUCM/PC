package Oyentes;

import Mensajes.*;
import java.net.ServerSocket;
import java.net.Socket;

import java.io.ObjectOutputStream;

/**
 * 
 * @author Daniela Escobar & Alessandro de Armas
 * 
 *         Manda el fichero solicitado.
 *
 */

class Emisor extends Thread {

    private int puerto;
    private Fichero f;

    public Emisor(int puerto, Fichero f) {
        this.puerto = puerto;
        this.f = f;
    }

    @Override
    public void run() {
        try {
            ServerSocket listen = new ServerSocket(puerto);
            Socket s = listen.accept();
            ObjectOutputStream fout = new ObjectOutputStream(s.getOutputStream());

            fout.writeObject(f);

            s.close();
            listen.close();
        } catch (Exception e) {
            System.err.println(e + " Error al mandar " + f.getName());
        }

    }

}