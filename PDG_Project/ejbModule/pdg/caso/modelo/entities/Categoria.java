/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdg.caso.modelo.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import pdg.patrones.java.compositentity.dependentobjects.CategoriaDO;

/**
 *
 * @author SEBASTIAN
 */
@Entity
@Table
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c"),
		@NamedQuery(name = "Categoria.findByIdCategoria", query = "SELECT c FROM Categoria c WHERE c.idCategoria = :idCategoria"),
		@NamedQuery(name = "Categoria.findByNombre", query = "SELECT c FROM Categoria c WHERE c.nombre = :nombre"),
		@NamedQuery(name = "Categoria.findByDescripcion", query = "SELECT c FROM Categoria c WHERE c.descripcion = :descripcion") })
public class Categoria extends CategoriaDO implements Serializable {// Cambio de
																	// extensión
																	// de un DO.
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@SequenceGenerator(name = "CATEGORIAID_GENERATOR", sequenceName = "SEQ_CATEGORIA")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORIAID_GENERATOR")
	@Column(name = "id_categoria", nullable = false)
	private Integer idCategoria;
	@Basic(optional = false)
	@Column(nullable = false, length = 30)
	private String nombre;
	@Column(length = 500)
	private String descripcion;
	@JoinTable(name = "categoria_producto", joinColumns = {
			@JoinColumn(name = "categoria_id_categoria", referencedColumnName = "id_categoria", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "producto_id_producto", referencedColumnName = "id_producto", nullable = false) })
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Producto> productoList;
	@OneToMany(mappedBy = "categoriaIdCategoria", fetch = FetchType.LAZY)
	private List<Intereses> interesesList;

	public Categoria() {
	}

	public Categoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Categoria(Integer idCategoria, String nombre) {
		this.idCategoria = idCategoria;
		this.nombre = nombre;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@XmlTransient
	public List<Producto> getProductoList() {
		return productoList;
	}

	public void setProductoList(List<Producto> productoList) {
		this.productoList = productoList;
	}

	@XmlTransient
	public List<Intereses> getInteresesList() {
		return interesesList;
	}

	public void setInteresesList(List<Intereses> interesesList) {
		this.interesesList = interesesList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idCategoria != null ? idCategoria.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Categoria)) {
			return false;
		}
		Categoria other = (Categoria) object;
		if ((this.idCategoria == null && other.idCategoria != null)
				|| (this.idCategoria != null && !this.idCategoria.equals(other.idCategoria))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.driso.osr.mapping.entities.Categoria[ idCategoria=" + idCategoria + " ]";
	}

}
