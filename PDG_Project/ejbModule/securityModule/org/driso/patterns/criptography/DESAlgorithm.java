package org.driso.patterns.criptography;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class DESAlgorithm extends Algorithm {

	public DESAlgorithm() {
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
		String mensajeCifrado = null;
		try {
			theKey = hexToBytes(llave);
			mensajeAEncriptar = hexToBytes(mensajeEncriptar);
			KeySpec ks = new DESKeySpec(theKey);
			SecretKeyFactory kf = SecretKeyFactory.getInstance("DES");
			SecretKey ky = kf.generateSecret(ks);
			Cipher cf = Cipher.getInstance("DES/ECB/NoPadding");
			cf.init(Cipher.ENCRYPT_MODE, ky);
			byte[] theCph = cf.doFinal(mensajeAEncriptar);
			System.out.println("Key     : " + bytesToHex(theKey));
			System.out.println("Message : " + bytesToHex(mensajeAEncriptar));
			mensajeCifrado = bytesToHex(theCph);
			System.out.println("Cipher  : " + bytesToHex(theCph));
			theKey = hexToBytes(llave);
			mensajeAEncriptar = hexToBytes(mensajeEncriptar);

		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
				| BadPaddingException e) {
			e.printStackTrace();
		}
		return mensajeCifrado;
	}

	@Override
	public String decrypt(String llave, String mensajeDescifrar) throws Exception {
		String mensajeOriginal = null;
		try {
			theKey = hexToBytes(llave);
			mensajeADescifrar = hexToBytes(mensajeDescifrar);
			KeySpec ks = new DESKeySpec(theKey);
			SecretKeyFactory kf = SecretKeyFactory.getInstance("DES");
			SecretKey ky = kf.generateSecret(ks);
			Cipher cf = Cipher.getInstance("DES/ECB/NoPadding");
			cf.init(Cipher.DECRYPT_MODE, ky);
			byte[] theCph = cf.doFinal(mensajeADescifrar);
			System.out.println("Key     : " + bytesToHex(theKey));
			System.out.println("Message : " + bytesToHex(mensajeADescifrar));
			mensajeOriginal = bytesToHex(theCph);
			System.out.println("Cipher  : " + bytesToHex(theCph));

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