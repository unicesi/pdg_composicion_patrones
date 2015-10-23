package org.driso.osr.domain.dtos;

import java.io.Serializable;
import java.util.List;

public class Pagina implements Serializable{
    
    
	private static final long serialVersionUID = -1259749740596316550L;

	private int paginaId;
    
    private String url;

    private List<Rol> roles;
    
    public Pagina() {
    }

    public Pagina(int paginaId, String url) {
        this.paginaId = paginaId;
        this.url = url;
    }

    public Pagina( org.driso.osr.mapping.entities.Pagina pagina )
    {
        this.paginaId = pagina.getIdPagina();
        this.url = pagina.getUrlPagina();
    }
    public int getPaginaId() {
        return paginaId;
    }

    public void setPaginaId(int paginaId) {
        this.paginaId = paginaId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }
    
    
}
