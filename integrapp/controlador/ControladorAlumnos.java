package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import persistencia.PersistenciaAlumnos;
import vistas.ExpedienteException;
import vistas.GuiAltaAlumno;
import vistas.GuiBajaAlumno;
import vistas.GuiModalModificarAlumno;
import vistas.GuiModificarAlumno;
import vistas.GuiPanelPrincipal;
import vistas.GuiPrincipal;

public class ControladorAlumnos implements ActionListener {
	private GuiPrincipal mainGui;
	private GuiPanelPrincipal panelPrincipal;
	private GuiBajaAlumno panelBajaAlumno;
	private GuiAltaAlumno panelAltaAlumno;
	private GuiModificarAlumno panelModificarAlumno;
	private GuiModalModificarAlumno modalModificarAlumno;

	public ControladorAlumnos(GuiPrincipal mainGui, GuiPanelPrincipal panelPrincipal, GuiBajaAlumno panelBajaAlumno,
			GuiAltaAlumno panelAltaAlumno, GuiModificarAlumno panelModificarAlumno,
			GuiModalModificarAlumno modalModificarAlumno) {
		
		this.mainGui = mainGui;
		this.panelPrincipal = panelPrincipal;
		this.panelBajaAlumno = panelBajaAlumno;
		this.panelAltaAlumno = panelAltaAlumno;
		this.panelModificarAlumno = panelModificarAlumno;
		this.modalModificarAlumno = modalModificarAlumno;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Detecta el evento de hacer click en el Submenu Baja Alumnos
		if (e.getSource().equals(mainGui.getSubmenuBajaAlumnos())) {
			panelBajaAlumno.mostrar(new PersistenciaAlumnos().listaAlumnosBd());
				
		//Detecta el evento de hacer click en el Submenu Modificar Alumnos
		} else if (e.getActionCommand().equals("submenuModificarAlumno")) {
			modalModificarAlumno.mostrar(new PersistenciaAlumnos().listaAlumnosBd());
			mainGui.setPanel(panelModificarAlumno);
				
		//Detecta el evento de hacer click para abrir el panel alta alumnos
		} else if (e.getActionCommand().equals("panelAltaAlumnos")) {
			mainGui.setPanel(panelAltaAlumno);
							
		//Detecta el evento de hacer click para agregar un alumno en la base de datos
		} else if (e.getActionCommand().equals("agregarAlumno")) {
				
			//Recoge una excepción en el caso de que el expediente sea incorrecto.
			try {
				
				new PersistenciaAlumnos().agregarAlumnoBd(panelAltaAlumno.getDatos());
				panelAltaAlumno.reciclar();
			
			} catch (NumberFormatException | ExpedienteException e1) {
				JOptionPane.showMessageDialog(panelAltaAlumno, "El expediente debe contener 7 números");
			}
							
		//Detecta el evento de hacer click en aceptar para borrar un Alumno
		} else if (e.getActionCommand().equals("eliminarAlumno")) {
			new PersistenciaAlumnos().eliminarAlumnoBd(panelBajaAlumno.getDatos());
			panelBajaAlumno.mostrar(new PersistenciaAlumnos().listaAlumnosBd());
							
		//Detecta el evento de hacer click en aceptar para modificar un Alumno
		} else if (e.getActionCommand().equals("modalModificarAlumno")) {
			panelModificarAlumno.mostrarAlumno(modalModificarAlumno.getDatos());
			modalModificarAlumno.dispose();
			mainGui.setPanel(panelModificarAlumno);
							
		} else if (e.getActionCommand().equals("modificarAlumnoDef")) {
			try {
				new PersistenciaAlumnos().modificarAlumnoBd(panelModificarAlumno.getDatos());
				mainGui.setPanel(panelPrincipal);
			} catch (NumberFormatException | ExpedienteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
						
		//Detecta el evento de hacer click para cancelar el proceso de borrar un Alumno
		} else if (e.getActionCommand().equals("cancelarAlumno")) {
			panelBajaAlumno.dispose();
		}
	}
}
