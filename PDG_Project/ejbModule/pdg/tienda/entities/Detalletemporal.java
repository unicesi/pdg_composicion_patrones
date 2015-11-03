package pdg.tienda.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the DETALLETEMPORAL database table.
 * 
 */
@Entity
@NamedQuery(name="Detalletemporal.findAll", query="SELECT d FROM Detalletemporal d")
public class Detalletemporal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private long iddetalletemporal;

	private BigDecimal cantidad;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="IDPRODUCTO")
	private Producto producto;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="IDUSUARIO")
	private Usuario usuario;

	public Detalletemporal() {
	}

	public long getIddetalletemporal() {
		return this.iddetalletemporal;
	}

	public void setIddetalletemporal(long iddetalletemporal) {
		this.iddetalletemporal = iddetalletemporal;
	}

	public BigDecimal getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}