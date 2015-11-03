package pdg.tienda.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PROVEEDORPRODUCTO database table.
 * 
 */
@Embeddable
public class ProveedorproductoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private long idproveedor;

	@Column(insertable=false, updatable=false)
	private long idproducto;

	public ProveedorproductoPK() {
	}
	public long getIdproveedor() {
		return this.idproveedor;
	}
	public void setIdproveedor(long idproveedor) {
		this.idproveedor = idproveedor;
	}
	public long getIdproducto() {
		return this.idproducto;
	}
	public void setIdproducto(long idproducto) {
		this.idproducto = idproducto;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProveedorproductoPK)) {
			return false;
		}
		ProveedorproductoPK castOther = (ProveedorproductoPK)other;
		return 
			(this.idproveedor == castOther.idproveedor)
			&& (this.idproducto == castOther.idproducto);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.idproveedor ^ (this.idproveedor >>> 32)));
		hash = hash * prime + ((int) (this.idproducto ^ (this.idproducto >>> 32)));
		
		return hash;
	}
}