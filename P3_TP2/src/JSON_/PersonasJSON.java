package JSON_;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Logica.Persona;

public class PersonasJSON {
	private ArrayList<Persona> personas;
	
	public PersonasJSON(){
		personas = new ArrayList<Persona>();
	}
	
	public void agregar(Persona p) {
		if (!contiene(p)) {
			personas.add(p);
		}
	}
	
	private boolean contiene(Persona persona) {
		return personas.contains(persona);
	}

	public Persona obtener(int indice) {
		return personas.get(indice);
	}
	
	public int tamanio() {
		return personas.size();
	}
	
	public String GeneraJSON_pretty() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(this);
		return json;
	}
	
	public void guardarJSON(String json, String ArchivoDestino) {
		try {
			FileWriter writer = new FileWriter(ArchivoDestino);
			writer.write(json);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static PersonasJSON leerJSON(String archivo) {
		Gson gson = new Gson();
		PersonasJSON nuevo = null;
		try {
			BufferedReader br = new BufferedReader (new FileReader(archivo));
			nuevo = gson.fromJson(br, PersonasJSON.class);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nuevo;
	}
	
}
