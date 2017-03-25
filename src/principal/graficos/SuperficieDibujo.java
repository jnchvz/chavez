package principal.graficos;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import principal.Constantes;
import principal.GestorPrincipal;
import principal.control.GestorControles;
import principal.control.Raton;
import principal.herramientas.DatosDebug;
import principal.herramientas.DibujoDebug;
import principal.maquinaestado.GestorEstados;

public class SuperficieDibujo extends Canvas { // surface draw
	private static final long serialVersionUID = -6227038142688953660L;

	private int ancho; // width
	private int alto; // height

	private Raton raton;

	public SuperficieDibujo(final int ancho, final int alto) { // surface draw
		this.ancho = ancho; // width
		this.alto = alto; //height

		this.raton = new Raton(this); // mouse

		setIgnoreRepaint(true);
		setCursor(raton.obtenerCursor());
		setPreferredSize(new Dimension(ancho, alto));
		addKeyListener(GestorControles.teclado);
		addMouseListener(raton);
		setFocusable(true);
		requestFocus();
	}

	public void actualizar() { // update
		raton.actualizar(this);
	}

	public void dibujar(final GestorEstados ge) { // draw
		final BufferStrategy buffer = getBufferStrategy();

		if (buffer == null) {
			createBufferStrategy(4);
			return;
		}

		final Graphics2D g = (Graphics2D) buffer.getDrawGraphics();

		DibujoDebug.reiniciarContadorObjetos(); // reinitialize object counter

		//g.setFont(Constantes.FUENTE_PIXEL);
		DibujoDebug.dibujarRectanguloRelleno(g, 0, 0, Constantes.ANCHO_PANTALLA_COMPLETA, Constantes.ALTO_PANTALLA_COMPLETA, Color.black);

		if (Constantes.FACTOR_ESCALADO_X != 1.0 || Constantes.FACTOR_ESCALADO_Y != 1.0) {
			g.scale(Constantes.FACTOR_ESCALADO_X, Constantes.FACTOR_ESCALADO_Y);
		}

		ge.dibujar(g);

		g.setColor(Color.white);

		DibujoDebug.dibujarString(g, "FPS: " + GestorPrincipal.obtenerFPS(), 20, 20);
		DibujoDebug.dibujarString(g, "APS: " + GestorPrincipal.obtenerAPS(), 20, 30);

		DatosDebug.enviarDato("ESCALA X: " + Constantes.FACTOR_ESCALADO_X);
		DatosDebug.enviarDato("ESCALA Y: " + Constantes.FACTOR_ESCALADO_Y);
		DatosDebug.enviarDato("OPF: " + DibujoDebug.obtenerContadorObjetos());

		if (GestorControles.teclado.debug) {
			DatosDebug.dibujarDatos(g);
		} else {
			DatosDebug.vaciarDatos();
		}

		Toolkit.getDefaultToolkit().sync();

		g.dispose();

		buffer.show();
	}

	public int obtenerAncho() {
		return ancho;
	}

	public int obtenerAlto() {
		return alto;
	}

	public Raton obtenerRaton() {
		return raton;
	}
}
