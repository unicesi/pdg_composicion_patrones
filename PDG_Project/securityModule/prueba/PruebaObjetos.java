package prueba;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;

import Interfaces.IDecryptor;
import Interfaces.IEncryptor;
import seguridad.AKey;
import seguridad.Decryptor;
import seguridad.EncryptedMessage;
import seguridad.Encryptor;
import seguridad.Message;
import seguridad.ServicioLlaves;

public class PruebaObjetos {

	private static ServicioLlaves servicio;
	private static IEncryptor iEncryptor = new Encryptor();
	private static IDecryptor iDecryptor = new Decryptor();

	public static void main(String args[]) {

		servicio = new ServicioLlaves();

		System.out.println("Inicia Asimetrica");
		System.out.println("---");

		System.out.println("Se pide la generación de la llave asimetica");

		KeyPair parLlaves = servicio.generarLlaveAsimetrica("RSA");
		RSAPublicKey llavePublicaRSA = servicio.ObtenerLlavePublica(parLlaves);
		RSAPrivateKey llavePrivadaRSA = servicio.ObtenerLlavePrivada(parLlaves);
		AKey publica = new AKey();
		publica.setKey(llavePublicaRSA);
		AKey privada = new AKey();
		privada.setKey(llavePrivadaRSA);

		System.out.println("La llave Publica es: " + Arrays.toString(llavePublicaRSA.getEncoded()));
		System.out.println("La llave Privada es: " + Arrays.toString(llavePrivadaRSA.getEncoded()));
		System.out.println("\n");

		Message mensaje = new Message();
		mensaje.setMensaje(llavePublicaRSA);

		System.out.println("Se pide encriptar la llave publica con un algoritmo X");

		EncryptedMessage mensajeEncriptado = new EncryptedMessage();
		try {
			mensajeEncriptado = iEncryptor.encrypt(mensaje, "AES");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Object[] arreglo = (Object[]) mensajeEncriptado.getMensaje();

		System.out.println("\n");
		AKey llaveUsada = (AKey) arreglo[0];

		EncryptedMessage nuevoMensajeEncriptado = new EncryptedMessage();
		nuevoMensajeEncriptado.setMensaje(arreglo[1]);

		System.out.println("\n");

		System.out.println("Bajo algun mecanismo se envía la llave pública al cliente");
		System.out.println("\n");

		System.out.println("El cliente ahora desencripta la llave");
		Message mensajeDesencriptado = new Message();
		try {
			mensajeDesencriptado = iDecryptor.decrypt(llaveUsada, nuevoMensajeEncriptado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\n");

		RSAPublicKey rsa = (RSAPublicKey) mensajeDesencriptado.getMensaje();
		AKey publicaRSA = new AKey();
		publicaRSA.setKey(rsa);

		System.out.println("\n");
		System.out.println("Se prueba encriptar el objeto Perro con AES");

		Perro perro = new Perro();
		System.out.println("Nombre del perro:" + perro.getNombre());

		Message perroAEncriptar = new Message();
		perroAEncriptar.setMensaje(perro);
		EncryptedMessage perroEncriptado = null;
		try {
			perroEncriptado = iEncryptor.encrypt(perroAEncriptar, "AES");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Se prueba desencriptar el objeto Perro");
		Object[] arregloPerro = (Object[]) perroEncriptado.getMensaje();
		perroEncriptado.setMensaje(arregloPerro[1]);
		Message perroDesencriptado = null;
		try {
			perroDesencriptado = iDecryptor.decrypt((AKey) arregloPerro[0], perroEncriptado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Perro nuevoPerro = (Perro) perroDesencriptado.getMensaje();

		System.out.println("Nombre" + nuevoPerro.getNombre());

		System.out.println("\n");
		System.out.println("\n");

		System.out.println("Ahora se encripta asimetricamente al perro con las llaves generadas (Publica)");

		try {
			perroEncriptado = iEncryptor.encrypt(perroAEncriptar, publicaRSA);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Ahora se desencripta asimetricamente al perro con las llaves generadas(Privada)");

		try {
			perroDesencriptado = iDecryptor.decrypt(privada, perroEncriptado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		nuevoPerro = (Perro) perroDesencriptado.getMensaje();

		System.out.println("\n");

		System.out.println("Y el resultado es:");
		System.out.println("");
		System.out.println("Nombre del perro: " + nuevoPerro.getNombre());

	}

}
