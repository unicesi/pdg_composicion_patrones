package org.driso.patterns.criptography;

public interface IAlgorithm {

	public String encrypt(String llave, String mensajeEncriptar) throws Exception;

	public String decrypt(String llave, String mensajeDescifrar) throws Exception;

	public String obtenerKey();

}
