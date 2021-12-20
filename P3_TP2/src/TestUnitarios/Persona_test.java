package TestUnitarios;

import static org.junit.Assert.*;
import org.junit.Test;

import Logica.Persona;

public class Persona_test {
	Persona persona = new Persona("Nicolas Luna", 3, 5, 2, 4);
		
	@Test
	public void clonar_test() {
		Persona clon = new Persona(); //persona sin nombre
		clon.clonar(persona);
		assertTrue(persona.equals(clon));
	}

}
