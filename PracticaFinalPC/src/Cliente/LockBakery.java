package Cliente;

import java.io.Serializable;

public class LockBakery implements Serializable{

	private IntReference[] turn;

	private int n;

	public LockBakery(int n) {
		this.n = n;
		turn = new IntReference[n];
		for (int i = 0; i < n; i++) {
			turn[i] = new IntReference(-1);
		}
	}

	int max(IntReference[] turn) {
		int max = -1;
		for (int i = 0; i < turn.length; i++) {
			max = (turn[i].n > max) ? turn[i].n : max;
		}
		return max;
	}

	public void takeLock(int i) {
		turn[i].n = -1;
		turn[i].n = max(turn) + 1;
		for (int j = 0; j < n; j++) {
			if (j != i) {
				while (mayor(i, j) && turn[j].n != -1)
					;
			}
		}
	}

	boolean mayor(int a, int b) {
		return (turn[a].n > turn[b].n || (turn[a] == turn[b] && a > b));
	}

	public void releaseLock(int i) {
		turn[i].n = -1;
	}

}
