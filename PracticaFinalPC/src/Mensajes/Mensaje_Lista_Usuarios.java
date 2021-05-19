package Mensajes;

public class Mensaje_Lista_Usuarios extends Mensaje {

    private String tipo = "Mensaje_Lista_Usuarios";
    private String help = "devuelve la lista de usuarios";

    private String origen;
    private String destino;

    public Mensaje_Lista_Usuarios() {
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