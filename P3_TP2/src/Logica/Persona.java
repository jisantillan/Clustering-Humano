package Logica;

import java.util.Arrays;


public class Persona{
	private String nombrePersona;
	private int interesDeportes;
	private int interesNoticias;
	private int interesMusica; 
	private int interesCiencia;
	private static int cantCamposInteres = 4;
	private static int maximoInteres = 5;
	private static int[] rango_interes = {1,maximoInteres};

	public Persona() {
		int constante = 1;
		this.interesDeportes = constante;
		this.interesMusica   = constante;
		this.interesNoticias = constante;
		this.interesCiencia  = constante;
	}
	
	public Persona(String nombre, int interesDeportes, int interesNoticias, int interesMusica, int interesCiencia) {
	   	verificarNombre(nombre);
	   	this.nombrePersona   = nombre;
		this.interesDeportes = esIndice(interesDeportes);
		this.interesNoticias = esIndice(interesNoticias);
		this.interesMusica   = esIndice(interesMusica);
		this.interesCiencia  = esIndice(interesCiencia);
	}
	
	private void verificarNombre(String nombre) {
		for (int i = 0; i < nombre.length(); i++) {
			if (Character.isDigit(nombre.charAt(i)))
				throw new  IllegalArgumentException ("Error: el nombre no puede contener caracteres numericos");
		}
	}
	private int esIndice(int valor){
		if(valor < rango_interes[0] || valor > rango_interes[1])
			throw new IllegalArgumentException ("Error: el indice debe estar entre "+rango_interes[0]+" y "+rango_interes[1]+".");
		else
			return valor;
	}
	
	public void clonar(Persona p){
	 	this.nombrePersona   = p.getNombre();
		this.interesDeportes = p.getInteresDeportes();
		this.interesNoticias = p.getInteresNoticias();
		this.interesMusica   = p.getInteresMusica();
		this.interesCiencia  = p.getInteresCiencia();
	}
	
	public int[] conjuntoIntereses() {
		int[] ret = {interesDeportes, interesNoticias, interesMusica, interesCiencia};
		return ret;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + interesCiencia;
		result = prime * result + interesDeportes;
		result = prime * result + interesMusica;
		result = prime * result + interesNoticias;
		result = prime * result + ((nombrePersona == null) ? 0 : nombrePersona.hashCode());
		result = prime * result + Arrays.hashCode(rango_interes);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		if (interesCiencia != other.interesCiencia)
			return false;
		if (interesDeportes != other.interesDeportes)
			return false;
		if (interesMusica != other.interesMusica)
			return false;
		if (interesNoticias != other.interesNoticias)
			return false;
		if (nombrePersona == null) {
			if (other.nombrePersona != null)
				return false;
		} else if (!nombrePersona.equals(other.nombrePersona))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return   nombrePersona ;
	}
	public int getInteresDeportes() {
		return interesDeportes;
	}
	public int getInteresMusica() {
		return interesMusica;
	}
	public int getInteresNoticias() {
		return interesNoticias;
	}
	public int getInteresCiencia() {
		return interesCiencia;
	}
	public String getNombre() {
		return nombrePersona;
	}
	public static int getCantCamposInteres() {
		return cantCamposInteres;
	}
	public static int getMaximoInteres() {
		return maximoInteres;
	}
	
}
