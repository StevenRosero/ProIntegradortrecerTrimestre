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
import javax.swing.table.DefaultTableModel;

public class GuiAltaProyectoAuxBorrar extends JPanel implements InterfazGui {
	private JLabel lblIconoInterrogacion;
	private JTextArea txtAyudaA;
	private JTextArea textAreaB;
	private JLabel lblCajaAyuda;
	private JLayeredPane layeredPaneBackground;
	private JLabel lblBackground;
	private JLayeredPane layeredPaneAyuda;
	private JButton btnConsultarProyectos;
	private JTextField textField;
	private JTextField textField_2;
	private JTable table;
	private DefaultTableModel modelo;
	
	public GuiAltaProyectoAuxBorrar() {
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
					.addComponent(layeredPaneAyuda, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(layeredPaneBackground, GroupLayout.DEFAULT_SIZE, 990, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(151, Short.MAX_VALUE)
					.addComponent(layeredPaneAyuda, GroupLayout.PREFERRED_SIZE, 534, GroupLayout.PREFERRED_SIZE))
				.addComponent(layeredPaneBackground, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE)
		);
		
		//JButton Botón Consultar Proyectos
		btnConsultarProyectos = new JButton("A\u00D1ADIR PROYECTO");
		btnConsultarProyectos.setBorder(null);
		btnConsultarProyectos.setBackground(new Color(176, 224, 230));
		btnConsultarProyectos.setHorizontalTextPosition(SwingConstants.CENTER);
		btnConsultarProyectos.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 14));
		btnConsultarProyectos.setBounds(569, 564, 207, 59);
		layeredPaneBackground.add(btnConsultarProyectos);
		
		JLabel lblNewLabel = new JLabel("Nombre Del Proyecto");
		lblNewLabel.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblNewLabel.setBounds(34, 217, 164, 33);
		layeredPaneBackground.add(lblNewLabel);
		
		JLabel lblUrl = new JLabel("URL");
		lblUrl.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblUrl.setBounds(34, 303, 164, 33);
		layeredPaneBackground.add(lblUrl);
		
		textField_2 = new JTextField();
		textField_2.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		textField_2.setColumns(10);
		textField_2.setBounds(34, 255, 299, 33);
		layeredPaneBackground.add(textField_2);
		
		textField = new JTextField();
		textField.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		textField.setBounds(34, 334, 299, 33);
		layeredPaneBackground.add(textField);
		textField.setColumns(10);
		
		JLabel lblCurso = new JLabel("CURSO");
		lblCurso.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblCurso.setBounds(34, 556, 59, 28);
		layeredPaneBackground.add(lblCurso);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		scrollPane.setBounds(406, 220, 525, 315);
		layeredPaneBackground.add(scrollPane);
		
		scrollPane.setViewportView(table);
		
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		comboBox_1.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		comboBox_1.setBackground(Color.WHITE);
		comboBox_1.setBounds(34, 584, 117, 39);
		layeredPaneBackground.add(comboBox_1);
		
		JLabel lblAo = new JLabel("A\u00D1O");
		lblAo.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblAo.setBounds(34, 384, 59, 28);
		layeredPaneBackground.add(lblAo);
		
		JLabel lblCiclo = new JLabel("CICLO");
		lblCiclo.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblCiclo.setBounds(34, 471, 59, 28);
		layeredPaneBackground.add(lblCiclo);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(2018, 1900, 2100, 1));
		spinner.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		spinner.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		spinner.setBounds(34, 409, 154, 33);
		layeredPaneBackground.add(spinner);
		
		JLabel lblGrupo = new JLabel("GRUPO");
		lblGrupo.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 16));
		lblGrupo.setBounds(216, 556, 59, 28);
		layeredPaneBackground.add(lblGrupo);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		comboBox_2.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		comboBox_2.setBackground(Color.WHITE);
		comboBox_2.setBounds(216, 584, 117, 39);
		layeredPaneBackground.add(comboBox_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		comboBox.setBorder(new LineBorder(new Color(176, 224, 230), 4));
		comboBox.setBackground(new Color(255, 255, 255));
		comboBox.setBounds(34, 499, 299, 33);
		layeredPaneBackground.add(comboBox);
		
		//JLabel Background
		lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon(GuiAltaProyectoAuxBorrar.class.getResource("/images/polybg.jpg")));
		lblBackground.setBounds(0, 0, 990, 685);
	
		layeredPaneBackground.add(lblBackground);
		
		//Imagen Icono Interrogación
		lblIconoInterrogacion = new JLabel("");
		lblIconoInterrogacion.setBounds(100, 40, 74, 74);
		layeredPaneAyuda.add(lblIconoInterrogacion);
		lblIconoInterrogacion.setIcon(new ImageIcon(GuiAltaProyectoAuxBorrar.class.getResource("/images/Interrogacion.png")));
		
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
		lblCajaAyuda.setBounds(0, 0, 284, 534);
		layeredPaneAyuda.add(lblCajaAyuda);
		lblCajaAyuda.setOpaque(true);
		lblCajaAyuda.setBackground(new Color(176, 224, 230));
		setLayout(groupLayout);
		setBounds(0, 0, 1280, 685);
	}
	
	@Override
	public void setControlador(ControladorIntegraApp control) {
		
		
	}
}
