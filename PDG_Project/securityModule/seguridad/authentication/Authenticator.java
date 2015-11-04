package seguridad.authentication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import seguridad.Authenticationinformation;
import seguridad.Subject;

@Stateless
public class Authenticator {

	/**
	 * Sujetos que identifica un autenticador
	 */
	private HashMap<String, Subject> subjects;

	/**
	 * DAO Authenticator para acceder a la información que autentica.
	 */
	@EJB
	private AuthenticatorDAO authenticatorDAO;

	/**
	 * Collection que tiene la información de todos los sujetos autenticados al
	 * sistema que utilice Authenticator.
	 */
	private Collection<Authenticationinformation> authenticationInformations;

	/**
	 * Información de todas las credenciales de los sujetos dentro del sistema.
	 */
	private HashMap<String, ProofOfID> proofsOfID;

	public Authenticator() {

		authenticatorDAO = new AuthenticatorDAO();
		// authenticationInformations =
		// authenticatorDAO.findByIdSystem(idSystem);
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
	public ProofOfID login(String codSubject, String password) throws AuthenticatorException {
		ProofOfID proofOfID = new ProofOfID(codSubject, false);
		Authenticationinformation authenticationInformation = buscarPorCodSubject_Password(codSubject, password);
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
	public Authenticationinformation buscarPorCodSubject_Password(String codSubject, String password) {
		Iterator<Authenticationinformation> iter = getAuthenticationInformations().iterator();
		Authenticationinformation authenticationinformationRetorno = null;
		Authenticationinformation authenticationinformationActual = null;
		while (iter.hasNext()) {
			// System.out.println("Entra al while");
			authenticationinformationActual = iter.next();
			if (String.valueOf(authenticationinformationActual.getCodSubject()).equals(codSubject)
					&& authenticationinformationActual.getPassword().equals(password)) {
				// System.out.println("Entra el if");
				authenticationinformationRetorno = authenticationinformationActual;
				// System.out.println("Retorno: " +
				// authenticationinformationRetorno.toString());
				// System.out.println("Retorno: " +
				// authenticationinformationRetorno.toString());
				// System.out.println("Retorno: " +
				// authenticationinformationRetorno.toString());
				return authenticationinformationRetorno;
			} else {
				// System.out.println("Entró al else");

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
	 * Autentica a un sujeto en el sistema.
	 * 
	 * @param subject-Sujeto
	 *            a autenticar.
	 * @return true- si lo autentica, false- si no lo autentica.
	 */
	public boolean autenticar(Subject subject) {

		Authenticationinformation authenticationinformation = new Authenticationinformation(
				Long.parseLong(subject.getCodSubject()), subject.getPassword(), "1");
		try {
			authenticationInformations.add(authenticationinformation);
			System.out.println("FALTA AGREGARLO A LA BASE DE DATOS HABILITANDO LA PORCIÓN DE CÓDIGO SIGUIENTE.");
			// TODO authenticatorDAO.create(authenticationinformation);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	

}
