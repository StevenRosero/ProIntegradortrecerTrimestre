package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JOptionPane;

import modelo.AdministradorPojo;
import vistas.GuiBajaAlumno;
import vistas.ExpedienteException;
import vistas.GuiAltaAlumno;
import vistas.GuiAltaCiclo;
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
	private GuiAltaCiclo panelAltaCiclo;
	
	public ControladorIntegraApp(GuiPanelPrincipal panelPrincipal, GuiPrincipal mainGui,
			GuiLogin ventanaLogin, GuiAltaProyecto ventanaAltaProyecto, GuiBajaAlumno ventanaBajaAlumno
			, GuiAltaAlumno ventanaAltaAlumno, GuiAltaCiclo ventanaAltaCiclo) {
		this.mainGui = mainGui;
		this.ventanaLogin = ventanaLogin;
		this.panelAltaProyecto = ventanaAltaProyecto;
		this.panelPrincipal = panelPrincipal;
		this.panelBajaAlumno = ventanaBajaAlumno;
		this.panelAltaAlumno = ventanaAltaAlumno;
		this.panelAltaCiclo = ventanaAltaCiclo;
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
			panelAltaProyecto.cargarModelo(baseDatos.listaAlumnosBaseDatos());
			panelAltaProyecto.cargarCiclos(baseDatos.listaCiclosFormativos());
		
		//Detecta el evento de hacer click en el Submenu Alta Ciclos
		} else if (e.getActionCommand().equals("panelAltaCiclos")) {
			mainGui.setPanel(panelAltaCiclo);
		
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
			
		//Detecta el evento de hacer click para agregar un proyecto en la base de datos
		} else if (e.getActionCommand().equals("agregarProyecto")) {
				try {
					baseDatos.agregarProyecto(panelAltaProyecto.getDatos());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
		
		//Detecta el evento de hacer click en aceptar para borrar un Alumno
		} else if (e.getActionCommand().equals("eliminarAlumno")) {
			baseDatos.eliminarAlumnoBaseDatos(panelBajaAlumno.getDatos());
			panelBajaAlumno.mostrar(baseDatos.listaAlumnosBaseDatos());
		
		//Detecta el evento de hacer click para cancelar el proceso de borrar un Alumno
		} else if (e.getActionCommand().equals("cancelarAlumno")) {
			panelBajaAlumno.dispose();
		
		//Detecta el evento de hacer click para intentar dar de alta un Ciclo Formativo
		} else if (e.getActionCommand().equals("agregarCiclo")) {
			baseDatos.altaCicloBaseDatos(panelAltaCiclo.getDatos());
		}
	}
}
