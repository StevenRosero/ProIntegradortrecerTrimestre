package vistas;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import controlador.ControladorOtrosEventos;

public class GuiPanelPrincipal extends JPanel implements InterfazGui {
	private static final String txtAdmin = "ID: ADMINISTRADOR";
	private JLabel lblLogo;
	private JLayeredPane layeredPaneBackground;
	private JLabel lblBackground;
	private JLabel lbltextoIntro;
	private JButton btnConsultarProyectos;
	private JLabel lblCandado;
	private JButton btnLogin;
	private JLabel lblIdentificacion;
	
	public GuiPanelPrincipal() {
		setBorder(null);
		inicializar();
	}
	public void hacerVisible() {
		setVisible(true);
	}
	@Override
	public void inicializar() {
		
		//Color Background del panel
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
		
		//JLabel imagen Candado
		lblCandado = new JLabel("");
		lblCandado.setIcon(new ImageIcon(GuiPanelPrincipal.class.getResource("/images/Candado.png")));
		lblCandado.setBounds(451, 484, 73, 79);
		layeredPaneBackground.add(lblCandado);
		
		//JLabel Texto Estado Identificación
		lblIdentificacion = new JLabel("ID: USUARIO P\u00DABLICO");
		lblIdentificacion.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 11));
		lblIdentificacion.setOpaque(true);
		lblIdentificacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdentificacion.setHorizontalTextPosition(SwingConstants.CENTER);
		lblIdentificacion.setForeground(new Color(255, 255, 255));
		lblIdentificacion.setBackground(new Color(0, 0, 0));
		lblIdentificacion.setBounds(364, 629, 248, 14);
		layeredPaneBackground.add(lblIdentificacion);
		
		//Jbutton Botón de Login
		btnLogin = new JButton("LOGIN");
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setActionCommand("LOGIN");
		btnLogin.setHorizontalTextPosition(SwingConstants.CENTER);
		btnLogin.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 14));
		btnLogin.setBorder(null);
		btnLogin.setBackground(new Color(0, 0, 0));
		btnLogin.setBounds(431, 574, 113, 33);
		layeredPaneBackground.add(btnLogin);
		
		//JButton Botón Consultar Proyectos
		btnConsultarProyectos = new JButton("CONSULTAR PROYECTOS");
		btnConsultarProyectos.setBorder(null);
		btnConsultarProyectos.setBackground(new Color(176, 224, 230));
		btnConsultarProyectos.setHorizontalTextPosition(SwingConstants.CENTER);
		btnConsultarProyectos.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 14));
		btnConsultarProyectos.setBounds(381, 401, 207, 59);
		layeredPaneBackground.add(btnConsultarProyectos);
		
		//JLabel Imagen Logotipo
		lblLogo = new JLabel("");
		lblLogo.setBounds(333, 45, 303, 216);
		layeredPaneBackground.add(lblLogo);
		lblLogo.setIcon(new ImageIcon(GuiPanelPrincipal.class.getResource("/images/logo_p.png")));
		
		//JLabel con Texto de Introducción centrado en html
		lbltextoIntro = DefaultComponentFactory.getInstance().createLabel("<html><body style=text-align:'center'>IntegraAPP es una aplicaci\u00F3n que te permite gestionar proyectos integradores.<br> Para acceder solo tienes que pulsar un elemento de la barra del menu superior o utilizar los accesos r\u00E1pidos a trav\u00E9s de los botones. Recuerda que para acceder a operaciones especiales como Agregar, Modificar o Eliminar, necesitas identificarte como superusuario <b>(ADMIN:1234)</b></html>");
		lbltextoIntro.setBounds(114, 258, 744, 132);
		layeredPaneBackground.add(lbltextoIntro);
		lbltextoIntro.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 19));
		lbltextoIntro.setHorizontalAlignment(SwingConstants.CENTER);
		lbltextoIntro.setHorizontalTextPosition(SwingConstants.CENTER);
		
		//JLabel Background
		lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(GuiPanelPrincipal.class.getResource("/images/polybg.jpg")));
		lblBackground.setBounds(2, 0, 984, 685);
		layeredPaneBackground.add(lblBackground);
		setLayout(groupLayout);
		
		//Tamaño del Panel
		setBounds(0, 0, 986, 685);
	}
	
	public void setControlador(ControladorOtrosEventos controlEventos) {
		btnLogin.addActionListener(controlEventos);
		
	}
	
	public JButton getBtnLogin() {
		return btnLogin;
	}
	
	public JLabel getLblIdentificacion() {
		return lblIdentificacion;
	}
	
	public void cambiarAdministrador() {
		getLblIdentificacion().setText(txtAdmin);
	}
}
