package org.driso.osr.domain.dtos;

import java.io.Serializable;



public class DetalleFinal implements Serializable{
    
    
	private static final long serialVersionUID = -8589535932332834786L;

	private int unidades;
    
    private double descuentos;

    private double impuestos;

    private double subtotal;

    private Resena resena;
    
    private Producto producto;

    private Compra compra;

    
    public DetalleFinal( org.driso.osr.mapping.entities.DetalleFinal detalleFinal )
    {
        this.unidades = detalleFinal.getUnidades();
        this.descuentos = detalleFinal.getDescuentos().doubleValue();
        this.impuestos = detalleFinal.getImpuestos().doubleValue();
        this.subtotal = detalleFinal.getSubtotal().doubleValue();
        this.producto = new Producto(detalleFinal.getProducto());
        this.resena = new Resena(detalleFinal.getResenaId());
    }
    
    public DetalleFinal() {
    }

    public DetalleFinal(int unidades, double descuentos, double impuestos, 
           double subtotal, Resena resenaId, Compra compraId) {
        this.unidades = unidades;
        this.descuentos = descuentos;
        this.impuestos = impuestos;
        this.subtotal = subtotal;
        this.resena = resenaId;
        this.compra = compraId;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public double getDescuentos() {
        return descuentos;
    }

    public void setDescuentos(double descuentos) {
        this.descuentos = descuentos;
    }

    public double getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(double impuestos) {
        this.impuestos = impuestos;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Resena getResena() {
        return resena;
    }

    public void setResena(Resena resena) {
        this.resena = resena;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }
    
    
}
