package pdg.patrones.java.compositentity.dependentobjects;

import javax.ejb.CreateException;
import javax.ejb.FinderException;

public interface CategoriaDOLocalHome {

	public static final Integer idCategoria=null;
	public static final String nombre=null;
	public static final String descripcion=null;
	
	public CategoriaDOLocal create(Integer idCategoria, String nombre, String descripcion) throws CreateException;
    public CategoriaDOLocal findByPrimaryKey(Object key) throws FinderException;
    
}
