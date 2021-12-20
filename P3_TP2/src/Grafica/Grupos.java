package Grafica;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Grafica_auxiliares.Elementos;
import Grafica_auxiliares.estilo;
import Logica.Cluster;
import VerGrafo.panel;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JScrollPane;


public class Grupos {
	private JFrame frame;
	private Cluster cluster;
	private JPanel panel_lista;
	private JPanel panel_grafico;
	private JPanel panel_Promedio;
	private JTable primerTabla;
	private JScrollPane primerScrollPane;
	private JTable segundaTabla;
	private JScrollPane segundoScrollPane;
	private JTable promediosTabla;
	private JScrollPane promediosScrollPane;

	/**
	 * Create the application.
	 */
	public Grupos(Cluster recibido) {
		cluster = recibido;
		initialize();
	}
	public void setVisible(boolean b) {
		frame.setVisible(b);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame();
		TablasGrupos();
		DibujoGrafico();
		TablaPromedios();
		Botones();
	}

	
	private void frame() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		estilo.ventanaCentrada(frame, 700, 600);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {50, 100, 100, 100, 100, 100, 100, 50};
		gridBagLayout.rowHeights = new int[] {50, 100, 100, 100, 100, 100, 50};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0};
		frame.getContentPane().setLayout(gridBagLayout);
	}
	
	private void TablasGrupos() {
		panel_lista = new JPanel();
		panel_lista.setLayout(null);
		gridBagConstraints(panel_lista, 1, 1, 3, 5);		
		primeraTabla();
		segundaTabla();
	}
	
	private void DibujoGrafico() {
		panel_grafico = new JPanel();
		panel_grafico.setLayout(null);
		gridBagConstraints(panel_grafico, 4, 1, 3, 3);
		panel p=new panel(cluster);//clase
		p.setBounds(0, 0, 300, 495);
		panel_grafico.add(p);
	}
	
	private void TablaPromedios() {
		panel_Promedio = new JPanel();
		panel_Promedio.setLayout(null);
		gridBagConstraints(panel_Promedio, 4, 4, 3, 1);
		
		ArrayList<String> Promedios = cluster.ListaPromedioInteres();
		promediosScrollPane = new JScrollPane();
		promediosScrollPane.setBounds(0, 0, 295, 95);
		panel_Promedio.add(promediosScrollPane);
		promediosTabla = new JTable();
		promediosTabla.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		promediosScrollPane.setViewportView(promediosTabla);
		llenarTablaPromedios(promediosTabla, Promedios);
	}
	
	private void Botones() {
		Elementos.gridBagConstraints_both(frame, Elementos.crearBoton("Ingresar Persona", accionVolver()), 4, 5, 2, 1);
		Elementos.gridBagConstraints_both(frame, Elementos.crearBoton_salir(frame), 6, 5, 1, 1);
	}
	
	private void primeraTabla() {
		ArrayList<String> grupo1 = cluster.PrimerGrupo();
		primerScrollPane = new JScrollPane();
		primerScrollPane.setBounds(10, 11, 275, 247);
		panel_lista.add(primerScrollPane);
		primerTabla = new JTable();
		primerTabla.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		primerScrollPane.setViewportView(primerTabla);
		llenarTabla(primerTabla, grupo1, "Grupos 1");
	}
	
	private void segundaTabla() {
		ArrayList<String> grupo2 = cluster.SegundoGrupo();
		segundoScrollPane = new JScrollPane();
		segundoScrollPane.setBounds(10, 269, 275, 220);
		panel_lista.add(segundoScrollPane);
		segundaTabla = new JTable();
		segundaTabla.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		segundoScrollPane.setViewportView(segundaTabla);
		llenarTabla(segundaTabla, grupo2, "Grupo 2");
	}
	
	private void llenarTabla(JTable tabla, ArrayList<String> nombres, String grupo) {
		Object[] fila = new Object[nombres.size()];
		for (int indice = 0; indice < nombres.size(); indice++) {
			fila[indice]=nombres.get(indice);
		}
		((DefaultTableModel) tabla.getModel()).addColumn(grupo, fila);
	}	
	
	private void llenarTablaPromedios(JTable tabla, ArrayList<String> promedios) {
		Object[] fila = new Object[promedios.size()];
		String[] interes = {"Deportes", "Noticias", "Musica", "Ciencias"}; 
		((DefaultTableModel) tabla.getModel()).addColumn("Intereses", interes);
		for (int indice = 0; indice < promedios.size(); indice++) {
			fila[indice]=promedios.get(indice);
		}
		((DefaultTableModel) tabla.getModel()).addColumn("Promedios", fila);
	}
	
	private void gridBagConstraints(JPanel panel, int x, int y, int width, int height) {
		Elementos.gridBagConstraints_both(frame, panel, x, y, width, height);
	}
	
	private ActionListener accionVolver() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Form_ingreso ventana = new Form_ingreso();
				ventana.setVisible();
				frame.dispose();
			}
		};
	}
}
