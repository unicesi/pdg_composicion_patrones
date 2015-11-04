package seguridad.authentication;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the AUTHENTICATIONINFORMATION database table.
 * 
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "Authenticationinformation.findAll", query = "SELECT a FROM Authenticationinformation a") })
@NamedQuery(name = "Authenticationinformation.findAll", query = "SELECT a FROM Authenticationinformation a")
public class Authenticationinformation implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AuthenticationinformationPK id;

	private String autenticado;

	private String password;

	public Authenticationinformation() {
	}

	public Authenticationinformation(AuthenticationinformationPK id, String password, String autenticado) {
		this.id=id;
		this.password=password;
		this.autenticado=autenticado;
	}
	
	public AuthenticationinformationPK getId() {
		return this.id;
	}

	public void setId(AuthenticationinformationPK id) {
		this.id = id;
	}

	public String getAutenticado() {
		return this.autenticado;
	}

	public void setAutenticado(String autenticado) {
		this.autenticado = autenticado;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}