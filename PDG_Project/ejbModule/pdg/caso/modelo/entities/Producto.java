/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdg.caso.modelo.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import pdg.patrones.java.compositentity.dependentobjects.CaracteristicasDOLocal;
import pdg.patrones.java.compositentity.dependentobjects.CategoriaDOLocal;
import pdg.patrones.seguridad.compositentity.coarsegrainedobjects.ProductoCGO;

/**
 *
 * @author SEBASTIAN
 */
@Entity
@Table
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p"),
    @NamedQuery(name = "Producto.findByIdProducto", query = "SELECT p FROM Producto p WHERE p.idProducto = :idProducto"),
    @NamedQuery(name = "Producto.findByNombre", query = "SELECT p FROM Producto p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Producto.findByPrecio", query = "SELECT p FROM Producto p WHERE p.precio = :precio"),
    @NamedQuery(name = "Producto.findByCantidadActual", query = "SELECT p FROM Producto p WHERE p.cantidadActual = :cantidadActual"),
    @NamedQuery(name = "Producto.findByCantidadCritica", query = "SELECT p FROM Producto p WHERE p.cantidadCritica = :cantidadCritica"),
    @NamedQuery(name = "Producto.findByCalificacionPromedio", query = "SELECT p FROM Producto p WHERE p.calificacionPromedio = :calificacionPromedio"),
    @NamedQuery(name = "Producto.findByDescripcion", query = "SELECT p FROM Producto p WHERE p.descripcion = :descripcion")})
public class Producto extends ProductoCGO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @SequenceGenerator( name="PRODUCTOID_GENERATOR", sequenceName="SEQ_PRODUCTO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PRODUCTOID_GENERATOR")
    @Column(name = "id_producto", nullable = false)
    private Integer idProducto;
    @Basic(optional = false)
    @Column(nullable = false, length = 100)
    private String nombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;
    @Column(name = "cantidad_actual")
    private Integer cantidadActual;
    @Basic(optional = false)
    @Column(name = "cantidad_critica", nullable = false)
    private int cantidadCritica;
    @Column(name = "calificacion_promedio")
    private Integer calificacionPromedio;
    @Column(length = 2000)
    private String descripcion;
    @Column(length = 1000)
    private String imagen;
    @JoinTable(name = "proveedor_producto", joinColumns = {
        @JoinColumn(name = "producto_id_producto", referencedColumnName = "id_producto", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "proveedor_id_proveedor", referencedColumnName = "id_proveedor", nullable = false)})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Proveedor> proveedorList;
    @ManyToMany(mappedBy = "productoList", fetch = FetchType.LAZY)
    private List<Categoria> categoriaList;
    @OneToMany(mappedBy = "productoIdProducto", fetch = FetchType.LAZY)
    private List<Intereses> interesesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto", fetch = FetchType.LAZY)
    private List<DetalleSuministro> detalleSuministroList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoIdProducto", fetch = FetchType.LAZY)
    private List<Caracteristicas> caracteristicasList;
    @JoinColumns({
        @JoinColumn(name = "promocion_id", referencedColumnName = "id_promocion", nullable = false),
        @JoinColumn(name = "promocion_nombre", referencedColumnName = "nombre", nullable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Promocion promocion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto", fetch = FetchType.LAZY)
    private List<DetalleFinal> detalleFinalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto", fetch = FetchType.LAZY)
    private List<DetalleTemporal> detalleTemporalList;

    public Producto() {
    }

    public Producto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Producto(Integer idProducto, String nombre, BigDecimal precio, int cantidadCritica) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadCritica = cantidadCritica;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getCantidadActual() {
        return cantidadActual;
    }

    public void setCantidadActual(Integer cantidadActual) {
        this.cantidadActual = cantidadActual;
    }

    public int getCantidadCritica() {
        return cantidadCritica;
    }

    public void setCantidadCritica(int cantidadCritica) {
        this.cantidadCritica = cantidadCritica;
    }

    public Integer getCalificacionPromedio() {
        return calificacionPromedio;
    }

    public void setCalificacionPromedio(Integer calificacionPromedio) {
        this.calificacionPromedio = calificacionPromedio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Proveedor> getProveedorList() {
        return proveedorList;
    }

    public void setProveedorList(List<Proveedor> proveedorList) {
        this.proveedorList = proveedorList;
    }

    @XmlTransient
    public List<Categoria> getCategoriaList() {
        return categoriaList;
    }

    public void setCategoriaList(List<Categoria> categoriaList) {
        this.categoriaList = categoriaList;
    }

    @XmlTransient
    public List<Intereses> getInteresesList() {
        return interesesList;
    }

    public void setInteresesList(List<Intereses> interesesList) {
        this.interesesList = interesesList;
    }

    @XmlTransient
    public List<DetalleSuministro> getDetalleSuministroList() {
        return detalleSuministroList;
    }

    public void setDetalleSuministroList(List<DetalleSuministro> detalleSuministroList) {
        this.detalleSuministroList = detalleSuministroList;
    }

    @XmlTransient
    public List<Caracteristicas> getCaracteristicasList() {
        return caracteristicasList;
    }

    public void setCaracteristicasList(List<Caracteristicas> caracteristicasList) {
        this.caracteristicasList = caracteristicasList;
    }

    public Promocion getPromocion() {
        return promocion;
    }

    public void setPromocion(Promocion promocion) {
        this.promocion = promocion;
    }

    @XmlTransient
    public List<DetalleFinal> getDetalleFinalList() {
        return detalleFinalList;
    }

    public void setDetalleFinalList(List<DetalleFinal> detalleFinalList) {
        this.detalleFinalList = detalleFinalList;
    }

    @XmlTransient
    public List<DetalleTemporal> getDetalleTemporalList() {
        return detalleTemporalList;
    }

    public void setDetalleTemporalList(List<DetalleTemporal> detalleTemporalList) {
        this.detalleTemporalList = detalleTemporalList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProducto != null ? idProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.idProducto == null && other.idProducto != null) || (this.idProducto != null && !this.idProducto.equals(other.idProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.driso.osr.mapping.entities.Producto[ idProducto=" + idProducto + " ]";
    }

	
	@Override
	public Collection<CategoriaDOLocal> getCategorias() {
		
		return categoriaList;
	}

	@Override
	public void setCategorias(Collection<CategoriaDOLocal> categoria) {
		categoriaList=categorias;
		
	}

	@Override
	public Collection<CaracteristicasDOLocal> getCaracteristicas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCaracteristicas(Collection<CaracteristicasDOLocal> caracteristicas) {
		// TODO Auto-generated method stub
		
	}
    
}
