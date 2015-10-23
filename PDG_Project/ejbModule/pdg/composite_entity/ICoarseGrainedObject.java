package pdg.composite_entity;

import java.util.*;

import javax.ejb.EntityContext;
import pdg.composite_entity.*;

public interface ICoarseGrainedObject {

	/**
	 * Método que crea el dependent object traido por su DAO,
	 * mendiante @PersistenceContext.
	 * 
	 * @param aDependentObject
	 * @return El Id que representa una tupla o dependent object.
	 */
	public String ejbCreate(ADependentObject aDependentObject);

	/**
	 * 
	 * @param primaryKey
	 * @return
	 */
	public String ejbFindByPrimaryKey(String primaryKey);

	/**
	 * Si un Dependent object tiene otros dependent objects, entonces los
	 * devuelve todos.
	 * 
	 * @return dependent object que tiene asociados.
	 */
	public Collection<ADependentObject> getDependentObjects();

}
