package serpis.ad;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class VentanaMain {
	
	private static EntityManagerFactory entityManagerFactory;
	
	public static void main(String[] args) {
		Logger.getLogger("org.hibernate").setLevel(Level.OFF);
	
		entityManagerFactory=
			Persistence.createEntityManagerFactory("serpis.ad.gventa");
		VentaDao.init(entityManagerFactory);
	
		VentaDao.newPedido();
		VentaDao.showPedidoLinea();
	
		entityManagerFactory.close();		

	}
	
	
}
