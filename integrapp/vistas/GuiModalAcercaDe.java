package vistas;
import javax.swing.*;
import java.awt.*;
import controlador.ControladorOtrosEventos;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class GuiModalAcercaDe extends JDialog implements InterfazGui {
	private JLabel lblLogo;
	private JButton btnCancelar;
	private JLabel lblUEM;
	private JLabel lblTitulo;
	private JLabel lblDevelopers;
	private JLabel lblVersion;
	private JLabel lblCopy;
	private JLabel lblNormativa;
	
	public GuiModalAcercaDe() {
		inicializar();
	}
	
	@Override
	public void inicializar() {
		try {
			UIManager.setLookAndFeel(
			UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setModalityType(DEFAULT_MODALITY_TYPE);
		// Titulo de la ventana
		setTitle("Acerca De Integra APP");

		// Tamaño del JDialog
		setBounds(100, 100, 650, 650);

		// label imagen papelera
		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(GuiModalAcercaDe.class.getResource("/images/logo_p.png")));

		btnCancelar = new JButton("CERRAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setActionCommand("cancelarCiclo");
		btnCancelar.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 13));
		btnCancelar.setBorder(null);
		btnCancelar.setBackground(new Color(176, 224, 230));

		lblUEM = new JLabel("Universidad Europea DAM M12");
		lblUEM.setFont(new Font("Dialog", Font.PLAIN, 10));
		
					lblTitulo = new JLabel("INTEGRA APP\r\n\r\n");
					lblTitulo.setFont(new Font("Dialog", Font.PLAIN, 13));
		
		lblVersion = new JLabel("Version: ( 1.0 )");
		lblVersion.setFont(new Font("Dialog", Font.PLAIN, 13));
		
		lblDevelopers = new JLabel("Developers : Raul Ordas , Jincheng Weng , Steven Rosero.");
		lblDevelopers.setFont(new Font("Dialog", Font.PLAIN, 13));
		
		lblCopy = new JLabel("(c) Copyright Integrama 2018.  All rights reserved.");
		lblCopy.setFont(new Font("Dialog", Font.PLAIN, 13));
		
		lblNormativa = new JLabel("Esta aplicación se ha creado para uso exclusivo de la universidad.");
		lblNormativa.setFont(new Font("Dialog", Font.PLAIN, 13));

		// Inicializa el Layout del JDialog
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(172)
					.addComponent(lblLogo)
					.addContainerGap(159, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(118)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNormativa)
						.addComponent(lblCopy)
						.addComponent(lblDevelopers)
						.addComponent(lblVersion)
						.addComponent(lblTitulo))
					.addContainerGap(125, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(231)
					.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(236, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(476, Short.MAX_VALUE)
					.addComponent(lblUEM)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblLogo, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTitulo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblVersion)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblDevelopers)
					.addGap(2)
					.addComponent(lblCopy)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNormativa)
					.addGap(114)
					.addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addComponent(lblUEM)
					.addGap(237))
		);
		
		getContentPane().setLayout(groupLayout);

		// Centra el JDialog
		setLocationRelativeTo(null);

		// Aplica la propiedad Modal al Jdialog
		setModalityType(DEFAULT_MODALITY_TYPE);

		// Cierra la ventana al salir
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}
	
	public void setControlador(ControladorOtrosEventos controlEv) {
		btnCancelar.addActionListener(controlEv);
	}

	@Override
	public void hacerVisible() {
		setVisible(true);

	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}
	
}
