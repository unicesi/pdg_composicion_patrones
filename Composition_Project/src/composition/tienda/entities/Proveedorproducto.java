package composition.tienda.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PROVEEDORPRODUCTO database table.
 * 
 */
@Entity
@NamedQuery(name="Proveedorproducto.findAll", query="SELECT p FROM Proveedorproducto p")
public class Proveedorproducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProveedorproductoPK id;

	private BigDecimal precio;

	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="IDESTADO")
	private Estado estado;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="IDPRODUCTO")
	private Producto producto;

	//bi-directional many-to-one association to Proveedor
	@ManyToOne
	@JoinColumn(name="IDPROVEEDOR")
	private Proveedor proveedor;

	public Proveedorproducto() {
	}

	public ProveedorproductoPK getId() {
		return this.id;
	}

	public void setId(ProveedorproductoPK id) {
		this.id = id;
	}

	public BigDecimal getPrecio() {
		return this.precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

}