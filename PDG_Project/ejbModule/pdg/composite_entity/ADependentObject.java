package pdg.composite_entity;

import java.io.Serializable;


import java.util.*;

/**
 * Entity implementation class for Entity: ADependentObject EJB Local Entity que
 * representa a un DependentObject en Composite Entity
 */
public abstract class ADependentObject  implements Serializable {

	private static final long serialVersionUID = 1L;

	public ADependentObject() {

	}

	/**
	 * Si un Dependent object tiene otros dependent objects, entonces los
	 * devuelve todos.
	 * 
	 * @return dependent object que tiene asociados.
	 */
	protected abstract Set<List> getDependentObjects();

}
