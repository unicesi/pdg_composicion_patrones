package seguridad.authentication;

import seguridad.Subject;

public class Tester {

	public static void main(String[] args) {

		long time1 = System.currentTimeMillis();
		Subject subject = new Subject("12345", "passwordSubject");
		Subject subject2 = new Subject("58901", "passwordSubject2");
		System.out.println("Se cre� el sujeto:" + subject.toString());
		String idSystem = "56789";
		System.out.println("El id de este sistema es:" + idSystem);
		Authenticator authenticator = new Authenticator(idSystem);
		System.out.println("Se cre� el autenticador para este sistema: " + authenticator.toString());
		System.out.println("Se proceder� a autenticar al sujeto...");
		System.out.println("Autentic�? " + authenticator.autenticar(subject));
		System.out.println("Autentic�2? " + authenticator.autenticar(subject2));
		System.out.println("Autenticado: "
				+ ((Authenticationinformation) authenticator.getAuthenticationInformations().iterator().next())
						.toString());

		try {
			System.out.println("Se proceder� a generar el proof of id del usuario....");
			ProofOfID proofOfID = authenticator.login(subject.getCodSubject(), subject.getPassword());
			ProofOfID proofOfID2 = authenticator.login(subject2.getCodSubject(), subject2.getPassword());
			System.out.println("Ya se obtuvo el proof of id:" + proofOfID.toString());
			System.out.println("Ya se obtuvo el proof of id2:" + proofOfID2.toString());
			System.out.println("Se asignar� el PoId al sujeto... ");
			subject.agregarProofOfId(proofOfID);
			subject2.agregarProofOfId(proofOfID2);
			System.out.println("Se agreg� el proof of id en el sujeto:");
			System.out.println("Id en posesi�n del sujeto:" + subject.toString() + " con PoId: "
					+ subject.getProofsOfID().iterator().next().toString());
			System.out.println("Se agreg� el proof of id en el sujeto2:");
			System.out.println("Id en posesi�n del sujeto2:" + subject2.toString() + " con PoId: "
					+ subject2.getProofsOfID().iterator().next().toString());
			System.out.println("PRUEBA FINALIZADA SATISFACTORIAMENTE");
		} catch (AuthenticatorException e) {
			System.out.println("PRUEBA FALLIDA");
			e.printStackTrace();
		}

		System.out.println("Est� autenticado el sujeto? " + authenticator.estaAutenticadoPermitido(subject));
		System.out.println("PoId en authenticator:" + authenticator.getProofsOfID().get(subject.getCodSubject()));
		System.out.println("Est� autenticado el sujeto2? " + authenticator.estaAutenticadoPermitido(subject2));
		System.out.println("PoId en authenticator:" + authenticator.getProofsOfID().get(subject2.getCodSubject()));

		System.out.println("===================================================================================");
		System.out.println("PRUEBA DE AUTENTICACI�N FALLIDA");
		try {
			System.out.println(authenticator.login("3456", "8956"));
		} catch (AuthenticatorException e) {
			System.out.println(e.getMessage());
			System.out.println("prueba fallida satisfactoriamente");
		}

		long time2 = System.currentTimeMillis();

		System.out.println("=================================================================");

		System.out.println("Tiempo de ejecuci�n: " + ((time2 - time1) / 1) + " milisegundos.");

		System.out.println("=====================================================================");
	}

}
