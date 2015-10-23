package org.driso.compositentities.dependents;

import org.driso.osr.domain.dtos.Caracteristica;
import org.driso.osr.domain.util.ListMapper;
import org.driso.osr.exception.OSRException;
import org.driso.osr.exception.ProductExceptionCode;
import org.driso.patterns.compositentity.ADependentObject;
import java.util.*;

public class CaracteristicaDependentObject extends ADependentObject {

	private List<Caracteristica> caracteristicas;

	public CaracteristicaDependentObject() {
		super();
		caracteristicas = inicializarCaracteristicas();
	}

	private List<Caracteristica> inicializarCaracteristicas() {
		List<org.driso.osr.mapping.entities.Caracteristicas> caracteristicasEJB = em
				.createNamedQuery("Caracteristicas.findAll").getResultList();
		if (!caracteristicasEJB.isEmpty()) {

			return ListMapper.toCaracteristicasDTOList(caracteristicasEJB);

		} else {
			throw new OSRException(ProductExceptionCode.EMPTY_PRODUCT_LIST);
		}

	}

	public List<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(List<Caracteristica> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

}
