package pdg.patrones.java.compositentity.dependentobjects;

import javax.ejb.CreateException;
import javax.ejb.FinderException;

public interface CaracteristicasDOLocalHome {

	public static final Integer idCaracteristica=null;
	public static final String nombre=null;
	public static final String detalle=null;
	
	public CaracteristicasDOLocal create(Integer idCaracteristica, String nombre, String detalle) throws CreateException;
    public CaracteristicasDOLocal findByPrimaryKey(Object key) throws FinderException;
    
}
