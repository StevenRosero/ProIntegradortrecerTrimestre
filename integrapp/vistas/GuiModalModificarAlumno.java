package vistas;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import modelo.AlumnoPojo;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import controlador.ControladorAlumnos;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import javax.swing.ListSelectionModel;

public class GuiModalModificarAlumno extends JDialog implements InterfazGui{
	private DefaultListModel<AlumnoPojo> modelo;
	private JScrollPane scrollPane;
	private JList<AlumnoPojo> list;
	private JButton btnModificarAlumno;
	private JLabel lblPapelera;
	private JButton btnCancelar;
	private JLabel lblModificar;
	private JLabel lblModificarAlumno;
	
	public GuiModalModificarAlumno() {
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
		setTitle("MODIFICAR ALUMNO");
		
		//Icono de la ventana
		setIconImage(Toolkit.getDefaultToolkit().getImage(GuiBajaCiclo.class.getResource("/images/iconoVentana.png")));

		// Tamaño del JDialog
		setBounds(100, 100, 650, 650);

		// inicializa el ScrollPane de la Lista
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(176, 224, 230), 4));

		// JButton Eliminar Alumno
		btnModificarAlumno = new JButton("MODIFICAR ALUMNO");
		btnModificarAlumno.setActionCommand("modalModificarAlumno");
		btnModificarAlumno.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 13));
		btnModificarAlumno.setBorder(null);
		btnModificarAlumno.setBackground(new Color(176, 224, 230));

		// label imagen papelera
		lblPapelera = new JLabel("");
		lblPapelera.setIcon(new ImageIcon(GuiModalModificarAlumno.class.getResource("/images/update.png")));

		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setActionCommand("cancelarModificarAlumno");
		btnCancelar.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 13));
		btnCancelar.setBorder(null);
		btnCancelar.setBackground(new Color(176, 224, 230));

		lblModificar = new JLabel("Seleccione el alumno que desee modificar");
		lblModificar.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 16));

		lblModificarAlumno = new JLabel("MODIFICAR ALUMNO");
		lblModificarAlumno.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 39));

		// Inicializa el Layout del JDialog
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(100)
					.addComponent(btnModificarAlumno, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
					.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
					.addGap(99))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(31, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 575, GroupLayout.PREFERRED_SIZE)
					.addGap(28))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(185, Short.MAX_VALUE)
					.addComponent(lblModificar)
					.addGap(163))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(281, Short.MAX_VALUE)
					.addComponent(lblPapelera)
					.addGap(254))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(124, Short.MAX_VALUE)
					.addComponent(lblModificarAlumno, GroupLayout.PREFERRED_SIZE, 406, GroupLayout.PREFERRED_SIZE)
					.addGap(104))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addComponent(lblModificarAlumno, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPapelera)
					.addGap(31)
					.addComponent(lblModificar)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnModificarAlumno, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
					.addGap(37))
		);

		// Inicializa el modelo de la lista
		modelo = new DefaultListModel<AlumnoPojo>();

		// Inicializa la lista y la agrega al viewport del scrollpane
		list = new JList<AlumnoPojo>();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);

		// Modelo asignado a la lista
		list.setModel(modelo);
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

	public void setControlador(ControladorAlumnos control) {
		btnModificarAlumno.addActionListener(control);
		btnCancelar.addActionListener(control);
		
	}
	
	// Método que muestra los alumnos disponibles en la base de datos mediante el
	// uso de un JList
	public void mostrar(ArrayList<AlumnoPojo> alumnos) {
		reciclar();

		// agrega los alumnos de la base de datos a la lista que se utiliza como modelo
		// del JList
		for (int i = 0; i < alumnos.size(); i++) {
			modelo.addElement(alumnos.get(i));
		}
		hacerVisible();

	}

	// Método que recicla los elementos del modelo vaciando la lista
	private void reciclar() {
		modelo.removeAllElements();
	}

	// Método que almacena y retorna la selección del JList en un objeto de tipo
	// AlumnoPojo.
	public AlumnoPojo getDatos() {
		
		AlumnoPojo alumnoBorrar = list.getSelectedValue();

		return alumnoBorrar;
	}	
}
