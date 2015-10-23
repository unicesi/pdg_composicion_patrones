package org.driso.compositentities.dependents;

import java.util.List;

import org.driso.osr.domain.dtos.Categoria;
import org.driso.osr.domain.util.ListMapper;
import org.driso.osr.exception.OSRException;
import org.driso.osr.exception.ProductExceptionCode;
import org.driso.patterns.compositentity.ADependentObject;

public class CategoriaDependentObject extends ADependentObject {

	private List<Categoria> categorias;

	public CategoriaDependentObject() {
		super();
		categorias = inicializarCategorias();
	}

	private List<Categoria> inicializarCategorias() {
		List<org.driso.osr.mapping.entities.Categoria> categoriasEJB = em.createNamedQuery("Categoria.findAll")
				.getResultList();
		if (!categoriasEJB.isEmpty()) {

			return ListMapper.toCategoriaDTOList(categoriasEJB);

		} else {
			throw new OSRException(ProductExceptionCode.EMPTY_PRODUCT_LIST);
		}

	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

}
