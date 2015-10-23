package org.driso.osr.domain.dtos;

import java.util.Date;
import java.util.List;

public class Usuario {

    private Integer idUsuario;

    private String documentoIdentidad;

    private String correo;

    private byte[] contrasena;

    private String nombres;

    private String apelidos;

    private char sexo;

    private String celular;

    private String direccion;

    private String telefonoResidencia;

    private Date fechaNacimiento;
    
    private TipoDocumento tipoDocumento;

    private Ciudad ciudad;

    private Carrito carrito;

    private List<Rol> roles;
    
    private List<Compra> compras;
    
    private List<Interes> intereses;
    
    public Usuario()
    {
        
    }

    public Usuario(Integer idUsuario, String documentoIdentidad, String correo, byte[] contrasena, String nombres, String apelidos, char sexo, String celular, String direccion, String telefonoResidencia, Date fechaNaciminto, TipoDocumento tipoDocumento, Ciudad ciudad, Carrito carrito) {
        this.idUsuario = idUsuario;
        this.documentoIdentidad = documentoIdentidad;
        this.correo = correo;
        this.contrasena = contrasena;
        this.nombres = nombres;
        this.apelidos = apelidos;
        this.sexo = sexo;
        this.celular = celular;
        this.direccion = direccion;
        this.telefonoResidencia = telefonoResidencia;
        this.fechaNacimiento = fechaNaciminto;
        this.tipoDocumento = tipoDocumento;
        this.ciudad = ciudad;
        this.carrito = carrito;
    }

    
    public Usuario( org.driso.osr.mapping.entities.Usuario usuario )
    {
        this.idUsuario = usuario.getIdUsuario();
        this.documentoIdentidad = usuario.getDocumentoIdentidad();
        this.correo = usuario.getCorreo();
        this.contrasena = usuario.getContrasena();
        this.nombres = usuario.getNombres();
        this.apelidos = usuario.getApelidos();
        this.sexo = usuario.getSexo();
        this.celular = usuario.getCelular();
        this.direccion = usuario.getDireccion();
        this.telefonoResidencia = usuario.getTelefonoResidencia();
        this.fechaNacimiento = usuario.getFechaNaciminto();
        this.tipoDocumento = new TipoDocumento(usuario.getTipoDocumentoIdTpdoc());
        this.ciudad = new Ciudad(usuario.getCiudadIdCiudad());
        
    }
    
    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public byte[] getContrasena() {
        return contrasena;
    }

    public void setContrasena(byte[] contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApelidos() {
        return apelidos;
    }

    public void setApelidos(String apelidos) {
        this.apelidos = apelidos;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefonoResidencia() {
        return telefonoResidencia;
    }

    public void setTelefonoResidencia(String telefonoResidencia) {
        this.telefonoResidencia = telefonoResidencia;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }

    public List<Interes> getIntereses() {
        return intereses;
    }

    public void setIntereses(List<Interes> intereses) {
        this.intereses = intereses;
    }
   
    
}
