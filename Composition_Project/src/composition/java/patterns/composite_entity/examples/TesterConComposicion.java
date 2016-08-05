package composition.java.patterns.composite_entity.examples;

import java.math.BigDecimal;
import java.security.Key;

import composition.seguridad.patterns.authenticator.Authenticator;
import composition.seguridad.patterns.authenticator.ProofOfID;
import composition.seguridad.patterns.criptography.resources.AKey;
import composition.seguridad.patterns.criptography.resources.Decryptor;
import composition.seguridad.patterns.criptography.resources.EncryptedMessage;
import composition.seguridad.patterns.criptography.resources.Encryptor;
import composition.seguridad.patterns.criptography.resources.IDecryptor;
import composition.seguridad.patterns.criptography.resources.IEncryptor;
import composition.seguridad.patterns.criptography.resources.Message;
import composition.seguridad.patterns.resources.Subject;
import composition.tienda.entities.Authenticationinformation;
import composition.tienda.entities.Categoria;
import composition.tienda.entities.Producto;

public class TesterConComposicion {

	public static void main(String[] args) {

		long time1 = System.currentTimeMillis();
		IEncryptor encryptor = new Encryptor();

		IDecryptor decryptor = new Decryptor();
		//
		// // AUTHENTICATION
		//
		// System.out.println("=================================================================");
		// System.out.println("================================AUTHENTICATION==========================");
		// Subject subject = new Subject("12345", "passwordSubject");
		// System.out.println("Se creó el sujeto:" + subject.toString());
		// Authenticator authenticator = new Authenticator();
		//
		// System.out.println("Se procederá a autenticar al sujeto...");
		// System.out.println("Autenticó? " +
		// authenticator.autenticar(subject));
		//
		// System.out.println("Autenticado: "
		// + ((Authenticationinformation)
		// authenticator.getAuthenticationInformations().iterator().next())
		// .toString());
		// Key llave = authenticator.getLlave();
		//
		// AKey key = new AKey();
		// key.setKey(llave);
		//
		// Message pass = new Message();
		// pass.setMensaje(subject.getPassword());
		// try {
		// System.out.println("Se procederá a generar el proof of id del
		// usuario....");
		// ProofOfID proofOfID = authenticator.login(subject.getCodSubject(),
		// encryptor.encryptConLlave(pass, key));
		// System.out.println("Ya se obtuvo el proof of id:" +
		// proofOfID.toString());
		// System.out.println("Se asignará el PoId al sujeto... ");
		// subject.agregarProofOfId(proofOfID);
		// System.out.println("Id en posesión del sujeto:" + subject.toString()
		// + " con PoId: "
		// + subject.getProofsOfID().iterator().next().toString());
		// System.out.println("PRUEBA AUTHENTICATION FINALIZADA
		// SATISFACTORIAMENTE");
		// System.out.println("=================================================================");
		// }catch (Exception e) {
		// e.printStackTrace();
		// System.err.println("PRUEBA FALLIDA");
		// System.err.println("=================================================================");
		// }

		// COMPOSITE ENTITY
		System.out.println("=================================================================");
		System.out.println("=============================COMPOSITE ENTITY AND PRODUCTS==========================");
		ProductoCompositeEntity productoCompositeEntity = new ProductoCompositeEntity();
		System.out.println("Se crea el composite:" + productoCompositeEntity.toString());

		Key llave2 = productoCompositeEntity.getLlave();
		System.out.println("Se obtiene la llave generada por el composite para encriptar:" + llave2.toString());

		AKey key2 = new AKey();
		key2.setKey(llave2);

		try {
			System.out.println("================================PRUEBA UNO==========================");
			System.out.println("Se procede a crear el producto:");
			Categoria categoria = new Categoria();
			categoria.setIdcategoria(1);
			categoria.setNombre("Categoría 1");
			System.out.println("Se crea la categoria:" + categoria.toString());
			Producto producto = new Producto();
			producto.setIdproducto(11);
			producto.setCategoria(categoria);
			producto.setColor("VERDE");
			producto.setDescripcion("Producto de prueba");
			producto.setExistencias(new BigDecimal(20000));
			producto.setExistenciasminimas(new BigDecimal(1000));
			producto.setNombre("ProductoPueba1");
			producto.setPreciobase(new BigDecimal(25000));
			System.out.println("Se creó el producto:" + producto.toString());
			Message productMessage = new Message();
			productMessage.setMensaje(producto);
			EncryptedMessage productoEncriptado = encryptor.encryptConLlave(productMessage, key2);
			System.out.println("Se encripta el producto..." + productoEncriptado.toString());
			System.out.println("Se manda al composite para crearlo");
			Message idProductoCreadorM = decryptor.decrypt(key2,
					productoCompositeEntity.crearProducto(productoEncriptado));
			long idProductoCreado = (long) idProductoCreadorM.getMensaje();
			if (idProductoCreado == -1) {
				System.out.println("PRUEBA FALLIDA");
				System.out.println("=================================================================");
			} else {
				System.out.println("El id del producto que se agregó fue:" + idProductoCreado);
				System.out.println("================PRUEBA FINALIZADA SATISFACTORIAMENTE==============");
				System.out.println("=================================================================");
			}

			System.out.println("================================PRUEBA DOS==========================");
			System.out.println("Se procede a buscar el producto agregado...");
			long idproducto = 11;
			System.out.println("El id del producto a buscar es:" + idproducto);
			Message idProductoMessage = new Message();
			idProductoMessage.setMensaje(idproducto);// Ya que el id del
														// producto
			// agregado fue 11.
			EncryptedMessage idProductoEncriptado = encryptor.encryptConLlave(idProductoMessage, key2);
			EncryptedMessage productoABuscarEncriptado = productoCompositeEntity.buscarProducto(idProductoEncriptado);
			Message productoABuscarM = decryptor.decrypt(key2, productoABuscarEncriptado);
			Producto productoBuscado = (Producto) productoABuscarM.getMensaje();
			System.out.println("El producto buscado por usted es este:" + productoBuscado.toString());
			System.out.println("================PRUEBA DOS FINALIZADA SATISFACTORIAMENTE==============");
			System.out.println("=================================================================");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("PRUEBA FALLIDA");
			System.out.println("=================================================================");
		}
		long time2 = System.currentTimeMillis();

		System.out.println("=================================================================");

		System.out.println("Tiempo de ejecución: " + ((time2 - time1) / 1) + " milisegundos.");

		System.out.println("=====================================================================");

	}

}
