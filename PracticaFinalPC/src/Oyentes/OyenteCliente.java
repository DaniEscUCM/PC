package Oyentes;

import Mensajes.*;
import Servidor.Servidor;

import java.io.*;
import java.net.Socket;

/**
 * Implementa el interfaz “Runnable” o hereda de la clase “Thread”, y es usada
 * para proporcionar concurrencia respecto a las sesiones de cada usuario con el
 * servidor. El m´etodo “run()” se limita a hacer lecturas del flujo de entrada
 * correspondiente, realizar las acciones oportunas, y devolver los resultados
 * en forma de mensajes que ser´an enviados al usuario o usuarios involucrados.
 */
public class OyenteCliente extends Thread {

    private Socket s;
    private ObjectInputStream fin;
    private ObjectOutputStream fout;
    private Servidor server;

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
    public void run() {
            try {
            fin = new ObjectInputStream(s.getInputStream());
            fout = new ObjectOutputStream(s.getOutputStream());
            while (true) {

                Mensaje m = (Mensaje) fin.readObject();

                switch (m.getTipo()) {
                    case "Mensaje_Cerrar_Conexion": {

                        /**
                         * eliminar la informacion del cliente en las tablas envio de mensaje de
                         * confirmacion fout
                         */
                        String id = fin.readLine();// pedir leer al cliente
                        server.deleteUser(id);
                        fout.writeBytes("Conexion Cerrada");
                        break;
                    }
                    case "Mensaje_Conexion": {
                        /**
                         * Guardar informacion del usuario en las tablas y enviar mensaje de
                         * confirmacion fout
                         */
                        String id = fin.readLine();// pedir leer al cliente
                        // String ip =;
                        // aqui hay que leer el ArrayList de alguna manera
                        server.addUser(id, fin, fout);
                        fout.writeBytes("Conexion Establecida");
                        break;
                    }
                    case "Mensaje_Lista_Usuarios": {
                        /**
                         * crear un mensaje con la informacion de usuarios del sistema
                         *  envio de mensaje de confirmacion lista de usuarios
                         */

                         server.info().
                        break;
                    }
                    case "Mensaje_Pedir_fichero": {
                        /**
                         * buscar usuario que contiene el fichero y obtener fout2 envio mensaje
                         * EMITIR_FICHERO por fout2
                         */
                        break;
                    }
                    case "Mensaje_Preparado_ClienteServidor": {
                        /**
                         * buscar fout1 (flujo del cliente al que hay qie enviar la informacion) envio
                         * de fout1 mensaje PREARADO_SERVIDOR_CLIENTE
                         */
                        break;
                    }

                }

                // excute(ser)

            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}