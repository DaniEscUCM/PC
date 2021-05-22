package Mensajes;


public class Mensaje_Pedir_Fichero extends Mensaje {

	private static final long serialVersionUID = -3218454228349802506L;
	static private String tipo = "Mensaje_Pedir_Fichero";
	private String nombre_fichero;

    public Mensaje_Pedir_Fichero() {
        super(tipo);
    }

    public Mensaje_Pedir_Fichero(String origen, String destino,String nombre_fichero) {
        super(tipo, origen, destino);
        this.nombre_fichero=nombre_fichero;
    }
    
    public String getNombreFichero() {
    	return this.nombre_fichero;
    }

}