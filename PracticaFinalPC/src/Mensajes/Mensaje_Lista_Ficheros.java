package Mensajes;

import java.util.ArrayList;

public class Mensaje_Lista_Ficheros extends Mensaje {

  
	private static final long serialVersionUID = 3134145038370843606L;
	static private String tipo = "Mensaje_Lista_Ficheros";
    private ArrayList<String> lista_ficheros;

    public Mensaje_Lista_Ficheros() {
        super(tipo);
    }

    public Mensaje_Lista_Ficheros(String origen, String destino, ArrayList<String> lista_ficheros) {
        super(tipo, origen, destino);
        this.lista_ficheros = lista_ficheros;
    }

    public ArrayList<String> getLista_ficheros() {
        return this.lista_ficheros;
    }

}