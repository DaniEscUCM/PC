package Mensajes;

import Servidor.Servidor;

public class Mensaje_Lista_Usuarios extends Mensaje {

    static private String tipo = "Mensaje_Lista_Usuarios";
    private String origen;
    private String destino;

    public Mensaje_Lista_Usuarios() {
        super(tipo);
    }

    public Mensaje_Lista_Usuarios(String origen, String destino) {
        super(tipo, origen, destino);
        this.origen = origen;
        this.destino = destino;
    }

}