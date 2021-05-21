
package Cliente;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Mensajes.Fichero;
import Oyentes.OyenteServidor;

/* Clase principal de la aplicación cliente. Tendria al menos los siguientes
atributos: nombre de usuario, direccion ip de la maquina. Puedes tener tambi´en como
atributos los objetos que proporcionan la comunicaci´on con el servidor (socket y
flujos). Es responsable de llevar a cabo la comunicaci´on con el servidor, y cuando
sea necesario ejecutar el env´ıo o recepci´on de informaci´on. Adem´as ofrece el soporte
para la interacci´on con el usuario del sistema.
 */
/**
 * 
 * @author Daniela Esocbar & Alessandro de Armas
 * 
 *         Comunica con el usuario por consola, recibe los comandos y manda las
 *         instrucciones al cliente.
 *
 */

public class MainCliente {

	// se supone que deben haber dos ficheros de texto aqui , no se si mas ,es la
	// informacion???khe

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		System.out.println("Bienvenido Usuario");
		Usuario user;
		Boolean go = true;
		String server_ip = "localhost";

		if (args.length > 0) {
			String user_id = args[0];
			String user_ip = args[1];
			ArrayList<String> names = new ArrayList<String>();
			Map<String, Fichero> files = new HashMap<String, Fichero>();
			for (int i = 2; i < args.length - 1; i++) {
				Fichero x = new Fichero(args[i]);
				files.put(x.getName(), x);
				names.add(x.getName());
			}
			user = new Usuario(user_id, user_ip, names, files);
		} else {
			System.out.print("Por favor, introduce nombre de usuario y direcci\u00edn ip: ");
			String[] words = in.nextLine().toLowerCase().trim().split("\\s+");
			String nombre_de_usuario = words[0];
			String ip = words[1];
			user = new Usuario(nombre_de_usuario, ip);
			System.out.print("Introduzca IP de servidor: ");
			words = in.nextLine().toLowerCase().trim().split("\\s+");
			server_ip = words[0];
		}
		Cliente client = new Cliente(user);
		Socket s = new Socket(server_ip, 1234);
		(new OyenteServidor(client, s)).start();
		while (go) {
			String[] words = in.nextLine().toLowerCase().trim().split("\\s+");// espera por una instruccion

			switch (words[0]) {
				case "exit": {
					System.out.println("Cerrando todas las conexiones");
					// se cierran todas las conexiones
					go = false;
					break;
				}
				default: {
					System.out.println("ERROR: COMMAND NOT RECONNIZED");
					break;
				}
			}
			in.close();

		}
		/*
		 * 
		 * InputStream() Fichero f = (Fichero) objectInput.readObject();->esto sería
		 * agregar un nuevo archivo??
		 *
		 * 
		 */
		/*
		 * - leer nombre teclado - crear socket con servidor - crear nuevo thread
		 * OyenteServidor para leer el socket - enviar MENSAJE_CONEXION - establecer
		 * menu con usuario: consultar lista usuarios: enviar MENSAJE_LISTA_USUARIOS
		 * -pedir fichero enviar MENSAJE_PEDIR_FICHERO 3 -salir enviar
		 * MENSAJE_CERRAR_CONEXION - crear nuevo thread OyenteServidor para leer el
		 * socket - enviar MENSAJE_CONEXION - establecer menu con usuario: consultar
		 * lista usuarios: enviar MENSAJE_LISTA_USUARIOS -pedir fichero enviar
		 * MENSAJE_PEDIR_FICHERO 3 -salir enviar MENSAJE_CERRAR_CONEXION
		 */

	}
}