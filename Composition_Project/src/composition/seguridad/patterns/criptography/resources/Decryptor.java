package composition.seguridad.patterns.criptography.resources;

import java.security.*;

import composition.seguridad.patterns.criptography.asimetric.RSAAlgorithm;
import composition.seguridad.patterns.criptography.simetric.AESAlgorithm;
import composition.seguridad.patterns.criptography.simetric.DESAlgorithm;

public class Decryptor implements IDecryptor {

	private IAlgorithm algoritmo;

	public Decryptor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Message decrypt(AKey llave, EncryptedMessage mensajeADescifrar) throws Exception {
		System.out.println("Entró al decryptor");

		Key laLlave = llave.getLlave();
		String tipoAlgoritmo = laLlave.getAlgorithm();
		Message mensajeDesencriptado = new Message();

		if (TiposAlgoritmosCifrado.AES.equals(tipoAlgoritmo)) {
			System.out.println("Seleccionó AES");
			algoritmo = new AESAlgorithm();

		} else {
			if (TiposAlgoritmosCifrado.DES.equals(tipoAlgoritmo)) {
				System.out.println("Seleccionó DES");
				algoritmo = new DESAlgorithm();
			} else {
				if (TiposAlgoritmosCifrado.RSA.equals(tipoAlgoritmo)) {
					System.out.println("Seleccionó RSA");
					algoritmo = new RSAAlgorithm();
				}
			}

		}

		try {
			mensajeDesencriptado = algoritmo.decrypt(llave, mensajeADescifrar);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mensajeDesencriptado;
	}
}
