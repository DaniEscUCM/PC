package Parte1;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Daniela Escobar & Alessandro de Armas
 * 
 *         Conexión con los Sistema de ficheros
 * 
 */

public class ServidorParte1  {

    @SuppressWarnings("resource")
	public static void main(String[] args) {
        try {
        	ServerSocket listen;
        	Socket s;
            listen = new ServerSocket(1234);
            System.out.println(InetAddress.getLocalHost());
            System.out.println("esperando...");
            s = listen.accept();// cliente
            System.out.println("Cliente conectado");
            Thread a=new Thread() {
            @Override
	            public void run() {
	                     nuevo_hilo(s);
	            }
            };
            a.start();
            a.join();
            s.close();
            listen.close();

        } catch (Exception e) {
            System.out.println(e + " Servidor");
        }
    }

    private static void nuevo_hilo(Socket s_priv) {
        try {

            ObjectOutputStream o = new ObjectOutputStream(s_priv.getOutputStream());
           
            ObjectInputStream i = new ObjectInputStream(s_priv.getInputStream());
        
            
            Prueba a = (Prueba) i.readObject();
            Prueba b=new Prueba(a.getData()+" Saludos del servidor");
            System.out.println(b.getData());
            o.writeObject(b);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
