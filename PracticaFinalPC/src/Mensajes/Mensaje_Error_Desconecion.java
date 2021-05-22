package Mensajes;

public class Mensaje_Error_Desconecion extends Mensaje {

	private static final long serialVersionUID = 6493460124103789630L;
	private static String tipo="Mensaje_Error_Desconecion";

	public Mensaje_Error_Desconecion(String origen, String destino) {
		super(tipo, origen, destino);
	}

	public Mensaje_Error_Desconecion() {
		super(tipo);
	}

}
