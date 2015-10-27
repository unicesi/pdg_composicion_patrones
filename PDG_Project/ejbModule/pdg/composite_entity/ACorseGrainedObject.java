package pdg.composite_entity;

import java.io.Serializable;

import javax.ejb.EntityBean;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.persistence.*;
import pdg.composite_entity.*;

/**
 * Entity implementation class for Entity: ACorseGrainedObject
 *
 */
@Entity
@LocalBean
public abstract class	 ACorseGrainedObject implements Serializable, ICoarseGrainedObject, EntityBean {

	
	
	private static final long serialVersionUID = 1L;

	public ACorseGrainedObject() {
		super();
	}
   
}
