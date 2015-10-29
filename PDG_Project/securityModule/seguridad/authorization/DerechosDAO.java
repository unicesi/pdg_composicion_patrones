package seguridad.authorization;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DerechosDAO {

	@PersistenceContext(name = "PDG_Project")
	EntityManager entityManager;

	public DerechosDAO() {

	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * Devuelve el Derecho que tiene el id pasado por parámetro.
	 * 
	 * @param idProtectedObject-Id
	 *            del Derecho que se desea buscar.
	 * @return El Derecho encontrado.
	 */
	public Derecho findByIdProtectedObject(Integer idProtectedObject) {
		Derecho derecho = (Derecho) entityManager.createNamedQuery("Derechos.findByIdProtectedObject")
				.setParameter("idProtectedObject", String.valueOf(idProtectedObject)).getResultList().iterator().next();
		return derecho;
	}

	/**
	 * Encuentra un Derecho dado el codigo del sujeto autenticado
	 * 
	 * @param codSubject
	 * @return Derecho
	 */
	public Derecho findByCodSubject(Integer codSubject) {
		Derecho derecho = (Derecho) entityManager.createNamedQuery("Derechos.findByCodSubject")
				.setParameter("codSubject", String.valueOf(codSubject)).getResultList().iterator().next();
		return derecho;
	}

	/**
	 * Colección de todos los Derecho de un mismo tipo.
	 * 
	 * @return Toda la lista de Derecho
	 */
	public Collection<Derecho> findAll() {
		Collection<Derecho> derechos = entityManager.createNamedQuery("Derechos.findAll").getResultList();
		return derechos;
	}

	/**
	 * Guarda o persiste un Derecho pasado por parámetro.
	 * 
	 * @param derecho-Derecho
	 *            a guardar.
	 */
	public void create(Derecho derecho) {
		entityManager.persist(derecho);
	}

	/**
	 * Borra un Derecho pasado por parámetro.
	 * 
	 * @param derechoID-identifiación
	 *            del Derecho a borrar.
	 */
	public void delete(Integer derechoID) {
		entityManager.remove(findById(derechoID));
	}

	public Derecho findById(Integer derechoID) {
		return (Derecho) entityManager.createNamedQuery("Derechos.findById").getResultList().iterator().next();
	}

	/**
	 * Actualiza un Derecho pasado por parámetro.
	 * 
	 * @param derecho-Derecho
	 *            a actualizar.
	 * @return El Derecho actualizado.
	 */
	public Derecho update(Derecho derecho) {
		return entityManager.merge(derecho);
	}

	
	public boolean checkRight(){
		
	}
}
