package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JOptionPane;
import vistas.*;
import persistencia.*;

public class ControladorProyectos implements ActionListener {
	private GuiPrincipal mainGui;
	private GuiPanelPrincipal panelPrincipal;
	private GuiAltaProyecto panelAltaProyecto;
	private GuiConsultarProyectos panelConsultarProyectos;
	private GuiModalDetalleProyecto panelDetalleProyecto;
	private GuiBajaProyecto panelBajaProyecto;
	private GuiModalModificarProyecto modalModificarProyecto;
	private GuiModificarProyecto panelModificarProyecto;
	
	public ControladorProyectos(GuiPrincipal mainGui, GuiPanelPrincipal panelPrincipal,
			GuiAltaProyecto panelAltaProyecto, GuiConsultarProyectos panelConsultarProyectos,
			GuiBajaProyecto panelBajaProyecto, GuiModalModificarProyecto modalModificarProyecto,
			GuiModificarProyecto panelModificarProyecto) {

		this.mainGui = mainGui;
		this.panelPrincipal = panelPrincipal;
		this.panelAltaProyecto = panelAltaProyecto;
		this.panelConsultarProyectos = panelConsultarProyectos;
		this.panelBajaProyecto = panelBajaProyecto;
		this.modalModificarProyecto = modalModificarProyecto;
		this.panelModificarProyecto = panelModificarProyecto;
		panelDetalleProyecto = new GuiModalDetalleProyecto();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Detecta el evento de hacer click en el Submenu Consultar Proyectos
		if(e.getActionCommand().equals("consultarProyectos")) {
				mainGui.setPanel(panelConsultarProyectos);
				panelConsultarProyectos.cargarCiclos(new PersistenciaCiclos().listadoCiclosBd());
		
		} else if (e.getActionCommand().equals("btnFiltro")) {
				try {
					panelConsultarProyectos.cargarModeloProyectos(new PersistenciaProyectos().consultaProyectos(panelConsultarProyectos.getDatos()));
					
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
		//Detecta el evento de hacer click en el Submenu Alta Proyectos
		} else if (e.getSource().equals(mainGui.getSubmenuAltaProyecto())) {
			mainGui.setPanel(panelAltaProyecto);
			panelAltaProyecto.reciclar();
			panelAltaProyecto.cargarModelo(new PersistenciaAlumnos().listaAlumnosBd());
			panelAltaProyecto.cargarCiclos(new PersistenciaCiclos().listadoCiclosBd());
					
		//Detecta el evento de hacer click para agregar un proyecto en la base de datos
		} else if (e.getActionCommand().equals("agregarProyecto")) {
			try {
				new PersistenciaProyectos().agregarProyecto(panelAltaProyecto.getDatos());
				panelAltaProyecto.reciclar();
				mainGui.setPanel(panelPrincipal);
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(panelAltaProyecto, "Debe introducir números en el formato adecuado");
			}
		
		//Detecta el evento de hacer click para ver los detalles de un proyecto seleccionado
		} else if (e.getActionCommand().equals("verDetalles")) {
			try {
				panelDetalleProyecto.mostrarCiclo(new PersistenciaCiclos().cicloSeleccionado(panelConsultarProyectos.proyectoSeleccionado().getCiclo().getIdentificador()));
				panelDetalleProyecto.mostrarListaAlumnos(new PersistenciaAlumnos().consultaAlumnosBd(panelConsultarProyectos.proyectoSeleccionado().getIdProyecto()));
				panelDetalleProyecto.mostrar(panelConsultarProyectos.proyectoSeleccionado());
	
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		
		//Detecta el evento de hacer click en el Submenu Baja Proyectos
		} else if (e.getActionCommand().equals("submenuBajaProyecto")) {
			mainGui.setPanel(panelPrincipal);
			panelBajaProyecto.mostrar(new PersistenciaProyectos().listadoProyectosDb());
		
		//Detecta el evento de hacer click para confirmar la eliminación de un Proyecto
		} else if (e.getActionCommand().equals("eliminarProyecto")) {
			new PersistenciaProyectos().eliminarProyecto(panelBajaProyecto.getDatos());
			panelBajaProyecto.mostrar(new PersistenciaProyectos().listadoProyectosDb());
		
		//Detecta el evento de hacer click en el Submenu Modificar Proyectos
		} else if (e.getActionCommand().equals("submenuModificarProyectos")) {
			modalModificarProyecto.mostrar(new PersistenciaProyectos().listadoProyectosDb());
		
		//Detecta el evento de hacer click para confirmar la selección del Proyecto a modificar
		} else if (e.getActionCommand().equals("modalModificarProyecto")) {
			mainGui.setPanel(panelModificarProyecto);
			panelModificarProyecto.cargarCiclos(new PersistenciaCiclos().listadoCiclosBd());
			panelModificarProyecto.mostrarProyecto(modalModificarProyecto.getDatos());
			panelModificarProyecto.alumnosQueNoParticipan(new PersistenciaAlumnos().listaAlumnosBd(),
					new PersistenciaAlumnos().consultaAlumnosDeProyecto(panelModificarProyecto.getIdProyecto()));
			
			panelModificarProyecto.cargarModelo(new PersistenciaAlumnos().consultaAlumnosDeProyecto(panelModificarProyecto.getIdProyecto()));
			modalModificarProyecto.setVisible(false);
		
		//Detecta el evento de confirmar definitivamente la información que modifica el proyecto.
		} else if (e.getActionCommand().equals("modificarProyecto")) {
			try {
				new PersistenciaProyectos().modificarProyecto(panelModificarProyecto.getDatos());
			} catch (NumberFormatException | FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
