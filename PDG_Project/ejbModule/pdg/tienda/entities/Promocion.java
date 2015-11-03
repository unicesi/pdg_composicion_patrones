package pdg.tienda.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the PROMOCION database table.
 * 
 */
@Entity
@NamedQuery(name="Promocion.findAll", query="SELECT p FROM Promocion p")
public class Promocion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private long idpromocion;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_FIN")
	private Date fechaFin;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_INICIO")
	private Date fechaInicio;

	private String nombre;

	private double porcentaje;

	//bi-directional many-to-one association to Detallecompra
	@OneToMany(mappedBy="promocion")
	private List<Detallecompra> detallecompras;

	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="IDESTADO")
	private Estado estado;

	//bi-directional many-to-many association to Producto
	@ManyToMany
	@JoinTable(
		name="PRODUCTOPROMOCION"
		, joinColumns={
			@JoinColumn(name="IDPROMOCION")
			}
		, inverseJoinColumns={
			@JoinColumn(name="IDPRODUCTO")
			}
		)
	private List<Producto> productos;

	public Promocion() {
	}

	public long getIdpromocion() {
		return this.idpromocion;
	}

	public void setIdpromocion(long idpromocion) {
		this.idpromocion = idpromocion;
	}

	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPorcentaje() {
		return this.porcentaje;
	}

	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}

	public List<Detallecompra> getDetallecompras() {
		return this.detallecompras;
	}

	public void setDetallecompras(List<Detallecompra> detallecompras) {
		this.detallecompras = detallecompras;
	}

	public Detallecompra addDetallecompra(Detallecompra detallecompra) {
		getDetallecompras().add(detallecompra);
		detallecompra.setPromocion(this);

		return detallecompra;
	}

	public Detallecompra removeDetallecompra(Detallecompra detallecompra) {
		getDetallecompras().remove(detallecompra);
		detallecompra.setPromocion(null);

		return detallecompra;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

}