package vistas;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;

import controlador.ControladorAlumnos;
import controlador.ControladorCiclos;
import controlador.ControladorOtrosEventos;
import modelo.AlumnoPojo;
import modelo.CicloFormativoPojo;

import javax.swing.border.LineBorder;

public class GuiModificarCiclo extends JPanel implements InterfazGui {
	private JLayeredPane layeredPaneBackground;
	private JLabel lblBackground;
	private JButton btnModificarCiclo;
	private JLabel lblNombreCiclo;
	private JButton btnBack;
	private JLabel lblModificarCiclo;
	private JLabel lblImagenCiclo;
	private int  idCiclo;
	private JTextField txtNombreCiclo;
	private JTextArea txtDescripcionCiclo;
	private JScrollPane spDescripcion;
	private JLabel lblDescripcinCicloFormativo;
	
	public GuiModificarCiclo() {
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
		
		spDescripcion = new JScrollPane();
		spDescripcion.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		spDescripcion.setBounds(260, 269, 458, 261);
		layeredPaneBackground.add(spDescripcion);
		
		txtDescripcionCiclo = new JTextArea();
		txtDescripcionCiclo.setLineWrap(true);
		txtDescripcionCiclo.setWrapStyleWord(true);
		txtDescripcionCiclo.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 14));
		txtDescripcionCiclo.setBorder(null);
		spDescripcion.setViewportView(txtDescripcionCiclo);
		
		//JButton Botón Agregar Alumno
		btnModificarCiclo = new JButton("MODIFICAR CICLO");
		btnModificarCiclo.setActionCommand("modificarCicloDef");
		btnModificarCiclo.setBorder(null);
		btnModificarCiclo.setBackground(new Color(176, 224, 230));
		btnModificarCiclo.setHorizontalTextPosition(SwingConstants.CENTER);
		btnModificarCiclo.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 14));
		btnModificarCiclo.setBounds(391, 561, 207, 59);
		layeredPaneBackground.add(btnModificarCiclo);
		
		//Etiqueta Imagen Añadir Alumno
		lblImagenCiclo = new JLabel("");
		lblImagenCiclo.setIcon(new ImageIcon(GuiModificarCiclo.class.getResource("/images/addproject.png")));
		lblImagenCiclo.setBounds(34, 30, 60, 60);
		layeredPaneBackground.add(lblImagenCiclo);
		
		txtNombreCiclo = new JTextField();
		txtNombreCiclo.setColumns(10);
		txtNombreCiclo.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		txtNombreCiclo.setBounds(260, 181, 458, 33);
		layeredPaneBackground.add(txtNombreCiclo);
		
		lblDescripcinCicloFormativo = new JLabel("DESCRIPCI\u00D3N CICLO FORMATIVO");
		lblDescripcinCicloFormativo.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblDescripcinCicloFormativo.setBounds(361, 236, 261, 33);
		layeredPaneBackground.add(lblDescripcinCicloFormativo);
		
		//Etiqueta Nombre Alumno
		lblNombreCiclo = new JLabel("NOMBRE DEL CICLO FORMATIVO");
		lblNombreCiclo.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblNombreCiclo.setBounds(361, 147, 269, 33);
		layeredPaneBackground.add(lblNombreCiclo);
		
		//Etiqueta Alta Alumno
		lblModificarCiclo = new JLabel("MODIFICAR CICLO FORMATIVO");
		lblModificarCiclo.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 37));
		lblModificarCiclo.setBounds(104, 56, 570, 34);
		layeredPaneBackground.add(lblModificarCiclo);
		
		//JButton Back
		btnBack = new JButton("");
		btnBack.setContentAreaFilled(false);
		btnBack.setOpaque(false);
		btnBack.setPressedIcon(new ImageIcon(GuiModificarCiclo.class.getResource("/images/back.png")));
		btnBack.setSelectedIcon(new ImageIcon(GuiModificarCiclo.class.getResource("/images/back.png")));
		btnBack.setActionCommand("Volver");
		btnBack.setBorder(null);
		btnBack.setBorderPainted(false);
		btnBack.setBackground(new Color(255, 255, 255));
		btnBack.setIcon(new ImageIcon(GuiModificarCiclo.class.getResource("/images/back.png")));
		btnBack.setBounds(878, 30, 89, 61);
		layeredPaneBackground.add(btnBack);
		
		
		//JLabel Imagen Background
		lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(GuiModificarCiclo.class.getResource("/images/polybg.jpg")));
		lblBackground.setBounds(0, 0, 986, 685);
	
		layeredPaneBackground.add(lblBackground);
		setLayout(groupLayout);
		setBounds(0, 0, 986, 685);
	}
	
	public void setControlador(ControladorCiclos control, ControladorOtrosEventos controlEv) {
		btnBack.addActionListener(controlEv);
		btnModificarCiclo.addActionListener(control);
		
	}
	
	//Método que refleja los datos almacenados en el objeto Ciclo de la base de datos en los campos de texto
	public void mostrarCiclo(CicloFormativoPojo ciclo) {
		idCiclo = ciclo.getIdentificador();
		txtNombreCiclo.setText(ciclo.getNombre());
		txtDescripcionCiclo.setText(ciclo.getDescripcion());
	
	}
	
	//Método que almacena y retorna los datos consignados por el usuario en un objeto de tipo AlumnoPojo
	public CicloFormativoPojo getDatos() {
	
		CicloFormativoPojo ciclo = new CicloFormativoPojo(idCiclo, txtNombreCiclo.getText(), txtDescripcionCiclo.getText());
		
		return ciclo;
	}
	
	//Método que recicla los campos y los deja vacíos.
	public void reciclar() {
		txtNombreCiclo.setText("");
		txtDescripcionCiclo.setText("");
	}
}
