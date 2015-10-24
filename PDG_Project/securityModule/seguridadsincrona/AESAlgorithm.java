package seguridadsincrona;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.*;
import org.apache.commons.codec.binary.Base64;
import seguridad.Algorithm;

public class AESAlgorithm extends Algorithm {

	public AESAlgorithm() {
		super();

	}

	@Override
	public String encrypt(Key llave, String mensajeEncriptar) throws Exception {

		System.out.println("Entra a encriptar con AES, y el mensaje es: " + mensajeEncriptar);
		String mensajeCifrado = null;
		Cipher cf;
		try {
			String nuevoMensajeEncriptar = mensajeEncriptar.trim();

			cf = Cipher.getInstance("AES/ECB/PKCS5Padding");
			// http://crypto.stackexchange.com/questions/9043/what-is-the-difference-between-pkcs5-padding-and-pkcs7-padding
			cf.init(Cipher.ENCRYPT_MODE, llave);
			byte[] theCph = cf.doFinal(nuevoMensajeEncriptar.getBytes("UTF-8"));

			mensajeCifrado = Base64.encodeBase64String(theCph);
			System.out.println("Mensaje Cifrado:" + mensajeCifrado);

		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException e) {
			e.printStackTrace();
		}

		return mensajeCifrado;

	}

	@Override
	public String decrypt(Key llave, String mensajeADescifrar) throws Exception {

		String mensajeOriginal = null;

		Cipher cf;
		try {
			cf = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cf.init(Cipher.DECRYPT_MODE, llave);
			byte[] theCph = cf.doFinal(Base64.decodeBase64(mensajeADescifrar));

			mensajeOriginal = new String(theCph, StandardCharsets.UTF_8);

		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException e) {
			e.printStackTrace();
		}

		return mensajeOriginal;
	}

	@Override
	public byte[] decryptByte(Key llave, String mensajeADescifrar) {

		String mensajeOriginal = null;
		byte[] theCph = null;
		Cipher cf;
		try {
			cf = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cf.init(Cipher.DECRYPT_MODE, llave);
			theCph = cf.doFinal(Base64.decodeBase64(mensajeADescifrar));

			mensajeOriginal = new String(theCph, StandardCharsets.UTF_8);

		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException e) {
			e.printStackTrace();
		}

		return theCph;
	}

}
