package seguridad.authentication;

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

	private Authenticator authenticator;

	public Subject(Integer codSubject, Authenticator authenticator) {
		this.codSubject = codSubject;
		this.authenticator = authenticator;
		proofOfID = null;// Mientras se autentica.
		// TODO ¿Debería él tener ese ProofOfID?
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

}
