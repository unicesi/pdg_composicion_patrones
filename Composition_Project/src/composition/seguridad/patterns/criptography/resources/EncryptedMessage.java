package composition.seguridad.patterns.criptography.resources;

public class EncryptedMessage {

	private Object mensajeEncriptado;

	public void setMensaje(Object mensajeEncriptado) {
		this.mensajeEncriptado = mensajeEncriptado;

	}

	public Object getMensaje() {

		return mensajeEncriptado;
	}

}
