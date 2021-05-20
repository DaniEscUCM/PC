
/*
   * Sirve como raız de la jerarquıa de mensajes que debemos diseñar. Tiene como
   * atributos al tipo, origen y destino del mensaje en cuestion;
   */
public abstract class Mensaje {

    private String tipo;
    private final String help;
    private String origen;
    private String destino;

    protected static final String incorrectArgsMsg = "Incorrect argument format";

    public Mensaje(String tipo, String help, String origen, String destino) {
        this.tipo_de_mensaje = tipo;
        this.help = help;
        this.origen = origen;
        this.destino = destino;
    }

    public String getDestino() {
        return this.destino;
    }

    public String getOrigen() {
        return this.origen;
    }

    public String getTipo() {
        return this.tipo;
    }

    public String helpText() {
        return "ayuda: " + help + "\n";
    }

    protected boolean matchMensajeName(String name) {
        return this.tipo.equalsIgnoreCase(name);
    }

}