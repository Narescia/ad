package serpis.ad;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

public class VentaDao {
	
	private static EntityManagerFactory entityManagerFactory;
	private static Scanner sc = new Scanner (System.in);

	public static void init(EntityManagerFactory entityManagerFactory) {
		VentaDao.entityManagerFactory = entityManagerFactory;
	}
	static void showCategoria () {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Categoria> categorias= entityManager.
				createQuery("from Categoria order by id", Categoria.class).getResultList();
		System.out.println("--------------------CATEGORIA-----------------\n" );
		for (Categoria categoria : categorias)
			System.out.println(categoria);
		
		
		entityManager.getTransaction().commit();
	}

	static void showArticulos () {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Articulo> Articulos= entityManager.
				createQuery("from Articulo order by id", Articulo.class).getResultList();
		System.out.println("\n--------------------ARTICULO----------------- \n" );
		for (Articulo articulo : Articulos)
			System.out.println(articulo);
		
		
		entityManager.getTransaction().commit();
	}
	
	static void showCliente () {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Cliente> Clientes= entityManager.
				createQuery("from Cliente order by id", Cliente.class).getResultList();
		System.out.println("\n--------------------CLIENTE-----------------\n" );
		for (Cliente cliente : Clientes)
			System.out.println(cliente);
		
		
		entityManager.getTransaction().commit();	
	}
	
	public static void showPedido() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Pedido> Pedidos = entityManager.createQuery("from Pedido order by id", Pedido.class).getResultList();
		System.out.println("\n-------------------------------------PEDIDO-------------------------------------\n");
		for (Pedido pedido : Pedidos)
			System.out.println(pedido);

		entityManager.getTransaction().commit();
	}

	public static void showPedidoLinea() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<PedidoLinea> PedidoLineas = entityManager.createQuery("from PedidoLinea order by id", PedidoLinea.class)
				.getResultList();
		System.out
				.println("\n-------------------------------------PEDIDO LINEA-------------------------------------\n");
		for (PedidoLinea pedidolinea : PedidoLineas)
			System.out.println(pedidolinea);

		entityManager.getTransaction().commit();	
	}
	
	public static void newCategoria(int numero) throws MySQLIntegrityConstraintViolationException {
		System.out.println("creando categoria nueva");
		Categoria categoria = new Categoria();
		categoria.setNombre("Categoria " + numero);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		// System.out.println("Creando "+ categoria);
		entityManager.persist(categoria);
		System.out.println("Creada " + categoria);
		entityManager.getTransaction().commit();
	}
	
	public static void newCliente(int numero) throws MySQLIntegrityConstraintViolationException {
		System.out.println("creando Cliente nueva");
		Cliente Cliente = new Cliente();
		Cliente.setNombre("Cliente " + numero);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		// System.out.println("Creando "+ Cliente);
		entityManager.persist(Cliente);
		System.out.println("Creada " + Cliente);
		entityManager.getTransaction().commit();
	}
	
	public static void newPedido() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Pedido pedido = new Pedido();
		Cliente cliente = entityManager.getReference(Cliente.class, 1L);
		pedido.setCliente(cliente);
		PedidoLinea pedidoLinea1 = new PedidoLinea();
		pedido.add(pedidoLinea1);
//		//0j0 las dos sentencias siguientes mantienen sincronizada la asociación
//		pedido.getPedidoLineas().add(pedidoLinea1);
//		pedidoLinea1.setPedido(pedido);
		Articulo articulo = entityManager.getReference(Articulo.class, 1L);
		pedidoLinea1.setArticulo(articulo);
		
		entityManager.persist(pedido);
		entityManager.getTransaction().commit();
		
		for(PedidoLinea pedidoLinea : pedido.getPedidoLineas())
			System.out.println(pedidoLinea);
	}

}
