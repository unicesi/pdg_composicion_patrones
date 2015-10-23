package org.driso.osr.domain.dtos;

import java.io.Serializable;
import org.driso.osr.mapping.entities.Intereses;


public class Interes implements Serializable{
    
    
	private static final long serialVersionUID = 3289543866801853985L;

	private Integer idInteres;

    private Usuario usuario;
    
    private Producto producto;
    
    private Categoria categoria;

    public Interes() {
    }

    public Interes(Integer idInteres, Usuario usuarioId,
            Producto productoId, Categoria categoriaId) {
        this.idInteres = idInteres;
        this.usuario = usuarioId;
        this.producto = productoId;
        this.categoria = categoriaId;
    }

    public Interes( Intereses interes )
    {
        this.idInteres = interes.getIdInteres();
        this.usuario = new Usuario(interes.getUsuarioIdUsuario());
        this.categoria = new Categoria( interes.getCategoriaIdCategoria() ); 
        this.producto = new Producto( interes.getProductoIdProducto() );
    }
    public Integer getIdInteres() {
        return idInteres;
    }

    public void setIdInteres(Integer idInteres) {
        this.idInteres = idInteres;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    
}
