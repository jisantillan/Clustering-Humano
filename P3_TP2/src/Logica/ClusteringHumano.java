package Logica;

import JSON_.ManejoDato;

public class ClusteringHumano {
	ManejoDato datos;
	Cluster cluster;
	
	public ClusteringHumano() {
		datos = new ManejoDato();
	}
	
	public void agregarPersona(Persona persona) {
		datos.agregarPersona(persona);
	}
	
	public void dividirGrupos() {	
		inicializarCluster();
		actualizarJSON();
		cargarCluster();
		cluster.dividirGrupos();
	}
	
	private void inicializarCluster() {
		cluster = new Cluster(datos.cantidadPersonasCargadas());
	}
	
	private void actualizarJSON() {
		datos.actualizarDatos();
	}
	
	private void cargarCluster() {
		datos.actualizarCluster(cluster);
		cluster.generadorDeSimilitudes();		
	}
	
	// se tiene  que usar despues de dividir, se necesita el cluster inicializado
	public double promedioInteres(int indice) {
		return cluster.promedioInteres(indice);
	}

	public ManejoDato getDatos() {
		return datos;
	}

	public Cluster getCluster() {
		return cluster;
	}
	
	
}
