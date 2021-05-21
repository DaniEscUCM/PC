package Cliente;

import Mensajes.Fichero;

/**
 * 
 * @author Daniela Escobar & Alessandro de Armas
 * 
 * Clase cliente que guarda al usuario, sus conexiones y realiza las acciones 
 * que tienen que ver con lo que solicita acceder otro cliente y su usuario.
 *
 */
public class Cliente {
	
    private Usuario whoami;

    public Cliente(Usuario me){
        whoami = me;
    }

    public String getId(){
        return whoami.getId_usuario();
    }

    public String getIP(){
        return whoami.getDireccion_ip();
    }
    
    public Fichero getFile(String name) {//mas de un proceso podrá acceder a varios ficheros(?)
    	return whoami.getFile(name);
    }
}
