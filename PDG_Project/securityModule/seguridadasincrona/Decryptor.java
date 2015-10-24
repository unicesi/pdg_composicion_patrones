package seguridadasincrona;

import java.security.PrivateKey;

import javax.crypto.Cipher;

public class Decryptor implements IDecryptor {

	public Decryptor() {
		// TODO Auto-generated constructor stub
	}

	public static String decrypt(byte[] text, PrivateKey key) {
		byte[] dectyptedText = null;
		System.out.println("Empieza la desencriptación");
		try {
			final Cipher cipher = Cipher.getInstance("RSA");

			cipher.init(Cipher.DECRYPT_MODE, key);
			dectyptedText = cipher.doFinal(text);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		System.out.println("Termina la desencriptación");
		return new String(dectyptedText);
	}

}
