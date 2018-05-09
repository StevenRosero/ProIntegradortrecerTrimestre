package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import persistencia.PersistenciaAlumnos;
import vistas.*;

public class ControladorAlumnos implements ActionListener {
	private GuiPrincipal mainGui;
	private GuiPanelPrincipal panelPrincipal;
	private GuiAltaAlumno panelAltaAlumno;
	private GuiBajaAlumno panelBajaAlumno;
	private GuiModalModificarAlumno modalModificarAlumno;
	private GuiModificarAlumno panelModificarAlumno;
	

	public ControladorAlumnos(GuiPrincipal mainGui, GuiPanelPrincipal panelPrincipal, 
			GuiBajaAlumno panelBajaAlumno, GuiAltaAlumno panelAltaAlumno,
			GuiModificarAlumno panelModificarAlumno, GuiModalModificarAlumno modalModificarAlumno) {
		
		this.mainGui = mainGui;
		this.panelPrincipal = panelPrincipal;
		this.panelAltaAlumno = panelAltaAlumno;
		this.panelBajaAlumno = panelBajaAlumno;
		this.modalModificarAlumno = modalModificarAlumno;
		this.panelModificarAlumno = panelModificarAlumno;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Detecta el evento de hacer click en el Submenu Baja Alumnos
		if (e.getSource().equals(mainGui.getSubmenuBajaAlumnos())) {
			panelBajaAlumno.mostrar(new PersistenciaAlumnos().listaAlumnosBd());
				
		//Detecta el evento de hacer click en el Submenu Modificar Alumnos
		} else if (e.getActionCommand().equals("submenuModificarAlumno")) {
			modalModificarAlumno.mostrar(new PersistenciaAlumnos().listaAlumnosBd());
				
		//Detecta el evento de hacer click en el Submenu Alta Alumnos
		} else if (e.getActionCommand().equals("panelAltaAlumnos")) {
			mainGui.setPanel(panelAltaAlumno);
							
		//Detecta el evento de hacer click para agregar un alumno en la base de datos
		} else if (e.getActionCommand().equals("agregarAlumno")) {
				
			//Recoge una excepción en el caso de que el expediente sea incorrecto.
			try {
				
				new PersistenciaAlumnos().agregarAlumnoBd(panelAltaAlumno.getDatos());
				mainGui.setPanel(panelPrincipal);
				panelAltaAlumno.reciclar();
			
			} catch (NumberFormatException | ExpedienteException e1) {
				JOptionPane.showMessageDialog(panelAltaAlumno, "El expediente debe contener 7 números");
			}
							
		//Detecta el evento de hacer click en aceptar para borrar un Alumno
		} else if (e.getActionCommand().equals("eliminarAlumno")) {
			
			try {
				new PersistenciaAlumnos().eliminarAlumnoBd(panelBajaAlumno.getDatos());
			
			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(panelBajaAlumno, "Debe Elegir una opción de la lista para continuar");
			}
			panelBajaAlumno.mostrar(new PersistenciaAlumnos().listaAlumnosBd());
							
		//Detecta el evento de hacer click en aceptar para seleccionar un alumno para modificar.
		} else if (e.getActionCommand().equals("modalModificarAlumno")) {
			
			try {
				panelModificarAlumno.mostrarAlumno(modalModificarAlumno.getDatos());
				modalModificarAlumno.dispose();
				mainGui.setPanel(panelModificarAlumno);
				
			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(panelBajaAlumno, "Debe Elegir una opción de la lista para continuar");
			}
	
		//Detecta el evento de confirmar la modificación de los datos de un alumno.
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
			mainGui.setPanel(panelPrincipal);
		
		} else if (e.getActionCommand().equals("cancelarModificarAlumno")) {
			modalModificarAlumno.dispose();
			mainGui.setPanel(panelPrincipal);
		}
	}
}
