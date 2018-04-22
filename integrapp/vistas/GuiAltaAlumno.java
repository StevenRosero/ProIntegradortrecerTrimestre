package vistas;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import controlador.ControladorIntegraApp;
import modelo.AlumnoPojo;

import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.ComponentOrientation;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GuiAltaAlumno extends JPanel implements InterfazGui {
	private JLabel lblIconoInterrogacion;
	private JTextArea txtAyudaA;
	private JTextArea textAreaB;
	private JLabel lblCajaAyuda;
	private JLayeredPane layeredPaneBackground;
	private JLabel lblBackground;
	private JLayeredPane layeredPaneAyuda;
	private JButton btnAgregarAlumno;
	private JTextField txtApellido1;
	private JLabel lblNombreAlumno;
	private JLabel lblApellido1;
	private JButton btnBack;
	private JLabel lblAltaAlumno;
	private JTextField txtNombreAlumno;
	private JTextField txtExpediente;
	private JTextField txtApellido2;
	private JLabel lblImagenProyecto;
	
	public GuiAltaAlumno() {
		inicializar();
	}
	public void hacerVisible() {
		setVisible(true);
	}
	@Override
	public void inicializar() {
		setBackground(new Color(255, 255, 255));
		
		//Panel por capas Contenedor Ayuda
		layeredPaneAyuda = new JLayeredPane();
		layeredPaneAyuda.setOpaque(true);
		
		//Panel por capas con el background y resto de componentes
		layeredPaneBackground = new JLayeredPane();
		
		//Layout
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(layeredPaneAyuda, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(layeredPaneBackground, GroupLayout.DEFAULT_SIZE, 990, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(151, Short.MAX_VALUE)
					.addComponent(layeredPaneAyuda, GroupLayout.PREFERRED_SIZE, 534, GroupLayout.PREFERRED_SIZE))
				.addComponent(layeredPaneBackground, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE)
		);
		
		//JButton Botón Agregar Alumno
		btnAgregarAlumno = new JButton("A\u00D1ADIR ALUMNO");
		btnAgregarAlumno.setActionCommand("agregarAlumno");
		btnAgregarAlumno.setBorder(null);
		btnAgregarAlumno.setBackground(new Color(176, 224, 230));
		btnAgregarAlumno.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAgregarAlumno.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 14));
		btnAgregarAlumno.setBounds(365, 561, 207, 59);
		layeredPaneBackground.add(btnAgregarAlumno);
		
		//Etiqueta Imagen Añadir Alumno
		lblImagenProyecto = new JLabel("");
		lblImagenProyecto.setIcon(new ImageIcon(GuiAltaAlumno.class.getResource("/images/addproject.png")));
		lblImagenProyecto.setBounds(34, 30, 60, 60);
		layeredPaneBackground.add(lblImagenProyecto);
		
		//Etiqueta Nombre Alumno
		lblNombreAlumno = new JLabel("NOMBRE DEL ALUMNO");
		lblNombreAlumno.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblNombreAlumno.setBounds(379, 138, 183, 33);
		layeredPaneBackground.add(lblNombreAlumno);
		
		//Etiqueta Apellido 2
		JLabel lblApellido2 = new JLabel("APELLIDO 2");
		lblApellido2.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblApellido2.setBounds(418, 337, 95, 33);
		layeredPaneBackground.add(lblApellido2);
		
		//TxtArea Apellido2
		txtApellido2 = new JTextField();
		txtApellido2.setColumns(10);
		txtApellido2.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		txtApellido2.setBounds(318, 369, 299, 33);
		layeredPaneBackground.add(txtApellido2);
		
		//Etiqueta Alta Alumno
		lblAltaAlumno = new JLabel("ALTA DE ALUMNO");
		lblAltaAlumno.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 37));
		lblAltaAlumno.setBounds(104, 56, 388, 34);
		layeredPaneBackground.add(lblAltaAlumno);
		
		//Etiqueta Apellido1
		lblApellido1 = new JLabel("APELLIDO 1");
		lblApellido1.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblApellido1.setBounds(418, 243, 95, 33);
		layeredPaneBackground.add(lblApellido1);
		
		//Campo Nombre Alumno
		txtNombreAlumno = new JTextField();
		txtNombreAlumno.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		txtNombreAlumno.setColumns(10);
		txtNombreAlumno.setBounds(318, 171, 299, 33);
		layeredPaneBackground.add(txtNombreAlumno);
		
		//Campo Apellido1
		txtApellido1 = new JTextField();
		txtApellido1.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		txtApellido1.setBounds(318, 274, 299, 33);
		layeredPaneBackground.add(txtApellido1);
		txtApellido1.setColumns(10);
		
		//Campo Expediente
		txtExpediente = new JTextField();
		txtExpediente.setColumns(7);
		txtExpediente.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		txtExpediente.setBounds(318, 475, 299, 33);
		layeredPaneBackground.add(txtExpediente);
		
		//Etiqueta Expediente
		JLabel lblExpediente = new JLabel("EXPEDIENTE");
		lblExpediente.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblExpediente.setBounds(411, 440, 106, 33);
		layeredPaneBackground.add(lblExpediente);
		
		//JButton Back
		btnBack = new JButton("");
		btnBack.setContentAreaFilled(false);
		btnBack.setOpaque(false);
		btnBack.setPressedIcon(new ImageIcon(GuiAltaAlumno.class.getResource("/images/back.png")));
		btnBack.setSelectedIcon(new ImageIcon(GuiAltaAlumno.class.getResource("/images/back.png")));
		btnBack.setActionCommand("Volver");
		btnBack.setBorder(null);
		btnBack.setBorderPainted(false);
		btnBack.setBackground(new Color(255, 255, 255));
		btnBack.setIcon(new ImageIcon(GuiAltaAlumno.class.getResource("/images/back.png")));
		btnBack.setBounds(878, 30, 89, 61);
		layeredPaneBackground.add(btnBack);
		
		
		//JLabel Imagen Background
		lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(GuiAltaAlumno.class.getResource("/images/polybg.jpg")));
		lblBackground.setBounds(0, 0, 990, 685);
	
		layeredPaneBackground.add(lblBackground);
		
		//Imagen Icono Interrogación
		lblIconoInterrogacion = new JLabel("");
		lblIconoInterrogacion.setBounds(100, 40, 74, 74);
		layeredPaneAyuda.add(lblIconoInterrogacion);
		lblIconoInterrogacion.setIcon(new ImageIcon(GuiAltaAlumno.class.getResource("/images/Interrogacion.png")));
		
		//Texto Ayuda A
		txtAyudaA = new JTextArea();
		txtAyudaA.setForeground(new Color(255, 255, 255));
		txtAyudaA.setBounds(29, 152, 211, 89);
		layeredPaneAyuda.add(txtAyudaA);
		txtAyudaA.setLineWrap(true);
		txtAyudaA.setOpaque(false);
		txtAyudaA.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 16));
		txtAyudaA.setText("Si necesitas\r\nayuda con la aplicaci\u00F3n\r\npuedes acceder a la wiki\r\nen el siguiente enlace:\r\nwww.wiki.com");
		
		//Texto AyudaB
		textAreaB = new JTextArea();
		textAreaB.setText("O si lo prefieres, puedes\r\ncontactar con el equipo\r\nde desarrolladores\r\na trav\u00E9s de nuestro correo\r\nelectr\u00F3nico.\r\n\u00A1Estaremos encantados\r\nde ayudarte!\r\npepito@pepito.es");
		textAreaB.setOpaque(false);
		textAreaB.setLineWrap(true);
		textAreaB.setForeground(Color.WHITE);
		textAreaB.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 16));
		textAreaB.setBounds(26, 288, 211, 149);
		layeredPaneAyuda.add(textAreaB);
		
		//Container Ayuda
		lblCajaAyuda = new JLabel("");
		lblCajaAyuda.setBounds(0, 0, 284, 534);
		layeredPaneAyuda.add(lblCajaAyuda);
		lblCajaAyuda.setOpaque(true);
		lblCajaAyuda.setBackground(new Color(176, 224, 230));
		setLayout(groupLayout);
		setBounds(0, 0, 1280, 685);
	}
	
	@Override
	public void setControlador(ControladorIntegraApp control) {
		btnBack.addActionListener(control);
		btnAgregarAlumno.addActionListener(control);
		
	}
	
	public AlumnoPojo getDatos() throws NumberFormatException, ExpedienteException {
		int expediente = 0;
		String nombre;
		String apellido1;
		String apellido2;
		
		expediente = Integer.parseInt(txtExpediente.getText());
		if (txtExpediente.getText().length() != 7) throw new ExpedienteException();
			
		nombre = txtNombreAlumno.getText();
		apellido1 = txtApellido1.getText();
		apellido2 = txtApellido2.getText();
		
		AlumnoPojo alumno = new AlumnoPojo(0 ,expediente, nombre, apellido1, apellido2);
		
		return alumno;
	}
	
	public void reciclar() {
		txtNombreAlumno.setText("");
		txtApellido1.setText("");
		txtApellido2.setText("");
		txtExpediente.setText("");
	}
}
