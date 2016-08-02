package composition.java.patterns.composite_entity.examples;

import java.util.*;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import composition.tienda.entities.Categoria;
import composition.tienda.entities.Producto;
import composition.tienda.entities.Valorproductocuantitativo;

@Stateless
@LocalBean
public class ProductosDAO {

	@PersistenceContext(unitName = "Composition_Project")
	EntityManager entityManager;

	public ProductosDAO() {
		super();
	}

	/**
	 * private void crearTabla() { entityManager.createQuery(
	 * "CREATE TABLE ProductoCGO(id_producto int NOT NULL, nombre varchar2(30) NOT NULL, precio decimal NOT NULL, cantidad_critica int NOT NULL, id_categoria int,   PRIMARY KEY(id_producto));"
	 * );
	 * 
	 * }
	 * 
	 */

	/**
	 * Encuentra un producto dado su identificación.
	 * 
	 * @param idProducto
	 * @return Producto con todas sus características y su categoria respectiva.
	 */
	public Producto findByIdProducto(long idProducto) {
		// Se encuentra el producto:
		Producto productoEncontrado = (Producto) getEntityManager().createNamedQuery("Producto.findById")
				.setParameter("idproducto", idProducto);
		// Se cargan sus características:
		productoEncontrado.setValorproductocuantitativos(findCaracteristicasByProducto(idProducto));
		// Se carga la información de su categoría:
		productoEncontrado.setCategoria(findCategoriaById(productoEncontrado.getCategoria().getIdcategoria()));
		return productoEncontrado;
	}

	/**
	 * Encuentra las características del producto cuya identificación es pasada
	 * por parámetro.
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
	 * Encuentra la categoría perteneciente al producto cuya identificación de
	 * la categoría es pasada por parámetro. por parámetro.
	 * 
	 * @param idCategoria
	 * @return Categoria.
	 */
	private Categoria findCategoriaById(long idCategoria) {
		Categoria categoria = (Categoria) getEntityManager().createNamedQuery("Categoria.findById")
				.setParameter("idCategoria", idCategoria).getResultList().iterator().next();
		return categoria;
	}

	/**
	 * Encuentra y carga todos los productos con sus características y la
	 * información de su categoría respectiva.
	 * 
	 * @return Collection productos.
	 */
	public List<Producto> findAll() {
		List<Producto> productos = (List) entityManager.createNamedQuery("Producto.findAll").getResultList();
		Iterator<Producto> iter = productos.iterator();
		while (iter.hasNext()) {
			Producto producto = iter.next();
			// Se cargan sus características:
			producto.setValorproductocuantitativos(findCaracteristicasByProducto(producto.getIdproducto()));
			// Se carga la información de su categoría:
			producto.setCategoria(findCategoriaById(producto.getCategoria().getIdcategoria()));

		}
		return productos;
	}

	/**
	 * Crea un producto y lo persiste.
	 * 
	 * @param producto
	 */
	public void create(Producto producto) {
		Categoria categoria = producto.getCategoria();
		List<Valorproductocuantitativo> valoresproductoscuantitativos = producto.getValorproductocuantitativos();
		while (valoresproductoscuantitativos.iterator().hasNext()) {
			Valorproductocuantitativo valorproductocuantitativo = valoresproductoscuantitativos.iterator().next();
			getEntityManager().persist(valorproductocuantitativo);
		}
		getEntityManager().persist(categoria);
		getEntityManager().persist(producto);
	}

	/**
	 * Actualiza la información de un producto en la base de datos.
	 * 
	 * @param producto
	 * @return Producto actualizado.
	 */
	public Producto update(Producto producto) {
		return getEntityManager().merge(producto);
	}

	/**
	 * Elimina un producto de la base de datos.
	 * 
	 * @param producto
	 *            a eliminar.
	 */
	public void delete(Producto producto) {
		while (producto.getValorproductocuantitativos().iterator().hasNext()) {
			Valorproductocuantitativo valorproductocuantitativo = producto.getValorproductocuantitativos().iterator()
					.next();
			getEntityManager().remove(valorproductocuantitativo);
		}
		getEntityManager().remove(producto);
	}

	// public boolean findACoarseGrainedObject(long primaryKey) {
	// ProductosCGO productoEncontrado = (ProductosCGO) entityManager
	// .createQuery("SELECT FROM ProductoCGO WHERE id_producto=:" + primaryKey +
	// ";");
	// return productoEncontrado != null;
	// }

	// /**
	// * Selecciona el ProductoCGO que tiene el idProducto indicado.
	// *
	// * @param idProducto-Id
	// * del producto coarse-grained object que se quiere buscar.
	// * @return ProductoCGO encontrado.
	// */
	// public ProductosCGO findProducto(Integer idProducto) {
	// ProductosCGO productoEncontrado = (ProductosCGO) entityManager
	// .createQuery("SELECT FROM ProductoCGO WHERE id_producto=:" + idProducto +
	// ");");
	// return productoEncontrado;
	// }

	// /**
	// * Retornan la lista de categorías a las que pertenece ese productoCGO.
	// *
	// * @param productoCGO
	// * @return lista de categorias.
	// */
	// public List<Categoria> loadCategorias(ProductosCGO productoCGO) {
	// List<Categoria> categorias = entityManager
	// .createQuery("SELECT c FROM Categoria c, Producto p WHERE p.idproducto =
	// " + productoCGO.getIdProducto()
	// + "AND c.idCategoria = :pCGO.id_categoria;")
	// .getResultList();
	//
	// return categorias;
	//
	// }

	// public List<Valorproductocuantitativo> loadCaracteristicas(ProductosCGO
	// productoCGO) {
	// List<Valorproductocuantitativo> caracteristicas =
	// entityManager.createQuery(
	// "SELECT c FROM Caracteristicas c WHERE c.producto_id_producto = :" +
	// productoCGO.getIdProducto() + ";")
	// .getResultList();
	// return caracteristicas;
	// }

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
