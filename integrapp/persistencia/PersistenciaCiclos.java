package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.AlumnoPojo;
import modelo.CicloFormativoPojo;

public class PersistenciaCiclos {
	private ConexionBBDD conexion;
	
	
	
	
	public PersistenciaCiclos() {
		conexion = new ConexionBBDD();
	}

	public void agregarCicloBd(CicloFormativoPojo ciclo) {
		Connection con = null;
		PreparedStatement ps = null;
		String query = "INSERT INTO CICLOS(NOMBRE, DESCRIPCION) VALUES(?,?)";
		
		//Prepara la sentencia SQL para insertar Alumno
		try {
			con = conexion.conectarBd();
			ps=con.prepareStatement(query);
			ps.setString(1, ciclo.getNombre());
			ps.setString(2, ciclo.getDescripcion());
			
			//Ejecuta la sentencia SQL de inserción
			ps.executeUpdate(); 
			JOptionPane.showMessageDialog(null, "La Operación se ha realizado con éxito");
		
		} catch(SQLException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "No se ha podido realizar la operación" + e.getMessage());
		
		//Bloque final para cerrar las conexiones y liberar recursos
		} finally {
		
			try {
				
				if (ps != null) {
					ps.close();	
				}
			
				if (con != null) {
					con.close();
				}
			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void eliminarCicloBd() {
		
	}
	
	public void modificarCicloBd() {
		
	}
	
	public ArrayList<CicloFormativoPojo> listadoCiclosBd() {
		Connection con = null;
		Statement st = null;
		ArrayList<CicloFormativoPojo> listaCiclosFormativos = new ArrayList<CicloFormativoPojo>();
		CicloFormativoPojo ciclo = null;
		String query = "SELECT * FROM CICLOS ORDER BY ID_C";
		
		try {
			con = conexion.conectarBd();
			st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				ciclo = new CicloFormativoPojo(rs.getInt(1), rs.getString(2), rs.getString(3));
				
				listaCiclosFormativos.add(ciclo);
			}
				
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null, "No se ha podido realizar la operación de carga de Alumnos");
		
		} finally {
			
			try {
				if (st != null) {
					st.close();
				}
			
				if (con != null) {
					con.close();
				}
			
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		return listaCiclosFormativos;
	}
}
