package seguridadasincrona;

import java.security.PublicKey;

import javax.crypto.Cipher;

public class Encryptor implements IEncryptor {

	public Encryptor() {
		// TODO Auto-generated constructor stub
	}
	
	
public static byte[] encrypt(String text, PublicKey key) {
		
		System.out.println("Empieza la encriptación");
		System.out.println("Se pasó el texto y la llave pública");
		byte[] cipherText = null;
		try {
			final Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			cipherText = cipher.doFinal(text.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Termina la encriptación");
		return cipherText;
	}

}
