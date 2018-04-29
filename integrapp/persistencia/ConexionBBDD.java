package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBBDD {
	private String driver = "org.sqlite.JDBC";
	private String url = "jdbc:sqlite:DbIntegraApp.db";
	
	public ConexionBBDD() {
	
	}

	public Connection conectarBd() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url);
		
		return con;
	}
}
