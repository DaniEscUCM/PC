package Mensajes;

public class Mensaje_Preparado_ServidorCliente extends Mensaje {

	private static final long serialVersionUID = -3627360888472452553L;
	static private String tipo = "Mensaje_Preparado_ServidorCliente";
    private String IP;
    //private LockBakery cerrojo;
    private int puerto;
    private String destinoFinal;

    public Mensaje_Preparado_ServidorCliente() {
        super(tipo);
    }

    public Mensaje_Preparado_ServidorCliente(String origen, String destino, String ip, int puerto, String destinoFinal) {
        super(tipo, origen, destino);
        this.IP = ip;
        this.puerto = puerto;
        //this.cerrojo = l;
        this.destinoFinal = destinoFinal;
    }

    @Override
    public String getDestino() {
        return super.getDestino();
    }

    @Override
    public String getTipo() {
        return super.getTipo();
    }
/*
    public LockBakery getCerrojo() {
        return cerrojo;
    }*/

    public int getPuerto() {
        return puerto;
    }

    public String getIp() {
        return this.IP;
    }

    public String getDestinoFinal() {
        return destinoFinal;
    }

}
