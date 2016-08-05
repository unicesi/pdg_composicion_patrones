package composition.java.patterns.service_locator.examples;

import java.security.Key;

import javax.ejb.Stateless;
import javax.naming.ServiceUnavailableException;

import composition.java.patterns.service_locator.ServiceLocator;
import composition.seguridad.patterns.authenticator.Authenticator;
import composition.seguridad.patterns.authenticator.AuthenticatorException;
import composition.seguridad.patterns.authenticator.ProofOfID;
import composition.seguridad.patterns.authorization.AuthorizatorDAO;
import composition.seguridad.patterns.authorization.DerechosDAO;
import composition.seguridad.patterns.criptography.resources.AKey;
import composition.seguridad.patterns.criptography.resources.Encryptor;
import composition.seguridad.patterns.criptography.resources.IEncryptor;
import composition.seguridad.patterns.criptography.resources.Message;
import composition.seguridad.patterns.resources.Subject;
import composition.tienda.entities.Authenticationinformation;


public class Tester {

	private static ServiceLocator serviceLocator;

	private static ProductoEJBRemote productoEJBRemote;

	public static void main(String args[]) {
		long idService = 11;
		System.out.println("El id del service Locator es:" + idService);
		try {
			serviceLocator = ServiceLocator.getInstance(idService);
			System.out.println("Se crea el service locator:" + serviceLocator.toString());
			probarServiceLocatorConEJBProducto();
			probarComposicion();
		} catch (Exception e1) {

			e1.printStackTrace();
			System.err.println("PRUEBA FALLIDA");
		}

	}

	public static void probarServiceLocatorConEJBProducto() {
		long time1 = System.currentTimeMillis();
		try {
			productoEJBRemote = (ProductoEJBRemote) serviceLocator.getLocal(Servicios.SERVICE_PRODUCTOEJB);
			// "java:global/ACADEM-EAR/ACADEM-EJB/EvaluacionBean!co.edu.icesi.academ.evaluaciones.EvaluacionBeanRemote"
			String s = "" + productoEJBRemote.inicializo();
			System.out.println("" + s);
			System.out.println("Encontr� el ProductoEJB? " + " " + s);
		} catch (ServiceUnavailableException e) {
			e.printStackTrace();
			System.err.println("PRUEBA FALLIDA");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("PRUEBA FALLIDA");
		}
		long time2 = System.currentTimeMillis();

		System.out.println("=====================================================================");

		System.out.println("Tiempo de ejecuci�n: " + ((time2 - time1) / 1) + " milisegundos.");

		System.out.println("=====================================================================");

	}

	public static void probarComposicion() {
		long time1 = System.currentTimeMillis();
		Subject subject = new Subject("12345", "passwordSubject");
		Subject subject2 = new Subject("58901", "passwordSubject2");
		System.out.println("Se cre� el sujeto:" + subject.toString());
		Authenticator authenticator = new Authenticator();

		System.out.println("Se proceder� a autenticar al sujeto...");
		System.out.println("Autentic�? " + authenticator.autenticar(subject));
		System.out.println("Autentic�2? " + authenticator.autenticar(subject2));
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
			System.out.println("Se proceder� a generar el proof of id del usuario....");
			ProofOfID proofOfID = authenticator.login(subject.getCodSubject(), encryptor.encryptConLlave(pass, key));

			System.out.println("Ya se obtuvo el proof of id:" + proofOfID.toString());

			System.out.println("Se asignar� el PoId al sujeto... ");
			subject.agregarProofOfId(proofOfID);

			System.out.println("Se agreg� el proof of id en el sujeto:");
			System.out.println("Id en posesi�n del sujeto:" + subject.toString() + " con PoId: "
					+ subject.getProofsOfID().iterator().next().toString());

			System.out.println("PRUEBA FINALIZADA SATISFACTORIAMENTE");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("PRUEBA FALLIDA");
		}

		System.out.println("Est� autenticado el sujeto? " + authenticator.estaAutenticadoPermitido(subject));
		System.out.println("PoId en authenticator:" + authenticator.getProofsOfID().get(subject.getCodSubject()));
		System.out.println("Est� autenticado el sujeto2? " + authenticator.estaAutenticadoPermitido(subject2));
		System.out.println("PoId en authenticator:" + authenticator.getProofsOfID().get(subject2.getCodSubject()));

		System.out.println("=================================================================");

		System.out.println(
				"Una vez autenticado en el sistema, se verifica que est� autorizado para el servicio ProductoEJB");
		System.out.println("=================================================================");

		try {
			String nameProtectedObject = Servicios.SERVICE_PRODUCTOEJB;
			System.out.println("Objeto protegido: " + nameProtectedObject);
			String tipoAcceso = "PERMITIDO";
			System.out.println("Tipo de derecho o acceso: " + tipoAcceso);
			DerechosDAO derechosDAO = new DerechosDAO();
			System.out.println("Agreg� el derecho? " + derechosDAO
					.agregarDerecho(Long.parseLong(subject.getCodSubject()), nameProtectedObject, tipoAcceso));
			System.out.println("Petici�n de servicio por parte del sujeto...");
			System.out.println("Est� permitida la petici�n? Check right?"
					+ derechosDAO.checkRight(Long.parseLong(subject.getCodSubject()), nameProtectedObject));
			System.out.println("PRUEBA REALIZADA SATISFACTORIAMENTE");
			System.out.println("==========================================================================");
			System.out.println("SEGUNDA PRUEBA==================================================");
			AuthorizatorDAO authorizatorDAO = new AuthorizatorDAO();
			long idSystem = serviceLocator.getIdService();
			System.out.println("Id del sistema:" + idSystem);
			System.out.println("Agreg� el authorizatorInformation? " + authorizatorDAO
					.agregarAuthorizationinformation(Long.parseLong(subject.getCodSubject()), idSystem, tipoAcceso));
			System.out.println("Petici�n del service locator al sistema...");

			// ==Peticion servicio==//
			// Se pide el servicio.=============//
			try {
				// TODO-Falta din�mica de petici�n del servicio.
				System.out.println("Est� permitida la petici�n? Check right?"
						+ authorizatorDAO.checkRight(Long.parseLong(subject.getCodSubject()), idSystem));
				if (authorizatorDAO.checkRight(Long.parseLong(subject.getCodSubject()), idSystem) == true) {
					productoEJBRemote = (ProductoEJBRemote) serviceLocator.getLocal(Servicios.SERVICE_PRODUCTOEJB);
					// "java:global/ACADEM-EAR/ACADEM-EJB/EvaluacionBean!co.edu.icesi.academ.evaluaciones.EvaluacionBeanRemote"
					String s = "" + productoEJBRemote.inicializo();
					System.out.println("" + s);
					System.out.println("Encontr� el ProductoEJB? " + " " + s);
				} else {
					System.err.println("PRUEBA FALLIDA, NO ESTA AUTORIZADO PARA ACCEDER AL SERVICIO");
				}
			} catch (ServiceUnavailableException e) {
				e.printStackTrace();
				System.err.println("PRUEBA FALLIDA");
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("PRUEBA FALLIDA");
			}
			// ================================//
			// =====================//

			System.out.println("PRUEBA REALIZADA SATISFACTORIAMENTE");
			System.out.println("==========================================================================");

		} catch (Exception e) {
			System.err.println("PRUEBAS FALLIDA");
			System.out.println("==========================================================================");
		}

		long time2 = System.currentTimeMillis();

		System.out.println("=================================================================");

		System.out.println("Tiempo de ejecuci�n: " + ((time2 - time1) / 1) + " milisegundos.");

		System.out.println("=====================================================================");

	}

}
