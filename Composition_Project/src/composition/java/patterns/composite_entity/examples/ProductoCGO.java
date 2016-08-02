package composition.java.patterns.composite_entity.examples;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.EntityContext;
import javax.ejb.FinderException;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import composition.java.patterns.composite_entity.ACoarseGrainedObject;
import composition.tienda.entities.Categoria;
import composition.tienda.entities.Detallecompra;
import composition.tienda.entities.Detallesuministro;
import composition.tienda.entities.Detalletemporal;
import composition.tienda.entities.Estado;
import composition.tienda.entities.Producto;
import composition.tienda.entities.Promocion;
import composition.tienda.entities.Proveedorproducto;
import composition.tienda.entities.Valorproductocuantitativo;

// @Table
// @XmlRootElement
// @NamedQueries({ @NamedQuery(name = "ProductoCGO.findAll", query = "SELECT p
// FROM Producto p"),
// @NamedQuery(name = "Producto.findByIdProducto", query = "SELECT p FROM
// Producto p WHERE p.idProducto = :idProducto"),
// @NamedQuery(name = "Producto.findByNombre", query = "SELECT p FROM Producto p
// WHERE p.nombre = :nombre"),
// @NamedQuery(name = "Producto.findByPrecio", query = "SELECT p FROM Producto p
// WHERE p.precio = :precio"),
// @NamedQuery(name = "Producto.findByCantidadActual", query = "SELECT p FROM
// Producto p WHERE p.cantidadActual = :cantidadActual"),
// @NamedQuery(name = "Producto.findByCantidadCritica", query = "SELECT p FROM
// Producto p WHERE p.cantidadCritica = :cantidadCritica"),
// @NamedQuery(name = "Producto.findByCalificacionPromedio", query = "SELECT p
// FROM Producto p WHERE p.calificacionPromedio = :calificacionPromedio"),
// @NamedQuery(name = "Producto.findByDescripcion", query = "SELECT p FROM
// Producto p WHERE p.descripcion = :descripcion") })
@Stateless
@LocalBean
public class ProductoCGO extends ACoarseGrainedObject<Producto> implements Comparable<Producto> {

	private static final long serialVersionUID = 1L;

	private EntityContext context;

	@EJB
	ProductosDAO productoDAO;

	// @Id
	private long idproducto;

	private String color;

	private String condicion;

	private String descripcion;

	// @Column(name = "EAN_UCC")
	private String eanUcc;

	private BigDecimal existencias;

	private BigDecimal existenciasminimas;

	// @Temporal(TemporalType.DATE)
	private Date fechacreacion;

	// @Column(name = "GTIN_14")
	private String gtin14;

	// @Column(name = "GTIN_8")
	private String gtin8;

	private String mpn;

	private String nombre;

	private BigDecimal preciobase;

	// bi-directional many-to-one association to Detallecompra
	// @OneToMany(mappedBy = "producto")
	private List<Detallecompra> detallecompras;

	// bi-directional many-to-one association to Detallesuministro
	// @OneToMany(mappedBy = "producto")
	private List<Detallesuministro> detallesuministros;

	// bi-directional many-to-one association to Detalletemporal
	// @OneToMany(mappedBy = "producto")
	private List<Detalletemporal> detalletemporals;

	// bi-directional many-to-one association to Categoria
	// @ManyToOne
	// @JoinColumn(name = "IDCATEGORIA")
	private Categoria categoria;

	// bi-directional many-to-one association to Estado
	// @ManyToOne
	// @JoinColumn(name = "IDESTADO")
	private Estado estado;

	// bi-directional many-to-many association to Promocion
	// @ManyToMany(mappedBy = "productos")
	private List<Promocion> promocions;

	// bi-directional many-to-one association to Proveedorproducto
	// @OneToMany(mappedBy = "producto")
	private List<Proveedorproducto> proveedorproductos;

	// bi-directional many-to-one association to Valorproductocuantitativo
	// @OneToMany(mappedBy = "producto")
	private List<Valorproductocuantitativo> valorproductocuantitativos;

	/**
	 * Lista de los productos, dependen de la existencia de éste Coarse-grained
	 * object.
	 */
	private List<Producto> productosList;

