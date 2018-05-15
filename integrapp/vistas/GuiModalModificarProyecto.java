package vistas;

import javax.swing.*;
import modelo.ProyectoPojo;
import java.util.ArrayList;
import controlador.ControladorProyectos;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GuiModalModificarProyecto extends JDialog implements InterfazGui{
	private DefaultListModel<ProyectoPojo> modelo;
	private JScrollPane scrollPane;
	private JList<ProyectoPojo> listProyectos;
	private JButton btnModificarProyecto;
	private JLabel lblPapelera;
	private JButton btnCancelar;
	private JLabel lblModificar;
	private JLabel lblModificarAlumno;
	
	public GuiModalModificarProyecto() {
		inicializar();	
	}
	
	@Override
	public void inicializar() {
		
		try {
			UIManager.setLookAndFeel(
			UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Titulo de la ventana
		setTitle("Modificar Proyecto");

		// Tamaño del JDialog
		setBounds(100, 100, 650, 650);
		
		//Icono de la ventana
		setIconImage(Toolkit.getDefaultToolkit().getImage(GuiBajaCiclo.class.getResource("/images/iconoVentana.png")));

		// inicializa el ScrollPane de la Lista
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(176, 224, 230), 4));

		// JButton Eliminar Alumno
		btnModificarProyecto = new JButton("MODIFICAR PROYECTO");
		btnModificarProyecto.setActionCommand("modalModificarProyecto");
		btnModificarProyecto.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 13));
		btnModificarProyecto.setBorder(null);
		btnModificarProyecto.setBackground(new Color(176, 224, 230));

		// label imagen papelera
		lblPapelera = new JLabel("");
		lblPapelera.setIcon(new ImageIcon(GuiModalModificarProyecto.class.getResource("/images/ModificarProy.png")));

		btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setActionCommand("cancelarProyecto");
		btnCancelar.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 13));
		btnCancelar.setBorder(null);
		btnCancelar.setBackground(new Color(176, 224, 230));

		lblModificar = new JLabel("Seleccione el Proyecto que desee modificar");
		lblModificar.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 16));

		lblModificarAlumno = new JLabel("MODIFICAR PROYECTO");
		lblModificarAlumno.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 39));

		// Inicializa el Layout del JDialog
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(100)
					.addComponent(btnModificarProyecto, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
					.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
					.addGap(99))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(31, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 575, GroupLayout.PREFERRED_SIZE)
					.addGap(28))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(162, Short.MAX_VALUE)
					.addComponent(lblModificar)
					.addGap(163))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(104, Short.MAX_VALUE)
					.addComponent(lblModificarAlumno, GroupLayout.PREFERRED_SIZE, 447, GroupLayout.PREFERRED_SIZE)
					.addGap(83))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(260)
					.addComponent(lblPapelera)
					.addContainerGap(264, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addComponent(lblModificarAlumno, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblPapelera)
					.addGap(19)
					.addComponent(lblModificar)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnModificarProyecto, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
					.addGap(37))
		);

		// Inicializa el modelo de la lista
		modelo = new DefaultListModel<ProyectoPojo>();

		// Inicializa la lista y la agrega al viewport del scrollpane
		listProyectos = new JList<ProyectoPojo>();
		listProyectos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(listProyectos);

		// Modelo asignado a la lista
		listProyectos.setModel(modelo);
		getContentPane().setLayout(groupLayout);

		// Centra el JDialog
		setLocationRelativeTo(null);

		// Aplica la propiedad Modal al Jdialog
		setModalityType(DEFAULT_MODALITY_TYPE);
		

		// Cierra la ventana al salir
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	@Override
	public void hacerVisible() {
		setVisible(true);
		
	}

	public void setControlador(ControladorProyectos control) {
		btnModificarProyecto.addActionListener(control);
		btnCancelar.addActionListener(control);
		
	}
	
	// Método que muestra los alumnos disponibles en la base de datos mediante el
	// uso de un JList
	public void mostrar(ArrayList<ProyectoPojo> proyectos) {
		reciclar();

		// agrega los alumnos de la base de datos a la lista que se utiliza como modelo
		// del JList
		for (int i = 0; i < proyectos.size(); i++) {
			modelo.addElement(proyectos.get(i));
		}
		hacerVisible();

	}

	// Método que recicla los elementos del modelo vaciando la lista
	private void reciclar() {
		modelo.removeAllElements();
	}

	// Método que almacena y retorna la selección del JList en un objeto de tipo
	// AlumnoPojo.
	public ProyectoPojo getDatos() {
		ProyectoPojo proyectoModificar = listProyectos.getSelectedValue();

		return proyectoModificar;
	}
}
