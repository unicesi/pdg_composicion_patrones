package org.driso.osr.domain.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class Promocion implements Serializable{

    
	private static final long serialVersionUID = -4524977035564980012L;

	private String descripcion;

    private Date fechaInicio;

    private Date fechaFin;
    
    private int promocionId;

    private String nombre;

    private double porcentaje;
    
    private List<org.driso.osr.mapping.entities.Producto> productos;
    
    
    public Promocion( org.driso.osr.mapping.entities.Promocion promocion )
    {
        this.descripcion = promocion.getDescripcion();
        this.fechaFin = promocion.getFechaFin();
        this.fechaInicio = promocion.getFechaInicio();
        this.nombre = promocion.getPromocionPK().getNombre();
        this.promocionId = promocion.getPromocionPK().getIdPromocion();
        this.porcentaje = promocion.getPorcentaje().doubleValue();
    }
    
    public Promocion() {
    }

    public Promocion(int promocionId, String nombre, String descripcion, Date fechaInicio, Date fechaFin, 
    		double porcentaje  ) 
    {
        
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.promocionId = promocionId;
        this.nombre = nombre;
        this.porcentaje =  porcentaje;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getPromocionId() {
        return promocionId;
    }

    public void setPromocionId(int promocion) {
        this.promocionId = promocion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}

	public List<org.driso.osr.mapping.entities.Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<org.driso.osr.mapping.entities.Producto> productos) {
        this.productos = productos;
    }
    
    
}
