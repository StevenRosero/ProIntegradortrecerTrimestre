package vistas;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.Font;
import controlador.ControladorIntegraApp;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.ComponentOrientation;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GuiAltaProyecto extends JPanel implements InterfazGui {
	private JLayeredPane layeredPaneBackground;
	private JLabel lblBackground;
	private JButton btnAgregarProyecto;
	private JTextField txtUrl;
	private JTextField txtNombreProyecto;
	private JLabel lblNombreProyecto;
	private JTextArea textAreaDescripcion;
	private JScrollPane scrollPaneDescripcion;
	private JLabel lblUrl;
	private JLabel lblAnyo;
	private JSpinner spinnerAnyo;
	private JLabel lblCiclo;
	private JComboBox comboBoxCiclo;
	private JLabel lblCurso;
	private JLabel lblGrupo;
	private JComboBox comboBoxGrupo;
	private JButton btnBack;
	private JLabel lblAltaProyecto;
	private JButton btnUpload;
	private JLabel lblcargaImagen;
	
	public GuiAltaProyecto() {
		inicializar();
	}
	public void hacerVisible() {
		setVisible(true);
	}
	@Override
	public void inicializar() {
		
		//Color Background Panel
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
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(layeredPaneBackground, GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE)
		);
		
		//JButton Botón Agregar Proyecto
		btnAgregarProyecto = new JButton("A\u00D1ADIR PROYECTO");
		btnAgregarProyecto.setBorder(null);
		btnAgregarProyecto.setBackground(new Color(176, 224, 230));
		btnAgregarProyecto.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAgregarProyecto.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 14));
		btnAgregarProyecto.setBounds(713, 570, 207, 59);
		layeredPaneBackground.add(btnAgregarProyecto);
		
		JLabel lblImagenProyecto = new JLabel("");
		lblImagenProyecto.setIcon(new ImageIcon(GuiAltaProyecto.class.getResource("/images/addproject.png")));
		lblImagenProyecto.setBounds(34, 30, 60, 60);
		layeredPaneBackground.add(lblImagenProyecto);
		
		//Etiqueta Nombre Proyecto
		lblNombreProyecto = new JLabel("NOMBRE DEL PROYECTO");
		lblNombreProyecto.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblNombreProyecto.setBounds(34, 121, 243, 33);
		layeredPaneBackground.add(lblNombreProyecto);
		
		lblcargaImagen = new JLabel("<html><body style=text-align:'center'>Cargar una Imagen del Proyecto<br>(JPG tama\u00F1o 400x400)</html>");
		lblcargaImagen.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 12));
		lblcargaImagen.setBounds(489, 590, 187, 33);
		layeredPaneBackground.add(lblcargaImagen);
		
		lblAltaProyecto = new JLabel("ALTA DE PROYECTO");
		lblAltaProyecto.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 37));
		lblAltaProyecto.setBounds(104, 56, 388, 34);
		layeredPaneBackground.add(lblAltaProyecto);
		
		//Etiqueta Url
		lblUrl = new JLabel("URL");
		lblUrl.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblUrl.setBounds(34, 335, 164, 33);
		layeredPaneBackground.add(lblUrl);
		
		//Campo Nombre Proyecto
		txtNombreProyecto = new JTextField();
		txtNombreProyecto.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		txtNombreProyecto.setColumns(10);
		txtNombreProyecto.setBounds(34, 159, 299, 33);
		layeredPaneBackground.add(txtNombreProyecto);
		
		//Campo Url
		txtUrl = new JTextField();
		txtUrl.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		txtUrl.setBounds(34, 366, 299, 33);
		layeredPaneBackground.add(txtUrl);
		txtUrl.setColumns(10);
		
		//Etiqueta Integrantes
		JLabel lblIntegrantes = new JLabel("<html><body style=text-align:'center'>INTEGRANTES DEL PROYECTO<br>(Pulse Ctrl + Alumno para realizar una selecci\u00F3n m\u00FAltiple)</html>");
		lblIntegrantes.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblIntegrantes.setBounds(483, 121, 380, 71);
		layeredPaneBackground.add(lblIntegrantes);
		
		//Etiqueta Descripcion
		JLabel lblDescripcion = new JLabel("DESCRIPCION");
		lblDescripcion.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblDescripcion.setBounds(36, 203, 164, 33);
		layeredPaneBackground.add(lblDescripcion);
		
		//JButton Back
		btnBack = new JButton("");
		btnBack.setContentAreaFilled(false);
		btnBack.setOpaque(false);
		btnBack.setPressedIcon(new ImageIcon(GuiAltaProyecto.class.getResource("/images/back.png")));
		btnBack.setSelectedIcon(new ImageIcon(GuiAltaProyecto.class.getResource("/images/back.png")));
		btnBack.setActionCommand("Volver");
		btnBack.setBorder(null);
		btnBack.setBorderPainted(false);
		btnBack.setBackground(new Color(255, 255, 255));
		btnBack.setIcon(new ImageIcon(GuiAltaProyecto.class.getResource("/images/back.png")));
		btnBack.setBounds(878, 30, 89, 61);
		layeredPaneBackground.add(btnBack);
		
		//Scroll Pane Descripcion
		scrollPaneDescripcion = new JScrollPane();
		scrollPaneDescripcion.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		scrollPaneDescripcion.setBounds(34, 239, 299, 85);
		layeredPaneBackground.add(scrollPaneDescripcion);
		
		//Campo Descripcion
		textAreaDescripcion = new JTextArea();
		scrollPaneDescripcion.setViewportView(textAreaDescripcion);
		
		//Evento Pulsar Botón Subir Imagen Proyecto
		btnUpload = new JButton("");
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				agregarImagen();
				
			}
		});
		
		
		btnUpload.setContentAreaFilled(false);
		btnUpload.setBorderPainted(false);
		btnUpload.setIcon(new ImageIcon(GuiAltaProyecto.class.getResource("/images/upload.png")));
		btnUpload.setHorizontalTextPosition(SwingConstants.CENTER);
		btnUpload.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 14));
		btnUpload.setBorder(null);
		btnUpload.setBackground(new Color(176, 224, 230));
		btnUpload.setBounds(419, 570, 60, 59);
		layeredPaneBackground.add(btnUpload);
		
		//Etiqueta Curso
		lblCurso = new JLabel("CURSO");
		lblCurso.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblCurso.setBounds(36, 556, 59, 28);
		layeredPaneBackground.add(lblCurso);
		
		//Scroll Pane Integrantes
		JScrollPane scrollPaneIntegrantes = new JScrollPane();
		scrollPaneIntegrantes.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		scrollPaneIntegrantes.setBounds(418, 206, 502, 315);
		layeredPaneBackground.add(scrollPaneIntegrantes);
		
		//Lista Integrantes
		JList<String> listaIntegrantes = new JList();
		listaIntegrantes.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		listaIntegrantes.setBorder(null);
		listaIntegrantes.setBackground(new Color(255, 255, 255));
		listaIntegrantes.setModel(new AbstractListModel() {
			String[] values = new String[] {"RAUL ORDAS FERNANDEZ", "JAVIER GUTIERREZ PEREZ", "MIGUEL ALVAREZ NU\u00D1EZ", "JUAN MARIA LOPEZ ESTEVEZ", "RAMON DE LA SERNA", "IRENE REAL FERRANDEZ", "LOLA ORTU\u00D1O JIMENEZ", "ANDRES PERES LOPEZ", "JUAN DE LOS MONTES Y LA TIERRA", "RAUL ORDAS FERNANDEZ", "RAUL ORDAS FERNANDEZ", "JAVIER GUTIERREZ PEREZ", "MIGUEL ALVAREZ NU\u00D1EZ", "JUAN MARIA LOPEZ ESTEVEZ", "RAMON DE LA SERNA", "IRENE REAL FERRANDEZ", "LOLA ORTU\u00D1O JIMENEZ", "ANDRES PERES LOPEZ", "JUAN DE LOS MONTES Y LA TIERRA", "JAVIER GUTIERREZ PEREZ", "MIGUEL ALVAREZ NU\u00D1EZ", "JUAN MARIA LOPEZ ESTEVEZ", "RAMON DE LA SERNA", "IRENE REAL FERRANDEZ", "LOLA ORTU\u00D1O JIMENEZ", "ANDRES PERES LOPEZ", "JUAN DE LOS MONTES Y LA TIERRA"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPaneIntegrantes.setViewportView(listaIntegrantes);
		
		
		//ComboBox Curso
		JComboBox comboBoxCurso = new JComboBox();
		comboBoxCurso.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		comboBoxCurso.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		comboBoxCurso.setBackground(Color.WHITE);
		comboBoxCurso.setBounds(36, 584, 117, 39);
		layeredPaneBackground.add(comboBoxCurso);
		
		//Etiqueta Año
		lblAnyo = new JLabel("A\u00D1O");
		lblAnyo.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblAnyo.setBounds(35, 410, 59, 28);
		layeredPaneBackground.add(lblAnyo);
		
		//Etiqueta Ciclo
		lblCiclo = new JLabel("CICLO");
		lblCiclo.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblCiclo.setBounds(37, 479, 59, 28);
		layeredPaneBackground.add(lblCiclo);
		
		//Spinner Año
		spinnerAnyo = new JSpinner();
		spinnerAnyo.setModel(new SpinnerNumberModel(2018, 1900, 2100, 1));
		spinnerAnyo.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		spinnerAnyo.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		spinnerAnyo.setBounds(35, 435, 154, 33);
		layeredPaneBackground.add(spinnerAnyo);
		
		//Etiqueta Grupo
		lblGrupo = new JLabel("GRUPO");
		lblGrupo.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblGrupo.setBounds(218, 556, 59, 28);
		layeredPaneBackground.add(lblGrupo);
		
		//ComboBox Grupo
		comboBoxGrupo = new JComboBox();
		comboBoxGrupo.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		comboBoxGrupo.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		comboBoxGrupo.setBackground(Color.WHITE);
		comboBoxGrupo.setBounds(218, 584, 117, 39);
		layeredPaneBackground.add(comboBoxGrupo);
		
		//Combo Box Ciclo
		comboBoxCiclo = new JComboBox();
		comboBoxCiclo.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		comboBoxCiclo.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		comboBoxCiclo.setBackground(new Color(255, 255, 255));
		comboBoxCiclo.setBounds(37, 507, 299, 33);
		layeredPaneBackground.add(comboBoxCiclo);
		
		//JLabel Imagen Background
		lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(GuiAltaProyecto.class.getResource("/images/polybg.jpg")));
		lblBackground.setBounds(0, 0, 986, 685);
		layeredPaneBackground.add(lblBackground);
		setLayout(groupLayout);
		
		//Tamaño del Panel
		setBounds(0, 0, 986, 685);
	}
	
	@Override
	public void setControlador(ControladorIntegraApp control) {
		btnBack.addActionListener(control);
		
	}
	
	//Método que permite importar imagenes
	private void agregarImagen() {
		//crea e inicializa el JFileChooser para elegir imagen del proyecto
		JFileChooser subirImagen = new JFileChooser();
		
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imágenes JPG", "jpg");
		subirImagen.setFileFilter(filtro);
		subirImagen.setAcceptAllFileFilterUsed(false);
		
		int valorRetorno = subirImagen.showOpenDialog(this);
		if (valorRetorno == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " + subirImagen.getSelectedFile().getName());
		}
	}
	
	
	public void getDatos() {
		//TODO
	}
}
