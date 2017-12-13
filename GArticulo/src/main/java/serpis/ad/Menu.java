package serpis.ad;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.sql.PreparedStatement;

public class Menu {
	
	private static Scanner scanner = new Scanner(System.in);
	
	private class Option{
		public String label;
		public Runnable runnable;
		public Option(String label, Runnable runnable) {
			this.label = label;
			this.runnable = runnable;
		}
	}
		
	private List<Option> options = new ArrayList<>();
		
	public Menu () {		
	}
		
	public Menu add(String label, Runnable runnable) {
		options.add(new Option(label, runnable));
		return this;
	}
	
	public void run() {
		while(true) {
			Option option = scanOption();
			if(option.runnable == null)
				return;
			option.runnable.run();
		}
	}
		
	private Option scanOption() {
		String validOptions = String.format("^[0-%s]$", options.size()- 1);
		for(int index = 0; index < options.size(); index++)
			System.out.printf("%s - %s\n", index, options.get(index).label);
		while (true) {
			System.out.println("Elige opción: ");
			String option = scanner.nextLine();
			if(option.matches(validOptions))
				return options.get(Integer.parseInt(option));
			System.out.println("Opción inválida. Vuelve a introducir.");
		}	
		
	}
	
}
