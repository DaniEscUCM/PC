package Servidor;

import java.net.ServerSocket;
import java.net.Socket;

import Oyentes.OyenteCliente;

/**
 * @author Daniela Escobar & Alessandro de Armas
 */
public class MainServidor {

    private static ServerSocket listen;

    public static void main(String[] args) {
        Servidor server = new Servidor();
        Socket s;
        while (true) {
            try {
                s = listen.accept();
                (new OyenteCliente(s, server)).start();
            } catch (Exception e) {

            }

        }
    }

}