package org.driso.patterns.compositentity;

public abstract class ACompositeEntity{

	protected String tipo;
	
	protected ACoarseGranedObject coarseGranedObject;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public ACoarseGranedObject getCoarseGranedObject() {
		return coarseGranedObject;
	}

	public void setCoarseGranedObject(ACoarseGranedObject coarseGranedObject) {
		this.coarseGranedObject = coarseGranedObject;
	}
	
	
	
}
