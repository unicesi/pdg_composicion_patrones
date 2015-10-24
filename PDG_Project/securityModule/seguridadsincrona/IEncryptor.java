package seguridadsincrona;

public interface IEncryptor {

	public Object[] encrypt(String mensajeEncriptar, String tipoAlgoritmo) throws Exception;

}
