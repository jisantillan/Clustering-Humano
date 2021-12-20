package TestUnitarios;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import Logica.Cluster;
import Logica.Persona;

public class ClusterTest {
	Cluster c;
	Persona p1 = new Persona("Pepito", 1, 3, 1, 4);
	Persona p2 = new Persona("Juan", 5, 2, 1, 1);
	Persona p3 = new Persona("Maria", 1, 1, 5, 5);
	Persona p4 = new Persona("Ana", 3, 3, 1, 4);
	
	@Before 
	public void grafo_de_prueba() {
		c = new Cluster(4);
	
		c.agregarPersona(p1);
		c.agregarPersona(p2);
		c.agregarPersona(p3);
		c.agregarPersona(p4);
		
		c.generadorDeSimilitudes();
		
		
										   //            p2	 
										  //           -  -  -
										 //          -    -    -
										//       p1 - - - - - - - p3   --> Grafo completo  
		                               //            -    -     -
									  //               -  -  -
									 //                   p4
	}

	@Test
	public void agregar_Persona_test(){
		assertEquals(4, c.getGrafo().cantVertices());
	}
	
	@Test
	public void generador_Similitudes_test(){
		assertEquals(6, c.getGrafo().cantAristas());
	}
	
	@Test
	public void dividir_Grupos_test() {
		c.dividirGrupos();
		assertEquals(2,c.getAGM().cantAristas()-1);
		
	}
}
