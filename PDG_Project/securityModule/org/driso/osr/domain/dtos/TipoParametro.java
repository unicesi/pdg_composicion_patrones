package org.driso.osr.domain.dtos;

import java.io.Serializable;
import java.util.List;


public class TipoParametro implements Serializable{

    
	private static final long serialVersionUID = 3576468092404912155L;

	private Integer tipoParamId;
 
    private String nombre;

    private List<ParametroOSR> parametrosOSR;
    
    public TipoParametro() {
    }

    public TipoParametro(Integer tipoParamId, String nombre) {
        this.tipoParamId = tipoParamId;
        this.nombre = nombre;
    }

    public TipoParametro( org.driso.osr.mapping.entities.TipoParametro tipoParametro )
    {
        this.tipoParamId = tipoParametro.getIdTipo();
        this.nombre = tipoParametro.getNombre();
    }
    
    public Integer getTipoParamId() {
        return tipoParamId;
    }

    public void setTipoParamId(Integer tipoParamId) {
        this.tipoParamId = tipoParamId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<ParametroOSR> getParametrosOSR() {
        return parametrosOSR;
    }

    public void setParametrosOSR(List<ParametroOSR> parametrosOSR) {
        this.parametrosOSR = parametrosOSR;
    }

    
}
