package Mensajes;

public class Mensaje_error_usuario_existente extends Mensaje {

	private static final long serialVersionUID = 6370010097396339021L;
	private static String tipo = "mensaje_error_usuario_existente";

    public Mensaje_error_usuario_existente(String origen, String destino) {
        super(tipo, origen, destino);
    }

}
