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

public class GuiConsultarProyectos extends JPanel implements InterfazGui {
	private JLayeredPane layeredPaneBackground;
	private JLabel lblBackground;
	private JButton btnDetalles;
	private JTextField txtBuscar;
	private JLabel lblElegirFiltro;
	private JLabel lblOpcionFiltro;
	private JComboBox<CicloFormativoPojo> comboBoxFiltro;
	private JButton btnBack;
	private JLabel lblAltaProyecto;
	private JList<ProyectoPojo> listaResultados;
	private DefaultListModel<ProyectoPojo> modelo;
	private DefaultComboBoxModel<CicloFormativoPojo> modeloCiclos;
	private JComboBox<CicloFormativoPojo> comboBoxOpciones;
	private JButton btnBuscar;
	
	public GuiConsultarProyectos() {
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
		btnDetalles = new JButton("VER DETALLES");
		btnDetalles.setActionCommand("verDetalles");
		btnDetalles.setBorder(null);
		btnDetalles.setBackground(new Color(176, 224, 230));
		btnDetalles.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDetalles.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 14));
		btnDetalles.setBounds(619, 569, 207, 59);
		layeredPaneBackground.add(btnDetalles);
		
		JLabel lblImagenProyecto = new JLabel("");
		lblImagenProyecto.setIcon(new ImageIcon(GuiConsultarProyectos.class.getResource("/images/addproject.png")));
		lblImagenProyecto.setBounds(34, 30, 60, 60);
		layeredPaneBackground.add(lblImagenProyecto);
		
		//Etiqueta Nombre Proyecto
		lblElegirFiltro = new JLabel("ELIJA UN FILTRO DE CONSULTA");
		lblElegirFiltro.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblElegirFiltro.setBounds(45, 159, 243, 28);
		layeredPaneBackground.add(lblElegirFiltro);
		
		//Etiqueta Alta Proyecto
		lblAltaProyecto = new JLabel("CONSULTAR PROYECTOS");
		lblAltaProyecto.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 37));
		lblAltaProyecto.setBounds(104, 56, 459, 34);
		layeredPaneBackground.add(lblAltaProyecto);
		
		btnBuscar = new JButton("BUSCAR CON ESTE FILTRO");
		btnBuscar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnBuscar.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 14));
		btnBuscar.setBorder(null);
		btnBuscar.setBackground(new Color(176, 224, 230));
		btnBuscar.setActionCommand("btnFiltro");
		btnBuscar.setBounds(45, 501, 207, 40);
		layeredPaneBackground.add(btnBuscar);
		
		JLabel lblIntroduceTexto = new JLabel("Introduzca el texto aqu\u00ED");
		lblIntroduceTexto.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblIntroduceTexto.setBounds(45, 268, 182, 28);
		layeredPaneBackground.add(lblIntroduceTexto);
		
		//Campo Url
		txtBuscar = new JTextField();
		txtBuscar.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 12));
		txtBuscar.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		txtBuscar.setBounds(45, 299, 400, 33);
		layeredPaneBackground.add(txtBuscar);
		txtBuscar.setColumns(10);
		txtBuscar.setEnabled(false);
		
		//JButton Back
		btnBack = new JButton("");
		btnBack.setContentAreaFilled(false);
		btnBack.setOpaque(false);
		btnBack.setPressedIcon(new ImageIcon(GuiConsultarProyectos.class.getResource("/images/back.png")));
		btnBack.setSelectedIcon(new ImageIcon(GuiConsultarProyectos.class.getResource("/images/back.png")));
		btnBack.setActionCommand("Volver");
		btnBack.setBorder(null);
		btnBack.setBorderPainted(false);
		btnBack.setBackground(new Color(255, 255, 255));
		btnBack.setIcon(new ImageIcon(GuiConsultarProyectos.class.getResource("/images/back.png")));
		btnBack.setBounds(878, 30, 89, 61);
		layeredPaneBackground.add(btnBack);
		
		comboBoxOpciones = new JComboBox<CicloFormativoPojo>();
		comboBoxOpciones.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 11));
		comboBoxOpciones.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		comboBoxOpciones.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		comboBoxOpciones.setBackground(Color.WHITE);
		comboBoxOpciones.setBounds(45, 414, 400, 33);
		comboBoxOpciones.setEnabled(false);
		comboBoxOpciones.setModel(modeloCiclos);
		layeredPaneBackground.add(comboBoxOpciones);
		
		//Scroll Pane Integrantes
		JScrollPane scrollPaneResultados = new JScrollPane();
		scrollPaneResultados.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		scrollPaneResultados.setBounds(479, 186, 473, 355);
		layeredPaneBackground.add(scrollPaneResultados);
		
		//Lista Integrantes
		listaResultados = new JList();
		listaResultados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaResultados.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 14));
		listaResultados.setBorder(null);
		listaResultados.setBackground(new Color(255, 255, 255));
		scrollPaneResultados.setViewportView(listaResultados);
		
		//Inicializa el modelo de la lista JList
		modelo = new DefaultListModel<>();
		listaResultados.setModel(modelo);
		
		//Etiqueta Año
		lblOpcionFiltro = new JLabel("Elija una opci\u00F3n para el filtro seleccionado si existe alguna disponible");
		lblOpcionFiltro.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 12));
		lblOpcionFiltro.setBounds(45, 380, 400, 28);
		layeredPaneBackground.add(lblOpcionFiltro);
		
		//Combo Box Ciclo
		comboBoxFiltro = new JComboBox<CicloFormativoPojo>();
		comboBoxFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBoxFiltro.getSelectedIndex() == 0) {
					txtBuscar.setEnabled(false);
					comboBoxOpciones.setEnabled(false);
				} else if (comboBoxFiltro.getSelectedIndex() == 1) {
					txtBuscar.setEnabled(true);
					comboBoxOpciones.setEnabled(false);
				} else if (comboBoxFiltro.getSelectedIndex() == 2) {
					txtBuscar.setEnabled(true);
					comboBoxOpciones.setEnabled(false);
				} else if (comboBoxFiltro.getSelectedIndex() == 3) {
					txtBuscar.setEnabled(true);
					comboBoxOpciones.setEnabled(false);
				} else if (comboBoxFiltro.getSelectedIndex() == 4) {
					txtBuscar.setEnabled(false);
					comboBoxOpciones.setEnabled(true);
				} else if (comboBoxFiltro.getSelectedIndex() == 5) {
					txtBuscar.setEnabled(true);
					comboBoxOpciones.setEnabled(false);
				} else if (comboBoxFiltro.getSelectedIndex() == 6) {
					txtBuscar.setEnabled(true);
					comboBoxOpciones.setEnabled(false);
				} else if (comboBoxFiltro.getSelectedIndex() == 7) {
					txtBuscar.setEnabled(true);
					comboBoxOpciones.setEnabled(false);
				}
			}
		});
		
		comboBoxFiltro.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 14));
		comboBoxFiltro.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		comboBoxFiltro.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		comboBoxFiltro.setBackground(new Color(255, 255, 255));
		comboBoxFiltro.setBounds(45, 186, 291, 33);
		comboBoxFiltro.setModel(new DefaultComboBoxModel(new String[] {"Ver Todos", "Filtrar Por Identificador de Proyecto", "Filtrar Por Nombre de Proyecto", "Filtrar Por A\u00F1o del Proyecto", "Filtrar Por Ciclo del Proyecto", "Filtrar Por Nombre del Alumno", "Filtrar Por Apellido del Alumno", "Filtrar Por Expediente del Alumno"}));
		layeredPaneBackground.add(comboBoxFiltro);
		
		//JLabel Imagen Background
		lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(GuiConsultarProyectos.class.getResource("/images/polybg.jpg")));
		lblBackground.setBounds(0, 0, 986, 685);
		layeredPaneBackground.add(lblBackground);
		setLayout(groupLayout);
		
		//Tamaño del Panel
		setBounds(0, 0, 986, 685);
	}
	
	public void setControlador(ControladorProyectos control, ControladorOtrosEventos controlEv) {
		btnBack.addActionListener(controlEv);
		btnBuscar.addActionListener(control);
		btnDetalles.addActionListener(control);
	}
	
	public String getDatos() throws NumberFormatException {
		int anyo = 0;
		int idProyecto = 0;
		int expediente = 0;
		String nombre = "";
		
		if (comboBoxFiltro.getSelectedIndex() == 0) {
			return "SELECT * FROM PROYECTOS";
		
		} else if (comboBoxFiltro.getSelectedIndex() == 1) {
			idProyecto = Integer.parseInt(txtBuscar.getText());
			return "SELECT * FROM PROYECTOS WHERE ID_P = " + idProyecto; 
			
		} else if (comboBoxFiltro.getSelectedIndex() == 2) {
			nombre = txtBuscar.getText();
			return "SELECT * FROM PROYECTOS WHERE NOMBRE LIKE '%" + nombre + "%'"; 
		
		} else if (comboBoxFiltro.getSelectedIndex() == 3) {
			anyo = Integer.parseInt(txtBuscar.getText());
			return "SELECT * FROM PROYECTOS WHERE ANYO = " + anyo;
			
		} else if (comboBoxFiltro.getSelectedIndex() == 4) {
			return "SELECT * FROM PROYECTOS WHERE CICLO = " + modeloCiclos.getElementAt(comboBoxOpciones.getSelectedIndex()).getIdentificador();
		
		} else if (comboBoxFiltro.getSelectedIndex() == 5) {
			nombre = txtBuscar.getText();
			return "SELECT * FROM PROYECTOS, ALUMNOS, REALIZAN"
					+ " WHERE REALIZAN.ALUMNO = ALUMNOS.ID_A"
					+ " AND REALIZAN.PROYECTO = PROYECTOS.ID_P AND ALUMNOS.NOMBRE LIKE '%" + nombre + "%'";
		
		} else if (comboBoxFiltro.getSelectedIndex() == 6) {
			nombre = txtBuscar.getText();
			return "SELECT * FROM PROYECTOS, ALUMNOS, REALIZAN WHERE"
					+ " REALIZAN.ALUMNO = ALUMNOS.ID_A AND REALIZAN.PROYECTO = PROYECTOS.ID_P"
					+ " AND ALUMNOS.APELLIDO1 LIKE '%" + nombre + "%'";
			
		} else {
			expediente = Integer.parseInt(txtBuscar.getText());
			return "SELECT * FROM PROYECTOS, ALUMNOS, REALIZAN"
					+ " WHERE REALIZAN.ALUMNO = ALUMNOS.ID_A AND REALIZAN.PROYECTO = PROYECTOS.ID_P "
					+ "AND ALUMNOS.EXPEDIENTE = " + expediente;
		}
	}
	
	
	public void cargarModeloProyectos(ArrayList<ProyectoPojo> listaProyectos) {
		modelo.removeAllElements();
		
		for (int i = 0; i < listaProyectos.size(); i++) {
			modelo.addElement(listaProyectos.get(i));
		}	
	}
	
	public void cargarCiclos(ArrayList<CicloFormativoPojo> listaCiclos) {
		modeloCiclos.removeAllElements();
		
		for (int i = 0; i < listaCiclos.size(); i++) {
			modeloCiclos.addElement(listaCiclos.get(i));
		}	
	}
	
	public ProyectoPojo proyectoSeleccionado() {
		ProyectoPojo proyecto = null;
		if (listaResultados.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "No Hay ningún proyecto disponible para mostrar");
		} else {
			proyecto = listaResultados.getSelectedValue();
		}
		return proyecto;
	}
}
