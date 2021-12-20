package JSON_;

import java.util.ArrayList;
import Logica.Cluster;
import Logica.Persona;

public class ManejoDato {	
	PersonasJSON personasJSON;
	ArrayList<Persona> listaPersonas;

	public ManejoDato() {	
		personasJSON = new PersonasJSON();	
		if (PersonasJSON.leerJSON("src\\JSON_DATOS\\datos.JSON")!=null)
			personasJSON = PersonasJSON.leerJSON("src\\JSON_DATOS\\datos.JSON");
		listaPersonas = new ArrayList<Persona>();
	}
		
	public void agregarPersona(Persona persona) {
		listaPersonas.add(persona);
	}
	
	public void actualizarDatos() {
		for (Persona persona : listaPersonas) {
			personasJSON.agregar(persona);
		}
		String json = personasJSON.GeneraJSON_pretty();
		personasJSON.guardarJSON(json, "src\\JSON_DATOS\\datos.JSON");	
	}
	
	public void actualizarCluster(Cluster Cluster) {
		int indice = 0;
		while(indice < personasJSON.tamanio()) {
			Cluster.agregarPersona(personasJSON.obtener(indice));
			indice++;
		}
	}
	
	public int cantidadPersonasCargadas() {
		return personasJSON.tamanio()+listaPersonas.size();
	}
		
}
