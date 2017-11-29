package serpis.ad;

import java.sql.SQLException;

public class Menu {

	public static void main(String[] args) {
		try {
			ArticuloDao.lectura();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ArticuloDao.delete();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
