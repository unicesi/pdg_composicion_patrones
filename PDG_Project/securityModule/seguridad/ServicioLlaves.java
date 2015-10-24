package seguridad;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import seguridadasincrona.RSAKeyGen;

public class ServicioLlaves {

	public ServicioLlaves() {
		// TODO Auto-generated constructor stub
	}

	public KeyPair generarLlaveAsincrona(String tipoAlgoritmo) {

		RSAKeyGen rsa = new RSAKeyGen();
		KeyPair par = rsa.generarLlaves();

		return par;

	}

	public Key generarLlaveSincrona(String tipoAlgoritmo) {

		return null;

	}

	public Key arreglarLlave(byte[] llave) {

		byte[] encodedKey = null;

		PublicKey publicKey = null;
		try {
			publicKey = KeyFactory.getInstance("RSA").generatePublic(new PKCS8EncodedKeySpec(llave));
		} catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return publicKey;
	}

	public Key arreglarLlave(String llave) {

		byte[] encodedKey = null;

		String nuevo2 = llave.substring(1, llave.length() - 1);
		System.out.println("nuevo 2: " + nuevo2);
		String nuevo3 = nuevo2.replace(" ", "");
		System.out.println("nuevo 3: " + nuevo3);

		StringTokenizer token = new StringTokenizer(nuevo3, ",");

		int cantidad = token.countTokens();

		String[] arreglo = new String[cantidad];

		int contador = 0;

		while (token.hasMoreTokens()) {

			arreglo[contador] = token.nextToken();
			contador++;

		}

		System.out.println("El arreglo queó asi:" + Arrays.toString(arreglo));
		
		byte[] bytes=new byte[arreglo.length];
        for (int i=0; i<arreglo.length; i++) {
            bytes[i]=Byte.parseByte(arreglo[i]);
        }

        System.out.println("El arreglo queó asi:" + Arrays.toString(bytes));
        
		//Key publicKey = null;
		PublicKey publicKey=null;
		try {
			publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(bytes));
		} catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("La llave quedó como:"+Arrays.toString(publicKey.getEncoded()));
		
		return publicKey;
	}

}
