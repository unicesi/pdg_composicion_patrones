package seguridadsincrona;

import java.security.Key;

public interface IDecryptor {
	
	
	public Object decrypt(Key llave, String mensajeADescifrar) throws Exception;
	public byte[] decryptByte(Key llave, String mensajeADescifrar);


}
