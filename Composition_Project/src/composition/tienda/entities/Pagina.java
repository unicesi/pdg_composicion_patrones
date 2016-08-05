package composition.tienda.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PAGINA database table.
 * 
 */
@Entity
@NamedQuery(name="Pagina.findAll", query="SELECT p FROM Pagina p")
public class Pagina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long idpagina;

	private String nombre;

	//bi-directional many-to-many association to Rol
	@ManyToMany(mappedBy="paginas")
	private List<Rol> rols;

	public Pagina() {
	}

	public long getIdpagina() {
		return this.idpagina;
	}

	public void setIdpagina(long idpagina) {
		this.idpagina = idpagina;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Rol> getRols() {
		return this.rols;
	}

	public void setRols(List<Rol> rols) {
		this.rols = rols;
	}

}