package Logica;

import java.util.ArrayList;
import java.util.HashMap;

public class AGM extends Grafo{
	private ArrayList<Persona> listaVertices;					 //Listas auxiliares que usamos al ir armando el AGM
	private ArrayList <HashMap<Integer,Integer>> listaVecinos;
	private static int distanciaTentativa; 
	
	public AGM(Grafo g) {
		super(g.cantVertices()); // Dado que un arbol tiene |Aristas| = |Vertices| -1 --> Nos creara una lista de vecinos de tamaño V-1
		distanciaTentativa = calcularDistanciaTentativa(g);
		generar(g);
	}
	private void generar(Grafo ingresado) {
		listaVertices = new ArrayList<Persona>(); 	 	
		listaVertices.add(ingresado.vertices().get(0)); 
		listaVecinos = new ArrayList <HashMap<Integer,Integer>>(); 
		
		while (ingresado.cantVertices() > listaVecinos.size()) {
			listaVecinos.add(new HashMap<Integer,Integer>());
		}
		while (ingresado.cantVertices() > listaVertices.size()) {
			listaVertices.add(buscarVecinoMenorPeso(ingresado, listaVertices));
		}
		super.setVecinos(listaVecinos);
		super.setVertices(listaVertices);		
//		ingresado.setVecinos(listaVecinos); //Esto no estoy seguro que este bien, haciendo esto generamos el agm en cluster y podriamos usar sus 
//		ingresado.setVertices(listaVertices);//metodos dividirGrupos() y mostrarGrupos() cuando estemos dentro de Clustering (gui)
	}	
	@Override 
	 public int cantAristas() {
		return listaVertices.size()-1;
	}
	//Lista de vertices que formara el agm
	private Persona buscarVecinoMenorPeso(Grafo grafoCompleto, ArrayList<Persona> listaPersonas) {
		Persona candidatoAux = new Persona();
		int numVerticePersona = 0 ;
		int numVecinoMinimo = 0; 
		int peso = distanciaTentativa;
		int pesoAux;
		
		for (Persona persona : listaPersonas) {
			candidatoAux.clonar(AGM.vecinoMenorPeso(grafoCompleto, persona, listaPersonas));
			pesoAux = grafoCompleto.calcularPeso(persona, candidatoAux);
			if (peso > pesoAux) {
				peso = pesoAux;
				numVerticePersona =listaPersonas.indexOf(persona);
				numVecinoMinimo ++ ;
			}
			else {
				numVecinoMinimo  ++ ;
			}
		}
		
		listaVecinos.get(numVerticePersona).put(numVecinoMinimo, peso);
		listaVecinos.get(numVecinoMinimo).put(numVerticePersona, peso);
		return candidatoAux; 
	}
	
	public static Persona vecinoMenorPeso(Grafo grafo, Persona persona, ArrayList<Persona> excepciones) {
		int indiceVecino=0 , peso = distanciaTentativa;
		int indiceMenor=0;
		while (indiceVecino < grafo.cantVertices()) {
			if (grafo.getVecinos(persona).get(indiceVecino)==null) {
				
			}else {
				int pesoAlgunVecino = grafo.getVecinos(persona).get(indiceVecino); // buscamos el peso de los vecinos por persona ( devuelve el hashMap) el get (i) te da el peso en vecinos del grafo original
				if(!excepciones.contains(grafo.vertices().get(indiceVecino))) { // sino esta contenido en los vertices que forman el agm
					if (peso > pesoAlgunVecino) {
						indiceMenor = indiceVecino;
						peso = pesoAlgunVecino;
					}
				}
			}
			indiceVecino++;
		}
		return grafo.vertices().get(indiceMenor);
	}	
	
	private int calcularDistanciaTentativa(Grafo g) {
		int campos = Persona.getCantCamposInteres(); //4
		int maximo = Persona.getMaximoInteres();     //5
		return ( (maximo-1)*campos )+1;              //( (5-1)x4 )+1 = 17  // no se puede tener un peso/similaridad > 17
	}
}
