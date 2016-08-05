package composition.tienda.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the AUTHORIZATIONINFORMATION database table.
 * 
 */
@Embeddable
@NamedQueries({
	@NamedQuery(name = "Authorizationinformation.findById", query = "SELECT a FROM Authorizationinformation a WHERE a.cod_subject = :codSubject AND a.id_system = :idSystem"),
	@NamedQuery(name = "Authorizationinformation.findByCodSubject", query = "SELECT a FROM Authorizationinformation a WHERE a.cod_subject = :codSubject"),
	@NamedQuery(name = "Authorizationinformation.findByIdSystem", query = "SELECT a FROM Authorizationinformation a WHERE a.id_system = :idSystem")})
public class AuthorizationinformationPK implements Serializable {
	
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "COD_SUBJECT")
	private long codSubject;

	@Column(name = "ID_SYSTEM")
	private long idSystem;

	public AuthorizationinformationPK() {
	}

	public AuthorizationinformationPK(long codSubject, long idSystem) {
		this.codSubject = codSubject;
		this.idSystem = idSystem;
	}

	public long getCodSubject() {
		return this.codSubject;
	}

	public void setCodSubject(long codSubject) {
		this.codSubject = codSubject;
	}

	public long getIdSystem() {
		return this.idSystem;
	}

	public void setIdSystem(long idSystem) {
		this.idSystem = idSystem;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AuthorizationinformationPK)) {
			return false;
		}
		AuthorizationinformationPK castOther = (AuthorizationinformationPK) other;
		return (this.codSubject == castOther.codSubject) && (this.idSystem == castOther.idSystem);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.codSubject ^ (this.codSubject >>> 32)));
		hash = hash * prime + ((int) (this.idSystem ^ (this.idSystem >>> 32)));

		return hash;
	}
}