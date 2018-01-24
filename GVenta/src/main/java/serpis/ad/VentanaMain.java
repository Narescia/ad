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
			Persistence.createEntityManagerFactory("serpis.ad.GVentaEclipse");
	
	showCategoria();
	
	showArticulos();
	
	showCliente();
	
//	modify(23L);
	
//	remove (2L);
	
//	newCategoria();
	
//	showAll();
	
	entityManagerFactory.close();
		 
		
		
	}
	
	private static void showCategoria () {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Categoria> categorias= entityManager.
				createQuery("from Categoria order by id", Categoria.class).getResultList();
		System.out.println("--------------------CATEGORIA-----------------\n" );
		for (Categoria categoria : categorias)
			System.out.println(categoria);
		
		
		entityManager.getTransaction().commit();
	}

	private static void showArticulos () {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Articulo> Articulos= entityManager.
				createQuery("from Articulo order by id", Articulo.class).getResultList();
		System.out.println("\n--------------------ARTICULO----------------- \n" );
		for (Articulo articulo : Articulos)
			System.out.println(articulo);
		
		
		entityManager.getTransaction().commit();
	}
	private static void showCliente () {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Cliente> Clientes= entityManager.
				createQuery("from Cliente order by id", Cliente.class).getResultList();
		System.out.println("\n--------------------CLIENTE-----------------\n" );
		for (Cliente cliente : Clientes)
			System.out.println(cliente);
		
		
		entityManager.getTransaction().commit();
	}
}
