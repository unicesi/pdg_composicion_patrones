package pdg.patrones.seguridad.compositentity.coarsegrainedobjects;

import java.awt.List;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.LocalBean;
import javax.ejb.RemoveException;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import pdg.patrones.java.compositentity.dependentobjects.CaracteristicasDOLocal;
import pdg.patrones.java.compositentity.dependentobjects.CaracteristicasDOLocalHome;
import pdg.patrones.java.compositentity.dependentobjects.CategoriaDOLocal;
import pdg.patrones.java.compositentity.dependentobjects.CategoriaDOLocalHome;

/**
 * Session Bean implementation class ProductoCGO
 */
@Stateless
@LocalBean
public abstract class ProductoCGO implements ProductoCGOLocal, EntityBean {

	private static final long serialVersionUID = 1L;

	private EntityContext context;

	// getters and setters for CMP fields
	// ====================================
	public abstract Integer getIdProducto();

	public abstract void setIdProducto(Integer IdProducto);

	public abstract String getNombre();

	public abstract void setNombre(String nombre);

	public abstract BigDecimal getPrecio();

	public abstract void setPrecio(BigDecimal precio);

	public abstract int getCantidadCritica();

	public abstract void setCantidadCritica(int cantidadCritica);

	// CMR Fields
	public abstract List getCategorias();

	public abstract void setCategorias(List categorias);

	public abstract List getCaracteristicas();

	public abstract void setCaracteristicas(List caracteristicas);

	// EJB create method
	// ===================
	public String ejbCreate(Integer IdProducto) throws CreateException {
		setIdProducto(IdProducto);
		return null;
	}

	public void ejbPostCreate(Integer IdProducto) throws CreateException {
		try {
			InitialContext ic = new InitialContext();
			List categoriasLocales=new List();
			
			CategoriaDOLocalHome categoriasHome = (CategoriaDOLocalHome) ic
					.lookup("java:comp/env/ejb/local/Categorias");
			CategoriaDOLocal categoriasLocal = categoriasHome.create(CategoriaDOLocalHome.idCategoria,
					CategoriaDOLocalHome.nombre, CategoriaDOLocalHome.descripcion);
			categoriasLocales.add(categoriasLocal); 
			setCategorias(categoriasLocales);

			Collection<CaracteristicasDOLocal> caracteristicasLocales = new ArrayList<CaracteristicasDOLocal>();
			for (int i = 0; i < getCaracteristicas().size(); i++) {
				CaracteristicasDOLocalHome caracteristicaHome = (CaracteristicasDOLocalHome) ic
						.lookup("java:comp/env/ejb/local/Categorias");
				CaracteristicasDOLocal caracteristicaLocal = caracteristicaHome.create(
						CaracteristicasDOLocalHome.idCaracteristica, CaracteristicasDOLocalHome.nombre,
						CaracteristicasDOLocalHome.detalle);
				caracteristicasLocales.add(caracteristicaLocal);
			}
			setCaracteristicas(caracteristicasLocales);

		} catch (NamingException ne) {
			throw new CreateException("could not lookup ejb. Exception is " + ne.getMessage());
		}
	}

	/**
	 * Default constructor.
	 */
	public ProductoCGO() {
		context = null;
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
		context = ctx;

	}

	@Override
	public void unsetEntityContext() throws EJBException, RemoteException {

	}

}
