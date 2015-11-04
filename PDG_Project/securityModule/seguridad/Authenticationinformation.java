package seguridad;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the AUTHENTICATIONINFORMATION database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "Authenticationinformation.findByCodSubject", query = "SELECT a FROM Authenticationinformation a WHERE a.codSubject = :codSubject") })

@NamedQuery(name = "Authenticationinformation.findAll", query = "SELECT a FROM Authenticationinformation a")
public class Authenticationinformation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "COD_SUBJECT")
	private long codSubject;

	private String autenticado;

	private String password;

	public Authenticationinformation() {
	}

	public Authenticationinformation(long codSubject, String password, String autenticado) {
		this.codSubject = codSubject;
		this.password = password;
		this.autenticado = autenticado;
	}

	public long getCodSubject() {
		return this.codSubject;
	}

	public void setCodSubject(long codSubject) {
		this.codSubject = codSubject;
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