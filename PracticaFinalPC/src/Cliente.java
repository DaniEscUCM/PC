import Main.Fichero;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Cliente {

    private String nombre_de_usuario;
    private String direccion_ip;

    // ver
    private static Socket socket;

    public static void main(String[] args) {

        ObjectInputStream objectInput;
        
		try {
			objectInput = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			
			e.printStackTrace();
		}

        /*
          - leer nombre teclado 
          - crear socket con servidor 
          - crear nuevo thread OyenteServidor para leer el socket 
          - enviar MENSAJE_CONEXION 
          - establecer  menu con usuario: 
          		consultar lista usuarios: enviar MENSAJE_LISTA_USUARIOS
          -pedir fichero enviar MENSAJE_PEDIR_FICHERO 3 
          -salir enviar MENSAJE_CERRAR_CONEXION
          
         */

        Fichero f = (Fichero) objectInput.readObject();

    }
}