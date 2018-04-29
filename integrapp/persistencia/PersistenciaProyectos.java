package persistencia;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			//Invoca el m�todo que permite conectar a la base de datos
			con = conexion.conectarBd();
			
			//Prepara la sentencia realizar una transacci�n en la base de datos
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
			}
		
			//Itera la lista de Alumnos y los va agregando a la tabla realizan junto al identificador del proyecto
			query = "INSERT INTO REALIZAN VALUES(?,?)";
			
			for (int i = 0; i < proyecto.getListaAlumnos().size(); i++) {
				ps=con.prepareStatement(query);
				ps.setInt(1, proyecto.getListaAlumnos().get(i).getIdAlumno());
				ps.setInt(2, id);
				ps.executeQuery();
			}
		
			JOptionPane.showMessageDialog(null, "La Operaci�n se ha realizado con �xito");
			  
			} catch(Exception e) { 
				JOptionPane.showMessageDialog(null, e.getMessage()); 
			
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
	
	public void eliminarProyecto() {
		
	}
	
	public void modificarProyecto() {
		
	}
	
	public ProyectoPojo listadoProyectosDb() {
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
			}
				
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		} finally {
			
			try {
				if (st != null) {
					st.close();
				}
			
				if (con != null) {
					con.close();
				}
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return proyecto;
	}
	
	public void consultaProyectosAnyo(int anyo) {
		
	}
	
	public void consultaProyectosCurso(int curso) {
		
	}
	
	public void consultaProyectosCiclo(int ciclo) {
		
	}
	
	public void consultaProyectosNombre(String nombre) {
		
	}
}

