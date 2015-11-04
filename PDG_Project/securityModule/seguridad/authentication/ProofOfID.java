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
	
	/**
	 * Identificaci�n del sistema que le otorga this.
	 */
	private String idSystem;

	public ProofOfID(String idSystem, String codSubject, boolean permitido) {
		this.idSystem=idSystem;
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

	public String getIdSystem() {
		return idSystem;
	}

	public void setIdSystem(String idSystem) {
		this.idSystem = idSystem;
	} 
	
	public String toString(){
		return "Sistema: "+ idSystem+ ", Sujeto: "+codSubject+", Permitido:"+permitido;
	}

}
