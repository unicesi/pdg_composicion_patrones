package composition.seguridad.patterns.criptography.resources;

import java.security.Key;

public class AKey {

	private Key llave;

	public Key getLlave() {

		return llave;
	}

	public void setKey(Object llave) {

		this.llave = (Key) llave;

	}

}
