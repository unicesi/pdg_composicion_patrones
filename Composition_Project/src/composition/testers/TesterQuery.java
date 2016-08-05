package composition.testers;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import composition.tienda.entities.Authenticationinformation;

/**
 * Session Bean implementation class TesterQuery
 */
@Stateless
@LocalBean
public class TesterQuery {

	@PersistenceContext(unitName = "Composition_Project")
	EntityManager entityManager;

	
    /**
     * Default constructor. 
     */
    public TesterQuery() {
      
    }
    
    
    public List<Authenticationinformation> getAuthenticationinformations(long codSubject){ 
    	TypedQuery<Authenticationinformation> query=entityManager.createNamedQuery("Authenticationinformation.findAll", Authenticationinformation.class);
    	List<Authenticationinformation> authenticationinformations=query.getResultList();
		return authenticationinformations;     	   	
    }

}
