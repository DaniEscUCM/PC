package Oyentes;
import Mensajes.*;
import java.io.*;
import java.net.Socket;

/**
 * Implementa el interfaz “Runnable” o hereda de la clase “Thread”, y es usada
 * para proporcionar concurrencia respecto a las sesiones de cada usuario con el
 * servidor. El m´etodo “run()” se limita a hacer lecturas del flujo de entrada
 * correspondiente, realizar las acciones oportunas, y devolver los resultados
 * en forma de mensajes que ser´an enviados al usuario o usuarios involucrados.
 */
public class OyenteCliente extends Thread implements Runnable {

    private Socket s;
    //private Servidor ser;

    public OyenteCliente(Socket s) {
        this.s = s;
    }
    
    @Override
    public void run() {
        ObjectInputStream fin = new ObjectInputStream(s.getInputStream());
        while (true) {

            Mensaje m = (Mensaje) fin.readObject();
            /*
             * switch(m.getClass()){ case Mensaje_Cerrar_Conexion :{ break;} case :{ break;}
             * case :{ break;} case :{ break;} case :{ break;}
             * 
             * }
             */
            //excute(ser)

        }

    }

}