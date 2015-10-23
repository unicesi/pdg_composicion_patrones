package org.driso.patterns.criptography;

public interface IEncryptor {

	public String[] encrypt(String mensajeEncriptar, String tipoAlgoritmo) throws Exception;

}
