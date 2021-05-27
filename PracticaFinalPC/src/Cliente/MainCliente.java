package Cliente;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
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

	private static String help = "help                   -   imprime este mensaje " + System.lineSeparator()
			+ "fichero nombre_fichero -   pide el fichero nombre_fichero y muestra su contenido por pantalla"
			+ System.lineSeparator() + "lista_usuarios         -   muestra por pantalla la lista de todos los usuario"
			+ System.lineSeparator()
			+ "cargar nombre_fichero  -   carga el fichero nombre_fichero y avisa al servidor. Tiene que ser .txt"
			+ System.lineSeparator() + "lista_ficheros         -   muestra los ficheros que se pueden cargar"
			+ System.lineSeparator() + "mis_ficheros           -   muestra los ficheros que tengo cargados"
			+ System.lineSeparator() + "exit                   -   salir y cortar todas las conexiones";

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Bienvenido Usuario");
		Cliente user;
		Boolean go = true;
		String server_ip = "localhost";

		// Semaphore mainSemaphore = new Semaphore(0);
		LockBakery lock = new LockBakery(2);

		System.out.print("Por favor, introduce nombre de usuario y direcci\u00f3n ip: ");
		String[] words = in.nextLine().toLowerCase().trim().split("\\s+");
		String nombre_de_usuario = words[0];
		String ip = words[1];
		user = new Cliente(nombre_de_usuario, ip);
		System.out.print("Introduzca IP de servidor: ");
		words = in.nextLine().toLowerCase().trim().split("\\s+");
		server_ip = words[0];

		System.out.println("USUARIO: " + user.getId() + " ip: " + user.getIp());

		try {
			Socket s = new Socket(server_ip, 1234);
			System.out.println("Estableciendo conexi\u00f3n ...");
			ObjectOutputStream fout = new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream fin = new ObjectInputStream(s.getInputStream());

			OyenteServidor o = new OyenteServidor(user, fin, fout, lock);
			o.start();
			System.out.println("mandando mensaje de conexi\u00f3n");

			fout.writeObject(
					new Mensaje_Conexion(user.getId(), "server", user.getId(), user.getIp(), user.getShared_info()));
			// mainSemaphore.acquire();
			lock.takeLock(0);
			lock.takeLock(1);

			while (go) {
				lock.takeLock(0);
				System.out.print("Introduzca una acci\u00f3n(help para ayuda):");
				words = in.nextLine().toLowerCase().trim().split("\\s+");// espera por una instruccion
				try {
					switch (words[0]) {
						case "exit": {
							System.out.println("Cerrando todas las conexiones");
							fout.writeObject(new Mensaje_Cerrar_Conexion(user.getId(), "server"));
							go = false;
							// mainSemaphore.acquire();
							lock.takeLock(1);
							break;
						}
						case "fichero": {
							fout.writeObject(new Mensaje_Pedir_Fichero(user.getId(), "server", words[1]));
							// mainSemaphore.acquire();
							lock.takeLock(1);
							break;
						}
						case "lista_usuarios": {
							fout.writeObject(new Mensaje_Lista_Usuarios(user.getId(), "server"));
							// mainSemaphore.acquire();
							lock.takeLock(1);
							break;
						}
						case "help": {
							System.out.println(help);
							break;
						}
						case "cargar": {
							Fichero file = new Fichero(words[1]);
							user.addShared_info(file);
							fout.writeObject(new Mensaje_Fichero_Cargado(user.getId(), "server", file.getName()));
							// mainSemaphore.acquire();
							lock.takeLock(1);
							System.out.println("FICHERO AGREGADO");
							break;
						}
						case "lista_ficheros": {
							fout.writeObject(new Mensaje_Lista_Ficheros(user.getId(), "server", null));
							lock.takeLock(1);
							// mainSemaphore.acquire();
							break;
						}
						case "mis_ficheros": {
							System.out.println(user.getShared_info());
							break;
						}
						default: {
							System.out.println("ERROR: COMMAND NOT RECONNIZED");
							break;
						}
					}
				} catch (Exception e) {
					System.err.println(e);
				}
				lock.releaseLock(1);
			}
			in.close();
			s.close();
		} catch (Exception e) {
			System.out.println(e + " Error al desconectar cliente");
		}
	}
}