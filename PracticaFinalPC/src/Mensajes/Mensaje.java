package Mensajes;

import Servidor.Servidor;

/*
   * Sirve como raız de la jerarquıa de mensajes que debemos dise�ar. Tiene como
   * atributos al tipo, origen y destino del mensaje en cuestion;
   */
public abstract class Mensaje {
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
    
    public abstract boolean execute(Servidor s);

}