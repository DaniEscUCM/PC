package Servidor;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;
import java.util.*;
import Mensajes.*;
import Cliente.Usuario;
import java.util.HashMap;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import Oyentes.*;

/**
 * @author Daniela Escobar & Alessandro de Armas
 */
public class Servidor {
    // tabla de usuarios,con flujo de entrada[0] y flujo de salida[1]
    private Map<Usuario, Object[]> tabla_usuarios = new HashMap<Usuario, Object[]>();

    // tabla de informacion son los ficheros que se quieren compartir con los demas
    private Map<Usuario, ArrayList<Fichero>> tabla_informacion = new HashMap<Usuario, ArrayList<Fichero>>();

    private String direccion_ip = "1.1.1.1";
    private int puerto = 666;

    private static ServerSocket listen;

    public static void main(String[] args) {
        loadClients();
        Socket s;
        while (true) {
            try {
                s = listen.accept();
                (new OyenteCliente(s)).start();

                /*
                 * ObjectOutputStream objectOutput = new
                 * ObjectOutputStream(s.getOutputStream());
                 * 
                 * List<Fichero> listF = new ArrayList<Fichero>();
                 * 
                 * listF.add(new Fichero()); for (Fichero f : listF) {
                 * objectOutput.writeObject(f); objectOutput.flush(); }
                 */
            } catch (Exception e) {

            }

        }
    }

    private static void loadClients() {
        try {
            FileInputStream filin = new FileInputStream("users.txt");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}