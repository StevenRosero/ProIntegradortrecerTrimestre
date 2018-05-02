package modelo;

public class CicloFormativoPojo {
	private int identificador;
	private String nombre;
	private String descripcion;
	
	public CicloFormativoPojo(int identificador, String nombre, String descripcion) {
		this.identificador = identificador;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public CicloFormativoPojo(int identificador) {
		this.identificador = identificador;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public int getIdentificador() {
		return identificador;
	}

	@Override
	public String toString() {
		return identificador  + " " + nombre;
	}	
}
