package Interfaces;

import seguridad.AKey;
import seguridad.EncryptedMessage;
import seguridad.Message;

public interface IDecryptor {

	public Message decrypt(AKey llave, EncryptedMessage mensajeADescifrar) throws Exception;

}
