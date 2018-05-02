package persistencia;

import java.sql.*;
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
		
			desconectarPs(con, ps);
		}
	}
	
	public void eliminarAlumnoBd(AlumnoPojo alumnoEliminar) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Statement st = null;
		ArrayList<Integer> proyectos = new ArrayList<Integer>();
		String query = "SELECT PROYECTO FROM REALIZAN WHERE ALUMNO = " + alumnoEliminar.getIdAlumno();
		int proy = 0;
		int cont = 0;
		boolean cancelarOperacion = false;;
		
		//Prepara la sentencia SQL para eliminar Alumno
		try {
			
			con = conexion.conectarBd();
			st = con.createStatement();
			rs = st.executeQuery(query);
			
			//Almacena en un array los proyectos en los que el alumno tiene vinculaciones
			while (rs.next()) {
				proy = rs.getInt(1);
				proyectos.add(proy);
			}
			
			st.close();
			rs.close();
			
			//Itera el array para ver si algún proyecto tiene menos de 2 participantes.
			for (int i = 0; i < proyectos.size(); i++) {
				query = "SELECT COUNT(ALUMNO) FROM REALIZAN WHERE PROYECTO = " + proyectos.get(i);
				st = con.createStatement();
				rs = st.executeQuery(query);
				
				while (rs.next()) {
					cont = rs.getInt(1);
					
					//Si el proyecto tiene menos de dos alumnos no permite dejar el proyecto sin al menos un alumno
					if (cont < 2) {
						cancelarOperacion = true;
					}
				}
			}
			
			st.close();
			rs.close();
			
			//Elimina definitivamente los alumnos si en todos los proyectos hay mas de 1 participante
			for (int i = 0; i < proyectos.size() && !cancelarOperacion; i++) {
				query = "DELETE FROM REALIZAN WHERE PROYECTO = ? AND ALUMNO = ?";
				ps=con.prepareStatement(query);
				ps.setInt(1, proyectos.get(i));
				ps.setInt(2, alumnoEliminar.getIdAlumno());
				ps.executeUpdate();
			}
			
			if(ps != null) {
				ps.close();
			}
			
			//Elimina el alumno de la tabla Alumnos si es posible
			query = "DELETE FROM ALUMNOS WHERE ID_A = ?";
			ps=con.prepareStatement(query);
			ps.setInt(1, alumnoEliminar.getIdAlumno());
			
			//Ejecuta la sentencia SQL
			ps.executeUpdate(); 
			JOptionPane.showMessageDialog(null, "La Operación se ha realizado con éxito");
		
		} catch(SQLException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "No se ha podido realizar la operación" + e.getMessage());
		
		//Bloque final para cerrar las conexiones y liberar recursos
		} finally {
		
			desconectarPs(con, ps);
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
			
			desconectarPs(con, ps);
		}
	}
	
	public ArrayList<AlumnoPojo> listaAlumnosBd() {
		Connection con = null;
		Statement st = null;
		ArrayList<AlumnoPojo> listaAlumnosBaseDatos = new ArrayList<AlumnoPojo>();
		AlumnoPojo alumno = null;
		String query = "SELECT * FROM ALUMNOS ORDER BY ID_A";
		
		//Prepara la sentencia SQL para almacenar la lista de alumnos de la base de datos
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
		
		//Bloque final para cerrar las conexiones y liberar recursos
		} finally {
			
			desconectarSt(con, st);
		}
		
		return listaAlumnosBaseDatos;
	}
	
	public ArrayList<AlumnoPojo> consultaAlumnosBd(int id) {
		Connection con = null;
		Statement st = null;
		ArrayList<AlumnoPojo> listaAlumnosBaseDatos = new ArrayList<AlumnoPojo>();
		AlumnoPojo alumno = null;
		String query = "SELECT ALUMNOS.ID_A, ALUMNOS.EXPEDIENTE, ALUMNOS.NOMBRE, ALUMNOS.APELLIDO1, "
				+ "ALUMNOS.APELLIDO2 FROM ALUMNOS, REALIZAN, PROYECTOS WHERE  ALUMNOS.ID_A = REALIZAN.ALUMNO "
				+ "AND PROYECTOS.ID_P = REALIZAN.PROYECTO AND REALIZAN.PROYECTO = " + id;
		
		//Prepara la sentencia SQL para consultar los alumnos que corrresponden a un proyecto
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
			
			desconectarSt(con, st);
		}
		
		return listaAlumnosBaseDatos;
	}
	public ArrayList<AlumnoPojo> consultaAlumnosDeProyecto(int idProyecto) {
		ArrayList<AlumnoPojo> listAlumnos = new ArrayList<AlumnoPojo>();
		AlumnoPojo alumno;
		Connection con = null;
		Statement st = null;
		String query = "SELECT ALUMNOS.ID_A, ALUMNOS.EXPEDIENTE, ALUMNOS.NOMBRE, ALUMNOS.APELLIDO1, "
				+ "ALUMNOS.APELLIDO2 FROM ALUMNOS, REALIZAN WHERE ALUMNOS.ID_A = REALIZAN.ALUMNO AND REALIZAN.PROYECTO = " + idProyecto;
		
		try {
			con = conexion.conectarBd();
			st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				alumno = new AlumnoPojo(rs.getInt(1), rs.getInt(2), 
						rs.getString(3), rs.getString(4), rs.getString(5));
				
				listAlumnos.add(alumno);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		} finally {
			
			desconectarSt(con, st);
		}
		return listAlumnos;
	}
	
	private void desconectarSt(Connection con, Statement st) {
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
	
	private void desconectarPs(Connection con, PreparedStatement ps) {
		try {
			if (ps != null) {
				ps.close();
			}
			
			if (con != null) {
				con.close();
			}
				
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}
}
