package persistencia;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.sqlite.SQLiteConfig;

public class ConexionBBDD {
	private String driver;
	private String url;
	
	public ConexionBBDD() {
		
		try {
			InputStreamReader fichero = new FileReader("database/integrapp.properties");
			Properties ficheroPropiedades = new Properties();
			ficheroPropiedades.load(fichero);
			this.driver = ficheroPropiedades.getProperty("driver");
			this.url = ficheroPropiedades.getProperty("url");
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Connection conectarBd() throws ClassNotFoundException, SQLException {
		SQLiteConfig config = new SQLiteConfig();  
	    config.enforceForeignKeys(true);  
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, config.toProperties());
		
		return con;
	}
}
