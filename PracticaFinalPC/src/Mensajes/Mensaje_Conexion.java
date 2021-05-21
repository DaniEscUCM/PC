package Mensajes;

import Servidor.Servidor;

public class Mensaje_Conexion extends Mensaje {

    static private String tipo = "Mensaje_Conexion";
    private String origen;
    private String destino;

    public Mensaje_Conexion() {
    	super(tipo);
    }
    
    public Mensaje_Conexion(String origen,String destino) {
        super(tipo, origen, destino);
        this.origen = origen;
        this.destino = destino;
    }

    public boolean execute(Servidor servidor) {
        servidor.establecerConexion(this.origen,this.destino);
        return false;
    }

	
}