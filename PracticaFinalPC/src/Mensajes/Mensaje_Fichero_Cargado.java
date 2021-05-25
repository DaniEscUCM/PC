package Mensajes;

public class Mensaje_Fichero_Cargado extends Mensaje {
	
	private static final long serialVersionUID = -76248070589070515L;
	private static String tipo="Mensaje_Fichero_Cargado";

	private String file;
	

	public Mensaje_Fichero_Cargado( String origen, String destino, String file) {
		super(tipo, origen, destino);
		this.file = file;
	}

	public Mensaje_Fichero_Cargado() {
		super(tipo);

	}

	public String getFile() {
		return file;
	}

		
}
