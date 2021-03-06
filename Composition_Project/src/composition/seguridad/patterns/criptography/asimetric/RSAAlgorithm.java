package composition.seguridad.patterns.criptography.asimetric;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import javax.crypto.Cipher;

import composition.seguridad.patterns.criptography.resources.AKey;
import composition.seguridad.patterns.criptography.resources.EncryptedMessage;
import composition.seguridad.patterns.criptography.resources.Message;

public class RSAAlgorithm extends AsymAlgorithm {

	public RSAAlgorithm() {
		// TODO Auto-generated constructor stub
	}

	public EncryptedMessage encrypt(AKey key, Message mensajeEncriptar) throws Exception {

		System.out.println("Entró a encriptar con RSA");

		Object mensaje = mensajeEncriptar.getMensaje();
		Key llave = key.getLlave();
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(bs);
		os.writeObject(mensaje);
		os.close();
		byte[] bytes = bs.toByteArray();

		Cipher cf;
		byte[] mensajeCifrado = null;

		try {
			cf = Cipher.getInstance("RSA");
			cf.init(Cipher.ENCRYPT_MODE, llave);
			mensajeCifrado = cf.doFinal(bytes);
		
		} catch (Exception e) {
			e.printStackTrace();
		}

		EncryptedMessage mensajeEncriptado = new EncryptedMessage();
		mensajeEncriptado.setMensaje(mensajeCifrado);
		return mensajeEncriptado;
	}

	public Message decrypt(AKey llave, EncryptedMessage mensajeADescifrar) throws Exception {

		System.out.println("Entró a desencriptar con RSA");
		Object elMensaje = mensajeADescifrar.getMensaje();

		byte[] mensaje = (byte[]) elMensaje;
		byte[] theCph = null;

		Key laLlave = llave.getLlave();

		Cipher cf;
		try {
			cf = Cipher.getInstance("RSA");

			cf.init(Cipher.DECRYPT_MODE, laLlave);
			theCph = cf.doFinal(mensaje);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		ByteArrayInputStream bs = new ByteArrayInputStream(theCph);
		ObjectInputStream is = new ObjectInputStream(bs);
		Object objeto = (Object) is.readObject();
		is.close();

		Message mensajeDesencriptado = new Message();
		mensajeDesencriptado.setMensaje(objeto);

		return mensajeDesencriptado;

	}

}
