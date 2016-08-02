package composition.seguridad.patterns.criptography.resources;

import java.security.Key;

import composition.seguridad.patterns.criptography.asimetric.RSAAlgorithm;
import composition.seguridad.patterns.criptography.simetric.AESAlgorithm;
import composition.seguridad.patterns.criptography.simetric.DESAlgorithm;

public class Encryptor implements IEncryptor {

	private Key key;

	private EncryptedMessage encryptedMessage;

	private ServicioLlaves servicio;

	public Encryptor() {

	}

	@Override
	public EncryptedMessage encrypt(Message mensajeEncriptar, String tipoAlgoritmo) throws Exception {

		servicio = new ServicioLlaves();

		Object[] parejaLlave_MensajeEncryptado = null;
		if (TiposAlgoritmosCifrado.AES.equals(tipoAlgoritmo)) {
			parejaLlave_MensajeEncryptado = new Object[2];
			IAlgorithm iAlgorithm = new AESAlgorithm();

			key = servicio.generarLlaveSimetrica("AES");

			AKey llave = new AKey();
			llave.setKey(key);

			encryptedMessage = iAlgorithm.encrypt(llave, mensajeEncriptar);
			parejaLlave_MensajeEncryptado[0] = (Object) llave;
			parejaLlave_MensajeEncryptado[1] = (Object) encryptedMessage.getMensaje();

		} else if (TiposAlgoritmosCifrado.DES.equalsIgnoreCase(tipoAlgoritmo)) {
			parejaLlave_MensajeEncryptado = new Object[2];
			IAlgorithm iAlgorithm = new DESAlgorithm();

			key = servicio.generarLlaveSimetrica("DES");
			AKey llave = new AKey();
			llave.setKey(key);

			encryptedMessage = iAlgorithm.encrypt(llave, mensajeEncriptar);
			parejaLlave_MensajeEncryptado[0] = (Object) llave;
			parejaLlave_MensajeEncryptado[1] = (Object) encryptedMessage.getMensaje();
		} else {

			throw new Exception("ENCRYPTOR:" + "El tipo de algoritmo que indic� es nulo o no est� disponible.");
		}
		encryptedMessage.setMensaje(parejaLlave_MensajeEncryptado);
		return encryptedMessage;

	}

	@Override
	public EncryptedMessage encrypt(Message mensajeEncriptar, AKey llave) throws Exception {

		IAlgorithm algoritmo = new RSAAlgorithm();

		EncryptedMessage mensajeEncriptado = algoritmo.encrypt(llave, mensajeEncriptar);
		return mensajeEncriptado;
	}

	/**
	 * Encripta dado un mensaje y una llave conocida de cualquier tipo de
	 * algoritmo simetrico.
	 * 
	 * @param mensajeEncriptar
	 * @param llave
	 * @return EncryptedMessage.
	 * @throws Exception
	 */
	public EncryptedMessage encryptConLlave(Message mensajeEncriptar, AKey llave) throws Exception {

		String tipoAlgoritmo = llave.getLlave().getAlgorithm();

		if (TiposAlgoritmosCifrado.AES.equals(tipoAlgoritmo)) {

			IAlgorithm iAlgorithm = new AESAlgorithm();

			encryptedMessage = iAlgorithm.encrypt(llave, mensajeEncriptar);

		} else if (TiposAlgoritmosCifrado.DES.equalsIgnoreCase(tipoAlgoritmo)) {

			IAlgorithm iAlgorithm = new DESAlgorithm();

			encryptedMessage = iAlgorithm.encrypt(llave, mensajeEncriptar);

		} else {

			throw new Exception("ENCRYPTOR:" + "El tipo de algoritmo que indic� es nulo o no est� disponible.");
		}

		return encryptedMessage;

	}

}
