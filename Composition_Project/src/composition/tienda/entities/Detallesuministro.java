package composition.tienda.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the DETALLESUMINISTRO database table.
 * 
 */
@Entity
@NamedQuery(name="Detallesuministro.findAll", query="SELECT d FROM Detallesuministro d")
public class Detallesuministro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long iddetallesuministro;

	private BigDecimal cantidad;

	private BigDecimal valorunitario;

	//bi-directional many-to-one association to Ordensuministro
	@ManyToOne
	@JoinColumn(name="IDORDENSUMINISTRO1")
	private Ordensuministro ordensuministro;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="IDPRODUCTO")
	private Producto producto;

	public Detallesuministro() {
	}

	public long getIddetallesuministro() {
		return this.iddetallesuministro;
	}

	public void setIddetallesuministro(long iddetallesuministro) {
		this.iddetallesuministro = iddetallesuministro;
	}

	public BigDecimal getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getValorunitario() {
		return this.valorunitario;
	}

	public void setValorunitario(BigDecimal valorunitario) {
		this.valorunitario = valorunitario;
	}

	public Ordensuministro getOrdensuministro() {
		return this.ordensuministro;
	}

	public void setOrdensuministro(Ordensuministro ordensuministro) {
		this.ordensuministro = ordensuministro;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}