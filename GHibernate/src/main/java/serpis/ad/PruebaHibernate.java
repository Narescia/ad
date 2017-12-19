package serpis.ad;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PruebaHibernate {

	private static EntityManagerFactory entityManagerFactory;
	public static void main(String[] args) {
		entityManagerFactory = 
			Persistence.createEntityManagerFactory("serpis.ad.ghibernate");
		
		showAll();
		modify(1L);
		showAll();
	
		entityManagerFactory.close();
	}
	
	private static void showAll() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List <Categoria> categorias = 
			entityManager.createQuery("from Categoria", Categoria.class).getResultList();
		for (Categoria categoria : categorias)
			System.out.println(categoria);
		entityManager.getTransaction().commit();
	}
	
	private static void modify (long id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Categoria categoria = entityManager.find(Categoria.class, id);
		categoria.setNombre("modificado " + new Date());
		entityManager.getTransaction().commit();
	}

}
