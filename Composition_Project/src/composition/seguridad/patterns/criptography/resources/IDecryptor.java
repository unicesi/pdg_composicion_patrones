package composition.seguridad.patterns.criptography.resources;

import composition.seguridad.patterns.criptography.resources.AKey;
import composition.seguridad.patterns.criptography.resources.EncryptedMessage;
import composition.seguridad.patterns.criptography.resources.Message;

public interface IDecryptor {

	public Message decrypt(AKey llave, EncryptedMessage mensajeADescifrar) throws Exception;

}
