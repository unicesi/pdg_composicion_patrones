package org.driso.osr.domain.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



public class Compra implements Serializable{

    
	private static final long serialVersionUID = 8950200561865982203L;

	private Integer idCompra;

    private Date fechaCompra;
    
    private double valorTotal;
    
    private Usuario usuario;
    
    private EstadoCompras estadoCompras;
    
    private List<DetalleFinal> detalleFinalList;

        
    public Compra() {
    }

    public Compra(int idCompra, Date fechaCompra, Usuario usuarioId,
            EstadoCompras estadoComprasId) {
        this.idCompra = idCompra;
        this.fechaCompra = fechaCompra;
        this.usuario = usuarioId;
        this.estadoCompras = estadoComprasId;
    }

    public Compra( org.driso.osr.mapping.entities.Compra compra )
    {
        this.fechaCompra = compra.getFechaCompra();
        this.idCompra = compra.getIdCompra();
        this.valorTotal = compra.getValorTotal().doubleValue();
        this.estadoCompras = new EstadoCompras(compra.getEstadoComprasIdEstado());
    }
    
    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuarioId) {
        this.usuario = usuarioId;
    }

    public EstadoCompras getEstadoCompras() {
        return estadoCompras;
    }

    public void setEstadoCompras(EstadoCompras estadoComprasId) {
        this.estadoCompras = estadoComprasId;
    }

    public List<DetalleFinal> getDetalleFinalList() {
        return detalleFinalList;
    }

    public void setDetalleFinalList(List<DetalleFinal> detalleFinalList) {
        this.detalleFinalList = detalleFinalList;
    }

    


}
