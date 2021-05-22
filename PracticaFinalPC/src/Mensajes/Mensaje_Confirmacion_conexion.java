package Mensajes;

public class Mensaje_Confirmacion_conexion extends Mensaje {

	private static final long serialVersionUID = 2687694972485558145L;
	private static String tipo = "mensaje_confirmacion_conexion";

    public Mensaje_Confirmacion_conexion(String origen, String destino) {
        super(tipo, origen, destino);
    }
}
