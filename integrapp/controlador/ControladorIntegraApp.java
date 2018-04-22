package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.AdministradorPojo;
import vistas.GuiBajaAlumno;
import vistas.ExpedienteException;
import vistas.GuiAltaAlumno;
import vistas.GuiAltaProyecto;
import vistas.GuiBajaAlumno;
import vistas.GuiLogin;
import vistas.GuiPanelPrincipal;
import vistas.GuiPrincipal;
	
public class ControladorIntegraApp implements ActionListener {
	private static final AdministradorPojo admin = new AdministradorPojo("ADMIN", "1234");
	private GuiPrincipal mainGui;
	private GuiLogin ventanaLogin;
	private GuiAltaProyecto panelAltaProyecto;
	private GuiPanelPrincipal panelPrincipal;
	private GuiBajaAlumno panelBajaAlumno;
	private ConexionBaseDatos baseDatos;
	private GuiAltaAlumno panelAltaAlumno;
	
	public ControladorIntegraApp(GuiPanelPrincipal panelPrincipal, GuiPrincipal mainGui,
			GuiLogin ventanaLogin, GuiAltaProyecto ventanaAltaProyecto, GuiBajaAlumno ventanaBajaAlumno
			, GuiAltaAlumno ventanaAltaAlumno) {
		this.mainGui = mainGui;
		this.ventanaLogin = ventanaLogin;
		this.panelAltaProyecto = ventanaAltaProyecto;
		this.panelPrincipal = panelPrincipal;
		this.panelBajaAlumno = ventanaBajaAlumno;
		this.panelAltaAlumno = ventanaAltaAlumno;
		mainGui.setPanel(this.panelPrincipal);
		baseDatos = new ConexionBaseDatos();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Detecta eventos en el Botón Login del Panel Principal
		if (e.getSource().equals(panelPrincipal.getBtnLogin())) {
			ventanaLogin.hacerVisible();
		
		//Detecta el evento de intento de identificación de usuario (Login)
		} else if (e.getSource().equals(ventanaLogin.getBtnAceptar())) {
			
			//Si la identificación es satisfactoria ejecuta el método esUsuarioAdministrador
			if (admin.comprobarContrasenya(ventanaLogin.getDatos()) == 1) {
				ventanaLogin.reciclar();
				ventanaLogin.dispose();
				mainGui.esUsuarioAdministrador();
				panelPrincipal.cambiarAdministrador();
			}
		
		//Detecta el evento de cancelar la operación de Login
		} else if (e.getSource().equals(ventanaLogin.getBtnCancelar())) {
			ventanaLogin.reciclar();
			ventanaLogin.dispose();
		
		//Detecta el evento de hacer click en el Submenu Alta Proyectos
		} else if (e.getSource().equals(mainGui.getSubmenuAltaProyecto())) {
			mainGui.setPanel(panelAltaProyecto);
		
		//Detecta el evento de hacer click en el Submenu Baja Alumnos
		} else if (e.getSource().equals(mainGui.getSubmenuBajaAlumnos())) {
			panelBajaAlumno.mostrar(baseDatos.listaAlumnosBaseDatos());
		}
		
		//Detecta el evento de hacer click en el icono volver al inicio
		else if (e.getActionCommand().equals("Volver")) {
			mainGui.setPanel(panelPrincipal);
		
		//Detecta el evento de hacer click para abrir el panel alta alumnos
		} else if (e.getActionCommand().equals("panelAltaAlumnos")) {
			mainGui.setPanel(panelAltaAlumno);
			
		//Detecta el evento de hacer click para agregar un alumno en la base de datos
		} else if (e.getActionCommand().equals("agregarAlumno")) {
			
			//Recoge una excepción en el caso de que el expediente sea incorrecto.
			try {
				baseDatos.agregarAlumno(panelAltaAlumno.getDatos());
				panelAltaAlumno.reciclar();
			} catch (NumberFormatException | ExpedienteException e1) {
				JOptionPane.showMessageDialog(panelAltaAlumno, "El expediente debe contener 7 números");
			}
		
		//Detecta el evento de hacer click en aceptar para borrar un Alumno
		} else if (e.getActionCommand().equals("eliminarAlumno")) {
			baseDatos.eliminarAlumnoBaseDatos(panelBajaAlumno.getDatos());
			panelBajaAlumno.mostrar(baseDatos.listaAlumnosBaseDatos());
		
		//Detecta el evento de hacer click para cancelar el proceso de borrar un Alumno
		} else if (e.getActionCommand().equals("cancelarAlumno")) {
			panelBajaAlumno.dispose();
		}
	}
}
