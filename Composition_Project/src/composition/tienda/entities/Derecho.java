package composition.tienda.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the DERECHO database table.
 * 
 */
@Entity
@NamedQuery(name = "Derecho.findAll", query = "SELECT d FROM Derecho d")
public class Derecho implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DerechoPK id;

	@Column(name = "TIPO_ACCESO")
	private String tipoAcceso;

	public Derecho() {
	}

	public Derecho(DerechoPK id, String tipoAcceso) {
		this.id = id;
		this.tipoAcceso = tipoAcceso;
	}

	public DerechoPK getId() {
		return this.id;
	}

	public void setId(DerechoPK id) {
		this.id = id;
	}

	public String getTipoAcceso() {
		return this.tipoAcceso;
	}

	public void setTipoAcceso(String tipoAcceso) {
		this.tipoAcceso = tipoAcceso;
	}

}