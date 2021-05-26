package Cliente;

import java.io.Serializable;

/**
 * 
 * @author Daniela Escobar & Alessandro de Armas
 *
 */

public class IntReference implements Serializable {
	
	private static final long serialVersionUID = 657117479759359532L;
	
	public volatile int n;

	IntReference() {
		n = 0;
	}

	IntReference(int _n) {
		n = _n;
	}
}
