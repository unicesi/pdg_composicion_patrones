package org.driso.osr.domain.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;




public class Carrito implements Serializable{
    

	private static final long serialVersionUID = 3889449112985996487L;

	private Integer carritoId;

    private Date fechaUltimamodificacion;
    
    private double valorAcumulado;

    private Usuario usuario;
    
    private List<DetalleTemporal> detalleTemporalList;

    public Carrito() {
    }

    public Carrito(Integer idCarro, Date fechaUltimamodificacion, 
            double valorAcumulado, Usuario usuarioId) {
        this.carritoId = idCarro;
        this.fechaUltimamodificacion = fechaUltimamodificacion;
        this.valorAcumulado = valorAcumulado;
        this.usuario = usuarioId;
    }

    public Carrito( org.driso.osr.mapping.entities.Carrito carrito )
    {
        this.carritoId = carrito.getIdCarro();
        this.fechaUltimamodificacion = carrito.getFechaUltimamodificacion();
        this.valorAcumulado = carrito.getValorAcumulado().doubleValue();
        this.usuario = new Usuario(carrito.getUsuario());
    }
    
    public Integer getIdCarro() {
        return carritoId;
    }

    public void setIdCarro(Integer idCarro) {
        this.carritoId = idCarro;
    }

    public Date getFechaUltimamodificacion() {
        return fechaUltimamodificacion;
    }

    public void setFechaUltimamodificacion(Date fechaUltimamodificacion) {
        this.fechaUltimamodificacion = fechaUltimamodificacion;
    }

    public double getValorAcumulado() {
        return valorAcumulado;
    }

    public void setValorAcumulado(double valorAcumulado) {
        this.valorAcumulado = valorAcumulado;
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuarioId) {
        this.usuario = usuarioId;
    }

    public List<DetalleTemporal> getDetalleTemporalList() {
        return detalleTemporalList;
    }

    public void setDetalleTemporalList(List<DetalleTemporal> detalleTemporalList) {
        this.detalleTemporalList = detalleTemporalList;
    }
    
    
    
}
