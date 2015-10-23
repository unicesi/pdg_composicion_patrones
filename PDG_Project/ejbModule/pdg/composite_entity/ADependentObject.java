package pdg.composite_entity;

import java.io.Serializable;

import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.persistence.*;

import java.util.*;

/**
 * Entity implementation class for Entity: ADependentObject EJB Local Entity que
 * representa a un DependentObject en Composite Entity
 */

@Entity
@LocalBean
// @PersistenceContext(name="PDG_Project")
public abstract class ADependentObject implements Serializable, EntityBean {

	private static final long serialVersionUID = 1L;

	public ADependentObject() {

	}

	/**
	 * Si un Dependent object tiene otros dependent objects, entonces los
	 * devuelve todos.
	 * 
	 * @return dependent object que tiene asociados.
	 */
	protected abstract Collection<ADependentObject> getDependentObjects();

}
