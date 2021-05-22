package Mensajes;

import java.io.Serializable;

/*
   * Sirve como raız de la jerarquıa de mensajes que debemos dise�ar. Tiene como
   * atributos al tipo, origen y destino del mensaje en cuestion;
   */
public abstract class Mensaje implements Serializable {
	private static final long serialVersionUID = -9190527409302867699L;
	private String tipo;
    private String origen;
    private String destino;

    public Mensaje(String tipo, String origen, String destino) {
        this.tipo = tipo;
        this.origen = origen;
        this.destino = destino;
    }

    public Mensaje(String tipo) {
        this.tipo = tipo;
    }

    public String getDestino() {
        return this.destino;
    }

    public String getOrigen() {
        return this.origen;
    }

    public String getTipo() {
        return this.tipo;
    }

}