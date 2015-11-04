package pdg.composite_entity;

import java.util.*;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;


/**
 * Session Bean implementation class ACoarseDAO
 */
@Stateless
@LocalBean
public abstract class ACoarseDAO<T> {

	

	/**
	 * Devuelve el CoarseGrainedObject que tiene el id pasado por parámetro.
	 * 
	 * @param id-Id
	 *            del CoarseGrainedObject que se desea buscar.
	 * @return El coarse-grained object encontrado.
	 */
	public abstract T findById(Integer id);

	/**
	 * Colección de todos los CoarseGrainedObjects de un mismo tipo.
	 * 
	 * @return Toda la lista de Coarse-Grained objects
	 */
	public abstract Collection<T> findAll();

	/**
	 * Guarda o persiste un coarse-grained object pasado por parámetro.
	 * 
	 * @param aCoarseGrainedObject-Coarse-Grained
	 *            object a guardar.
	 */
	public abstract void create(T aCoarseGrainedObject);

	/**
	 * Borra un coarse-grained object pasado por parámetro.
	 * 
	 * @param aCoarseGrainedObjectID-identifiación del Coarse-Grained
	 *            object a borrar.
	 */
	public abstract void delete(Integer aCoarseGrainedObjectID);

	/**
	 * Actualiza un coarse-grained object pasado por parámetro.
	 * 
	 * @param aCoarseGrainedObject-Coarse-Grained
	 *            object a actualizar.
	 * @return El coarse-grained object actualizado.
	 */
	public abstract T update(T aCoarseGrainedObject);

	/**
	 * Encuentra el coarse-grained object que tiene esa primary key en la base
	 * de datos.
	 * 
	 * @param primaryKey-del
	 *            coarse-grained object que se requiere encontrar.
	 * @return true-Si se encuentra el coarse-grained object. false-en caso de
	 *         no encontrarse en la BD.
	 */
	public abstract boolean findACoarseGrainedObject(Integer primaryKey);


}
