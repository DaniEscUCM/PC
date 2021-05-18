package Main;

import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.concurrent.Semaphore;

public class Fichero implements Serializable {

    private String name = "NULL";
    private ServerSocket listen;
    private Socket s;
    private byte[] buf;
    private Semaphore turn;
    private Semaphore body;
    private Boolean disp = true;
    private int waiting = 0;

    private Semaphore sem_read;// sem_read=Sem(0), el cliente debería tener el bool read=false y bloquearse
    private Semaphore sem_write;// sem_read=Sem(0)
    private Boolean read;// bool debería ser otra clase?? BoolReference.
    private Boolean finish;// indica al cliente si terminó de leer
    // private Boolean write;

    public Fichero(int puerto, Socket _s, Boolean read, Boolean write, Semaphore sem_read, Semaphore sem_write) {// s?
        try {
            this.read = read;
            // this.write=write;
            this.sem_read = sem_read;
            this.sem_write = sem_write;
            turn = new Semaphore(0);
            body = new Semaphore(1);
            this.s = _s;
            listen = new ServerSocket(puerto);
            this.read = false;
            // this.write=false;
            while (true) {
                s = listen.accept();// cliente

                body.acquire();
                while (!disp) {
                    waiting++;
                    body.release();
                    turn.acquire();// PT
                }
                disp = false;
                nuevo_hilo();
                disp = true;
                if (waiting > 0) {
                    waiting--;
                    turn.release();
                } else {
                    body.release();
                }
            }

        } catch (Exception e) {
            // insertar aqui procesado de error xD
        }
    }

    private void nuevo_hilo() throws Exception {
        OutputStream o = s.getOutputStream();
        InputStream i = s.getInputStream();
        i.read(buf);

        name = new String(buf);
        FileInputStream filin = new FileInputStream(name);

        int v = filin.read();
        while (v != -1) {
            read = true;
            o.write((char) v);
            sem_write.release();// libera al cliente para que lea
            // sem_read.acquire();// PT

            v = filin.read();
            read = false;
            sem_read.acquire(); // el cliente si está leyendo no puede escribir, espera a que el cliente le deje
        }
        finish = true;
        sem_read.release();// ??
        filin.close();
    }

}
