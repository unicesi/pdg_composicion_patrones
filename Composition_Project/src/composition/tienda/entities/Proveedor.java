package composition.tienda.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PROVEEDOR database table.
 * 
 */
@Entity
@NamedQuery(name="Proveedor.findAll", query="SELECT p FROM Proveedor p")
public class Proveedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long idproveedor;

	private String celular;

	private String correo;

	private String direccion;

	private String nit;

	private String nombre;

	private String telefonofijo;

	//bi-directional many-to-one association to Ordensuministro
	@OneToMany(mappedBy="proveedor")
	private List<Ordensuministro> ordensuministros;

	//bi-directional many-to-one association to Ciudad
	@ManyToOne
	@JoinColumn(name="IDCIUDAD")
	private Ciudad ciudad;

	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="IDESTADO")
	private Estado estado;

	//bi-directional many-to-one association to Proveedorproducto
	@OneToMany(mappedBy="proveedor")
	private List<Proveedorproducto> proveedorproductos;

	public Proveedor() {
	}

	public long getIdproveedor() {
		return this.idproveedor;
	}

	public void setIdproveedor(long idproveedor) {
		this.idproveedor = idproveedor;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNit() {
		return this.nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefonofijo() {
		return this.telefonofijo;
	}

	public void setTelefonofijo(String telefonofijo) {
		this.telefonofijo = telefonofijo;
	}

	public List<Ordensuministro> getOrdensuministros() {
		return this.ordensuministros;
	}

	public void setOrdensuministros(List<Ordensuministro> ordensuministros) {
		this.ordensuministros = ordensuministros;
	}

	public Ordensuministro addOrdensuministro(Ordensuministro ordensuministro) {
		getOrdensuministros().add(ordensuministro);
		ordensuministro.setProveedor(this);

		return ordensuministro;
	}

	public Ordensuministro removeOrdensuministro(Ordensuministro ordensuministro) {
		getOrdensuministros().remove(ordensuministro);
		ordensuministro.setProveedor(null);

		return ordensuministro;
	}

	public Ciudad getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Proveedorproducto> getProveedorproductos() {
		return this.proveedorproductos;
	}

	public void setProveedorproductos(List<Proveedorproducto> proveedorproductos) {
		this.proveedorproductos = proveedorproductos;
	}

	public Proveedorproducto addProveedorproducto(Proveedorproducto proveedorproducto) {
		getProveedorproductos().add(proveedorproducto);
		proveedorproducto.setProveedor(this);

		return proveedorproducto;
	}

	public Proveedorproducto removeProveedorproducto(Proveedorproducto proveedorproducto) {
		getProveedorproductos().remove(proveedorproducto);
		proveedorproducto.setProveedor(null);

		return proveedorproducto;
	}

}