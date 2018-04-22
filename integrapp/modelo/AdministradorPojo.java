package modelo;

import javax.swing.JOptionPane;

public class AdministradorPojo {
	private String usuario;
	private String contrasenya;
	
	public AdministradorPojo(String usuario, String contrasenya) {
		this.usuario = usuario;
		this.contrasenya = contrasenya;
	}
	
	//Método que comprueba si la contraseña es correcta
	public int comprobarContrasenya(AdministradorPojo usuario) {
		
		//Si la contraseña es correcta retorna un dato de tipo entero (1) al controlador
		if (usuario.getUsuario().equals(this.usuario)
		&& usuario.getContrasenya().equals(this.contrasenya)) {
		
			JOptionPane.showMessageDialog(null, "Los Datos Introducidos son correctos");
			return 1;
		
		//Si la contraseña es incorrecta retorna un dato de tipo entero (0) al controlador
		} else {
			JOptionPane.showMessageDialog(null, "Los Datos Introducidos son Incorrectos");
			return 0;
		}
	}

	public String getUsuario() {
		return usuario;
	}

	public String getContrasenya() {
		return contrasenya;
	}
}
