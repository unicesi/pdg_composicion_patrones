package pdg.tienda.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the COMPRA database table.
 * 
 */
@Entity
@NamedQuery(name="Compra.findAll", query="SELECT c FROM Compra c")
public class Compra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private long idcompra;

	@Temporal(TemporalType.DATE)
	private Date fechacompra;

	@Temporal(TemporalType.DATE)
	private Date fechaentrega;

	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="IDESTADO")
	private Estado estado;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="IDUSUARIO")
	private Usuario usuario;

	//bi-directional many-to-one association to Detallecompra
	@OneToMany(mappedBy="compra")
	private List<Detallecompra> detallecompras;

	public Compra() {
	}

	public long getIdcompra() {
		return this.idcompra;
	}

	public void setIdcompra(long idcompra) {
		this.idcompra = idcompra;
	}

	public Date getFechacompra() {
		return this.fechacompra;
	}

	public void setFechacompra(Date fechacompra) {
		this.fechacompra = fechacompra;
	}

	public Date getFechaentrega() {
		return this.fechaentrega;
	}

	public void setFechaentrega(Date fechaentrega) {
		this.fechaentrega = fechaentrega;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Detallecompra> getDetallecompras() {
		return this.detallecompras;
	}

	public void setDetallecompras(List<Detallecompra> detallecompras) {
		this.detallecompras = detallecompras;
	}

	public Detallecompra addDetallecompra(Detallecompra detallecompra) {
		getDetallecompras().add(detallecompra);
		detallecompra.setCompra(this);

		return detallecompra;
	}

	public Detallecompra removeDetallecompra(Detallecompra detallecompra) {
		getDetallecompras().remove(detallecompra);
		detallecompra.setCompra(null);

		return detallecompra;
	}

}