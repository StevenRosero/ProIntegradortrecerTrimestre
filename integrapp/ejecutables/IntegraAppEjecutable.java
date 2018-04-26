package ejecutables;

import java.awt.EventQueue;

import controlador.ConexionBaseDatos;
import controlador.ControladorIntegraApp;
import vistas.GuiAltaAlumno;
import vistas.GuiAltaCiclo;
import vistas.GuiAltaProyecto;
import vistas.GuiBajaAlumno;
import vistas.GuiLogin;
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
					//Declara e inicializa los elementos que conforman el UI y el controlador.
					GuiPrincipal mainGui = new GuiPrincipal();
					GuiPanelPrincipal panelInicio = new GuiPanelPrincipal();
					GuiLogin ventanaLogin = new GuiLogin();
					GuiAltaProyecto ventanaAltaProyecto = new GuiAltaProyecto();
					GuiBajaAlumno ventanaBajaAlumno = new GuiBajaAlumno();
					GuiAltaAlumno ventanaAltaAlumno = new GuiAltaAlumno();
					GuiAltaCiclo ventanaAltaCiclo = new GuiAltaCiclo();
					GuiModificarAlumno ventanaModificarAlumno = new GuiModificarAlumno();
					GuiModalModificarAlumno modalModificarAlumno = new GuiModalModificarAlumno();
					ConexionBaseDatos baseDatos = new ConexionBaseDatos();
					
					ControladorIntegraApp control = new ControladorIntegraApp(panelInicio, mainGui, ventanaLogin, 
							ventanaAltaProyecto, ventanaBajaAlumno, ventanaAltaAlumno, ventanaAltaCiclo,
							ventanaModificarAlumno, modalModificarAlumno, baseDatos);
					
					//Asigna el controlador a cada una de las ventanas del programa
					mainGui.setControlador(control);
					panelInicio.setControlador(control);
					ventanaLogin.setControlador(control);
					ventanaAltaProyecto.setControlador(control);
					ventanaBajaAlumno.setControlador(control);
					ventanaAltaAlumno.setControlador(control);
					ventanaAltaCiclo.setControlador(control);
					ventanaModificarAlumno.setControlador(control);
					modalModificarAlumno.setControlador(control);
					
					//establece la conexion con la base de datos
					baseDatos.conectar();
					
					//Hace Visible el UI que da salida visual al hilo principal de la aplicación
					mainGui.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
		});
	}
}
