package Mensajes;

import Servidor.Servidor;

public class Mensaje_Preparado_ClienteServidor extends Mensaje {

    static private String tipo = "Mensaje_Preparado_ClienteServidor";
    private String origen;
    private String destino;

    public Mensaje_Preparado_ClienteServidor() {
        super(tipo);
    }

    public Mensaje_Preparado_ClienteServidor(String origen, String destino) {
        super(tipo, origen, destino);
        this.origen = origen;
        this.destino = destino;
    }

}