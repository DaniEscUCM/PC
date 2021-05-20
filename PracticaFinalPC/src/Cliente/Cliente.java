package Cliente;

public class Cliente {
    private Usuario whoami;

    public Cliente(Usuario me){
        whoami=me;
    }

    public String getId(){
        return whoami.getId_usuario();
    }

    public String getIP(){
        return whoami.getDireccion_ip();
    }
}
