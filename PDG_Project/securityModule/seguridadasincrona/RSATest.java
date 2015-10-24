package seguridadasincrona;


import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.Cipher;

/**
 * @author JavaDigest
 * 
 */
public class RSATest {


	private static RSAPrivateKey priv = null;
	private static RSAPublicKey pub = null;
	
	private static RSAPublicKey otraLlave=null;

	public static void generateKey() {

		System.out.println("Entró a generar las llaves");
		KeyPairGenerator keyGen;
		try {
			keyGen = KeyPairGenerator.getInstance("RSA");

			keyGen.initialize(1024);
			 KeyPair key = keyGen.generateKeyPair();

			RSAPrivateKey prvKey = (RSAPrivateKey) key.getPrivate();
			RSAPublicKey pblKey = (RSAPublicKey) key.getPublic();
			
			priv=prvKey;
			pub=pblKey;
			System.out.println("se generaron las llaves");
			
			
			
			//Se genera una llave adicional, se saca la pública, y si se pasa como parámetro
			// más adelante en el código, con la privada anterior, se genera un error de desencriptación.
			key= keyGen.generateKeyPair();
			otraLlave= (RSAPublicKey) key.getPublic();

			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public static boolean areKeysPresent() {


		if (priv!=null && pub!=null) {
			System.out.println("True");
			return true;
				
		}
		System.out.println("False");
		return false;
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
	
	public static String decrypt(byte[] text, PrivateKey key) {
		byte[] dectyptedText = null;
		System.out.println("Empieza la desencriptación");
		try {
			// get an RSA cipher object and print the provider
			final Cipher cipher = Cipher.getInstance("RSA");

			// decrypt the text using the private key
			cipher.init(Cipher.DECRYPT_MODE, key);
			dectyptedText = cipher.doFinal(text);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		System.out.println("Termina la desencriptación");
		return new String(dectyptedText);
	}

	
	public static void main(String[] args) {

		try {

			System.out.println("Entró a verificar las llaves");
			if (!areKeysPresent()) {
				System.out.println("ELas llaves no existen");
				
				generateKey();
			}

			System.out.println("Sigue la ejecución");
			
			final String originalText = "Text to be encrypted ";
		
			
			final PublicKey publicKey = pub;
			//final byte[] cipherText = encrypt(originalText, publicKey);
			final byte[] cipherText = encrypt(originalText, publicKey);

			
		
			final PrivateKey privateKey = priv;
			final String plainText = decrypt(cipherText, privateKey);

	
			System.out.println("Original: " + originalText);
			System.out.println("Encrypted: " + cipherText.toString());
			System.out.println("Decrypted: " + plainText);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
