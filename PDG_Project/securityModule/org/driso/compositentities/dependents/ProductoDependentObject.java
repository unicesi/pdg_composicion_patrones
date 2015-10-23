package org.driso.compositentities.dependents;

import org.driso.osr.domain.dtos.Caracteristica;
import org.driso.osr.domain.dtos.Categoria;
import org.driso.osr.domain.dtos.Producto;
import org.driso.osr.domain.util.ListMapper;
import org.driso.osr.exception.OSRException;
import org.driso.osr.exception.ProductExceptionCode;
import org.driso.osr.mapping.gateways.rows.CaracteristicaRowGateway;
import org.driso.osr.mapping.gateways.rows.CategoriaRowGateway;
import org.driso.osr.mapping.gateways.rows.ProductoRowGateway;
import org.driso.patterns.compositentity.ADependentObject;
import java.util.*;

public class ProductoDependentObject extends ADependentObject {

	private List<Producto> productos;

	public ProductoDependentObject() {
		super();
		productos = inicializarProductos();
	}

	private List<Producto> inicializarProductos() {
		List<org.driso.osr.mapping.entities.Producto> productosEJB = em.createNamedQuery("Producto.findAll")
				.getResultList();
		if (!productosEJB.isEmpty()) {

			return ListMapper.toProductoDTOList(productosEJB);

		} else {
			throw new OSRException(ProductExceptionCode.EMPTY_PRODUCT_LIST);
		}

	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

}
