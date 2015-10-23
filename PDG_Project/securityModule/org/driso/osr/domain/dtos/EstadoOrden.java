package org.driso.osr.domain.dtos;

import java.io.Serializable;
import java.util.List;


public class EstadoOrden implements Serializable
{

    
	private static final long serialVersionUID = 189097212399281043L;

	private Integer estadoId;

    private String descripcion;

    private List<OrdenSuministro> ordenesSuministro;
    
    public EstadoOrden() {
    }

    public EstadoOrden(Integer estadoId, String descripcion) {
        this.estadoId = estadoId;
        this.descripcion = descripcion;
    }

    public EstadoOrden( org.driso.osr.mapping.entities.EstadoOrden estado )
    {
        this.estadoId = estado.getIdEstado();
        this.descripcion = estado.getValor();
    }
    
    public Integer getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Integer estadoId) {
        this.estadoId = estadoId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<OrdenSuministro> getOrdenesSuministro() {
        return ordenesSuministro;
    }

    public void setOrdenesSuministro(List<OrdenSuministro> ordenesSuministro) {
        this.ordenesSuministro = ordenesSuministro;
    }
    
    
    
}
