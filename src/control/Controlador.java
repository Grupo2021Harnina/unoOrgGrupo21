package control;

import javax.swing.JOptionPane;

import modelo.Casilla;
import modelo.Coordenada;
import modelo.Densidad;
import modelo.Dificultad;
import modelo.GestionDatos;
import modelo.TableroAleatorio;

public class Controlador {
	private GestionDatos gestion;

	public Controlador() {
		gestion = new GestionDatos();
	}

	public void iniciarJuego(Densidad densidad, Dificultad dificultad) {
		gestion.crearTablero(dificultad.getLongitud(), densidad.getPorcentaje());

	}

	public Casilla[][] getCasillasDesveladas() {
		return gestion.getCasillasDesveladas();
	}

	public void desvelarCasilla(Coordenada coordenada) {
		gestion.comprobarCasilla(coordenada);
	}

	public void marcarCasilla(Coordenada coordenada2) {
		gestion.marcarCasilla(coordenada2);
		
	}
	
	public boolean comprobarGanador() {
		return gestion.comprobarFinJuego();
	}

	
}
