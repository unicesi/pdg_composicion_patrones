package org.driso.osr.domain.dtos;

import java.io.Serializable;
import org.driso.osr.mapping.entities.Caracteristicas;

public class Caracteristica implements Serializable{

    
	private static final long serialVersionUID = -1682002303326091107L;

	private Integer caracteristicaId;

    private String nombre;

    private String detalle;
    
    private Producto producto;

    public Caracteristica(Integer idCaracteristica, String nombre, String detalle,
                            Producto idProducto) {
        this.caracteristicaId = idCaracteristica;
        this.nombre = nombre;
        this.detalle = detalle;
        this.producto = idProducto;
    }

    public Caracteristica() {
    }

    public Caracteristica( Caracteristicas caracteristica )
    {
        this.caracteristicaId = caracteristica.getIdCaracteristica();
        this.detalle = caracteristica.getDetalle();
        this.nombre = caracteristica.getNombre();
        this.producto = new Producto(caracteristica.getProductoIdProducto());
    }
    
    public Integer getIdCaracteristica() {
        return caracteristicaId;
    }

    public void setIdCaracteristica(Integer idCaracteristica) {
        this.caracteristicaId = idCaracteristica;
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

    public Producto getProducto() {
        return producto;
    }

    public void setIdProducto(Producto idProducto) {
        this.producto = idProducto;
    }
    
}
