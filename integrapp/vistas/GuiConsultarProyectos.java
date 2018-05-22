package vistas;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.Font;
import controlador.ControladorOtrosEventos;
import controlador.ControladorProyectos;
import modelo.CicloFormativoPojo;
import modelo.ProyectoPojo;
import javax.swing.border.LineBorder;
import java.awt.ComponentOrientation;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class GuiConsultarProyectos extends JPanel implements InterfazGui {
	private static final String TARDE_2 = "T2";
	private static final String TARDE_1 = "T1";
	private static final String MANYANA_2 = "M2";
	private static final String MANYANA_1 = "M1";
	private static final String ANYO_2 = "2";
	private static final String ANYO_1 = "1";
	private static final String FILTRAR_POR_EXPEDIENTE_DEL_ALUMNO = "Filtrar Por Expediente del Alumno";
	private static final String FILTRAR_POR_APELLIDO_DEL_ALUMNO = "Filtrar Por Apellido del Alumno";
	private static final String FILTRAR_POR_NOMBRE_DEL_ALUMNO = "Filtrar Por Nombre del Alumno";
	private static final String FILTRAR_POR_CICLO_DEL_PROYECTO = "Filtrar Por Ciclo del Proyecto";
	private static final String FILTRAR_POR_AÑO_DEL_PROYECTO = "Filtrar Por A\u00F1o del Proyecto";
	private static final String FILTRAR_POR_NOMBRE_DE_PROYECTO = "Filtrar Por Nombre de Proyecto";
	private static final String FILTRAR_POR_IDENTIFICADOR_DE_PROYECTO = "Filtrar Por Identificador de Proyecto";
	private static final String FILTRAR_POR_CURSO_Y_GRUPO_DE_PROYECTO = "Filtrar Por Curso o Grupo de Proyecto";
	private static final String VER_TODOS = "Ver Todos";
	private JLayeredPane layeredPaneBackground;
	private JLabel lblBackground;
	private JButton btnDetalles;
	private JTextField txtBuscar;
	private JLabel lblElegirFiltro;
	private JLabel lblOpcionFiltro;
	private JComboBox<String> comboBoxFiltro;
	private JButton btnBack;
	private JLabel lblAltaProyecto;
	private JList<ProyectoPojo> listaResultados;
	private DefaultListModel<ProyectoPojo> modelo;
	private DefaultComboBoxModel<CicloFormativoPojo> modeloCiclos;
	private JComboBox<CicloFormativoPojo> comboBoxOpciones;
	private JButton btnBuscar;
	private JLabel lblNumProyectos;
	private JLabel lblCurso;
	private JLabel lblGrupo;
	private JComboBox<String> comboCurso;
	private JComboBox<String> comboGrupo;
	
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
		
		//Jlabel Grupo
		lblGrupo = new JLabel("GRUPO");
		lblGrupo.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 12));
		lblGrupo.setBounds(245, 443, 43, 19);
		layeredPaneBackground.add(lblGrupo);
		
		//JLabel Curso
		lblCurso = new JLabel("CURSO");
		lblCurso.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 12));
		lblCurso.setBounds(45, 443, 49, 19);
		layeredPaneBackground.add(lblCurso);
		
		//ComboBox Cursos
		comboCurso = new JComboBox<String>();
		comboCurso.setModel(new DefaultComboBoxModel<String>(new String[] {VER_TODOS, ANYO_1, ANYO_2}));
		comboCurso.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		comboCurso.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		comboCurso.setBackground(Color.WHITE);
		comboCurso.setBounds(100, 431, 99, 31);
		layeredPaneBackground.add(comboCurso);
		
		//ComboBox Grupos
		comboGrupo = new JComboBox<String>();
		comboGrupo.setModel(new DefaultComboBoxModel<String>(new String[] {VER_TODOS, MANYANA_1, MANYANA_2, TARDE_1, TARDE_2}));
		comboGrupo.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		comboGrupo.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		comboGrupo.setBackground(Color.WHITE);
		comboGrupo.setBounds(298, 431, 99, 31);
		layeredPaneBackground.add(comboGrupo);
		
		//JButton buscar con el filtro seleccionado
		btnBuscar = new JButton("BUSCAR CON ESTE FILTRO");
		btnBuscar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnBuscar.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 14));
		btnBuscar.setBorder(null);
		btnBuscar.setBackground(new Color(176, 224, 230));
		btnBuscar.setActionCommand("btnFiltro");
		btnBuscar.setBounds(45, 501, 207, 40);
		layeredPaneBackground.add(btnBuscar);
		
		//JLabel Numero de proyectos encontrados
		lblNumProyectos = new JLabel("");
		lblNumProyectos.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumProyectos.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 14));
		lblNumProyectos.setBounds(479, 159, 473, 14);
		layeredPaneBackground.add(lblNumProyectos);
		
		//JLabel Introduzca el texto en el campo del filtro de búsqueda
		JLabel lblIntroduceTexto = new JLabel("Introduzca el texto aqu\u00ED");
		lblIntroduceTexto.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblIntroduceTexto.setBounds(45, 245, 182, 28);
		layeredPaneBackground.add(lblIntroduceTexto);
		
		//Campo Url
		txtBuscar = new JTextField();
		txtBuscar.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 12));
		txtBuscar.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		txtBuscar.setBounds(45, 276, 400, 33);
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
		
		//ComboBox opciones del filtro
		comboBoxOpciones = new JComboBox<CicloFormativoPojo>();
		comboBoxOpciones.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 11));
		comboBoxOpciones.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		comboBoxOpciones.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		comboBoxOpciones.setBackground(Color.WHITE);
		comboBoxOpciones.setBounds(45, 376, 400, 33);
		comboBoxOpciones.setEnabled(false);
		comboBoxOpciones.setModel(modeloCiclos);
		layeredPaneBackground.add(comboBoxOpciones);
		
		//Scroll Pane Integrantes
		JScrollPane scrollPaneResultados = new JScrollPane();
		scrollPaneResultados.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		scrollPaneResultados.setBounds(479, 186, 473, 355);
		layeredPaneBackground.add(scrollPaneResultados);
		
		//Lista Integrantes
		listaResultados = new JList<ProyectoPojo>();
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
		lblOpcionFiltro.setBounds(45, 342, 400, 28);
		layeredPaneBackground.add(lblOpcionFiltro);
		
		reciclarGrupoyCurso();
		
		//Combo Box Filtro
		comboBoxFiltro = new JComboBox<String>();
		comboBoxFiltro.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				if (comboBoxFiltro.getSelectedIndex() == 0) {
					reciclarGrupoyCurso();
					txtBuscar.setEnabled(false);
					comboBoxOpciones.setEnabled(false);
				
				} else if (comboBoxFiltro.getSelectedIndex() == 1) {
					reciclarGrupoyCurso();
					txtBuscar.setEnabled(true);
					comboBoxOpciones.setEnabled(false);
				
				} else if (comboBoxFiltro.getSelectedIndex() == 2) {
					reciclarGrupoyCurso();
					txtBuscar.setEnabled(true);
					comboBoxOpciones.setEnabled(false);
				
				} else if (comboBoxFiltro.getSelectedIndex() == 3) {
					reciclarGrupoyCurso();
					txtBuscar.setEnabled(true);
					comboBoxOpciones.setEnabled(false);
				
				} else if (comboBoxFiltro.getSelectedIndex() == 4) {
					txtBuscar.setEnabled(false);
					lblCurso.setVisible(true);
					lblGrupo.setVisible(true);
					comboBoxOpciones.setEnabled(true);
					comboGrupo.setEnabled(true);
					comboGrupo.setVisible(true);
					comboCurso.setVisible(true);
					comboCurso.setEnabled(true);
				
				} else if (comboBoxFiltro.getSelectedIndex() == 5) {
					reciclarGrupoyCurso();
					txtBuscar.setEnabled(true);
					comboBoxOpciones.setEnabled(false);
				
				} else if (comboBoxFiltro.getSelectedIndex() == 6) {
					reciclarGrupoyCurso();
					txtBuscar.setEnabled(true);
					comboBoxOpciones.setEnabled(false);
				
				} else if (comboBoxFiltro.getSelectedIndex() == 7) {
					reciclarGrupoyCurso();
					txtBuscar.setEnabled(true);
					comboBoxOpciones.setEnabled(false);
				
				} else if (comboBoxFiltro.getSelectedIndex() == 8) {
					reciclarGrupoyCurso();
					lblCurso.setVisible(true);
					lblGrupo.setVisible(true);
					comboBoxOpciones.setEnabled(false);
					comboGrupo.setEnabled(true);
					comboGrupo.setVisible(true);
					comboCurso.setVisible(true);
					comboCurso.setEnabled(true);
				}
			}
		});
		
		comboBoxFiltro.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 14));
		comboBoxFiltro.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		comboBoxFiltro.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		comboBoxFiltro.setBackground(new Color(255, 255, 255));
		comboBoxFiltro.setBounds(45, 186, 291, 33);
		comboBoxFiltro.setModel(new DefaultComboBoxModel<String>(new String[] {VER_TODOS, FILTRAR_POR_IDENTIFICADOR_DE_PROYECTO,
				FILTRAR_POR_NOMBRE_DE_PROYECTO, FILTRAR_POR_AÑO_DEL_PROYECTO, FILTRAR_POR_CICLO_DEL_PROYECTO, FILTRAR_POR_NOMBRE_DEL_ALUMNO, 
				FILTRAR_POR_APELLIDO_DEL_ALUMNO, FILTRAR_POR_EXPEDIENTE_DEL_ALUMNO, FILTRAR_POR_CURSO_Y_GRUPO_DE_PROYECTO}));
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
	
	//Método que ajusta el estado de los combobox Grupo y curso en función de los componentes seleccionados
	private void reciclarGrupoyCurso() {
		lblCurso.setVisible(false);
		lblGrupo.setVisible(false);
		comboCurso.setVisible(false);
		comboCurso.setEnabled(false);
		comboGrupo.setEnabled(false);
		comboGrupo.setVisible(false);
		comboGrupo.setSelectedIndex(0);
		comboCurso.setSelectedIndex(0);
	}
	
	public void setControlador(ControladorProyectos control, ControladorOtrosEventos controlEv) {
		btnBack.addActionListener(controlEv);
		btnBuscar.addActionListener(control);
		btnDetalles.addActionListener(control);
	}
	
	public String getDatos() throws Exception {
		int anyo = 0;
		int idProyecto = 0;
		int expediente = 0;
		String nombre = "";
		
		if (comboBoxFiltro.getSelectedIndex() == 0) {
			return String.valueOf(0);
		
		} else if (comboBoxFiltro.getSelectedIndex() == 1) {
			idProyecto = Integer.parseInt(txtBuscar.getText());
			return String.valueOf(idProyecto);
			
		} else if (comboBoxFiltro.getSelectedIndex() == 2) {
			nombre = txtBuscar.getText();
			if (nombre.equals("")) throw new Exception();
			
			return nombre;
		
		} else if (comboBoxFiltro.getSelectedIndex() == 3) {
			anyo = Integer.parseInt(txtBuscar.getText());
			return String.valueOf(anyo);
			
		} else if (comboBoxFiltro.getSelectedIndex() == 4) {
			return String.valueOf(modeloCiclos.getElementAt(comboBoxOpciones.getSelectedIndex()).getIdentificador());
		
		} else if (comboBoxFiltro.getSelectedIndex() == 5) {
			nombre = txtBuscar.getText();
			if (nombre.equals("")) throw new Exception();
			
			return nombre;
		
		} else if (comboBoxFiltro.getSelectedIndex() == 6) {
			nombre = txtBuscar.getText();
			if (nombre.equals("")) throw new Exception();
			
			return nombre;
			
		} else if (comboBoxFiltro.getSelectedIndex() == 7) {
			expediente = Integer.parseInt(txtBuscar.getText());
			return String.valueOf(expediente);
		
		} else {
			
			return FILTRAR_POR_CURSO_Y_GRUPO_DE_PROYECTO;
		}
	}
	
	public String getFiltro() {
		return comboBoxFiltro.getSelectedItem().toString();
	}
	
	public void cargarModeloProyectos(ArrayList<ProyectoPojo> listaProyectos) {
		modelo.removeAllElements();
		
		for (int i = 0; i < listaProyectos.size(); i++) {
			modelo.addElement(listaProyectos.get(i));
		}
		
		lblNumProyectos.setText("Se han encontrado " + modelo.size() + " proyectos.");
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
	
	public void reciclar() {
		lblNumProyectos.setText("");
		listaResultados.removeAll();
		modelo.removeAllElements();
	}
	
	
	public String getComboCurso() {
		return comboCurso.getSelectedItem().toString();
	}
	
	public String getComboGrupo() {
		return comboGrupo.getSelectedItem().toString();
	}
	
	public static String getFiltrarPorExpedienteDelAlumno() {
		return FILTRAR_POR_EXPEDIENTE_DEL_ALUMNO;
	}
	
	public static String getFiltrarPorApellidoDelAlumno() {
		return FILTRAR_POR_APELLIDO_DEL_ALUMNO;
	}
	public static String getFiltrarPorNombreDelAlumno() {
		return FILTRAR_POR_NOMBRE_DEL_ALUMNO;
	}
	
	public static String getFiltrarPorCicloDelProyecto() {
		return FILTRAR_POR_CICLO_DEL_PROYECTO;
	}
	
	public static String getFiltrarPorAñoDelProyecto() {
		return FILTRAR_POR_AÑO_DEL_PROYECTO;
	}
	
	public static String getFiltrarPorNombreDeProyecto() {
		return FILTRAR_POR_NOMBRE_DE_PROYECTO;
	}
	
	public static String getFiltrarPorIdentificadorDeProyecto() {
		return FILTRAR_POR_IDENTIFICADOR_DE_PROYECTO;
	}
	
	public static String getFiltrarPorCursoYGrupoDeProyecto() {
		return FILTRAR_POR_CURSO_Y_GRUPO_DE_PROYECTO;
	}
	
	public static String getTarde2() {
		return TARDE_2;
	}
	
	public static String getTarde1() {
		return TARDE_1;
	}
	
	public static String getManyana2() {
		return MANYANA_2;
	}
	
	public static String getManyana1() {
		return MANYANA_1;
	}
	
	public static String getAnyo2() {
		return ANYO_2;
	}
	
	public static String getAnyo1() {
		return ANYO_1;
	}
	
	public static String getVerTodos() {
		return VER_TODOS;
	}
}
