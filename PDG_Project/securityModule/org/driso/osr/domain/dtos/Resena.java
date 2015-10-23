package org.driso.osr.domain.dtos;

import java.io.Serializable;
import java.util.Date;


public class Resena implements Serializable{
    
    
	private static final long serialVersionUID = 7186968739464384582L;

	private Integer resenaId;
    
    private String detalleResena;
    
    private Date fecha;
    
    private DetalleFinal detalleFinal;

    public Resena( org.driso.osr.mapping.entities.Resena resena )
    {
        this.detalleResena = resena.getDetalleResena();
        this.resenaId = resena.getIdResena();
        this.fecha = resena.getFecha();
        this.detalleFinal = new DetalleFinal(resena.getDetalleFinal());
    }
    
    public Resena() {
    }

    public Resena(int resenaId, String detalleResena, Date fecha, 
            DetalleFinal detalleFinalId) {
        this.resenaId = resenaId;
        this.detalleResena = detalleResena;
        this.fecha = fecha;
        this.detalleFinal = detalleFinalId;
    }



    public String getDetalleResena() {
        return detalleResena;
    }

    public void setDetalleResena(String detalleResena) {
        this.detalleResena = detalleResena;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getResenaId() {
        return resenaId;
    }

    public void setResenaId(Integer resenaId) {
        this.resenaId = resenaId;
    }

    public DetalleFinal getDetalleFinal() {
        return detalleFinal;
    }

    public void setDetalleFinal(DetalleFinal detalleFinalId) {
        this.detalleFinal = detalleFinalId;
    }

    
}
