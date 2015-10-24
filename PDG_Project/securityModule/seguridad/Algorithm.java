package seguridad;

import java.security.Key;

import javax.crypto.KeyGenerator;

public abstract class Algorithm implements IAlgorithm {

	
	/*
	 * Esta clase debería implementar el Generador de Llaves
	 */
	
	
	protected byte[] theKey;
	protected byte[] mensajeAEncriptar;
	protected byte[] esperado;
	protected byte[] mensajeADescifrar;
	
	protected Algorithm() {

		byte[] theKey = null;
		byte[] theMsg = null;
		byte[] theExp = null;

	}

	public byte[] getKey() {
		return theKey;
	}

	public void setKey(byte[] key) {
		this.theKey = key;
	}

	public byte[] getMensajeAEncriptar() {
		return mensajeAEncriptar;
	}

	public void setMensajeAEncriptar(byte[] mensajeAEncriptar) {
		this.mensajeAEncriptar = mensajeAEncriptar;
	}

	public byte[] getEsperado() {
		return esperado;
	}

	public void setEsperado(byte[] esperado) {
		this.esperado = esperado;
	}

	public byte[] getTheKey() {
		return theKey;
	}

	public void setTheKey(byte[] theKey) {
		this.theKey = theKey;
	}

	public byte[] getMensajeADescifrar() {
		return mensajeADescifrar;
	}

	public void setMensajeADescifrar(byte[] mensajeADescifrar) {
		this.mensajeADescifrar = mensajeADescifrar;
	}

	public String obtenerKey() {
		// TODO Auto-generated method stub
		return null;
	}

}
