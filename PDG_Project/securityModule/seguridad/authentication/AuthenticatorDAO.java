package seguridad.authentication;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AuthenticatorDAO {

	@PersistenceContext(name = "PDG_Project")
	EntityManager entityManager;

	public AuthenticatorDAO() {

	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * Devuelve el AuthenticationInformation que tiene el id pasado por
	 * par�metro.
	 * 
	 * @param id-Id
	 *            del AuthenticationInformation que se desea buscar.
	 * @return El AuthenticationInformation encontrado.
	 */
	public AuthenticationInformation findById(Integer idAuthenticator) {
		AuthenticationInformation authenticationInformation = (AuthenticationInformation) entityManager
				.createNamedQuery("AuthenticationInformation.findById")
				.setParameter("idAuthenticator", String.valueOf(idAuthenticator)).getResultList().iterator().next();
		return authenticationInformation;
	}

	/**
	 * Encuentra un AuthenticationInformation dado el codigo del sujeto
	 * autenticado
	 * 
	 * @param codSubject
	 * @return AuthenticationInformation
	 */
	public AuthenticationInformation findByCodSubject(Integer codSubject) {
		AuthenticationInformation authenticationInformation = (AuthenticationInformation) entityManager
				.createNamedQuery("AuthenticationInformation.findByCodSubject")
				.setParameter("codSubject", String.valueOf(codSubject)).getResultList().iterator().next();
		return authenticationInformation;
	}

	/**
	 * Colecci�n de todos los AuthenticationInformations de un mismo tipo.
	 * 
	 * @return Toda la lista de AuthenticationInformations
	 */
	public Collection<AuthenticationInformation> findAll() {
		Collection<AuthenticationInformation> authenticationInformations = entityManager
				.createNamedQuery("AuthenticationInformation.findAll").getResultList();
		return authenticationInformations;
	}

	/**
	 * Guarda o persiste un AuthenticationInformation pasado por par�metro.
	 * 
	 * @param authenticationInformation-AuthenticationInformation
	 *            a guardar.
	 */
	public void create(AuthenticationInformation authenticationInformation) {
		entityManager.persist(authenticationInformation);
	}

	/**
	 * Borra un AuthenticationInformation pasado por par�metro.
	 * 
	 * @param authenticationInformationID-identifiaci�n
	 *            del AuthenticationInformationt a borrar.
	 */
	public void delete(Integer authenticationInformationID) {
		entityManager.remove(findById(authenticationInformationID));
	}

	/**
	 * Actualiza un AuthenticationInformation pasado por par�metro.
	 * 
	 * @param authenticationInformation-AuthenticationInformation
	 *            a actualizar.
	 * @return El coarse-grained object actualizado.
	 */
	public AuthenticationInformation update(AuthenticationInformation authenticationInformation) {
		return entityManager.merge(authenticationInformation);
	}

}
