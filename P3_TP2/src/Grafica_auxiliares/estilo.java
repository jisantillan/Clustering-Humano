package Grafica_auxiliares;

import java.awt.Toolkit;

import javax.swing.JFrame;

import Grafica.Presentacion;

public class estilo {
	public static void ventanaCentrada(JFrame frame, int ancho, int alto) {
		setTitle(frame);
		setIcono(frame);
		frame.setResizable(false);
		frame.setBounds(0,0, ancho, alto);
		frame.setLocationRelativeTo(null); //ubicar la ventana en el centro
	}
	
	private static void setTitle(JFrame frame) {
		frame.setTitle("Clustering Humano");
	}
	
	private static void setIcono(JFrame frame) {
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Presentacion.class.getResource("/Imagenes/ch_icon.png")));
	}	
	
}
