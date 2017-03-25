package principal.herramientas;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class DatosDebug { // data debug
	
	private static ArrayList<String> datos = new ArrayList<String>();

	public static void enviarDato(final String dato) { // send data add
		datos.add(dato);
	}

	public static void vaciarDatos() { //clear
		datos.clear();
	}

	public static void dibujarDatos(final Graphics g) { // draw data
		g.setColor(Color.white);

		for (int i = 0; i < datos.size(); i++) {
			DibujoDebug.dibujarString(g, datos.get(i), 20, 40 + i * 10);
		}

		datos.clear();
	}
}

