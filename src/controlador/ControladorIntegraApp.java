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
		
		//Detecta eventos en el Bot�n Login del Panel Principal
		if (e.getSource().equals(mainGui.getBtnLogin())) {
			ventanaLogin.hacerVisible();
		
		//Detecta el evento de intento de identificaci�n de usuario (Login)
		} else if (e.getSource().equals(ventanaLogin.getBtnAceptar())) {
			
			//Si la identificaci�n es satisfactoria ejecuta el m�todo esUsuarioAdministrador
			if (admin.comprobarContrasenya(ventanaLogin.getDatos()) == 1) {
				ventanaLogin.dispose();
				mainGui.esUsuarioAdministrador();
			}
		
		//Detecta el evento de cancelar la operaci�n de Login
		} else if (e.getSource().equals(ventanaLogin.getBtnCancelar())) {
			ventanaLogin.dispose();
		}
	}
}
