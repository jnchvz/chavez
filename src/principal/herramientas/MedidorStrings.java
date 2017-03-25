package principal.herramientas;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class MedidorStrings { // strings meter
	
	public static int medirAnchoPixeles(final Graphics g, final String s) { // measure width pixels
		FontMetrics fm = g.getFontMetrics();

		return fm.stringWidth(s);
	}

	public static int medirAltoPixeles(final Graphics g, final String s) { // measure height pixels
		FontMetrics fm = g.getFontMetrics();

		return (int) fm.getLineMetrics(s, g).getHeight();
	}

	public static Rectangle obtenerContorno(final Graphics g, final String s, final Point p) { // get contour
		FontMetrics fm = g.getFontMetrics();
		final int margen = 2; // margin

		final int ancho = fm.stringWidth(s); // width
		final int alto = (int) fm.getLineMetrics(s, g).getHeight(); // height

		return new Rectangle(p.x - margen, p.y - alto + margen, ancho + margen * 2, alto); // rectangle
	}
}
