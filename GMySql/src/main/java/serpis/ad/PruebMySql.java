package serpis.ad;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface PruebMySql {
	
	public static void main(String[] args) throws SQLException{
		//TODO conectar...
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/dbprueba", "root", "sistemas"
		);
		
		
		Statement statement = connection.createStatement();
	    ResultSet resultset = statement.executeQuery("select * from categoria");
	    
	    while (resultset.next() ) {
	        int numColumns = resultset.getMetaData().getColumnCount();
	        for ( int i = 1 ; i <= numColumns ; i++ ) {
	           
	           System.out.println( "Columna" + i + " = " + resultset.getObject(i) );
	        }
	    }
	    connection.close();
	}

}
