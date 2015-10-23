package pdg.patrones.seguridad.compositentity.coarsegrainedobjects;

import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;

public interface ProductoCGOLocalHome extends EJBLocalHome {

	public ProductoCGOLocal create(Integer IdProducto, String nombre, String precio, int cantidadCritica) throws CreateException;
	
	public ProductoCGOLocal findByPrimaryKey(Integer IdProducto) throws FinderException;
	
	public Collection<ProductoCGO> findAllProduductosCGO() throws FinderException;
	
}
