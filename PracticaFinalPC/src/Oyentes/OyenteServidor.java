package Oyentes;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Cliente.Cliente;
import Mensajes.Mensaje;

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
    public void run() {
        try {
        	boolean go=true;
            while (go) {
                Mensaje mensaje = (Mensaje) fin.readObject();// se queda oyendo esperando por mensaje del servidor
                
                switch (mensaje.getTipo()) {
                    case "mensaje_confirmacion_conexion": {
                        System.out.println("Conexión confirmada");
                        break;
                    }
                    case "mensaje_error_usuario_existente": {
                        System.out.println("Este usuario ya existía");
                        break;
                    }
                    case "Mensaje_Confirmar_Desconecion":{
                    	go=false;
                    	System.out.println("Desconectado del Servidor");
                    	break;
                    }
                    case "Mensaje_Error_Desconecion":{
                    	System.out.println("Error al desconectar, usuario ya se había eliminado?");
                    	break;
                    }
                    default: {
                        System.err.println("DANGER unknown message");
                        break;

                    }
                }
            }
            fin.close();
            fout.close();
        } catch (Exception e) {
            System.err.println(e + " " + client.getId());
        }

    }
}