package serpis.ad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ArticuloDao{
	
	private static Connection connection;
	private static Scanner scanner = new Scanner(System.in);
	
	public static Connection conecta() throws SQLException{
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/dbprueba", "root", "sistemas"
				);
		return connection;
	}
	
	public static void lectura() throws SQLException{
		Statement statement = conecta().createStatement();
	    ResultSet resultset = statement.executeQuery("select * from articulo");
	    System.out.println("ID \t Nombre \t Precio \t Categoría");
	    while (resultset.next() ) {
	        String id = resultset.getString("id");
	        String nombre = resultset.getString("nombre");
	        String precio = resultset.getString("precio");
	        String categoria = resultset.getString("categoria");
	        System.out.println(id + "\t" + nombre + "\t" + precio + "\t"+"\t" + categoria);
	    }   
	}
	public static void delete() throws SQLException{
		Statement statement = conecta().createStatement();
		scanId("Introduce el id que quieres borrar: ");
		ResultSet resultset = statement.executeQuery("delete from articulo where id = ");
	}
	
	public static int scanId(String label) {
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
	
}
