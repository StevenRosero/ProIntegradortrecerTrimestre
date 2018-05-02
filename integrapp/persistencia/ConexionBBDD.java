package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.sqlite.SQLiteConfig;

public class ConexionBBDD {
	private String driver = "org.sqlite.JDBC";
	private String url = "jdbc:sqlite:DbIntegraApp.db";
	
	public ConexionBBDD() {
	
	}
	
	public Connection conectarBd() throws ClassNotFoundException, SQLException {
		SQLiteConfig config = new SQLiteConfig();  
	    config.enforceForeignKeys(true);  
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, config.toProperties());
		
		return con;
	}
}
