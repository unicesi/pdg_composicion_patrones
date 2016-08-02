package composition.tienda.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the USUARIO database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long idusuario;

	private String apellido;

	private String celular;

	private String contrasena;

	private String correo;

	private String direccion;

	@Temporal(TemporalType.DATE)
	private Date fechanacimiento;

	private String nombre;

	private String numerodocumento;

	private String sexo;

	private String suscripcion;

	private String telefonoresidencia;

	//bi-directional many-to-one association to Compra
	@OneToMany(mappedBy="usuario")
	private List<Compra> compras;

	//bi-directional many-to-one association to Detalletemporal
	@OneToMany(mappedBy="usuario")
	private List<Detalletemporal> detalletemporals;

	//bi-directional many-to-one association to Ciudad
	@ManyToOne
	@JoinColumn(name="IDCIUDAD")
	private Ciudad ciudad;

	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="IDESTADO")
	private Estado estado;

	//bi-directional many-to-many association to Rol
	@ManyToMany
	@JoinTable(
		name="USUARIOROL"
		, joinColumns={
			@JoinColumn(name="IDUSUARIO")
			}
		, inverseJoinColumns={
			@JoinColumn(name="IDROL")
			}
		)
	private List<Rol> rols;

	//bi-directional many-to-one association to Tipodocumento
	@ManyToOne
	@JoinColumn(name="IDTIPODOCUMENTO")
	private Tipodocumento tipodocumento;

	public Usuario() {
	}

	public long getIdusuario() {
		return this.idusuario;
	}

	public void setIdusuario(long idusuario) {
		this.idusuario = idusuario;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
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

	public Date getFechanacimiento() {
		return this.fechanacimiento;
	}

	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumerodocumento() {
		return this.numerodocumento;
	}

	public void setNumerodocumento(String numerodocumento) {
		this.numerodocumento = numerodocumento;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getSuscripcion() {
		return this.suscripcion;
	}

	public void setSuscripcion(String suscripcion) {
		this.suscripcion = suscripcion;
	}

	public String getTelefonoresidencia() {
		return this.telefonoresidencia;
	}

	public void setTelefonoresidencia(String telefonoresidencia) {
		this.telefonoresidencia = telefonoresidencia;
	}

	public List<Compra> getCompras() {
		return this.compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public Compra addCompra(Compra compra) {
		getCompras().add(compra);
		compra.setUsuario(this);

		return compra;
	}

	public Compra removeCompra(Compra compra) {
		getCompras().remove(compra);
		compra.setUsuario(null);

		return compra;
	}

	public List<Detalletemporal> getDetalletemporals() {
		return this.detalletemporals;
	}

	public void setDetalletemporals(List<Detalletemporal> detalletemporals) {
		this.detalletemporals = detalletemporals;
	}

	public Detalletemporal addDetalletemporal(Detalletemporal detalletemporal) {
		getDetalletemporals().add(detalletemporal);
		detalletemporal.setUsuario(this);

		return detalletemporal;
	}

	public Detalletemporal removeDetalletemporal(Detalletemporal detalletemporal) {
		getDetalletemporals().remove(detalletemporal);
		detalletemporal.setUsuario(null);

		return detalletemporal;
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

	public List<Rol> getRols() {
		return this.rols;
	}

	public void setRols(List<Rol> rols) {
		this.rols = rols;
	}

	public Tipodocumento getTipodocumento() {
		return this.tipodocumento;
	}

	public void setTipodocumento(Tipodocumento tipodocumento) {
		this.tipodocumento = tipodocumento;
	}

}