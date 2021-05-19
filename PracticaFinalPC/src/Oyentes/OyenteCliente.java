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
    ObjectInputStream fin;
    ObjectOutputStream fout;

    //private Servidor ser; --> lo dice en el video 47 33:15, se haga como en prácticas anteriores,
    // pasar referencias de las variables. Como los IntReferences, los oyentes sería los que modifican esta 
    //información, se controlaría con concurrencia, semáforos?

    public OyenteCliente(Socket s) {
        this.s = s;
    }
    
    @Override
    public void run() {
        try{
        fin = new ObjectInputStream(s.getInputStream());
        fout = new ObjectOutputStream(s.getOutputStream());
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
        }catch(Exception e){
            System.out.println(e);
        }

    }

}