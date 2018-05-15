package persistencia;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JLabel;
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
		
		String query = "INSERT INTO " + TableContracts.AlumnosContracts.TABLA + " " + "("
				+ TableContracts.AlumnosContracts.EXPEDIENTE_RELATIVO + ", " + TableContracts.AlumnosContracts.NOMBRE_RELATIVO + ", "
				+ TableContracts.AlumnosContracts.APELLIDO1_RELATIVO + ", " + TableContracts.AlumnosContracts.APELLIDO2_RELATIVO
				+ ") VALUES (?,?,?,?)";
		
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
		
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "No se ha podido conectar a la base de datos");
		
		} catch(SQLException e2) {
			
			if (e2.getErrorCode() == 19) {
				JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos e introducir un expediente que no esté utilizado");
			
			} else {
				JOptionPane.showMessageDialog(null, e2.getMessage());
			}
			
		
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
		
		String query = "SELECT " + TableContracts.RealizanContracts.PROYECTO + " FROM " 
				+ TableContracts.RealizanContracts.TABLA + " WHERE " + TableContracts.RealizanContracts.ALUMNO 
				+ " = " + alumnoEliminar.getIdAlumno();
		
		int proy = 0;
		int cont = 0;
		String titularidadUnica = "";
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
				//query = "SELECT COUNT(ALUMNO) FROM REALIZAN WHERE PROYECTO = " + proyectos.get(i);
				
				query = "SELECT COUNT (" + TableContracts.RealizanContracts.ALUMNO_RELATIVO + ") FROM " 
				+ TableContracts.RealizanContracts.TABLA + " WHERE " + TableContracts.RealizanContracts.PROYECTO_RELATIVO 
				+ " = " + proyectos.get(i);
				
				st = con.createStatement();
				rs = st.executeQuery(query);
				
				while (rs.next()) {
					cont = rs.getInt(1);
					
					//Si el proyecto tiene menos de dos alumnos no permite dejar el proyecto sin al menos un alumno
					if (cont < 2) {
						titularidadUnica += proyectos.get(i) + " -";
						cancelarOperacion = true;
					}
				}
			}
			
			st.close();
			rs.close();
			
			//Elimina definitivamente los alumnos si en todos los proyectos hay mas de 1 participante
			for (int i = 0; i < proyectos.size() && !cancelarOperacion; i++) {
				//query = "DELETE FROM REALIZAN WHERE PROYECTO = ? AND ALUMNO = ?";
				
				query = "DELETE FROM " + TableContracts.RealizanContracts.TABLA +  " WHERE " 
				+ TableContracts.RealizanContracts.PROYECTO_RELATIVO + " = ? AND " 
				+ TableContracts.RealizanContracts.ALUMNO_RELATIVO + " = ?"; 
				
				ps=con.prepareStatement(query);
				ps.setInt(1, proyectos.get(i));
				ps.setInt(2, alumnoEliminar.getIdAlumno());
				ps.executeUpdate();
			}
			
			if(ps != null) {
				ps.close();
			}
			
			//Elimina el alumno de la tabla Alumnos si es posible
			query = "DELETE FROM " + TableContracts.AlumnosContracts.TABLA
					+ " WHERE " + TableContracts.AlumnosContracts.IDENTIFICADOR_RELATIVO + " = ?";
			
			ps=con.prepareStatement(query);
			ps.setInt(1, alumnoEliminar.getIdAlumno());
			
			//Ejecuta la sentencia SQL
			ps.executeUpdate(); 
			JOptionPane.showMessageDialog(null, "La Operación se ha realizado con éxito");
		
		} catch(SQLException | ClassNotFoundException e) {
			if(e.getMessage().equals("[SQLITE_CONSTRAINT_FOREIGNKEY]  A foreign key constraint failed (FOREIGN KEY constraint failed)")) {
				
				JOptionPane.showMessageDialog(null, new JLabel("<html><body style='text-align: center'>"
						+ " No es posible dejar un proyecto sin al menos un alumno asociado.<br>Elimine primero el proyecto " + titularidadUnica + "</html>"));
			}
			JOptionPane.showMessageDialog(null, "No se ha podido realizar la operación");
		
		//Bloque final para cerrar las conexiones y liberar recursos
		} finally {
		
			desconectarPs(con, ps);
		}
	}
	
	public void modificarAlumnoBd(AlumnoPojo alumno) {
		Connection con = null;
		PreparedStatement ps = null;
		
		String query = "UPDATE " + TableContracts.AlumnosContracts.TABLA + " SET " 
		+ TableContracts.AlumnosContracts.EXPEDIENTE_RELATIVO + " = ?, " 
		+ TableContracts.AlumnosContracts.NOMBRE_RELATIVO + " = ?, " 
		+ TableContracts.AlumnosContracts.APELLIDO1_RELATIVO + " = ?, " 
		+ TableContracts.AlumnosContracts.APELLIDO2_RELATIVO + " = ? WHERE " 
		+ TableContracts.AlumnosContracts.IDENTIFICADOR_RELATIVO + " = ?";
		
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
		
		String query = "SELECT * FROM " + TableContracts.AlumnosContracts.TABLA + " ORDER BY " 
		+ TableContracts.AlumnosContracts.EXPEDIENTE_RELATIVO;
		
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
		//String query = "SELECT ALUMNOS.ID_A, ALUMNOS.EXPEDIENTE, ALUMNOS.NOMBRE, ALUMNOS.APELLIDO1, "
			//	+ "ALUMNOS.APELLIDO2 FROM ALUMNOS, REALIZAN, PROYECTOS WHERE  ALUMNOS.ID_A = REALIZAN.ALUMNO "
				//+ "AND PROYECTOS.ID_P = REALIZAN.PROYECTO AND REALIZAN.PROYECTO = " + id;
		
		String query = "SELECT " +  TableContracts.AlumnosContracts.IDENTIFICADOR + ", " 
		+ TableContracts.AlumnosContracts.EXPEDIENTE + ", " + TableContracts.AlumnosContracts.NOMBRE + ", " 
		+ TableContracts.AlumnosContracts.APELLIDO1 + ", " + TableContracts.AlumnosContracts.APELLIDO2 + " FROM " 
		+ TableContracts.AlumnosContracts.TABLA + ", " + TableContracts.RealizanContracts.TABLA + ", " 
		+ TableContracts.ProyectosContracts.TABLA + " WHERE " + TableContracts.AlumnosContracts.IDENTIFICADOR + " = " 
		+ TableContracts.RealizanContracts.ALUMNO + " AND " + TableContracts.ProyectosContracts.IDENTIFICADOR + " = " 
		+ TableContracts.RealizanContracts.PROYECTO + " AND " + TableContracts.RealizanContracts.PROYECTO + " = " + id; 
		
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
		//String query = "SELECT ALUMNOS.ID_A, ALUMNOS.EXPEDIENTE, ALUMNOS.NOMBRE, ALUMNOS.APELLIDO1, "
			//	+ "ALUMNOS.APELLIDO2 FROM ALUMNOS, REALIZAN WHERE ALUMNOS.ID_A = REALIZAN.ALUMNO AND REALIZAN.PROYECTO = " + idProyecto;
		
		String query = "SELECT " + TableContracts.AlumnosContracts.IDENTIFICADOR + ", " 
		+ TableContracts.AlumnosContracts.EXPEDIENTE + ", " + TableContracts.AlumnosContracts.NOMBRE + ", " 
		+ TableContracts.AlumnosContracts.APELLIDO1 + ", " + TableContracts.AlumnosContracts.APELLIDO2 
		+ " FROM " + TableContracts.AlumnosContracts.TABLA + ", " + TableContracts.RealizanContracts.TABLA 
		+ " WHERE " + TableContracts.AlumnosContracts.IDENTIFICADOR + " = " + TableContracts.RealizanContracts.ALUMNO 
		+ " AND " + TableContracts.RealizanContracts.PROYECTO + " = " + idProyecto; 
		
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
