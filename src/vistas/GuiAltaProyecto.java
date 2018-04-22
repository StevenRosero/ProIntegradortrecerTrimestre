package vistas;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import controlador.ControladorIntegraApp;
import javax.swing.border.LineBorder;
import java.awt.ComponentOrientation;

public class GuiAltaProyecto extends JPanel implements InterfazGui {
	private JLabel lblLogo;
	private JLabel lblIconoInterrogacion;
	private JTextArea txtAyudaA;
	private JTextArea textAreaB;
	private JLabel lblCajaAyuda;
	private JLayeredPane layeredPaneBackground;
	private JLabel lblBackground;
	private JLayeredPane layeredPaneAyuda;
	private JButton btnConsultarProyectos;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	public GuiAltaProyecto() {
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
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addComponent(layeredPaneAyuda, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(layeredPaneBackground, GroupLayout.DEFAULT_SIZE, 1003, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(layeredPaneAyuda, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 530, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(layeredPaneBackground, GroupLayout.PREFERRED_SIZE, 757, GroupLayout.PREFERRED_SIZE)
							.addGap(11))))
		);
		
		//JButton Botón Consultar Proyectos
		btnConsultarProyectos = new JButton("A\u00D1ADIR PROYECTO");
		btnConsultarProyectos.setBorder(null);
		btnConsultarProyectos.setBackground(new Color(176, 224, 230));
		btnConsultarProyectos.setHorizontalTextPosition(SwingConstants.CENTER);
		btnConsultarProyectos.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 14));
		btnConsultarProyectos.setBounds(572, 620, 207, 59);
		layeredPaneBackground.add(btnConsultarProyectos);
		
		JLabel lblNewLabel = new JLabel("Nombre Del Proyecto");
		lblNewLabel.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblNewLabel.setBounds(33, 263, 164, 33);
		layeredPaneBackground.add(lblNewLabel);
		
		//JLabel Imagen Logotipo
		lblLogo = new JLabel("");
		lblLogo.setBounds(346, 21, 303, 216);
		layeredPaneBackground.add(lblLogo);
		lblLogo.setIcon(new ImageIcon(GuiAltaProyecto.class.getResource("/images/logo_p.png")));
		
		JLabel lblUrl = new JLabel("URL");
		lblUrl.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblUrl.setBounds(33, 349, 164, 33);
		layeredPaneBackground.add(lblUrl);
		
		textField_2 = new JTextField();
		textField_2.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		textField_2.setColumns(10);
		textField_2.setBounds(33, 301, 299, 33);
		layeredPaneBackground.add(textField_2);
		
		textField = new JTextField();
		textField.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		textField.setBounds(33, 380, 299, 33);
		layeredPaneBackground.add(textField);
		textField.setColumns(10);
		
		JLabel lblCurso = new JLabel("CURSO");
		lblCurso.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblCurso.setBounds(33, 602, 59, 28);
		layeredPaneBackground.add(lblCurso);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		scrollPane.setBounds(486, 263, 373, 315);
		layeredPaneBackground.add(scrollPane);
		
		JList<String> list = new JList();
		list.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		list.setBorder(null);
		list.setBackground(new Color(255, 255, 255));
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"RAUL ORDAS FERNANDEZ", "JAVIER GUTIERREZ PEREZ", "MIGUEL ALVAREZ NU\u00D1EZ", "JUAN MARIA LOPEZ ESTEVEZ", "RAMON DE LA SERNA", "IRENE REAL FERRANDEZ", "LOLA ORTU\u00D1O JIMENEZ", "ANDRES PERES LOPEZ", "JUAN DE LOS MONTES Y LA TIERRA", "RAUL ORDAS FERNANDEZ", "RAUL ORDAS FERNANDEZ", "JAVIER GUTIERREZ PEREZ", "MIGUEL ALVAREZ NU\u00D1EZ", "JUAN MARIA LOPEZ ESTEVEZ", "RAMON DE LA SERNA", "IRENE REAL FERRANDEZ", "LOLA ORTU\u00D1O JIMENEZ", "ANDRES PERES LOPEZ", "JUAN DE LOS MONTES Y LA TIERRA", "JAVIER GUTIERREZ PEREZ", "MIGUEL ALVAREZ NU\u00D1EZ", "JUAN MARIA LOPEZ ESTEVEZ", "RAMON DE LA SERNA", "IRENE REAL FERRANDEZ", "LOLA ORTU\u00D1O JIMENEZ", "ANDRES PERES LOPEZ", "JUAN DE LOS MONTES Y LA TIERRA"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane.setViewportView(list);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		comboBox_1.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		comboBox_1.setBackground(Color.WHITE);
		comboBox_1.setBounds(33, 630, 117, 39);
		layeredPaneBackground.add(comboBox_1);
		
		JLabel lblAo = new JLabel("A\u00D1O");
		lblAo.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblAo.setBounds(33, 430, 59, 28);
		layeredPaneBackground.add(lblAo);
		
		JLabel lblCiclo = new JLabel("CICLO");
		lblCiclo.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblCiclo.setBounds(33, 517, 59, 28);
		layeredPaneBackground.add(lblCiclo);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(2018, 1900, 2100, 1));
		spinner.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		spinner.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		spinner.setBounds(33, 455, 154, 33);
		layeredPaneBackground.add(spinner);
		
		JLabel lblGrupo = new JLabel("GRUPO");
		lblGrupo.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblGrupo.setBounds(215, 602, 59, 28);
		layeredPaneBackground.add(lblGrupo);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		comboBox_2.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		comboBox_2.setBackground(Color.WHITE);
		comboBox_2.setBounds(215, 630, 117, 39);
		layeredPaneBackground.add(comboBox_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		comboBox.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		comboBox.setBackground(new Color(255, 255, 255));
		comboBox.setBounds(33, 545, 299, 33);
		layeredPaneBackground.add(comboBox);
		
		//JLabel Background
		lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(GuiAltaProyecto.class.getResource("/images/polybg.jpg")));
		lblBackground.setBounds(10, -11, 1003, 757);
	
		layeredPaneBackground.add(lblBackground);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(168, 362, 289, 28);
		layeredPaneBackground.add(textField_1);
		
		//Imagen Icono Interrogación
		lblIconoInterrogacion = new JLabel("");
		lblIconoInterrogacion.setBounds(100, 40, 74, 74);
		layeredPaneAyuda.add(lblIconoInterrogacion);
		lblIconoInterrogacion.setIcon(new ImageIcon(GuiAltaProyecto.class.getResource("/images/Interrogacion.png")));
		
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
		lblCajaAyuda.setBounds(0, 0, 275, 530);
		layeredPaneAyuda.add(lblCajaAyuda);
		lblCajaAyuda.setOpaque(true);
		lblCajaAyuda.setBackground(new Color(176, 224, 230));
		setLayout(groupLayout);
		setBounds(0, 0, 1180, 730);
	}
	
	@Override
	public void setControlador(ControladorIntegraApp control) {

		
	}
	
}
