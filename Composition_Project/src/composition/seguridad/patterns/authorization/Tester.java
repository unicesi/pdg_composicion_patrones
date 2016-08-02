package composition.seguridad.patterns.authorization;

import composition.seguridad.patterns.resources.Subject;

public class Tester {

	public static void main(String[] args) {
		long t1 = System.currentTimeMillis();
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
			System.out.println("Petición de servicio por parte del sujeto...");
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
			System.err.println("PRUEBA FALLIDA");
			System.out.println("==========================================================================");
		}
		long t2 = System.currentTimeMillis();
		System.out.println("Tiempo de ejecución:" + (t2 - t1) + "milisegundos");
		System.out.println("==========================================================================");
	}

}
