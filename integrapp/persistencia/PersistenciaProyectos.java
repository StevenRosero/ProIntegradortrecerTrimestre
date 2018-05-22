package persistencia;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.CicloFormativoPojo;
import modelo.ProyectoPojo;
import vistas.GuiConsultarProyectos;

public class PersistenciaProyectos {
	private ConexionBBDD conexion;
	
	public PersistenciaProyectos(){
		conexion = new ConexionBBDD();
	}

	public void agregarProyecto(ProyectoPojo proyecto) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement st = null;

		String query = "INSERT INTO " + TableContracts.ProyectosContracts.TABLA 
				+ "(" + TableContracts.ProyectosContracts.NOMBRE_RELATIVO + ", " 
				+ TableContracts.ProyectosContracts.DESCRIPCION_RELATIVO + ", " 
				+ TableContracts.ProyectosContracts.URL_RELATIVO + ", "
				+ TableContracts.ProyectosContracts.ANYO_RELATIVO + ", " 
				+ TableContracts.ProyectosContracts.NOTA_RELATIVO + ", " 
				+ TableContracts.ProyectosContracts.CURSO_RELATIVO + ", " 
				+ TableContracts.ProyectosContracts.GRUPO_RELATIVO + ", " 
				+ TableContracts.ProyectosContracts.IMAGEN_RELATIVO + ", " 
				+ TableContracts.ProyectosContracts.CICLO_RELATIVO + ") VALUES (?,?,?,?,?,?,?,?,?)";
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
			
			query = "SELECT MAX (" + TableContracts.ProyectosContracts.IDENTIFICADOR_RELATIVO + ") FROM " 
			+ TableContracts.ProyectosContracts.TABLA;
			
			int id = 0;
			st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while (rs.next()) {
				id = rs.getInt(1);
				System.out.println(id);
			}
		
			//Itera la lista de Alumnos y los va agregando a la tabla realizan junto al identificador del proyecto
			ps.close();
			
			query = "INSERT INTO " + TableContracts.RealizanContracts.TABLA + " VALUES(?,?)";
			
			for (int i = 0; i < proyecto.getListaAlumnos().size(); i++) {
				ps=con.prepareStatement(query);
				ps.setInt(1, proyecto.getListaAlumnos().get(i).getIdAlumno());
				ps.setInt(2, id);
				ps.executeUpdate();
			}
			
