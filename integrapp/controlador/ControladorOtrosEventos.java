package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.AdministradorPojo;
import vistas.*;

public class ControladorOtrosEventos implements ActionListener {
	private static final AdministradorPojo admin = new AdministradorPojo("ADMIN", "1234");
	private GuiPrincipal mainGui;
	private GuiLogin ventanaLogin;
	private GuiPanelPrincipal panelPrincipal;
	
	public ControladorOtrosEventos(GuiPrincipal mainGui, GuiLogin ventanaLogin, GuiPanelPrincipal panelPrincipal) {
		this.mainGui = mainGui;
		this.ventanaLogin = ventanaLogin;
		this.panelPrincipal = panelPrincipal;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Detecta eventos en el Botón Login del Panel Principal
		if (e.getSource().equals(panelPrincipal.getBtnLogin())) {
			ventanaLogin.hacerVisible();
				
		//Detecta el evento de intento de identificación de usuario (Login)
		} else if (e.getSource().equals(ventanaLogin.getBtnAceptar())) {
					
			//Si la identificación es satisfactoria ejecuta el método esUsuarioAdministrador
			if (admin.comprobarContrasenya(ventanaLogin.getDatos()) == 1) {
				ventanaLogin.reciclar();
				ventanaLogin.dispose();
				mainGui.esUsuarioAdministrador();
				panelPrincipal.cambiarAdministrador();
			}
				
			//Detecta el evento de cancelar la operación de Login
			} else if (e.getSource().equals(ventanaLogin.getBtnCancelar())) {
				ventanaLogin.reciclar();
				ventanaLogin.dispose();
			}
						
			//Detecta el evento de hacer click en el icono volver al inicio
			if (e.getActionCommand().equals("Volver")) {
				mainGui.setPanel(panelPrincipal);
		}
	}
}
