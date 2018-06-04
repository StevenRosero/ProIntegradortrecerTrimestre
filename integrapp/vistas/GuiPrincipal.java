package vistas;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;
import controlador.*;

@SuppressWarnings("serial")
public class GuiPrincipal extends JFrame implements InterfazGui {
	private JMenuBar menuBar;
	private JMenu menuAlumnos;
	private JMenu menuCiclosFormativos;
	private JSeparator separador3;
	private JSeparator separador2;
	private JSeparator separador1;
	private JMenuItem submenuConsultarProyectos;
	private JMenuItem submenuAltaProyecto;
	private JMenuItem submenuBajaProyecto;
	private JMenuItem submenuModificarProyecto;
	private JMenuItem submenuAltaAlumnos;
	private JMenuItem submenuBajaAlumnos;
	private JMenuItem submenuModificarAlumnos;
	private JMenuItem submenuAltaCiclos;
	private JMenuItem submenuBajaCiclos;
	private JMenuItem submenuModificarCiclos;
	private JMenuItem submenuAcercaDe;
	private JScrollPane panelVistaActiva;
	private GuiAyuda ayuda;
	private JMenu menuAcercaDe;
	
	public GuiPrincipal() {
		super("IntegraApp - Gestión de Proyectos Integradores");
		inicializar();
	}
	
	public void hacerVisible() {
		setVisible(true);
	}

