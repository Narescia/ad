package serpis.ad;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
	
	public enum Option {Salir, Nuevo, Editar, Eliminar, Consultar, Listar};
	public static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		while (true) {
			Option option = scanOption();
			if(option == Option.Salir)
				break;
			else if (option == Option.Nuevo)
				;
			else if (option == Option.Editar)
				;
			else if (option == Option.Eliminar)
				;
			else if (option == Option.Consultar)
				;
			else if (option == Option.Listar)
				;
			
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

}
