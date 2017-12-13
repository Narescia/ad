package serpis.ad;

import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Field;

public class ArticuloMain {

	public enum Option {Salir, Nuevo, Editar, Eliminar, Consultar, Listar};
	public enum State {Vacío, Medio, Lleno};
	public static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) throws SQLException{
		
		//showFields(String.class);
		Articulo articulo = new Articulo();
//		ArticuloDao.scan(Option.class);
//		
		
		new Menu()
			.add("Salir", null)
			.add("Nuevo", () -> ArticuloDao.insertar())
			.add("Editar", () -> ArticuloDao.editar())
			.run();
		
//		while (true) {	
//			Option option = Menu.scanOption();
//			if(option == Option.Salir)
//				break;
//			else if (option == Option.Nuevo)
//				ArticuloDao.insertar();
//			else if (option == Option.Editar)
//				ArticuloDao.editar();
//			else if (option == Option.Eliminar)
//				ArticuloDao.borrar();
//			else if (option == Option.Consultar)
//				ArticuloDao.consultar();
//			else if (option == Option.Listar)
//				ArticuloDao.lectura();
			//runnable.run();	

	}
	

}


