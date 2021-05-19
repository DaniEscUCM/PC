package Mensajes;

import java.io.Serializable;

public class Fichero implements Serializable {

    private String name;
    private String data;

    public Fichero(String name, String data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public String getData() {
        return data;
    }

    public void setData(String datos) {
        this.data = datos;
    }
}
