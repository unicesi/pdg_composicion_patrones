package pdg.patrones.java.compositentity.dependentobjects;

import java.rmi.RemoteException;

import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.LocalBean;
import javax.ejb.RemoveException;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class CaracteristicasDO
 */
@Stateless
@LocalBean
public abstract class CaracteristicasDO implements CaracteristicasDOLocal,EntityBean {
	
	private static final long serialVersionUID = 1L;

	private EntityContext context;
	
	// Getters and setters for Container Managed-Persistence fields
	// ============================================================
	public abstract Integer getIdCaracteristica();
	public abstract void setIdCaracteristica(Integer idCaracteristica);
	
	public abstract String getNombre();
	public abstract void setNombre(String nombre);
	
	public abstract String getDetalle();
	public abstract void setDetalle(String detalle);
	
	// EJB create Method
		// =================
		public Object ejbCreate(Integer idCaracteristica, String nombre, String detalle) {
			setIdCaracteristica(idCaracteristica);
			setNombre(nombre);
			setDetalle(detalle);
			return null;
		}
	
    /**
     * Default constructor. 
     */
    public CaracteristicasDO() {
        context=null;
    }


	@Override
	public void ejbActivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void ejbLoad() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void ejbPassivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void ejbRemove() throws RemoveException, EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void ejbStore() throws EJBException, RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setEntityContext(EntityContext ctx) throws EJBException, RemoteException {
		this.context = ctx;
	}

	@Override
	public void unsetEntityContext() throws EJBException, RemoteException {
		this.context = null;
	}

}
