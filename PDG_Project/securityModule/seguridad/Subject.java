package seguridad;

import java.util.Collection;

import seguridad.authentication.Authenticator;
import seguridad.authentication.AuthenticatorException;
import seguridad.authentication.ProofOfID;
import seguridad.authorization.Derecho;

public class Subject {

	/**
	 * Código que identifica a un sujeto dentro del sistema.
	 */
	private Integer codSubject;

	/**
	 * Credencial autenticadora dentro del sistema de autenticaciones si la
	 * tiene.
	 */
	private ProofOfID proofOfID;

	/**
	 * Authenticator que le permite autenticarse.
	 */
	private Authenticator authenticator;

	/**
	 * Derechos que le dan permisos de acceso a los servicios de las entidades
	 * de negocio que maneja un sistema(Service Locator por ejemplo).
	 */
	private Collection<Derecho> derechos;

	public Subject(Integer codSubject, Authenticator authenticator) {
		this.codSubject = codSubject;
		this.authenticator = authenticator;
		proofOfID = null;// Mientras se autentica.
		// TODO ¿Debería él tener ese ProofOfID?
		this.derechos = null;
	}

	/**
	 * Solicita al autenticador pasado por el sistema que le otorgue un
	 * ProofOfID, dado su código.
	 */
	public void requestAuthent() {
		try {
			proofOfID = authenticator.login(codSubject);
		} catch (AuthenticatorException e) {
			proofOfID = null;
		}
	}

	public Integer getCodSubject() {
		return codSubject;
	}

	public void setCodSubject(Integer codSubject) {
		this.codSubject = codSubject;
	}

	public ProofOfID getProofOfID() {
		return proofOfID;
	}

	public void setProofOfID(ProofOfID proofOfID) {
		this.proofOfID = proofOfID;
	}

	public Authenticator getAuthenticator() {
		return authenticator;
	}

	public void setAuthenticator(Authenticator authenticator) {
		this.authenticator = authenticator;
	}

	public Collection<Derecho> getDerechos() {
		return derechos;
	}

	public void setDerechos(Collection<Derecho> derechos) {
		this.derechos = derechos;
	}

}
