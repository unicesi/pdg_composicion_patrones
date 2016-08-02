package composition.java.patterns.service_locator.examples;

import java.io.IOException;

import javax.ejb.EJB;
import javax.naming.ServiceUnavailableException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import composition.java.patterns.service_locator.*;

/**
 * Servlet implementation class ServletPrueba
 */
@WebServlet("/ServletPrueba")
public class ServletPrueba extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	Tester tester;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		tester.probarServiceLocatorConEJBProducto(); 
		tester.probarComposicion(); 
		/*
		response.getWriter().append("Served at: ").append(request.getContextPath());

		ServiceLocator serviceLocator = null;
		try {
			serviceLocator = ServiceLocator.getInstance();
		} catch (Exception e1) {

			e1.printStackTrace();
			System.err.println("PRUEBA FALLIDA");
		}
		try {
			ProfileRemote profileHome = (ProfileRemote) serviceLocator.getLocal(
					"java:global/Composition_Project/ProfileEJB!composition.java.patterns.service_locator.examples.ProfileLocal");
			// "java:global/ACADEM-EAR/ACADEM-EJB/EvaluacionBean!co.edu.icesi.academ.evaluaciones.EvaluacionBeanRemote"
			String s = "" + profileHome.funciona();
			System.out.println("" + s);
			response.getWriter().append("Encontró el ProfileEJB? ").append("" + s);
		} catch (ServiceUnavailableException e) {
			e.printStackTrace();
			System.err.println("PRUEBA FALLIDA");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("PRUEBA FALLIDA");
		}*/
	}

}
