package composition.seguridad.patterns.authorization;

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
@WebServlet("/TestAuthorizator")
public class TestAuthorizatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	DerechosDAO derechosDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestAuthorizatorServlet() {
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
		out.println("<h3>" + "Sujeto: " + subject.toString() + "</h3>");
		String nameProtectedObject = " java:global/Composition_Project/AuthorizatorDAO";
		out.println("<h3>" + "Objeto protegido: " + nameProtectedObject + "</h3>");
		String tipoAcceso = "PERMITIDO";
		out.println("<h3>" + "Tipo de derecho o acceso: " + tipoAcceso + "</h3>");
		try {
			DerechosDAO derechosDAO = new DerechosDAO();
			derechosDAO.cargar(); 
			out.println("<h3>" + "Agregó el derecho? " + derechosDAO.agregarDerecho(
					Long.parseLong(subject.getCodSubject()), nameProtectedObject, tipoAcceso) + "</h3>");
			out.println("<h3>" + "Petición de servicio por parte del sujeto..." + "</h3>");
			out.println("<h3>" + "Está permitida la petición? Check right?"
					+ derechosDAO.checkRight(Long.parseLong(subject.getCodSubject()), nameProtectedObject) + "</h3>");
			out.println("<h3>" + "PRUEBA REALIZADA SATISFACTORIAMENTE" + "</h3>");
			out.println(
					"<h3>" + "==========================================================================" + "</h3>");
			out.println("<h3>" + "SEGUNDA PRUEBA==================================================" + "</h3>");
			AuthorizatorDAO authorizatorDAO = new AuthorizatorDAO();
			long idSystem = 2343;
			out.println("<h3>" + "Id del sistema:" + idSystem + "</h3>");
			out.println(
					"<h3>" + "Agregó el authorizatorInformation? " + authorizatorDAO.agregarAuthorizationinformation(
							Long.parseLong(subject.getCodSubject()), idSystem, tipoAcceso) + "</h3>");
			out.println("<h3>" + "Petición del service locator al sistema..." + "</h3>");
			out.println("<h3>" + "Está permitida la petición? Check right?"
					+ authorizatorDAO.checkRight(Long.parseLong(subject.getCodSubject()), idSystem) + "</h3>");
			out.println("<h3>" + "PRUEBA REALIZADA SATISFACTORIAMENTE" + "</h3>");
			out.println(
					"<h3>" + "==========================================================================" + "</h3>");

		} catch (Exception e) {
			out.println("<h3>" + e.getMessage() + "</h3>");
			out.println("<h3>" + "PRUEBA FALLIDA" + "</h3>");
			out.println("<h3>" + "=================================================================" + "</h3>");
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
		System.out.println("Cod subje:" + subject.getCodSubject());
		System.out.println("Pas sub:" + subject.getPassword());
		Subject subject2 = new Subject("58901", "passwordSubject2");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>TestAuthenticator</title></head><body>");
		out.println("<h1>Request servlet" + request.getServletPath() + "</h1>");
		out.println("<h3>" + "Sujeto: " + subject.toString() + "</h3>");
		String nameProtectedObject = " java:global/Composition_Project/AuthorizatorDAO";
		out.println("<h3>" + "Objeto protegido: " + nameProtectedObject + "</h3>");
		String tipoAcceso = "PERMITIDO";
		out.println("<h3>" + "Tipo de derecho o acceso: " + tipoAcceso + "</h3>");
		try {
			DerechosDAO derechosDAO = new DerechosDAO();
			out.println("<h3>" + "Agregó el derecho? " + derechosDAO.agregarDerecho(
					Long.parseLong(subject.getCodSubject()), nameProtectedObject, tipoAcceso) + "</h3>");
			out.println("<h3>" + "Petición de servicio por parte del sujeto..." + "</h3>");
			out.println("<h3>" + "Está permitida la petición? Check right?"
					+ derechosDAO.checkRight(Long.parseLong(subject.getCodSubject()), nameProtectedObject) + "</h3>");
			out.println("<h3>" + "PRUEBA REALIZADA SATISFACTORIAMENTE" + "</h3>");
			out.println(
					"<h3>" + "==========================================================================" + "</h3>");
			out.println("<h3>" + "SEGUNDA PRUEBA==================================================" + "</h3>");
			AuthorizatorDAO authorizatorDAO = new AuthorizatorDAO();
			long idSystem = 2343;
			out.println("<h3>" + "Id del sistema:" + idSystem + "</h3>");
			out.println(
					"<h3>" + "Agregó el authorizatorInformation? " + authorizatorDAO.agregarAuthorizationinformation(
							Long.parseLong(subject.getCodSubject()), idSystem, tipoAcceso) + "</h3>");
			out.println("<h3>" + "Petición del service locator al sistema..." + "</h3>");
			out.println("<h3>" + "Está permitida la petición? Check right?"
					+ authorizatorDAO.checkRight(Long.parseLong(subject.getCodSubject()), idSystem) + "</h3>");
			out.println("<h3>" + "PRUEBA REALIZADA SATISFACTORIAMENTE" + "</h3>");
			out.println(
					"<h3>" + "==========================================================================" + "</h3>");

		} catch (Exception e) {
			out.println("<h3>" + e.getMessage() + "</h3>");
			out.println("<h3>" + "PRUEBA FALLIDA" + "</h3>");
			out.println("<h3>" + "=================================================================" + "</h3>");
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
