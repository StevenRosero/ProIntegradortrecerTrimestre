package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;

import persistencia.PersistenciaAlumnos;
import persistencia.PersistenciaCiclos;
import persistencia.PersistenciaProyectos;
import vistas.GuiAltaProyecto;
import vistas.GuiModalAUX;
import vistas.GuiPanelPrincipal;
import vistas.GuiPrincipal;

public class ControladorProyectos implements ActionListener {
	private GuiPrincipal mainGui;
	private GuiPanelPrincipal panelPrincipal;
	private GuiAltaProyecto panelAltaProyecto;
	private GuiModalAUX modalAUX;
	
	public ControladorProyectos(GuiPrincipal mainGui, GuiPanelPrincipal panelPrincipal,
			GuiAltaProyecto panelAltaProyecto) {

		this.mainGui = mainGui;
		this.panelPrincipal = panelPrincipal;
		this.panelAltaProyecto = panelAltaProyecto;
		modalAUX = new GuiModalAUX();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Detecta el evento de hacer click en el Submenu Consultar Proyectos
		if(e.getActionCommand().equals("CONSULTAR")) {
			try {
				modalAUX.mostrar(new PersistenciaProyectos().listadoProyectosDb());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
		//Detecta el evento de hacer click en el Submenu Alta Proyectos
		} else if (e.getSource().equals(mainGui.getSubmenuAltaProyecto())) {
			mainGui.setPanel(panelAltaProyecto);
			
			panelAltaProyecto.cargarModelo(new PersistenciaAlumnos().listaAlumnosBd());
			panelAltaProyecto.cargarCiclos(new PersistenciaCiclos().listadoCiclosBd());
					
		//Detecta el evento de hacer click para agregar un proyecto en la base de datos
		} else if (e.getActionCommand().equals("agregarProyecto")) {
			try {
				new PersistenciaProyectos().agregarProyecto(panelAltaProyecto.getDatos());
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(panelAltaProyecto, "Debe introducir números en el formato adecuado");
			}
		}
	}
}
