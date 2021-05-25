package Mensajes;

public class Mensaje_Preparado_ClienteServidor extends Mensaje {

    private static final long serialVersionUID = -1420503469254360487L;
    static private String tipo = "Mensaje_Preparado_ClienteServidor";
    private String IP;
    private Lock cerrojo;
    private int puerto;

    public Mensaje_Preparado_ClienteServidor() {
        super(tipo);
    }

    public Mensaje_Preparado_ClienteServidor(String origen, String destino, String ip, int puerto, Lock l) {
        super(tipo, origen, destino);
        this.IP = ip;
        this.puerto = puerto;
        this.cerrojo = l;
    }

}