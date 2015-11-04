package seguridad.authorization;

import seguridad.Subject;

public class Tester {

	public static void main(String[] args) {

		try {
			Subject subject = new Subject("12345", "passwordSubject");
			System.out.println("Sujeto: " + subject.toString());
			String nameProtectedObject = "pdg_jndi:pdg_pool/AUTHENTICATIONINFORMATION";
			System.out.println("Objeto protegido: " + nameProtectedObject);
			String tipoAcceso = "PERMITIDO";
			System.out.println("Tipo de derecho o acceso: " + tipoAcceso);
			DerechosDAO derechosDAO = new DerechosDAO();
			System.out.println("Agregó el derecho? " + derechosDAO
					.agregarDerecho(Long.parseLong(subject.getCodSubject()), nameProtectedObject, tipoAcceso));
			System.out.println("Petición de servicio del sujeto...");
			System.out.println("Está permitida la petición? Check right?"
					+ derechosDAO.checkRight(Long.parseLong(subject.getCodSubject()), nameProtectedObject));
			System.out.println("PRUEBA REALIZADA SATISFACTORIAMENTE");
			System.out.println("==========================================================================");
			System.out.println("SEGUNDA PRUEBA==================================================");
			AuthorizatorDAO authorizatorDAO = new AuthorizatorDAO();
			long idSystem = 2343;
			System.out.println("Id del sistema:" + idSystem);
			System.out.println("Agregó el authorizatorInformation? " + authorizatorDAO
					.agregarAuthorizationinformation(Long.parseLong(subject.getCodSubject()), idSystem, tipoAcceso));
			System.out.println("Petición del service locator al sistema...");
			System.out.println("Está permitida la petición? Check right?"
					+ authorizatorDAO.checkRight(Long.parseLong(subject.getCodSubject()), idSystem));
			System.out.println("PRUEBA REALIZADA SATISFACTORIAMENTE");
			System.out.println("==========================================================================");
		} catch (Exception e) {
			System.err.println("PRUEBAS FALLIDA");
			System.out.println("==========================================================================");
		}

	}

}
