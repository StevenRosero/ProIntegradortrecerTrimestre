package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.AlumnoPojo;

public class PersistenciaAlumnos {
	private ConexionBBDD conexion;
	
	
	public PersistenciaAlumnos() {
		conexion = new ConexionBBDD();
	}
	
	public void agregarAlumnoBd(AlumnoPojo alumno) {
		Connection con = null;
		PreparedStatement ps = null;
		String query = "INSERT INTO ALUMNOS(EXPEDIENTE, NOMBRE, APELLIDO1, APELLIDO2)"
				+ " VALUES(?,?,?,?)";
		
		//Prepara la sentencia SQL para insertar Alumno
		try {
			con = conexion.conectarBd();
			ps=con.prepareStatement(query);
			ps.setInt(1, alumno.getExpediente());
			ps.setString(2, alumno.getNombre());
			ps.setString(3, alumno.getApellido1());
			ps.setString(4, alumno.getApellido2());
			
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
	
	public void eliminarAlumnoBd(AlumnoPojo alumnoEliminar) {
		Connection con = null;
		PreparedStatement ps = null;
		String query = "DELETE FROM ALUMNOS WHERE ID_A = ?";
		
		//Prepara la sentencia SQL para eliminar Alumno
		try {
			con = conexion.conectarBd();
			ps=con.prepareStatement(query);
			ps.setInt(1, alumnoEliminar.getIdAlumno());
			
			//Ejecuta la sentencia SQL
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
	
	public void modificarAlumnoBd(AlumnoPojo alumno) {
		Connection con = null;
		PreparedStatement ps = null;
		String query = "UPDATE ALUMNOS SET EXPEDIENTE = ?, NOMBRE = ?,"
				+ " APELLIDO1 = ?, APELLIDO2 = ? WHERE ID_A = ?";
		
		//Prepara la sentencia SQL para modificar Alumno
		try {
			con = conexion.conectarBd();
			ps=con.prepareStatement(query);
			ps.setInt(1, alumno.getExpediente());
			ps.setString(2, alumno.getNombre());
			ps.setString(3, alumno.getApellido1());
			ps.setString(4, alumno.getApellido2());
			ps.setInt(5, alumno.getIdAlumno());
			
			//Ejecuta la sentencia SQL
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
	
	public ArrayList<AlumnoPojo> listaAlumnosBd() {
		Connection con = null;
		Statement st = null;
		ArrayList<AlumnoPojo> listaAlumnosBaseDatos = new ArrayList<AlumnoPojo>();
		AlumnoPojo alumno = null;
		String query = "SELECT * FROM ALUMNOS ORDER BY ID_A";
		
		try {
			con = conexion.conectarBd();
			st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				alumno =  new AlumnoPojo(rs.getInt(1),rs.getInt(2),rs.getString(3),
						rs.getString(4), rs.getString(5));
				listaAlumnosBaseDatos.add(alumno);
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
		
		return listaAlumnosBaseDatos;
	}
}
