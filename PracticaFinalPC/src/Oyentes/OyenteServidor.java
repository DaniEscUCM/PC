package Oyentes;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import Mensajes.Mensaje;

import Cliente.Cliente;

/**
 * implementa el interfaz Runnable o hereda de la clase Thread seria usada para
 * llevar a cabo una escucha continua en el canal de comunicacion con el
 * servidor, en un hilo diferente
 */
public class OyenteServidor extends Thread {

    private Cliente client;
    private Socket s;
    private ObjectInputStream fin;
    private ObjectOutputStream fout;

    public OyenteServidor(Cliente client, Socket s) {
        this.client = client;
        this.s = s;
    }

    @Override
    public void run() {
        try {
            System.out.println("Estableciendo conexi\u00f3n ...");
            fin = new ObjectInputStream(s.getInputStream());
            fout = new ObjectOutputStream(s.getOutputStream());
            //fout.writeObject(new Mensaje_Conexion(origen=yo,destino=mensaje.getOrigen,client.getId(),client.getId(),client.getShared_info );

            while (true) {
                Mensaje mensaje = (Mensaje) fin.readObject();// se queda oyendo esperando por mensaje del servidor
                switch(mensaje.getTipo()):{
                    case "":{
                        break;}
                    default : {
                        System.err.println("DANGER unknown message");
                    
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(e + " " + client.getId());
        }

    }
}