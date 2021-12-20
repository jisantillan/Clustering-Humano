package Grafica;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import Grafica_auxiliares.Elementos;
import Grafica_auxiliares.estilo;
import Logica.ClusteringHumano;
import Logica.Persona;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Enumeration;

public class Form_ingreso {

	JFrame frame;
	private GridBagLayout GridLayout;
	private JTextField textField_nombre;
	private JTextField textField_apellido;
	private int[] valoresRadioButton = {1,2,3,4,5};
	private ButtonGroup bgNoticias = new ButtonGroup();
	private ButtonGroup bgDeportes = new ButtonGroup();
	private ButtonGroup bgCiencias = new ButtonGroup();
	private ButtonGroup bgMusica   = new ButtonGroup();
	private ClusteringHumano cluster = new ClusteringHumano();
	

	/**
	 * Create the application.
	 */
	public Form_ingreso() {
		initialize();
	}

	public void setVisible() {
		frame.setVisible(true);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		estilo.ventanaCentrada(frame, 450, 350);
		
		GridLayout = new GridBagLayout();
		GridLayout.columnWidths = new int[] {30, 70, 110, 30, 70, 110, 30};
		GridLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 1.0, 1.0, 0.0};
		GridLayout.rowWeights = new double[]{0.0};
		frame.getContentPane().setLayout(GridLayout);
		
		Elementos.setFuente(new Font("Calibri", Font.BOLD, 11));
		
		ingresoNombre();
		msjConsigna();
		ingresoIntereses();
		botonIngresarPersona();
		botonDividir();
		botonSalir();	
	}

	private void botonIngresarPersona() {
		JButton boton = Elementos.crearBoton("Ingresar Persona", boton_AccionIngresar());
		Elementos.gridBagConstraints_both(frame, boton, 1, 8, 2, 1);
		
	}
	private void botonDividir() {
		JButton boton = Elementos.crearBoton("Dividir grupo", boton_AccionDividir());
		Elementos.gridBagConstraints_both(frame, boton, 4, 8, 2, 1);
	}
	private void botonSalir() {
		JButton boton = Elementos.crearBoton_salir(frame);
		Elementos.gridBagConstraints_both(frame, boton, 1, 9, 5, 1);
	}
	private void crearRadioButtons(JPanel panel, int[] valores, ButtonGroup grupoBotones) {
		Elementos.crearRadioButtons(panel, valores, grupoBotones);
	}
	private void crearLabel(String msj, int gridx, int gridy, int gridwidth) {
		Elementos.gridBagConstraints_Horizaontal(frame, Elementos.crearLabel(msj), gridx, gridy, gridwidth, 1);
	}
	
	private void ingresoNombre() {
		crearLabel("Formulario:", 1, 0, 1);
		crearLabel("Nombre:", 1, 1, 1);
		crearLabel("Apellido:", 4, 1, 1);
		textField_nombre = new JTextField();
		gridBagConstraints(textField_nombre,2,1,1);
		textField_apellido = new JTextField();
		gridBagConstraints(textField_apellido,5,1,1);
		
	}
	
	private void msjConsigna() {
		crearLabel("Ingrese el nivel de interes que tiene la persona por los siguientes temas.", 1, 2, 5);
		crearLabel("(Donde 1 es el menor interes y 5 el maximo interes sobre un tema)", 1, 3, 5);
	}
	
	private void ingresoIntereses() {
		crearCampoInteres("Deportes:"   , 1, 4, bgDeportes);
		crearCampoInteres("Noticias:"   , 1, 5, bgNoticias);
		crearCampoInteres("M\u00FAsica:", 1, 6, bgMusica);
		crearCampoInteres("Ciencia:"    , 1, 7, bgCiencias);
	}

	private void crearCampoInteres(String text, int gridx, int gridy, ButtonGroup bg) {
		crearLabel(text, gridx, gridy, 1);		
		JPanel panel = new JPanel();
		gridBagConstraints(panel, gridx+1, gridy, 3);
		crearRadioButtons(panel, valoresRadioButton, bg);
	}
			
	private void gridBagConstraints(JTextField textField, int gridx, int gridy, int gridwidth) {
		Elementos.gridBagConstraints_Horizaontal(frame, textField, gridx, gridy, gridwidth, 1);
	}
	private void gridBagConstraints(JPanel panel, int gridx, int gridy, int gridwidth) {
		Elementos.gridBagConstraints_both(frame, panel, gridx, gridy, gridwidth, 1);
	}

	private ActionListener boton_AccionIngresar() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String interesD = obtenerSeleccionado(bgDeportes);
				String interesN = obtenerSeleccionado(bgNoticias);
				String interesM = obtenerSeleccionado( bgMusica );
				String interesC = obtenerSeleccionado(bgCiencias);
				String nombreP = textField_nombre.getText() +" "+ textField_apellido.getText();
				
				
				if (nombreP.isBlank()) {
					JOptionPane.showMessageDialog(null, "Se deben ingresar un nombre y apellido");
				}else {
					if (interesD==null || interesN==null || interesM==null || interesC==null) {
						JOptionPane.showMessageDialog(null, "Se deben llenar los campos de interes");
					}else {
						agregarPersona(nombreP, Integer.parseInt(interesD),Integer.parseInt(interesN),Integer.parseInt(interesM),Integer.parseInt(interesC));
						vaciarCampos();
					}
				}
			}
		};
	}
	private void vaciarCampos() {
		textField_nombre.setText("");
		textField_apellido.setText("");
		bgDeportes.clearSelection();
		bgNoticias.clearSelection();
		bgMusica.clearSelection();
		bgCiencias.clearSelection();
	}

	private String obtenerSeleccionado(ButtonGroup bg) {
		String valor = null;
		Enumeration<AbstractButton> iter = bg.getElements();
		while(iter.hasMoreElements()) {
			AbstractButton elem = iter.nextElement();
			if (elem.isSelected()) {
				valor = (String)elem.getText();
			}
		}
		return valor;
	}

	private ActionListener boton_AccionDividir() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cluster.dividirGrupos();
				Grupos grupos = new Grupos(cluster.getCluster());
				grupos.setVisible(true);
				frame.dispose(); //destruye ventana
			}
		};
	}
	
	private void agregarPersona(String nombre, int interesDeportes, int interesNoticias, int interesMusica, int interesCiencia) {  // Esto deberia hacerlo con JSON
		//creo persona
		Persona per = new Persona(nombre, interesDeportes,interesNoticias, interesMusica,  interesCiencia);	
		cluster.agregarPersona(per);
	}

}
