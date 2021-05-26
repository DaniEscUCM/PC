package Mensajes;

public class Mensaje_Lista_Ficheros extends Mensaje {

    static private String tipo = "Mensaje_Lista_Ficheros";
    private String nombre_fichero;

    public Mensaje_Lista_Ficheros() {
        super(tipo);
    }

    public Mensaje_Lista_Ficheros(String origen, String destino, String nombre_fichero) {
        super(tipo, origen, destino);
        this.nombre_fichero = nombre_fichero;
    }

    public String getNombreFichero() {
        return this.nombre_fichero;
    }

}