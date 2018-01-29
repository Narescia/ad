package serpis.ad;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import serpis.ad.Articulo;
import serpis.ad.Categoria;
import serpis.ad.Cliente;

public class VentanaMain {
	
	
	private static EntityManagerFactory entityManagerFactory;
	
	public static void main(String[] args) {
	Logger.getLogger("org.hibernate").setLevel(Level.OFF);
	
	entityManagerFactory=
			Persistence.createEntityManagerFactory("serpis.ad.GVenta");
	
	VentaDao.showCategoria();
	
	VentaDao.showArticulos();
	
	VentaDao.showCliente();
	
//	modify(23L);
	
//	remove (2L);
	
//	newCategoria();
	
//	showAll();
	
	entityManagerFactory.close();		
		
	}
	
	
}
