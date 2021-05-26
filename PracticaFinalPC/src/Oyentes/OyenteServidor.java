package Oyentes;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

import Cliente.Cliente;
import Mensajes.*;
import Cliente.LockBakery;

/**
 * implementa el interfaz Runnable o hereda de la clase Thread seria usada para
 * llevar a cabo una escucha continua en el canal de comunicacion con el
 * servidor, en un hilo diferente
 */
public class OyenteServidor extends Thread {

    private Cliente cliente;
    private ObjectInputStream fin;
    private ObjectOutputStream fout;
    private Semaphore sem;

    public OyenteServidor(Cliente cliente, ObjectInputStream fin, ObjectOutputStream fout, Semaphore sem) {
        this.cliente = cliente;
        this.fin = fin;
        this.fout = fout;
        this.sem = sem;
    }

    @Override
    public void run() {
        try {
            boolean go = true;
            while (go) {
                Mensaje mensaje = (Mensaje) fin.readObject();// se queda oyendo esperando por mensaje del servidor

                switch (mensaje.getTipo()) {
                    case "mensaje_confirmacion_conexion": {
                        System.out.println("Conexión confirmada");
                        this.sem.release();
                        break;
                    }
                    case "mensaje_error_usuario_existente": {
                        System.out.println("Este usuario ya existía");
                        this.sem.release();
                        break;
                    }
                    case "Mensaje_Confirmar_Desconexion": {
                        go = false;
                        System.out.println("Desconectado del Servidor");
                        this.sem.release();
                        break;
                    }
                    case "Mensaje_Error_Desconecion": {
                        System.out.println("Error al desconectar, usuario ya se había eliminado?");
                        this.sem.release();
                        break;
                    }
                    case "Mensaje_Confirmar_Lista_Usuarios": {
                        Mensaje_Confirmar_Lista_Usuarios men = (Mensaje_Confirmar_Lista_Usuarios) mensaje;
                        ArrayList<String> lista_usuarios = men.getLista();
                        System.out.println("Lista de usuarios Registrados :");
                        System.out.println(lista_usuarios);
                        this.sem.release();
                        break;
                    }
                    case "Mensaje_Emitir_Fichero": {
                        Mensaje_Emitir_Fichero men = (Mensaje_Emitir_Fichero) mensaje;

                       //int puerto = cliente.getPuerto();
                        LockBakery l = new LockBakery(2);
                        l.takeLock(0);

                        new Emisor(men.getPuerto(), cliente.getFile(men.getNombreFichero()), l).start();

                        fout.writeObject(new Mensaje_Preparado_ClienteServidor(cliente.getId(), "server",
                                cliente.getIp(), men.getPuerto(), men.getEmisor(), l));
                        break;
                    }
                    case "Mensaje_Preparado_ServidorCliente": {
                        Mensaje_Preparado_ServidorCliente men = (Mensaje_Preparado_ServidorCliente) mensaje;
                        (new Receptor(men.getPuerto(), men.getIp(), sem, men.getCerrojo())).start();
                        break;
                    }
                    case "Mensaje_Error_Fichero": {
                        System.out.println("Fichero no encontrado");
                        sem.release();
                        break;
                    }
                    case "Mensaje_Lista_Ficheros":{
                    	Mensaje_Lista_Ficheros men=(Mensaje_Lista_Ficheros) mensaje;
                    	System.out.println(men.getLista_ficheros());
                    	sem.release();
                    	break;
                    }
                    default: {
                        System.err.println("DANGER unknown message " + mensaje.getTipo());
                        sem.release();
                        break;

                    }
                }
            }

          //  fin.close();
         //   fout.close();
        } catch (Exception e) {
            System.err.println(e + " " + cliente.getId());
        }

    }
}