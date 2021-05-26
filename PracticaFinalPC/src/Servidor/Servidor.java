package Servidor;

import java.util.*;
import java.io.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import Mensajes.Mensaje_Preparado_ServidorCliente;

/**
 * @author Daniela Escobar y Alessandro de Armas
 * 
 *         La clase Servidor contiene toda la informacion necesaria para
 *         realizar las distintas conexiones. Trabaja como un monitor para
 *         proteger los datos que se modifican.
 *
 */
public class Servidor {

    private Map<String, Object[]> tabla_usuarios = new HashMap<String, Object[]>();
    // String Id_usuario , array con sus conexiones

    // tabla de informacion son los ficheros que se quieren compartir con los demas
    private Map<String, ArrayList<String>> tabla_informacion = new HashMap<String, ArrayList<String>>();

    // Tabla de cada id con su ip
    private Map<String, String> tabla_ip = new HashMap<String, String>();

    private ArrayList<String> users_names = new ArrayList<String>();

    // TODO lista de oyentes con los clientes, informar cuando se cierre el
    // servidor.
    // oyente[i].mensaje_de_cerrarConexion()

    private int nr = 0, nw = 0, waitw = 0;
    private Lock l = new ReentrantLock(true);
    private final Condition oktoread = l.newCondition(), oktowrite = l.newCondition();

    public Servidor() {
        // loadClients();
    }

    /*
     * private void loadClients() { try { Scanner filin = new Scanner("users.txt");
     * String[] words; while (filin.hasNextLine()) { words =
     * filin.nextLine().toLowerCase().trim().split("\\s+"); MainCliente.main(words);
     * } filin.close(); } catch (Exception e) { System.out.println(e); } }
     */

    public boolean addUser(String id, String ip, ArrayList<String> shared, ObjectInputStream in,
            ObjectOutputStream out) {

        l.lock();
        while (nw > 0 || nr > 0) {
            try {
                waitw++;
                oktowrite.wait();
            } catch (InterruptedException e) {
                System.out.println("dio un error en el oktowrite");
            }
        }
        waitw--;
        nw++;
        l.unlock();

        if (tabla_usuarios.containsKey(id)) {
            return false;
        }
        tabla_informacion.put(id, shared);
        users_names.add(id);
        tabla_usuarios.put(id, new Object[] { (Object) in, (Object) out });
        tabla_ip.put(id, ip);

        l.lock();
        nw--;
        oktowrite.signal();
        oktoread.signalAll();
        l.unlock();

        return true;
    }

    /*
     * public boolean deleteUser(String id) { l.lock(); while (nw > 0 || nr > 0) try
     * { oktowrite.wait(); } catch (InterruptedException e) {
     * 
     * } nw++; l.unlock(); // eliminar la informacion del cliente de las tablas
     * 
     * // lock // unlock return false; }
     */

    // Metodos para los mensajes

    public boolean cerrarConexion(String origen, String destino) {
        l.lock();
        while (nw > 0 || nr > 0) {
            try {
                waitw++;
                oktowrite.wait();
            } catch (InterruptedException e) {
                System.out.println("dio un error en el oktowrite");
            }
        }
        waitw--;
        nw++;
        l.unlock();

        if (!tabla_usuarios.containsKey(origen)) {
            return false;
        }
        tabla_usuarios.remove(origen);
        tabla_informacion.remove(origen);
        users_names.remove(origen);
        tabla_ip.remove(origen);
        l.lock();
        nw--;
        oktowrite.signal();
        oktoread.signalAll();
        l.unlock();
        return true;

    }

    public ArrayList<String> lista_usuarios(String origen, String destino) {

        l.lock();
        while (nw > 0 || waitw > 0) {
            try {
                oktoread.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        nr++;
        l.unlock();

        ArrayList<String> lista = users_names;

        l.lock();
        nr--;
        if (nr == 0 && waitw > 0)
            oktowrite.signal();
        l.unlock();

        return lista;
    }

    public String getUsuario_from_file(String nombreFichero) {
        l.lock();
        while (nw > 0 || waitw > 0) {
            try {
                oktoread.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        nr++;
        l.unlock();
        String user2 = "none";
        for (String user : users_names) {
            if (tabla_informacion.get(user).contains(nombreFichero)) {
                user2 = user;
                break;
            }
        }
        l.lock();
        nr--;
        if (nr == 0 && waitw > 0)
            oktowrite.signal();
        l.unlock();
        return user2;
    }

    public ObjectOutputStream getFlujo_from_user(String user) {
        l.lock();
        while (nw > 0 || waitw > 0) {
            try {
                oktoread.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        nr++;
        l.unlock();
        ObjectOutputStream fout = null;
        if (user != "none") {
            fout = (ObjectOutputStream) tabla_usuarios.get(user)[1];
        }
        l.lock();
        nr--;
        if (nr == 0 && waitw > 0)
            oktowrite.signal();
        l.unlock();
        return fout;
    }

    public void mandarMensaje(Mensaje_Preparado_ServidorCliente mensaje, String destinoFinal) {
        l.lock();
        while (nw > 0 || waitw > 0) {
            try {
                oktoread.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        nr++;
        l.unlock();

        try {
            ((ObjectOutputStream) tabla_usuarios.get(destinoFinal)[1]).writeObject(mensaje);
        } catch (Exception e) {
            System.err.println(e);
        }

        l.lock();
        nr--;
        if (nr == 0 && waitw > 0)
            oktowrite.signal();
        l.unlock();
    }

    public void addFileTo(String name_file, String origen) {
        tabla_informacion.get(origen).add(name_file);
    }

    public String lista_Ficheros() {

        l.lock();
        while (nw > 0 || waitw > 0) {
            try {
                oktoread.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        nr++;
        l.unlock();

        String ficheros_disponibles = "";
        for (String user : users_names) {
            ficheros_disponibles += tabla_informacion.get(user).toString();
        }

        l.lock();
        nr--;
        if (nr == 0 && waitw > 0)
            oktowrite.signal();
        l.unlock();

        return ficheros_disponibles;
    }

}
