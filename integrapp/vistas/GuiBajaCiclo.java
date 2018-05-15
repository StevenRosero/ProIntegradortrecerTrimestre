package vistas;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import modelo.CicloFormativoPojo;
import java.util.ArrayList;
import javax.swing.*;
import controlador.ControladorCiclos;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ListSelectionModel;

public class GuiBajaCiclo extends JDialog implements InterfazGui{
	private DefaultListModel<CicloFormativoPojo> modelo;
	private JScrollPane scrollPane;
	private JList<CicloFormativoPojo> list;
	private JButton borrarCiclo;
	private JLabel lblPapelera;
	private JButton btnCancelar;
	private JLabel lblCiclo;
	private JLabel lblBajaDeCiclo;
	
	public GuiBajaCiclo() {
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
		setTitle("Baja De Ciclos Formativos");
		
		//Icono de la ventana
		setIconImage(Toolkit.getDefaultToolkit().getImage(GuiBajaCiclo.class.getResource("/images/iconoVentana.png")));

		// Tamaño del JDialog
		setBounds(100, 100, 650, 650);

		// inicializa el ScrollPane de la Lista
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(176, 224, 230), 4));

		// JButton Eliminar Ciclo Formativo
		borrarCiclo = new JButton("ELIMINAR CICLO");
		borrarCiclo.setActionCommand("borrarCiclo");
		borrarCiclo.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 13));
		borrarCiclo.setBorder(null);
		borrarCiclo.setBackground(new Color(176, 224, 230));

		// label imagen papelera
		lblPapelera = new JLabel("");
		lblPapelera.setIcon(new ImageIcon(GuiBajaCiclo.class.getResource("/images/delete.png")));

		btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setActionCommand("cancelarCiclo");
		btnCancelar.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 13));
		btnCancelar.setBorder(null);
		btnCancelar.setBackground(new Color(176, 224, 230));

		lblCiclo = new JLabel("Seleccione el Ciclo Formativo que desee eliminar");
		lblCiclo.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 16));

		lblBajaDeCiclo = new JLabel("BAJA DE CICLO FORMATIVO");
		lblBajaDeCiclo.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 39));

		// Inicializa el Layout del JDialog
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(100)
					.addComponent(borrarCiclo, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
					.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
					.addGap(99))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(281, Short.MAX_VALUE)
					.addComponent(lblPapelera)
					.addGap(254))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(31, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 575, GroupLayout.PREFERRED_SIZE)
					.addGap(28))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(59, Short.MAX_VALUE)
					.addComponent(lblBajaDeCiclo)
					.addGap(50))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(144)
					.addComponent(lblCiclo)
					.addContainerGap(147, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addComponent(lblBajaDeCiclo, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPapelera)
					.addGap(31)
					.addComponent(lblCiclo)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
					.addGap(38)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(borrarCiclo, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
					.addGap(37))
		);

		// Inicializa el modelo de la lista
		modelo = new DefaultListModel<CicloFormativoPojo>();

		// Inicializa la lista y la agrega al viewport del scrollpane
		list = new JList<CicloFormativoPojo>();
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

	public void setControlador(ControladorCiclos control) {
		borrarCiclo.addActionListener(control);
		btnCancelar.addActionListener(control);
		
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
	// CicloFormativoPojo. Lanza una excepción si el usuario no elige ninguna opción.
	public CicloFormativoPojo getDatos() {
		
		CicloFormativoPojo cicloBorrar = list.getSelectedValue();

		return cicloBorrar;
	}
}
