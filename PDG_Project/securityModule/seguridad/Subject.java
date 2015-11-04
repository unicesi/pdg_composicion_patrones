package seguridad;

import java.util.ArrayList;
import java.util.Collection;

import seguridad.authentication.ProofOfID;

public class Subject {

	/**
	 * Código que identifica a un sujeto dentro del sistema.
	 */
	private String codSubject;

	/**
	 * Password del sujeto dentro del sistema.
	 */
	private String password;

	/**
	 * Credenciales autenticadoras dentro del sistema de autenticaciones si las
	 * tiene.
	 */
	private Collection<ProofOfID> proofsOfID;

	/**
	 * Derechos que le dan permisos de acceso a los servicios de las entidades
	 * de negocio que maneja un sistema(Service Locator por ejemplo).
	 */
	private Collection<Derecho> derechos;

	public Subject(String codSubject, String password) {
		this.codSubject = codSubject;
		this.password = password;
		proofsOfID = new ArrayList<ProofOfID>();// Mientras se autentica.
		this.derechos = new ArrayList<Derecho>();
	}

	public String getCodSubject() {
		return codSubject;
	}

	public void setCodSubject(String codSubject) {
		this.codSubject = codSubject;
	}

	public Collection<ProofOfID> getProofsOfID() {
		return proofsOfID;
	}

	public void setProofsOfID(Collection<ProofOfID> proofsOfID) {
		this.proofsOfID = proofsOfID;
	}

	public Collection<Derecho> getDerechos() {
		return derechos;
	}

	public void setDerechos(Collection<Derecho> derechos) {
		this.derechos = derechos;
	}

	/**
	 * Agrega un proofOfId a éste subject.
	 * 
	 * @param proofOfID-ProofOfId
	 *            entregado por un sistema.
	 * @return true- si lo agregó, false- si no lo agregó.
	 */
	public boolean agregarProofOfId(ProofOfID proofOfID) {
		if (proofsOfID.contains(proofOfID)) {
			return false;
		} else {
			proofsOfID.add(proofOfID);
			return true;
		}
	}

	/**
	 * Asigna un derecho al sujeto.
	 * @param derecho
	 * @return true-si lo asignó. false- si no lo asignó.
	 */
	public boolean asignarDerecho(Derecho derecho) {
		if (derechos.contains(derecho)) {
			return false;
		} else {
			derechos.add(derecho);
			return true;
		}

	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String toString() {
		return "Sujeto: " + codSubject;
	}

}
