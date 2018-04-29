package vistas;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.Font;
import controlador.ControladorOtrosEventos;
import controlador.ControladorProyectos;
import modelo.AlumnoPojo;
import modelo.CicloFormativoPojo;
import modelo.ProyectoPojo;

import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.ComboBoxUI;

import java.awt.ComponentOrientation;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Blob;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	private JComboBox<CicloFormativoPojo> comboBoxCiclo;
	private JLabel lblCurso;
	private JLabel lblGrupo;
	private JComboBox<String> comboBoxGrupo;
	private JButton btnBack;
	private JLabel lblAltaProyecto;
	private JButton btnUpload;
	private JLabel lblcargaImagen;
	private JList<AlumnoPojo> listaIntegrantes;
	private DefaultListModel<AlumnoPojo> modelo;
	private JComboBox<String> comboBoxCurso;
	private DefaultComboBoxModel<CicloFormativoPojo> modeloCiclos;
	private JTextField txtNota;
	private byte[] fileContents;
	
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
		
		//Inicializa la lista de Ciclos
		modeloCiclos = new DefaultComboBoxModel<CicloFormativoPojo>();
		
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
		btnAgregarProyecto.setActionCommand("agregarProyecto");
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
		
		//Etiqueta texto carga de imagen
		lblcargaImagen = new JLabel("<html><body style=text-align:'center'>Cargar una Imagen del Proyecto<br>(JPG tama\u00F1o 400x400)</html>");
		lblcargaImagen.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 12));
		lblcargaImagen.setBounds(489, 590, 187, 33);
		layeredPaneBackground.add(lblcargaImagen);
		
		//Etiqueta Alta Proyecto
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
		txtNombreProyecto.setBounds(34, 159, 342, 33);
		layeredPaneBackground.add(txtNombreProyecto);
		
		JLabel lblNota = new JLabel("NOTA");
		lblNota.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblNota.setBounds(331, 410, 45, 28);
		layeredPaneBackground.add(lblNota);
		
		txtNota = new JTextField();
		txtNota.setColumns(10);
		txtNota.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		txtNota.setBounds(261, 435, 115, 33);
		layeredPaneBackground.add(txtNota);
		
		//Campo Url
		txtUrl = new JTextField();
		txtUrl.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		txtUrl.setBounds(34, 366, 342, 33);
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
		scrollPaneDescripcion.setBounds(34, 239, 342, 85);
		layeredPaneBackground.add(scrollPaneDescripcion);
		
		//Campo Descripcion
		textAreaDescripcion = new JTextArea();
		scrollPaneDescripcion.setViewportView(textAreaDescripcion);
		
		//Evento Pulsar Botón Subir Imagen Proyecto
		btnUpload = new JButton("");
		btnUpload.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					agregarImagen();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
		listaIntegrantes = new JList();
		listaIntegrantes.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		listaIntegrantes.setBorder(null);
		listaIntegrantes.setBackground(new Color(255, 255, 255));
		scrollPaneIntegrantes.setViewportView(listaIntegrantes);
		
		//Inicializa el modelo de la lista JList
		modelo = new DefaultListModel<>();
		listaIntegrantes.setModel(modelo);
		
		//ComboBox Curso
		comboBoxCurso = new JComboBox<String>();
		comboBoxCurso.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6"}));
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
		spinnerAnyo.setBounds(35, 435, 117, 33);
		layeredPaneBackground.add(spinnerAnyo);
		
		
		//Etiqueta Grupo
		lblGrupo = new JLabel("GRUPO");
		lblGrupo.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblGrupo.setBounds(317, 556, 59, 28);
		layeredPaneBackground.add(lblGrupo);
		
		//ComboBox Grupo
		comboBoxGrupo = new JComboBox<String>();
		comboBoxGrupo.setModel(new DefaultComboBoxModel(new String[] {"M1", "M2", "T1", "T2"}));
		comboBoxGrupo.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		comboBoxGrupo.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		comboBoxGrupo.setBackground(Color.WHITE);
		comboBoxGrupo.setBounds(259, 584, 117, 39);
		layeredPaneBackground.add(comboBoxGrupo);
		
		//Combo Box Ciclo
		comboBoxCiclo = new JComboBox<CicloFormativoPojo>();
		comboBoxCiclo.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 11));
		comboBoxCiclo.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		comboBoxCiclo.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		comboBoxCiclo.setBackground(new Color(255, 255, 255));
		comboBoxCiclo.setBounds(37, 507, 339, 33);
		comboBoxCiclo.setModel(modeloCiclos);
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
	
	public void setControlador(ControladorProyectos control, ControladorOtrosEventos controlEv) {
		btnBack.addActionListener(controlEv);
		btnUpload.addActionListener(control);
		btnAgregarProyecto.addActionListener(control);
		
	}
	
	//Método que permite importar imagenes
	private void agregarImagen() throws IOException {
     
		//crea e inicializa el JFileChooser para elegir imagen del proyecto
		JFileChooser subirImagen = new JFileChooser();
		
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imágenes JPG", "jpg");
		subirImagen.setFileFilter(filtro);
		subirImagen.setAcceptAllFileFilterUsed(false);
		
		int valorRetorno = subirImagen.showOpenDialog(this);
		if (valorRetorno == JFileChooser.APPROVE_OPTION) {
			File archivo = new File(subirImagen.getSelectedFile().getPath());
			FileInputStream arch2 = new FileInputStream(archivo);
			fileContents = Files.readAllBytes(archivo.toPath());
		}	
	}
	
	
	public ProyectoPojo getDatos() throws FileNotFoundException, NumberFormatException {
		ArrayList<AlumnoPojo> listaAlumnos = new ArrayList<AlumnoPojo>();
		String nombre = txtNombreProyecto.getText();
		String descripcion = textAreaDescripcion.getText();
		String url = txtUrl.getText();
		int anyo = (int) spinnerAnyo.getValue();
		double nota = Double.parseDouble(txtNota.getText());
		CicloFormativoPojo ciclo = comboBoxCiclo.getItemAt(comboBoxCiclo.getSelectedIndex());
		String grupo =  comboBoxGrupo.getItemAt(comboBoxGrupo.getSelectedIndex());
		int curso = Integer.parseInt(comboBoxCurso.getItemAt(comboBoxCurso.getSelectedIndex()));
		byte[] imagen = null;
		
		if (fileContents == null) {
			System.out.println("Hola");
		} else {
			imagen = fileContents;
		}
		
		
		for (AlumnoPojo aux : listaIntegrantes.getSelectedValuesList()) {
			listaAlumnos.add(aux);
			System.out.println(aux);
		}
		
		ProyectoPojo proyecto = new ProyectoPojo(nombre, descripcion, url, anyo, nota, ciclo, curso, grupo, listaAlumnos,imagen);
		fileContents = null;
		
		return proyecto;
	}
	
	public void cargarModelo(ArrayList<AlumnoPojo> listaAlumnos) {
		modelo.removeAllElements();
		
		for (int i = 0; i < listaAlumnos.size(); i++) {
			modelo.addElement(listaAlumnos.get(i));
		}	
	}
	
	public void cargarCiclos(ArrayList<CicloFormativoPojo> listaCiclos) {
		modeloCiclos.removeAllElements();
		
		for (int i = 0; i < listaCiclos.size(); i++) {
			modeloCiclos.addElement(listaCiclos.get(i));
		}	
	}
}
