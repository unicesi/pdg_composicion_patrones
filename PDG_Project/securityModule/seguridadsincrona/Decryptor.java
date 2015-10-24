package seguridadsincrona;

import java.security.*;

import seguridad.IAlgorithm;

public class Decryptor implements IDecryptor {

	private IAlgorithm algoritmo;

	public Decryptor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String decrypt(Key llave, String mensajeADescifrar) throws Exception {
		
		String tipoAlgoritmo= llave.getAlgorithm();
		String mensajeEncriptado = "";
		
		if (TiposAlgoritmosCifrado.AES.equals(tipoAlgoritmo)){
			
			algoritmo=new AESAlgorithm();
			
		}else{
			if (TiposAlgoritmosCifrado.DES.equals(tipoAlgoritmo)){
				algoritmo=new DESAlgorithm();
			}
		}

		try {
			mensajeEncriptado = algoritmo.decrypt(llave, mensajeADescifrar);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mensajeEncriptado;
	}

	@Override
	public byte[] decryptByte(Key llave, String mensajeADescifrar) {
		
		String tipoAlgoritmo= llave.getAlgorithm();
		byte[] mensajeEncriptado = null;;
		
		if (TiposAlgoritmosCifrado.AES.equals(tipoAlgoritmo)){
			
			algoritmo=new AESAlgorithm();
			
		}else{
			if (TiposAlgoritmosCifrado.DES.equals(tipoAlgoritmo)){
				algoritmo=new DESAlgorithm();
			}
		}

		try {
			mensajeEncriptado = algoritmo.decryptByte(llave, mensajeADescifrar);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mensajeEncriptado;
		
		
	}

}
