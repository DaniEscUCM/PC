package Mensajes;

public class Mensaje_Confirmacion_conexion extends Mensaje {

    private static String tipo = "mensaje_confirmacion_conexion";

    public Mensaje_Confirmacion_conexion(String origen, String destino) {
        super(tipo, origen, destino);
    }
}
