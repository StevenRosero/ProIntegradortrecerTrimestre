package controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.AlumnoPojo;
import modelo.CicloFormativoPojo;
import modelo.ProyectoPojo;
import vistas.GuiAltaAlumno;


public class ConexionBaseDatos {
	private static final String url = "jdbc:sqlite:DbIntegraApp.db";
	private Connection conexion;
	private int estado;
	
	public ConexionBaseDatos() {
		
	}
	
	public void conectar() {
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection(url);
		
		} catch (Exception e) {
			
			estado = JOptionPane.showConfirmDialog(null,"Error", "No se ha podido conectar a la base de datos."
					+ " ¿Desea Continuar?",JOptionPane.YES_NO_OPTION);
			
			if (estado == JOptionPane.NO_OPTION) {
				System.exit(0);
			}
		}
	}
	
	public void desconectar() {
		try {
			conexion.close();
		} catch (SQLException e) {
			JOptionPane.showInternalMessageDialog(null, "No se ha podido desconectar correctamente de la base de datos");
		}
	}
	
	public void agregarProyecto(ProyectoPojo proyecto) {
		
		try {  
			//guarda imagen en tabla sql
			PreparedStatement ps;
			File archivo = new File(proyecto.getImagen());
			FileInputStream arch2 = new FileInputStream(archivo);
			
			//Ejecuta la sentencia para agregar proyecto en la base de datos
			ps=conexion.prepareStatement(("INSERT INTO PROYECTOS VALUES(SEC_ID_P.NEXTVAL,?,?,?,?,?,?,?)"));
			ps.setString(1, proyecto.getNombre());
			ps.setString(2, proyecto.getUrl());
			ps.setInt(3, proyecto.getCurso());
			ps.setString(4, proyecto.getGrupo());
			ps.setInt(5, proyecto.getAnyo());
			ps.setInt(6, proyecto.getCiclo().getIdentificador());
			ps.setBinaryStream(7, arch2);
			ps.executeUpdate();
			
			//Ejecuta la sentencia para agregar los alumnos del proyecto en la tabla Realizan
			int id = 0;
			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery("SELECT MAX(ID_P) FROM PROYECTOS");
			
			while (rs.next()) {
				id = rs.getInt(1);
			}
		
			//Itera la lista de Alumnos y los va agregando a la tabla realizan junto al identificador del proyecto
			for (int i = 0; i < proyecto.getListaAlumnos().size(); i++) {
				ps=conexion.prepareStatement(("INSERT INTO REALIZAN VALUES(?,?)"));
				ps.setInt(1, proyecto.getListaAlumnos().get(i).getIdAlumno());
				ps.setInt(2, id);
				ps.executeQuery();
			}
		
			JOptionPane.showMessageDialog(null, "La Operación se ha realizado con éxito");
			  
			} catch(Exception e) { 
			
				JOptionPane.showMessageDialog(null, e.getMessage()); 
			}
	}
	
	public void agregarAlumno(AlumnoPojo alumno) {
		try {  
			//Prepara la sentencia SQL para insertar Alumno
			PreparedStatement ps;
			ps=conexion.prepareStatement(("INSERT INTO ALUMNOS(EXPEDIENTE, NOMBRE, APELLIDO1, APELLIDO2) VALUES(?,?,?,?)"));
			ps.setInt(1, alumno.getExpediente());
			ps.setString(2, alumno.getNombre());
			ps.setString(3, alumno.getApellido1());
			ps.setString(4, alumno.getApellido2());
			
			//Ejecuta la sentencia SQL de inserción
			ps.executeUpdate(); 
			JOptionPane.showMessageDialog(null, "La Operación se ha realizado con éxito");
			
			} catch(Exception e) { 
				JOptionPane.showMessageDialog(null, "No se ha podido realizar la operación" + e.getMessage()); 
			}
	}
	
	public ArrayList<AlumnoPojo> listaAlumnosBaseDatos() {
		ArrayList<AlumnoPojo> listaAlumnosBaseDatos = new ArrayList<AlumnoPojo>();
		AlumnoPojo alumno = null;
		
		
		try {  
			//Prepara la sentencia SQL para insertar Alumno
			Statement stmt = conexion.createStatement();
			
			//Recupera los datos de la base de datos y los almacena en una variable
			ResultSet rs = stmt.executeQuery("SELECT * FROM ALUMNOS ORDER BY ID_A");
			
			while (rs.next()) {
				alumno = new AlumnoPojo(rs.getInt(1),rs.getInt(2),rs.getString(3),
						rs.getString(4), rs.getString(5));
				
				listaAlumnosBaseDatos.add(alumno);	
			}
			
			}catch(Exception e){ 
			JOptionPane.showMessageDialog(null, "No se ha podido realizar la operación de carga de Alumnos"); 
			}
			
		return listaAlumnosBaseDatos;
		
	}
	
	public ArrayList<CicloFormativoPojo> listaCiclosFormativos() {
		ArrayList<CicloFormativoPojo> listaCiclosFormativos = new ArrayList<CicloFormativoPojo>();
		CicloFormativoPojo ciclo = null;
		
		
		try {  
			
			//Prepara la sentencia SQL para insertar Alumno
			Statement stmt = conexion.createStatement();
			
			//Recupera los datos de la base de datos y los almacena en una variable
			ResultSet rs = stmt.executeQuery("SELECT * FROM CICLOS ORDER BY ID_C");
			
			while (rs.next()) {
				ciclo = new CicloFormativoPojo(rs.getInt(1), rs.getString(2), rs.getString(3));
				
				listaCiclosFormativos.add(ciclo);	
			}
			
			}catch(Exception e){ 
			JOptionPane.showMessageDialog(null, "No se ha podido realizar la operación de carga de Ciclos"); 
			}
			
		return listaCiclosFormativos;
		
	}
	
	public void eliminarAlumnoBaseDatos(AlumnoPojo alumnoEliminar) {
		try {  
			//Prepara la sentencia SQL para insertar Alumno
			PreparedStatement ps;
			ps=conexion.prepareStatement(("DELETE FROM ALUMNOS WHERE ID_A = ?"));
			ps.setInt(1, alumnoEliminar.getIdAlumno());
			
			//Ejecuta la sentencia SQL de inserción
			ps.executeUpdate();

			JOptionPane.showMessageDialog(null, "La Operación se ha realizado con éxito");
			
			}catch(Exception e){ 
			JOptionPane.showMessageDialog(null, "No se ha podido realizar la operación"); 
			}
	}
	
	public void altaCicloBaseDatos(CicloFormativoPojo cicloAlta) {
		
		try {  
			//Prepara la sentencia SQL para insertar Alumno
			PreparedStatement ps;
			ps=conexion.prepareStatement(("INSERT INTO CICLOS(NOMBRE, DESCRIPCION) VALUES(?,?)"));
			ps.setString(1, cicloAlta.getNombre());
			ps.setString(2, cicloAlta.getDescripcion());
			
			//Ejecuta la sentencia SQL de inserción
			ps.executeUpdate();

			JOptionPane.showMessageDialog(null, "La Operación se ha realizado con éxito");
			
			}catch(Exception e){ 
			JOptionPane.showMessageDialog(null, "No se ha podido realizar la operación"); 
			}
	}
	
	public void modificarAlumno(AlumnoPojo alumno) {
		try {  
			//carga el driver de oracle  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			  
			//crea la conexión
			Connection con= DriverManager.getConnection(  
			"jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","111082");  
			  
			//Prepara la sentencia SQL para insertar Alumno
			PreparedStatement ps;
			ps=con.prepareStatement(("UPDATE ALUMNOS SET EXPEDIENTE = ?, NOMBRE = ?, APELLIDO1 = ?, APELLIDO2 = ? WHERE ID_A = ?"));
			ps.setInt(1, alumno.getExpediente());
			ps.setString(2, alumno.getNombre());
			ps.setString(3, alumno.getApellido1());
			ps.setString(4, alumno.getApellido2());
			ps.setInt(5, alumno.getIdAlumno());
			
			//Ejecuta la sentencia SQL de inserción
			ps.executeUpdate();

			//cierra la conexion 
			con.close();  
			JOptionPane.showMessageDialog(null, "La Operación se ha realizado con éxito");
			
			}catch(Exception e){ 
				System.out.println(e.getMessage());
				JOptionPane.showMessageDialog(null, "No se ha podido realizar la operación"); 
			}
	}
}
