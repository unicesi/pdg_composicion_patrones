package composition.java.patterns.service_locator.examples;

import java.util.HashMap;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import composition.tienda.entities.Categoria;
import composition.tienda.entities.Producto;
import composition.tienda.entities.Valorproductocuantitativo;

/**
 * Session Bean implementation class ProyectoEJB
 */
@Stateless
@LocalBean
public class ProductoEJB implements ProductoEJBRemote {

	/**
	 * Productos que maneja con llave:id valor:Producto
	 */
	private HashMap<Long, Producto> productos;

	@PersistenceContext(unitName = "Composition_Project")
	EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public ProductoEJB() {
		productos = new HashMap<Long, Producto>();
	}

	@Override
	public HashMap<Long, Producto> getProductos() {
		return productos;
	}

	@Override
	public Producto findByIdProducto(long idProducto) {

		if (productos.containsKey(idProducto)) {
			return productos.get(idProducto);
		} else {
			// Se encuentra el producto:
			Producto productoEncontrado = (Producto) getEntityManager().createNamedQuery("Producto.findById")
					.setParameter("idproducto", idProducto);
			// Se cargan sus caracter�sticas:
			productoEncontrado.setValorproductocuantitativos(findCaracteristicasByProducto(idProducto));
			// Se carga la informaci�n de su categor�a:
			productoEncontrado.setCategoria(findCategoriaById(productoEncontrado.getCategoria().getIdcategoria()));
			return productoEncontrado;
		}

	}

	/**
	 * Encuentra las caracter�sticas del producto cuya identificaci�n es pasada
	 * por par�metro.
	 * 
	 * @param idProducto
	 * @return Valorproductocuantitativo.
	 */
	private List<Valorproductocuantitativo> findCaracteristicasByProducto(long idProducto) {
		List<Valorproductocuantitativo> valoresproductocuantitativos = (List) getEntityManager()
				.createNamedQuery("Valorproductocuantitativo.findByIdAndByIdProducto")
				.setParameter("idProducto", idProducto).getResultList();
		return valoresproductocuantitativos;
	}

	/**
	 * Encuentra la categor�a perteneciente al producto cuya identificaci�n de
	 * la categor�a es pasada por par�metro. por par�metro.
	 * 
	 * @param idCategoria
	 * @return Categoria.
	 */
	private Categoria findCategoriaById(long idCategoria) {
		Categoria categoria = (Categoria) getEntityManager().createNamedQuery("Categoria.findById")
				.setParameter("idCategoria", idCategoria).getResultList().iterator().next();
		return categoria;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public boolean crearProducto(Producto producto) {
		productos.put(producto.getIdproducto(), producto);
		System.out.println("El producto se cre� localmente.");
		try {
			Categoria categoria = producto.getCategoria();
			List<Valorproductocuantitativo> valoresproductoscuantitativos = producto.getValorproductocuantitativos();
			if (valoresproductoscuantitativos != null) {
				while (valoresproductoscuantitativos.iterator().hasNext()) {
					Valorproductocuantitativo valorproductocuantitativo = valoresproductoscuantitativos.iterator()
							.next();
					getEntityManager().persist(valorproductocuantitativo);
				}
			} else {
				getEntityManager().persist(categoria);
				getEntityManager().persist(producto);
			}
			return true;
		} catch (Exception e) {
			System.err.println("Error de creaci�n de producto:" + e.getMessage());
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean inicializo() {
		return this.productos != null;
	}

}
