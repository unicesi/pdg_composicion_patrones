package composition.seguridad.patterns.authenticator;

public class AuthenticatorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuthenticatorException(String mensajeError) {
		super(mensajeError);
	}

}
