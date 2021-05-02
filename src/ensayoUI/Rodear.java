package ensayoUI;

import utiles.Utiles;

public class Rodear {
	public static void main(String[] args) {
		int x = 0, y = 4;
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (!(x==i&&y==j)) {
//					System.out.println(i + ":" + j);
					}
			}
		}
		for (int k = 0; k < 8; k++) {
			int[] alrededor = Utiles.damePosicionAlrededor(k);
			System.out.println((x+alrededor[0])+":"+(y+alrededor[1]));
		}
	}
}
