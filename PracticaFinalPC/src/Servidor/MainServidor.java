package Servidor;

import java.net.ServerSocket;
import java.net.Socket;

import Oyentes.OyenteCliente;

/**
 * @author Daniela Escobar & Alessandro de Armas
 */

public class MainServidor {

    private static ServerSocket listen;
    private static final int PUERTO = 1234;

    public static void main(String[] args) {
        Servidor server = new Servidor(PUERTO+1);
        try {
            listen = new ServerSocket(PUERTO);
        } catch (Exception e) {
            System.err.println(e + " error en el puerto");
        }
        Socket s;
        System.out.println("Listo para escuchar");
        while (true) {
            try {
                s = listen.accept();
                OyenteCliente o = new OyenteCliente(s, server);
                o.start();
            } catch (Exception e) {
                System.err.println(e + " Error con oyente");
            }

        }
    }

}