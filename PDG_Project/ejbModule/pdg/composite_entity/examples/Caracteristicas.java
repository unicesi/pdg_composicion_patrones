/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pdg.composite_entity.examples;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import pdg.composite_entity.ADependentObject;

/**
 *
 * @author SEBASTIAN
 */
@Entity
@Table
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Caracteristicas.findAll", query = "SELECT c FROM Caracteristicas c"),
		@NamedQuery(name = "Caracteristicas.findByIdCaracteristica", query = "SELECT c FROM Caracteristicas c WHERE c.idCaracteristica = :idCaracteristica"),
		@NamedQuery(name = "Caracteristicas.findByNombre", query = "SELECT c FROM Caracteristicas c WHERE c.nombre = :nombre"),
		@NamedQuery(name = "Caracteristicas.findByDetalle", query = "SELECT c FROM Caracteristicas c WHERE c.detalle = :detalle") })
public class Caracteristicas extends ADependentObject implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@SequenceGenerator(name = "CARACTERISTICAID_GENERATOR", sequenceName = "SEQ_CARACTERISTICA")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CARACTERISTICAID_GENERATOR")
	@Column(name = "id_caracteristica", nullable = false)
	private Integer idCaracteristica;
	@Basic(optional = false)
	@Column(nullable = false, length = 30)
	private String nombre;
	@Basic(optional = false)
	@Column(nullable = false, length = 50)
	private String detalle;
	@JoinColumn(name = "producto_id_producto", referencedColumnName = "id_producto", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Producto productoIdProducto;

	public Caracteristicas() {
	}

	public Caracteristicas(Integer idCaracteristica) {
		this.idCaracteristica = idCaracteristica;
	}

	public Caracteristicas(Integer idCaracteristica, String nombre, String detalle) {
		this.idCaracteristica = idCaracteristica;
		this.nombre = nombre;
		this.detalle = detalle;
	}

	public Integer getIdCaracteristica() {
		return idCaracteristica;
	}

	public void setIdCaracteristica(Integer idCaracteristica) {
		this.idCaracteristica = idCaracteristica;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Producto getProductoIdProducto() {
		return productoIdProducto;
	}

	public void setProductoIdProducto(Producto productoIdProducto) {
		this.productoIdProducto = productoIdProducto;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idCaracteristica != null ? idCaracteristica.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Caracteristicas)) {
			return false;
		}
		Caracteristicas other = (Caracteristicas) object;
		if ((this.idCaracteristica == null && other.idCaracteristica != null)
				|| (this.idCaracteristica != null && !this.idCaracteristica.equals(other.idCaracteristica))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "org.driso.osr.mapping.entities.Caracteristicas[ idCaracteristica=" + idCaracteristica + " ]";
	}

	@Override
	protected Set<List> getDependentObjects() {
		// TODO Auto-generated method stub
		return null;
	}

}
