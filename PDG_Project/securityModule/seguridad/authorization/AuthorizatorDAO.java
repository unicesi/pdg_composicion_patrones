package seguridad.authorization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import seguridad.Authorizationinformation;
import seguridad.AuthorizationinformationPK;

/**
 * Session Bean implementation class AuthorizatorDAO
 */
@Stateless
@LocalBean
public class AuthorizatorDAO implements AuthorizatorDAORemote, AuthorizatorDAOLocal {

	/**
	 * Tipos de acceso de un sujeto para acceder a los servicios de un
	 * sistema(ServiceLocator)
	 * 
	 */
	public static final String[] TIPO_ACCESOS = { "PERMITIDO", "DENEGADO" };

	@PersistenceContext(name = "PDG_Project")
	EntityManager entityManager;

	/**
	 * Colección de derechos de un sujeto para acceder a un
	 * sistema(SERVICE_LOCATOR).
	 */
	private Collection<Authorizationinformation> authorizationinformations;

	/**
	 * Default constructor.
	 */
	public AuthorizatorDAO() {
		this.authorizationinformations = new ArrayList<Authorizationinformation>();
		// this.authorizationinformations=findAll();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Collection<Authorizationinformation> getAuthorizationinformations() {
		return authorizationinformations;
	}

	public void setAuthorizationinformations(Collection<Authorizationinformation> authorizationinformations) {
		this.authorizationinformations = authorizationinformations;
	}

	public static String[] getTipoAccesos() {
		return TIPO_ACCESOS;
	}

	/**
	 * Colección de todos los Authorizationinformations.
	 * 
	 * @return Toda la lista de Authorizationinformations.
	 */
	public Collection<Authorizationinformation> findAll() {
		Collection<Authorizationinformation> authorizationinformation = entityManager
				.createNamedQuery("Authorizationinformations.findAll").getResultList();
		return authorizationinformation;
	}

	/**
	 * Devuelve el Authorizationinformation del sujeto y el sistema pasados por
	 * parámetro.
	 * 
	 * @param codSubject
	 * @param idSystem
	 * @return Authorizationinformation.
	 */
	public Authorizationinformation findById(long codSubject, long idSystem) {
		Authorizationinformation authorizationinformation = (Authorizationinformation) entityManager
				.createNamedQuery("Authorizationinformation.findById")
				.setParameter("codSubject", String.valueOf(codSubject))
				.setParameter("idSystem", String.valueOf(idSystem)).getResultList().iterator().next();
		return authorizationinformation;
	}

	/**
	 * Devuelve el Authorizationinformation del sujeto pasado por parámetro.
	 * 
	 * @param codSubject
	 * 
	 * @return Authorizationinformation.
	 */
	public Authorizationinformation findByCodSubject(long codSubject) {
		Authorizationinformation authorizationinformation = (Authorizationinformation) entityManager
				.createNamedQuery("Authorizationinformation.findByCodSubject")
				.setParameter("codSubject", String.valueOf(codSubject)).getResultList().iterator().next();
		return authorizationinformation;
	}

	/**
	 * Devuelve el Authorizationinformation del sujeto pasado por parámetro.
	 * 
	 * @param codSubject
	 * 
	 * @return Authorizationinformation.
	 */
	public Authorizationinformation findByIdSystem(long idSystem) {
		Authorizationinformation authorizationinformation = (Authorizationinformation) entityManager
				.createNamedQuery("Authorizationinformation.findByIdSystem")
				.setParameter("idSystem", String.valueOf(idSystem)).getResultList().iterator().next();
		return authorizationinformation;
	}

	/**
	 * Guarda o persiste un Authorizationinformation pasado por parámetro.
	 * 
	 * @param authorizationinformation-Authorizationinformation
	 *            a guardar.
	 */
	public void create(Authorizationinformation authorizationinformation) {
		entityManager.persist(authorizationinformation);
	}

	/**
	 * Actualiza un Authorizationinformation pasado por parámetro.
	 * 
	 * @param authorizationinformation-Authorizationinformation
	 *            a actualizar.
	 * @return El Authorizationinformation actualizado.
	 */
	public Authorizationinformation update(Authorizationinformation authorizationinformation) {
		return entityManager.merge(authorizationinformation);
	}

	/**
	 * Agrega un derecho nuevo.
	 * 
	 * @param idSubject
	 * @param idProtectedObject
	 * @param tipoAcceso
	 * @return true- SI lo agregó. false- si no lo agregó.
	 */
	public boolean agregarAuthorizationinformation(long codSubject, long idSystem, String tipoAcceso) {
		try {
			AuthorizationinformationPK id = new AuthorizationinformationPK(codSubject, idSystem);
			Authorizationinformation authorizationinformation = new Authorizationinformation(id, tipoAcceso);
			if (!authorizationinformations.contains(authorizationinformation)) {
				authorizationinformations.add(authorizationinformation);
				// TODO entityManager.createQuery(
				// "INSERT INTO authorizationinformation VALUES(" + codSubject +
				// "," +
				// idSystem + "," + tipoAcceso);
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;

	}

	/**
	 * Verifica si un sujeto tiene permisos de acceso a un sistema(SERVICE
	 * LOCATOR).
	 * 
	 * @param codSubject
	 * @param idSystem
	 * @return true- si tiene permisos. false- si no los tiene.
	 */
	public boolean checkRight(long codSubject, long idSystem) {
		Authorizationinformation authorizationinformation = buscarDerecho(codSubject, idSystem);
		if (authorizationinformation == null) {
			return false;
		} else {
			return authorizationinformation.getTipoAcceso().equals(TIPO_ACCESOS[0]) ? true
					: authorizationinformation.getTipoAcceso().equals(TIPO_ACCESOS[1]) ? false : false;
		}
		// TODO return findById(codSubject, idSystem) != null
		// || findById(codSubject,
		// idSystem).getTipoAcceso().equals(TIPO_ACCESOS[0]) ? true :
		// false;

	}

	/**
	 * Busca si existe un Authorizationinformation con un codSubject y un
	 * idSystem dado.
	 * 
	 * @param codSubject
	 * @param idSystem
	 * @return Authorizationinformation si existe. null si no existe.
	 */
	private Authorizationinformation buscarDerecho(long codSubject, long idSystem) {
		Authorizationinformation authorizationinformation = null;
		Authorizationinformation authorizationinformationActual = null;
		Iterator<Authorizationinformation> iter = authorizationinformations.iterator();
		while (iter.hasNext()) {
			authorizationinformationActual = iter.next();
			if (authorizationinformationActual.getId().getCodSubject() == codSubject
					&& authorizationinformationActual.getId().getIdSystem() == idSystem) {
				authorizationinformation = authorizationinformationActual;
				break;
			} else {
				continue;
			}
		}
		return authorizationinformation;
	}

}
