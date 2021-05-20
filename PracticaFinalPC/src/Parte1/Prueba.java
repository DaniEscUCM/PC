package Parte1;

import java.io.Serializable;

public class Prueba implements Serializable {
	private String data;
	
	public Prueba(String data) {
		this.data=data;
	}

	public String getData() {
		return data;
	}
}
