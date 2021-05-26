package Mensajes;

public class Mensaje_Confirmar_Desconexion extends Mensaje {
	
	private static final long serialVersionUID = -76248070589070515L;
	private static String tipo="Mensaje_Confirmar_Desconexion";

	public Mensaje_Confirmar_Desconexion( String origen, String destino) {
		super(tipo, origen, destino);
	}

	public Mensaje_Confirmar_Desconexion() {
		super(tipo);
	}

}
