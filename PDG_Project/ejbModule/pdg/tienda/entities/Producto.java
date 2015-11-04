package pdg.tienda.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the PRODUCTO database table.
 * 
 */
@Entity
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long idproducto;

	private String color;

	private String condicion;

	private String descripcion;

	@Column(name="EAN_UCC")
	private String eanUcc;

	private BigDecimal existencias;

	private BigDecimal existenciasminimas;

	@Temporal(TemporalType.DATE)
	private Date fechacreacion;

	@Column(name="GTIN_14")
	private String gtin14;

	@Column(name="GTIN_8")
	private String gtin8;

	private String mpn;

	private String nombre;

	private BigDecimal preciobase;

	//bi-directional many-to-one association to Detallecompra
	@OneToMany(mappedBy="producto")
	private List<Detallecompra> detallecompras;

	//bi-directional many-to-one association to Detallesuministro
	@OneToMany(mappedBy="producto")
	private List<Detallesuministro> detallesuministros;

	//bi-directional many-to-one association to Detalletemporal
	@OneToMany(mappedBy="producto")
	private List<Detalletemporal> detalletemporals;

	//bi-directional many-to-one association to Categoria
	@ManyToOne
	@JoinColumn(name="IDCATEGORIA")
	private Categoria categoria;

	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="IDESTADO")
	private Estado estado;

	//bi-directional many-to-many association to Promocion
	@ManyToMany(mappedBy="productos")
	private List<Promocion> promocions;

	//bi-directional many-to-one association to Proveedorproducto
	@OneToMany(mappedBy="producto")
	private List<Proveedorproducto> proveedorproductos;

	//bi-directional many-to-one association to Valorproductocuantitativo
	@OneToMany(mappedBy="producto")
	private List<Valorproductocuantitativo> valorproductocuantitativos;

	public Producto() {
	}

	public long getIdproducto() {
		return this.idproducto;
	}

	public void setIdproducto(long idproducto) {
		this.idproducto = idproducto;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCondicion() {
		return this.condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEanUcc() {
		return this.eanUcc;
	}

	public void setEanUcc(String eanUcc) {
		this.eanUcc = eanUcc;
	}

	public BigDecimal getExistencias() {
		return this.existencias;
	}

	public void setExistencias(BigDecimal existencias) {
		this.existencias = existencias;
	}

	public BigDecimal getExistenciasminimas() {
		return this.existenciasminimas;
	}

	public void setExistenciasminimas(BigDecimal existenciasminimas) {
		this.existenciasminimas = existenciasminimas;
	}

	public Date getFechacreacion() {
		return this.fechacreacion;
	}

	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public String getGtin14() {
		return this.gtin14;
	}

	public void setGtin14(String gtin14) {
		this.gtin14 = gtin14;
	}

	public String getGtin8() {
		return this.gtin8;
	}

	public void setGtin8(String gtin8) {
		this.gtin8 = gtin8;
	}

	public String getMpn() {
		return this.mpn;
	}

	public void setMpn(String mpn) {
		this.mpn = mpn;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getPreciobase() {
		return this.preciobase;
	}

	public void setPreciobase(BigDecimal preciobase) {
		this.preciobase = preciobase;
	}

	public List<Detallecompra> getDetallecompras() {
		return this.detallecompras;
	}

	public void setDetallecompras(List<Detallecompra> detallecompras) {
		this.detallecompras = detallecompras;
	}

	public Detallecompra addDetallecompra(Detallecompra detallecompra) {
		getDetallecompras().add(detallecompra);
		detallecompra.setProducto(this);

		return detallecompra;
	}

	public Detallecompra removeDetallecompra(Detallecompra detallecompra) {
		getDetallecompras().remove(detallecompra);
		detallecompra.setProducto(null);

		return detallecompra;
	}

	public List<Detallesuministro> getDetallesuministros() {
		return this.detallesuministros;
	}

	public void setDetallesuministros(List<Detallesuministro> detallesuministros) {
		this.detallesuministros = detallesuministros;
	}

	public Detallesuministro addDetallesuministro(Detallesuministro detallesuministro) {
		getDetallesuministros().add(detallesuministro);
		detallesuministro.setProducto(this);

		return detallesuministro;
	}

	public Detallesuministro removeDetallesuministro(Detallesuministro detallesuministro) {
		getDetallesuministros().remove(detallesuministro);
		detallesuministro.setProducto(null);

		return detallesuministro;
	}

	public List<Detalletemporal> getDetalletemporals() {
		return this.detalletemporals;
	}

	public void setDetalletemporals(List<Detalletemporal> detalletemporals) {
		this.detalletemporals = detalletemporals;
	}

	public Detalletemporal addDetalletemporal(Detalletemporal detalletemporal) {
		getDetalletemporals().add(detalletemporal);
		detalletemporal.setProducto(this);

		return detalletemporal;
	}

	public Detalletemporal removeDetalletemporal(Detalletemporal detalletemporal) {
		getDetalletemporals().remove(detalletemporal);
		detalletemporal.setProducto(null);

		return detalletemporal;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Promocion> getPromocions() {
		return this.promocions;
	}

	public void setPromocions(List<Promocion> promocions) {
		this.promocions = promocions;
	}

	public List<Proveedorproducto> getProveedorproductos() {
		return this.proveedorproductos;
	}

	public void setProveedorproductos(List<Proveedorproducto> proveedorproductos) {
		this.proveedorproductos = proveedorproductos;
	}

	public Proveedorproducto addProveedorproducto(Proveedorproducto proveedorproducto) {
		getProveedorproductos().add(proveedorproducto);
		proveedorproducto.setProducto(this);

		return proveedorproducto;
	}

	public Proveedorproducto removeProveedorproducto(Proveedorproducto proveedorproducto) {
		getProveedorproductos().remove(proveedorproducto);
		proveedorproducto.setProducto(null);

		return proveedorproducto;
	}

	public List<Valorproductocuantitativo> getValorproductocuantitativos() {
		return this.valorproductocuantitativos;
	}

	public void setValorproductocuantitativos(List<Valorproductocuantitativo> valorproductocuantitativos) {
		this.valorproductocuantitativos = valorproductocuantitativos;
	}

	public Valorproductocuantitativo addValorproductocuantitativo(Valorproductocuantitativo valorproductocuantitativo) {
		getValorproductocuantitativos().add(valorproductocuantitativo);
		valorproductocuantitativo.setProducto(this);

		return valorproductocuantitativo;
	}

	public Valorproductocuantitativo removeValorproductocuantitativo(Valorproductocuantitativo valorproductocuantitativo) {
		getValorproductocuantitativos().remove(valorproductocuantitativo);
		valorproductocuantitativo.setProducto(null);

		return valorproductocuantitativo;
	}

}