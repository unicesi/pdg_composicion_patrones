package composition.java.patterns.composite_entity;

import java.util.List;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.FinderException;

import composition.java.patterns.composite_entity.examples.ProductoCGO;

public interface ICoarseGrainedObject<T> {

	/**
	 * Método que crea el dependent object traido por su DAO,
	 * mendiante @PersistenceContext.
	 * 
	 * @param aCoarseGrainedObject-Objeto
	 *            de tipo ADependentObject que recibe para crear.
	 * @return El Id que representa una tupla o dependent object.
	 * @throws CreateException-Cuando
	 *             no se puede crear el coarse-grained object en la base de
	 *             datos.
	 */
	public long ejbCreate(T aCoarseGrainedObject) throws CreateException;

	/**
	 * Obtiene el id del EJB que se busca
	 * 
	 * @param primaryKey
	 * @return El id del EJB que se busca.
	 * @throws FinderException-Cuando
	 *             no se encuentra el coarse-grained object buscado por esa
	 *             llave primaria.
	 */
	public T ejbFindByPrimaryKey(long primaryKey) throws FinderException;

	public void ejbPostCreate(T aCoarseGrainedObject);

	/**
	 * Devuelve el ACoarseGrainedObject actual.
	 * 
	 * @return aCoarseGrainedObject- Devuelve un ACoarseGrainedObject
	 */
	public ACoarseGrainedObject<T> getCoarseGrainedObject();  

	/**
	 * Si un Dependent object tiene otros dependent objects, entonces los
	 * devuelve todos.
	 * 
	 * @return dependent object que tiene asociados.
	 */
	public Set getDependentObjects();

	/**
	 * Actualiza los valores del coarse-grained object actual.
	 * 
	 * @param aCoarseGrainedObject-Coarse-grained
	 *            object a actualizar.
	 */
	public void setCoarseGrainedObjectData(T aCoarseGrainedObject);
}
