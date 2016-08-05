package composition.tienda.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ORDENSUMINISTRO database table.
 * 
 */
@Entity
@NamedQuery(name="Ordensuministro.findAll", query="SELECT o FROM Ordensuministro o")
public class Ordensuministro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long idordensuministro;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	//bi-directional many-to-one association to Detallesuministro
	@OneToMany(mappedBy="ordensuministro")
	private List<Detallesuministro> detallesuministros;

	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="IDESTADO")
	private Estado estado;

	//bi-directional many-to-one association to Proveedor
	@ManyToOne
	@JoinColumn(name="IDPROVEEDOR")
	private Proveedor proveedor;

	public Ordensuministro() {
	}

	public long getIdordensuministro() {
		return this.idordensuministro;
	}

	public void setIdordensuministro(long idordensuministro) {
		this.idordensuministro = idordensuministro;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<Detallesuministro> getDetallesuministros() {
		return this.detallesuministros;
	}

	public void setDetallesuministros(List<Detallesuministro> detallesuministros) {
		this.detallesuministros = detallesuministros;
	}

	public Detallesuministro addDetallesuministro(Detallesuministro detallesuministro) {
		getDetallesuministros().add(detallesuministro);
		detallesuministro.setOrdensuministro(this);

		return detallesuministro;
	}

	public Detallesuministro removeDetallesuministro(Detallesuministro detallesuministro) {
		getDetallesuministros().remove(detallesuministro);
		detallesuministro.setOrdensuministro(null);

		return detallesuministro;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

}