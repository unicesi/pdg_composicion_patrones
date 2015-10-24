package seguridadsincrona;

import java.security.Key;

import javax.crypto.KeyGenerator;

import seguridad.IAlgorithm;

public class Encryptor implements IEncryptor {

	private Key key;

	private String encryptedMessage;

	private KeyGenerator keyGenerator;

	public Encryptor() {

	}

	@Override
	public Object[] encrypt(String mensajeEncriptar, String tipoAlgoritmo) throws Exception {
		Object[] parejaLlave_MensajeEncryptado = null;
		if (TiposAlgoritmosCifrado.AES.equals(tipoAlgoritmo)) {
			parejaLlave_MensajeEncryptado = new Object[2];
			IAlgorithm iAlgorithm = new AESAlgorithm();

			keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128);
			key = keyGenerator.generateKey();

			encryptedMessage = iAlgorithm.encrypt(key, mensajeEncriptar);
			parejaLlave_MensajeEncryptado[0] = (Object) key;
			parejaLlave_MensajeEncryptado[1] = (Object) encryptedMessage;
		} else if (TiposAlgoritmosCifrado.DES.equalsIgnoreCase(tipoAlgoritmo)) {
			parejaLlave_MensajeEncryptado = new Object[2];
			IAlgorithm iAlgorithm = new DESAlgorithm();
			keyGenerator = KeyGenerator.getInstance("DES");
			keyGenerator.init(56);
			key = keyGenerator.generateKey();
			encryptedMessage = iAlgorithm.encrypt(key, mensajeEncriptar);
			parejaLlave_MensajeEncryptado[0] = (Object) key;
			parejaLlave_MensajeEncryptado[1] = (Object) encryptedMessage;
		} else {
			throw new Exception("ENCRYPTOR:" + "El tipo de algoritmo que indic� es nulo o no est� disponible.");
		}
		return parejaLlave_MensajeEncryptado;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getEncryptedMessage() {
		return encryptedMessage;
	}

	public void setEncryptedMessage(String encryptedMessage) {
		this.encryptedMessage = encryptedMessage;
	}

}
