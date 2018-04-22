package vistas;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import controlador.ControladorIntegraApp;

public class GuiPanelPrincipal extends JPanel implements InterfazGui {
	private static final String txtAdmin = "ID: ADMINISTRADOR";
	private JLabel lblLogo;
	private JLabel lblIconoInterrogacion;
	private JTextArea txtAyudaA;
	private JTextArea textAreaB;
	private JLabel lblCajaAyuda;
	private JLayeredPane layeredPaneBackground;
	private JLabel lblBackground;
	private JLayeredPane layeredPaneAyuda;
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
		setBackground(new Color(255, 255, 255));
		
		//Panel por capas Contenedor Ayuda
		layeredPaneAyuda = new JLayeredPane();
		layeredPaneAyuda.setOpaque(true);
		
		//Panel por capas con el background y resto de componentes
		layeredPaneBackground = new JLayeredPane();
		
		//Layout
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(1)
					.addComponent(layeredPaneAyuda, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(layeredPaneBackground, GroupLayout.PREFERRED_SIZE, 987, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(148, Short.MAX_VALUE)
					.addComponent(layeredPaneAyuda, GroupLayout.PREFERRED_SIZE, 534, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(2)
					.addComponent(layeredPaneBackground, GroupLayout.PREFERRED_SIZE, 680, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
		lblBackground.setBounds(2, 0, 985, 679);
	
		layeredPaneBackground.add(lblBackground);
		
		//Imagen Icono Interrogación
		lblIconoInterrogacion = new JLabel("");
		lblIconoInterrogacion.setBounds(100, 40, 74, 74);
		layeredPaneAyuda.add(lblIconoInterrogacion);
		lblIconoInterrogacion.setIcon(new ImageIcon(GuiPanelPrincipal.class.getResource("/images/Interrogacion.png")));
		
		//Texto Ayuda A
		txtAyudaA = new JTextArea();
		txtAyudaA.setForeground(new Color(255, 255, 255));
		txtAyudaA.setBounds(29, 152, 211, 89);
		layeredPaneAyuda.add(txtAyudaA);
		txtAyudaA.setLineWrap(true);
		txtAyudaA.setOpaque(false);
		txtAyudaA.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 16));
		txtAyudaA.setText("Si necesitas\r\nayuda con la aplicaci\u00F3n\r\npuedes acceder a la wiki\r\nen el siguiente enlace:\r\nwww.wiki.com");
		
		//Texto AyudaB
		textAreaB = new JTextArea();
		textAreaB.setText("O si lo prefieres, puedes\r\ncontactar con el equipo\r\nde desarrolladores\r\na trav\u00E9s de nuestro correo\r\nelectr\u00F3nico.\r\n\u00A1Estaremos encantados\r\nde ayudarte!\r\npepito@pepito.es");
		textAreaB.setOpaque(false);
		textAreaB.setLineWrap(true);
		textAreaB.setForeground(Color.WHITE);
		textAreaB.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 16));
		textAreaB.setBounds(26, 288, 211, 149);
		layeredPaneAyuda.add(textAreaB);
		
		//Container Ayuda
		lblCajaAyuda = new JLabel("");
		lblCajaAyuda.setBounds(0, 0, 284, 535);
		layeredPaneAyuda.add(lblCajaAyuda);
		lblCajaAyuda.setOpaque(true);
		lblCajaAyuda.setBackground(new Color(176, 224, 230));
		setLayout(groupLayout);
		setBounds(0, 0, 1280, 685);
	}
	
	@Override
	public void setControlador(ControladorIntegraApp control) {
		btnLogin.addActionListener(control);
		
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
