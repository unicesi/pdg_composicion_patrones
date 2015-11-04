package pdg.tienda.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ESTADO database table.
 * 
 */
@Entity
@NamedQuery(name="Estado.findAll", query="SELECT e FROM Estado e")
public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long idestado;

	private String nombreestado;

	//bi-directional many-to-one association to Compra
	@OneToMany(mappedBy="estado")
	private List<Compra> compras;

	//bi-directional many-to-one association to Ordensuministro
	@OneToMany(mappedBy="estado")
	private List<Ordensuministro> ordensuministros;

	//bi-directional many-to-one association to Producto
	@OneToMany(mappedBy="estado")
	private List<Producto> productos;

	//bi-directional many-to-one association to Promocion
	@OneToMany(mappedBy="estado")
	private List<Promocion> promocions;

	//bi-directional many-to-one association to Proveedor
	@OneToMany(mappedBy="estado")
	private List<Proveedor> proveedors;

	//bi-directional many-to-one association to Proveedorproducto
	@OneToMany(mappedBy="estado")
	private List<Proveedorproducto> proveedorproductos;

	//bi-directional many-to-one association to Rol
	@OneToMany(mappedBy="estado")
	private List<Rol> rols;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="estado")
	private List<Usuario> usuarios;

	public Estado() {
	}

	public long getIdestado() {
		return this.idestado;
	}

	public void setIdestado(long idestado) {
		this.idestado = idestado;
	}

	public String getNombreestado() {
		return this.nombreestado;
	}

	public void setNombreestado(String nombreestado) {
		this.nombreestado = nombreestado;
	}

	public List<Compra> getCompras() {
		return this.compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public Compra addCompra(Compra compra) {
		getCompras().add(compra);
		compra.setEstado(this);

		return compra;
	}

	public Compra removeCompra(Compra compra) {
		getCompras().remove(compra);
		compra.setEstado(null);

		return compra;
	}

	public List<Ordensuministro> getOrdensuministros() {
		return this.ordensuministros;
	}

	public void setOrdensuministros(List<Ordensuministro> ordensuministros) {
		this.ordensuministros = ordensuministros;
	}

	public Ordensuministro addOrdensuministro(Ordensuministro ordensuministro) {
		getOrdensuministros().add(ordensuministro);
		ordensuministro.setEstado(this);

		return ordensuministro;
	}

	public Ordensuministro removeOrdensuministro(Ordensuministro ordensuministro) {
		getOrdensuministros().remove(ordensuministro);
		ordensuministro.setEstado(null);

		return ordensuministro;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Producto addProducto(Producto producto) {
		getProductos().add(producto);
		producto.setEstado(this);

		return producto;
	}

	public Producto removeProducto(Producto producto) {
		getProductos().remove(producto);
		producto.setEstado(null);

		return producto;
	}

	public List<Promocion> getPromocions() {
		return this.promocions;
	}

	public void setPromocions(List<Promocion> promocions) {
		this.promocions = promocions;
	}

	public Promocion addPromocion(Promocion promocion) {
		getPromocions().add(promocion);
		promocion.setEstado(this);

		return promocion;
	}

	public Promocion removePromocion(Promocion promocion) {
		getPromocions().remove(promocion);
		promocion.setEstado(null);

		return promocion;
	}

	public List<Proveedor> getProveedors() {
		return this.proveedors;
	}

	public void setProveedors(List<Proveedor> proveedors) {
		this.proveedors = proveedors;
	}

	public Proveedor addProveedor(Proveedor proveedor) {
		getProveedors().add(proveedor);
		proveedor.setEstado(this);

		return proveedor;
	}

	public Proveedor removeProveedor(Proveedor proveedor) {
		getProveedors().remove(proveedor);
		proveedor.setEstado(null);

		return proveedor;
	}

	public List<Proveedorproducto> getProveedorproductos() {
		return this.proveedorproductos;
	}

	public void setProveedorproductos(List<Proveedorproducto> proveedorproductos) {
		this.proveedorproductos = proveedorproductos;
	}

	public Proveedorproducto addProveedorproducto(Proveedorproducto proveedorproducto) {
		getProveedorproductos().add(proveedorproducto);
		proveedorproducto.setEstado(this);

		return proveedorproducto;
	}

	public Proveedorproducto removeProveedorproducto(Proveedorproducto proveedorproducto) {
		getProveedorproductos().remove(proveedorproducto);
		proveedorproducto.setEstado(null);

		return proveedorproducto;
	}

	public List<Rol> getRols() {
		return this.rols;
	}

	public void setRols(List<Rol> rols) {
		this.rols = rols;
	}

	public Rol addRol(Rol rol) {
		getRols().add(rol);
		rol.setEstado(this);

		return rol;
	}

	public Rol removeRol(Rol rol) {
		getRols().remove(rol);
		rol.setEstado(null);

		return rol;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setEstado(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setEstado(null);

		return usuario;
	}

}