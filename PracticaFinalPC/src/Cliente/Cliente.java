package Cliente;

import Mensajes.Fichero;

/**
 * 
 * @author Daniela Escobar & Alessandro de Armas
 * 
 *         Clase cliente que guarda al usuario, sus conexiones y realiza las
 *         acciones que tienen que ver con lo que solicita acceder otro cliente
 *         y su usuario. Similar a la clase Servidor.
 */
public class Cliente {
    // deberiamos guardar por aqui las conexiones->oyentes
    // oyente con el servidor
    // map id, oyente -> con los demás clientes
    // oyente.mandar_mensaje_y_ALSERVIDOR();
    // oyente[i].mandar_mensaje_x(id cliente);

    private Usuario whoami;

    public Cliente(Usuario me) {
        whoami = me;
    }

    public String getId() {
        return whoami.getId_usuario();
    }

    public String getIP() {
        return whoami.getDireccion_ip();
    }

    public Fichero getFile(String name) {// mas de un proceso podr� acceder a varios ficheros(?)
        return whoami.getFile(name);
    }

    public ArrayList<String> getShared_info() {
        return whoami.getShared_info();
    }

    public void mensajeConexionServidor() {
        System.out.println("Conexi\u00f3n establecida con el servidor");
    }
}
