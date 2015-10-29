package prueba;

import java.io.Serializable;

public class Perro implements Serializable{

	private int edad = 7;
	private String raza = "Siberiano";
	private String nombre = "Romsky";

	public int getEdad() {
		return edad;
	}

	public String getRaza() {
		return raza;
	}

	public String getNombre() {
		return nombre;
	}

}
