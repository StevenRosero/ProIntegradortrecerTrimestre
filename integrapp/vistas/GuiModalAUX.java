package vistas;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import modelo.AlumnoPojo;
import modelo.ProyectoPojo;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import controlador.ControladorOtrosEventos;
import controlador.ControladorProyectos;
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
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.swing.JTextArea;

public class GuiModalAUX extends JDialog implements InterfazGui{
	private DefaultListModel<AlumnoPojo> modelo;
	private JButton btnModificarAlumno;
	private JLabel lblProyecto;
	private JButton btnCancelar;
	private JLabel lblModificarAlumno;
	private JTextArea textResultado;
	private JScrollPane scrollPane;
	
	public GuiModalAUX() {
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

		// Tamaño del JDialog
		setBounds(100, 100, 650, 650);

		// JButton Eliminar Alumno
		btnModificarAlumno = new JButton("MODIFICAR ALUMNO");
		btnModificarAlumno.setActionCommand("modalModificarAlumno");
		btnModificarAlumno.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 13));
		btnModificarAlumno.setBorder(null);
		btnModificarAlumno.setBackground(new Color(176, 224, 230));

		// label imagen papelera
		lblProyecto = new JLabel("");
		lblProyecto.setIcon(new ImageIcon(GuiModalAUX.class.getResource("/images/update.png")));

		btnCancelar = new JButton("CANCELAR");
		btnCancelar.setActionCommand("cancelarAlumno");
		btnCancelar.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 13));
		btnCancelar.setBorder(null);
		btnCancelar.setBackground(new Color(176, 224, 230));

		lblModificarAlumno = new JLabel("MODIFICAR ALUMNO");
		lblModificarAlumno.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 39));
		
		scrollPane = new JScrollPane();

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
					.addContainerGap(124, Short.MAX_VALUE)
					.addComponent(lblModificarAlumno, GroupLayout.PREFERRED_SIZE, 406, GroupLayout.PREFERRED_SIZE)
					.addGap(104))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(184)
					.addComponent(lblProyecto, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
					.addGap(27))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addComponent(lblModificarAlumno, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(33)
							.addComponent(lblProyecto, GroupLayout.PREFERRED_SIZE, 334, GroupLayout.PREFERRED_SIZE)
							.addGap(94))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
							.addGap(66)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnModificarAlumno, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
					.addGap(37))
		);
		
		textResultado = new JTextArea();
		scrollPane.setViewportView(textResultado);

		// Inicializa el modelo de la lista
		modelo = new DefaultListModel<AlumnoPojo>();
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
		btnModificarAlumno.addActionListener(control);
		btnCancelar.addActionListener(control);
		
	}
	
	// Método que muestra los alumnos disponibles en la base de datos mediante el
	// uso de un JList
	public void mostrar(ProyectoPojo proyecto) throws IOException {
		byte[] imageObtained= proyecto.getBlobImagen();
		ByteArrayInputStream bais = new ByteArrayInputStream(imageObtained);
		BufferedImage img = ImageIO.read(bais);

		reciclar();
		lblProyecto.setIcon(new ImageIcon(img));
		textResultado.setText(proyecto.toString());
		hacerVisible();

	}

	// Método que recicla los elementos del modelo vaciando la lista
	private void reciclar() {
		modelo.removeAllElements();
	}
}
