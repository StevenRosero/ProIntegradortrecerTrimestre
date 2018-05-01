package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import persistencia.PersistenciaCiclos;
import vistas.GuiAltaCiclo;
import vistas.GuiBajaCiclo;
import vistas.GuiModalModificarCiclo;
import vistas.GuiModificarCiclo;
import vistas.GuiPanelPrincipal;
import vistas.GuiPrincipal;

public class ControladorCiclos implements ActionListener {
	private GuiPrincipal mainGui;
	private GuiAltaCiclo panelAltaCiclo;
	private GuiPanelPrincipal panelPrincipal;
	private GuiBajaCiclo panelBajaCiclo;
	private GuiModalModificarCiclo modalModificarCiclo;
	private GuiModificarCiclo panelModificarCiclo;
	
	public ControladorCiclos(GuiPrincipal mainGui, GuiAltaCiclo panelAltaCiclo, 
			GuiPanelPrincipal panelPrincipal, GuiBajaCiclo panelBajaCiclo, 
			GuiModalModificarCiclo modalModificarCiclo, GuiModificarCiclo panelModificarCiclo) {
		
		this.mainGui = mainGui;
		this.panelAltaCiclo = panelAltaCiclo;
		this.panelPrincipal = panelPrincipal;
		this.panelBajaCiclo = panelBajaCiclo;
		this.modalModificarCiclo =  modalModificarCiclo;
		this.panelModificarCiclo = panelModificarCiclo;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//Detecta el evento de hacer click en el Submenu Alta Ciclos
		if (e.getActionCommand().equals("panelAltaCiclos")) {
			mainGui.setPanel(panelAltaCiclo);
					
		//Detecta el evento de hacer click para confirmar el alta de un Ciclo Formativo
		} else if (e.getActionCommand().equals("agregarCiclo")) {
			new PersistenciaCiclos().agregarCicloBd(panelAltaCiclo.getDatos());
		
		//Detecta el evento de hacer click en el Submenu Baja Ciclos
		} else if (e.getActionCommand().equals("panelBajaCiclos")) {
			panelBajaCiclo.mostrar(new PersistenciaCiclos().listadoCiclosBd());
		
		//Detecta el evento de hacer click para confirmar la baja de un Ciclo Formativo
		} else if (e.getActionCommand().equals("borrarCiclo")) {
			new PersistenciaCiclos().eliminarCicloBd(panelBajaCiclo.getDatos());
			panelBajaCiclo.mostrar(new PersistenciaCiclos().listadoCiclosBd());
		
		//Detecta el evento de hacer click en el Submenu Modificar Ciclo Formativo
		} else if (e.getActionCommand().equals("submenuModificarCiclos")) {
			modalModificarCiclo.mostrar(new PersistenciaCiclos().listadoCiclosBd());
		
		//Detecta el evento de hacer click para confirmar la selección del Ciclo Formativo a modificar
		} else if (e.getActionCommand().equals("botonModificarCiclo")) {
			mainGui.setPanel(panelModificarCiclo);
			panelModificarCiclo.mostrarCiclo(modalModificarCiclo.getDatos());
			modalModificarCiclo.setVisible(false);
			
		} else if(e.getActionCommand().equals("modificarCicloDef")) {
			new PersistenciaCiclos().modificarCicloBd(panelModificarCiclo.getDatos());
			mainGui.setPanel(panelPrincipal);
		}
	}
}
