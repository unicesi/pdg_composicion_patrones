package composition.java.patterns.service_locator;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.naming.InitialContext;

import composition.seguridad.patterns.authenticator.Authenticator;
import composition.seguridad.patterns.authorization.AuthorizatorDAO;

//import javax.naming.InitialContext;

/**
 * Session Bean implementation class ServiceLocator1
 */
@Singleton
@LocalBean
public class ServiceLocator {

	private long idService;

	

	private static ServiceLocator me;
	InitialContext context = null;

	public ServiceLocator() throws Exception {
		
	}

	
	public ServiceLocator(long idService) throws Exception {
		this.idService = idService;
		
		context = new InitialContext();
	}

	// Returns the instance of ServiceLocator class
	public static ServiceLocator getInstance(long idService) throws Exception {
		if (me == null) {
			me = new ServiceLocator(idService);
		}
		return me;
	}
	/*
	 * // Converts the serialized string into EJBHandle // then to EJBObject.
	 * public EJBObject getService(String id) {
	 * 
	 * try { byte[] bytes = new String(id).getBytes(); InputStream io = new
	 * ByteArrayInputStream(bytes); ObjectInputStream os = new
	 * ObjectInputStream(io); javax.ejb.Handle handle =
	 * (javax.ejb.Handle)os.readObject(); return handle.getEJBObject(); }
	 * catch(Exception ex) { ex.printStackTrace(); } return null; }
	 * 
	 * // Returns the String that represents the given // EJBObject's handle in
	 * serialized format. protected String getId(EJBObject session) throws
	 * Exception { try { javax.ejb.Handle handle = session.getHandle();
	 * ByteArrayOutputStream fo = new ByteArrayOutputStream();
	 * ObjectOutputStream so = new ObjectOutputStream(fo);
	 * so.writeObject(handle); so.flush(); so.close(); return new
	 * String(fo.toByteArray()); } catch(RemoteException ex) { throw new
	 * Exception(""); } catch(IOException ex) { throw new Exception(""); } }
	 * 
	 * // Returns the EJBHome object for requested service // name. Throws
	 * ServiceLocatorException If Any Error // occurs in lookup public EJBHome
	 * getHome(String name, Class<?> clazz) throws Exception { Object objref =
	 * context.lookup(name); EJBHome home = (EJBHome)
	 * PortableRemoteObject.narrow(objref, clazz); return home;
	 * 
	 * 
	 * }
	 */

	public Object getLocal( String name) throws Exception {

		Object objref = context.lookup(name);

		return objref;

	}

	public long getIdService() {
		return idService;
	}

	public void setIdService(long idService) {
		this.idService = idService;
	}

	public String toString() {
		return "SERVICE" + idService;
	}

}
