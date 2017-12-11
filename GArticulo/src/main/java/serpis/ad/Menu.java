package serpis.ad;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import java.sql.PreparedStatement;

public class Menu {
	
	public static void insertar () throws SQLException{
		String nombre = ArticuloDao.scanNombre("Introduce un nombre: ");
		int precio = ArticuloDao.scanPrecio("Introduce un precio: ");
		long categoria;
		do {
			categoria = ArticuloDao.scanId("Introduce una categoría: ");
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
//		Statement statement = ArticuloDao.conecta().createStatement();
		long id;
		do {
			id = ArticuloDao.scanId("Introduce el id que quieres actualizar: ");
		}while(!ArticuloDao.existeID(id, "articulo"));
		String nombre = ArticuloDao.scanNombre("Introduce el nuevo nombre: ");
		int precio = ArticuloDao.scanPrecio("Introduce el nuevo precio: ");
		long categoria;
		do {
			categoria = ArticuloDao.scanId("Introduce la categoría que quieres actualizar: ");
		}while(!ArticuloDao.existeID(categoria, "articulo"));
		
		PreparedStatement statement = ArticuloDao.conecta().prepareStatement(
				"update articulo set nombre=?, precio=?, categoria=? where id = ?"
		);
		statement.setString(1, nombre);
		statement.setInt(2, precio);
		statement.setLong(3, categoria);
		statement.setLong(4, id);
		statement.executeUpdate();
		
/*		statement.execute("update articulo "
				+ "set "
				+ "nombre='"+nombre+"', "
				+ "precio="+precio+", "
				+ "categoria="+categoria+ " where id = " + id);	*/
	}
	
	
	public static void borrar() throws SQLException{
		long id;
		do {
			id = ArticuloDao.scanId("Introduce el id que quieres actualizar: ");
		}while(!ArticuloDao.existeID(id, "articulo"));
		PreparedStatement statement = ArticuloDao.conecta().prepareStatement(
				"delete from articulo where id = ?"
		);
		statement.setLong(1, id);
		
	}
	public static void consultar() throws SQLException{
		long id;
		do {
			id = ArticuloDao.scanId("Introduce el id que quieres consultar: ");
		}while(!ArticuloDao.existeID(id, "articulo"));
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
