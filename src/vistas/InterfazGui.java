package vistas;

import controlador.ControladorIntegraApp;

public interface InterfazGui {
	
	public void inicializar();
	public void hacerVisible();
	public void setControlador(ControladorIntegraApp control);
}
