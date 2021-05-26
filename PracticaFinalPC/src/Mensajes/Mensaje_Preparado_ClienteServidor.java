package Mensajes;

import Cliente.LockBakery;

public class Mensaje_Preparado_ClienteServidor extends Mensaje {

    private static final long serialVersionUID = -1420503469254360487L;
    static private String tipo = "Mensaje_Preparado_ClienteServidor";
    private String IP;
    private LockBakery cerrojo;
    private int puerto;
    private String destinoFinal;

    public Mensaje_Preparado_ClienteServidor() {
        super(tipo);
    }

    public Mensaje_Preparado_ClienteServidor(String origen, String destino, String ip, int puerto, String destinoFinal,
            LockBakery l) {
        super(tipo, origen, destino);
        this.IP = ip;
        this.puerto = puerto;
        this.cerrojo = l;
        this.destinoFinal = destinoFinal;
    }

    /**
     * @return the iP
     */
    public String getIP() {
        return IP;
    }

    /**
     * @return the puerto
     */
    public int getPuerto() {
        return puerto;
    }

    public LockBakery getCerrojo() {
        return cerrojo;
    }

    /**
     * @return the destinoFinal
     */
    public String getDestinoFinal() {
        return destinoFinal;
    }
}