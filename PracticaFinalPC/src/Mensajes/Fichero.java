package Mensajes;

import java.io.Serializable;
import java.util.Scanner;

/**
 * 
 * @author Daniela Escobar & Alessandro de Armas
 * 
 * Fichero guarda el nombre del fichero txt y los datos que contiene.
 *
 */

public class Fichero implements Serializable {

	private static final long serialVersionUID = -4199941526146432733L;
	private String name;
    private String data="";
    
    public Fichero(String name){
    	Scanner in=new Scanner(name);    	
    	while(in.hasNextLine()) {
    		String info=in.nextLine()+System.lineSeparator();
    		data+=info;
    	}
    	in.close();
    	
    }
    
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
