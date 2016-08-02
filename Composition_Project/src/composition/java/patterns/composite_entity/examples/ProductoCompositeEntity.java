package composition.java.patterns.composite_entity.examples;

import java.rmi.RemoteException;
import java.security.Key;
import java.util.Iterator;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import composition.seguridad.patterns.criptography.resources.AKey;
import composition.seguridad.patterns.criptography.resources.Decryptor;
import composition.seguridad.patterns.criptography.resources.EncryptedMessage;
import composition.seguridad.patterns.criptography.resources.Encryptor;
import composition.seguridad.patterns.criptography.resources.IDecryptor;
import composition.seguridad.patterns.criptography.resources.IEncryptor;
import composition.seguridad.patterns.criptography.resources.Message;
import composition.seguridad.patterns.criptography.resources.ServicioLlaves;
import composition.seguridad.patterns.criptography.resources.TiposAlgoritmosCifrado;
import composition.tienda.entities.Categoria;
import composition.tienda.entities.Producto;
import composition.tienda.entities.Valorproductocuantitativo;

/**
 * Session Bean implementation class ProductoCompositeEntity
 */
@Stateless
@LocalBean
public class ProductoCompositeEntity {
	/**
	 * LLave para cifrar y descifrar passwords de sujetos autenticados.
	 */
	private Key llave;

	/**
	 * Tipo de algoritmo de cifrado definido para AES.
	 */
	public static final String TIPO_ALGORITMO = TiposAlgoritmosCifrado.AES;

	/**
	 * Encriptor del password.
	 */
	private IEncryptor encryptor;

	/**
	 * Decriptor del password.
	 */
	private IDecryptor decryptor;
	/**
	 * Servicio de generación de llaves.
	 */
	private ServicioLlaves servicioLlaves;

	@EJB
	ProductoCGO productoCGO;

