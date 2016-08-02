package composition.seguridad.patterns.authenticator;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import composition.seguridad.patterns.criptography.resources.AKey;
import composition.seguridad.patterns.criptography.resources.Decryptor;
import composition.seguridad.patterns.criptography.resources.EncryptedMessage;
import composition.seguridad.patterns.criptography.resources.Encryptor;
import composition.seguridad.patterns.criptography.resources.IDecryptor;
import composition.seguridad.patterns.criptography.resources.IEncryptor;
import composition.seguridad.patterns.criptography.resources.Message;
import composition.seguridad.patterns.criptography.resources.ServicioLlaves;
import composition.seguridad.patterns.criptography.resources.TiposAlgoritmosCifrado;
import composition.seguridad.patterns.resources.Subject;
import composition.tienda.entities.Authenticationinformation;

@Stateless
public class Authenticator {

	/**
	 * LLave para cifrar y descifrar passwords de sujetos autenticados.
	 */
	private Key llave;

	/**
	 * Tipo de algoritmo de cifrado definido para AES.
	 */
	public static final String TIPO_ALGORITMO = TiposAlgoritmosCifrado.AES;

	/**
	 * Encriptor del password.
	 */
	private IEncryptor encryptor;

	/**
	 * Decriptor del password.
	 */
	private IDecryptor decryptor;

	/**
	 * Sujetos que identifica un autenticador
	 */
	private HashMap<String, Subject> subjects;

	/**
	 * DAO Authenticator para acceder a la información que autentica.
	 */
	@EJB
	AuthenticatorDAO authenticatorDAO;

	/**
	 * Collection que tiene la información de todos los sujetos autenticados al
	 * sistema que utilice Authenticator.
	 */
	private Collection<Authenticationinformation> authenticationInformations;

	/**
	 * Información de todas las credenciales de los sujetos dentro del sistema.
	 */
	private HashMap<String, ProofOfID> proofsOfID;

	/**
	 * Servicio de generación de llaves.
	 */
	private ServicioLlaves servicioLlaves;

