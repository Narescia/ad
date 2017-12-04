package serpis.ad;

import java.sql.SQLException;
import java.util.Scanner;

public class ArticuloMain {

	public enum Option {Salir, Nuevo, Editar, Eliminar, Consultar, Listar};
	public enum State {Vacío, Medio, Lleno};
	public static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) throws SQLException{
//		ArticuloDao.scan(Option.class);
//		
//		Runnable runnable = () -> nuevo();
//		runnable.run();
//		new Menu.add("Salir", null)
//			.add("Nuevo", () -> nuevo())
//			.add("Editar", () -> editar())
//			.run();
//		ArticuloDao.scan(State.class);
		
//		while (true) {
//			
//			Option option = ArticuloDao.scanOption();
//			
//			if(option == Option.Salir)
//				break;
//			else if (option == Option.Nuevo)
//				runnable = () -> nuevo();
//			else if (option == Option.Editar)
//				;
//			else if (option == Option.Eliminar)
//				ArticuloDao.borrar();
//			else if (option == Option.Consultar)
//				;
//			else if (option == Option.Listar)
//				;
//			runnable.run();	
//			
//		}
		
	}
	

}


