package pdg.composite_entity.examples;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.EntityContext;
import javax.ejb.FinderException;
import javax.ejb.LocalBean;
import javax.ejb.ObjectNotFoundException;

import javax.persistence.Entity;

import pdg.composite_entity.ACoarseGrainedObject;

@Entity
@LocalBean
public class ProductoCGO extends ACoarseGrainedObject<ProductoCGO> {

	private static final long serialVersionUID = 1L;

	private EntityContext context;

	@EJB
	ProductoDAO productoDAO;

	/**
	 * Identificador del producto en la base de datos.
	 */
	private Integer idProducto;

	/**
	 * Nombre del producto.
	 */
	private String nombre;

	/**
	 * Precio del producto.
	 */
	private BigDecimal precio;

	/**
	 * Número que representa la cantidad que se considera crítica a tener en el
	 * inventario de éste producto.
	 */
	private int cantidadCritica;

	/**
	 * Listado de las categorías a las que pertenece el producto.
	 */
	private List<Categoria> categoriaList;

	/**
	 * Listado de las características del producto.
	 */
	private List<Caracteristicas> caracteristicasList;

	/**
	 * Lista de los productos, dependen de la existencia de éste Coarse-grained
	 * object.
	 */
	private List<Producto> productosList;

	public ProductoCGO() {

	}

	@Override
	public Integer ejbCreate(ProductoCGO aCoarseGrainedObject) throws CreateException {
		try {
			this.idProducto = aCoarseGrainedObject.getIdProducto();
			setCoarseGrainedObjectData(aCoarseGrainedObject);
			getACoarseDAO().create(aCoarseGrainedObject);
		} catch (Exception ex) {
			throw new EJBException("Razón:" + ex.getMessage());
		}
		return this.idProducto;
	}

	@Override
	public void setCoarseGrainedObjectData(ProductoCGO aCoarseGrainedObject) {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer ejbFindByPrimaryKey(Integer primaryKey) throws FinderException {
		boolean resultado;
		try {
			ProductoDAO productoDAO = (ProductoDAO) getACoarseDAO();
			resultado = productoDAO.findACoarseGrainedObject(primaryKey);
		} catch (Exception ex) {
			throw new EJBException("Razón:" + ex.getMessage());
		}
		if (resultado) {
			return primaryKey;
		} else {
			throw new ObjectNotFoundException();
		}
	}

	@Override
	public void ejbPostCreate(ProductoCGO aCoarseGrainedObject) {

	}

	@Override
	public ProductoCGO getCoarseGrainedObject() {
		return this;
	}

	@Override
	public Set<List> getDependentObjects() {
		Set<List> dependentObjects = new HashSet<List>();
		dependentObjects.add(productosList);
		return dependentObjects;
	}

	@Override
	public void ejbRemove() {
		try {
			if (categoriaList != null) {
				productoDAO.getEntityManager()
						.createQuery("DELETE FROM Categoria WHERE producto_id_producto=:" + String.valueOf(idProducto));
				categoriaList = null;
			}
			if (caracteristicasList != null) {
				productoDAO.getEntityManager().createQuery(
						"DELETE FROM CaracteristicasWHERE producto_id_producto=:" + String.valueOf(idProducto));
				caracteristicasList = null;
			}
			productoDAO.delete(idProducto);
		} catch (Exception ex) {
			try {
				throw new Exception("Razón:" + ex.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void ejbActivate() throws EJBException, RemoteException {
		idProducto = (Integer) context.getPrimaryKey();
	}

	@Override
	public void ejbLoad() throws EJBException, RemoteException {
		setCoarseGrainedObjectData(productoDAO.findById(idProducto));
		// TODO
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
		context = null;
	}

	@Override
	public void ejbPassivate() throws EJBException, RemoteException {
		idProducto = null;
	}

	// ===-GETTERS AND SETTERS==============//
	// =====================================//

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public int getCantidadCritica() {
		return cantidadCritica;
	}

	public void setCantidadCritica(int cantidadCritica) {
		this.cantidadCritica = cantidadCritica;
	}

	public List<Categoria> getCategoriaList() {
		return categoriaList;
	}

	public void setCategoriaList(List<Categoria> categoriaList) {
		this.categoriaList = categoriaList;
	}

	public List<Caracteristicas> getCaracteristicasList() {
		return caracteristicasList;
	}

	public void setCaracteristicasList(List<Caracteristicas> caracteristicasList) {
		this.caracteristicasList = caracteristicasList;
	}

	public List<Producto> getProductosList() {
		return productosList;
	}

	public void setProductosList(List<Producto> productosList) {
		this.productosList = productosList;
	}

	// =====================================//

}
