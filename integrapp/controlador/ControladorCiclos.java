package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import persistencia.PersistenciaCiclos;
import vistas.GuiAltaCiclo;
import vistas.GuiPanelPrincipal;
import vistas.GuiPrincipal;

public class ControladorCiclos implements ActionListener {
	private GuiPrincipal mainGui;
	private GuiAltaCiclo panelAltaCiclo;
	private GuiPanelPrincipal panelPrincipal;
	
	public ControladorCiclos(GuiPrincipal mainGui, GuiAltaCiclo panelAltaCiclo, GuiPanelPrincipal panelPrincipal) {
		
		this.mainGui = mainGui;
		this.panelAltaCiclo = panelAltaCiclo;
		this.panelPrincipal = panelPrincipal;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//Detecta el evento de hacer click en el Submenu Alta Ciclos
		if (e.getActionCommand().equals("panelAltaCiclos")) {
			mainGui.setPanel(panelAltaCiclo);
					
		//Detecta el evento de hacer click para intentar dar de alta un Ciclo Formativo
		} else if (e.getActionCommand().equals("agregarCiclo")) {
			new PersistenciaCiclos().agregarCicloBd(panelAltaCiclo.getDatos());
		}
	}
}
