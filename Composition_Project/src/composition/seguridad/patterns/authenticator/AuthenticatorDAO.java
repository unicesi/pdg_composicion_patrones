package composition.seguridad.patterns.authenticator;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import composition.tienda.entities.Authenticationinformation;

@Stateless
public class AuthenticatorDAO {

	@PersistenceContext(unitName = "Composition_Project")
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
	 * Encuentra un AuthenticationInformation dado el codigo del sujeto
	 * autenticado
	 * 
	 * @param codSubject
	 * @return AuthenticationInformation
	 */
	public List<Authenticationinformation> findByCodSubject(String codSubject) {
		// List<Authenticationinformation> authenticationInformations = (List)
		// entityManager
		// .createNamedQuery("Authenticationinformation.findByCodSubject").setParameter("codSubject",
		// codSubject)
		// .getResultList();
		List<Authenticationinformation> authenticationInformations = (List) entityManager
				.createQuery("SELECT * FROM Authenticationinformation").getResultList();
		return authenticationInformations;
	}

	/**
	 * Encuentra un AuthenticationInformation dado la identificación del sistema
	 * 
	 * @param codSubject
	 * @return AuthenticationInformation
	 */
	public List<Authenticationinformation> findByIdSystem(String idSystem) {
		List<Authenticationinformation> authenticationInformations = (List) entityManager
				.createNamedQuery("Authenticationinformation.findByIdSystem").setParameter("idSystem", idSystem)
				.getResultList();
		return authenticationInformations;
	}

	/**
	 * Encuentra un AuthenticationInformation dado la identificación del sistema
	 * 
	 * @param codSubject
	 * @return AuthenticationInformation
	 */
	public Authenticationinformation findByIdSystem_CodSubject(String idSystem, String codSubject) {
		Authenticationinformation authenticationInformation = (Authenticationinformation) entityManager
				.createNamedQuery("Authenticationinformation.findByIdSystem_CodSubject")
				.setParameter("idSystem", idSystem).setParameter("codSubject", codSubject).getResultList().iterator()
				.next();
		return authenticationInformation;
	}

	/**
	 * Colección de todos los AuthenticationInformations de un mismo tipo.
	 * 
	 * @return Toda la lista de AuthenticationInformations
	 */
	public Collection<Authenticationinformation> findAll() {
		TypedQuery<Authenticationinformation> authenticationInformationsQuery = (TypedQuery<Authenticationinformation>) entityManager
				.createNamedQuery("Authenticationinformation.findAll", Authenticationinformation.class);
		List<Authenticationinformation> authenticationInformations = authenticationInformationsQuery.getResultList();
		return authenticationInformations;
	}

	/**
	 * Guarda o persiste un AuthenticationInformation pasado por parámetro.
	 * 
	 * @param authenticationInformation-AuthenticationInformation
	 *            a guardar.
	 */
	public void create(Authenticationinformation authenticationInformation) {
		System.out.println("Entra al create");
		System.out.println("ToString del entity manager: " + entityManager.toString());
		entityManager.createQuery("INSERT INTO authenticationinformation VALUES("
				+ authenticationInformation.getCodSubject() + ",\'" + authenticationInformation.getPassword() + "\',"
				+ authenticationInformation.getAutenticado() + ");");
		System.out.println("Completó consulta");
		System.out.println("Authentication information traida de BD: "
				+ findByCodSubject(String.valueOf(authenticationInformation.getCodSubject())).toString());
	}

	/**
	 * Borra un AuthenticationInformation pasado por parámetro.
	 * 
	 * @param authenticationInformationID-identifiación
	 *            del AuthenticationInformationt a borrar.
	 */
	public void delete(String codSubject) {
		entityManager.remove(findByCodSubject(codSubject));
	}

	/**
	 * Actualiza un AuthenticationInformation pasado por parámetro.
	 * 
	 * @param authenticationInformation-AuthenticationInformation
	 *            a actualizar.
	 * @return El coarse-grained object actualizado.
	 */
	public Authenticationinformation update(Authenticationinformation authenticationInformation) {
		return entityManager.merge(authenticationInformation);
	}

	/**
	 * ¿El sujeto con código codSubject está autenticado en el sistema?
	 * 
	 * @return true-Si lo está, false- si no lo está.
	 */
	public boolean isAutenticado(String idSystem, String codSubject) {
		String autenticado = findByIdSystem_CodSubject(idSystem, codSubject).getAutenticado();
		return autenticado.equalsIgnoreCase("1");
	}

}
