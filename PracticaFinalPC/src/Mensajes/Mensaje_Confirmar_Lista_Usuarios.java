package Mensajes;

import java.util.ArrayList;

public class Mensaje_Confirmar_Lista_Usuarios extends Mensaje {

    private static final long serialVersionUID = 6493460124103789630L;
    private static String tipo = "Mensaje_Confirmar_Lista_Usuarios";
    ArrayList<String> lista;

    public Mensaje_Confirmar_Lista_Usuarios(String origen, String destino, ArrayList<String>  lista) {
        super(tipo, origen, destino);
        this.lista = lista;
    }

    public Mensaje_Confirmar_Lista_Usuarios() {
        super(tipo);
    }

    public ArrayList<String>  getLista() {
        return this.lista;
    }

}
