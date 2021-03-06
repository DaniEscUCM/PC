package Oyentes;

import Mensajes.*;
import Servidor.Servidor;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

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

    private final String GoodMsg = "Nuevo usuario conectado : ";
    private final String ErrMsg = "ERROR: no se establecio la conexion";

    public OyenteCliente(Socket s, Servidor server) {
        this.s = s;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            boolean go = true;
            System.out.println("Conectando con nuevo cliente ...");

            fout = new ObjectOutputStream(s.getOutputStream());// salida
            fin = new ObjectInputStream(s.getInputStream());// entrada

            System.out.println("Listo para trabajar");
            while (go) {
                
                Mensaje m = (Mensaje) fin.readObject();
                switch (m.getTipo()) {
                    case "Mensaje_Conexion": {
                        Mensaje_Conexion men = (Mensaje_Conexion) m;
                        if (server.addUser(men.getOrigen(), men.getIp_cliente(), men.getShared_info(), fin, fout)) {
                            System.out.println(GoodMsg + men.getOrigen());
                            fout.writeObject(new Mensaje_Confirmacion_conexion("server", men.getDestino()));
                        } else {
                            System.out.println(ErrMsg);
                            fout.writeObject(new Mensaje_error_usuario_existente("server", men.getDestino()));
                        }
                        break;
                    }
                    case "Mensaje_Cerrar_Conexion": {
                        if (server.cerrarConexion(m.getOrigen(), m.getDestino())) {
                            go = false;
                            System.out.println("El cliente: " + m.getOrigen() + " se ha desconectado");
                            fout.writeObject(new Mensaje_Confirmar_Desconexion("server", m.getOrigen()));
                        } else {
                            System.out.println("El cliente: " + m.getOrigen() + " no se ha desconectado");
                            fout.writeObject(new Mensaje_Error_Desconecion("server", m.getOrigen()));
                        }
                        break;
                    }
                    case "Mensaje_Lista_Usuarios": {
                        ArrayList<String> lista = server.lista_usuarios();
                        fout.writeObject(new Mensaje_Confirmar_Lista_Usuarios("server", m.getOrigen(), lista));
                        break;
                    }
                    case "Mensaje_Pedir_Fichero": {
                        Mensaje_Pedir_Fichero men = (Mensaje_Pedir_Fichero) m;
                        String owner = server.getUsuario_from_file(men.getNombreFichero());
                        ObjectOutputStream fout2 = server.getFlujo_from_user(owner);
                        int _puerto=server.getPuerto();

                        if (fout2 != null) {
                            fout2.writeObject(new Mensaje_Emitir_Fichero("server", owner, men.getNombreFichero(),
                                    men.getOrigen(),_puerto));
                        } else {
                            fout.writeObject(new Mensaje_Error_Fichero("server", men.getOrigen()));
                        }
                        break;
                    }
                    case "Mensaje_Preparado_ClienteServidor": {
                        Mensaje_Preparado_ClienteServidor men = (Mensaje_Preparado_ClienteServidor) m;
                        server.mandarMensaje(
                                new Mensaje_Preparado_ServidorCliente("server", men.getDestinoFinal(), men.getIP(),
                                        men.getPuerto(), men.getDestinoFinal()),
                                men.getDestinoFinal());
                        break;
                    }
                    case "Mensaje_Fichero_Cargado": {
                        Mensaje_Fichero_Cargado men = (Mensaje_Fichero_Cargado) m;

                        server.addFileTo(men.getFile(), men.getOrigen());
                        break;
                    }
                    case "Mensaje_Lista_Ficheros": {
                        fout.writeObject(new Mensaje_Lista_Ficheros("server", m.getOrigen(), server.lista_Ficheros()));
                        break;
                    }
                    default: {
                        System.err.println("DANGER unknown message " + m.getTipo());
                        break;
                    }
                }

            }
            
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}