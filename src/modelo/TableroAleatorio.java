package modelo;

import java.util.ArrayList;

import utiles.Utiles;

public class TableroAleatorio extends Tablero {

	// Constructor aleatorio
	public TableroAleatorio(int lado, int minas) {
		super(lado);
		ArrayList<Coordenada> posiciones = generaAleatorio(minas, lado);
		disponerTablero(posiciones);
	}

	// constructor no aleatorio
	public TableroAleatorio(int lado, ArrayList<Coordenada> posiciones) {
		super(lado);
		disponerTablero(posiciones);
	}

	private void disponerTablero(ArrayList<Coordenada> posiciones) {
		colocarMinas(posiciones);
		contarMinasAlrededor(posiciones);
	}
	//Ahora vamos a sumarle 1 alrededor de la mina.
	public void contarMinasAlrededor(ArrayList<Coordenada> posiciones) {
		int longitud = 8;
		for (Coordenada coordenada : posiciones) {
			for (int i = 0; i < longitud; i++) {
				int[] vector = Utiles.damePosicionAlrededor(i);
				Coordenada miCordenada = new Coordenada(coordenada.getPosX()+vector[0], coordenada.getPosY()+vector[1]);
				if(enLimites(miCordenada) &&!getCasilla(miCordenada).isMina()) {
				getCasilla(miCordenada).incrementaUnaMinaAlrededor();
				}
			}
		}
	}
	public Casilla[][] getCasillasDesveladas() {
		Casilla resultados[][] = new Casilla[getAlto()][getAncho()];
		for (int i = 0; i < resultados.length; i++) {
			for (int j = 0; j < resultados[i].length; j++) {
				Casilla casilla = getCasilla(new Coordenada(i, j));
				if(!casilla.isVelada()) {
					resultados[i][j] = casilla;
				}
			}
		}
		return resultados;
	}

	//DESVELAR CONTIGUAS
	public boolean desvelarCasillas(Coordenada coordenada, boolean verificarMarcadas) {
		Casilla casilla = getCasilla(coordenada);
		boolean marcado = false;
		boolean continuarJuego = true;
		if(verificarMarcadas) {
			marcado = comprobarMarcadasAlrededor(coordenada);
		}
		int numMinasAlrededor = casilla.getMinasAlrededor();
		if((numMinasAlrededor == 0 || marcado) &&
				(!casilla.isMina()&&(casilla.isVelada()||marcado))&&
				!casilla.isMarcada()) {
			casilla.mostrarCasilla();
			int longitud = 8;
			for (int i = 0; i < longitud; i++) {
				int[] vector = Utiles.damePosicionAlrededor(i);
				Coordenada miCordenada = new Coordenada(coordenada.getPosX()+vector[0], coordenada.getPosY()+vector[1]);
				if(enLimites(miCordenada)&&continuarJuego){
					continuarJuego = desvelarCasillas(miCordenada, false);
				}
			}
		}
		else if(!casilla.isMarcada()){
			casilla.mostrarCasilla();
			if(casilla.isMina()) {
				continuarJuego = false;
			}
		}
		return continuarJuego;
	}
	
	

	private boolean comprobarMarcadasAlrededor(Coordenada coordenada) {
		int longitud = 8;
		int contador = 0;
		Casilla casilla = getCasilla(coordenada);
		int numeroMinasAlrededor = casilla.getMinasAlrededor();
		for (int i = 0; i < longitud; i++) {
			int[] vector = Utiles.damePosicionAlrededor(i);
			Coordenada miCordenada = new Coordenada(coordenada.getPosX()+vector[0], coordenada.getPosY()+vector[1]);
			if(enLimites(miCordenada)&&getCasilla(miCordenada).isMarcada()){
				contador++;
			}
		}
		return numeroMinasAlrededor==contador&&!casilla.isMina();
	}
	
	
	

	private void colocarMinas(ArrayList<Coordenada> posiciones) {
		for (Coordenada coordenada : posiciones) {
			ponerMina(coordenada);
		}
	}

	private void ponerMina(Coordenada coordenada) {
		getCasilla(coordenada).setMina(true);
	}

	public ArrayList<Coordenada> generaAleatorio(int minas, int longitud) {
		assert minas > 0 && longitud > 0;
		assert minas < longitud * longitud;
//		long inicio = System.currentTimeMillis();
		ArrayList<Coordenada> coordenadas = new ArrayList<Coordenada>();
		for (int i = 0; i < minas; i++) {
			Coordenada coord;
			do {
				coord = dameCoordenada(longitud);
			} while (existeCoord(coord, coordenadas));
			coordenadas.add(coord);
		}
//		 long fin = System.currentTimeMillis();
//		 System.out.println("en milis "+(fin-inicio));
		return coordenadas;

	}

	private Coordenada dameCoordenada(int lado) {
		return new Coordenada(Utiles.dameNumero(lado), Utiles.dameNumero(lado));
	}

	private boolean existeCoord(Coordenada coord, ArrayList<Coordenada> coordenadas) {
		for (int i = 0; i < coordenadas.size(); i++) {
			if (coord.equals(coordenadas.get(i))) {
				return true;
			}
		}
		return false;
	}

	public void desvelarTodasCasillas() {
		for (int i = 0; i < getAlto(); i++) {
			for (int j = 0; j < getAncho(); j++) {
				Casilla casilla = getCasilla(new Coordenada(i, j));
				casilla.mostrarCasilla();
			}
		}
	}

	public void marcarCasilla(Casilla casilla) {
		casilla.setMarcada(!casilla.isMarcada());
	}
}
