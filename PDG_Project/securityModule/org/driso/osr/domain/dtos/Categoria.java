package org.driso.osr.domain.dtos;

import java.io.Serializable;
import java.util.List;




public class Categoria implements Serializable{
 
    
	private static final long serialVersionUID = 2255138974354777724L;

	private Integer idCategoria;

    private String nombre;

    private String descripcion;
    
    private List<Producto> productos;
    
    private List<Interes> intereses;
    
    public Categoria( org.driso.osr.mapping.entities.Categoria categoria )
    {
        this.nombre = categoria.getNombre();
        this.descripcion = categoria.getDescripcion();
        this.idCategoria = categoria.getIdCategoria();
    }
    
    public Categoria() {
    }

    public Categoria(Integer idCategoria, String nombre, String descripcion) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public List<Interes> getIntereses() {
        return intereses;
    }

    public void setIntereses(List<Interes> intereses) {
        this.intereses = intereses;
    }

    
}
