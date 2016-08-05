package composition.tienda.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the DERECHO database table.
 * 
 */
@Embeddable
@NamedQueries({
		@NamedQuery(name = "Derecho.findById", query = "SELECT d FROM Derecho d WHERE d.id_subject = :idSubject AND d.name_protected_object = :nameProtectedObject"),
		@NamedQuery(name = "Derecho.findByNameProtectedObject", query = "SELECT d FROM Derecho d WHERE d.name_protected_object = :nameProtectedObject"),
		@NamedQuery(name = "Derecho.findByIdSubject", query = "SELECT d FROM Derecho d WHERE d.id_subject = :idSubject"), })
public class DerechoPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "NAME_PROTECTED_OBJECT")
	private String nameProtectedObject;

	@Column(name = "ID_SUBJECT")
	private long idSubject;

	public DerechoPK() {
	}

	public DerechoPK(long idSubject, String nameProtectedObject) {
		this.idSubject = idSubject;
		this.nameProtectedObject = nameProtectedObject;
	}

	public String getNameProtectedObject() {
		return this.nameProtectedObject;
	}

	public void setNameProtectedObject(String nameProtectedObject) {
		this.nameProtectedObject = nameProtectedObject;
	}

	public long getIdSubject() {
		return this.idSubject;
	}

	public void setIdSubject(long idSubject) {
		this.idSubject = idSubject;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DerechoPK)) {
			return false;
		}
		DerechoPK castOther = (DerechoPK) other;
		return this.nameProtectedObject.equals(castOther.nameProtectedObject)
				&& (this.idSubject == castOther.idSubject);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.nameProtectedObject.hashCode();
		hash = hash * prime + ((int) (this.idSubject ^ (this.idSubject >>> 32)));

		return hash;
	}
}