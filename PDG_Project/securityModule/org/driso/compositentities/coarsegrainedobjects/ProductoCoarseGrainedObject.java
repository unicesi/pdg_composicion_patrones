/**
 * 
 */
package org.driso.compositentities.coarsegrainedobjects;

import java.util.*;

import org.driso.compositentities.dependents.CaracteristicaDependentObject;
import org.driso.compositentities.dependents.CategoriaDependentObject;
import org.driso.compositentities.dependents.ProductoDependentObject;
import org.driso.osr.domain.dtos.Caracteristica;
import org.driso.osr.domain.dtos.Categoria;
import org.driso.osr.domain.dtos.Producto;
import org.driso.osr.domain.util.ListMapper;
import org.driso.osr.exception.OSRException;
import org.driso.osr.exception.ProductExceptionCode;
import org.driso.patterns.compositentity.ACoarseGranedObject;

public class ProductoCoarseGrainedObject extends ACoarseGranedObject {

	private ProductoDependentObject productos;
	private CaracteristicaDependentObject caracteristicas;
	private CategoriaDependentObject categorias;

	public ProductoCoarseGrainedObject() {
		super();
		productos = new ProductoDependentObject();
		caracteristicas = new CaracteristicaDependentObject();
		categorias = new CategoriaDependentObject();
		dependentObjects.add(productos);
		dependentObjects.add(caracteristicas);
		dependentObjects.add(categorias);
	}

	public ProductoDependentObject getProductos() {
		return productos;
	}

	public void setProductos(ProductoDependentObject productos) {
		this.productos = productos;
	}

	public CaracteristicaDependentObject getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(CaracteristicaDependentObject caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public CategoriaDependentObject getCategorias() {
		return categorias;
	}

	public void setCategorias(CategoriaDependentObject categorias) {
		this.categorias = categorias;
	}

	// ------------------------------------------//

}
