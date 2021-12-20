package Grafica;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import Grafica_auxiliares.Elementos;
import Grafica_auxiliares.estilo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Presentacion {

	private JFrame frame;
	private GridBagLayout GridLayout;

	/**
	 * Create the application.
	 */
	public Presentacion() {
		initialize();
	}

	public void setVisible(boolean booleano) {
		frame.setVisible(booleano);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		estilo.ventanaCentrada(frame, 450, 350);
		
		GridLayout = new GridBagLayout();
		GridLayout.rowHeights = new int[] {50, 50, 50, 50, 50, 0, 50, 50};
		GridLayout.columnWidths = new int[] {65, 64, 64, 64, 64, 64, 65};
		GridLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		GridLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		frame.getContentPane().setLayout(GridLayout);
		
		Elementos.setFuente(new Font("Calibri", Font.BOLD, 12));
		crearImagenPrincipal();
		crearSeparador();
		crearBotonSalir();
		crearBotonIniciar();
	}
	
	private void crearImagenPrincipal() {
		JLabel lbl = Elementos.crearLabel_Image(new ImageIcon(Presentacion.class.getResource("/Imagenes/clusteringhumano1.png")));
		Elementos.gridBagConstraints(frame, lbl, 1, 1, 5, 4, GridBagConstraints.CENTER );
	}
	private void crearSeparador() {
		
		Elementos.gridBagConstraints_both(frame,Elementos.crearSeparador(), 0, 5, 7, 1);
	}	
	private void crearBotonSalir() {
		JButton boton = Elementos.crearBoton_salir(frame);
		Elementos.gridBagConstraints_both(frame, boton, 1, 6, 2, 1);
	}
	private void crearBotonIniciar() {
		JButton boton = Elementos.crearBoton("Iniciar", accionIniciar());
		Elementos.gridBagConstraints_both(frame, boton, 4, 6, 2, 1);
	}
	
	private ActionListener accionIniciar() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Form_ingreso ventana = new Form_ingreso();
				ventana.setVisible();
				frame.dispose();
			}
		};
	}

}
