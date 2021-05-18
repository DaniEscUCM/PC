package Main;

import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.FileInputStream;

public class Fichero implements Serializable {

    private String name = "NULL";
    private ServerSocket listen;
    private Socket s;
    private byte[] buf;

    public Fichero(int puerto) {
        try {
            listen = new ServerSocket(puerto);
            while (true) {
                s = listen.accept();
                OutputStream o = s.getOutputStream();
                InputStream i = s.getInputStream();
                i.read(buf);
                name = new String(buf);
                FileInputStream filin = new FileInputStream(name);
                int v = filin.read();
                while (v != -1) {
                    o.write(v);
                    v = filin.read();
                }
                filin.close();
            }

        } catch (Exception e) {
            // insertar aqui procesado de error xD

        }

        // que le llamen
        // crea nuevo proceso
        // con la llamada me da el nombre del fichero
        // leo fichero
        // devuelve contenido en flujo de salida
    }

    // resibe nombre del fichero
    // contenido, flujo de salida
    // nuevo proceso, para cada comunicación

}
