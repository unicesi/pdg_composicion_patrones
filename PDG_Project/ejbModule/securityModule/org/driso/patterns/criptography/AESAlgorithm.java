package org.driso.patterns.criptography;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.*;
import javax.crypto.spec.*;

public class AESAlgorithm extends Algorithm {

	private KeyGenerator keyGenerator;
	private Key key;

	public AESAlgorithm() {
		super();

	}

	public byte[] hexToBytes(String str) {
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

	public String bytesToHex(byte[] data) {
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

	@Override
	public String encrypt(String llave, String mensajeEncriptar) throws Exception {
		keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128);
		key = keyGenerator.generateKey();

		String mensajeCifrado = null;
		Cipher cf;
		try {
			theKey = hexToBytes(llave);
			mensajeAEncriptar = hexToBytes(mensajeEncriptar);
			key = new SecretKeySpec(theKey, 0, 16, "AES");
			cf = Cipher.getInstance("AES/ECB/NoPadding");
			cf.init(Cipher.ENCRYPT_MODE, key);
			byte[] theCph = cf.doFinal(mensajeAEncriptar);
			System.out.println("Key     : " + bytesToHex(theKey));
			System.out.println("Message : " + bytesToHex(mensajeAEncriptar));
			System.out.println("Cipher  : " + bytesToHex(theCph));
			mensajeCifrado = bytesToHex(theCph);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException e) {
			e.printStackTrace();
		}

		return mensajeCifrado;
	}

	@Override
	public String decrypt(String llave, String mensajeDescifrar) throws Exception {
		keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128);
		key = keyGenerator.generateKey();

		String mensajeOriginal = null;

		Cipher cf;
		try {
			theKey = hexToBytes(llave);
			mensajeADescifrar = hexToBytes(mensajeDescifrar);
			key = new SecretKeySpec(theKey, 0, 16, "AES");
			cf = Cipher.getInstance("AES/ECB/NoPadding");
			cf.init(Cipher.DECRYPT_MODE, key);
			byte[] theCph = cf.doFinal(mensajeADescifrar);
			System.out.println("Key     : " + bytesToHex(theKey));
			System.out.println("Message : " + bytesToHex(mensajeADescifrar));
			System.out.println("Cipher  : " + bytesToHex(theCph));
			mensajeOriginal = bytesToHex(theCph);
			System.out.println("Expected: " + bytesToHex(esperado));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException e) {
			e.printStackTrace();
		}

		return mensajeOriginal;
	}

	@Override
	public String obtenerKey() {
		return bytesToHex(theKey);
	}

}
