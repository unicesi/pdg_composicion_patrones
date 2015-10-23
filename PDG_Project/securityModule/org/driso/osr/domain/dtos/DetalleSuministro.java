package org.driso.osr.domain.dtos;

import java.io.Serializable;


public class DetalleSuministro implements Serializable{
    

    
	private static final long serialVersionUID = 7065306129271106933L;

	private int unidades;
    
    private Producto producto;
    
    private OrdenSuministro ordenSuministro;

    public DetalleSuministro() {
    }

    public DetalleSuministro(int unidades, Producto productoId,
            OrdenSuministro ordenSumId) {
        this.unidades = unidades;
        this.producto = productoId;
        this.ordenSuministro = ordenSumId;
    }

    public DetalleSuministro( org.driso.osr.mapping.entities.DetalleSuministro detalle )
    {
        this.producto = new Producto(detalle.getProducto());
        this.unidades = detalle.getUnidades();
        this.ordenSuministro = new OrdenSuministro(detalle.getOrdenSuministro());
    }
    
    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public OrdenSuministro getOrdenSuministro() {
        return ordenSuministro;
    }

    public void setOrdenSuministro(OrdenSuministro ordenSuministro) {
        this.ordenSuministro = ordenSuministro;
    }

    
}
