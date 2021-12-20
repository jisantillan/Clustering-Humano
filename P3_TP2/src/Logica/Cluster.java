package Logica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Cluster {
	private Grafo grafo ;
	private AGM agmFinal;
	private ArrayList<Integer> aristaPesada ; // tiene en 0 un vertice y en 1 al otro vertice que entre los dos conforman la arista mas pesada
	private double[] acumInteres = new double[Persona.getCantCamposInteres()];
		
	public Cluster (int v) {
		grafo = new Grafo (v);
	}
	
	public void dividirGrupos() {
		agmFinal = new AGM (grafo);
		dividirAGM();
	}
	
	public void agregarPersona(Persona p) {
		grafo.agregarVertice(p);
		actualizarAcumInteres(p.conjuntoIntereses());
	}
	
	private void dividirAGM() {
		aristaPesada = new ArrayList<Integer>();
		aristaPesada.add(agmFinal.AristaMasPesada().get(0));
		aristaPesada.add(agmFinal.AristaMasPesada().get(1));
		agmFinal.eliminarArista(agmFinal.vertices().get(aristaPesada.get(0)), agmFinal.vertices().get(aristaPesada.get(1)));
	}
	
	//Esta es la logica para mostrar grupos, pero dsp  lo vamos a mostrar con Clustering() Asi que se sacaria
	public void mostrarGrupos() {
		Set<Integer> s = new HashSet<Integer> ();
		s = BFS.alcanzables(grafo, aristaPesada.get(0));
		recorrerGrupo(s);
		s.clear();
		s = BFS.alcanzables(grafo, aristaPesada.get(1));
		recorrerGrupo(s);
	}
		
	public ArrayList<String> getVecinosNombre (String s) {
		for (int i = 0; i < agmFinal.cantVertices(); i++) {
			if(agmFinal.getNombrePersona(i).equals(s)) {
				return agmFinal.getNombredeVecinos(agmFinal.getVertice(s));
			}
		}
		return null;
	}
	
	public ArrayList<String> PrimerGrupo() {
		Set<Integer> s = new HashSet<Integer> ();
		s = BFS.alcanzables(agmFinal, aristaPesada.get(0));
		return recorrerGrupo(s);
	}
	
	public ArrayList<String> SegundoGrupo() {
		Set<Integer> s = new HashSet<Integer> ();
		s = BFS.alcanzables(agmFinal, aristaPesada.get(1));
		return recorrerGrupo(s);
	}
	
	private ArrayList<String> recorrerGrupo(Set<Integer> s) {
		ArrayList<String> ret = new ArrayList<String>();
		for(Integer i : s)
			ret.add(agmFinal.vertices().get(i).getNombre());
		return ret;
	}
	
	public void generadorDeSimilitudes() {
		// Como el grafo es completo vamos a relacionar todos los vertices entre si
		for (int i = 0; i < grafo.vertices().size(); i++) {
			if(grafo.vertices().get(i)!=null)
				for (int j = 0; j < grafo.vertices().size(); j++) {
					if(i != j) {// ya que no es un di-grafo
						if(grafo.vertices().get(j)!=null)
								grafo.agregarArista(grafo.vertices().get(i),grafo.vertices().get(j));
					}
				}
		}
	}
	
	public double promedioInteres(int numInteres) {
		double ret = acumInteres[numInteres] / grafo.cantVertices();
		return ret;
	}
	
	public ArrayList<String> ListaPromedioInteres(){
		ArrayList<String> lista = new ArrayList<String>();
		for (int indice = 0; indice < acumInteres.length; indice++) {
			String s = promedioInteres(indice)+"";
			lista.add(s.substring(0, 3));
		}
		return lista;
	}
	
	private void actualizarAcumInteres(int[] interes){
		for (int indice = 0; indice < interes.length; indice++) {
			acumInteres[indice] = acumInteres[indice] + interes[indice];
		}
	}
	public ArrayList<Integer> getAristaPesada() {
		return aristaPesada;
	}
	
	public Grafo getGrafo() {
		return grafo;
	}
	
	public Grafo getAGM() {
		return agmFinal;
	}
	
}
