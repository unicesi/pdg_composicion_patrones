package org.driso.patterns.criptography;

import java.security.Key;

import javax.crypto.KeyGenerator;

public abstract class Algorithm implements IAlgorithm {

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
		return mensajeADescifrar;
	}

	public void setMensajeAEncriptar(byte[] mensajeAEncriptar) {
		this.mensajeADescifrar = mensajeAEncriptar;
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

}
