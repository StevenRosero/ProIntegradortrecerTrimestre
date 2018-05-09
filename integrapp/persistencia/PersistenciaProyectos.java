package persistencia;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.CicloFormativoPojo;
import modelo.ProyectoPojo;

public class PersistenciaProyectos {
	private ConexionBBDD conexion;
	
	public PersistenciaProyectos(){
		conexion = new ConexionBBDD();
	}

	public void agregarProyecto(ProyectoPojo proyecto) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement st = null;
		String query = "INSERT INTO PROYECTOS(NOMBRE, DESCRIPCION, URL, ANYO, NOTA, CURSO, GRUPO, IMAGEN, CICLO) "
				+ "VALUES(?,?,?,?,?,?,?,?,?)";
		
		
		try {
			//Invoca el método que permite conectar a la base de datos
			con = conexion.conectarBd();
			
			//Prepara la sentencia realizar una transacción en la base de datos
			ps=con.prepareStatement(query);
			ps.setString(1, proyecto.getNombre());
			ps.setString(2, proyecto.getDescripcion());
			ps.setString(3, proyecto.getUrl());
			ps.setInt(4, proyecto.getAnyo());
			ps.setDouble(5, proyecto.getNota());
			ps.setInt(6, proyecto.getCurso());
			ps.setString(7, proyecto.getGrupo());
			ps.setBytes(8, proyecto.getBlobImagen());
			ps.setInt(9, proyecto.getCiclo().getIdentificador());
			
			//Ejecuta la sentencia sql
			ps.executeUpdate();
			
			//Ejecuta la sentencia para agregar los alumnos del proyecto en la tabla Realizan
			query = "SELECT MAX(ID_P) FROM PROYECTOS"; 
			int id = 0;
			st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while (rs.next()) {
				id = rs.getInt(1);
				System.out.println(id);
			}
		
			//Itera la lista de Alumnos y los va agregando a la tabla realizan junto al identificador del proyecto
			ps.close();
			query = "INSERT INTO REALIZAN VALUES(?,?)";
			
			for (int i = 0; i < proyecto.getListaAlumnos().size(); i++) {
				ps=con.prepareStatement(query);
				ps.setInt(1, proyecto.getListaAlumnos().get(i).getIdAlumno());
				ps.setInt(2, id);
				ps.executeUpdate();
			}
		
			JOptionPane.showMessageDialog(null, "La Operación se ha realizado con éxito");
			  
			} catch(SQLException e) { 
				JOptionPane.showMessageDialog(null, "Solo puede dejar en blanco la URL, GRUPO e IMAGEN"); 
			
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, "No se ha podido conectar con la Base de Datos"); 
			
			} finally {
				
				try {
					if (st != null) {
						st.close();
					}
					
					if(ps != null) {
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
	
	public void eliminarProyecto(ProyectoPojo proyecto) {
		Connection con = null;
		PreparedStatement ps = null;
		String query = "DELETE FROM REALIZAN WHERE PROYECTO = " + proyecto.getIdProyecto() ;
		
		try {
			con = conexion.conectarBd();
			ps = con.prepareStatement(query);
			ps.executeUpdate();
			ps.close();
			
			query = "DELETE FROM PROYECTOS WHERE ID_P = " + proyecto.getIdProyecto() ;
			ps = con.prepareStatement(query);
			ps.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "La Operación se ha realizado con éxito");
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			desconectarPs(con, ps);
		}
	}
	
	public void modificarProyecto(ProyectoPojo proyecto) {
		Connection con = null;
		PreparedStatement ps = null;
		String query = "UPDATE PROYECTOS SET NOMBRE = ?, DESCRIPCION = ?, URL = ?, ANYO = ?, NOTA = ?, "
				+ "CURSO = ?, GRUPO = ?, IMAGEN = ?, CICLO = ? WHERE ID_P = ?";
		
		try {
			con = conexion.conectarBd();
			ps = con.prepareStatement(query);
			ps.setString(1, proyecto.getNombre());
			ps.setString(2, proyecto.getDescripcion());
			ps.setString(3, proyecto.getUrl());
			ps.setInt(4, proyecto.getAnyo());
			ps.setDouble(5, proyecto.getNota());
			ps.setInt(6, proyecto.getCurso());
			ps.setString(7, proyecto.getGrupo());
			ps.setBytes(8, proyecto.getBlobImagen());
			ps.setInt(9, proyecto.getCiclo().getIdentificador());
			ps.setInt(10, proyecto.getIdProyecto());
			
			ps.executeUpdate();
			ps.close();
			
			//Itera la lista de Alumnos y los va agregando a la tabla realizan junto al identificador del proyecto
			query = "INSERT INTO REALIZAN VALUES(?,?)";
			
			for (int i = 0; i < proyecto.getListaAlumnos().size(); i++) {
				ps=con.prepareStatement(query);
				ps.setInt(1, proyecto.getListaAlumnos().get(i).getIdAlumno());
				ps.setInt(2, proyecto.getIdProyecto());
				ps.executeUpdate();	
				ps.close();
			}
			
			//Itera la lista de Alumnos y los elimina la tabla realizan junto al identificador del proyecto
			query = "DELETE FROM REALIZAN WHERE ALUMNO = ? AND PROYECTO = ?";
			
			for (int i = 0; i < proyecto.getListAlumnosEliminar().size(); i++) {
				ps=con.prepareStatement(query);
				ps.setInt(1, proyecto.getListAlumnosEliminar().get(i).getIdAlumno());
				ps.setInt(2, proyecto.getIdProyecto());
				ps.executeUpdate();	
			}
			
			JOptionPane.showMessageDialog(null, "La Operación se ha realizado con éxito");
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			desconectarPs(con, ps);
		}
	}
	
	public ArrayList<ProyectoPojo> listadoProyectosDb() {
		ArrayList<ProyectoPojo> listaProyectos = new ArrayList<ProyectoPojo>();
		ProyectoPojo proyecto = null;
		Connection con = null;
		Statement st = null;
		String query = "SELECT ID_P, NOMBRE, DESCRIPCION, URL, ANYO, NOTA, CURSO, GRUPO, IMAGEN, CICLO FROM PROYECTOS";
		
		try {
			con = conexion.conectarBd();
			st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				proyecto = new ProyectoPojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getInt(5), rs.getDouble(6), rs.getInt(7), rs.getString(8), rs.getBytes(9), new CicloFormativoPojo(rs.getInt(10)));
			
				listaProyectos.add(proyecto);
			}
				
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		} finally {
			
			desconectarSt(con, st);
		}
		return listaProyectos;
	}
	
	public ArrayList<ProyectoPojo> consultaProyectos(String query) {
		ArrayList<ProyectoPojo> listaProyectos = new ArrayList<ProyectoPojo>();
		ProyectoPojo proyecto = null;
		Connection con = null;
		Statement st = null;
		
		try {
			con = conexion.conectarBd();
			st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				proyecto = new ProyectoPojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getInt(5), rs.getDouble(6), rs.getInt(7), rs.getString(8), rs.getBytes(9), new CicloFormativoPojo(rs.getInt(10)));
				
				listaProyectos.add(proyecto);
				System.out.println(proyecto);
			}
				
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		} finally {
			
			desconectarSt(con, st);
		}
		return listaProyectos;
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

