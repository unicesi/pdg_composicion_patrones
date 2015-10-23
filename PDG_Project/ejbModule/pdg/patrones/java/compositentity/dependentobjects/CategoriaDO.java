package pdg.patrones.java.compositentity.dependentobjects;

import java.rmi.RemoteException;

import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.LocalBean;
import javax.ejb.RemoveException;
import javax.ejb.Stateless;
import javax.naming.InitialContext;

/**
 * Session Bean implementation class CategoriaDO
 */
@Stateless
@LocalBean
public abstract class CategoriaDO implements CategoriaDOLocal,EntityBean {

	private static final long serialVersionUID = 1L;

	private EntityContext context;
	
	public CategoriaDO(){
		context=null;
	}

	// Getters and setters for Container Managed-Persistence fields
	// ============================================================
	public abstract Integer getIdCategoria();

	public abstract void setIdCategoria(Integer idCategoria);

	public abstract String getNombre();

	public abstract void setNombre(String nombre);

	public abstract String getDescripcion();

	public abstract void setDescripcion(String descripcion);

	// EJB create Method
	// =================
	public Object ejbCreate(Integer idCategoria, String nombre, String descripcion) {
		setIdCategoria(idCategoria);
		setNombre(nombre);
		setDescripcion(descripcion);
		return null;
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
