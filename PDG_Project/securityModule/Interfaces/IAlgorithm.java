package Interfaces;

import seguridad.AKey;
import seguridad.EncryptedMessage;
import seguridad.Message;

public interface IAlgorithm {

	public EncryptedMessage encrypt(AKey key, Message mensajeEncriptar) throws Exception;

	public Message decrypt(AKey llave, EncryptedMessage mensajeADescifrar) throws Exception;

}
