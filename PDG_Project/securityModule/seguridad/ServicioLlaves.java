package seguridad;

import java.security.Key;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import javax.crypto.KeyGenerator;
import seguridadasincrona.RSAKeyGen;

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

}
