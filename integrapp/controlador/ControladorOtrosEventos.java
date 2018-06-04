package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import persistencia.PersistenciaUsuarios;
import vistas.*;

public class ControladorOtrosEventos implements ActionListener {
	private GuiPrincipal mainGui;
	private GuiLogin ventanaLogin;
	private GuiPanelPrincipal panelPrincipal;
	private GuiModalAcercaDe panelAcercaDe;
	
	public ControladorOtrosEventos(GuiPrincipal mainGui, GuiLogin ventanaLogin, GuiPanelPrincipal panelPrincipal, 
			GuiModalAcercaDe panelAcercaDe) {
		this.mainGui = mainGui;
		this.ventanaLogin = ventanaLogin;
		this.panelPrincipal = panelPrincipal;
		this.panelAcercaDe = panelAcercaDe;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Detecta eventos en el Botón Login del Panel Principal
		if (e.getSource().equals(panelPrincipal.getBtnLogin())) {
			ventanaLogin.hacerVisible();
				
		//Detecta el evento de intento de identificación de usuario (Login)
		} else if (e.getSource().equals(ventanaLogin.getBtnAceptar())) {
					
			//Si la identificación es satisfactoria ejecuta el método esUsuarioAdministrador
			if (new PersistenciaUsuarios().comprobarPassword(ventanaLogin.getDatos()) == 1) {
				ventanaLogin.reciclar();
				ventanaLogin.dispose();
				mainGui.esUsuarioAdministrador();
				panelPrincipal.cambiarAdministrador();
			}
				
			//Detecta el evento de cancelar la operación de Login
			} else if (e.getSource().equals(ventanaLogin.getBtnCancelar())) {
				ventanaLogin.reciclar();
				ventanaLogin.dispose();
			
			//Detecta el evento de pulsar cerrar en la ventana Acercade
			} else if (e.getSource().equals(panelAcercaDe.getBtnCancelar())) {
				panelAcercaDe.dispose();
			
			//Detecta el evento de pulsar en el menu AcercaDe
			} else if (e.getSource().equals(mainGui.getSubmenuAcercaDe())) {
				panelAcercaDe.hacerVisible();
			}
						
			//Detecta el evento de hacer click en el icono volver al inicio
			if (e.getActionCommand().equals("Volver")) {
				mainGui.setPanel(panelPrincipal);
		}
	}
}
