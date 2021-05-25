package Cliente;

public class IntReference {
	public volatile int n;

	IntReference() {
		n = 0;
	}

	IntReference(int _n) {
		n = _n;
	}
}
