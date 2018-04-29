package ejecutables;

import java.awt.EventQueue;
import controlador.ControladorAlumnos;
import controlador.ControladorCiclos;
import controlador.ControladorOtrosEventos;
import controlador.ControladorProyectos;
import vistas.GuiAltaAlumno;
import vistas.GuiAltaCiclo;
import vistas.GuiAltaProyecto;
import vistas.GuiBajaAlumno;
import vistas.GuiLogin;
import vistas.GuiModalAUX;
import vistas.GuiModalModificarAlumno;
import vistas.GuiModificarAlumno;
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
					GuiBajaAlumno ventanaBajaAlumno = new GuiBajaAlumno();
					GuiAltaAlumno ventanaAltaAlumno = new GuiAltaAlumno();
					GuiAltaCiclo ventanaAltaCiclo = new GuiAltaCiclo();
					GuiModificarAlumno ventanaModificarAlumno = new GuiModificarAlumno();
					GuiModalModificarAlumno modalModificarAlumno = new GuiModalModificarAlumno();
					GuiModalAUX modalAux = new GuiModalAUX();
					
					//Declara e inicializa el controlador de Otros Eventos
					ControladorOtrosEventos controlEventos = new ControladorOtrosEventos(mainGui, ventanaLogin, panelInicio);
					panelInicio.setControlador(controlEventos);
					ventanaLogin.setControlador(controlEventos);
					
					
					//Declara e inicializa el controlador de Alumnos
					ControladorAlumnos controlAlumnos = new ControladorAlumnos(mainGui, panelInicio, 
							ventanaBajaAlumno, ventanaAltaAlumno, ventanaModificarAlumno, modalModificarAlumno);
					
					ventanaAltaAlumno.setControlador(controlAlumnos, controlEventos);
					ventanaBajaAlumno.setControlador(controlAlumnos);
					ventanaModificarAlumno.setControlador(controlAlumnos, controlEventos);
					modalModificarAlumno.setControlador(controlAlumnos);
					
					
					//Declara e inicializa el controlador de Ciclos
					ControladorCiclos controlCiclos = new ControladorCiclos(mainGui,ventanaAltaCiclo, panelInicio);
					
					ventanaAltaCiclo.setControlador(controlCiclos, controlEventos);
					
					
					
					//Declara e inicializa el controlador de Proyectos
					ControladorProyectos controlProyectos = new ControladorProyectos(mainGui, panelInicio,ventanaAltaProyecto);
					ventanaAltaProyecto.setControlador(controlProyectos, controlEventos);
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
