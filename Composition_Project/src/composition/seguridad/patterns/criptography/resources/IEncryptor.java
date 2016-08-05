package composition.seguridad.patterns.criptography.resources;

import composition.seguridad.patterns.criptography.resources.AKey;
import composition.seguridad.patterns.criptography.resources.EncryptedMessage;
import composition.seguridad.patterns.criptography.resources.Message;

public interface IEncryptor {

	public EncryptedMessage encrypt(Message mensajeEncriptar, String tipoAlgoritmo) throws Exception;

	public EncryptedMessage encrypt(Message mensajeEncriptar, AKey llave) throws Exception;

	/**
	 * Encripta dado un mensaje y una llave conocida de cualquier tipo de
	 * algoritmo simetrico.
	 * 
	 * @param mensajeEncriptar
	 * @param llave
	 * @return EncryptedMessage.
	 * @throws Exception
	 */
	public EncryptedMessage encryptConLlave(Message mensajeEncriptar, AKey llave) throws Exception;

}
