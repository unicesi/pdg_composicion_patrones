package org.driso.patterns.compositentity;

import java.util.*;

public abstract class ACoarseGranedObject{ 

	protected String tipo;
	
	protected List<ADependentObject> dependentObjects;

	public List<ADependentObject> getDependentObjects() {
		return dependentObjects;
	}

	public void setDependentObjects(List<ADependentObject> dependentObjects) {
		this.dependentObjects = dependentObjects;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
