package org.driso.osr.domain.dtos;

import java.io.Serializable;


public class DetalleTemporal implements Serializable{

    
	private static final long serialVersionUID = 3236536950259230331L;

	private int unidades;
    
    private double subtotal;

    private Producto producto;

    private Carrito carrito;

    public DetalleTemporal() {
    }

    public DetalleTemporal(int unidades, double subtotal, Producto producto,
            Carrito carrito) {
        this.unidades = unidades;
        this.subtotal = subtotal;
        this.producto = producto;
        this.carrito = carrito;
    }

    public DetalleTemporal( org.driso.osr.mapping.entities.DetalleTemporal detalle )
    {
        this.carrito = new Carrito(detalle.getCarrito());
        this.producto = new Producto(detalle.getProducto());
        this.subtotal = detalle.getSubtotal().doubleValue();
        this.unidades = detalle.getUnidades();
    }
    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }
    
    
}
