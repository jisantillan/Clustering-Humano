package TestUnitarios;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import Logica.AGM;
import Logica.Grafo;
import Logica.Persona;

public class AGM_test {
	AGM agm;
	
	@Before 
	public void grafo_de_prueba() {
		Grafo g = new Grafo(4);
		Persona p1 = new Persona("Pepito", 1, 3, 1, 4);
		Persona p2 = new Persona("Juan", 5, 2, 1, 1);
		Persona p3 = new Persona("Maria", 1, 1, 5, 5);
		Persona p4 = new Persona("Ana", 3, 3, 1, 4);
		g.agregarVertice(p1);
		g.agregarVertice(p2);
		g.agregarVertice(p3);
		g.agregarVertice(p4);
										   //            p2	 
		g.agregarArista(p1, p2);		  //           -  -  -
		g.agregarArista(p1, p3);         //          -    -    -
		g.agregarArista(p1, p4);        //       p1 - - - - - - - p3   --> Grafo completo  
		g.agregarArista(p2, p4);       //            -    -     -
		g.agregarArista(p3, p4);      //               -  -  -
		g.agregarArista(p2, p3);     //                   p4
		agm = new AGM(g); 
	}

	@Test
	public void cantidad_Vertices_test(){
		assertEquals(4, agm.cantVertices());
	}
	@Test
	public void cantidad_Aristas_test() {
		assertEquals(3, agm.cantAristas());
	}
	
	@Test
	public void cantVecinos1_test() {
		assertEquals(2, agm.getVecinos(0).size());
	}
	@Test
	public void cantVecinos2_test() {
		assertEquals(2, agm.getVecinos(1).size());
	}
	@Test
	public void cantVecinos3_test() {
		assertEquals(1, agm.getVecinos(2).size());
	}
	@Test
	public void cantVecinos4_test() {
		assertEquals(1, agm.getVecinos(3).size());
	}
}
