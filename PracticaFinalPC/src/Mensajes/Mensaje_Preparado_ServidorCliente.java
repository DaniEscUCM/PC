package Mensajes;

public class Mensaje_Preparado_ServidorCliente extends Mensaje {

    static private String tipo = "Mensaje_Preparado_ClienteServidor";
    private String IP;
    private Lock cerrojo;
    private int puerto;

    public Mensaje_Preparado_ServidorCliente() {
        super(tipo);
    }

    public Mensaje_Preparado_ServidorCliente(String origen, String destino, String ip, int puerto, Lock l) {
        super(tipo, origen, destino);
        this.IP = ip;
        this.puerto = puerto;
        this.cerrojo = l;
    }
}
