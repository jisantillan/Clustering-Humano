package VerGrafo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JPanel;

import Logica.Cluster;
import Logica.Grafo;
//dibuja el grafo en el panel
public class panel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Cluster cluster;
	Grafo g;
	ArrayList<vertice> vertices;
	ArrayList<arista> aristas;
	HashMap <String, HashMap<String, Integer>> Graf;

	ArrayList<Point> puntosVertices;
	ArrayList<arista> puntosAristas;
	Random r=new Random();
	int cantVert;
	public panel(Cluster c){//grafo grafo1,Cluster c){
			cantVert=cantVert(c);
			this.setSize(280,400);//(800,600);
			this.setBackground(Color.DARK_GRAY);
			vertices= new ArrayList<>();
			aristas=new ArrayList<>();
			puntosVertices=new ArrayList<>();
			puntosAristas=new ArrayList<>();
			
			Graf =new HashMap <String, HashMap<String, Integer>>();
			HashMap<String, Integer> aux= new HashMap<String, Integer>();//HashMap<'A', peso>- crea lista de vecinos
		
			//creo vertices
			for (int j = 0; j < cantVert(c); j++) {//cantidad de vertices
				aux= new HashMap<String, Integer>();
				//creo vecinos
				for (int i = 0; i < c.getAGM().cantidadVecinos(j); i++) {//cuantos vecinos tiene				
					if(c.getAGM().vertices().get(i).getNombre().equals(c.getAGM().getNombrePersona(j))) {
						aux.put(c.getAGM().getNombredeVecinos(c.getAGM().vertices().get(i)).get(i),1);
					}
				}
				//obtiene nombre del vertice y sus vecinos
				crearVertice(c.getAGM().getNombrePersona(j), aux);
			}
			crearMapa(c);
		}
	
	public int cantVert(Cluster cluster){
		ArrayList<String> grupo1 = cluster.PrimerGrupo();
		ArrayList<String> grupo2 = cluster.SegundoGrupo();
		return grupo1.size()+grupo2.size();
	}
	/*creamos vertices con posicion*/
	public void crearVertices(String v, int x, int y){	
		vertices.add(new vertice(v,x,y));//valor
	}

	public void crearArista(int d,int x, int y, int x1, int y1){
		aristas.add(new arista(d,x,y,x1,y1));
	}
	public void crearMapa(Cluster c){
		aleatoridad ram= new aleatoridad(cantVert);
		ArrayList<Point> ra= ram.getList();
		HashMap<String, Point> conjunto= new HashMap<String, Point>();
		
		//creamos nombre de vertices
		for (int j = 0; j < cantVert; j++) {
			crearVertices(c.getAGM().getNombrePersona(j), ra.get(j).x,ra.get(j).y);
			//lo guarda
			conjunto.put(c.getAGM().getNombrePersona(j), new Point(ra.get(j).x,ra.get(j).y));
		}
		
		//recorremos vertices
		for (int v=0;v<cantVert;v++){
			//copiamos datos del grafo
			HashMap<String, Integer> relacion= getVertice(c.getAGM().getNombrePersona(v));//obtengo nombre de relacion
			//obtiene sus aristas
			for(int u=0;u<cantVert;u++){	
				int d=0;
				try{
					d=relacion.get(c.getAGM().getNombrePersona(u));//ya que si es 0 no hay arista(es solo un vertice)
				}
				catch(Exception e){}
				if(d!=0){
					//creamos las relaciones
					crearArista(d,conjunto.get(c.getAGM().getNombrePersona(v)).x, conjunto.get(c.getAGM().getNombrePersona(v)).y,conjunto.get(c.getAGM().getNombrePersona(u)).x,conjunto.get(c.getAGM().getNombrePersona(u)).y);
				}
			}
		}
	}

	/*te muestra todas las relaciones del vertice que pasamos*/
	public HashMap<String, Integer> getVertice(String v){
		return Graf.get (v);
	}
	/*crea vertice y relacion en constructor*/
	public void crearVertice(String vertice,HashMap<String, Integer> relacion){
		Graf.put(vertice,relacion);
	}
	
	public HashMap<Integer, Integer> agmvecinos (int v){
		return cluster.getAGM().getVecinos(v);		
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(int i=0;i<vertices.size();i++){
			vertices.get(i).paint(g);
		}
		for(int i=0;i<aristas.size();i++){
			aristas.get(i).paint(g);
		}
		this.repaint();
	}

}