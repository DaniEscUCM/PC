package Mensajes;

public class Mensaje_Lista_Usuarios extends Mensaje {

	private static final long serialVersionUID = 8967104155744953826L;
	static private String tipo = "Mensaje_Lista_Usuarios";

    public Mensaje_Lista_Usuarios() {
        super(tipo);
    }

    public Mensaje_Lista_Usuarios(String origen, String destino) {
        super(tipo, origen, destino);
    }

}