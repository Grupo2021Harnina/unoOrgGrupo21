package Presentador;
import modelo.Coordenada;

public class RespuestaDesvelo {

		public boolean veladas[][];

		public RespuestaDesvelo(int lado) {
			super();
		}
		public void setVeloPosicion(Coordenada coordenada,boolean velada) {
			veladas[coordenada.getPosX()][coordenada.getPosY()]=velada;
		}
		
		public boolean getVeloPosicion(Coordenada coordenada) {
			return veladas[coordenada.getPosX()][coordenada.getPosY()];
		}
}
