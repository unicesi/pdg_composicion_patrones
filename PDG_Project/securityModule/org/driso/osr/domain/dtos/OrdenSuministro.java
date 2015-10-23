package org.driso.osr.domain.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class OrdenSuministro implements Serializable{
    
    
	private static final long serialVersionUID = -5390730007839289777L;

	private Integer ordenSumId;

    private Date fecha;

    private String observaciones;

    private Proveedor proveedor;

    private EstadoOrden estadoOrden;

    private List<DetalleSuministro> detalleSuministroList;

    public OrdenSuministro() {
    }

    public OrdenSuministro(Integer ordenSumId, Date fecha,
            String observaciones, Proveedor proveedorId, EstadoOrden estadoOrdenId) {
        this.ordenSumId = ordenSumId;
        this.fecha = fecha;
        this.observaciones = observaciones;
        this.proveedor = proveedorId;
        this.estadoOrden = estadoOrdenId;
    }

    public OrdenSuministro( org.driso.osr.mapping.entities.OrdenSuministro orden )
    {
        this.ordenSumId = orden.getIdOrden();
        this.observaciones = orden.getObservaciones();
        this.fecha = orden.getFecha();
        this.estadoOrden = new EstadoOrden(orden.getEstadoOrdenIdEstado());
        this.proveedor = new Proveedor(orden.getProveedorIdProveedor());
    }
    
    public Integer getOrdenSumId() {
        return ordenSumId;
    }

    public void setOrdenSumId(Integer ordenSumId) {
        this.ordenSumId = ordenSumId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public EstadoOrden getEstadoOrden() {
        return estadoOrden;
    }

    public void setEstadoOrden(EstadoOrden estadoOrden) {
        this.estadoOrden = estadoOrden;
    }

    public List<DetalleSuministro> getDetalleSuministroList() {
        return detalleSuministroList;
    }

    public void setDetalleSuministroList(List<DetalleSuministro> detalleSuministroList) {
        this.detalleSuministroList = detalleSuministroList;
    }
    
    
}
