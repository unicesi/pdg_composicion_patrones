package org.driso.osr.domain.dtos;

import java.io.Serializable;
import java.util.List;



public class Pais implements Serializable{
    
    
	private static final long serialVersionUID = -3352852111225899898L;

	private Integer paisId;

    private String nombre;

    private List<Region> regiones;
    public Pais() {
    }

    public Pais(Integer paisId, String nombre) {
        this.paisId = paisId;
        this.nombre = nombre;
    }

    public Pais( org.driso.osr.mapping.entities.Pais pais )
    {
        this.paisId = pais.getIdPais();
        this.nombre = pais.getNombre();
    }
    public Integer getPaisId() {
        return paisId;
    }

    public void setPaisId(Integer paisId) {
        this.paisId = paisId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Region> getRegiones() {
        return regiones;
    }

    public void setRegiones(List<Region> regiones) {
        this.regiones = regiones;
    }
    
    
    
}