	public ProductoCGO() {
		productoDAO = new ProductosDAO();
		productosList = new ArrayList<Producto>();
		valorproductocuantitativos = new ArrayList<Valorproductocuantitativo>();
		// try {
		// //ejbLoad();
		// } catch (EJBException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (RemoteException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	@Override
	public long ejbCreate(Producto producto) throws CreateException {
		try {
			setCoarseGrainedObjectData(producto);// Aquí se actualiza en
													// memoria.
		} catch (Exception ex) {
			throw new EJBException("Razón de excepción:" + ex.getMessage());
		}
		return this.idproducto;
	}

	@Override
	public void setCoarseGrainedObjectData(Producto producto) {
		this.idproducto = producto.getIdproducto();
		this.color = producto.getColor();
		this.condicion = producto.getCondicion();
		this.descripcion = producto.getDescripcion();
		this.eanUcc = producto.getEanUcc();
		this.existencias = producto.getExistencias();
		this.existenciasminimas = producto.getExistenciasminimas();
		this.fechacreacion = producto.getFechacreacion();
		this.gtin14 = producto.getGtin14();
		this.gtin8 = producto.getGtin8();
		this.mpn = producto.getMpn();
		this.nombre = producto.getNombre();
		this.preciobase = producto.getPreciobase();
		// TODO FALTA RECARGAR LOS ATRIBUTOS DE LISTAS ANTES DE CATEGORIA EN EL
		// ORDEN DE NAVEGACIÓN DERECHO.
		this.categoria = producto.getCategoria();
		this.estado = producto.getEstado();
		// TODO FALTA RECARGAR PROMOCIONES Y PROVEEDORES
		this.valorproductocuantitativos = producto.getValorproductocuantitativos();
		productosList.add(producto);
	}

	@Override
	public Producto ejbFindByPrimaryKey(long primaryKey) throws FinderException {
		Producto producto = null;
		producto = findByPrimaryKey(primaryKey);
		if (producto == null) {
			producto = getProductoDAO().findByIdProducto(primaryKey);
			if (producto == null) {
				throw new FinderException("No existe un Producto con esa identificación." + primaryKey);
			} else {
				if (!getProductosList().contains(producto)) {
					getProductosList().add(producto);
				}
			}
		}

		return producto;
	}

	/**
	 * Encuentra un Producto por su id producto
	 * 
	 * @param primaryKey
	 * @return Producto buscado.
	 */
	public Producto findByPrimaryKey(long primaryKey) {
		Producto buscado = null;
		Producto actual = null;
		while (productosList.iterator().hasNext()) {
			actual = productosList.iterator().next();
			if (actual.getIdproducto() == primaryKey) {
				buscado = actual;
				break;
			} else {
				continue;
			}
		}
		return buscado;
	}

	@Override
	public void ejbPostCreate(Producto producto) {

	}

	@Override
	public ProductoCGO getCoarseGrainedObject() {
		return this;
	}

	@Override
	public Set getDependentObjects() {
		Set dependentObjects = new HashSet();
		// dependentObjects.add(productosList);
		// return dependentObjects;
		dependentObjects.add(getValorproductocuantitativos());
		dependentObjects.add(getCategoria());
		dependentObjects.add(getProductosList());
		return dependentObjects;
	}

	@Override
	public void ejbRemove() {
		while (getProductosList().iterator().hasNext()) {
			Producto producto = getProductosList().iterator().next();
			getProductoDAO().delete(producto);
			producto.setCategoria(null);
			producto.setValorproductocuantitativos(null);
		}
		setProductosList(null);
	}

	@Override
	public void ejbActivate() throws EJBException, RemoteException {

	}

	@Override
	public void ejbLoad() throws EJBException, RemoteException {
		if (getProductosList().isEmpty()) {
			List<Producto> productos = getProductoDAO().findAll();
			setCoarseGrainedObjectData(productos.iterator().next()); // Actualiza
																		// en
																		// CGO
																		// con
																		// los
																		// datos
																		// del
																		// primer
																		// producto
																		// encontrado
																		// en
																		// BD.
			getProductosList().addAll(productos);

		} else {
			List<Producto> productos = getProductoDAO().findAll();
			setCoarseGrainedObjectData(productos.iterator().next());
			while (productos.iterator().hasNext()) {
				Producto producto = productos.iterator().next();
				if (!getProductosList().contains(producto)) {
					getProductosList().add(producto);
				}
			}
		}

	}

	public void ejbLoad(long idProducto) throws EJBException, RemoteException {
		Producto producto = getProductoDAO().findByIdProducto(idProducto);
		setCoarseGrainedObjectData(producto);
		if (!getProductosList().contains(producto)) {
			getProductosList().add(producto);
		}
	}

	@Override
	public void ejbStore() throws EJBException, RemoteException {
		if (getProductosList().isEmpty()) {
			throw new EJBException("No hay nada que guardar en Base de datos.");
		} else {
			while (getProductosList().iterator().hasNext()) {
				getProductoDAO().create(getProductosList().iterator().next());
			}
		}
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
		// idProducto = null;
	}

	@Override
	public int compareTo(Producto o) {
		return (this.idproducto < o.getIdproducto()) ? 1 : (this.idproducto > o.getIdproducto()) ? -1 : 0;
	}

	// =BUSINESS METHODS=================//
	// ==================================//
	/**
	 * Relaciona al ProductoCGO con una categoria existente.
	 * 
	 * @param categoria
	 * @return true- si relacionó, false si no relacionó.
	 */
	public boolean addCategoria(Categoria categoria) {
		boolean agrego = false;
		setCategoria(categoria);
		agrego = true;
		return agrego;
	}

	/**
	 * Adiciona una nueva característica al ProductoCGO.
	 * 
	 * @param caracteristica
	 * @return true- si adicionó, false si no adicionó.
	 */
	public boolean addCaracteristica(Valorproductocuantitativo caracteristica) {
		boolean agrego = false;
		valorproductocuantitativos.add(caracteristica);
		agrego = true;
		return agrego;
	}

	// =====================================================//

	// ===-GETTERS AND SETTERS==============//
	// =====================================//

	public EntityContext getContext() {
		return context;
	}

	public void setContext(EntityContext context) {
		this.context = context;
	}

	public ProductosDAO getProductoDAO() {
		return productoDAO;
	}

	public void setProductoDAO(ProductosDAO productoDAO) {
		this.productoDAO = productoDAO;
	}

	public long getIdproducto() {
		return idproducto;
	}

	public void setIdproducto(long idproducto) {
		this.idproducto = idproducto;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCondicion() {
		return condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEanUcc() {
		return eanUcc;
	}

	public void setEanUcc(String eanUcc) {
		this.eanUcc = eanUcc;
	}

	public BigDecimal getExistencias() {
		return existencias;
	}

	public void setExistencias(BigDecimal existencias) {
		this.existencias = existencias;
	}

	public BigDecimal getExistenciasminimas() {
		return existenciasminimas;
	}

	public void setExistenciasminimas(BigDecimal existenciasminimas) {
		this.existenciasminimas = existenciasminimas;
	}

	public Date getFechacreacion() {
		return fechacreacion;
	}

	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public String getGtin14() {
		return gtin14;
	}

	public void setGtin14(String gtin14) {
		this.gtin14 = gtin14;
	}

	public String getGtin8() {
		return gtin8;
	}

	public void setGtin8(String gtin8) {
		this.gtin8 = gtin8;
	}

	public String getMpn() {
		return mpn;
	}

	public void setMpn(String mpn) {
		this.mpn = mpn;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getPreciobase() {
		return preciobase;
	}

	public void setPreciobase(BigDecimal preciobase) {
		this.preciobase = preciobase;
	}

	public List<Detallecompra> getDetallecompras() {
		return detallecompras;
	}

	public void setDetallecompras(List<Detallecompra> detallecompras) {
		this.detallecompras = detallecompras;
	}

	public List<Detallesuministro> getDetallesuministros() {
		return detallesuministros;
	}

	public void setDetallesuministros(List<Detallesuministro> detallesuministros) {
		this.detallesuministros = detallesuministros;
	}

	public List<Detalletemporal> getDetalletemporals() {
		return detalletemporals;
	}

	public void setDetalletemporals(List<Detalletemporal> detalletemporals) {
		this.detalletemporals = detalletemporals;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Promocion> getPromocions() {
		return promocions;
	}

	public void setPromocions(List<Promocion> promocions) {
		this.promocions = promocions;
	}

	public List<Proveedorproducto> getProveedorproductos() {
		return proveedorproductos;
	}

	public void setProveedorproductos(List<Proveedorproducto> proveedorproductos) {
		this.proveedorproductos = proveedorproductos;
	}

	public List<Valorproductocuantitativo> getValorproductocuantitativos() {
		return valorproductocuantitativos;
	}

	public void setValorproductocuantitativos(List<Valorproductocuantitativo> valorproductocuantitativos) {
		this.valorproductocuantitativos = valorproductocuantitativos;
	}

	public List<Producto> getProductosList() {
		return productosList;
	}

	public void setProductosList(List<Producto> productosList) {
		this.productosList = productosList;
	}

	// =====================================//

}
