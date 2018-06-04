package vistas;

@SuppressWarnings("serial")
public class ExpedienteException extends Exception {
	public ExpedienteException() {
		super("El Expediente ha de tener 7 Cifras");
	}
}
