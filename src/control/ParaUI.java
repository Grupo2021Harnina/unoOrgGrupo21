package control;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import modelo.Casilla;
import modelo.Coordenada;
import modelo.Densidad;
import modelo.Dificultad;
import modelo.TableroAleatorio;
import vista.UI;

public class ParaUI extends UI {

	private Controlador controlador;

	public ParaUI() {
		controlador = new Controlador();
		this.iniciarElementos();
		// leyes de demeter
		// para solucionar esto es crear metodos delegados
//		jPanelOpciones.btnIniciar.addActionListener(l);

	}

	private void iniciarElementos() {
		getBtnIniciar().addActionListener(actionListenerIniciar);
		
	}

	private void iniciarBotonera() {
		super.crearBotonera();
		Component[] boton = this.botonera.getComponents();
		for (Component component : boton) {
			//((JButton) component).addActionListener(actionListenerBotonera);
			((JButton) component).addMouseListener(mouseListener);
		}

	};
	
	MouseAdapter mouseListener = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			JButton boton = (JButton) e.getSource();
			Coordenada coordenada2 = botonera.getCoordenada(boton);
			if(e.getButton()==3) {
				controlador.marcarCasilla(coordenada2);
				marcarBotonera(coordenada2);
			}else if(e.getButton()==1){
				controlador.desvelarCasilla(coordenada2);
				actualizarBotonera();
				if(controlador.comprobarGanador()) {
					JOptionPane.showMessageDialog(null, "has ganado");
				}
			}
			
		}
	};
	
	private void marcarBotonera(Coordenada coordenada2) {
		JButton button = botonera.getButton(coordenada2);
		if(Color.RED.equals(button.getBackground())){
			button.setBackground(Color.DARK_GRAY);
		}else {
			button.setBackground(Color.RED);
		}
	}

	private void actualizarBotonera() {
		Casilla[][] CasillasDesveladas = controlador.getCasillasDesveladas();
		for (int i = 0; i < CasillasDesveladas.length; i++) {
			for (int j = 0; j < CasillasDesveladas[i].length; j++) {
				Casilla casilla = CasillasDesveladas[i][j];
				if (casilla != null) {
					JButton boton = this.botonera.getBoton(i, j);
				if (casilla.isMina()) {
					boton.setBackground(Color.BLACK);
					
					
				}else {
					if(casilla.getMinasAlrededor()==1) {
						boton.setForeground(Color.BLUE);
					}else if(casilla.getMinasAlrededor()==3) {
						boton.setForeground(Color.RED);
					}
					if(casilla.getMinasAlrededor()==2) {
						boton.setForeground(Color.GREEN);
					}
					boton.setBackground(Color.WHITE);
					boton.setText(String.valueOf(casilla.getMinasAlrededor()));
				}
				}
			}
		}
	}

	

	ActionListener actionListenerBotonera = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton boton = (JButton) e.getSource();
			Coordenada coordenada2 = botonera.getCoordenada(boton);
			controlador.desvelarCasilla(coordenada2);
			actualizarBotonera();
		}

	};
		
	//Iniciamos la botonera con los valores marcados.
	ActionListener actionListenerIniciar = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			Densidad densidad = (Densidad) getCmbDensidad().getSelectedItem();
			Dificultad dificultad = (Dificultad) getCmbDificultad().getSelectedItem();
			iniciarBotonera();
			controlador.iniciarJuego(densidad, dificultad);
		}
	};

}
