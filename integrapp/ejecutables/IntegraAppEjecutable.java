package ejecutables;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import controlador.ControladorAlumnos;
import controlador.ControladorCiclos;
import controlador.ControladorOtrosEventos;
import controlador.ControladorProyectos;
import vistas.GuiAltaAlumno;
import vistas.GuiAltaCiclo;
import vistas.GuiAltaProyecto;
import vistas.GuiBajaAlumno;
import vistas.GuiBajaCiclo;
import vistas.GuiBajaProyecto;
import vistas.GuiConsultarProyectos;
import vistas.GuiLogin;
import vistas.GuiModalDetalleProyecto;
import vistas.GuiModalModificarAlumno;
import vistas.GuiModalModificarCiclo;
import vistas.GuiModalModificarProyecto;
import vistas.GuiModificarAlumno;
import vistas.GuiModificarCiclo;
import vistas.GuiModificarProyecto;
import vistas.GuiPanelPrincipal;
import vistas.GuiPrincipal;

public class IntegraAppEjecutable {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
	
				try {
					//Declara e inicializa los elementos que conforman el UI.
					GuiPrincipal mainGui = new GuiPrincipal();
					GuiPanelPrincipal panelInicio = new GuiPanelPrincipal();
					GuiLogin ventanaLogin = new GuiLogin();
					GuiAltaProyecto ventanaAltaProyecto = new GuiAltaProyecto();
					GuiModalModificarProyecto modalModificarProyecto = new GuiModalModificarProyecto();
					GuiModificarProyecto ventanaModificarProyecto = new GuiModificarProyecto();
					GuiBajaAlumno ventanaBajaAlumno = new GuiBajaAlumno();
					GuiAltaAlumno ventanaAltaAlumno = new GuiAltaAlumno();
					GuiAltaCiclo ventanaAltaCiclo = new GuiAltaCiclo();
					GuiModificarAlumno ventanaModificarAlumno = new GuiModificarAlumno();
					GuiModalModificarAlumno modalModificarAlumno = new GuiModalModificarAlumno();
					GuiConsultarProyectos ventanaConsultarProyectos = new GuiConsultarProyectos();
					GuiModalDetalleProyecto modalAux = new GuiModalDetalleProyecto();
					GuiBajaProyecto ventanaBajaProyecto = new GuiBajaProyecto();
					GuiBajaCiclo ventanaBajaCiclos = new GuiBajaCiclo();
					GuiModalModificarCiclo modalModificarCiclo = new GuiModalModificarCiclo();
					GuiModificarCiclo ventanaModificarCiclo = new GuiModificarCiclo();
					
					//Declara e inicializa el controlador de Otros Eventos
					ControladorOtrosEventos controlEventos = new ControladorOtrosEventos(mainGui, ventanaLogin, panelInicio);
					ventanaLogin.setControlador(controlEventos);
					
					
					//Declara e inicializa el controlador de Alumnos
					ControladorAlumnos controlAlumnos = new ControladorAlumnos(mainGui, panelInicio, 
							ventanaBajaAlumno, ventanaAltaAlumno, ventanaModificarAlumno, modalModificarAlumno);
					
					ventanaAltaAlumno.setControlador(controlAlumnos, controlEventos);
					ventanaBajaAlumno.setControlador(controlAlumnos);
					ventanaModificarAlumno.setControlador(controlAlumnos, controlEventos);
					modalModificarAlumno.setControlador(controlAlumnos);
					
					
					//Declara e inicializa el controlador de Ciclos
					ControladorCiclos controlCiclos = new ControladorCiclos(mainGui,ventanaAltaCiclo, panelInicio, 
							ventanaBajaCiclos, modalModificarCiclo, ventanaModificarCiclo);
					ventanaAltaCiclo.setControlador(controlCiclos, controlEventos);
					ventanaBajaCiclos.setControlador(controlCiclos);
					modalModificarCiclo.setControlador(controlCiclos);
					ventanaModificarCiclo.setControlador(controlCiclos, controlEventos);
					
					
					//Declara e inicializa el controlador de Proyectos
					ControladorProyectos controlProyectos = new ControladorProyectos(mainGui, panelInicio,ventanaAltaProyecto,
							ventanaConsultarProyectos, ventanaBajaProyecto, modalModificarProyecto, ventanaModificarProyecto);
					
					ventanaAltaProyecto.setControlador(controlProyectos, controlEventos);
					ventanaConsultarProyectos.setControlador(controlProyectos, controlEventos);
					panelInicio.setControlador(controlEventos, controlProyectos);
					ventanaBajaProyecto.setControlador(controlProyectos);
					modalModificarProyecto.setControlador(controlProyectos);
					ventanaModificarProyecto.setControlador(controlProyectos, controlEventos);
					modalAux.setControlador(controlProyectos);
					
					//Asigna los controladores al mainGUI
					mainGui.setControlador(controlProyectos, controlAlumnos, controlEventos, controlCiclos);
					
					
					//Hace Visible el UI que da salida visual al hilo principal de la aplicación
					mainGui.setVisible(true);
					mainGui.setPanel(panelInicio);
					
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
		});
	}
}
