package seguridad;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the AUTHORIZATIONINFORMATION database table.
 * 
 */
@Entity
@NamedQuery(name = "Authorizationinformation.findAll", query = "SELECT a FROM Authorizationinformation a")
public class Authorizationinformation implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AuthorizationinformationPK id;

	@Column(name = "TIPO_ACCESO")
	private String tipoAcceso;

	public Authorizationinformation() {
	}

	public Authorizationinformation(AuthorizationinformationPK id, String tipoAcceso) {
		this.id = id;
		this.tipoAcceso = tipoAcceso;
	}

	public AuthorizationinformationPK getId() {
		return this.id;
	}

	public void setId(AuthorizationinformationPK id) {
		this.id = id;
	}

	public String getTipoAcceso() {
		return this.tipoAcceso;
	}

	public void setTipoAcceso(String tipoAcceso) {
		this.tipoAcceso = tipoAcceso;
	}

}