			JOptionPane.showMessageDialog(null, "La Operación se ha realizado con éxito");
			  
			} catch(SQLException e) { 
				System.out.println(e.getMessage());
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
		
		String query =  "DELETE FROM " + TableContracts.RealizanContracts.TABLA + " WHERE " 
		+ TableContracts.RealizanContracts.PROYECTO_RELATIVO + " = " + proyecto.getIdProyecto();
		
		try {
			con = conexion.conectarBd();
			ps = con.prepareStatement(query);
			ps.executeUpdate();
			ps.close();
			
			query =  "DELETE FROM " + TableContracts.ProyectosContracts.TABLA +  " WHERE " 
			+ TableContracts.ProyectosContracts.IDENTIFICADOR_RELATIVO + " = " + proyecto.getIdProyecto();
			
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
		
		String query =  "UPDATE " + TableContracts.ProyectosContracts.TABLA + " SET " 
		+ TableContracts.ProyectosContracts.NOMBRE_RELATIVO + " = ?, " 
		+ TableContracts.ProyectosContracts.DESCRIPCION_RELATIVO + " = ?, " 
		+ TableContracts.ProyectosContracts.URL_RELATIVO + " = ?, " 
		+ TableContracts.ProyectosContracts.ANYO_RELATIVO + " = ?, " 
		+ TableContracts.ProyectosContracts.NOTA_RELATIVO + " = ?, " 
		+ TableContracts.ProyectosContracts.CURSO_RELATIVO + " = ?, " 
		+ TableContracts.ProyectosContracts.GRUPO_RELATIVO + " = ?, " 
		+ TableContracts.ProyectosContracts.IMAGEN_RELATIVO + " = ?, " 
		+ TableContracts.ProyectosContracts.CICLO_RELATIVO + " = ? " 
		+ " WHERE " + TableContracts.ProyectosContracts.IDENTIFICADOR_RELATIVO + " = ?";
		
		System.out.println(proyecto);
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
			
			query = "INSERT INTO " + TableContracts.RealizanContracts.TABLA + " VALUES(?,?)";
			
			
			for (int i = 0; i < proyecto.getListaAlumnos().size(); i++) {
				ps=con.prepareStatement(query);
				ps.setInt(1, proyecto.getListaAlumnos().get(i).getIdAlumno());
				ps.setInt(2, proyecto.getIdProyecto());
				ps.executeUpdate();	
				ps.close();
			}
			
			//Itera la lista de Alumnos y los elimina la tabla realizan junto al identificador del proyecto
			
			query = "DELETE FROM " + TableContracts.RealizanContracts.TABLA + " WHERE " 
			+ TableContracts.RealizanContracts.ALUMNO_RELATIVO + " = ? AND " 
			+ TableContracts.RealizanContracts.PROYECTO_RELATIVO + " = ?";
			
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
		
		String query = "SELECT " + TableContracts.ProyectosContracts.IDENTIFICADOR_RELATIVO + ", " 
		+ TableContracts.ProyectosContracts.NOMBRE_RELATIVO + ", " 
		+ TableContracts.ProyectosContracts.DESCRIPCION_RELATIVO + ", " 
		+ TableContracts.ProyectosContracts.URL_RELATIVO + ", " 
		+ TableContracts.ProyectosContracts.ANYO_RELATIVO + ", " 
		+ TableContracts.ProyectosContracts.NOTA_RELATIVO + ", "
		+ TableContracts.ProyectosContracts.CURSO_RELATIVO + ", " 
		+ TableContracts.ProyectosContracts.GRUPO_RELATIVO + ", " 
		+ TableContracts.ProyectosContracts.IMAGEN_RELATIVO + ", " 
		+ TableContracts.ProyectosContracts.CICLO_RELATIVO 
		+ " FROM " + TableContracts.ProyectosContracts.TABLA + " ORDER BY " 
		+ TableContracts.ProyectosContracts.NOMBRE_RELATIVO;
		
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
	
	public ArrayList<ProyectoPojo> consultaProyectos(String filtro, String valorConsignadoUsuario, String curso, String grupo) {
		ArrayList<ProyectoPojo> listaProyectos = new ArrayList<ProyectoPojo>();
		ProyectoPojo proyecto = null;
		Connection con = null;
		Statement st = null;
		String query = comprobarFiltro(filtro, valorConsignadoUsuario, curso, grupo);
		
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
	
	private String comprobarFiltro(String filtro, String valorConsignadoUsuario, String curso, String grupo) {
		String query = "";
		
		//Evalua el filtro elegido por el usuario comparándolo con el valor de las constantes del comboBox pertenecientes a la vistaConsultarProyectos
		if (filtro.equals(GuiConsultarProyectos.getFiltrarPorIdentificadorDeProyecto())) {
			query =  consultaPorIdentificadorProyecto(valorConsignadoUsuario);
		
		} else if (filtro.equals(GuiConsultarProyectos.getFiltrarPorNombreDeProyecto())) {
			query = consultaPorNombreProyecto(valorConsignadoUsuario);
		
		} else if (filtro.equals(GuiConsultarProyectos.getFiltrarPorAñoDelProyecto())) {
			query = consultaPorAnyoProyecto(valorConsignadoUsuario);
					
		} else if (filtro.equals(GuiConsultarProyectos.getFiltrarPorCicloDelProyecto())) {
			query = consultarFiltroCiclo(valorConsignadoUsuario, curso, grupo);
				
		} else if (filtro.equals(GuiConsultarProyectos.getFiltrarPorNombreDelAlumno())) {
			query = consultaPorNombreAlumno(valorConsignadoUsuario); 
		
		} else if (filtro.equals(GuiConsultarProyectos.getFiltrarPorApellidoDelAlumno())) {
			query = consultaPorApellidoAlumno(valorConsignadoUsuario); 
		
		} else if (filtro.equals(GuiConsultarProyectos.getFiltrarPorExpedienteDelAlumno())) {
			query = consultaPorExpedienteAlumno(valorConsignadoUsuario); 
			
		} else if (filtro.equals(GuiConsultarProyectos.getFiltrarPorCursoYGrupoDeProyecto())) {
			query =  filtroCursoyGrupo(curso, grupo);
		
		} else {
			query = consultaTodosLosProyectos();
		}
		return query;
	}

	private String filtroCursoyGrupo(String curso, String grupo) {
		String query = "";
		
		if (curso != GuiConsultarProyectos.getVerTodos() && grupo != GuiConsultarProyectos.getVerTodos()) {
			query = consultaPorCursoyGrupo(curso, grupo);
		}
		
		if (curso == GuiConsultarProyectos.getVerTodos() && grupo != GuiConsultarProyectos.getVerTodos()) {
			query = consultaPorGrupo(grupo);
		}
		
		if (curso != GuiConsultarProyectos.getVerTodos() && grupo == GuiConsultarProyectos.getVerTodos()) {
			query = consultaPorCurso(curso);
		}
		
		if (curso == GuiConsultarProyectos.getVerTodos() && grupo == GuiConsultarProyectos.getVerTodos()) {
			query = consultaTodosLosProyectos();
		}
				
		return query;
	}

	private String consultarFiltroCiclo(String valorConsignadoUsuario, String curso, String grupo) {
		String query = "";
		
		if (curso != GuiConsultarProyectos.getVerTodos()) {
			query = consultaPorCicloProyectoyCurso(valorConsignadoUsuario, curso);
			
			if (grupo != GuiConsultarProyectos.getVerTodos()) {
				query = consultaPorCicloProyectoyCursoyGrupo(valorConsignadoUsuario, curso, grupo);
			}
		}
		
		if (curso == GuiConsultarProyectos.getVerTodos() && grupo != GuiConsultarProyectos.getVerTodos()) {
			query = consultaPorCicloProyectoyGrupo(valorConsignadoUsuario, grupo);
		}
		
		if (curso == GuiConsultarProyectos.getVerTodos() && grupo == GuiConsultarProyectos.getVerTodos()) {
			query = consultaPorCicloProyecto(valorConsignadoUsuario);
		}
				
		return query;
	}

	private String consultaTodosLosProyectos() {
		String query;
		query = "SELECT * FROM " + TableContracts.ProyectosContracts.TABLA
				+ " ORDER BY " + TableContracts.ProyectosContracts.NOMBRE_RELATIVO;;
		return query;
	}

	private String consultaPorIdentificadorProyecto(String valorConsignadoUsuario) {
		return "SELECT * FROM " + TableContracts.ProyectosContracts.TABLA + " WHERE " 
				+ TableContracts.ProyectosContracts.IDENTIFICADOR + " = " + valorConsignadoUsuario;
	}

	private String consultaPorNombreProyecto(String valorConsignadoUsuario) {
		return "SELECT * FROM " + TableContracts.ProyectosContracts.TABLA + " WHERE " 
				+ TableContracts.ProyectosContracts.NOMBRE + " LIKE '%" + valorConsignadoUsuario + "%' ORDER BY " 
				+ TableContracts.ProyectosContracts.NOMBRE;
	}

	private String consultaPorAnyoProyecto(String valorConsignadoUsuario) {
		return "SELECT * FROM " + TableContracts.ProyectosContracts.TABLA + " WHERE " 
				+ TableContracts.ProyectosContracts.ANYO  + " = " + valorConsignadoUsuario;
	}

	private String consultaPorCicloProyecto(String valorConsignadoUsuario) {
		return "SELECT * FROM " + TableContracts.ProyectosContracts.TABLA + " WHERE " 
				+ TableContracts.ProyectosContracts.CICLO  + " = " + valorConsignadoUsuario
				+ " ORDER BY " + TableContracts.ProyectosContracts.NOMBRE;
	}
	
	private String consultaPorCicloProyectoyCurso(String valorConsignadoUsuario, String curso) {
		return "SELECT * FROM " + TableContracts.ProyectosContracts.TABLA + " WHERE " 
				+ TableContracts.ProyectosContracts.CICLO  + " = " + valorConsignadoUsuario 
				+ " AND " + TableContracts.ProyectosContracts.CURSO + " = " + "'" + curso + "'" 
				+ " ORDER BY " + TableContracts.ProyectosContracts.NOMBRE;
	}
	
	private String consultaPorCicloProyectoyGrupo(String valorConsignadoUsuario, String grupo) {
		return "SELECT * FROM " + TableContracts.ProyectosContracts.TABLA + " WHERE " 
				+ TableContracts.ProyectosContracts.CICLO  + " = " + valorConsignadoUsuario 
				+ " AND " + TableContracts.ProyectosContracts.GRUPO + " = " + "'" + grupo + "'" 
				+ " ORDER BY " + TableContracts.ProyectosContracts.NOMBRE;
	}
	
	private String consultaPorCicloProyectoyCursoyGrupo(String valorConsignadoUsuario, String curso, String grupo) {
		return "SELECT * FROM " + TableContracts.ProyectosContracts.TABLA + " WHERE " 
				+ TableContracts.ProyectosContracts.CICLO  + " = " + valorConsignadoUsuario 
				+ " AND (" + TableContracts.ProyectosContracts.CURSO + " = " +  "'" +  curso + "'"
				+ " AND " + TableContracts.ProyectosContracts.GRUPO + " = " +  "'" + grupo +  "'"
				+ ") ORDER BY " + TableContracts.ProyectosContracts.NOMBRE;
	}
	
	private String consultaPorCursoyGrupo(String curso, String grupo) {
		return "SELECT * FROM " + TableContracts.ProyectosContracts.TABLA + " WHERE " 
				+ TableContracts.ProyectosContracts.CURSO + " = " +  "'" +  curso + "'"
				+ " AND " + TableContracts.ProyectosContracts.GRUPO + " = " +  "'" + grupo +  "'"
				+ " ORDER BY " + TableContracts.ProyectosContracts.NOMBRE;
	}
	
	private String consultaPorCurso(String curso) {
		return "SELECT * FROM " + TableContracts.ProyectosContracts.TABLA + " WHERE " 
				+ TableContracts.ProyectosContracts.CURSO + " = " +  "'" +  curso + "'"
				+ " ORDER BY " + TableContracts.ProyectosContracts.NOMBRE;
	}
	
	private String consultaPorGrupo(String grupo) {
		return "SELECT * FROM " + TableContracts.ProyectosContracts.TABLA + " WHERE " 
				+ TableContracts.ProyectosContracts.GRUPO + " = " +  "'" + grupo +  "'"
				+ " ORDER BY " + TableContracts.ProyectosContracts.NOMBRE;
	}

	private String consultaPorNombreAlumno(String valorConsignadoUsuario) {
		return "SELECT * FROM " + TableContracts.ProyectosContracts.TABLA + "," + TableContracts.AlumnosContracts.TABLA 
				+ "," + TableContracts.RealizanContracts.TABLA + " WHERE " + TableContracts.RealizanContracts.ALUMNO 
				+ " = " + TableContracts.AlumnosContracts.IDENTIFICADOR + " AND " + TableContracts.RealizanContracts.PROYECTO 
				+ " = " + TableContracts.ProyectosContracts.IDENTIFICADOR + " AND " + TableContracts.AlumnosContracts.NOMBRE 
				+ " LIKE '%" + valorConsignadoUsuario + "%' GROUP BY " + TableContracts.ProyectosContracts.NOMBRE;
	}

	private String consultaPorApellidoAlumno(String valorConsignadoUsuario) {
		return "SELECT * FROM " + TableContracts.ProyectosContracts.TABLA + "," + TableContracts.AlumnosContracts.TABLA 
				+ "," + TableContracts.RealizanContracts.TABLA + " WHERE " + TableContracts.RealizanContracts.ALUMNO 
				+ " = " + TableContracts.AlumnosContracts.IDENTIFICADOR + " AND " + TableContracts.RealizanContracts.PROYECTO 
				+ " = " + TableContracts.ProyectosContracts.IDENTIFICADOR + " AND " + TableContracts.AlumnosContracts.APELLIDO1 
				+ " LIKE '%" + valorConsignadoUsuario + "%' GROUP BY " + TableContracts.ProyectosContracts.NOMBRE;
	}

	private String consultaPorExpedienteAlumno(String valorConsignadoUsuario) {
		return "SELECT * FROM " + TableContracts.ProyectosContracts.TABLA + "," + TableContracts.AlumnosContracts.TABLA 
				+ "," + TableContracts.RealizanContracts.TABLA + " WHERE " + TableContracts.RealizanContracts.ALUMNO 
				+ " = " + TableContracts.AlumnosContracts.IDENTIFICADOR + " AND " + TableContracts.RealizanContracts.PROYECTO 
				+ " = " + TableContracts.ProyectosContracts.IDENTIFICADOR + " AND " + TableContracts.AlumnosContracts.EXPEDIENTE 
				+ " = " + valorConsignadoUsuario;
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

