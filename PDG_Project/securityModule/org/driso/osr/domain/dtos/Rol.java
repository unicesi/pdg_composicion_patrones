package org.driso.osr.domain.dtos;

import java.io.Serializable;
import java.util.List;


public class Rol implements Serializable{

    
	private static final long serialVersionUID = 438897547674911060L;

	private Integer rolId;

    private String nombre;

    private List<Usuario> usuarios;
    
    private List<Pagina> paginas;
    
    public Rol(Integer idRol, String nombre) {
        this.rolId = idRol;
        this.nombre = nombre;
    }

    public Rol() {
    }

    public Rol( org.driso.osr.mapping.entities.Rol rol )
    {
        this.rolId = rol.getIdRol();
        this.nombre = rol.getNombre();
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getRolId() {
        return rolId;
    }

    public void setRolId(Integer rolId) {
        this.rolId = rolId;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Pagina> getPaginas() {
        return paginas;
    }

    public void setPaginas(List<Pagina> paginas) {
        this.paginas = paginas;
    }
    
    
}
