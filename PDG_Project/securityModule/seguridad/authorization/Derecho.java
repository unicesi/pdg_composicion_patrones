package seguridad.authorization;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: Derecho
 *
 */
@Entity
@Table
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "Derechos.findAll", query = "SELECT d FROM Derechos d"),
		@NamedQuery(name = "Derechos.findById", query = "SELECT d FROM Derechos d WHERE d.id_derecho = :idDerecho"),
		@NamedQuery(name = "Derechos.findByIdProtectedObject", query = "SELECT d FROM Derechos d WHERE d.id_protectedObject = :idProtectedObject"),
		@NamedQuery(name = "Derechos.findByCodSubject", query = "SELECT d FROM Derechos d WHERE d.cod_subject = :codSubject"), })
public class Derecho implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Id de un derecho.
	 */
	@Id
	@Basic(optional = false)
	@Column(name = "id_derecho", nullable = false)
	private Integer idDerecho;

	/**
	 * Identificación del registro derechos sobre el objeto protegido.
	 */
	@Basic(optional = false)
	@Column(name = "id_protectedObject", nullable = false)
	private Integer idProtectedObject;

	/**
	 * Código del Subject autorizado en el sistema.
	 */
	@Basic(optional = false)
	@Column(name = "cod_subject", nullable = false)
	private Integer codSubject;

	@Basic(optional = false)
	@Column(name = "tieneDerecho", nullable = false)
	private boolean tieneDerecho;

	@Basic(optional = true)
	@Column(name = "tipo_acceso", nullable = true)
	private String tipoAcceso;
	
	public Derecho() {
		super();
	}

	public Derecho(Integer idProtectedObject, Integer codSubject, boolean tieneDerecho){
		this.idProtectedObject=idProtectedObject;
		this.codSubject=codSubject;
		this.tieneDerecho=tieneDerecho;
	}

	public Integer getIdProtectedObject() {
		return idProtectedObject;
	}

	public void setIdProtectedObject(Integer idProtectedObject) {
		this.idProtectedObject = idProtectedObject;
	}

	public Integer getCodSubject() {
		return codSubject;
	}

	public void setCodSubject(Integer codSubject) {
		this.codSubject = codSubject;
	}

	public boolean isTieneDerecho() {
		return tieneDerecho;
	}

	public void setTieneDerecho(boolean tieneDerecho) {
		this.tieneDerecho = tieneDerecho;
	}

	public String getTipoAcceso() {
		return tipoAcceso;
	}

	public void setTipoAcceso(String tipoAcceso) {
		this.tipoAcceso = tipoAcceso;
	}
	
	
	
}
