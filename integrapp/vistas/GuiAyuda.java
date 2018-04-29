package vistas;

import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.Cursor;

public class GuiAyuda extends JPanel {
	public JLabel lblIconoInterrogacion;
	private JTextArea txtAyudaA;
	private JTextArea textAreaB;
	
	public GuiAyuda() {
		setBackground(new Color(176, 224, 230));
		
		//Imagen Icono Interrogación
		lblIconoInterrogacion = new JLabel("");
		lblIconoInterrogacion.setBounds(100, 40, 74, 74);
		lblIconoInterrogacion.setIcon(new ImageIcon(GuiAltaAlumno.class.getResource("/images/Interrogacion.png")));
				
		//Texto Ayuda A
		txtAyudaA = new JTextArea();
		txtAyudaA.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtAyudaA.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//Abre el navegador web del usuario y le dirige a la url
				if (Desktop.isDesktopSupported()){
					try {
						Desktop.getDesktop().browse(new URI("www.google.com"));
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Lo siento, no dispone de medios para navegar por internet");
						e.printStackTrace();
					} catch (URISyntaxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		txtAyudaA.setEditable(false);
		txtAyudaA.setForeground(new Color(255, 255, 255));
		txtAyudaA.setBounds(29, 152, 211, 89);
		txtAyudaA.setLineWrap(true);
		txtAyudaA.setOpaque(false);
		txtAyudaA.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 16));
		txtAyudaA.setText("Si necesitas\r\nayuda con la aplicaci\u00F3n\r\npuedes acceder a la wiki\r\nen el siguiente enlace:\r\nwww.wiki.com");
		
		//Texto AyudaB
		textAreaB = new JTextArea();
		textAreaB.setEditable(false);
		textAreaB.setText("O si lo prefieres, puedes\r\ncontactar con el equipo\r\nde desarrolladores\r\na trav\u00E9s de nuestro correo\r\nelectr\u00F3nico.\r\n\u00A1Estaremos encantados\r\nde ayudarte!\r\npepito@pepito.es");
		textAreaB.setOpaque(false);
		textAreaB.setLineWrap(true);
		textAreaB.setForeground(Color.WHITE);
		textAreaB.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 16));
		textAreaB.setBounds(26, 288, 211, 149);

		setBounds(0, 0, 284, 534);
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(29)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textAreaB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtAyudaA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(100)
							.addComponent(lblIconoInterrogacion)))
					.addContainerGap(44, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(45)
					.addComponent(lblIconoInterrogacion)
					.addGap(73)
					.addComponent(txtAyudaA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(55)
					.addComponent(textAreaB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(58, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		setVisible(true);
		}
}
