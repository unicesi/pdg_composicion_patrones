package pdg.composite_entity.examples;

import java.math.BigDecimal;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pdg.composite_entity.ACoarseDAO;

@Entity
public class ProductoDAO extends ACoarseDAO<ProductoCGO> {

	@PersistenceContext(name = "PDG_Project")
	EntityManager entityManager;

	public ProductoDAO() {
		super();
		crearTabla();// TODO- AVERIGUAR SI SE PUEDE INCLUIR Y DE UNA VEZ CARGAR
						// DE LA BASE DE DATOS.

	}

	private void crearTabla() {
		entityManager.createQuery(
				"CREATE TABLE ProductoCGO(id_producto int NOT NULL, nombre varchar2(30) NOT NULL, precio decimal NOT NULL, cantidad_critica int NOT NULL, id_categoria int,   PRIMARY KEY(id_producto));");

	}

	@Override
	public ProductoCGO findById(Integer idProducto) {
		ProductoCGO productoEncontrado = (ProductoCGO) entityManager
				.createQuery("SELECT FROM ProductoCGO WHERE id_producto=:" + idProducto + ");");
		productoEncontrado.setCategoriaList(loadCategorias(productoEncontrado));
		productoEncontrado.setCaracteristicasList(loadCaracteristicas(productoEncontrado));
		return productoEncontrado;

	}

	@Override
	public Collection<ProductoCGO> findAll() {
		List<ProductoCGO> productosCGO = entityManager.createQuery("SELECT pCGO FROM ProductoCGO pCGO;")
				.getResultList();
		return productosCGO;
	}

	@Override
	public void create(ProductoCGO aCoarseGrainedObject) {
		if (aCoarseGrainedObject != null) {
			Integer idProducto = aCoarseGrainedObject.getIdProducto();
			String nombre = aCoarseGrainedObject.getNombre();
			BigDecimal precio = aCoarseGrainedObject.getPrecio();
			int cantidadCritica = aCoarseGrainedObject.getCantidadCritica();
			List<Categoria> categorias = aCoarseGrainedObject.getCategoriaList();
			List<Caracteristicas> caracteristicas = aCoarseGrainedObject.getCaracteristicasList();
			Iterator<Categoria> categoriaIter = categorias.iterator();
			Iterator<Caracteristicas> caracteristicasIter = caracteristicas.iterator();
			while (categoriaIter.hasNext()) {
				Integer idCategoria = categoriaIter.next().getIdCategoria();
				entityManager.createQuery("INSERT INTO ProductoCGO values(" + idProducto + "," + nombre + "," + precio
						+ "," + cantidadCritica + "," + idCategoria + ");");
			}
			while (caracteristicasIter.hasNext()) {
				entityManager.persist(caracteristicasIter.next());// TODO-¿Cómo
																	// aseguro
																	// yo
																	// incluir
																	// las
																	// características
																	// y
																	// categorías
																	// en ese
																	// Coarse-grained-object?
			}
		} else {
			System.out.println("El coarse grained object pasado por parámetro es nulo.");
		}
	}

	@Override
	public void delete(Integer aCoarseGrainedObjectID) {
		// TODO

	}

	@Override
	public ProductoCGO update(ProductoCGO aCoarseGrainedObject) {
		// TODO
		return null;
	}

	@Override
	public boolean findACoarseGrainedObject(Integer primaryKey) {
		ProductoCGO productoEncontrado = (ProductoCGO) entityManager
				.createQuery("SELECT FROM ProductoCGO WHERE id_producto=:" + primaryKey + ";");
		return productoEncontrado != null;
	}

	/**
	 * Selecciona el ProductoCGO que tiene el idProducto indicado.
	 * 
	 * @param idProducto-Id
	 *            del producto coarse-grained object que se quiere buscar.
	 * @return ProductoCGO encontrado.
	 */
	public ProductoCGO findProducto(Integer idProducto) {
		ProductoCGO productoEncontrado = (ProductoCGO) entityManager
				.createQuery("SELECT FROM ProductoCGO WHERE id_producto=:" + idProducto + ");");
		return productoEncontrado;
	}

	/**
	 * Retornan la lista de categorías a las que pertenece ese productoCGO.
	 * 
	 * @param productoCGO
	 * @return lista de categorias.
	 */
	public List<Categoria> loadCategorias(ProductoCGO productoCGO) {
		List<Categoria> categorias = entityManager
				.createQuery("SELECT c FROM Categoria c, ProductoCGO pCGO WHERE pCGO.id_producto = :"
						+ productoCGO.getIdProducto() + "AND c.idCategoria = :pCGO.id_categoria;")
				.getResultList();

		return categorias;

	}

	public List<Caracteristicas> loadCaracteristicas(ProductoCGO productoCGO) {
		List<Caracteristicas> caracteristicas = entityManager.createQuery(
				"SELECT c FROM Caracteristicas c WHERE c.producto_id_producto = :" + productoCGO.getIdProducto() + ";")
				.getResultList();
		return caracteristicas;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
