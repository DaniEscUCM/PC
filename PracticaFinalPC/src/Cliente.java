import Main.Fichero;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;

public class Cliente {

    private String nombre_de_usuario;
    private String direccion_ip;

    // se supone que deben haber dos ficheros de texto aqui , no se si mas ,es la
    // informacion

    // ver
    private static Socket socket;

    public static void main(String[] args) throws IOException {
        //cin>>x;

        InputStream input = new InputStream();

        //imprimir System.out.println(...)
        //leer System.in.read(buffer)
        nombre_de_usuario = input.read();
        
        ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());//Servidor/Fichero
      
        InputStream()
        Fichero f = (Fichero) objectInput.readObject();
       
        

        /*
          - leer nombre teclado 
          - crear socket con servidor 
          - crear nuevo thread
          OyenteServidor para leer el socket
           - enviar MENSAJE_CONEXION - establecer
          menu con usuario: consultar lista usuarios: enviar MENSAJE_LISTA_USUARIOS
          -pedir fichero enviar MENSAJE_PEDIR_FICHERO 3 -salir enviar
          MENSAJE_CERRAR_CONEXION
          
         */

        

    }
}