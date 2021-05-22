package Oyentes;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import Mensajes.Mensaje;
import Mensajes.Mensaje_Conexion;
import Cliente.Cliente;

/**
 * implementa el interfaz Runnable o hereda de la clase Thread seria usada para
 * llevar a cabo una escucha continua en el canal de comunicacion con el
 * servidor, en un hilo diferente
 */
public class OyenteServidor extends Thread {

    private Cliente client;
    private ObjectInputStream fin;
    private ObjectOutputStream fout;

    public OyenteServidor(Cliente client, ObjectInputStream fin, ObjectOutputStream fout) {
        this.client = client;
        this.fin = fin;
        this.fout = fout;
    }

    @Override
    public void start() {
        try {
            while (true) {
                Mensaje mensaje = (Mensaje) fin.readObject();// se queda oyendo esperando por mensaje del servidor
                
                switch (mensaje.getTipo()) {
                    case "mensaje_confirmacion_conexion": {
                        System.out.println("Conexión confirmada");
                        break;
                    }
                    case "mensaje_error_usuario_existente": {
                        System.out.println("Este usuario ya existía");
                    }
                    default: {
                        System.err.println("DANGER unknown message");

                    }
                }
            }
        } catch (Exception e) {
            System.err.println(e + " " + client.getId());
        }

    }
}