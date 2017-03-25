package principal.inventario;

import java.awt.Point;

public class ObjetoUnicoTiled { // unique tiled object
	
	private Point posicion; // point position
	private Objeto objeto; // object
	
	public ObjetoUnicoTiled(Point posicion, Objeto objeto) {
		this.posicion = posicion;
		this.objeto = objeto;
	}
	
	public Point obtenerPosicion() { // get position
		return posicion;
	}
	
	public Objeto obtenerObjeto() { // get object
		return objeto;
	}
}
