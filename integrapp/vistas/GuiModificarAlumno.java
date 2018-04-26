package vistas;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;

import controlador.ControladorIntegraApp;
import modelo.AlumnoPojo;
import javax.swing.border.LineBorder;

public class GuiModificarAlumno extends JPanel implements InterfazGui {
	private JLayeredPane layeredPaneBackground;
	private JLabel lblBackground;
	private JButton btnModificarAlumno;
	private JTextField txtApellido1;
	private JLabel lblNombreAlumno;
	private JLabel lblApellido1;
	private JButton btnBack;
	private JLabel lblModificarAlumno;
	private JTextField txtNombreAlumno;
	private JTextField txtExpediente;
	private JTextField txtApellido2;
	private JLabel lblImagenAlumno;
	private int  idAlumno;
	
	public GuiModificarAlumno() {
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
		btnModificarAlumno = new JButton("MODIFICAR ALUMNO");
		btnModificarAlumno.setActionCommand("modificarAlumnoDef");
		btnModificarAlumno.setBorder(null);
		btnModificarAlumno.setBackground(new Color(176, 224, 230));
		btnModificarAlumno.setHorizontalTextPosition(SwingConstants.CENTER);
		btnModificarAlumno.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 14));
		btnModificarAlumno.setBounds(391, 561, 207, 59);
		layeredPaneBackground.add(btnModificarAlumno);
		
		//Etiqueta Imagen Añadir Alumno
		lblImagenAlumno = new JLabel("");
		lblImagenAlumno.setIcon(new ImageIcon(GuiModificarAlumno.class.getResource("/images/addproject.png")));
		lblImagenAlumno.setBounds(34, 30, 60, 60);
		layeredPaneBackground.add(lblImagenAlumno);
		
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
		lblModificarAlumno = new JLabel("MODIFICAR ALUMNO");
		lblModificarAlumno.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 37));
		lblModificarAlumno.setBounds(104, 56, 388, 34);
		layeredPaneBackground.add(lblModificarAlumno);
		
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
		btnBack.setPressedIcon(new ImageIcon(GuiModificarAlumno.class.getResource("/images/back.png")));
		btnBack.setSelectedIcon(new ImageIcon(GuiModificarAlumno.class.getResource("/images/back.png")));
		btnBack.setActionCommand("Volver");
		btnBack.setBorder(null);
		btnBack.setBorderPainted(false);
		btnBack.setBackground(new Color(255, 255, 255));
		btnBack.setIcon(new ImageIcon(GuiModificarAlumno.class.getResource("/images/back.png")));
		btnBack.setBounds(878, 30, 89, 61);
		layeredPaneBackground.add(btnBack);
		
		
		//JLabel Imagen Background
		lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(GuiModificarAlumno.class.getResource("/images/polybg.jpg")));
		lblBackground.setBounds(0, 0, 986, 685);
	
		layeredPaneBackground.add(lblBackground);
		setLayout(groupLayout);
		setBounds(0, 0, 986, 685);
	}
	
	@Override
	public void setControlador(ControladorIntegraApp control) {
		btnBack.addActionListener(control);
		btnModificarAlumno.addActionListener(control);
		
	}
	
	//Método que refleja los datos almacenados en el objeto Alumno de la base de datos en los campos de texto
	public void mostrarAlumno(AlumnoPojo alumno) {
		idAlumno = alumno.getIdAlumno();
		txtNombreAlumno.setText(alumno.getNombre());
		txtApellido1.setText(alumno.getApellido1());
		txtApellido2.setText(alumno.getApellido2());
		txtExpediente.setText(Integer.toString(alumno.getExpediente()));
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
		
		AlumnoPojo alumno = new AlumnoPojo(idAlumno ,expediente, nombre, apellido1, apellido2);
		
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
