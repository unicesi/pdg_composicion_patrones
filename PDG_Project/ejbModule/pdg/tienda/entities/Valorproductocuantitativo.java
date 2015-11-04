package pdg.tienda.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the VALORPRODUCTOCUANTITATIVO database table.
 * 
 */
@Entity
@NamedQuery(name="Valorproductocuantitativo.findAll", query="SELECT v FROM Valorproductocuantitativo v")
public class Valorproductocuantitativo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long idvalorcuantitativo;

	@Column(name="UNIDAD_MEDIDA")
	private String unidadMedida;

	private String valor;

	@Column(name="VALOR_MAX")
	private String valorMax;

	@Column(name="VALOR_MIN")
	private String valorMin;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="IDPRODUCTO")
	private Producto producto;

	public Valorproductocuantitativo() {
	}

	public long getIdvalorcuantitativo() {
		return this.idvalorcuantitativo;
	}

	public void setIdvalorcuantitativo(long idvalorcuantitativo) {
		this.idvalorcuantitativo = idvalorcuantitativo;
	}

	public String getUnidadMedida() {
		return this.unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getValorMax() {
		return this.valorMax;
	}

	public void setValorMax(String valorMax) {
		this.valorMax = valorMax;
	}

	public String getValorMin() {
		return this.valorMin;
	}

	public void setValorMin(String valorMin) {
		this.valorMin = valorMin;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}