	/**
	 * Default constructor.
	 */
	public ProductoCompositeEntity() {
		servicioLlaves = new ServicioLlaves();
		try {
			llave = servicioLlaves.generarLlaveSimetrica(TIPO_ALGORITMO);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		encryptor = new Encryptor();
		decryptor = new Decryptor();
		productoCGO = new ProductoCGO();
		// try {
		// ejbLoad();
		// } catch (EJBException | RemoteException e) {
		// System.err.println("NO SE creó el composite");
		// e.printStackTrace();
		// }
	}

	/**
	 * Desencrypta el producto enviado y crea el producto. Devuelve el id del producto creado.
	 * 
	 * @param productoEncriptado-Producto
	 *            recibido.
	 */
	public EncryptedMessage crearProducto(EncryptedMessage productoEncriptado) {
		
		long idProductoCreado = -1;
		AKey key = new AKey();
		key.setKey(llave);
		Message productoM = null;
		try {
			productoM = decryptor.decrypt(key, productoEncriptado);
			System.out.println("Se desencriptó el producto enviado:" + ((Producto) productoM.getMensaje()).toString());
		} catch (Exception e) {
			System.err.println("NO DESENCRIPTÓ");
			e.printStackTrace();
		}
		Producto producto = (Producto) productoM.getMensaje();
		try {
			idProductoCreado = this.ejbCreate(producto);
			System.out.println("Se ha creado el producto.");
		} catch (CreateException e) {
			System.err.println("NO SE CREÓ EL PRODUCTO.");
			e.printStackTrace();
		}

		Message message=new Message();
		message.setMensaje(idProductoCreado); 
		EncryptedMessage encryptedMessage=null;
		try {
			encryptedMessage = encryptor.encryptConLlave(message, key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encryptedMessage; 

	}

	public long ejbCreate(Producto producto) throws CreateException {
		return productoCGO.ejbCreate(producto);
	}

	public void setCoarseGrainedObjectData(Producto producto) {
		productoCGO.setCoarseGrainedObjectData(producto);
	}

	/**
	 * Desencripta el id del producto enviado y busca el producto con ese id.
	 * 
	 * @param idProductoEncriptado
	 * @return Producto producto encontrado.
	 * @throws Exception-
	 *             Si no encuentra o no encripta en producto.
	 */
	public EncryptedMessage buscarProducto(EncryptedMessage idProductoEncriptado) throws Exception {

		AKey key = new AKey();
		key.setKey(llave);
		Message idProductoM = null;
		try {
			idProductoM = decryptor.decrypt(key, idProductoEncriptado);
			System.out.println("Se desencriptó el id del producto enviado:" + idProductoM.getMensaje().toString());
		} catch (Exception e) {
			System.err.println("NO DESENCRIPTÓ");
			e.printStackTrace();
		}
		long idProducto = (long) idProductoM.getMensaje();
		System.out.println("Se desencriptó el id del producto enviado:" + idProducto);
		Producto producto = ejbFindByPrimaryKey(idProducto);
		System.out.println("Se encontró el producto" + producto.toString());
		Message productoMessage = new Message();
		productoMessage.setMensaje(producto);// Ya que el id del producto
												// agregado fue 11.
		EncryptedMessage productoEncriptado = encryptor.encryptConLlave(productoMessage, key);
		System.out.println("Se encripta el producto y se retorna.");
		return productoEncriptado;
	}

	public Producto ejbFindByPrimaryKey(long primaryKey) throws FinderException {
		return productoCGO.ejbFindByPrimaryKey(primaryKey);
	}

	public Producto findByPrimaryKey(long primaryKey) {
		return productoCGO.findByPrimaryKey(primaryKey);
	}

	public void ejbPostCreate(Producto producto) {
		productoCGO.ejbPostCreate(producto);
	}

	public Set getDependentObjects() {
		return productoCGO.getDependentObjects();
	}

	public void ejbRemove() {
		productoCGO.ejbRemove();
	}

	public void ejbActivate() throws EJBException, RemoteException {
		productoCGO.ejbActivate();
	}

	public void ejbLoad() throws EJBException, RemoteException {
		productoCGO.ejbLoad();
	}

	public void ejbLoad(long idProducto) throws EJBException, RemoteException {
		productoCGO.ejbLoad(idProducto);
	}

	public void ejbStore() throws EJBException, RemoteException {
		productoCGO.ejbStore();
	}

	public void ejbPassivate() throws EJBException, RemoteException {
		productoCGO.ejbPassivate();
	}

	/**
	 * Relaciona al ProductoCGO con una categoria existente.
	 * 
	 * @param categoria
	 * @return true- si relacionó, false si no relacionó.
	 */
	public boolean addCategoria(Categoria categoria) {
		return productoCGO.addCategoria(categoria);
	}

	/**
	 * Adiciona una nueva característica al ProductoCGO.
	 * 
	 * @param caracteristica
	 * @return true- si adicionó, false si no adicionó.
	 */
	public boolean addCaracteristica(Valorproductocuantitativo caracteristica) {
		return productoCGO.addCaracteristica(caracteristica);
	}

	// ===============GETTERS AND SETTERS=======================//
	public ProductoCGO getProductoCGO() {
		return productoCGO;
	}

	public void setProductoCGO(ProductoCGO productoCGO) {
		this.productoCGO = productoCGO;
	}

	public Key getLlave() {
		return llave;
	}

	public void setLlave(Key llave) {
		this.llave = llave;
	}

	public IEncryptor getEncryptor() {
		return encryptor;
	}

	public void setEncryptor(IEncryptor encryptor) {
		this.encryptor = encryptor;
	}

	public IDecryptor getDecryptor() {
		return decryptor;
	}

	public void setDecryptor(IDecryptor decryptor) {
		this.decryptor = decryptor;
	}

	public ServicioLlaves getServicioLlaves() {
		return servicioLlaves;
	}

	public void setServicioLlaves(ServicioLlaves servicioLlaves) {
		this.servicioLlaves = servicioLlaves;
	}

	public String toString() {
		String toString = "";
		if (!productoCGO.getProductosList().isEmpty()) {

			Iterator<Producto> iter = productoCGO.getProductosList().iterator();
			while (iter.hasNext()) {

				Producto producto = iter.next();
				toString += producto.toString() + "\n";

			}

		} else {
			toString = "No hay productos creados";
		}

		return toString;
	}

	// ========================================================//

}
