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

public class GuiModificarProyecto extends JPanel implements InterfazGui {
	private JLayeredPane layeredPaneBackground;
	private JLabel lblBackground;
	private JButton btnModificarProyecto;
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
	private JLabel lblModificarProyecto;
	private JButton btnUpload;
	private JLabel lblcargaImagen;
	private JList<AlumnoPojo> listEliminar;
	private DefaultListModel<AlumnoPojo> modelo;
	private DefaultListModel<AlumnoPojo> modeloNuevos;
	private JComboBox<String> comboBoxCurso;
	private DefaultComboBoxModel<CicloFormativoPojo> modeloCiclos;
	private JTextField txtNota;
	private byte[] imagenConvertida;
	private JScrollPane scrollPane;
	private JLabel lblEliminar;
	private JList<AlumnoPojo> listAgregar;
	private int idProyecto;
	
	public GuiModificarProyecto() {
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
		btnModificarProyecto = new JButton("MODIFICAR PROYECTO");
		btnModificarProyecto.setActionCommand("modificarProyecto");
		btnModificarProyecto.setBorder(null);
		btnModificarProyecto.setBackground(new Color(176, 224, 230));
		btnModificarProyecto.setHorizontalTextPosition(SwingConstants.CENTER);
		btnModificarProyecto.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 14));
		btnModificarProyecto.setBounds(713, 570, 207, 59);
		layeredPaneBackground.add(btnModificarProyecto);
		
		JLabel lblImagenProyecto = new JLabel("");
		lblImagenProyecto.setIcon(new ImageIcon(GuiModificarProyecto.class.getResource("/images/addproject.png")));
		lblImagenProyecto.setBounds(34, 30, 60, 60);
		layeredPaneBackground.add(lblImagenProyecto);
		
		//Etiqueta Nombre Proyecto
		lblNombreProyecto = new JLabel("NOMBRE DEL PROYECTO");
		lblNombreProyecto.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblNombreProyecto.setBounds(34, 121, 243, 33);
		layeredPaneBackground.add(lblNombreProyecto);
		
		//Etiqueta texto carga de imagen
		lblcargaImagen = new JLabel("<html><body style=text-align:'center'>Cargar una Imagen del Proyecto<br>(JPG tama\u00F1o 250x333)</html>");
		lblcargaImagen.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 12));
		lblcargaImagen.setBounds(489, 590, 187, 33);
		layeredPaneBackground.add(lblcargaImagen);
		
		//Etiqueta Alta Proyecto
		lblModificarProyecto = new JLabel("MODIFICAR PROYECTO");
		lblModificarProyecto.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 37));
		lblModificarProyecto.setBounds(104, 56, 458, 34);
		layeredPaneBackground.add(lblModificarProyecto);
		
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
		JLabel lblAgregar = new JLabel("<html><body style=text-align:'center'>ELIMINAR INTEGRANTES DEL PROYECTO<br>(Seleccione los que desee Eliminar)</html>");
		lblAgregar.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblAgregar.setBounds(513, 135, 308, 71);
		layeredPaneBackground.add(lblAgregar);
		
		//Etiqueta Descripcion
		JLabel lblDescripcion = new JLabel("DESCRIPCION");
		lblDescripcion.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblDescripcion.setBounds(36, 203, 164, 33);
		layeredPaneBackground.add(lblDescripcion);
		
		//JButton Back
		btnBack = new JButton("");
		btnBack.setContentAreaFilled(false);
		btnBack.setOpaque(false);
		btnBack.setPressedIcon(new ImageIcon(GuiModificarProyecto.class.getResource("/images/back.png")));
		btnBack.setSelectedIcon(new ImageIcon(GuiModificarProyecto.class.getResource("/images/back.png")));
		btnBack.setActionCommand("Volver");
		btnBack.setBorder(null);
		btnBack.setBorderPainted(false);
		btnBack.setBackground(new Color(255, 255, 255));
		btnBack.setIcon(new ImageIcon(GuiModificarProyecto.class.getResource("/images/back.png")));
		btnBack.setBounds(878, 30, 89, 61);
		layeredPaneBackground.add(btnBack);
		
		//Scroll Pane Descripcion
		scrollPaneDescripcion = new JScrollPane();
		scrollPaneDescripcion.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		scrollPaneDescripcion.setBounds(34, 239, 342, 85);
		layeredPaneBackground.add(scrollPaneDescripcion);
		
		//Campo Descripcion
		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setWrapStyleWord(true);
		textAreaDescripcion.setLineWrap(true);
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
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		scrollPane.setBounds(419, 420, 502, 120);
		layeredPaneBackground.add(scrollPane);
		
		modeloNuevos = new DefaultListModel<AlumnoPojo>();
		
		listAgregar = new JList();
		listAgregar.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		listAgregar.setBorder(null);
		listAgregar.setBackground(Color.WHITE);
		listAgregar.setModel(modeloNuevos);
		scrollPane.setViewportView(listAgregar);
		
		lblEliminar = new JLabel("<html><body style=text-align:'center'>AGREGAR INTEGRANTES AL PROYECTO<br>(Seleccione los que desee Agregar)</html>");
		lblEliminar.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblEliminar.setBounds(513, 355, 317, 71);
		layeredPaneBackground.add(lblEliminar);
		btnUpload.setContentAreaFilled(false);
		btnUpload.setBorderPainted(false);
		btnUpload.setIcon(new ImageIcon(GuiModificarProyecto.class.getResource("/images/upload.png")));
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
		scrollPaneIntegrantes.setBounds(418, 206, 502, 120);
		layeredPaneBackground.add(scrollPaneIntegrantes);
		
		//Lista Integrantes
		listEliminar = new JList();
		listEliminar.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		listEliminar.setBorder(null);
		listEliminar.setBackground(new Color(255, 255, 255));
		scrollPaneIntegrantes.setViewportView(listEliminar);
		
		//Inicializa el modelo de la lista JList
		modelo = new DefaultListModel<>();
		listEliminar.setModel(modelo);
		
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
		
		//Deshabilita la opción de editar el jspinner para evitar que el usuario introduzca texto
		((JSpinner.DefaultEditor) spinnerAnyo.getEditor()).getTextField().setEditable(false);
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
		lblBackground.setIcon(new ImageIcon(GuiModificarProyecto.class.getResource("/images/polybg.jpg")));
		lblBackground.setBounds(0, 0, 986, 685);
		layeredPaneBackground.add(lblBackground);
		setLayout(groupLayout);
		
		//Tamaño del Panel
		setBounds(0, 0, 986, 685);
	}
	
	public void setControlador(ControladorProyectos control, ControladorOtrosEventos controlEv) {
		btnBack.addActionListener(controlEv);
		btnUpload.addActionListener(control);
		btnModificarProyecto.addActionListener(control);
		
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
			imagenConvertida = Files.readAllBytes(archivo.toPath());
		}	
	}
	
	
	public ProyectoPojo getDatos() throws Exception {
		ArrayList<AlumnoPojo> listaAlumnos = new ArrayList<AlumnoPojo>();
		ArrayList<AlumnoPojo> listAlumnosEliminar = new ArrayList<AlumnoPojo>();

		String nombre = txtNombreProyecto.getText();
		String descripcion = textAreaDescripcion.getText();
		
		String url = txtUrl.getText();
		
		if (txtUrl.getText().equals("")) {
			url = "Pagina Web No Disponible";
		}
		
		int anyo = (int) spinnerAnyo.getValue();
		double nota = Double.parseDouble(txtNota.getText());
		CicloFormativoPojo ciclo = comboBoxCiclo.getItemAt(comboBoxCiclo.getSelectedIndex());
		
		String grupo =  comboBoxGrupo.getItemAt(comboBoxGrupo.getSelectedIndex());
		
		if (grupo == null) {
			grupo = "No Disponible";
		}
		
		int curso = Integer.parseInt(comboBoxCurso.getItemAt(comboBoxCurso.getSelectedIndex()));
		
		for (AlumnoPojo aux : listEliminar.getSelectedValuesList()) {
			listAlumnosEliminar.add(aux);
			System.out.println(aux);
		}
		
		for (AlumnoPojo aux : listAgregar.getSelectedValuesList()) {
			listaAlumnos.add(aux);
			System.out.println(aux);
		}
		
		if (listaAlumnos.isEmpty()) throw new Exception();
		
		ProyectoPojo proyecto = new ProyectoPojo(idProyecto, nombre, descripcion, url, anyo, nota, ciclo, curso, grupo, listaAlumnos, imagenConvertida, listAlumnosEliminar);
		imagenConvertida = null;
		
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
	
	//Método que muestra el resto de alumnos posibles para agregar al proyecto
	public void alumnosQueNoParticipan (ArrayList<AlumnoPojo> listAlumnos, ArrayList<AlumnoPojo> alumnosNuevos) {
		modeloNuevos.removeAllElements();
		boolean agregar = true;
		
		for (int i = 0; i < listAlumnos.size(); i++) {
			agregar = true;
			
			for (int j = 0; j < alumnosNuevos.size(); j++) {
				
				if (listAlumnos.get(i).getIdAlumno() == alumnosNuevos.get(j).getIdAlumno()) {
				agregar = false;
				}
			}
			
			if (agregar) {
				modeloNuevos.addElement(listAlumnos.get(i));
			}
		}
	}
	
	public void mostrarProyecto(ProyectoPojo proyecto) {
		idProyecto = proyecto.getIdProyecto();
		imagenConvertida = proyecto.getBlobImagen();
		
		int indexCurso;
		int indexGrupo;

		txtNombreProyecto.setText(proyecto.getNombre());
		textAreaDescripcion.setText(proyecto.getDescripcion());
		txtUrl.setText(proyecto.getUrl());
		spinnerAnyo.setValue(proyecto.getAnyo());
		txtNota.setText(proyecto.getNota() + "");
		
		indexCurso = indiceCurso(proyecto); 
		comboBoxCurso.setSelectedIndex(indexCurso);
		
		indexGrupo = indiceGrupo(proyecto);
		comboBoxGrupo.setSelectedIndex(indexGrupo);
		
		comprobarCiclo(proyecto);
	}
	
	private int indiceCurso(ProyectoPojo proyecto) {
		
		if (proyecto.getCurso() == 1) {
			return 0;
		} else if (proyecto.getCurso() == 2) {
			return 1;
		} else if (proyecto.getCurso() == 3) {
			return 2;
		} else if (proyecto.getCurso() == 4) {
			return 3;
		} else if (proyecto.getCurso() == 5) {
			return 4;
		} else {
			return 5;
		}
	}
	
	private int indiceGrupo(ProyectoPojo proyecto) {
		if (proyecto.getGrupo().equals("M1")) {
			return 0;
		} else if (proyecto.getGrupo().equals("M2")) {
			return 1;
		} else if (proyecto.getGrupo().equals("T1")) {
			return 2;
		} else {
			return 3;
		}
	}
	
	//Método que deja seleccionado el ciclo al que corresponde el proyecto a modificar en el comboBox
	private void comprobarCiclo(ProyectoPojo proyecto) {
		for (int i = 0; i < modeloCiclos.getSize(); i++) {
			if (modeloCiclos.getElementAt(i).getIdentificador() == proyecto.getCiclo().getIdentificador()) {
				comboBoxCiclo.setSelectedIndex(i);
			}
		}
	}
	
	public int getIdProyecto() {
		return idProyecto;
	}
}
