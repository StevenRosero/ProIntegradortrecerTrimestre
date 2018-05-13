package persistencia;

public class TableContracts {

	public static class AlumnosContracts {

		//Tabla Alumnos
		public static final String TABLA = "ALUMNOS";
		
		//Campos Absolutos para consultas complejas
		public static final String IDENTIFICADOR = "ALUMNOS.ID_A";
		public static final String EXPEDIENTE = "ALUMNOS.EXPEDIENTE";
		public static final String NOMBRE = "ALUMNOS.NOMBRE";
		public static final String APELLIDO1 = "ALUMNOS.APELLIDO1";
		public static final String APELLIDO2 = "ALUMNOS.APELLIDO2";
		
		//Campos Relativos para consultas simples, insert y updates
		public static final String IDENTIFICADOR_RELATIVO = "ID_A";
		public static final String EXPEDIENTE_RELATIVO = "EXPEDIENTE";
		public static final String NOMBRE_RELATIVO = "NOMBRE";
		public static final String APELLIDO1_RELATIVO = "APELLIDO1";
		public static final String APELLIDO2_RELATIVO = "APELLIDO2";
		
	}
	
	public static class CiclosContracts {
		
		//Tabla Ciclos Formativos
		public static final String TABLA = "CICLOS";
		
		//Campos Absolutos para consultas complejas
		public static final String IDENTIFICADOR = "CICLOS.ID_C";
		public static final String NOMBRE = "CICLOS.NOMBRE";
		public static final String DESCRIPCION = "CICLOS.DESCRIPCION";
		
		//Campos Relativos para consultas simples, insert y updates
		public static final String IDENTIFICADOR_RELATIVO = "ID_C";
		public static final String NOMBRE_RELATIVO = "NOMBRE";
		public static final String DESCRIPCION_RELATIVO = "DESCRIPCION";
	}
	
	public static class ProyectosContracts {
		
		//Tabla Proyectos (Foreign Key Ciclo referenciada a la tabla Ciclos)
		//Campos Absolutos para consultas complejas
		public static final String TABLA = "PROYECTOS";
		public static final String IDENTIFICADOR = "PROYECTOS.ID_P";
		public static final String NOMBRE = "PROYECTOS.NOMBRE";
		public static final String DESCRIPCION = "PROYECTOS.DESCRIPCION";
		public static final String URL = "PROYECTOS.URL";
		public static final String ANYO = "PROYECTOS.ANYO";
		public static final String NOTA = "PROYECTOS.NOTA";
		public static final String CICLO = "PROYECTOS.CICLO";
		public static final String CURSO = "PROYECTOS.CURSO";
		public static final String GRUPO = "PROYECTOS.GRUPO";
		public static final String IMAGEN = "PROYECTOS.IMAGEN";
		
		//Campos Relativos para consultas simples, insert y updates
		public static final String IDENTIFICADOR_RELATIVO = "ID_P";
		public static final String NOMBRE_RELATIVO = "NOMBRE";
		public static final String DESCRIPCION_RELATIVO = "DESCRIPCION";
		public static final String URL_RELATIVO = "URL";
		public static final String ANYO_RELATIVO = "ANYO";
		public static final String NOTA_RELATIVO = "NOTA";
		public static final String CICLO_RELATIVO = "CICLO";
		public static final String CURSO_RELATIVO = "CURSO";
		public static final String GRUPO_RELATIVO = "GRUPO";
		public static final String IMAGEN_RELATIVO = "IMAGEN";
		
	}
	
	public static class RealizanContracts {
		
		//Tabla Realizan (Foreign Keys ALUMNO y PROYECTO referenciadas a las tablas Alumnos y Proyectos)
		//Campos Absolutos para consultas complejas
		public static final String TABLA = "REALIZAN";
		public static final String ALUMNO = "REALIZAN.ALUMNO";
		public static final String PROYECTO = "REALIZAN.PROYECTO";
		
		//Campos Relativos para consultas simples, insert y updates
		public static final String ALUMNO_RELATIVO = "ALUMNO";
		public static final String PROYECTO_RELATIVO = "PROYECTO";
	}
	
	public static class UsuariosContracts {
		
		//Tabla Usuarios
		public static final String TABLA = "USUARIOS";
		public static final String USUARIO = "USUARIOS.USUARIO";
		public static final String PASSWORD = "USUARIOS.PASSWORD";
	}
}