	public Authenticator() {
		servicioLlaves = new ServicioLlaves();
		try {
			llave = servicioLlaves.generarLlaveSimetrica(TIPO_ALGORITMO);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		encryptor = new Encryptor();
		decryptor = new Decryptor();
		// authenticatorDAO = new AuthenticatorDAO();
		// authenticationInformations = authenticatorDAO.findAll();
		authenticationInformations = new ArrayList<Authenticationinformation>();
		proofsOfID = new HashMap<String, ProofOfID>();
	}

	public AuthenticatorDAO getAuthenticatorDAO() {
		return authenticatorDAO;
	}

	public void setAuthenticatorDAO(AuthenticatorDAO authenticatorDAO) {
		this.authenticatorDAO = authenticatorDAO;
	}

	public Collection<Authenticationinformation> getAuthenticationInformations() {
		return authenticationInformations;
	}

	public void setAuthenticationInformations(Collection<Authenticationinformation> authenticationInformations) {
		this.authenticationInformations = authenticationInformations;
	}

	public HashMap<String, ProofOfID> getProofsOfID() {
		return proofsOfID;
	}

	public void setProofsOfID(HashMap<String, ProofOfID> proofsOfID) {
		this.proofsOfID = proofsOfID;
	}

	public HashMap<String, Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(HashMap<String, Subject> subjects) {
		this.subjects = subjects;
	}

	public IEncryptor getEncryptor() {
		return encryptor;
	}

	public void setEncryptor(IEncryptor encryptor) {
		this.encryptor = encryptor;
	}

	public IDecryptor getDecryptor() {
		return decryptor;
	}

	public void setDecryptor(IDecryptor decryptor) {
		this.decryptor = decryptor;
	}

	public static String getTipoAlgoritmo() {
		return TIPO_ALGORITMO;
	}

	public Key getLlave() {
		return llave;
	}

	public void setLlave(Key llave) {
		this.llave = llave;
	}
	// ===MÉTODOS LOGICA DE NEGOCIO==//
	// ==============================//

	private boolean isAuthenticado(Authenticationinformation authenticationinformation) {
		return authenticationinformation.getAutenticado().equalsIgnoreCase("1");
	}

	/**
	 * Retorna un ProofOfID para un subject especificado.
	 * 
	 * @param codSubject
	 * @return proofOfID(true)-Si está autenticado en el sistema,
	 *         ProofOfID(false) si no lo está.
	 * @throws AuthenticatorException-
	 *             si no está autenticado en el sistema.
	 */
	public ProofOfID login(String codSubject, EncryptedMessage password) throws AuthenticatorException {
		AKey key = new AKey();
		key.setKey(llave);
		Message passwordM = null;
		try {
			passwordM = decryptor.decrypt(key, password);
			System.out.println("El mensaje que retorna el decryptor es:" + passwordM.getMensaje());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String contrasenia = ((String) passwordM.getMensaje()).trim();
		System.out.println("La contraseña que se saca en el login es:" + contrasenia);
		ProofOfID proofOfID = new ProofOfID(codSubject, false);
		Authenticationinformation authenticationInformation = buscarPorCodSubject_Password(codSubject, contrasenia,
				key);
		System.out.println("Authentication en el login es: " + authenticationInformation);
		// System.out.println("Buscado:" + authenticationInformation);
		if (authenticationInformation == null) {
			// TODO proofsOfID.put(codSubject, proofOfID);
			throw new AuthenticatorException("No se encuentra autenticado en el sistema.");
		} else {
			if (isAuthenticado(authenticationInformation)) {
				proofOfID.setPermitido(true);
				proofsOfID.put(codSubject, proofOfID);
			}
		}
		return proofOfID;
	}

	/**
	 * Busca la información de autenticación del sujeto con ese código dentro
	 * del sistema que utiliza éste autenticador.
	 * 
	 * @param codSubject
	 * @return Authenticationinformation.
	 */
	public Authenticationinformation buscarPorCodSubject_Password(String codSubject, String password, AKey llave) {
		Iterator<Authenticationinformation> iter = getAuthenticationInformations().iterator();
		System.out.println("El iterador de AuInformations:" + iter);
		Authenticationinformation authenticationinformationRetorno = null;
		Authenticationinformation authenticationinformationActual = null;
		while (iter.hasNext()) {
			// System.out.println("Entra al while");
			authenticationinformationActual = iter.next();
			System.out.println("El actual es:" + authenticationinformationActual.toString());
			byte[] bytesDelPassword = servicioLlaves.arreglarLlave(authenticationinformationActual.getPassword());
			EncryptedMessage mensajeADescifrar = new EncryptedMessage();
			mensajeADescifrar.setMensaje(bytesDelPassword);
			Message passwordMDesencriptado = null;
			try {
				passwordMDesencriptado = decryptor.decrypt(llave, mensajeADescifrar);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String passwordDesencriptado = (String) passwordMDesencriptado.getMensaje();
			if (String.valueOf(authenticationinformationActual.getCodSubject()).equals(codSubject)
					&& passwordDesencriptado.equals(password)) {
				// System.out.println("Entra el if");
				authenticationinformationRetorno = authenticationinformationActual;

				System.out.println("Retorno: " + authenticationinformationRetorno.toString());
				// System.out.println("Retorno: " +
				// authenticationinformationRetorno.toString());
				// System.out.println("Retorno: " +
				// authenticationinformationRetorno.toString());
				return authenticationinformationRetorno;
			} else {
				System.out.println("Entró al else");

				continue;
				// System.out.println("Actual: " +
				// authenticationinformationActual.toString());

			}

		}

		return authenticationinformationRetorno;
	}

	/**
	 * Inhabilita a un ProofOfID de un sujeto dentro del sistema.
	 * 
	 * @param codSubject
	 */
	public void inhabilitarSubject(String codSubject) throws AuthenticatorException {
		if (proofsOfID.containsKey(codSubject)) {
			proofsOfID.get(codSubject).setPermitido(false);
		} else {
			throw new AuthenticatorException("No existen credenciales autenticadoras para el sujeto.");
		}
	}

	/**
	 * Habilita a un ProofOfID de un sujeto dentro del sistema.
	 * 
	 * @param codSubject
	 */
	public void habilitarSubject(String codSubject) throws AuthenticatorException {
		if (proofsOfID.containsKey(codSubject)) {
			proofsOfID.get(codSubject).setPermitido(true);
		} else {
			throw new AuthenticatorException("No existen credenciales autenticadoras para el sujeto.");
		}
	}

	/**
	 * Verifica si un sujeto está autenticado y permitido.
	 * 
	 * @param subject
	 * @return true- si está autenticado y permitido, false- si no lo está.
	 */
	public boolean estaAutenticadoPermitido(Subject subject) {
		return (proofsOfID.containsKey(subject.getCodSubject())
				&& proofsOfID.get(subject.getCodSubject()).isPermitido()) ? true : false;
	}

	/**
	 * Autentica a un sujeto en el sistema. Encripta el password.
	 * 
	 * @param subject-Sujeto
	 *            a autenticar.
	 * @return true- si lo autentica, false- si no lo autentica.
	 */
	public boolean autenticar(Subject subject) {

		String passwordCifrado = Arrays.toString((byte[]) cifrarPassword(subject.getPassword(), llave));

		Authenticationinformation authenticationinformation = new Authenticationinformation(
				Long.parseLong(subject.getCodSubject()), passwordCifrado, "1");
		try {
			authenticationInformations.add(authenticationinformation);
			System.out.println("Agrega el sujeto al authentication informations de forma local.");
			System.out.println("FALTA CREARLO EN BD");
			// authenticatorDAO.create(authenticationinformation);
			// System.out.println("Agrega el sujeto al authentication
			// informations en la base de datos.");
			// setAuthenticationInformations(authenticatorDAO.findAll());
			// System.out.println("Actualiza los authenticationinformations.");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * Cifra el password del sujeto a guardar.
	 * 
	 * @param password
	 * @param llave
	 * @return byte[] password cifrado.
	 */
	private byte[] cifrarPassword(String password, Key llave) {
		AKey key = new AKey();
		key.setKey(llave);
		Message passwordMessage = new Message();
		passwordMessage.setMensaje(password);
		EncryptedMessage passwordEncriptado = null;
		try {
			passwordEncriptado = encryptor.encryptConLlave(passwordMessage, key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		byte[] passwordCifrado = (byte[]) passwordEncriptado.getMensaje();
		return passwordCifrado;
	}

}
