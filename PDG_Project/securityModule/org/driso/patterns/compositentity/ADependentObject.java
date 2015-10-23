package org.driso.patterns.compositentity;

import javax.annotation.Resource;

import org.driso.osr.mapping.gateways.rows.EntityManager;
import org.driso.osr.mapping.gateways.rows.PersistenceContext;
import org.driso.osr.mapping.gateways.rows.SessionContext;

public abstract class ADependentObject{


	/**
	 * @generated DT_ID=none
	 */
	@PersistenceContext(unitName = "OSR3G-EJB")
	protected EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	

}
