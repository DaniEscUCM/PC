package Oyentes;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Cliente.Cliente;

/**
 * implementa el interfaz “Runnable” o hereda de la clase “Thread”, y seria
 * usada para llevar a cabo una escucha continua en el canal de comunicacion con
 * el servidor, en un hilo diferente
 */
public class OyenteServidor extends Thread {
	
	private Cliente client;
	private Socket s;
    private ObjectInputStream fin;
    private ObjectOutputStream fout;

    public OyenteServidor(Cliente client, Socket s) {
    	this.client=client;
    	this.s=s;
    }

    @Override
    public void run() {
    	try {
            fin = new ObjectInputStream(s.getInputStream());
            fout = new ObjectOutputStream(s.getOutputStream());
            while (true) {
            	
            }
    	}catch(Exception e) {
    		System.out.println(e+" "+client.getId());
    	}

    }
}