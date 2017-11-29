package serpis.ad;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public interface PruebMySql {
	
//	public static void main(String[] args) throws SQLException{
//		//TODO conectar...
//		Connection connection = DriverManager.getConnection(
//				"jdbc:mysql://localhost/dbprueba", "root", "sistemas"
//		);
//		
//		Statement statement = connection.createStatement();
//	    ResultSet resultset = statement.executeQuery("select * from categoria");
//	    System.out.println("ID \t Nombre");
//	    
//	    while (resultset.next() ) {
//	        int numColumns = resultset.getMetaData().getColumnCount();
//	        for ( int i = 1 ; i <= numColumns ; i++ ) {
//	           System.out.println(resultset.getString("id")+"\t"+resultset.getString("nombre"));
//	        }
//	    }
//	    connection.close();
//	    
//	 
//	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
	
	}

}
