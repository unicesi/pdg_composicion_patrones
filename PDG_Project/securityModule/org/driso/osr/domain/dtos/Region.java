package org.driso.osr.domain.dtos;

import java.io.Serializable;
import java.util.List;


public class Region implements Serializable{

    
	private static final long serialVersionUID = -5560775320102596383L;

	private Integer idRegion;

    private String nombre;

    private Pais pais;

    private List<Ciudad> ciudades;
    
    public Region() {
    }

    public Region(Integer idRegion, String nombre, Pais paisIdPais) {
        this.idRegion = idRegion;
        this.nombre = nombre;
        this.pais = paisIdPais;
    }

    public Region( org.driso.osr.mapping.entities.Region region )
    {
        this.idRegion = region.getIdRegion();
        this.nombre = region.getNombre();
        this.pais = new Pais( region.getPaisIdPais());
    }
    public Integer getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Integer idRegion) {
        this.idRegion = idRegion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public List<Ciudad> getCiudades() {
        return ciudades;
    }

    public void setCiudades(List<Ciudad> ciudades) {
        this.ciudades = ciudades;
    }
    
    
}
