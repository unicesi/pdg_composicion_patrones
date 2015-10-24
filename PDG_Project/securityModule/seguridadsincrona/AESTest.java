package seguridadsincrona;

import java.security.Key;//Modificado
import java.security.NoSuchAlgorithmException;
import java.security.spec.*;
import java.util.Arrays;

import javax.crypto.*;
import javax.crypto.spec.*;

class AESTest {
	public static void main(String[] args) {
		String test = "1";
		// Modificado
		KeyGenerator keyGenerator;
		Key ks=null;

		try {
			keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128);
			ks = keyGenerator.generateKey();
			System.out.println("La llave inicial es:" + ks.toString());
			System.out.println("La llave inicial es:" + Arrays.toString(ks.getEncoded()));
			String algoritmo = ks.getAlgorithm();
			System.out.println("algoritmo usado=" + algoritmo);

		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// -----------------Modificado hasta aqui--------

		try {
			byte[] theKey = null;
			byte[] theMsg = null;
			//byte[] theExp = null;
			if (test.equals("1")) {
				// theKey = hexToBytes("2b7e151628aed2a6abf7158809cf4f3c");
				theKey = ks.getEncoded();
				theMsg = hexToBytes("7b0c785e27e8ad3f8223207104725dd4");
				//theExp = hexToBytes("f69f2445df4f9b17ad2b417be66c3710");
			} else if (test.equals("2")) {
				theKey = hexToBytes("38627974656B6579"); // "8bytekey"
				theMsg = hexToBytes("6D6573736167652E"); // "message."
				//theExp = hexToBytes("7CF45E129445D451");
			} else {
				System.out.println("Usage:");
				System.out.println("java JceSunDesTest 1/2");
				return;
			}
			ks = new SecretKeySpec(theKey, 0, 16, "AES");
			// KeySpec ks = new SecretKeySpec(theKey,"AES");
			// SecretKeyFactory kf
			// = SecretKeyFactory.getInstance("AES/ECB/PKCS5Padding");

			// SecretKey ky = kf.generateSecret(ks);
			Cipher cf = Cipher.getInstance("AES/ECB/NoPadding");
			cf.init(Cipher.DECRYPT_MODE, ks);// Modificado
			byte[] theCph = cf.doFinal(theMsg);
			System.out.println("Key     : " + bytesToHex(theKey));
			System.out.println("Message : " + bytesToHex(theMsg));
			System.out.println("Cipher  : " + bytesToHex(theCph));
			//System.out.println("Expected: " + bytesToHex(theExp));
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	public static byte[] hexToBytes(String str) {
		if (str == null) {
			return null;
		} else if (str.length() < 2) {
			return null;
		} else {
			int len = str.length() / 2;
			byte[] buffer = new byte[len];
			for (int i = 0; i < len; i++) {
				buffer[i] = (byte) Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16);
			}
			return buffer;
		}

	}

	public static String bytesToHex(byte[] data) {
		if (data == null) {
			return null;
		} else {
			int len = data.length;
			String str = "";
			for (int i = 0; i < len; i++) {
				if ((data[i] & 0xFF) < 16)
					str = str + "0" + java.lang.Integer.toHexString(data[i] & 0xFF);
				else
					str = str + java.lang.Integer.toHexString(data[i] & 0xFF);
			}
			return str.toUpperCase();
		}
	}
}