package composition.java.patterns.service_locator.examples;

public enum Servicios {
;
	public static final String SERVICE_PRODUCTOS_DAO="java:global/Composition_Project/ProductosDAO!composition.java.patterns.composite_entity.examples.ProductosDAO";
	public static final String SERVICE_DERECHOS_DAO="java:global/Composition_Project/DerechosDAO, java:global/Composition_Project/DerechosDAO!composition.seguridad.patterns.authorization.DerechosDAO";
	public static final String SERVICE_AUTHENTICATOR="java:global/Composition_Project/Authenticator!composition.seguridad.patterns.authenticator.Authenticator";
	public static final String SERVICE_PRODUCTOEJB="java:global/Composition_Project/ProductoEJB!composition.java.patterns.service_locator.examples.ProductoEJBRemote";
	//TODO- Completar lista de servicios necesarios dentro de la aplicación, por cada ServiceLocator.
}
