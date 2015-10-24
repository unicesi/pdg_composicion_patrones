package prueba;

import java.security.Key;
import java.security.KeyPair;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import seguridad.ServicioLlaves;
import seguridadsincrona.*;

public class Prueba {

	private static RSAPublicKey rsaPubKey;
	private static RSAPrivateKey rsaPrivKey;
	private static KeyPair keyPair;

	public static void main(String args[]) {

		System.out.println("Inicia Asimetrica");
		System.out.println("---");

		System.out.println("Se pide la generación de la llave asimetica");

		obtenerLlaveAsimetrica();
		System.out.println("\n");
		System.out.println("La llave Publica es: " + Arrays.toString(rsaPubKey.getEncoded()));
		System.out.println("La llave Privada es: " + Arrays.toString(rsaPrivKey.getEncoded()));
		System.out.println("\n");

		System.out.println("Se pide encriptar la llave publica con un algoritmo X");

		Object[] llaveAsimEncriptada = encriptarLlaveAsimetrica(rsaPubKey, "AES");
		System.out.println("\n");
		Key llaveUsada = (Key) llaveAsimEncriptada[0];
		String llaveEncriptada = "" + llaveAsimEncriptada[1];
		System.out.println("La llave usada para encriptar es: " + Arrays.toString(llaveUsada.getEncoded()));
		System.out.println("La llave encriptada es: " + llaveEncriptada);
		System.out.println("\n");

		System.out.println("Bajo algun mecanismo se envía la llave pública al cliente");
		System.out.println("\n");

		System.out.println("El cliente ahora desencripta la llave");
		String llaveDesencriptada = desencriptar(llaveAsimEncriptada);
		System.out.println("La llave desencriptada es: " + llaveDesencriptada);
		System.out.println("\n");

		RSAPublicKey rsa = (RSAPublicKey) obtenerCodificacionLlave(llaveDesencriptada);

		String stringKey = "";

		byte[] encriptadoAsim = encriptarAsim("Este es el texto a encriptar", rsa);

		desencriptarAsim(encriptadoAsim, rsaPrivKey);

		System.out.println("\n");

		System.out.println("-----------------");
		System.out.println("Empieza simétrica");

		Object[] encriptado = encriptar("DES", "Este es el mensaje a encriptar");
		System.out.println("-----------------");
		String mensajeFinalDesencriptado = desencriptar(encriptado);
		System.out.println("Este es el mensaje final: " + mensajeFinalDesencriptado);

	}

	public static RSAPublicKey obtenerCodificacionLlave(String llaveCodificada) {

		ServicioLlaves servicio = new ServicioLlaves();
		PublicKey key = (PublicKey) servicio.arreglarLlave(llaveCodificada);
		RSAPublicKey laLlave = (RSAPublicKey) key;

		return laLlave;
	}

	private static byte[] encriptarAsim(String mensaje, RSAPublicKey llavePublica) {

		byte[] cert = seguridadasincrona.Encryptor.encrypt(mensaje, llavePublica);

		return cert;
	}

	private static void desencriptarAsim(byte[] mensajeEncriptado, RSAPrivateKey llavePrivada) {

		System.out.println("El mensaje encriptado es:" + Arrays.toString(mensajeEncriptado));

		String mensaje = seguridadasincrona.Decryptor.decrypt(mensajeEncriptado, llavePrivada);
		System.out.println("Desencriptado:" + mensaje);

	}

	public static void obtenerLlaveAsimetrica() {

		ServicioLlaves servicio = new ServicioLlaves();

		keyPair = servicio.generarLlaveAsincrona("RSA");
		rsaPubKey = (RSAPublicKey) keyPair.getPublic();
		rsaPrivKey = (RSAPrivateKey) keyPair.getPrivate();

	}

	public static Object[] encriptarLlaveAsimetrica(RSAPublicKey rsaPK, String tipoAlgoritmo) {
		IEncryptor iEncryptor = new Encryptor();

		byte[] llave = rsaPK.getEncoded();
		String strLlave = Arrays.toString(llave);

		Object[] arreglo = null;

		try {
			arreglo = iEncryptor.encrypt(strLlave, tipoAlgoritmo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return arreglo;

	}

	public static Object[] encriptar(String tipoAlgoritmo, String mensaje) {

		IEncryptor iEncryptor = new Encryptor();

		System.out.println("Empieza encriptación");

		try {
			Object[] arreglo = iEncryptor.encrypt(mensaje, tipoAlgoritmo);

			Key llave2 = (Key) arreglo[0];
			System.out.println("La llave usada es: " + Arrays.toString(llave2.getEncoded()));
			System.out.println("El mensaje cifrado es: " + arreglo[1].toString());

			return arreglo;

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

			return null;
		}

	}

	public static String desencriptar(Object[] pareja) {

		System.out.println("Empieza desencriptaciòn");
		IDecryptor decriptor = new Decryptor();
		Key llave = (Key) pareja[0];
		String mensaje = pareja[1].toString();
		String tipo = llave.getAlgorithm();
		String respuesta = "";
		try {
			respuesta = (String) decriptor.decrypt(llave, mensaje);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return respuesta;

	}
}
