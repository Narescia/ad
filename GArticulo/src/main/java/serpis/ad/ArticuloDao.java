package serpis.ad;

import java.sql.Connection;
import java.sql.DriverManager;
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
				"jdbc:mysql://localhost/dbprueba", "root", "sistemas"
				);
		return connection;
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
	
	public static Option scanOption() {
		for (int index = 0; index < Option.values().length; index++)
			System.out.printf("%s - %s\n", index, Option.values()[index]);
		String options = String.format("^[0-%s]$", Option.values().length - 1);
		while (true) {
			System.out.println("Elige opción: ");
			String line = scanner.nextLine();
			if(line.matches(options))
				return Option.values()[Integer.parseInt(line)];
			System.out.println("OPción inválida. Vuelve a introducir");
		}
	}
	
	public static State scanState() {
		for (int index = 0; index < State.values().length; index++)
			System.out.printf("%s - %s\n", index, State.values()[index]);
		String options = String.format("^[0-%s]$", State.values().length - 1);
		while (true) {
			System.out.println("Elige opción: ");
			String line = scanner.nextLine();
			if(line.matches(options))
				return State.values()[Integer.parseInt(line)];
			System.out.println("Opción inválida. Vuelve a introducir");
		}
	}
	
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
	
	public static void nuevo () {
		//TODO implementar
	}
	public static void modificar () {
		//TODO implementar
	}
	
	public static void borrar() throws SQLException{
		Statement statement = conecta().createStatement();
		scanId("Introduce el id que quieres borrar: ");
		ResultSet resultset = statement.executeQuery("delete from articulo where id =");
	}
	
}
