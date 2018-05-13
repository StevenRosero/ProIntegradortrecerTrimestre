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
					panelConsultarProyectos.cargarModeloProyectos(new PersistenciaProyectos().consultaProyectos(panelConsultarProyectos.getFiltro(), panelConsultarProyectos.getDatos()));
					
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(panelAltaProyecto, "Debe introducir números en el formato adecuado");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(panelAltaProyecto, "Debe rellenar el campo para realizar la búsqueda");
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
				JOptionPane.showMessageDialog(panelAltaProyecto, "No se ha encontrado la imagen seleccionada");
			
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(panelAltaProyecto, "Debe introducir números en el formato adecuado");
			
			} catch (Exception e3) {
				JOptionPane.showMessageDialog(panelAltaProyecto, "Debe seleccionar al menos un alumno");
			}
		
		//Detecta el evento de hacer click para ver los detalles de un proyecto seleccionado
		} else if (e.getActionCommand().equals("verDetalles")) {
			try {
				panelDetalleProyecto.mostrarCiclo(new PersistenciaCiclos().cicloSeleccionado(panelConsultarProyectos.proyectoSeleccionado().getCiclo().getIdentificador()));
				panelDetalleProyecto.mostrarListaAlumnos(new PersistenciaAlumnos().consultaAlumnosBd(panelConsultarProyectos.proyectoSeleccionado().getIdProyecto()));
				panelDetalleProyecto.mostrar(panelConsultarProyectos.proyectoSeleccionado());
	
			} catch (IOException e1) {
				
			} catch (NullPointerException e2) {
				
			}
		
		//Detecta el evento de hacer click en el Submenu Baja Proyectos
		} else if (e.getActionCommand().equals("submenuBajaProyecto")) {
			mainGui.setPanel(panelPrincipal);
			panelBajaProyecto.mostrar(new PersistenciaProyectos().listadoProyectosDb());
		
		//Detecta el evento de hacer click para confirmar la eliminación de un Proyecto
		} else if (e.getActionCommand().equals("eliminarProyecto")) {
			
			try {
				new PersistenciaProyectos().eliminarProyecto(panelBajaProyecto.getDatos());
				panelBajaProyecto.mostrar(new PersistenciaProyectos().listadoProyectosDb());
			
			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(panelBajaProyecto, "Debe seleccionar un elemento de la lista");
			}
			
		
		//Detecta el evento de hacer click en el Submenu Modificar Proyectos
		} else if (e.getActionCommand().equals("submenuModificarProyectos")) {
			modalModificarProyecto.mostrar(new PersistenciaProyectos().listadoProyectosDb());
		
		//Detecta el evento de hacer click para confirmar la selección del Proyecto a modificar
		} else if (e.getActionCommand().equals("modalModificarProyecto")) {
			
			try {
				panelModificarProyecto.mostrarProyecto(modalModificarProyecto.getDatos());
				mainGui.setPanel(panelModificarProyecto);
				panelModificarProyecto.cargarCiclos(new PersistenciaCiclos().listadoCiclosBd());
				panelModificarProyecto.alumnosQueNoParticipan(new PersistenciaAlumnos().listaAlumnosBd(),
						new PersistenciaAlumnos().consultaAlumnosDeProyecto(panelModificarProyecto.getIdProyecto()));
				
				panelModificarProyecto.cargarModelo(new PersistenciaAlumnos().consultaAlumnosDeProyecto(panelModificarProyecto.getIdProyecto()));
				modalModificarProyecto.setVisible(false);
			
			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(panelModificarProyecto, "Debe seleccionar un elemento de la lista");
			}
			
		
		//Detecta el evento de confirmar definitivamente la información que modifica el proyecto.
		} else if (e.getActionCommand().equals("modificarProyecto")) {
			try {
				new PersistenciaProyectos().modificarProyecto(panelModificarProyecto.getDatos());
				mainGui.setPanel(panelPrincipal);
			
			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(panelModificarProyecto, "No se ha encontrado la imagen seleccionada");
				
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(panelModificarProyecto, "Debe introducir números en el formato adecuado");

			} catch (Exception e3) {
				System.out.println(e3.getMessage());
				JOptionPane.showMessageDialog(panelModificarProyecto, "El proyecto debe contener al menos un alumno");
			}
		}
	}
}
