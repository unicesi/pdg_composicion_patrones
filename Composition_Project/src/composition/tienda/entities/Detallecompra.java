package composition.tienda.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the DETALLECOMPRA database table.
 * 
 */
@Entity
@NamedQuery(name="Detallecompra.findAll", query="SELECT d FROM Detallecompra d")
public class Detallecompra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long iddetalle;

	private BigDecimal calificacion;

	private BigDecimal cantidad;

	private BigDecimal precioventa;

	//bi-directional many-to-one association to Compra
	@ManyToOne
	@JoinColumn(name="IDCOMPRA")
	private Compra compra;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="IDPRODUCTO")
	private Producto producto;

	//bi-directional many-to-one association to Promocion
	@ManyToOne
	@JoinColumn(name="IDPROMOCION")
	private Promocion promocion;

	public Detallecompra() {
	}

	public long getIddetalle() {
		return this.iddetalle;
	}

	public void setIddetalle(long iddetalle) {
		this.iddetalle = iddetalle;
	}

	public BigDecimal getCalificacion() {
		return this.calificacion;
	}

	public void setCalificacion(BigDecimal calificacion) {
		this.calificacion = calificacion;
	}

	public BigDecimal getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getPrecioventa() {
		return this.precioventa;
	}

	public void setPrecioventa(BigDecimal precioventa) {
		this.precioventa = precioventa;
	}

	public Compra getCompra() {
		return this.compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Promocion getPromocion() {
		return this.promocion;
	}

	public void setPromocion(Promocion promocion) {
		this.promocion = promocion;
	}

}