package Parte1;

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

public class ServidorParte1 extends Thread {

    private ServerSocket listen;
    private Socket s;
    // private byte[] buf;
    // private int puerto;

    /*
     * public ServidorParte1(int puerto) { this.puerto = puerto; }
     */
    @Override
    public void run() {
        try {
            listen = new ServerSocket(1234);
            //while (true) {
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
           // }

        } catch (Exception e) {
            System.out.println(e + " Servidor");
        }
    }

    private void nuevo_hilo(Socket s_priv) {
        try {

            ObjectOutputStream o = new ObjectOutputStream(s.getOutputStream());
            System.out.println("out de servidor");
            ObjectInputStream i = new ObjectInputStream(s.getInputStream());
            System.out.println("in de servidor");
            /*
             * i.read(buf);
             * 
             * String name = new String(buf); FileInputStream filin = new
             * FileInputStream(name); int v = filin.read();
             * 
             * while (v != -1) { o.write((char) v); v = filin.read(); } filin.close();
             */

            Prueba a = (Prueba) i.readObject();
            o.writeObject(a);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
