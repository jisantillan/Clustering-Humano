package Grafica_auxiliares;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

public class Elementos {
	private static Font fuente;	
/*  TextField*/	
	public static JTextField crearTextField() {
		JTextField textField = new JTextField();
		return textField;
	}
	
/*  Labels  */ 	
	public static JLabel crearLabel(String text) {
		JLabel lbl = new JLabel(text);
		lbl.setFont(getFuente());
		return lbl;
	}
	public static JLabel crearLabel_Image(ImageIcon I) {
		JLabel lbl = new JLabel();
		lbl.setIcon(I);
		return lbl;
	}
	
/*  Checks  */ 
	public static void crearRadioButtons(JPanel panel, int[] valores, ButtonGroup grupo) {
		for (int valor : valores) {
			JRadioButton rdbtn = new JRadioButton(valor+"");
			panel.add(rdbtn);
			grupo.add(rdbtn);
		}
	}
	
/*  BOTONES  */ 
	
	public static JButton crearBoton(String text, ActionListener action) {
		JButton btn = new JButton(text);
		btn.setLocation(0,0);
		btn.addActionListener(action);
		btn.setFocusable(false);
		btn.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(128, 128, 128)));
		btn.setBackground(new Color(60, 179, 113));
		btn.setFont(getFuente());
		return btn;
	}
	
	public static JButton crearBoton_salir(JFrame frame) {
		return crearBoton("Salir", accionCerrarVentana(frame));
	}
		
/*  Separadores  */ 
	public static JSeparator crearSeparador() {
		JSeparator separador = new JSeparator();
		return separador;
	}
	
/*  Colocamiento en GridBagLayout */
	public static void gridBagConstraints(JFrame frame, Component componente, int gridColumna, int gridFila, int gridAncho, int gridAlto, int displayArea) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = displayArea;
		gbc.gridheight = gridAlto;
		gbc.gridwidth = gridAncho;
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.gridx = gridColumna;
		gbc.gridy = gridFila;
		frame.getContentPane().add(componente, gbc);
	}
	public static void gridBagConstraints_Horizaontal(JFrame frame, Component componente,int gridColumna, int gridFila, int gridAncho, int gridAlto) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = gridAncho;
		gbc.gridheight = gridAlto;
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.gridx = gridColumna;
		gbc.gridy =  gridFila;
		frame.getContentPane().add(componente, gbc);
	}

	public static void gridBagConstraints_both(JFrame frame, Component componente, int gridColumna, int gridFila, int gridAncho, int gridAlto) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = gridAncho;
		gbc.gridheight = gridAlto;
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = gridColumna;
		gbc.gridy = gridFila;
		frame.getContentPane().add(componente, gbc);
	}
	
/*  Fuente  */
	public static Font getFuente() {
		return fuente;
	}
	public static void setFuente(Font fuente) {
		Elementos.fuente = fuente;
	}
	
/*  Acciones  */	
	private static ActionListener accionCerrarVentana(JFrame frame) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		};
	}
	
}
