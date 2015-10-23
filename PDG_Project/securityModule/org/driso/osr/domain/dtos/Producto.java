package org.driso.osr.domain.dtos;

import java.io.Serializable;
import java.util.List;

public class Producto implements Serializable{

    
	private static final long serialVersionUID = 6889275235529904874L;

	private Integer productoId;

    private String nombre;

    private double precio;
    
    private int cantidadActual;
    
    private int cantidadCritica;

    private double calificacionPromedio;
    
    private String descripcion;

    private String imagen;
    
    private List<Categoria> categoriaList;

    private List<Caracteristica> caracteristicasList;

    private Promocion promocion;

    private List<DetalleFinal> detallesFinales;
    
    private List<Proveedor> proveedores;
    
    private List<Interes> intereses;
    
    private List<DetalleSuministro> detallesSuministro;
    
    private List<DetalleTemporal> detallesTemporales;
    
    public Producto( org.driso.osr.mapping.entities.Producto producto )
    {
        this.productoId = producto.getIdProducto();
        this.nombre = producto.getNombre();
        this.descripcion = producto.getDescripcion();
        this.calificacionPromedio = producto.getCalificacionPromedio();
        this.cantidadActual = producto.getCantidadActual();
        this.cantidadCritica = producto.getCantidadCritica();
        this.precio = producto.getPrecio().doubleValue();
        this.promocion = new Promocion(producto.getPromocion());
        this.imagen = producto.getImagen();
    }
    
    public Producto() {
    }

    public Producto(int productoId, String nombre, String descripcion) {
        this.productoId = productoId;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public boolean hasDiscount()
    {
    	return promocion == null;
    }
    
    public double getPriceAfterDiscount()
    {
    	double finalPrice = 0;
    	
    	if( this.promocion != null)
    	{
    		
    		double discount = this.promocion.getPorcentaje()*this.precio;
    		
    		finalPrice = this.precio - discount;
    		
    	}
    	
    	
    	return finalPrice;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }


    public int getCantidadActual() {
        return cantidadActual;
    }

    public void setCantidadActual(int cantidadActual) {
        this.cantidadActual = cantidadActual;
    }

    public int getCantidadCritica() {
        return cantidadCritica;
    }

    public void setCantidadCritica(int cantidadCritica) {
        this.cantidadCritica = cantidadCritica;
    }

    public double getCalificacionPromedio() {
        return calificacionPromedio;
    }

    public void setCalificacionPromedio(double calificacionPromedio) {
        this.calificacionPromedio = calificacionPromedio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public List<Categoria> getCategoriaList() {
        return categoriaList;
    }

    public void setCategoriaList(List<Categoria> categoriaList) {
        this.categoriaList = categoriaList;
    }

    public List<Caracteristica> getCaracteristicasList() {
        return caracteristicasList;
    }

    public void setCaracteristicasList(List<Caracteristica> caracteristicasList) {
        this.caracteristicasList = caracteristicasList;
    }

    public Promocion getPromocion() {
        return promocion;
    }

    public void setPromocion(Promocion promocion) {
        this.promocion = promocion;
    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public List<DetalleFinal> getDetallesFinales() {
        return detallesFinales;
    }

    public void setDetallesFinales(List<DetalleFinal> detallesFinales) {
        this.detallesFinales = detallesFinales;
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    public List<Interes> getIntereses() {
        return intereses;
    }

    public void setIntereses(List<Interes> intereses) {
        this.intereses = intereses;
    }

    public List<DetalleSuministro> getDetallesSuministro() {
        return detallesSuministro;
    }

    public void setDetallesSuministro(List<DetalleSuministro> detallesSuministro) {
        this.detallesSuministro = detallesSuministro;
    }

    public List<DetalleTemporal> getDetallesTemporales() {
        return detallesTemporales;
    }

    public void setDetallesTemporales(List<DetalleTemporal> detallesTemporales) {
        this.detallesTemporales = detallesTemporales;
    }

    
}
