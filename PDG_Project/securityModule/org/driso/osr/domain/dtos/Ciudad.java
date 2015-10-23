package org.driso.osr.domain.dtos;

import java.util.List;



public class Ciudad {

    private int idCiudad;
    
    private String nombre;
    
    private Region region;

    private List<Proveedor> proveedores;
    
    private List<Usuario> usuarios;
    
    public Ciudad() {
    }

    public Ciudad(int idCiudad, String nombre, Region regionId) {
        this.idCiudad = idCiudad;
        this.nombre = nombre;
        this.region = regionId;
    }

    public Ciudad( org.driso.osr.mapping.entities.Ciudad ciudad )
    {
        this.idCiudad = ciudad.getIdCiudad();
        this.nombre = ciudad.getNombre();
        this.region = new Region(ciudad.getRegionIdRegion());
    }
    
    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

   
    
}
