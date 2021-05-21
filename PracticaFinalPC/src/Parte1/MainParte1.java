package Parte1;

import java.util.*;
import java.io.*;
import java.net.Socket;

public class MainParte1 {
    public static void main(String[] args) {
        try {
            System.out.println("Welcome");
            // ServidorParte1 server = new ServidorParte1();
            // server.start();
            Scanner in = new Scanner(System.in);
            Socket s = new Socket("192.168.43.5", 1234);

            ObjectOutputStream fout = new ObjectOutputStream(s.getOutputStream());

            // System.out.println("out de cliente");
            ObjectInputStream fin = new ObjectInputStream(s.getInputStream());
            // System.out.println("in de cliente");

            /*
             * int n = -1; n = in.nextInt(); while (n != -1) {
             */
            System.out.println("Escribe mensaje:");
            String mensaje = "";
            mensaje = in.nextLine();
            Prueba x = new Prueba(mensaje);
            fout.writeObject(x);
            Prueba good = (Prueba) fin.readObject();
            System.out.println(good.getData());
            // n = in.nextInt();
            // }
            s.close();
            in.close();
            // esperala conexion de un cliente y
            // cuando la recive establece un socket con el
        } catch (Exception e) {
            System.out.println(e + " Cliente");
        }
    }

}
