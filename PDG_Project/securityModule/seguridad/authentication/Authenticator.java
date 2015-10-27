package seguridad.authentication;

import java.util.Collection;
import java.util.HashMap;

public class Authenticator {

	/**
	 * DAO Authenticator para acceder a la información que autentica.
	 */
	private AuthenticatorDAO authenticatorDAO;

	/**
	 * Collection que tiene la información de todos los sujetos autenticados al
	 * sistema que utilice Authenticator.
	 */
	private Collection<AuthenticationInformation> authenticationInformations;

	/**
	 * Información de todas las credenciales de los sujetos dentro del sistema.
	 */
	private HashMap<Integer, ProofOfID> proofsOfID;

	public Authenticator() {
		authenticatorDAO = new AuthenticatorDAO();
		authenticationInformations = authenticatorDAO.findAll();
		proofsOfID = new HashMap<Integer, ProofOfID>();
	}

	public AuthenticatorDAO getAuthenticatorDAO() {
		return authenticatorDAO;
	}

	public void setAuthenticatorDAO(AuthenticatorDAO authenticatorDAO) {
		this.authenticatorDAO = authenticatorDAO;
	}

	public Collection<AuthenticationInformation> getAuthenticationInformations() {
		return authenticationInformations;
	}

	public void setAuthenticationInformations(Collection<AuthenticationInformation> authenticationInformations) {
		this.authenticationInformations = authenticationInformations;
	}

	// ===MÉTODOS LOGICA DE NEGOCIO==//
	// ==============================//

	public HashMap<Integer, ProofOfID> getProofsOfID() {
		return proofsOfID;
	}

	public void setProofsOfID(HashMap<Integer, ProofOfID> proofsOfID) {
		this.proofsOfID = proofsOfID;
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
	public ProofOfID login(Integer codSubject) throws AuthenticatorException {
		ProofOfID proofOfID = new ProofOfID(codSubject, false);
		AuthenticationInformation authenthicationInformation = authenticatorDAO.findByCodSubject(codSubject);
		if (authenthicationInformation == null) {
			proofsOfID.put(codSubject, proofOfID);
			throw new AuthenticatorException("No se encuentra autenticado en el sistema.");
		} else {
			if (authenticationInformations.contains(authenthicationInformation)
					&& authenthicationInformation.isAutenticado()) {
				proofOfID.setPermitido(true);
				proofsOfID.put(codSubject, proofOfID);
			}
		}
		return proofOfID;
	}

	/**
	 * Inhabilita a un ProofOfID de un sujeto dentro del sistema.
	 * 
	 * @param codSubject
	 */
	public void inhabilitarSubject(Integer codSubject) throws AuthenticatorException {
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
	public void habilitarSubject(Integer codSubject) throws AuthenticatorException {
		if (proofsOfID.containsKey(codSubject)) {
			proofsOfID.get(codSubject).setPermitido(true);
		} else {
			throw new AuthenticatorException("No existen credenciales autenticadoras para el sujeto.");
		}
	}

	/**
	 * Verifica si un sujeto está autenticado o permitido.
	 * @param subject
	 * @return
	 */
	public boolean estaAutenticadoPermitido(Subject subject) {
		return (proofsOfID.containsKey(subject.getCodSubject())
				&& proofsOfID.get(subject.getCodSubject()).isPermitido()) ? true : false;
	}

}
