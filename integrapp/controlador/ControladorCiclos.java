package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import persistencia.PersistenciaCiclos;
import vistas.*;

public class ControladorCiclos implements ActionListener {
	private GuiPrincipal mainGui;
	private GuiPanelPrincipal panelPrincipal;
	private GuiAltaCiclo panelAltaCiclo;
	private GuiBajaCiclo panelBajaCiclo;
	private GuiModalModificarCiclo modalModificarCiclo;
	private GuiModificarCiclo panelModificarCiclo;
	
	public ControladorCiclos(GuiPrincipal mainGui, GuiAltaCiclo panelAltaCiclo, 
			GuiPanelPrincipal panelPrincipal, GuiBajaCiclo panelBajaCiclo, 
			GuiModalModificarCiclo modalModificarCiclo, GuiModificarCiclo panelModificarCiclo) {
		
		this.mainGui = mainGui;
		this.panelPrincipal = panelPrincipal;
		this.panelAltaCiclo = panelAltaCiclo;
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
			panelAltaCiclo.reciclar();
			mainGui.setPanel(panelPrincipal);
		
		
		//Detecta el evento de hacer click en el Submenu Baja Ciclos
		} else if (e.getActionCommand().equals("panelBajaCiclos")) {
			panelBajaCiclo.mostrar(new PersistenciaCiclos().listadoCiclosBd());
		
		
		//Detecta el evento de hacer click para confirmar la baja de un Ciclo Formativo
		} else if (e.getActionCommand().equals("borrarCiclo")) {
			
			try {
				new PersistenciaCiclos().eliminarCicloBd(panelBajaCiclo.getDatos());
			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(panelBajaCiclo, "Debe Elegir una opci�n de la lista para continuar");
			}
			panelBajaCiclo.mostrar(new PersistenciaCiclos().listadoCiclosBd());
		
		
		//Detecta el evento de hacer click en el Submenu Modificar Ciclo Formativo
		} else if (e.getActionCommand().equals("submenuModificarCiclos")) {
			modalModificarCiclo.mostrar(new PersistenciaCiclos().listadoCiclosBd());
		
		
		//Detecta el evento de hacer click para confirmar la selecci�n del Ciclo Formativo a modificar
		} else if (e.getActionCommand().equals("botonModificarCiclo")) {
			
			try {
				panelModificarCiclo.mostrarCiclo(modalModificarCiclo.getDatos());
				mainGui.setPanel(panelModificarCiclo);
				modalModificarCiclo.setVisible(false);
			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(modalModificarCiclo, "Debe Elegir una opci�n de la lista para continuar");
			}
			
		
		//Detecta el evento de confirmar la modificaci�n de los datos de un ciclo formativo.
		} else if(e.getActionCommand().equals("modificarCicloDef")) {
			new PersistenciaCiclos().modificarCicloBd(panelModificarCiclo.getDatos());
			mainGui.setPanel(panelPrincipal);
		
		
		//Detecta el evento de hacer click para cancelar el proceso de borrar un Ciclo
		} else if (e.getActionCommand().equals("cancelarCiclo")) {
			panelBajaCiclo.dispose();
		}
	}
}
