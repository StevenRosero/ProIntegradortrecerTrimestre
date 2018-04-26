package modelo;

import java.util.ArrayList;

public class ProyectoPojo {
	private String nombre;
	private String descripcion;
	private String url;
	private int anyo;
	private double nota;
	private CicloFormativoPojo ciclo;
	private int curso;
	private String grupo;
	private ArrayList<AlumnoPojo> listaAlumnos;
	private String imagen;
	
	public ProyectoPojo(String nombre, String descripcion, String url, int anyo, double nota, CicloFormativoPojo ciclo, int curso, String grupo,
			ArrayList<AlumnoPojo> listaAlumnos, String imagen) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.url = url;
		this.anyo = anyo;
		this.nota = nota;
		this.ciclo = ciclo;
		this.curso = curso;
		this.grupo = grupo;
		this.listaAlumnos = listaAlumnos;
		this.imagen = imagen;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getUrl() {
		return url;
	}

	public int getAnyo() {
		return anyo;
	}

	public int getCurso() {
		return curso;
	}
	
	public CicloFormativoPojo getCiclo() {
		return ciclo;
	}

	public String getGrupo() {
		return grupo;
	}

	public ArrayList<AlumnoPojo> getListaAlumnos() {
		return listaAlumnos;
	}

	public String getImagen() {
		return imagen;
	}

	@Override
	public String toString() {
		return "ProyectoPojo [nombre=" + nombre + ", descripcion=" + descripcion + ", url=" + url + ", anyo=" + anyo
				+ ", ciclo=" + ciclo + ", curso=" + curso + ", grupo=" + grupo + ", listaAlumnos=" + listaAlumnos
				+ ", imagen=" + imagen + "]";
	}
	
}
