package composition.seguridad.patterns.criptography.simetric;

import composition.seguridad.patterns.criptography.resources.IAlgorithm;

public abstract class SymAlgorithm implements IAlgorithm {

	protected byte[] theKey;
	protected byte[] mensajeAEncriptar;
	protected byte[] esperado;
	protected byte[] mensajeADescifrar;

	protected SymAlgorithm() {

		byte[] theKey = null;
		byte[] theMsg = null;
		byte[] theExp = null;

	}

}
