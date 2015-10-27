package seguridad.authentication;

public class ProofOfID {

	/**
	 * Indica si ésta está permitida o habilitada en el sistema.
	 */
	private boolean permitido;

	/**
	 * Código del sujeto propietario de este ProofOfID
	 */
	private Integer codSubject;

	public ProofOfID(Integer codSubject, boolean permitido) {
		this.codSubject = codSubject;
		this.permitido = permitido;

	}

	public boolean isPermitido() {
		return permitido;
	}

	public void setPermitido(boolean permitido) {
		this.permitido = permitido;
	}

	public Integer getCodSubject() {
		return codSubject;
	}

	public void setCodSubject(Integer codSubject) {
		this.codSubject = codSubject;
	}

}
