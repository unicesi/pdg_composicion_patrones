package seguridad.authentication;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the AUTHENTICATIONINFORMATION database table.
 * 
 */
@Embeddable
@NamedQueries({
		@NamedQuery(name = "Authenticationinformation.findByCodSubject", query = "SELECT a FROM Authenticationinformation a WHERE a.cod_subject = :codSubject"),
		@NamedQuery(name = "Authenticationinformation.findByIdSystem", query = "SELECT a FROM Authenticationinformation a WHERE a.id_system = :idSystem"),
		@NamedQuery(name = "Authenticationinformation.findByIdSystem_CodSubject", query = "SELECT a FROM Authenticationinformation a WHERE a.id_system = :idSystem AND a.cod_subject = :codSubject") })
public class AuthenticationinformationPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "ID_SYSTEM")
	private long idSystem;

	@Column(name = "COD_SUBJECT")
	private long codSubject;

	public AuthenticationinformationPK() {
	}

	public AuthenticationinformationPK(long idSystem, long codSubject) {
		this.idSystem=idSystem;
		this.codSubject=codSubject;
	}
	
	public long getIdSystem() {
		return this.idSystem;
	}

	public void setIdSystem(long idSystem) {
		this.idSystem = idSystem;
	}

	public long getCodSubject() {
		return this.codSubject;
	}

	public void setCodSubject(long codSubject) {
		this.codSubject = codSubject;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AuthenticationinformationPK)) {
			return false;
		}
		AuthenticationinformationPK castOther = (AuthenticationinformationPK) other;
		return (this.idSystem == castOther.idSystem) && (this.codSubject == castOther.codSubject);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.idSystem ^ (this.idSystem >>> 32)));
		hash = hash * prime + ((int) (this.codSubject ^ (this.codSubject >>> 32)));

		return hash;
	}
}