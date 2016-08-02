package composition.seguridad.patterns.authenticator;

import java.security.Key;

import javax.ejb.Stateless;

import composition.seguridad.patterns.criptography.resources.AKey;
import composition.seguridad.patterns.criptography.resources.Encryptor;
import composition.seguridad.patterns.criptography.resources.IEncryptor;
import composition.seguridad.patterns.criptography.resources.Message;
import composition.seguridad.patterns.resources.Subject;
import composition.tienda.entities.Authenticationinformation;

@Stateless
public class TesterA {

	public TesterA() {

		long time1 = System.currentTimeMillis();
		Subject subject = new Subject("12345", "passwordSubject");
		Subject subject2 = new Subject("58901", "passwordSubject2");
		System.out.println("Se creó el sujeto:" + subject.toString());
		Authenticator authenticator = new Authenticator();

		System.out.println("Se procederá a autenticar al sujeto...");
		System.out.println("Autenticó? " + authenticator.autenticar(subject));
		System.out.println("Autenticó2? " + authenticator.autenticar(subject2));
		System.out.println("Autenticado: "
				+ ((Authenticationinformation) authenticator.getAuthenticationInformations().iterator().next())
						.toString());

		Key llave = authenticator.getLlave();

		IEncryptor encryptor = new Encryptor();

		AKey key = new AKey();
		key.setKey(llave);

		Message pass = new Message();
		pass.setMensaje(subject.getPassword());

		Message pass2 = new Message();
		pass2.setMensaje(subject2.getPassword());

		try {
			System.out.println("Se procederá a generar el proof of id del usuario....");
			ProofOfID proofOfID = authenticator.login(subject.getCodSubject(), encryptor.encryptConLlave(pass, key));
			ProofOfID proofOfID2 = authenticator.login(subject2.getCodSubject(), encryptor.encryptConLlave(pass2, key));
			System.out.println("Ya se obtuvo el proof of id:" + proofOfID.toString());
			System.out.println("Ya se obtuvo el proof of id2:" + proofOfID2.toString());
			System.out.println("Se asignará el PoId al sujeto... ");
			subject.agregarProofOfId(proofOfID);
			subject2.agregarProofOfId(proofOfID2);
			System.out.println("Se agregó el proof of id en el sujeto:");
			System.out.println("Id en posesión del sujeto:" + subject.toString() + " con PoId: "
					+ subject.getProofsOfID().iterator().next().toString());
			System.out.println("Se agregó el proof of id en el sujeto2:");
			System.out.println("Id en posesión del sujeto2:" + subject2.toString() + " con PoId: "
					+ subject2.getProofsOfID().iterator().next().toString());
			System.out.println("PRUEBA FINALIZADA SATISFACTORIAMENTE");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("PRUEBA FALLIDA");
		}

		System.out.println("Está autenticado el sujeto? " + authenticator.estaAutenticadoPermitido(subject));
		System.out.println("PoId en authenticator:" + authenticator.getProofsOfID().get(subject.getCodSubject()));
		System.out.println("Está autenticado el sujeto2? " + authenticator.estaAutenticadoPermitido(subject2));
		System.out.println("PoId en authenticator:" + authenticator.getProofsOfID().get(subject2.getCodSubject()));

		System.out.println("===================================================================================");
		System.out.println("PRUEBA DE AUTENTICACIÓN FALLIDA");
		try {
			Message cod = new Message();
			Message pas = new Message();
			cod.setMensaje("8956");

			System.out.println(authenticator.login("3456", encryptor.encryptConLlave(pas, key)));
		} catch (AuthenticatorException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("prueba fallida satisfactoriamente");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("prueba fallida satisfactoriamente");
		}

		long time2 = System.currentTimeMillis();

		System.out.println("=================================================================");

		System.out.println("Tiempo de ejecución: " + ((time2 - time1) / 1) + " milisegundos.");

		System.out.println("=====================================================================");

	}

}
