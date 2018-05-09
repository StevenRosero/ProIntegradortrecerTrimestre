package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import modelo.AdministradorPojo;

public class PersistenciaUsuarios {
	private ConexionBBDD conexion;

	public PersistenciaUsuarios() {
		conexion = new ConexionBBDD();
	}
	
	public int comprobarPassword(AdministradorPojo usuario) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = conexion.conectarBd();
			String query = "SELECT * FROM USUARIOS WHERE USUARIO = ? AND PASSWORD = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, usuario.getUsuario());
			ps.setString(2, usuario.getContrasenya());
			rs = ps.executeQuery();
			
			//Si la contraseña es correcta retorna un dato de tipo entero (1) al controlador
			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "Los Datos Introducidos son correctos");
				return 1;
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		} finally {
			
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		JOptionPane.showMessageDialog(null, "Los Datos Introducidos son Incorrectos");
		return 0;
	}
}
