package composition.seguridad.patterns.authenticator;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Key;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import composition.seguridad.patterns.criptography.resources.AKey;
import composition.seguridad.patterns.criptography.resources.Encryptor;
import composition.seguridad.patterns.criptography.resources.IEncryptor;
import composition.seguridad.patterns.criptography.resources.Message;
import composition.seguridad.patterns.resources.Subject;
import composition.tienda.entities.Authenticationinformation;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestAuthenticator")
public class TestAuthenticatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	Authenticator authenticator;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestAuthenticatorServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long time1 = System.currentTimeMillis();

		Subject subject = new Subject("12345", "passwordSubject");
		System.out.println("Cod subje:" + subject.getCodSubject());
		System.out.println("Pas sub:" + subject.getPassword());
		Subject subject2 = new Subject("58901", "passwordSubject2");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>TestAuthenticator</title></head><body>");
		out.println("<h1>Request servlet" + request.getServletPath() + "</h1>");
		out.println("<h3>" + "Se creó el sujeto:" + subject.toString() + "</h3>");

		//authenticator = new Authenticator();
		out.println("<h3>" + "Se procederá a autenticar al sujeto..." + "</h3>");
		out.println("<h3>" + "Autenticó? " + authenticator.autenticar(subject) + "</h3>");
		out.println("<h3>" + "Autenticó2? " + authenticator.autenticar(subject2) + "</h3>");
		out.println("<h3>" + "Autenticado: "
				+ ((Authenticationinformation) authenticator.getAuthenticationInformations().iterator().next())
						.toString()
				+ "</h3>");
		Key llave = authenticator.getLlave();

		IEncryptor encryptor = new Encryptor();

		AKey key = new AKey();
		key.setKey(llave);

		Message pass = new Message();
		pass.setMensaje(subject.getPassword());

		Message pass2 = new Message();
		pass2.setMensaje(subject2.getPassword());
		try {
			out.println("<h3>" + "Se procederá a generar el proof of id del usuario...." + "</h3>");
			ProofOfID proofOfID = authenticator.login(subject.getCodSubject(), encryptor.encryptConLlave(pass, key));
			ProofOfID proofOfID2 = authenticator.login(subject2.getCodSubject(), encryptor.encryptConLlave(pass2, key));
			out.println("<h3>" + "Ya se obtuvo el proof of id:" + proofOfID.toString() + "</h3>");
			out.println("<h3>" + "Ya se obtuvo el proof of id2:" + proofOfID2.toString() + "</h3>");
			out.println("<h3>" + "Se asignará el PoId al sujeto... " + "</h3>");
			subject.agregarProofOfId(proofOfID);
			subject2.agregarProofOfId(proofOfID2);
			out.println("<h3>" + "Se agregó el proof of id en el sujeto:" + "</h3>");
			out.println("<h3>" + "Id en posesión del sujeto:" + subject.toString() + " con PoId: "
					+ subject.getProofsOfID().iterator().next().toString() + "</h3>");
			out.println("<h3>" + "Se agregó el proof of id en el sujeto2:" + "</h3>");
			out.println("<h3>" + "Id en posesión del sujeto2:" + subject2.toString() + " con PoId: "
					+ subject2.getProofsOfID().iterator().next().toString() + "</h3>");
			out.println("<h3>" + "PRUEBA FINALIZADA SATISFACTORIAMENTE" + "</h3>");
		} catch (Exception e) {
			out.println("<h3>" + e.getMessage() + "</h3>");
			out.println("<h3>" + "PRUEBA FALLIDA" + "</h3>");

		}
		out.println(
				"<h3>" + "Está autenticado el sujeto? " + authenticator.estaAutenticadoPermitido(subject) + "</h3>");
		out.println("<h3>" + "PoId en authenticator:" + authenticator.getProofsOfID().get(subject.getCodSubject())
				+ "</h3>");
		out.println(
				"<h3>" + "Está autenticado el sujeto2? " + authenticator.estaAutenticadoPermitido(subject2) + "</h3>");
		out.println("<h3>" + "PoId en authenticator:" + authenticator.getProofsOfID().get(subject2.getCodSubject())
				+ "</h3>");
		out.println("<h3>" + "==================================================================================="
				+ "</h3>");
		out.println("<h3>" + "PRUEBA DE AUTENTICACIÓN FALLIDA" + "</h3>");
		try {
			Message cod = new Message();
			Message pas = new Message();
			cod.setMensaje("8956");
			out.println("<h3>" + authenticator.login("3456", encryptor.encryptConLlave(pas, key)) + "</h3>");
		} catch (Exception e) {
			out.println("<h3>" + e.getMessage() + "</h3>");
			out.println("<h3>" + "prueba fallida satisfactoriamente" + "</h3>");

		}
		long time2 = System.currentTimeMillis();
		out.println("<h3>" + "=================================================================" + "</h3>");
		out.println("<h3>" + "Tiempo de ejecución: " + ((time2 - time1) / 1) + " milisegundos." + "</h3>");
		out.println("<h3>" + "=====================================================================" + "</h3>");
		out.println("</body></html>");
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long time1 = System.currentTimeMillis();
		Subject subject = new Subject("12345", "passwordSubject");
		Subject subject2 = new Subject("58901", "passwordSubject2");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>TestAuthenticator</title></head><body>");
		out.println("<h1>Request servlet" + request.getServletPath() + "</h1>");
		out.println("<h3>" + "Se creó el sujeto:" + subject.toString() + "</h3>");

		//authenticator = new Authenticator();
		out.println("<h3>" + "Se procederá a autenticar al sujeto..." + "</h3>");
		out.println("<h3>" + "Autenticó? " + authenticator.autenticar(subject) + "</h3>");
		out.println("<h3>" + "Autenticó2? " + authenticator.autenticar(subject2) + "</h3>");
		out.println("<h3>" + "Autenticado: "
				+ ((Authenticationinformation) authenticator.getAuthenticationInformations().iterator().next())
						.toString()
				+ "</h3>");
		Key llave = authenticator.getLlave();

		IEncryptor encryptor = new Encryptor();

		AKey key = new AKey();
		key.setKey(llave);

		Message pass = new Message();
		pass.setMensaje(subject.getPassword());

		Message pass2 = new Message();
		pass2.setMensaje(subject2.getPassword());
		try {
			out.println("<h3>" + "Se procederá a generar el proof of id del usuario...." + "</h3>");
			ProofOfID proofOfID = authenticator.login(subject.getCodSubject(), encryptor.encryptConLlave(pass, key));
			ProofOfID proofOfID2 = authenticator.login(subject2.getCodSubject(), encryptor.encryptConLlave(pass2, key));
			out.println("<h3>" + "Ya se obtuvo el proof of id:" + proofOfID.toString() + "</h3>");
			out.println("<h3>" + "Ya se obtuvo el proof of id2:" + proofOfID2.toString() + "</h3>");
			out.println("<h3>" + "Se asignará el PoId al sujeto... " + "</h3>");
			subject.agregarProofOfId(proofOfID);
			subject2.agregarProofOfId(proofOfID2);
			out.println("<h3>" + "Se agregó el proof of id en el sujeto:" + "</h3>");
			out.println("<h3>" + "Id en posesión del sujeto:" + subject.toString() + " con PoId: "
					+ subject.getProofsOfID().iterator().next().toString() + "</h3>");
			out.println("<h3>" + "Se agregó el proof of id en el sujeto2:" + "</h3>");
			out.println("<h3>" + "Id en posesión del sujeto2:" + subject2.toString() + " con PoId: "
					+ subject2.getProofsOfID().iterator().next().toString() + "</h3>");
			out.println("<h3>" + "PRUEBA FINALIZADA SATISFACTORIAMENTE" + "</h3>");
		} catch (Exception e) {
			out.println("<h3>" + e.getMessage() + "</h3>");
			out.println("<h3>" + "PRUEBA FALLIDA" + "</h3>");

		}
		out.println(
				"<h3>" + "Está autenticado el sujeto? " + authenticator.estaAutenticadoPermitido(subject) + "</h3>");
		out.println("<h3>" + "PoId en authenticator:" + authenticator.getProofsOfID().get(subject.getCodSubject())
				+ "</h3>");
		out.println(
				"<h3>" + "Está autenticado el sujeto2? " + authenticator.estaAutenticadoPermitido(subject2) + "</h3>");
		out.println("<h3>" + "PoId en authenticator:" + authenticator.getProofsOfID().get(subject2.getCodSubject())
				+ "</h3>");
		out.println("<h3>" + "==================================================================================="
				+ "</h3>");
		out.println("<h3>" + "PRUEBA DE AUTENTICACIÓN FALLIDA" + "</h3>");
		try {
			Message cod = new Message();
			Message pas = new Message();
			cod.setMensaje("8956");
			out.println("<h3>" + authenticator.login("3456", encryptor.encryptConLlave(pas, key)) + "</h3>");
		} catch (Exception e) {
			out.println("<h3>" + e.getMessage() + "</h3>");
			out.println("<h3>" + "prueba fallida satisfactoriamente" + "</h3>");

		}
		long time2 = System.currentTimeMillis();
		out.println("<h3>" + "=================================================================" + "</h3>");
		out.println("<h3>" + "Tiempo de ejecución: " + ((time2 - time1) / 1) + " milisegundos." + "</h3>");
		out.println("<h3>" + "=====================================================================" + "</h3>");
		out.println("</body></html>");
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
	}

}
