package Mensajes;

public class Mensaje_Cerrar_Conexion extends Mensaje {

    private String tipo = "Mensaje_Cerrar_Conexion";
    private String help = "cierra la conexion";

    private String origen;
    private String destino;

    public Mensaje_Cerrar_Conexion() {
        super(tipo, help, origen, destino);
    }

    public Mensaje_Cerrar_Conexion(String origen, String destino) {
        super(tipo, help, origen, destino);
    }

    public boolean execute(Servidor servidor) {
        servidor.cerrarConexion(origen,destino);
        return false;
    }

    public Command parse(String[] commandWords) {
        if (matchMensajeName(commandWords[0]))
            return new Mensaje_Cerrar_Conexion();
        return null;
    }

}