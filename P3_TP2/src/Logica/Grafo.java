package Logica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Grafo {
			
	private ArrayList <HashMap<Integer,Integer>> vecinos;// vecinos = [vertice]hashmap <vecino, peso>
	private ArrayList <Persona> vertices;	
	public Grafo(int v) 
	{		
		vertices = new ArrayList <Persona>(); 
		vecinos = new ArrayList <HashMap<Integer,Integer>> (); 
		for (int i = 0; i < v; i++)
			vecinos.add(new HashMap<Integer,Integer>());	
	}
		
	// Alta 
	public void agregarVertice(Persona persona){	
		if(!existePersona(persona))
		 	vertices.add(persona);
	}
	
	public boolean existePersona(Persona p) {
		if(vertices().contains(p))
		 	return true;
		return false;
	}
	
	public void agregarArista(Persona persona1,Persona persona2) {	
		if(existePersona(persona1) && existePersona(persona2)) { // si existen ambos vertices...
			int peso = calcularPeso(persona1, persona2);				 	
			int i = vertices.indexOf(persona1);
			int j = vertices.indexOf(persona2);
			vecinos.get(i).put(j, peso);
			vecinos.get(j).put(i, peso);
		}
	}
	
	// Bajas
	public void eliminarVertice(Persona p) {
		vertices.remove(p);		
	}
	
	public void eliminarArista(Persona persona1, Persona persona2){	
		int i = vertices.indexOf(persona1); // buscamos el vertice asociado a la persona inicio
		int j = vertices.indexOf(persona2); // buscamos el vertice asociado a la persona destino				
		vecinos.get(i).remove(j);
		vecinos.get(j).remove(i);
	}
	
	public int cantAristas() {
	    // La cantidad de aristas de un grafo completo esta dado por n (n-1)/2
//		int n = vecinos.size();
//		n = (n *(n-1))/2;
//		return n;
// 		en un grafo no dirigido la cantidad de aristas es igual a la suma de la cantidad de vecinos de cada vertice dividido por 2 
		int acumulacion = 0;
		int indice = 0;
		while(indice < cantVertices()) {
			acumulacion= acumulacion + getVecinos(indice).size();
			indice++;
		}
		return (acumulacion/2);
		
	}	
			
	public int cantVertices() {
		return vertices.size();
	}
	
	//Auxilaires
	public ArrayList<Integer> AristaMasPesada() {
		ArrayList<Integer> aristaPesada = new ArrayList<Integer>();
		int pmax = 0;
		int vaux;
		int verticeVecino = 0;
		int vertice = 0;
		for (int i = 0; i < vecinos.size(); i++) {
			vaux = vecinoMasPesado(vecinos.get(i));
			if(vecinos.get(i).get(vaux)!= null) {
				if(vecinos.get(i).get(vaux) > pmax ) {	
					pmax = vecinos.get(i).get(vaux);
					verticeVecino = vaux;
					vertice = i;}
				}
		}
		aristaPesada.add(vertice);
		aristaPesada.add(verticeVecino);
	
		return aristaPesada;
	}		

	private int vecinoMasPesado(HashMap<Integer , Integer > vecino) {
		Map <Integer,Integer> hm = new HashMap<Integer,Integer>();
		hm = vecino;
		int max = 0;
		int vertice = 0; 
		for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
		if(entry.getValue() >=0 ) {
			if(entry.getValue() > max) {
				max =entry.getValue() ;
				vertice = entry.getKey();}
			}
		}
		return vertice;
	}

		 
	public ArrayList<Persona> vertices() {
		return vertices;
	}
			
	public HashMap<Integer,Integer> getVecinos (Persona p) {		
		if(!vertices.contains(p))
			throw new IllegalArgumentException("La persona "+p.getNombre() +" no existe en el cluster");
		int i = vertices.indexOf(p);
		if(vecinos.get(i)!=null) 
			return vecinos.get(i);
		return null;
	}
	
	public HashMap<Integer,Integer> getVecinos (int i) {		
		return vecinos.get(i);
	}
	public ArrayList<HashMap<Integer,Integer>> getVecinos(){
		return vecinos;
	}
				
	public int calcularPeso(Persona i, Persona d) {   	
		int similaridad =  Math.abs(i.getInteresDeportes() - d.getInteresDeportes()) 
						 + Math.abs(i.getInteresMusica()- d.getInteresMusica())  	
						 + Math.abs( i.getInteresNoticias() - d.getInteresNoticias()) 
						 + Math.abs( i.getInteresCiencia() - d.getInteresCiencia()) ;
		return similaridad;
	}
		
	public String getNombrePersona(int indice) {
		return vertices.get(indice).getNombre();
	}
	
	public ArrayList<String> getNombredeVecinos (Persona p) {
		ArrayList<String> nombreVecinos = new ArrayList<String>();
		int i = vertices.indexOf(p);
		if(vecinos.get(i)!=null) {
			for (Map.Entry<Integer, Integer> entry : vecinos.get(i).entrySet()) {
				nombreVecinos.add(vertices.get(entry.getKey()).getNombre());
			}
		}
		return nombreVecinos;	

	}
			
	public Persona getVertice(String nombre) {
		for (Persona persona : vertices()) {
			if (persona.getNombre()==nombre) {
				return persona;
			}
		}
		return null;
	}
	
	public int cantidadVecinos(int indice) {
		return vecinos.get(indice).size();
	}
	
	public void setVecinos(ArrayList<HashMap<Integer, Integer>> vecinos) {
		this.vecinos = vecinos;
	}

	public void setVertices(ArrayList<Persona> vertices) {
		this.vertices = vertices;
	}
	
	
	
}


		
		 	
		 	 
		 	
