package TestUnitarios;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import Logica.Grafo;
import Logica.Persona;

public class Grafo_test {
	Grafo g = new Grafo(4);
	Persona p1 = new Persona("Pepito", 1, 3, 1, 4);
	Persona p2 = new Persona("Juan", 5, 2, 1, 1);
	Persona p3 = new Persona("Maria", 1, 1, 5, 5);
	Persona p4 = new Persona("Ana", 3, 3, 1, 4);
	
	@Before 
	public void grafo_de_prueba() {
		g = new Grafo(4);
		g.agregarVertice(p1);
		g.agregarVertice(p2);
		g.agregarVertice(p3);
		g.agregarVertice(p4);			   //            p2	 
		g.agregarArista(p1, p2);		  //           -  -  -
		g.agregarArista(p1, p3);         //          -    -    -
		g.agregarArista(p1, p4);        //       p1 - - - - - - - p3   --> Grafo completo  
		g.agregarArista(p2, p4);       //            -    -     -
		g.agregarArista(p3, p4);      //               -  -  -
		g.agregarArista(p2, p3);     //                   p4
	}
	
	@Test
	public void Agregar_Vertice() {
		Grafo g = new Grafo(3);
		g.agregarVertice(p4);
		assertEquals("Ana",g.vertices().get(0).getNombre());
	}
	
	@Test
	public void Agregar_Arista() {
		Grafo g = new Grafo(3);
		g.agregarVertice(p1);
		g.agregarVertice(p2);
		g.agregarArista(p1, p2);
		assertEquals(1, g.getVecinos(p1).size());
	}
	@Test
	public void calcular_Peso_test() {
		assertEquals(2,g.calcularPeso(p1, p4));
		
	}

	@Test
	public void Cantidad_Vertices() {
		assertEquals(4, g.cantVertices());
	}
	
	@Test
	public void Cantidad_Aristas() {
		assertEquals(6, g.cantAristas());
	}
	
	@Test
	public void Cantidad_Vecinos_Alguno(){
		assertEquals(3, g.getVecinos(p1).size());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void Cantidad_Vecinos_Inexistente() {
		Persona p5 = new Persona("Pepita", 1, 3, 1, 4);		
		assertEquals(3, g.getVecinos(p5).size());
	
	}
	
	public void Eliminar_Vertice() {
		g.eliminarVertice(p1);
		assertFalse(g.existePersona(p1));
		
		
	}
	
	@Test
	public void Eliminar_Arista() {
		g.eliminarArista(p1, p2);
		assertFalse(g.getVecinos(p1).containsKey(g.vertices().indexOf(p2)));
		assertFalse(g.getVecinos(p2).containsKey(g.vertices().indexOf(p1)));

	}
	
	
	
	@Test
	public void Consulta_Vertice() {
		assertEquals(0,g.vertices().indexOf(g.getVertice(p1.getNombre())));
		
	}
	
	

}
