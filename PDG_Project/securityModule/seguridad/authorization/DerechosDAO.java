package seguridad.authorization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import seguridad.Derecho;
import seguridad.DerechoPK;

@Stateless
@LocalBean
public class DerechosDAO {

	/**
	 * Tipos de acceso de un sujeto para acceder a los servicios de un protedted
	 * object(business object)
	 */
	public static final String[] TIPO_ACCESOS = { "PERMITIDO", "DENEGADO" };

	@PersistenceContext(name = "PDG_Project")
	EntityManager entityManager;

	/**
	 * Colección de derechos dentro de este sistema.
	 */
	private Collection<Derecho> derechos;

	public DerechosDAO() {

		this.derechos = new ArrayList<Derecho>();
		// TODO this.derechos = findAll();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Collection<Derecho> getDerechos() {
		return derechos;
	}

	public void setDerechos(Collection<Derecho> derechos) {
		this.derechos = derechos;
	}

	public static String[] getTipoAccesos() {
		return TIPO_ACCESOS;
	}

	/**
	 * Devuelve el derecho cuyo idSubject y idProtectedObject son pasados por
	 * parámetro.
	 * 
	 * @param idSubject
	 * @param idProtectedObject
	 * @return Derecho
	 */
	public Derecho findById(long idSubject, String nameProtectedObject) {
		Derecho derecho = (Derecho) entityManager.createNamedQuery("Derecho.findById")
				.setParameter("nameProtectedObject", String.valueOf(nameProtectedObject))
				.setParameter("idSubject", String.valueOf(idSubject)).getResultList().iterator().next();
		return derecho;
	}

	/**
	 * Devuelve el Derecho que tiene el id pasado por parámetro.
	 * 
	 * @param idProtectedObject-Id
	 *            del Derecho que se desea buscar.
	 * @return El Derecho encontrado.
	 */
	public Derecho findByNameProtectedObject(String nameProtectedObject) {
		Derecho derecho = (Derecho) entityManager.createNamedQuery("Derecho.findByNameProtectedObject")
				.setParameter("nameProtectedObject", String.valueOf(nameProtectedObject)).getResultList().iterator().next();
		return derecho;
	}

	/**
	 * Encuentra un Derecho dado el codigo del sujeto autenticado
	 * 
	 * @param codSubject
	 * @return Derecho
	 */
	public Derecho findByCodSubject(long idSubject) {
		Derecho derecho = (Derecho) entityManager.createNamedQuery("Derecho.findByIdSubject")
				.setParameter("idSubject", String.valueOf(idSubject)).getResultList().iterator().next();
		return derecho;
	}

	/**
	 * Colección de todos los Derecho de un mismo tipo.
	 * 
	 * @return Toda la lista de Derecho
	 */
	public Collection<Derecho> findAll() {
		Collection<Derecho> derechos = entityManager.createNamedQuery("Derecho.findAll").getResultList();
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

	/**
	 * Agrega un derecho nuevo.
	 * 
	 * @param idSubject
	 * @param idProtectedObject
	 * @param tipoAcceso
	 * @return true- SI lo agregó. false- si no lo agregó.
	 */
	public boolean agregarDerecho(long idSubject, String nameProtectedObject, String tipoAcceso) {
		try {
			DerechoPK id = new DerechoPK(idSubject, nameProtectedObject);
			Derecho derecho = new Derecho(id, tipoAcceso);
			if (!derechos.contains(derecho)) {
				derechos.add(derecho);
				// TODO entityManager.createQuery(
				// "INSERT INTO derecho VALUES(" + idSubject + "," +
				// idProtectedObject + "," + tipoAcceso);
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;

	}

	/**
	 * Verifica si un sujeto tiene permisos de acceso a servicios del protedted
	 * object pasados por parámetro.
	 * 
	 * @param idSubject
	 * @param idProtectedObject
	 * @return true- si tiene permisos. false- si no los tiene.
	 */
	public boolean checkRight(long idSubject, String nameProtectedObject) {
		Derecho derecho = buscarDerecho(idSubject, nameProtectedObject);
		if (derecho == null) {
			return false;
		} else {
			return derecho.getTipoAcceso().equals(TIPO_ACCESOS[0]) ? true
					: derecho.getTipoAcceso().equals(TIPO_ACCESOS[1]) ? false : false;
		}
		// TODO return findById(idSubject, idProtectedObject) != null
		// || findById(idSubject,
		// idProtectedObject).getTipoAcceso().equals(TIPO_ACCESOS[0]) ? true :
		// false;

	}

	/**
	 * Busca si existe un derecho para un sujeto sobre un objeto protegido.
	 * 
	 * @param idSubject
	 * @param idProtectedObject
	 * @return Derecho si existe. null si no existe.
	 */
	private Derecho buscarDerecho(long idSubject, String nameProtectedObject) {
		Derecho derecho = null;
		Derecho derechoActual = null;
		Iterator<Derecho> iter = derechos.iterator();
		while (iter.hasNext()) {
			derechoActual = iter.next();
			if (derechoActual.getId().getIdSubject() == idSubject
					&& derechoActual.getId().getNameProtectedObject().equals(nameProtectedObject)) {
				derecho = derechoActual;
				break;
			} else {
				continue;
			}
		}
		return derecho;
	}

}
