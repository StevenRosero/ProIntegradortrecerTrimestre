package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.AdministradorPojo;
import vistas.GuiLogin;
import vistas.GuiPanelPrincipal;
import vistas.GuiPrincipal;
	
public class ControladorIntegraApp implements ActionListener {
	private AdministradorPojo admin;
	private GuiPrincipal mainGui;
	private GuiLogin ventanaLogin;
	
	public ControladorIntegraApp(GuiPanelPrincipal panelPrincipal, GuiPrincipal mainGui, GuiLogin ventanaLogin) {
		this.mainGui = mainGui;
		this.ventanaLogin = ventanaLogin;
		admin = new AdministradorPojo("ADMIN", "1234");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Detecta eventos en el Botón Login del Panel Principal
		if (e.getSource().equals(mainGui.getBtnLogin())) {
			ventanaLogin.hacerVisible();
		
		//Detecta el evento de intento de identificación de usuario (Login)
		} else if (e.getSource().equals(ventanaLogin.getBtnAceptar())) {
			
			//Si la identificación es satisfactoria ejecuta el método esUsuarioAdministrador
			if (admin.comprobarContrasenya(ventanaLogin.getDatos()) == 1) {
				ventanaLogin.dispose();
				mainGui.esUsuarioAdministrador();
			}
		
		//Detecta el evento de cancelar la operación de Login
		} else if (e.getSource().equals(ventanaLogin.getBtnCancelar())) {
			ventanaLogin.dispose();
		}
	}
}
