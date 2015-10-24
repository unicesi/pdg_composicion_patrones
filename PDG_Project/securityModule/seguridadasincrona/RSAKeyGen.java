package seguridadasincrona;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;


public class RSAKeyGen {

	public RSAKeyGen() {
		// TODO Auto-generated constructor stub
	}
	
	
	public KeyPair generarLlaves(){
		
		KeyPairGenerator keyGen;
		KeyPair keys=null;
		try {
			keyGen = KeyPairGenerator.getInstance("RSA");

			keyGen.initialize(1024);
			keys = keyGen.generateKeyPair();

			//RSAPrivateKey prvKey = (RSAPrivateKey) keys.getPrivate();
			//RSAPublicKey pblKey = (RSAPublicKey) keys.getPublic();
			
			System.out.println("se generaron las llaves");
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return keys;
		
		
	}

}
