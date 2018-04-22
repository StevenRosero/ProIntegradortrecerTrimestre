package ejecutables;

import java.awt.EventQueue;

import controlador.ControladorIntegraApp;
import vistas.GuiLogin;
import vistas.GuiPanelPrincipal;
import vistas.GuiPrincipal;

public class IntegraAppEjecutable {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try {
					//Declara e inicializa los elementos que conforman el UI y el controlador.
					GuiPrincipal mainGui = new GuiPrincipal();
					GuiPanelPrincipal panelInicio = new GuiPanelPrincipal();
					GuiLogin ventanaLogin = new GuiLogin();
					ControladorIntegraApp control = new ControladorIntegraApp(panelInicio, mainGui, ventanaLogin);
					
					//Asigna el controladro a cada una de las ventanas del programa
					mainGui.setControlador(control);
					panelInicio.setControlador(control);
					ventanaLogin.setControlador(control);
					
					//Hace Visible el UI que da salida visual al hilo principal de la aplicación
					mainGui.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}
}
