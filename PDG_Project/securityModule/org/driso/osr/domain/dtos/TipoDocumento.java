package org.driso.osr.domain.dtos;

import java.io.Serializable;
import java.util.List;


public class TipoDocumento implements Serializable{

    
	private static final long serialVersionUID = -4042740753240076936L;

	private Integer tipoDocId;
    
    private String descripcion;

    private List<Usuario> usuarios;
    
    public TipoDocumento() {
    }

    public TipoDocumento(Integer tipoDocId, String descripcion) {
        this.tipoDocId = tipoDocId;
        this.descripcion = descripcion;
    }

    public TipoDocumento( org.driso.osr.mapping.entities.TipoDocumento tipoDocumento )
    {
        this.tipoDocId = tipoDocumento.getIdTpdoc();
        this.descripcion = tipoDocumento.getTipo();
    }
    
    public Integer getTipoDocId() {
        return tipoDocId;
    }

    public void setTipoDocId(Integer tipoDocId) {
        this.tipoDocId = tipoDocId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    
    
}
