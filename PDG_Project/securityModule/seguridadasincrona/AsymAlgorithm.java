package seguridadasincrona;


import Interfaces.IAlgorithm;

public abstract class AsymAlgorithm implements IAlgorithm {

	protected byte[] theKey;
	protected byte[] mensajeAEncriptar;
	protected byte[] esperado;
	protected byte[] mensajeADescifrar;

	protected AsymAlgorithm() {

		byte[] theKey = null;
		byte[] theMsg = null;
		byte[] theExp = null;

	}

}
