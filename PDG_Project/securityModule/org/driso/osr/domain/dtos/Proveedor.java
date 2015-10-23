package org.driso.osr.domain.dtos;

import java.io.Serializable;
import java.util.List;


public class Proveedor implements Serializable{
    
    
	private static final long serialVersionUID = 8354037451682240893L;

	private Integer proveedorId;

    private String nit;

    private String nombre;

    private String correoContacto;

    private String celularContacto;

    private String direccion;

    private String telefonoFijo;

    private List<Ciudad> ciudades;
    
    private List<Producto> productos;
    
    private List<OrdenSuministro> ordenesSuministro;
    
    public Proveedor() {
    }

    public Proveedor(Integer proveedorId, String nit, String nombre, 
            String correoContacto, String celularContacto, 
            String direccion, String telefonoFijo) {
        this.proveedorId = proveedorId;
        this.nit = nit;
        this.nombre = nombre;
        this.correoContacto = correoContacto;
        this.celularContacto = celularContacto;
        this.telefonoFijo = telefonoFijo;
        this.direccion = direccion;
    }

    public Proveedor( org.driso.osr.mapping.entities.Proveedor proveedor )
    {
        this.proveedorId = proveedor.getIdProveedor();
        this.nombre = proveedor.getNombre();
        this.nit = proveedor.getNit();
        this.correoContacto = proveedor.getCorreoContacto();
        this.celularContacto = proveedor.getCelularContacto();
        this.telefonoFijo = proveedor.getTelefonoFijo();
        this.direccion = proveedor.getDireccion();
    }
    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoContacto() {
        return correoContacto;
    }

    public void setCorreoContacto(String correoContacto) {
        this.correoContacto = correoContacto;
    }

    public String getCelularContacto() {
        return celularContacto;
    }

    public void setCelularContacto(String celularContacto) {
        this.celularContacto = celularContacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public Integer getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Integer proveedorId) {
        this.proveedorId = proveedorId;
    }

    public List<Ciudad> getCiudades() {
        return ciudades;
    }

    public void setCiudades(List<Ciudad> ciudades) {
        this.ciudades = ciudades;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public List<OrdenSuministro> getOrdenesSuministro() {
        return ordenesSuministro;
    }

    public void setOrdenesSuministro(List<OrdenSuministro> ordenesSuministro) {
        this.ordenesSuministro = ordenesSuministro;
    }

    
    
    
}
