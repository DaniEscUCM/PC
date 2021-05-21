package Mensajes;

import Servidor.Servidor;

public class Mensaje_Pedir_Fichero extends Mensaje {

	static private String tipo = "Mensaje_Pedir_Fichero";
    private String origen;
    private String destino;

    public Mensaje_Pedir_Fichero() {
        super(tipo);
    }

    public Mensaje_Pedir_Fichero(String origen, String destino) {
        super(tipo,origen, destino);
        this.origen = origen;
        this.destino= destino;
    }

    public boolean execute(Servidor servidor) {
        servidor.pedir_fichero(this.origen,this.destino);
        return false;
    }
    
}