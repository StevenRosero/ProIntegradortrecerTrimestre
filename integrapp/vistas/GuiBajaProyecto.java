package vistas;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import modelo.ProyectoPojo;
import java.util.ArrayList;
import javax.swing.*;
import controlador.ControladorProyectos;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class GuiBajaProyecto extends JDialog implements InterfazGui{
	private DefaultListModel<ProyectoPojo> modelo;
	private JScrollPane spProyectos;
	private JList<ProyectoPojo> listProyectos;
	private JButton btnEliminarProyecto;
	private JLabel lblPapelera;
	private JButton btnCancelar;
	private JLabel lblSeleccioneProyecto;
	private JLabel lblBajaDeProyecto;
	
	public GuiBajaProyecto() {
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
		setTitle("Baja De Proyectos");
		
		//Icono de la ventana
		setIconImage(Toolkit.getDefaultToolkit().getImage(GuiBajaCiclo.class.getResource("/images/iconoVentana.png")));
		
		// Tamaño del JDialog
		setBounds(100, 100, 650, 650);

		// inicializa el ScrollPane de la Lista
		spProyectos = new JScrollPane();
		spProyectos.setBorder(new LineBorder(new Color(176, 224, 230), 4));

		// JButton Eliminar Alumno
		btnEliminarProyecto = new JButton("ELIMINAR PROYECTO");
		btnEliminarProyecto.setActionCommand("eliminarProyecto");
		btnEliminarProyecto.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 13));
		btnEliminarProyecto.setBorder(null);
		btnEliminarProyecto.setBackground(new Color(176, 224, 230));

		// label imagen papelera
		lblPapelera = new JLabel("");
		lblPapelera.setIcon(new ImageIcon(GuiBajaProyecto.class.getResource("/images/delete.png")));

		btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setActionCommand("cancelarAlumno");
		btnCancelar.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 13));
		btnCancelar.setBorder(null);
		btnCancelar.setBackground(new Color(176, 224, 230));

		lblSeleccioneProyecto = new JLabel("Seleccione el Proyecto que desee eliminar");
		lblSeleccioneProyecto.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 16));

		lblBajaDeProyecto = new JLabel("BAJA DE PROYECTO");
		lblBajaDeProyecto.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 39));

		// Inicializa el Layout del JDialog
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(100)
					.addComponent(btnEliminarProyecto, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
					.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
					.addGap(99))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(31, Short.MAX_VALUE)
					.addComponent(spProyectos, GroupLayout.PREFERRED_SIZE, 575, GroupLayout.PREFERRED_SIZE)
					.addGap(28))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(185, Short.MAX_VALUE)
					.addComponent(lblSeleccioneProyecto)
					.addGap(163))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(281, Short.MAX_VALUE)
					.addComponent(lblPapelera)
					.addGap(254))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(136)
					.addComponent(lblBajaDeProyecto)
					.addContainerGap(122, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addComponent(lblBajaDeProyecto, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPapelera)
					.addGap(31)
					.addComponent(lblSeleccioneProyecto)
					.addGap(18)
					.addComponent(spProyectos, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnEliminarProyecto, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
					.addGap(37))
		);

		// Inicializa el modelo de la lista
		modelo = new DefaultListModel<ProyectoPojo>();

		// Inicializa la lista y la agrega al viewport del scrollpane
		listProyectos = new JList<ProyectoPojo>();
		listProyectos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		spProyectos.setViewportView(listProyectos);

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
		btnEliminarProyecto.addActionListener(control);
		btnCancelar.addActionListener(control);
		
	}
	
	// Método que muestra los proyectos disponibles en la base de datos mediante el
	// uso de un JList
	public void mostrar(ArrayList<ProyectoPojo> proyectos) {
		reciclar();

		// agrega los proyectos de la base de datos a la lista que se utiliza como modelo
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
	// ProyectoPojo.
	public ProyectoPojo getDatos() {
		ProyectoPojo proyectoBorrar = listProyectos.getSelectedValue();

		return proyectoBorrar;
	}
}
