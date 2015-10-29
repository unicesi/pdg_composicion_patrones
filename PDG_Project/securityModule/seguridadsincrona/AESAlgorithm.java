package seguridadsincrona;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.*;
import seguridad.AKey;
import seguridad.EncryptedMessage;
import seguridad.Message;

public class AESAlgorithm extends SymAlgorithm {

	public AESAlgorithm() {
		super();

	}

	@Override
	public EncryptedMessage encrypt(AKey key, Message mensajeEncriptar) throws Exception {
		System.out.println("Entró a encriptar con AES");

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

			cf = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cf.init(Cipher.ENCRYPT_MODE, llave);
			mensajeCifrado = cf.doFinal(bytes);

		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException e) {
			e.printStackTrace();
		}

		EncryptedMessage mensajeEncriptado = new EncryptedMessage();
		mensajeEncriptado.setMensaje(mensajeCifrado);
		return mensajeEncriptado;

	}

	@Override
	public Message decrypt(AKey llave, EncryptedMessage mensajeADescifrar) throws Exception {

		System.out.println("Entró a desencriptar con AES");
		Object elMensaje = mensajeADescifrar.getMensaje();

		byte[] mensaje = (byte[]) elMensaje;
		byte[] theCph = null;

		Key laLlave = llave.getLlave();

		Cipher cf;

		try {
			cf = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cf.init(Cipher.DECRYPT_MODE, laLlave);

			theCph = cf.doFinal(mensaje);

		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException e) {
			e.printStackTrace();
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
