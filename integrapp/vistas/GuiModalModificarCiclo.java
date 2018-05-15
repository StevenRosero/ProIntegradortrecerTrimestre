package vistas;

import javax.swing.*;
import modelo.CicloFormativoPojo;
import java.util.ArrayList;
import controlador.ControladorCiclos;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GuiModalModificarCiclo extends JDialog implements InterfazGui{
	private DefaultListModel<CicloFormativoPojo> modelo;
	private JScrollPane scrollPane;
	private JList<CicloFormativoPojo> list;
	private JButton modificarCiclo;
	private JLabel lblPapelera;
	private JButton btnCancelarModificarCiclo;
	private JLabel lblCiclo;
	private JLabel lblBajaDeCiclo;
	
	public GuiModalModificarCiclo() {
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
		setTitle("Modificar Ciclos Formativos");
		
		//Icono de la ventana
		setIconImage(Toolkit.getDefaultToolkit().getImage(GuiBajaCiclo.class.getResource("/images/iconoVentana.png")));

		// Tamaño del JDialog
		setBounds(100, 100, 650, 650);

		// inicializa el ScrollPane de la Lista
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(176, 224, 230), 4));

		// JButton Eliminar Ciclo Formativo
		modificarCiclo = new JButton("MODIFICAR CICLO");
		modificarCiclo.setActionCommand("botonModificarCiclo");
		modificarCiclo.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 13));
		modificarCiclo.setBorder(null);
		modificarCiclo.setBackground(new Color(176, 224, 230));

		// label imagen papelera
		lblPapelera = new JLabel("");
		lblPapelera.setIcon(new ImageIcon(GuiModalModificarCiclo.class.getResource("/images/ModificarProy.png")));

		btnCancelarModificarCiclo = new JButton("CANCELAR");
		btnCancelarModificarCiclo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelarModificarCiclo.setActionCommand("cancelarCiclo");
		btnCancelarModificarCiclo.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 13));
		btnCancelarModificarCiclo.setBorder(null);
		btnCancelarModificarCiclo.setBackground(new Color(176, 224, 230));

		lblCiclo = new JLabel("Seleccione el Ciclo Formativo que desee modificar");
		lblCiclo.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 16));

		lblBajaDeCiclo = new JLabel("MODIFICAR CICLO FORMATIVO");
		lblBajaDeCiclo.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 39));

		// Inicializa el Layout del JDialog
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(100)
					.addComponent(modificarCiclo, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
					.addComponent(btnCancelarModificarCiclo, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
					.addGap(99))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(144)
					.addComponent(lblCiclo)
					.addContainerGap(135, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(25, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblBajaDeCiclo)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 575, GroupLayout.PREFERRED_SIZE))
					.addGap(28))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(257)
					.addComponent(lblPapelera)
					.addContainerGap(278, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addComponent(lblBajaDeCiclo, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblPapelera)
					.addGap(19)
					.addComponent(lblCiclo)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(modificarCiclo, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancelarModificarCiclo, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
					.addGap(37))
		);

		// Inicializa el modelo de la lista
		modelo = new DefaultListModel<CicloFormativoPojo>();

		// Inicializa la lista y la agrega al viewport del scrollpane
		list = new JList<CicloFormativoPojo>();
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

	public void setControlador(ControladorCiclos control) {
		modificarCiclo.addActionListener(control);
		btnCancelarModificarCiclo.addActionListener(control);
		
	}
	
	// Método que muestra los ciclos formativos disponibles en la base de datos mediante el
	// uso de un JList
	public void mostrar(ArrayList<CicloFormativoPojo> ciclos) {
		reciclar();

		// agrega los ciclos de la base de datos a la lista que se utiliza como modelo
		// del JList
		for (int i = 0; i < ciclos.size(); i++) {
			modelo.addElement(ciclos.get(i));
		}
		hacerVisible();

	}

	// Método que recicla los elementos del modelo vaciando la lista
	private void reciclar() {
		modelo.removeAllElements();
	}

	// Método que almacena y retorna la selección del JList en un objeto de tipo
	// CicloFormativoPojo.
	public CicloFormativoPojo getDatos() {
		
		CicloFormativoPojo cicloModificar = list.getSelectedValue();

		return cicloModificar;
	}
}
