package vistas;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.Font;

import controlador.ControladorAlumnos;
import controlador.ControladorOtrosEventos;
import modelo.AlumnoPojo;
import javax.swing.border.LineBorder;

public class GuiAltaAlumno extends JPanel implements InterfazGui {
	private JLayeredPane layeredPaneBackground;
	private JLabel lblBackground;
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
		
		//Color Background del Panel
		setBackground(new Color(255, 255, 255));
		
		//Panel por capas con el background y resto de componentes
		layeredPaneBackground = new JLayeredPane();
		
		//Layout
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(layeredPaneBackground, GroupLayout.DEFAULT_SIZE, 986, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(layeredPaneBackground, GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE)
		);
		
		//JButton Botón Agregar Alumno
		btnAgregarAlumno = new JButton("A\u00D1ADIR ALUMNO");
		btnAgregarAlumno.setActionCommand("agregarAlumno");
		btnAgregarAlumno.setBorder(null);
		btnAgregarAlumno.setBackground(new Color(176, 224, 230));
		btnAgregarAlumno.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAgregarAlumno.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 14));
		btnAgregarAlumno.setBounds(391, 561, 207, 59);
		layeredPaneBackground.add(btnAgregarAlumno);
		
		//Etiqueta Imagen Añadir Alumno
		lblImagenProyecto = new JLabel("");
		lblImagenProyecto.setIcon(new ImageIcon(GuiAltaAlumno.class.getResource("/images/addproject.png")));
		lblImagenProyecto.setBounds(34, 30, 60, 60);
		layeredPaneBackground.add(lblImagenProyecto);
		
		//Etiqueta Nombre Alumno
		lblNombreAlumno = new JLabel("NOMBRE DEL ALUMNO");
		lblNombreAlumno.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblNombreAlumno.setBounds(405, 138, 183, 33);
		layeredPaneBackground.add(lblNombreAlumno);
		
		//Etiqueta Apellido 2
		JLabel lblApellido2 = new JLabel("APELLIDO 2");
		lblApellido2.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblApellido2.setBounds(444, 337, 95, 33);
		layeredPaneBackground.add(lblApellido2);
		
		//TxtArea Apellido2
		txtApellido2 = new JTextField();
		txtApellido2.setColumns(10);
		txtApellido2.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		txtApellido2.setBounds(344, 369, 299, 33);
		layeredPaneBackground.add(txtApellido2);
		
		//Etiqueta Alta Alumno
		lblAltaAlumno = new JLabel("ALTA DE ALUMNO");
		lblAltaAlumno.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 37));
		lblAltaAlumno.setBounds(104, 56, 388, 34);
		layeredPaneBackground.add(lblAltaAlumno);
		
		//Etiqueta Apellido1
		lblApellido1 = new JLabel("APELLIDO 1");
		lblApellido1.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblApellido1.setBounds(444, 243, 95, 33);
		layeredPaneBackground.add(lblApellido1);
		
		//Campo Nombre Alumno
		txtNombreAlumno = new JTextField();
		txtNombreAlumno.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		txtNombreAlumno.setColumns(10);
		txtNombreAlumno.setBounds(344, 171, 299, 33);
		layeredPaneBackground.add(txtNombreAlumno);
		
		//Campo Apellido1
		txtApellido1 = new JTextField();
		txtApellido1.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		txtApellido1.setBounds(344, 274, 299, 33);
		layeredPaneBackground.add(txtApellido1);
		txtApellido1.setColumns(10);
		
		//Campo Expediente
		txtExpediente = new JTextField();
		txtExpediente.setColumns(7);
		txtExpediente.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		txtExpediente.setBounds(344, 475, 299, 33);
		layeredPaneBackground.add(txtExpediente);
		
		//Etiqueta Expediente
		JLabel lblExpediente = new JLabel("EXPEDIENTE");
		lblExpediente.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblExpediente.setBounds(437, 440, 106, 33);
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
		lblBackground.setBounds(0, 0, 986, 685);
	
		layeredPaneBackground.add(lblBackground);
		setLayout(groupLayout);
		setBounds(0, 0, 986, 685);
	}
	
	public void setControlador(ControladorAlumnos control, ControladorOtrosEventos controlEv) {
		btnBack.addActionListener(controlEv);
		btnAgregarAlumno.addActionListener(control);
		
	}
	
	//Método que almacena y retorna los datos consignados por el usuario en un objeto de tipo AlumnoPojo
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
	
	//Método que recicla los campos y los deja vacíos.
	public void reciclar() {
		txtNombreAlumno.setText("");
		txtApellido1.setText("");
		txtApellido2.setText("");
		txtExpediente.setText("");
	}
}
