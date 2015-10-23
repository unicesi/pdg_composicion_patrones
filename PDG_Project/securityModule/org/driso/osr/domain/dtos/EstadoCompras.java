package org.driso.osr.domain.dtos;

import java.io.Serializable;
import java.util.List;



public class EstadoCompras implements Serializable{
    
    
	private static final long serialVersionUID = 2341835519882032405L;

	private Integer estadoId;
    
    private String valor;

    private List<Compra> compras;
    
    public EstadoCompras() {
    }

    public EstadoCompras(Integer estadoId, String valor) {
        this.estadoId = estadoId;
        this.valor = valor;
    }

    
    public EstadoCompras( org.driso.osr.mapping.entities.EstadoCompras estado )
    {
        this.estadoId = estado.getIdEstado();
        this.valor = estado.getValor();
    }
    
    public Integer getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Integer estadoId) {
        this.estadoId = estadoId;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }
    
    
    
}
