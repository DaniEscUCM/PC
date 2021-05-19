package Mensajes;

public class Mensaje_Conexion extends Mensaje {

    private String tipo = "Mensaje_Conexion";
    private String help = "abre la conexion";

    private String origen;
    private String destino;

    public Mensaje_Conexion() {
        super(tipo, help, origen, destino);
    }

    @Override
    public String getDestino() {
        return this.destino;
    }

    @Override
    public String getOrigen() {
        return this.tipo_de_mensaje;
    }

    @Override
    public int getTipo() {
        return this.tipo;
    }
}