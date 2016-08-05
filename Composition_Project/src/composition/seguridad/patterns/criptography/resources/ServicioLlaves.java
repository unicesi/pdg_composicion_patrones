package composition.seguridad.patterns.criptography.resources;

import java.security.Key;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.crypto.KeyGenerator;
import composition.seguridad.patterns.criptography.asimetric.RSAKeyGen;

public class ServicioLlaves {

	public ServicioLlaves() {
		// TODO Auto-generated constructor stub
	}

	public KeyPair generarLlaveAsimetrica(String tipoAlgoritmo) {

		RSAKeyGen rsa = new RSAKeyGen();
		KeyPair par = rsa.generarLlaves();

		return par;

	}

	public Key generarLlaveSimetrica(String tipoAlgoritmo) throws Exception {

		Key key = null;
		KeyGenerator keyGenerator = null;

		if (tipoAlgoritmo == "AES") {
			keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128);
			key = keyGenerator.generateKey();

		} else {

			if (tipoAlgoritmo == "DES") {
				keyGenerator = KeyGenerator.getInstance("DES");
				keyGenerator.init(56);
				key = keyGenerator.generateKey();

			}

		}
		return key;

	}

	public RSAPublicKey ObtenerLlavePublica(KeyPair parLlaves) {

		RSAPublicKey publica = (RSAPublicKey) parLlaves.getPublic();
		return publica;

	}

	public RSAPrivateKey ObtenerLlavePrivada(KeyPair parLlaves) {

		RSAPrivateKey publica = (RSAPrivateKey) parLlaves.getPrivate();
		return publica;

	}

	public byte[] arreglarLlave(String llave) {
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

		byte[] bytes = new byte[arreglo.length];
		for (int i = 0; i < arreglo.length; i++) {
			bytes[i] = Byte.parseByte(arreglo[i]);
		}
		System.out.println("El arreglo queó asi:" + Arrays.toString(bytes));

		return bytes;
	}
}
