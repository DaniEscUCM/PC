package Mensajes;

public class Mensaje_Cerrar_Conexion extends Mensaje {

	private static final long serialVersionUID = -4198693176165787402L;
	static private String tipo = "Mensaje_Cerrar_Conexion";

    public Mensaje_Cerrar_Conexion() {
        super(tipo);
    }

    public Mensaje_Cerrar_Conexion(String origen, String destino) {
        super(tipo, origen, destino);
    }

}