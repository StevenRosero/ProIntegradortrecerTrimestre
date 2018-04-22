package ejecutables;

import java.awt.EventQueue;

import vistas.GuiBajaAlumno;

public class DialogEjec {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				GuiBajaAlumno di = new GuiBajaAlumno();
				di.setVisible(true);
		
				
			}
		});

	}

}
