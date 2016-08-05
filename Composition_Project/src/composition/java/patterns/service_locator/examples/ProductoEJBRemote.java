package composition.java.patterns.service_locator.examples;

import java.util.HashMap;

import javax.ejb.Remote;

import composition.tienda.entities.Producto;

@Remote
public interface ProductoEJBRemote {

	/**
	 * Lista los productos existentes.
	 * 
	 * @return lista de productos List<Producto>
	 */
	public HashMap<Long,Producto> getProductos();

	/**
	 * Encuentra un producto dado el identificador.
	 * 
	 * @return
	 */
	public Producto findByIdProducto(long idProducto);

	/**
	 * Crea un producto en la base de datos y de manera local.
	 * 
	 * @param producto
	 * @return true- si fue agregado, false en caso contrario.
	 */
	public boolean crearProducto(Producto producto);

	/**
	 * Retorna un true si inicializó y tiene inicializada su lista de productos.
	 * @return true or false.
	 */
	public boolean inicializo(); 

}
