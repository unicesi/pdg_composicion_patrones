package org.driso.osr.domain.dtos;

import java.io.Serializable;
import org.driso.osr.mapping.entities.ParametrosOsr;


public class ParametroOSR implements Serializable{
    
    
	private static final long serialVersionUID = -3033814962741095399L;

	private Integer parametroId;
    
    private String nombre;
    
    private String valor;
    
    private TipoParametro tipoParametro;

    public ParametroOSR() {
    }

    
    public ParametroOSR(Integer parametroId, String nombre, 
            String valor, TipoParametro tipoParamId) {
        this.parametroId = parametroId;
        this.nombre = nombre;
        this.valor = valor;
        this.tipoParametro = tipoParamId;
    }

    public ParametroOSR( ParametrosOsr parametroOSR )
    {
        this.parametroId = parametroOSR.getIdParametro();
        this.nombre = parametroOSR.getNombre();
        this.valor = parametroOSR.getValor();
    }
    public Integer getParametroId() {
        return parametroId;
    }

    public void setParametroId(Integer parametroId) {
        this.parametroId = parametroId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public TipoParametro getTipoParametro() {
        return tipoParametro;
    }

    public void setTipoParametro(TipoParametro tipoParametro) {
        this.tipoParametro = tipoParametro;
    }
    
    
}
