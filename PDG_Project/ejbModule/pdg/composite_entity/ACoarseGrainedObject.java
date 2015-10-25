package pdg.composite_entity;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.EntityBean;
import javax.ejb.LocalBean;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ACorseGrainedObject
 *
 */

public abstract class ACoarseGrainedObject<T> implements Serializable, ICoarseGrainedObject<T>, EntityBean {

	private static final long serialVersionUID = 1L;

	@EJB
	protected ACoarseDAO<T> aCoarseDAO;

	public ACoarseGrainedObject() {
		super();
	}

	public ACoarseDAO<T> getACoarseDAO() {
		return aCoarseDAO;
	}

	public void setaCoarseDAO(ACoarseDAO<T> aCoarseDAO) {
		this.aCoarseDAO = aCoarseDAO;
	}

}
