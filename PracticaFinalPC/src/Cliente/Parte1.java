package Parte1;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.FileInputStream;

/**
 * @author Daniela Escobar & Alessandro de Armas
 * 
 *         Conexión con los Sistema de ficheros
 * 
 */

public class Parte1 implements Runnable {

    private ServerSocket listen;
    private Socket s;
    private byte[] buf;
    private int puerto = -1;

    public Parte1(int puerto) {
        this.puerto = puerto;
    }

    @Override
    public void run() {
        try {
            listen = new ServerSocket(puerto);

            while (true) {
                s = listen.accept();// cliente

                new Thread() {
                    @Override
                    public void run() {
                        nuevo_hilo(s);
                    }
                };

            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void nuevo_hilo(Socket s_priv) {
        try {

            OutputStream o = s.getOutputStream();
            InputStream i = s.getInputStream();

            i.read(buf);

            String name = new String(buf);
            FileInputStream filin = new FileInputStream(name);
            int v = filin.read();

            while (v != -1) {
                o.write((char) v);
                v = filin.read();
            }
            filin.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
