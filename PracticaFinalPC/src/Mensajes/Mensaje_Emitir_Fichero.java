package Mensajes;

public class Mensaje_Emitir_Fichero extends Mensaje {
    private static final long serialVersionUID = -3218454228349802506L;
    static private String tipo = "Mensaje_Emitir_Fichero";
    private String nombre_fichero;

    public Mensaje_Emitir_Fichero() {
        super(tipo);
    }

    public Mensaje_Emitir_Fichero(String origen, String destino, String nombre_fichero) {
        super(tipo, origen, destino);
        this.nombre_fichero = nombre_fichero;
    }

    public String getNombreFichero() {
        return this.nombre_fichero;
    }
}
