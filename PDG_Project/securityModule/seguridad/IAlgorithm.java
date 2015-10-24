package seguridad;

import java.security.Key;

public interface IAlgorithm {

	
	public String encrypt(Key key, String mensajeEncriptar) throws Exception;

	
	public String decrypt(Key llave, String mensajeADescifrar) throws Exception;
	
	public byte[] decryptByte(Key llave, String mensajeADescifrar);



}
