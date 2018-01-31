package serpis.ad;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class VentanaMain {
	
	private static EntityManagerFactory entityManagerFactory;
	
	public static void main(String[] args) {
		Logger.getLogger("org.hibernate").setLevel(Level.OFF);
	
		entityManagerFactory=
			Persistence.createEntityManagerFactory("serpis.ad.GVenta");
	
		VentaDao.showCategoria();
		VentaDao.showArticulos();
		VentaDao.showCliente();
	
		entityManagerFactory.close();		
		
	}
	
	
}
