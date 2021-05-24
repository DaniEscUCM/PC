package Mensajes;

public class Mensaje_Error_Fichero extends Mensaje {

    private static final long serialVersionUID = 6493460124103789630L;
    private static String tipo = "Mensaje_Error_Fichero";

    public Mensaje_Error_Fichero(String origen, String destino) {
        super(tipo, origen, destino);
    }

    public Mensaje_Error_Fichero() {
        super(tipo);
    }
}
