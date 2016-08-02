package composition.java.patterns.composite_entity.examples;

import java.math.BigDecimal;

import javax.ejb.CreateException;
import javax.ejb.FinderException;

import composition.tienda.entities.Categoria;
import composition.tienda.entities.Producto;

public class TesterSinComponer {

	
	public static void main(String[] args) {
		long time1 = System.currentTimeMillis();
		ProductoCompositeEntity productoCompositeEntity = new ProductoCompositeEntity();
		System.out.println("Se cre� el composite");
		System.out.println("=================================================================");
		System.out.println("================================CREACI�N DEL PRODUCTO==========================");
		System.out.println("Se procede a crear el producto:");
		Categoria categoria = new Categoria();
		categoria.setIdcategoria(1);
		categoria.setNombre("Categor�a 1");
		System.out.println("Se crea la categoria:" + categoria.toString());
		Producto producto = new Producto();
		producto.setIdproducto(11);
		producto.setCategoria(categoria);
		producto.setColor("VERDE");
		producto.setDescripcion("Producto de prueba");
		producto.setExistencias(new BigDecimal(20000));
		producto.setExistenciasminimas(new BigDecimal(1000));
		producto.setNombre("ProductoPueba1");
		producto.setPreciobase(new BigDecimal(25000));
		System.out.println("Se cre� el producto:" + producto.toString());
		System.out.println(
				"============================CREACI�N FINALIZADA SATISFACTORIAMENTE==========================");
		System.out.println("=================================================================");
		System.out
				.println("===========================CREACI�N DEL PRODUCTO EN EL COMPOSITE==========================");
		long idProductoCreado = -1;
		try {
			idProductoCreado = productoCompositeEntity.ejbCreate(producto);
		} catch (CreateException e) {
			System.out.println("PRUEBA FALLIDA");
			System.out.println("=================================================================");
		}
		if (idProductoCreado == -1) {
			System.out.println("PRUEBA FALLIDA");
			System.out.println("=================================================================");
		} else {
			System.out.println("El id del producto que se agreg� fue:" + idProductoCreado);
			System.out.println(
					"=====================CREACI�N EN COMPOSITE FINALIZADA SATISFACTORIAMENTE==========================");

			System.out.println("=================================================================");
		}

		System.out
				.println("===========================B�SQUEDA DEL PRODUCTO EN EL COMPOSITE==========================");
		System.out.println("Se procede a buscar el producto agregado...");
		Producto productoBuscado = null;
		try {
			productoBuscado = productoCompositeEntity.ejbFindByPrimaryKey(Long.parseLong("11")); 
		} catch (FinderException e) {
			System.out.println("PRUEBA FALLIDA");
			System.out.println("=================================================================");
		}
		System.out.println("El producto buscado por usted es este:" + productoBuscado.toString());
		System.out.println(
				"============================B�SQUEDA FINALIZADA SATISFACTORIAMENTE==========================");
//		System.out.println("=================================================================");
//		System.out.println("==================AGREGAR NUEVO PRODUCTO EN EL COMPOSITE==========================");
//		System.out.println("Se procede a crear el producto:");
//		Categoria categoria2 = new Categoria();
//		categoria2.setIdcategoria(2);
//		categoria2.setNombre("Categor�a 2");
//		System.out.println("Se crea la categoria:" + categoria2.toString());
//		Producto producto2 = new Producto();
//		producto2.setIdproducto(22);
//		producto2.setCategoria(categoria2);
//		producto2.setColor("AZUL");
//		producto2.setDescripcion("Producto de prueba2");
//		producto2.setExistencias(new BigDecimal(20000));
//		producto2.setExistenciasminimas(new BigDecimal(1000));
//		producto2.setNombre("ProductoPueba2");
//		producto2.setPreciobase(new BigDecimal(25000));
//		System.out.println("Se cre� el producto:" + producto2.toString());
//		long idProductoCreado2 = -1;
//		try {
//			idProductoCreado2 = productoCompositeEntity.ejbCreate(producto2);
//		} catch (CreateException e) {
//			System.out.println("PRUEBA FALLIDA");
//			System.out.println("=================================================================");
//		}
//		if (idProductoCreado2 == -1) {
//			System.out.println("PRUEBA FALLIDA");
//			System.out.println("=================================================================");
//		} else {
//			System.out.println("El id del producto que se agreg� fue:" + idProductoCreado2);
//			System.out.println("El producto que qued� referenciado en el Composite fue:"
//					+ productoCompositeEntity.getProductoCGO().getNombre());
//			System.out.println(
//					"============================INSERCI�N FINALIZADA SATISFACTORIAMENTE==========================");
//
//		}
//
		System.out.println("==================PRODUCTOS EN EL COMPOSITE==========================");
		
		System.out.println(productoCompositeEntity.toString()); 

		long time2 = System.currentTimeMillis();

		System.out.println("=================================================================");

		System.out.println("Tiempo de ejecuci�n: " + ((time2 - time1) / 1) + " milisegundos.");

		System.out.println("=====================================================================");

	}

}
