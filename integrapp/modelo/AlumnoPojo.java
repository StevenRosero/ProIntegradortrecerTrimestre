package modelo;

public class AlumnoPojo {
	private int idAlumno;
	private int expediente;
	private String nombre;
	private String apellido1;
	private String apellido2;
	
	public AlumnoPojo(int idAlumno, int expediente, String nombre, String apellido1, String apellido2) {
		this.idAlumno = idAlumno;
		this.expediente = expediente;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
	}
	
	
	
	public int getIdAlumno() {
		return idAlumno;
	}



	public int getExpediente() {
		return expediente;
	}



	public String getNombre() {
		return nombre;
	}



	public String getApellido1() {
		return apellido1;
	}



	public String getApellido2() {
		return apellido2;
	}

	@Override
	public String toString() {
		return "EXPEDIENTE: " + expediente + 
				"    NOMBRE: " + nombre + " " + apellido1 + " " + apellido2;
	}
}
