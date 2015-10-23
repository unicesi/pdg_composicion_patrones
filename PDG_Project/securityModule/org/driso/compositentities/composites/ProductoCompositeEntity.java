package org.driso.compositentities.composites;

import org.driso.compositentities.coarsegrainedobjects.ProductoCoarseGrainedObject;
import org.driso.patterns.compositentity.ACompositeEntity;

public class ProductoCompositeEntity extends ACompositeEntity {

	public ProductoCompositeEntity(){
		this.coarseGranedObject=new ProductoCoarseGrainedObject();
	}
	
}
