package vistas;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import controlador.ControladorIntegraApp;
import modelo.AdministradorPojo;
import javax.swing.GroupLayout.Alignment;

public class GuiBajaAlumno extends JDialog implements InterfazGui {
	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsuario;
	private JLabel lblUsuario;
	private JLabel lblCandado;
	private JLabel lblContrasenya;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JScrollPane scrollPane;

	public GuiBajaAlumno() {
		inicializar();
	}

	@Override
	public void inicializar() {
		//Obliga a que el JDialog sea Modal
				setModalityType(DEFAULT_MODALITY_TYPE);
				
				//Título de la ventana
				setTitle("Baja de Alumno");
				
				//Establece el fondo del ContentPane a Blanco
				getContentPane().setBackground(Color.WHITE);
				setBackground(Color.WHITE);
				
				//Establece la dimensión del JDialog
				setBounds(100, 100, 545, 610);
				
				//Layout de los Componentes
				getContentPane().setLayout(new BorderLayout());
				contentPanel.setPreferredSize(new Dimension(222, 10));
				contentPanel.setBackground(Color.WHITE);
				contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
				getContentPane().add(contentPanel, BorderLayout.CENTER);
				{
					//Campo de Texto Usuario
					txtUsuario = new JTextField();
					txtUsuario.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 16));
					txtUsuario.setBorder(new LineBorder(new Color(176, 224, 230), 4));
					txtUsuario.setMinimumSize(new Dimension(6, 10));
					txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
					txtUsuario.setPreferredSize(new Dimension(100, 10));
					txtUsuario.setColumns(10);
				}
				{
					//Etiqueta Usuario
					lblUsuario = new JLabel("<html><body style=text-align:'center>Introduzca el identificador del Alumno que desea eliminar<br> o seleccione uno de la lista</html>");
					lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
					lblUsuario.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 16));
				}
				//Etiqueta Candado
				lblCandado = new JLabel("");
				lblCandado.setIconTextGap(0);
				lblCandado.setHorizontalAlignment(SwingConstants.CENTER);
				lblCandado.setIcon(new ImageIcon(GuiBajaAlumno.class.getResource("/images/Candado.png")));
				{
					scrollPane = new JScrollPane();
					scrollPane.setBorder(new LineBorder(new Color(176, 224, 230), 4));
				}
				
				//Etiqueta Contraseña
				lblContrasenya = new JLabel("LISTA DE ALUMNOS");
				lblContrasenya.setHorizontalAlignment(SwingConstants.CENTER);
				lblContrasenya.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 25));
				GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
				gl_contentPanel.setHorizontalGroup(
					gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCandado, GroupLayout.PREFERRED_SIZE, 516, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 516, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblContrasenya, GroupLayout.PREFERRED_SIZE, 516, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsuario, GroupLayout.PREFERRED_SIZE, 516, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtUsuario, GroupLayout.PREFERRED_SIZE, 516, GroupLayout.PREFERRED_SIZE)
				);
				gl_contentPanel.setVerticalGroup(
					gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(1)
							.addComponent(lblCandado, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(159)
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(102)
									.addComponent(lblContrasenya, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblUsuario, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(59)
									.addComponent(txtUsuario, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))))
				);
				contentPanel.setLayout(gl_contentPanel);
				{
					//Panel de Botones
					JPanel buttonPane = new JPanel();
					buttonPane.setPreferredSize(new Dimension(10, 100));
					buttonPane.setBackground(new Color(255, 255, 255));
					getContentPane().add(buttonPane, BorderLayout.SOUTH);
					{
						//Botón Aceptar
						btnAceptar = new JButton("ACEPTAR");
						btnAceptar.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 14));
						btnAceptar.setBorder(null);
						btnAceptar.setBackground(new Color(176, 224, 230));
						btnAceptar.setPreferredSize(new Dimension(150, 40));
						btnAceptar.setActionCommand("OK");
						getRootPane().setDefaultButton(btnAceptar);
					}
					{
						//Botón Cancelar
						btnCancelar = new JButton("CANCELAR");
						
						btnCancelar.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 14));
						btnCancelar.setBorder(null);
						btnCancelar.setBackground(new Color(176, 224, 230));
						btnCancelar.setPreferredSize(new Dimension(150, 40));
						btnCancelar.setActionCommand("Cancel");
					}
					GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
					gl_buttonPane.setHorizontalGroup(
						gl_buttonPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_buttonPane.createSequentialGroup()
								.addGap(112)
								.addComponent(btnAceptar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGap(5)
								.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					);
					gl_buttonPane.setVerticalGroup(
						gl_buttonPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_buttonPane.createSequentialGroup()
								.addGap(30)
								.addGroup(gl_buttonPane.createParallelGroup(Alignment.LEADING)
									.addComponent(btnAceptar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					);
					buttonPane.setLayout(gl_buttonPane);
				}
				//centra el JDialog en el ViewPort
				setLocationRelativeTo(null);
		
	}

	@Override
	public void hacerVisible() {
		setVisible(true);
	}

	@Override
	public void setControlador(ControladorIntegraApp control) {
		btnAceptar.addActionListener(control);
		btnCancelar.addActionListener(control);
		
	}
	
	/*Método que obtiene los datos introducidos por el usuario
	y los almacena en un objeto de tipo AdministradorPojo*/
	public AdministradorPojo getDatos() {
		String usuario = "";
		String contrasenya = "";
		AdministradorPojo admin;
		
		usuario = txtUsuario.getText();
		admin = new AdministradorPojo(usuario, contrasenya);
		
		return admin;
	}
	
	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}
	
	//Método que convierte la contraseña a un String
	private String convertirContrasenya(char[] contrasenya) {
		String password = "";
		
		for (int i = 0; i < contrasenya.length; i++) {
			password += contrasenya[i];
		}
		return password;
	}
	
}
