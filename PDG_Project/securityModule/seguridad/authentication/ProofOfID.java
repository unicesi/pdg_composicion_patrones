package seguridad.authentication;

public class ProofOfID {

	/**
	 * Indica si �sta est� permitida o habilitada en el sistema.
	 */
	private boolean permitido;

	/**
	 * C�digo del sujeto propietario de este ProofOfID
	 */
	private String codSubject;

	public ProofOfID(String codSubject, boolean permitido) {

		this.codSubject = codSubject;
		this.permitido = permitido;

	}

	public boolean isPermitido() {
		return permitido;
	}

	public void setPermitido(boolean permitido) {
		this.permitido = permitido;
	}

	public String getCodSubject() {
		return codSubject;
	}

	public void setCodSubject(String codSubject) {
		this.codSubject = codSubject;
	}

	public String toString() {
		return  "Sujeto: " + codSubject + ", "+ "Permitido:" + permitido;
	}

}
