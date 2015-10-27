package seguridad.authentication;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: AuthenticationInformation
 *
 */
@Entity
@Table
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "AuthenticationInformation.findAll", query = "SELECT a FROM AuthenticationInformation a"),
		@NamedQuery(name = "AuthenticationInformation.findById", query = "SELECT a FROM AuthenticationInformation a WHERE a.id_authenticator = :idAuthenticator"),
		@NamedQuery(name = "AuthenticationInformation.findByCodSubject", query = "SELECT a FROM AuthenticationInformation a WHERE a.cod_subject = :codSubject"),})
public class AuthenticationInformation implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Identificación del registro autenticador.
	 */
	@Id
	@Basic(optional = false)
	@Column(name = "id_authenticator", nullable = false)
	private Integer idAuthenticator;

	/**
	 * Código del Subject autenticado en el sistema.
	 */
	@Basic(optional = false)
	@Column(name = "cod_subject", nullable = false)
	private Integer codSubject;

	@Basic(optional = false)
	@Column(name = "autenticado", nullable = false)
	private boolean autenticado;
	
	/**
	 * Constructor vacío.
	 */
	public AuthenticationInformation() {
		super();

	}

	/**
	 * Constructor que inserta un registro con un código de authentication.
	 * 
	 * @param idAuthenticator
	 */
	public AuthenticationInformation(Integer idAuthenticator) {
		this.idAuthenticator = idAuthenticator;
	}

	/**
	 * Constructor que inserta un registro con un código de authentication y uno
	 * de sujeto para establecer la relación.
	 * 
	 * @param idAuthenticator
	 * @param codSubject
	 */
	public AuthenticationInformation(Integer idAuthenticator, Integer codSubject) {
		this.codSubject = codSubject;
	}

	public Integer getIdAuthenticator() {
		return idAuthenticator;
	}

	public void setIdAuthenticator(Integer idAuthenticator) {
		this.idAuthenticator = idAuthenticator;
	}

	public Integer getCodSubject() {
		return codSubject;
	}

	public void setCodSubject(Integer codSubject) {
		this.codSubject = codSubject;
	}

	public boolean isAutenticado() {
		return autenticado;
	}

	public void setAutenticado(boolean autenticado) {
		this.autenticado = autenticado;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idAuthenticator != null ? idAuthenticator.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof AuthenticationInformation)) {
			return false;
		}
		AuthenticationInformation other = (AuthenticationInformation) object;
		if ((this.idAuthenticator == null && other.idAuthenticator != null)
				|| (this.idAuthenticator != null && !this.idAuthenticator.equals(other.idAuthenticator))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "[ ID_Authenticator=" + idAuthenticator + " ]";
	}

}
