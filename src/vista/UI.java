package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Densidad;
import modelo.Dificultad;

import javax.swing.JLabel;

public class UI extends JFrame {
	
	private JPanel contentPane;
	public JPanelOpciones jPanelOpciones;
	public Botonera botonera;

	
	/**
	 * Create the frame.
	 */
	public UI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		jPanelOpciones=new JPanelOpciones();
		contentPane.add(jPanelOpciones, BorderLayout.SOUTH);
		
		
		
	}

	public void tomaValores(Densidad densidad, Dificultad dificultad) {
		System.out.println(densidad+" "+dificultad);
		
	}

	public JButton getBtnIniciar() {
		return jPanelOpciones.getBtnIniciar();
	}

	public JComboBox getCmbDificultad() {
		return jPanelOpciones.getCmbDificultad();
	}

	public JComboBox getCmbDensidad() {
		return jPanelOpciones.getCmbDensidad();
	}

	public void crearBotonera() {
		if (botonera!=null) {
			contentPane.remove(botonera);
		}
		Dificultad dificultad = (Dificultad) getCmbDificultad().getSelectedItem();
		botonera = new Botonera(dificultad.getLongitud());
		contentPane.add(botonera, BorderLayout.CENTER);
		botonera.setVisible(true);
		//Se encarga de refrescar la botonera al cambiarle la dificultad
		contentPane.revalidate();
		
	}

//	public void crearBotonera(int tamano) {
//		 botonera = new Botonera(tamano);
//	     contentPane.add(botonera, BorderLayout.CENTER);
//	     botonera.setVisible(false);
//	}
}
