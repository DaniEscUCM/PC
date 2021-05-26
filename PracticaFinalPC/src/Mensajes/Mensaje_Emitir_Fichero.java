package Mensajes;

public class Mensaje_Emitir_Fichero extends Mensaje {
    private static final long serialVersionUID = -3218454228349802506L;
    static private String tipo = "Mensaje_Emitir_Fichero";
    private String nombre_fichero;
    private String emisor;
    private int puerto;

    public Mensaje_Emitir_Fichero(String origen, String owner, String nombre_fichero, String emisor, int puerto) {
        super(tipo, origen, owner);
        this.nombre_fichero = nombre_fichero;
        this.emisor = emisor;
        this.puerto=puerto;
    }

    public String getNombreFichero() {
        return this.nombre_fichero;
    }

    public String getEmisor() {
        return this.emisor;
    }

	public int getPuerto() {
		return puerto;
	}
    
    
}
