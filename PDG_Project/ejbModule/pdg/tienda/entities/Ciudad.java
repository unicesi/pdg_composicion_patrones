package pdg.tienda.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the CIUDAD database table.
 * 
 */
@Entity
@NamedQuery(name="Ciudad.findAll", query="SELECT c FROM Ciudad c")
public class Ciudad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long idciudad;

	@Column(name="CODIGO_DANE")
	private String codigoDane;

	private String nombre;

	//bi-directional many-to-one association to Departamento
	@ManyToOne
	@JoinColumn(name="IDDEPARTAMENTO")
	private Departamento departamento;

	//bi-directional many-to-one association to Proveedor
	@OneToMany(mappedBy="ciudad")
	private List<Proveedor> proveedors;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="ciudad")
	private List<Usuario> usuarios;

	public Ciudad() {
	}

	public long getIdciudad() {
		return this.idciudad;
	}

	public void setIdciudad(long idciudad) {
		this.idciudad = idciudad;
	}

	public String getCodigoDane() {
		return this.codigoDane;
	}

	public void setCodigoDane(String codigoDane) {
		this.codigoDane = codigoDane;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Departamento getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<Proveedor> getProveedors() {
		return this.proveedors;
	}

	public void setProveedors(List<Proveedor> proveedors) {
		this.proveedors = proveedors;
	}

	public Proveedor addProveedor(Proveedor proveedor) {
		getProveedors().add(proveedor);
		proveedor.setCiudad(this);

		return proveedor;
	}

	public Proveedor removeProveedor(Proveedor proveedor) {
		getProveedors().remove(proveedor);
		proveedor.setCiudad(null);

		return proveedor;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setCiudad(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setCiudad(null);

		return usuario;
	}

}