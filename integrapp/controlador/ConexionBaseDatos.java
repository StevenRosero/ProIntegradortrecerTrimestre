package controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.AlumnoPojo;
import vistas.GuiAltaAlumno;


public class ConexionBaseDatos {
	private static final String usuario = "SYSTEM";
	private static final String contrasenya = "111082";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	
	public ConexionBaseDatos() {
		
	}
	
	public void agregarProyecto() {
		try {  
			//carga el driver de oracle  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			  
			//crea la conexión
			Connection con= DriverManager.getConnection(url,usuario,contrasenya);  
			  
			//prepara la sentencia sql 
			Statement stmt=con.createStatement();  
			  
			//ejecuta la sentencia sql y la almacena en ResultSet
			ResultSet rs=stmt.executeQuery("INSERT INTO ALUMNOS VALUES"
					+ ""
					+ "");  
			while(rs.next()) {
				//prod = new ProductoPojo(rs.getString(2), rs.getInt(1));
				//lista.add(prod);
			}
			
			//guarda imagen en tabla sql
			PreparedStatement ps;
			//File archivo = new File(fichero);
			//FileInputStream arch2 = new FileInputStream(archivo);
			ps=con.prepareStatement(("INSERT INTO ALUMNOS VALUES(?,?,?,?,?,?,?,?)"));
			ps.setString(1, "pepe");
			ps.setInt(2, 3);
			//ps.setBinaryStream(3, arch2);
			ps.executeUpdate();
			
			rs=stmt.executeQuery("SELECT IMAGENES FROM ALUMNOS");
			while(rs.next()) {
				OutputStream salida = new FileOutputStream("C://destino//2new.jpg");
				salida.write(rs.getBytes(1));
				salida.close();
			}
			
			
			//cierra la conexion 
			con.close();  
			  
			}catch(Exception e){ 
			JOptionPane.showMessageDialog(null, e.getMessage()); 
			}
	}
	
	private void convertirImagen() {
		
	}
	
	public void agregarAlumno(AlumnoPojo alumno) {
		try {  
			//carga el driver de oracle  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			  
			//crea la conexión
			Connection con= DriverManager.getConnection(  
			"jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","111082");  
			  
			//Prepara la sentencia SQL para insertar Alumno
			PreparedStatement ps;
			ps=con.prepareStatement(("INSERT INTO ALUMNOS VALUES(SEC_ID_A.NEXTVAL,?,?,?,?)"));
			ps.setInt(1, alumno.getExpediente());
			ps.setString(2, alumno.getNombre());
			ps.setString(3, alumno.getApellido1());
			ps.setString(4, alumno.getApellido2());
			
			//Ejecuta la sentencia SQL de inserción
			ps.executeUpdate();

			//cierra la conexion 
			con.close();  
			JOptionPane.showMessageDialog(null, "La Operación se ha realizado con éxito");
			
			}catch(Exception e){ 
			JOptionPane.showMessageDialog(null, "No se ha podido realizar la operación"); 
			}
	}
	
	public ArrayList<AlumnoPojo> listaAlumnosBaseDatos() {
		ArrayList<AlumnoPojo> listaAlumnosBaseDatos = new ArrayList<AlumnoPojo>();
		AlumnoPojo alumno = null;
		
		
		try {  
			//carga el driver de oracle  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			  
			//crea la conexión
			Connection con= DriverManager.getConnection(  
			"jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","111082");  
			  
			//Prepara la sentencia SQL para insertar Alumno
			Statement stmt = con.createStatement();
			
			//Recupera los datos de la base de datos y los almacena en una variable
			ResultSet rs = stmt.executeQuery("SELECT * FROM ALUMNOS ORDER BY ID_A");
			
			while (rs.next()) {
				alumno = new AlumnoPojo(rs.getInt(1),rs.getInt(2),rs.getString(3),
						rs.getString(4), rs.getString(5));
				
				listaAlumnosBaseDatos.add(alumno);	
			}

			//cierra la conexion 
			con.close();  
			
			}catch(Exception e){ 
			JOptionPane.showMessageDialog(null, "No se ha podido realizar la operación de carga de Alumnos"); 
			}
			
		return listaAlumnosBaseDatos;
		
	}
	
	public void eliminarAlumnoBaseDatos(AlumnoPojo alumnoEliminar) {
		try {  
			//carga el driver de oracle  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			  
			//crea la conexión
			Connection con= DriverManager.getConnection(  
			"jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","111082");  
			  
			//Prepara la sentencia SQL para insertar Alumno
			PreparedStatement ps;
			ps=con.prepareStatement(("DELETE FROM ALUMNOS WHERE ID_A = ?"));
			ps.setInt(1, alumnoEliminar.getIdAlumno());
			
			//Ejecuta la sentencia SQL de inserción
			ps.executeUpdate();

			//cierra la conexion 
			con.close();  
			JOptionPane.showMessageDialog(null, "La Operación se ha realizado con éxito");
			
			}catch(Exception e){ 
			JOptionPane.showMessageDialog(null, "No se ha podido realizar la operación"); 
			}
	}
}
