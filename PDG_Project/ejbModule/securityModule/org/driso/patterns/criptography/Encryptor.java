package org.driso.patterns.criptography;

public class Encryptor implements IEncryptor {

	private String key;

	private String encryptedMessage;

	public Encryptor() {

	}

	@Override
	public String[] encrypt(String mensajeEncriptar, String tipoAlgoritmo) throws Exception {
		String[] parejaLlave_MensajeEncryptado = null;
		if (TiposAlgoritmosCifrado.AES.equals(tipoAlgoritmo)) {
			parejaLlave_MensajeEncryptado = new String[2];
			IAlgorithm iAlgorithm = new AESAlgorithm();
			// key=FALTA SABER CÓMO GENERAR LA LLAVE.
			encryptedMessage = iAlgorithm.encrypt(key, mensajeEncriptar);
			parejaLlave_MensajeEncryptado[0] = key;
			parejaLlave_MensajeEncryptado[1] = encryptedMessage;
		} else if (TiposAlgoritmosCifrado.DES.equalsIgnoreCase(tipoAlgoritmo)) {
			parejaLlave_MensajeEncryptado = new String[2];
			IAlgorithm iAlgorithm = new DESAlgorithm();
			// key=FALTA SABER CÓMO GENERAR LA LLAVE.
			encryptedMessage = iAlgorithm.encrypt(key, mensajeEncriptar);
			parejaLlave_MensajeEncryptado[0] = key;
			parejaLlave_MensajeEncryptado[1] = encryptedMessage;
		} else {
			throw new Exception("ENCRYPTOR:" + "El tipo de algoritmo que indicó es nulo o no está disponible.");
		}
		return parejaLlave_MensajeEncryptado;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getEncryptedMessage() {
		return encryptedMessage;
	}

	public void setEncryptedMessage(String encryptedMessage) {
		this.encryptedMessage = encryptedMessage;
	}

}
