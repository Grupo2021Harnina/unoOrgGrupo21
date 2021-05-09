package modelo;

import javax.swing.JOptionPane;

public class GestionDatos {
	private TableroAleatorio tablero;
	private int numeroMinas;
	private int numeroVeladas;

	public void crearTablero(int longitud, int porcentaje) {
		this.numeroMinas = calcularNumeroMinas(porcentaje, longitud);
		this.tablero = new TableroAleatorio(longitud, numeroMinas);
	}

	private int calcularNumeroMinas(int porcentaje, int longitud) {
		int numeroCasillas = longitud * longitud;
		return numeroMinas = numeroCasillas * porcentaje / 100;
	}

	public void comprobarCasilla(Coordenada coordenada) {
		if (!tablero.desvelarCasillas(coordenada, true)) {
			tablero.desvelarTodasCasillas();
			JOptionPane.showMessageDialog(null, "Has perdido, pulsaste una mina!!");
		} 
	}

	public Casilla[][] getCasillasDesveladas() {
		Casilla[][] casillas  = tablero.getCasillasDesveladas();
		contarDesveladas(casillas);
		return casillas;
	}

	public boolean comprobarFinJuego() {
		return numeroMinas==numeroVeladas;
	}
	
	
	private void contarDesveladas(Casilla[][] casillas) {
		numeroVeladas = 0;
		for (int i = 0; i < casillas.length; i++) {
			for (int j = 0; j < casillas[i].length; j++) {
				if(null==casillas[i][j]) {
					numeroVeladas++;
				}
			}
		}
		
	}

	public void marcarCasilla(Coordenada coordenada2) {
		tablero.marcarCasilla(tablero.getCasilla(coordenada2));
	}
	

}
