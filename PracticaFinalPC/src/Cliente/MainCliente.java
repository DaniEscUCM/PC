
package Cliente;

import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

import Mensajes.*;
import Oyentes.OyenteServidor;

/* Clase principal de la aplicacion cliente. Tendria al menos los siguientes
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

	private static String help = "exit - salir y cortar todas las conexiones";// +System.lineSeparator())

	public static void main(String[] args)  {
		Scanner in = new Scanner(System.in);
		System.out.println("Bienvenido Usuario");
		Cliente user;
		Boolean go = true;
		String server_ip = "localhost";
		Semaphore mainSemaphore = new Semaphore(0);

		if (args.length > 0) {
			String user_id = args[0];
			String user_ip = args[1];
			ArrayList<String> names = new ArrayList<String>();
			Map<String, Fichero> files = new HashMap<String, Fichero>();
			for (int i = 2; i < args.length - 1; i++) {
				//Fichero x = new Fichero(args[i]);
				//files.put(x.getName(), x);
				//names.add(x.getName());
			}
			user = new Cliente(user_id, user_ip, names, files,mainSemaphore);
		} else {
			System.out.print("Por favor, introduce nombre de usuario y dirección ip: ");
			String[] words = in.nextLine().toLowerCase().trim().split("\\s+");
			String nombre_de_usuario = words[0];
			String ip = words[1];
			user = new Cliente(nombre_de_usuario, ip,mainSemaphore);
			System.out.print("Introduzca IP de servidor: ");
			words = in.nextLine().toLowerCase().trim().split("\\s+");
			server_ip = words[0];
		}

		System.out.println("USUARIO: " + user.getId() + " ip: " + user.getIp());

		try {
			Socket s = new Socket(server_ip, 1234);
			System.out.println("Estableciendo conexi\u00f3n ...");
			ObjectOutputStream fout = new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream fin = new ObjectInputStream(s.getInputStream());

			OyenteServidor o = new OyenteServidor(user, fin, fout, mainSemaphore);
			o.start();

			System.out.println("mandando mensaje de conexion");

			fout.writeObject(
					new Mensaje_Conexion(user.getId(), "server", user.getId(), user.getIp(), user.getShared_info()));
			mainSemaphore.acquire();
			while (go) {
				System.out.println("Introduzca una accion(help para ayuda):");
				String[] words = in.nextLine().toLowerCase().trim().split("\\s+");// espera por una instruccion

				switch (words[0]) {
					case "exit": {
						System.out.println("Cerrando todas las conexiones");
						fout.writeObject(new Mensaje_Cerrar_Conexion(user.getId(), "server"));
						// TODO cerrar conexiones con los otros useres si es que hay
						go = false;
						mainSemaphore.acquire();
						break;
					}
					case "fichero": {
						fout.writeObject(new Mensaje_Pedir_Fichero(user.getId(), "server", words[1]));
						mainSemaphore.acquire();
						break;
					}
					case "lista_usuarios": {
						fout.writeObject(new Mensaje_Lista_Usuarios(user.getId(), "server"));
						mainSemaphore.acquire();
						break;
					}
					case "help": {
						System.out.println(help);
						break;
					}
					case "cargar": {
						Fichero file = new Fichero(words[1]);
						user.addShared_info(file);	
						fout.writeObject(new Mensaje_Fichero_Cargado(user.getId(),"server",file.getName()));
						mainSemaphore.acquire();	
						System.out.println("FICHERO AGREGADO");
						
						break;
					}
					default: {
						System.out.println("ERROR: COMMAND NOT RECONNIZED");
						break;
					}
				}

			}
			in.close();
			s.close();
		} catch (Exception e) {
			System.err.println(e);
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