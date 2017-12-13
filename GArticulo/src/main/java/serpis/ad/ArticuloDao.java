package serpis.ad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import serpis.ad.ArticuloMain.Option;
import serpis.ad.ArticuloMain.State;

public class ArticuloDao{
	
	private static Connection connection;
	private static Scanner scanner = new Scanner(System.in);
	
	public static Connection conecta() throws SQLException{
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/dbprueba?useSSL=false", "root", "sistemas"
				);
		return connection;
	}

	public static long scanId(String label) {
		while(true) {
			try {
				System.out.print(label);
				String line = scanner.nextLine();
				return Long.parseLong(line);
			} catch (NumberFormatException ex) {
				System.out.println("Debe ser un número. Introduce de nuevo");
			}
		}
	}
	
	public static String scanNombre(String label) {
		while(true) {
			try {
				System.out.print(label);
				String line = scanner.nextLine();
				return line;
			} catch (NumberFormatException ex) {
				System.out.println("Debe ser una cadena. Introduce de nuevo");
			}
		}
	}
	
	public static int scanPrecio(String label) {
		while(true) {
			try {
				System.out.print(label);
				String line = scanner.nextLine();
				return Integer.parseInt(line);
			} catch (NumberFormatException ex) {
				System.out.println("Debe ser un número. Introduce de nuevo");
			}
		}
	}
	
	public static long scanCategoria(String label) {
		while(true) {
			try {
				System.out.print(label);
				String line = scanner.nextLine();
				return Long.parseLong(line);
			} catch (NumberFormatException ex) {
				System.out.println("Debe ser un número. Introduce de nuevo");
			}
		}
	}
	
	
//	public static State scanState() {
//		for (int index = 0; index < State.values().length; index++)
//			System.out.printf("%s - %s\n", index, State.values()[index]);
//		String options = String.format("^[0-%s]$", State.values().length - 1);
//		while (true) {
//			System.out.println("Elige opción: ");
//			String line = scanner.nextLine();
//			if(line.matches(options))
//				return State.values()[Integer.parseInt(line)];
//			System.out.println("Opción inválida. Vuelve a introducir");
//		}
//	}
	
	public static <T extends Enum<T>> T scan(Class<T> enumType){
		T[] constants = enumType.getEnumConstants();
		for(int index = 0; index < constants.length; index++)
			System.out.printf("%s - %s\n", index, constants[index]);
		String options = String.format("^[0-%s]$", constants.length - 1);
		while (true) {
			System.out.println("Elige opción: ");
			String line = scanner.nextLine();
			if(line.matches(options))
				return constants[Integer.parseInt(line)];
			System.out.println("Opción inválida. Vuelve a introducir");
		}
	}
	
	public static boolean existeID(long id, String tabla) throws SQLException {
		long[] ids;
		Statement statement = conecta().createStatement();
	    ResultSet resultset = statement.executeQuery("select id from " + tabla + " where id = " + id);
	    
	    if (resultset.next())
	    	return true;
	    
	    System.out.println("Este id no existe en la tabla.");
	    return false;  
	}
	public static void insertar () throws SQLException{
		String nombre = scanNombre("Introduce un nombre: ");
		int precio = scanPrecio("Introduce un precio: ");
		long categoria;
		do {
			categoria = scanId("Introduce una categoría: ");
		}while(!ArticuloDao.existeID(categoria, "articulo"));
		
		PreparedStatement statement = ArticuloDao.conecta().prepareStatement(
				"insert into articulo (nombre, precio, categoria) values (?,?,?)"
		);
		statement.setString(1, nombre);
		statement.setInt(2, precio);
		statement.setLong(3, categoria);
		statement.executeUpdate();
	}
	
	public static void editar () throws SQLException{
		long id;
		do {
			id = scanId("Introduce el id que quieres actualizar: ");
		}while(!existeID(id, "articulo"));
		String nombre = scanNombre("Introduce el nuevo nombre: ");
		int precio = scanPrecio("Introduce el nuevo precio: ");
		long categoria;
		do {
			categoria = scanId("Introduce la categoría que quieres actualizar: ");
		}while(!existeID(categoria, "articulo"));
		
		PreparedStatement statement = ArticuloDao.conecta().prepareStatement(
				"update articulo set nombre=?, precio=?, categoria=? where id = ?"
		);
		statement.setString(1, nombre);
		statement.setInt(2, precio);
		statement.setLong(3, categoria);
		statement.setLong(4, id);
		statement.executeUpdate();
	}
	
	public static void borrar() throws SQLException{
		long id;
		do {
			id = scanId("Introduce el id que quieres actualizar: ");
		}while(!existeID(id, "articulo"));
		PreparedStatement statement = ArticuloDao.conecta().prepareStatement(
				"delete from articulo where id = ?"
		);
		statement.setLong(1, id);	
	}
	
	public static void consultar() throws SQLException{
		long id;
		do {
			id = scanId("Introduce el id que quieres consultar: ");
		}while(!existeID(id, "articulo"));
		PreparedStatement statement = ArticuloDao.conecta().prepareStatement(
				"select nombre, precio, categoria from articulo where id = ?"
		);
		statement.setLong(1, id);
		ResultSet rs = statement.executeQuery();
		rs.next();
		System.out.printf("%-4s | %-40s | %-8s | %s%n", "ID", "Nombre", "Precio", "Categoria");
        String nombre = rs.getString("nombre");
        String precio = rs.getString("precio");
        String categoria = rs.getString("categoria");
        System.out.printf("%-4s | %-40s | %-8s | %s%n", id, nombre, precio, categoria);
	}
	
	public static void lectura() throws SQLException{
		Statement statement = ArticuloDao.conecta().createStatement();
	    ResultSet resultset = statement.executeQuery("select * from articulo");
	    System.out.printf("%-4s | %-40s | %-8s | %s%n", "ID", "Nombre", "Precio", "Categoria");
	    while (resultset.next() ) {
	        String id = resultset.getString("id");
	        String nombre = resultset.getString("nombre");
	        String precio = resultset.getString("precio");
	        String categoria = resultset.getString("categoria");
	        System.out.printf("%-4s | %-40s | %-8s | %s%n", id, nombre, precio, categoria);	    }   
	}

	
}
