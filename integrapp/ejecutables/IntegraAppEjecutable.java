package ejecutables;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import controlador.*;
import vistas.*;

public class IntegraAppEjecutable {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
	
				try {
					
					//Crea las fuentes y las registra en el sistema
					try {
						File archivo = new File("fonts/AvenirLTStd-Book.otf");
						InputStream f = new FileInputStream(archivo);
						Font font = Font.createFont(Font.TRUETYPE_FONT, f);
						GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
						ge.registerFont(font);
						
						archivo = new File("fonts/AvenirLTStd-Roman.otf");
						f = new FileInputStream(archivo);
						font = Font.createFont(Font.TRUETYPE_FONT, f);
						ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
						ge.registerFont(font);
						
					} catch (FontFormatException | IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
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
					GuiModalAcercaDe ventanaAcercaDe = new GuiModalAcercaDe();
					
					//Declara e inicializa el controlador de Otros Eventos
					ControladorOtrosEventos controlEventos = new ControladorOtrosEventos(mainGui, ventanaLogin, panelInicio, ventanaAcercaDe);
					ventanaLogin.setControlador(controlEventos);
					ventanaAcercaDe.setControlador(controlEventos);
					
					
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
