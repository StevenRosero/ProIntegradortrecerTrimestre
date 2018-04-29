package vistas;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import modelo.AlumnoPojo;
import modelo.ProyectoPojo;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import controlador.ControladorOtrosEventos;
import controlador.ControladorProyectos;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Frame;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GuiModalDetalleProyecto extends JDialog implements InterfazGui{
	private DefaultListModel<AlumnoPojo> modelo;
	private JLabel lblImagenProyecto;
	private JButton btnCancelar;
	private JLabel lblIdentificador;
	private JTextArea txtDescripcion;
	private JLabel lblUrl;
	private JLabel lblAnyo;
	private JLabel lblNotaFinal;
	private JLabel lblAlumnosParticipantes;
	private JScrollPane scrollPaneAlumnos;
	private JLabel lblCiclo;
	private JLabel lblCurso;
	private JLabel lblGrupo;
	private JLabel lblCalificacion;
	private JList listAlumnos;
	private JLabel lblNombre;
	private JScrollPane scrollPaneDesc;
	private JLabel lblBarra;
	
	public GuiModalDetalleProyecto() {
		getContentPane().setBackground(new Color(255, 255, 255));
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
		
		// Titulo de la ventana
		setTitle("Detalles del Proyecto");

		// Tamaño del JDialog
		setBounds(100, 100, 800, 650);

		// label imagen papelera
		lblImagenProyecto = new JLabel("");
		lblImagenProyecto.setBounds(10, 11, 256, 334);
		lblImagenProyecto.setBorder(new LineBorder(new Color(176, 224, 230), 3));
		lblImagenProyecto.setIcon(new ImageIcon(GuiModalDetalleProyecto.class.getResource("/images/NOIMAGE.jpg")));

		btnCancelar = new JButton("VOLVER");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(520, 539, 167, 43);
		btnCancelar.setActionCommand("cancelarAlumno");
		btnCancelar.setFont(new Font("Avenir LT Std 45 Book", Font.PLAIN, 13));
		btnCancelar.setBorder(null);
		btnCancelar.setBackground(new Color(176, 224, 230));

		lblIdentificador = new JLabel("MODIFICAR ALUMNO");
		lblIdentificador.setBounds(284, 11, 297, 37);
		lblIdentificador.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 14));
		
		lblNombre = new JLabel("MODIFICAR ALUMNO");
		lblNombre.setBounds(284, 66, 476, 28);
		lblNombre.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 14));
		
		scrollPaneDesc = new JScrollPane();
		scrollPaneDesc.setBounds(284, 100, 476, 108);
		scrollPaneDesc.setBorder(new LineBorder(new Color(176, 224, 230), 3));
		
		lblUrl = new JLabel("MODIFICAR ALUMNO");
		lblUrl.setBounds(10, 570, 551, 30);
		lblUrl.setForeground(new Color(0, 204, 255));
		lblUrl.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 14));
		
		lblAnyo = new JLabel("MODIFICAR ALUMNO");
		lblAnyo.setBounds(284, 219, 224, 35);
		lblAnyo.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 14));
		
		lblNotaFinal = new JLabel("9.8");
		lblNotaFinal.setBounds(510, 446, 188, 82);
		lblNotaFinal.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotaFinal.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 66));
		
		lblAlumnosParticipantes = new JLabel("ALUMNOS PARTICIPANTES");
		lblAlumnosParticipantes.setBounds(10, 351, 256, 40);
		lblAlumnosParticipantes.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 14));
		
		scrollPaneAlumnos = new JScrollPane();
		scrollPaneAlumnos.setBounds(10, 397, 409, 167);
		scrollPaneAlumnos.setBorder(new LineBorder(new Color(176, 224, 230), 3));
		
		lblCiclo = new JLabel("MODIFICAR ALUMNO");
		lblCiclo.setBounds(284, 265, 422, 30);
		lblCiclo.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 14));
		
		lblCurso = new JLabel("MODIFICAR ALUMNO");
		lblCurso.setBounds(284, 315, 164, 30);
		lblCurso.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 14));
		
		lblGrupo = new JLabel("MODIFICAR ALUMNO");
		lblGrupo.setBounds(596, 315, 164, 30);
		lblGrupo.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 14));
		
		lblCalificacion = new JLabel("CALIFICACI\u00D3N");
		lblCalificacion.setBounds(522, 410, 164, 30);
		lblCalificacion.setHorizontalAlignment(SwingConstants.CENTER);
		lblCalificacion.setFont(new Font("Avenir LT Std 55 Roman", Font.PLAIN, 14));
		
		listAlumnos = new JList();
		scrollPaneAlumnos.setViewportView(listAlumnos);
		
		txtDescripcion = new JTextArea();
		txtDescripcion.setFocusable(false);
		scrollPaneDesc.setViewportView(txtDescripcion);

		// Inicializa el modelo de la lista
		modelo = new DefaultListModel<AlumnoPojo>();
		getContentPane().setLayout(null);
		getContentPane().add(scrollPaneAlumnos);
		getContentPane().add(lblNotaFinal);
		getContentPane().add(lblCalificacion);
		getContentPane().add(lblAlumnosParticipantes);
		getContentPane().add(lblImagenProyecto);
		getContentPane().add(lblCiclo);
		getContentPane().add(lblAnyo);
		getContentPane().add(lblNombre);
		getContentPane().add(lblIdentificador);
		getContentPane().add(lblCurso);
		getContentPane().add(lblGrupo);
		getContentPane().add(scrollPaneDesc);
		getContentPane().add(lblUrl);
		getContentPane().add(btnCancelar);
		
		lblBarra = new JLabel("");
		lblBarra.setBackground(new Color(176, 224, 230));
		lblBarra.setBounds(284, 41, 476, 9);
		getContentPane().add(lblBarra);

		// Centra el JDialog
		setLocationRelativeTo(null);

		// Aplica la propiedad Modal al Jdialog
		setModalityType(DEFAULT_MODALITY_TYPE);
		

		// Cierra la ventana al salir
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	@Override
	public void hacerVisible() {
		setVisible(true);
		
	}

	public void setControlador(ControladorProyectos control) {
		btnCancelar.addActionListener(control);
		
	}
	
	public void mostrar(ProyectoPojo proyecto) throws IOException {
		byte[] imageObtained= proyecto.getBlobImagen();
		ByteArrayInputStream bais = new ByteArrayInputStream(imageObtained);
		BufferedImage img = ImageIO.read(bais);

		reciclar();
		lblImagenProyecto.setIcon(new ImageIcon(img));
		lblIdentificador.setText("IDENTIFICADOR: " + proyecto.getIdProyecto() + "");
		lblNombre.setText("NOMBRE: " + proyecto.getNombre());
		txtDescripcion.setText(proyecto.getDescripcion());
		lblAnyo.setText("AÑO: " + proyecto.getAnyo() + "");
		lblCiclo.setText("CICLO FORMATIVO: " + proyecto.getCiclo().getNombre());
		lblCurso.setText("CURSO:  " + proyecto.getCurso() + "");
		lblGrupo.setText("GRUPO: " + proyecto.getGrupo());
		lblNotaFinal.setText(proyecto.getNota() + "");
		lblUrl.setText(proyecto.getUrl());
		hacerVisible();
	}

	// Método que recicla los elementos del modelo vaciando la lista
	private void reciclar() {
		modelo.removeAllElements();
	}
}
