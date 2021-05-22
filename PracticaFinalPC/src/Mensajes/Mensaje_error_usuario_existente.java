package Mensajes;

public class Mensaje_error_usuario_existente extends Mensaje {

    String tipo = "mensaje_error_usuario_existente";

    public Mensaje_error_usuario_existente(String origen, String destino) {
        super(tipo, origen, destino);
    }

}
