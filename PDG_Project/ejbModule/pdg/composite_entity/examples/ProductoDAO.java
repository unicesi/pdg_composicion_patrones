package pdg.composite_entity.examples;

import java.util.Collection;

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
		entityManager = getEntityManager();
	}

	@Override
	public ProductoCGO findById(Integer idProducto) {
		ProductoCGO productoEncontrado = (ProductoCGO) entityManager
				.createQuery("SELECT FROM ProductoCGO WHERE id_producto=:" + String.valueOf(idProducto));
		return productoEncontrado;

	}

	@Override
	public Collection<ProductoCGO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(ProductoCGO aCoarseGrainedObject) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer aCoarseGrainedObjectID) {
		// TODO Auto-generated method stub

	}

	@Override
	public ProductoCGO update(ProductoCGO aCoarseGrainedObject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean findACoarseGrainedObject(Integer primaryKey) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Selecciona el ProductoCGO que tiene el idProducto indicado.
	 * 
	 * @param idProducto-Id
	 *            del producto coarse-grained object que se quiere buscar.
	 * @return ProductoCGO encontrado.
	 */
	public ProductoCGO findProducto(Integer idProducto) {

	}

}
