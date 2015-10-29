package Interfaces;

import seguridad.AKey;
import seguridad.EncryptedMessage;
import seguridad.Message;

public interface IEncryptor {

	public EncryptedMessage encrypt(Message mensajeEncriptar, String tipoAlgoritmo) throws Exception;

	public EncryptedMessage encrypt(Message mensajeEncriptar, AKey llave) throws Exception;


}
