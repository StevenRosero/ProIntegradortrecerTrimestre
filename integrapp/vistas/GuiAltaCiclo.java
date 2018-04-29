package vistas;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.Font;

import controlador.ControladorCiclos;
import controlador.ControladorOtrosEventos;
import modelo.AlumnoPojo;
import modelo.CicloFormativoPojo;

import javax.swing.border.LineBorder;

public class GuiAltaCiclo extends JPanel implements InterfazGui {
	private JLayeredPane layeredPaneBackground;
	private JLabel lblBackground;
	private JButton btnAgregarCiclo;
	private JLabel lblNombreCiclo;
	private JLabel lblDescripcion;
	private JButton btnBack;
	private JLabel lblAltaCiclo;
	private JTextField txtNombreCiclo;
	private JLabel lblImagenCiclo;
	private JTextArea txtrDescripcion;
	private JScrollPane scrollPaneDesc;
	
	public GuiAltaCiclo() {
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
		
		//JButton Botón Agregar Ciclo Formativo
		btnAgregarCiclo = new JButton("A\u00D1ADIR CICLO");
		btnAgregarCiclo.setActionCommand("agregarCiclo");
		btnAgregarCiclo.setBorder(null);
		btnAgregarCiclo.setBackground(new Color(176, 224, 230));
		btnAgregarCiclo.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAgregarCiclo.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 14));
		btnAgregarCiclo.setBounds(391, 561, 207, 59);
		layeredPaneBackground.add(btnAgregarCiclo);
		
		//Etiqueta Imagen Añadir Ciclo
		lblImagenCiclo = new JLabel("");
		lblImagenCiclo.setIcon(new ImageIcon(GuiAltaCiclo.class.getResource("/images/addproject.png")));
		lblImagenCiclo.setBounds(34, 30, 60, 60);
		layeredPaneBackground.add(lblImagenCiclo);
		
		//Etiqueta Nombre Ciclo Formativo
		lblNombreCiclo = new JLabel("NOMBRE DEL CICLO FORMATIVO");
		lblNombreCiclo.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblNombreCiclo.setBounds(368, 134, 251, 33);
		layeredPaneBackground.add(lblNombreCiclo);
		
		//Etiqueta Alta Ciclo Formativo
		lblAltaCiclo = new JLabel("ALTA DE CICLO FORMATIVO");
		lblAltaCiclo.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 37));
		lblAltaCiclo.setBounds(104, 56, 589, 34);
		layeredPaneBackground.add(lblAltaCiclo);
		
		//Etiqueta Descripcion Ciclo
		lblDescripcion = new JLabel("DESCRIPCION");
		lblDescripcion.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblDescripcion.setBounds(437, 243, 113, 33);
		layeredPaneBackground.add(lblDescripcion);
		
		//Scroll Pane Descripcion Ciclo
		scrollPaneDesc = new JScrollPane();
		scrollPaneDesc.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		scrollPaneDesc.setBounds(265, 276, 458, 261);
		layeredPaneBackground.add(scrollPaneDesc);
		
		//Text Area Descripcion Ciclo
		txtrDescripcion = new JTextArea();
		txtrDescripcion.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 14));
		txtrDescripcion.setBorder(null);
		scrollPaneDesc.setViewportView(txtrDescripcion);
		
		//Campo Nombre Ciclo
		txtNombreCiclo = new JTextField();
		txtNombreCiclo.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		txtNombreCiclo.setColumns(10);
		txtNombreCiclo.setBounds(265, 178, 458, 33);
		layeredPaneBackground.add(txtNombreCiclo);
		
		//JButton Back
		btnBack = new JButton("");
		btnBack.setContentAreaFilled(false);
		btnBack.setOpaque(false);
		btnBack.setPressedIcon(new ImageIcon(GuiAltaCiclo.class.getResource("/images/back.png")));
		btnBack.setSelectedIcon(new ImageIcon(GuiAltaCiclo.class.getResource("/images/back.png")));
		btnBack.setActionCommand("Volver");
		btnBack.setBorder(null);
		btnBack.setBorderPainted(false);
		btnBack.setBackground(new Color(255, 255, 255));
		btnBack.setIcon(new ImageIcon(GuiAltaCiclo.class.getResource("/images/back.png")));
		btnBack.setBounds(878, 30, 89, 61);
		layeredPaneBackground.add(btnBack);
		
		
		//JLabel Imagen Background
		lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(GuiAltaCiclo.class.getResource("/images/polybg.jpg")));
		lblBackground.setBounds(0, 0, 986, 685);
	
		layeredPaneBackground.add(lblBackground);
		setLayout(groupLayout);
		setBounds(0, 0, 986, 685);
	}
	
	public void setControlador(ControladorCiclos control, ControladorOtrosEventos controlEv) {
		btnBack.addActionListener(controlEv);
		btnAgregarCiclo.addActionListener(control);
		
	}
	
	//Método que almacena y retorna los datos consignados por el usuario en un objeto de tipo CicloFormativoPojo
	public CicloFormativoPojo getDatos() {
		CicloFormativoPojo ciclo = new CicloFormativoPojo(0, txtNombreCiclo.getText(), txtrDescripcion.getText());
		
		return ciclo;
	}
	
	//Método que recicla los campos y los deja vacíos.
	public void reciclar() {
		txtNombreCiclo.setText("");
		txtrDescripcion.setText("");
	}
}
