package Mensajes;


public class Mensaje_Preparado_ClienteServidor extends Mensaje {

	private static final long serialVersionUID = -1420503469254360487L;
	static private String tipo = "Mensaje_Preparado_ClienteServidor";

    public Mensaje_Preparado_ClienteServidor() {
        super(tipo);
    }

    public Mensaje_Preparado_ClienteServidor(String origen, String destino) {
        super(tipo, origen, destino);
    }

}