	@Override
	public void inicializar() {
		try {
			UIManager.setLookAndFeel(
			UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Icono de la ventana
		setIconImage(Toolkit.getDefaultToolkit().getImage(GuiPrincipal.class.getResource("/images/iconoVentana.png")));
		setResizable(false);
		getContentPane().setBackground(new Color(255, 255, 255));
		setBackground(new Color(255, 255, 255));
		
		//Cambia el color de la línea de los submenus
		UIManager.put("PopupMenu.border", new LineBorder(Color.WHITE));
		setBounds(0, 0, 1290, 758);
		getContentPane().setLayout(null);
		 
		 ayuda = new GuiAyuda();
		 ayuda.setOpaque(true);
		 ayuda.setBounds(0, 195, 284, 534);
		 getContentPane().add(ayuda);
		
		 //Barra Menu Principal
		 menuBar = new JMenuBar();
		 menuBar.setBounds(0, 0, 1284, 45);
		 getContentPane().add(menuBar);
		 menuBar.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		 menuBar.setBackground(Color.WHITE);
		 menuBar.setPreferredSize(new Dimension (100, 45));
		
		 //Menu Proyectos
		 JMenu menuProyectos = new JMenu("             PROYECTOS");
		 menuProyectos.setBorder(new LineBorder(new Color(255, 255, 255)));
		 menuProyectos.setForeground(Color.WHITE);
		 menuProyectos.setOpaque(true);
		 menuProyectos.setBackground(new Color(176, 224, 230));
		 menuProyectos.setHorizontalTextPosition(SwingConstants.LEADING);
		 menuProyectos.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 22));
		 menuProyectos.setPreferredSize(new Dimension(300,10));
		 menuBar.add(menuProyectos);
		 
		 //SubMenu Consultar Proyectos
		 submenuConsultarProyectos = new JMenuItem("CONSULTAR");
		 submenuConsultarProyectos.setActionCommand("consultarProyectos");
		 submenuConsultarProyectos.setPreferredSize(new Dimension(111, 40));
		 submenuConsultarProyectos.setBorderPainted(true);
		 submenuConsultarProyectos.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		 submenuConsultarProyectos.setForeground(new Color(255, 255, 255));
		 submenuConsultarProyectos.setOpaque(true);
		 submenuConsultarProyectos.setBackground(new Color(176, 224, 230));
		 submenuConsultarProyectos.setIconTextGap(9);
		 submenuConsultarProyectos.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 17));
		 menuProyectos.add(submenuConsultarProyectos);
		 
		 //SubMenu Alta Proyectos
		 submenuAltaProyecto = new JMenuItem("ALTA");
		 submenuAltaProyecto.setEnabled(false);
		 submenuAltaProyecto.setPreferredSize(new Dimension(71, 40));
		 submenuAltaProyecto.setBorderPainted(true);
		 submenuAltaProyecto.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		 submenuAltaProyecto.setOpaque(true);
		 submenuAltaProyecto.setIconTextGap(9);
		 submenuAltaProyecto.setForeground(Color.WHITE);
		 submenuAltaProyecto.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 17));
		 submenuAltaProyecto.setBackground(new Color(176, 224, 230));
		 menuProyectos.add(submenuAltaProyecto);
		 
		 //SubMenu Baja Proyectos
		 submenuBajaProyecto = new JMenuItem("BAJA");
		 submenuBajaProyecto.setActionCommand("submenuBajaProyecto");
		 submenuBajaProyecto.setEnabled(false);
		 submenuBajaProyecto.setPreferredSize(new Dimension(71, 40));
		 submenuBajaProyecto.setBorderPainted(true);
		 submenuBajaProyecto.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		 submenuBajaProyecto.setOpaque(true);
		 submenuBajaProyecto.setIconTextGap(9);
		 submenuBajaProyecto.setForeground(Color.WHITE);
		 submenuBajaProyecto.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 17));
		 submenuBajaProyecto.setBackground(new Color(176, 224, 230));
		 menuProyectos.add(submenuBajaProyecto);
		 
		 //SubMenu Modificar Proyectos
		 submenuModificarProyecto = new JMenuItem("MODIFICAR");
		 submenuModificarProyecto.setActionCommand("submenuModificarProyectos");
		 submenuModificarProyecto.setEnabled(false);
		 submenuModificarProyecto.setPreferredSize(new Dimension(150, 40));
		 submenuModificarProyecto.setBorderPainted(true);
		 submenuModificarProyecto.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		 submenuModificarProyecto.setOpaque(true);
		 submenuModificarProyecto.setIconTextGap(9);
		 submenuModificarProyecto.setForeground(Color.WHITE);
		 submenuModificarProyecto.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 17));
		 submenuModificarProyecto.setBackground(new Color(176, 224, 230));
		 menuProyectos.add(submenuModificarProyecto);
		 
		 //Separador
		 separador1 = new JSeparator();
		 separador1.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		 menuBar.add(separador1);
		 
		 //Menu Alumnos
		 menuAlumnos = new JMenu("               ALUMNOS");
		 menuAlumnos.setBorder(null);
		 menuAlumnos.setPreferredSize(new Dimension(300, 10));
		 menuAlumnos.setOpaque(true);
		 menuAlumnos.setHorizontalTextPosition(SwingConstants.LEADING);
		 menuAlumnos.setForeground(Color.WHITE);
		 menuAlumnos.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 22));
		 menuAlumnos.setBackground(new Color(176, 224, 230));
		 menuBar.add(menuAlumnos);
		 
		 //SubMenu Alta Alumnos
		 submenuAltaAlumnos = new JMenuItem("ALTA");
		 submenuAltaAlumnos.setActionCommand("panelAltaAlumnos");
		 submenuAltaAlumnos.setEnabled(false);
		 submenuAltaAlumnos.setPreferredSize(new Dimension(160, 40));
		 submenuAltaAlumnos.setOpaque(true);
		 submenuAltaAlumnos.setIconTextGap(9);
		 submenuAltaAlumnos.setForeground(Color.WHITE);
		 submenuAltaAlumnos.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 17));
		 submenuAltaAlumnos.setBorderPainted(true);
		 submenuAltaAlumnos.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		 submenuAltaAlumnos.setBackground(new Color(176, 224, 230));
		 menuAlumnos.add(submenuAltaAlumnos);
		 
		 //SubMenu Baja Alumnos
		 submenuBajaAlumnos = new JMenuItem("BAJA");
		 submenuBajaAlumnos.setEnabled(false);
		 submenuBajaAlumnos.setPreferredSize(new Dimension(160, 40));
		 submenuBajaAlumnos.setOpaque(true);
		 submenuBajaAlumnos.setIconTextGap(9);
		 submenuBajaAlumnos.setForeground(Color.WHITE);
		 submenuBajaAlumnos.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 17));
		 submenuBajaAlumnos.setBorderPainted(true);
		 submenuBajaAlumnos.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		 submenuBajaAlumnos.setBackground(new Color(176, 224, 230));
		 menuAlumnos.add(submenuBajaAlumnos);
		 
		 //SubMenu Modificar Alumnos
		 submenuModificarAlumnos = new JMenuItem("MODIFICAR");
		 submenuModificarAlumnos.setActionCommand("submenuModificarAlumno");
		 submenuModificarAlumnos.setEnabled(false);
		 submenuModificarAlumnos.setPreferredSize(new Dimension(160, 40));
		 submenuModificarAlumnos.setOpaque(true);
		 submenuModificarAlumnos.setIconTextGap(9);
		 submenuModificarAlumnos.setForeground(Color.WHITE);
		 submenuModificarAlumnos.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 17));
		 submenuModificarAlumnos.setBorderPainted(true);
		 submenuModificarAlumnos.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		 submenuModificarAlumnos.setBackground(new Color(176, 224, 230));
		 menuAlumnos.add(submenuModificarAlumnos);
		 
		 //Separador
		 separador2 = new JSeparator();
		 separador2.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		 menuBar.add(separador2);
		 
		 //Menu Ciclos Formativos
		 menuCiclosFormativos = new JMenu("     CICLOS FORMATIVOS");
		 menuCiclosFormativos.setBorder(null);
		 menuCiclosFormativos.setPreferredSize(new Dimension(300, 10));
		 menuCiclosFormativos.setOpaque(true);
		 menuCiclosFormativos.setHorizontalTextPosition(SwingConstants.LEADING);
		 menuCiclosFormativos.setForeground(Color.WHITE);
		 menuCiclosFormativos.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 22));
		 menuCiclosFormativos.setBackground(new Color(176, 224, 230));
		 menuBar.add(menuCiclosFormativos);
		 
		 //Submenu Alta Ciclos
		 submenuAltaCiclos = new JMenuItem("ALTA");
		 submenuAltaCiclos.setActionCommand("panelAltaCiclos");
		 submenuAltaCiclos.setPreferredSize(new Dimension(160, 40));
		 submenuAltaCiclos.setOpaque(true);
		 submenuAltaCiclos.setIconTextGap(9);
		 submenuAltaCiclos.setForeground(Color.WHITE);
		 submenuAltaCiclos.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 17));
		 submenuAltaCiclos.setEnabled(false);
		 submenuAltaCiclos.setBorderPainted(true);
		 submenuAltaCiclos.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		 submenuAltaCiclos.setBackground(new Color(176, 224, 230));
		 menuCiclosFormativos.add(submenuAltaCiclos);
		 
		 //Submenu Baja Ciclos
		 submenuBajaCiclos = new JMenuItem("BAJA");
		 submenuBajaCiclos.setActionCommand("panelBajaCiclos");
		 submenuBajaCiclos.setPreferredSize(new Dimension(160, 40));
		 submenuBajaCiclos.setOpaque(true);
		 submenuBajaCiclos.setIconTextGap(9);
		 submenuBajaCiclos.setForeground(Color.WHITE);
		 submenuBajaCiclos.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 17));
		 submenuBajaCiclos.setEnabled(false);
		 submenuBajaCiclos.setBorderPainted(true);
		 submenuBajaCiclos.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		 submenuBajaCiclos.setBackground(new Color(176, 224, 230));
		 menuCiclosFormativos.add(submenuBajaCiclos);
		 
		 //Submenu Modificar Ciclos
		 submenuModificarCiclos = new JMenuItem("MODIFICAR");
		 submenuModificarCiclos.setActionCommand("submenuModificarCiclos");
		 submenuModificarCiclos.setPreferredSize(new Dimension(160, 40));
		 submenuModificarCiclos.setOpaque(true);
		 submenuModificarCiclos.setIconTextGap(9);
		 submenuModificarCiclos.setForeground(Color.WHITE);
		 submenuModificarCiclos.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 17));
		 submenuModificarCiclos.setEnabled(false);
		 submenuModificarCiclos.setBorderPainted(true);
		 submenuModificarCiclos.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		 submenuModificarCiclos.setBackground(new Color(176, 224, 230));
		 menuCiclosFormativos.add(submenuModificarCiclos);
		 
		 //separador
		 separador3 = new JSeparator();
		 separador3.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		 menuBar.add(separador3);
		 
		 //Menu Acerca De
		 menuAcercaDe = new JMenu("             ACERCA DE");
		 menuAcercaDe.setBorder(null);
		 menuAcercaDe.setPreferredSize(new Dimension(300, 10));
		 menuAcercaDe.setOpaque(true);
		 menuAcercaDe.setHorizontalTextPosition(SwingConstants.LEADING);
		 menuAcercaDe.setForeground(Color.WHITE);
		 menuAcercaDe.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 22));
		 menuAcercaDe.setBackground(new Color(176, 224, 230));
		 menuBar.add(menuAcercaDe);
		 
		 submenuAcercaDe = new JMenuItem("Integra App");
		 submenuAcercaDe.setActionCommand("acercade");
		 submenuAcercaDe.setPreferredSize(new Dimension(160, 40));
		 submenuAcercaDe.setOpaque(true);
		 submenuAcercaDe.setIconTextGap(9);
		 submenuAcercaDe.setForeground(Color.WHITE);
		 submenuAcercaDe.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 17));
		 submenuAcercaDe.setBorderPainted(true);
		 submenuAcercaDe.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		 submenuAcercaDe.setBackground(new Color(176, 224, 230));
		 menuAcercaDe.add(submenuAcercaDe);
		
		 panelVistaActiva = new JScrollPane();
		 panelVistaActiva.setBorder(null);
		 panelVistaActiva.setBackground(new Color(255, 255, 255));
		 panelVistaActiva.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		 panelVistaActiva.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		 panelVistaActiva.setBounds(294, 44, 986, 685);
		 getContentPane().add(panelVistaActiva);
		
		//Centra la ventana
		setLocationRelativeTo(null);
		
		//Cierra la aplicación al pulsar la cruz
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);	
	}

	public void setControlador(ControladorProyectos controlProy, ControladorAlumnos controlAl,
			ControladorOtrosEventos controlEv, ControladorCiclos controlCiclos) {
		
		//Listeners Menu Proyectos
		submenuConsultarProyectos.addActionListener(controlProy);
		submenuAltaProyecto.addActionListener(controlProy);
		submenuBajaProyecto.addActionListener(controlProy);
		submenuModificarProyecto.addActionListener(controlProy);
		
		//Listeners Menu Alumnos
		submenuAltaAlumnos.addActionListener(controlAl);
		submenuBajaAlumnos.addActionListener(controlAl);
		submenuModificarAlumnos.addActionListener(controlAl);
		
		//Listeners Menu Ciclos
		submenuAltaCiclos.addActionListener(controlCiclos);
		submenuBajaCiclos.addActionListener(controlCiclos);
		submenuModificarCiclos.addActionListener(controlCiclos);
		
		//Listeners Otros Eventos
		submenuAcercaDe.addActionListener(controlEv);
	}
	
	public void esUsuarioAdministrador() {
		//Habilita los submenus de alta para el administrador
		getSubmenuAltaAlumnos().setEnabled(true);
		getSubmenuAltaProyecto().setEnabled(true);
		getSubmenuAltaCiclos().setEnabled(true);
		
		//Habilita los submenus de baja para el administrador
		getSubmenuBajaAlumnos().setEnabled(true);
		getSubmenuBajaProyecto().setEnabled(true);
		getSubmenuBajaCiclos().setEnabled(true);
		
		//Habilita los submenus de modificar para el administrador
		getSubmenuModificarAlumnos().setEnabled(true);
		getSubmenuModificarProyecto().setEnabled(true);
		getSubmenuModificarCiclos().setEnabled(true);
	}

	public JMenuItem getSubmenuAltaCiclos() {
		return submenuAltaCiclos;
	}

	public JMenuItem getSubmenuBajaCiclos() {
		return submenuBajaCiclos;
	}

	public JMenuItem getSubmenuModificarCiclos() {
		return submenuModificarCiclos;
	}

	public JMenuItem getSubmenuAltaProyecto() {
		return submenuAltaProyecto;
	}

	public JMenuItem getSubmenuBajaProyecto() {
		return submenuBajaProyecto;
	}

	public JMenuItem getSubmenuModificarProyecto() {
		return submenuModificarProyecto;
	}

	public JMenuItem getSubmenuAltaAlumnos() {
		return submenuAltaAlumnos;
	}

	public JMenuItem getSubmenuBajaAlumnos() {
		return submenuBajaAlumnos;
	}

	public JMenuItem getSubmenuModificarAlumnos() {
		return submenuModificarAlumnos;
	}
	
	public JMenuItem getSubmenuAcercaDe() {
		return submenuAcercaDe;
	}

	public void setPanel(JPanel panel) {
		panelVistaActiva.setViewportView(panel);
	}
}
