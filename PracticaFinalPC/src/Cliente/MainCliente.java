
package Cliente;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/* Clase principal de la aplicación cliente. Tendria al menos los siguientes
atributos: nombre de usuario, direccion ip de la maquina. Puedes tener tambi´en como
atributos los objetos que proporcionan la comunicaci´on con el servidor (socket y
flujos). Es responsable de llevar a cabo la comunicaci´on con el servidor, y cuando
sea necesario ejecutar el env´ıo o recepci´on de informaci´on. Adem´as ofrece el soporte
para la interacci´on con el usuario del sistema.
 */

public class MainCliente {

    // se supone que deben haber dos ficheros de texto aqui , no se si mas ,es la
    // informacion

    // ver
    private static Socket socket;
    private Scanner in;

    public static void main(String[] args) throws IOException {
/*
        Cliente client=new Cliente();

        System.out.println("Bienvenido al sistema introduce USUARIO e IP");
        String nombre_de_usuario;
        String ip;
        String[] words = in.nextLine().toLowerCase().trim().split ("\\s+");
        nombre_de_usuario=words[0];
        ip=words[1];
        Usuario user=new Usuario()

  
        ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());//Servidor/Fichero
      
        InputStream()
        Fichero f = (Fichero) objectInput.readObject();*/
        /*
          - leer nombre teclado 
          - crear socket con servidor 
          - crear nuevo thread OyenteServidor para leer el socket 
          - enviar MENSAJE_CONEXION 
          - establecer  menu con usuario: 
          		consultar lista usuarios: enviar MENSAJE_LISTA_USUARIOS
          -pedir fichero enviar MENSAJE_PEDIR_FICHERO 3 
          -salir enviar MENSAJE_CERRAR_CONEXION
          - crear nuevo thread
          OyenteServidor para leer el socket
           - enviar MENSAJE_CONEXION - establecer
          menu con usuario: consultar lista usuarios: enviar MENSAJE_LISTA_USUARIOS
          -pedir fichero enviar MENSAJE_PEDIR_FICHERO 3 -salir enviar
          MENSAJE_CERRAR_CONEXION
*/   

    }
}