package seguridadsincrona;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.*;
import org.apache.commons.codec.binary.Base64;
import seguridad.Algorithm;

public class DESAlgorithm extends Algorithm {

	public DESAlgorithm() {
		super();

	}

	@Override
	public String encrypt(Key llave, String mensajeEncriptar) throws Exception {

		System.out.println("Entra a encriptar con DES, y el mensaje es:" + mensajeEncriptar);
		String mensajeCifrado = null;
		Cipher cf;
		try {
			String nuevoMensajeEncriptar = mensajeEncriptar.trim();
			System.out.println("nuevo:" + nuevoMensajeEncriptar);

			cf = Cipher.getInstance("DES/ECB/PKCS5Padding");
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
	public String decrypt(Key llave, String mensajeDescifrar) throws Exception {

		String mensajeOriginal = null;

		Cipher cf;
		try {

			cf = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cf.init(Cipher.DECRYPT_MODE, llave);
			byte[] theCph = cf.doFinal(Base64.decodeBase64(mensajeDescifrar));

			mensajeOriginal = new String(theCph, StandardCharsets.UTF_8);

		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException e) {
			e.printStackTrace();
		}

		return mensajeOriginal;

	}

	@Override
	public byte[] decryptByte(Key llave, String mensajeADescifrar) {
		byte[] mensajeOriginal = null;

		Cipher cf;
		try {

			cf = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cf.init(Cipher.DECRYPT_MODE, llave);
			mensajeOriginal = cf.doFinal(Base64.decodeBase64(mensajeADescifrar));

			//mensajeOriginal = new String(theCph, StandardCharsets.UTF_8);

		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException e) {
			e.printStackTrace();
		}

		return mensajeOriginal;
		
	}

}