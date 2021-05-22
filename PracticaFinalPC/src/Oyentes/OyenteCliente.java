package Oyentes;

import Mensajes.*;
import Servidor.Servidor;

import java.io.*;
import java.net.Socket;

/**
 * Implementa el interfaz Runnable y hereda de la clase Thread, y es usada para
 * proporcionar concurrencia respecto a las sesiones de cada usuario con el
 * servidor. El metodo run y se limita a hacer lecturas del flujo de entrada
 * correspondiente, realizar las acciones oportunas, y devolver los resultados
 * en forma de mensajes que seran enviados al usuario o usuarios involucrados.
 */
public class OyenteCliente extends Thread {

    private Socket s;
    private ObjectInputStream fin;
    private ObjectOutputStream fout;
    private Servidor server;

    private final String GoodMsg = "Nuevo usuario conectado  :";
    private final String ErrMsg = "ERROR: no se establecio la coneccion";

    // private Servidor ser; --> lo dice en el video 47 33:15, se haga como en
    // prácticas anteriores,
    // pasar referencias de las variables. Como los IntReferences, los oyentes sería
    // los que modifican esta
    // información, se controlaría con concurrencia, semáforos?

    public OyenteCliente(Socket s, Servidor server) {
        this.s = s;
        this.server = server;
    }

    @Override
    public void start() {
        try {
        	System.out.println("Conectando con nuevo cliente ...");
            fout = new ObjectOutputStream(s.getOutputStream());// salida
            fin = new ObjectInputStream(s.getInputStream());// entrada
            while (true) {
            	System.out.println("Listo para trabajar");
                Mensaje m = (Mensaje) fin.readObject();
                switch (m.getTipo()) {
                    case "Mensaje_Conexion": {

                        Mensaje_Conexion men = (Mensaje_Conexion) m;
                        if (server.addUser(men.getOrigen(), men.getIp_cliente(), men.getShared_info(), fin, fout)) {
                            System.out.println(GoodMsg);
                            fout.writeObject(new Mensaje_Confirmacion_conexion("server", men.getDestino()));
                        } else {
                            System.out.println(ErrMsg);
                            fout.writeObject(new Mensaje_error_usuario_existente("server", men.getDestino()));
                        }

                        break;
                    }
                    default: {
                        System.err.println("DANGER unknown message");

                    }
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}