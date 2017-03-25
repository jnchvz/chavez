package principal.entes;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import principal.Constantes;
import principal.ElementosPrincipales;
import principal.control.GestorControles;
import principal.herramientas.DibujoDebug;
import principal.inventario.RegistroObjetos;
import principal.inventario.armas.Arma;
import principal.inventario.armas.Desarmado;
//import principal.sonido.Sonido;
import principal.sprites.HojaSprites;

public class Jugador { // player
	
	private double posicionX; // x position
	private double posicionY; // y position

	private int direccion; // direction

	private double velocidad = 1;

	private boolean enMovimiento; // in motion

	private HojaSprites hs; // sprite sheets

	private BufferedImage imagenActual; // actual image

	private final int ANCHO_JUGADOR = 16; // width player
	private final int ALTO_JUGADOR = 16; // height player

	private final Rectangle LIMITE_ARRIBA = new Rectangle(Constantes.CENTRO_VENTANA_X - ANCHO_JUGADOR / 2,
			Constantes.CENTRO_VENTANA_Y, ANCHO_JUGADOR, 1);
	private final Rectangle LIMITE_ABAJO = new Rectangle(Constantes.CENTRO_VENTANA_X - ANCHO_JUGADOR / 2,
			Constantes.CENTRO_VENTANA_Y + ALTO_JUGADOR, ANCHO_JUGADOR, 1);
	private final Rectangle LIMITE_IZQUIERDA = new Rectangle(Constantes.CENTRO_VENTANA_X - ANCHO_JUGADOR / 2,
			Constantes.CENTRO_VENTANA_Y, 1, ALTO_JUGADOR);
	private final Rectangle LIMITE_DERECHA = new Rectangle(Constantes.CENTRO_VENTANA_X + ANCHO_JUGADOR / 2,
			Constantes.CENTRO_VENTANA_Y, 1, ALTO_JUGADOR);

	private int animacion; // animation
	private int estado; // state

	public static final int RESISTENCIA_TOTAL = 600;
	private int resistencia = 600;
	private int recuperacion = 0;
	private final int RECUPERACION_MAXIMA = 300;
	private boolean recuperado = true;

	public int limitePeso = 70; // limit weight
	public int pesoActual = 30; // actual weight

	private AlmacenEquipo ae;

    private ArrayList<Rectangle> alcanceActual;
    
    public int puntos = 0;

	public Jugador() {
		posicionX = ElementosPrincipales.mapa.obtenerPosicionInicial().getX();
		posicionY = ElementosPrincipales.mapa.obtenerPosicionInicial().getY();

		direccion = 0;

		enMovimiento = false;

		hs = new HojaSprites(Constantes.RUTA_PERSONAJE, Constantes.LADO_SPRITE, false);

		imagenActual = hs.obtenerSprite(0).obtenerImagen();

		animacion = 0;
		estado = 0;

		ae = new AlmacenEquipo((Arma) RegistroObjetos.obtenerObjeto(599));

        alcanceActual = new ArrayList<>();
	}

	public void actualizar() { // update
        cambiarHojaSprites();
		gestionarVelocidadResistencia();
		cambiarAnimacionEstado();
		enMovimiento = false;
		determinarDireccion();
		animar();
        actualizarArmas();
	}
	
    private void actualizarArmas() { // update weapons
        if (ae.obtenerArma1() instanceof  Desarmado) {
            return;
        }
        calcularAlcanceAtaque();
        ae.obtenerArma1().actualizar();
    }

    private void calcularAlcanceAtaque() { // calculate attack scope
        alcanceActual = ae.obtenerArma1().obtenerAlcance(this);
    }

    private void cambiarHojaSprites() { // change sprite sheets
        if (ae.obtenerArma1() instanceof  Arma && !(ae.obtenerArma1() instanceof Desarmado)) {
            hs = new HojaSprites(Constantes.RUTA_PERSONAJE_PISTOLA, Constantes.LADO_SPRITE, false);
        }
    }

	private void gestionarVelocidadResistencia() { // manage speed resistance
		if (GestorControles.teclado.corriendo && resistencia > 0) {
			velocidad = 2;
			recuperado = false;
			recuperacion = 0;
		} else {
			velocidad = 1;
			if (!recuperado && recuperacion < RECUPERACION_MAXIMA) {
				recuperacion++;
			}
			if (recuperacion == RECUPERACION_MAXIMA && resistencia < 600) {
				resistencia++;
			}
		}
	}

	private void cambiarAnimacionEstado() { // change animation state
		if (animacion < 30) {
			animacion++;
		} else {
			animacion = 0;
		}

		if (animacion < 15) {
			estado = 1;
		} else {
			estado = 2;
		}
	}

	private void determinarDireccion() { // determine direction
		final int velocidadX = evaluarVelocidadX();
		final int velocidadY = evaluarVelocidadY();

		if (velocidadX == 0 && velocidadY == 0) {
			return;
		}

		if ((velocidadX != 0 && velocidadY == 0) || (velocidadX == 0 && velocidadY != 0)) {
			mover(velocidadX, velocidadY);
		} else {
			// left and up, izquierda y arriba
			if (velocidadX == -1 && velocidadY == -1) {
				if (GestorControles.teclado.izquierda.obtenerUltimaPulsacion() > GestorControles.teclado.arriba
						.obtenerUltimaPulsacion()) {
					mover(velocidadX, 0);
				} else {
					mover(0, velocidadY);
				}
			}
			// left and down, izquierda y abajo
			if (velocidadX == -1 && velocidadY == 1) {
				if (GestorControles.teclado.izquierda.obtenerUltimaPulsacion() > GestorControles.teclado.abajo
						.obtenerUltimaPulsacion()) {
					mover(velocidadX, 0);
				} else {
					mover(0, velocidadY);
				}
			}
			// right and up, derecha y arriba
			if (velocidadX == 1 && velocidadY == -1) {
				if (GestorControles.teclado.derecha.obtenerUltimaPulsacion() > GestorControles.teclado.arriba
						.obtenerUltimaPulsacion()) {
					mover(velocidadX, 0);
				} else {
					mover(0, velocidadY);
				}
			}
			// right and down, derecha y abajo
			if (velocidadX == 1 && velocidadY == 1) {
				if (GestorControles.teclado.derecha.obtenerUltimaPulsacion() > GestorControles.teclado.abajo
						.obtenerUltimaPulsacion()) {
					mover(velocidadX, 0);
				} else {
					mover(0, velocidadY);
				}
			}
		}
	}

	private int evaluarVelocidadX() { // evaluate speed x
		int velocidadX = 0;

		if (GestorControles.teclado.izquierda.estaPulsada() && !GestorControles.teclado.derecha.estaPulsada()) {
			velocidadX = -1;
		} else if (GestorControles.teclado.derecha.estaPulsada() && !GestorControles.teclado.izquierda.estaPulsada()) {
			velocidadX = 1;
		}

		return velocidadX;
	}

	private int evaluarVelocidadY() { // evaluate speed y
		int velocidadY = 0;

		if (GestorControles.teclado.arriba.estaPulsada() && !GestorControles.teclado.abajo.estaPulsada()) {
			velocidadY = -1;
		} else if (GestorControles.teclado.abajo.estaPulsada() && !GestorControles.teclado.arriba.estaPulsada()) {
			velocidadY = 1;
		}

		return velocidadY;
	}

	private void mover(final int velocidadX, final int velocidadY) { // move
		enMovimiento = true;

		cambiarDireccion(velocidadX, velocidadY); // change direction

		if (!fueraMapa(velocidadX, velocidadY)) { // out of the map
			if (velocidadX == -1 && !enColisionIzquierda(velocidadX)) {
				posicionX += velocidadX * velocidad;
				restarResistencia();
			}
			if (velocidadX == 1 && !enColisionDerecha(velocidadX)) {
				posicionX += velocidadX * velocidad;
				restarResistencia();
			}
			if (velocidadY == -1 && !enColisionArriba(velocidadY)) {
				posicionY += velocidadY * velocidad;
				restarResistencia();
			}
			if (velocidadY == 1 && !enColisionAbajo(velocidadY)) {
				posicionY += velocidadY * velocidad;
				restarResistencia();
			}
		}
	}

	private void restarResistencia() { // decrease resistance
		if (GestorControles.teclado.corriendo && resistencia > 0) {
			resistencia--;
		}
	}

	private boolean enColisionArriba(int velocidadY) { // in collision up
		for (int r = 0; r < ElementosPrincipales.mapa.areasColisionPorActualizacion.size(); r++) {
			final Rectangle area = ElementosPrincipales.mapa.areasColisionPorActualizacion.get(r);

			int origenX = area.x;
			int origenY = area.y + velocidadY * (int) velocidad + 3 * (int) velocidad;

			final Rectangle areaFutura = new Rectangle(origenX, origenY, area.width,
					area.height);

			if (LIMITE_ARRIBA.intersects(areaFutura)) {
				return true;
			}
		}

		return false;		
	}

	private boolean enColisionAbajo(int velocidadY) { // in collision down
		for (int r = 0; r < ElementosPrincipales.mapa.areasColisionPorActualizacion.size(); r++) {
			final Rectangle area = ElementosPrincipales.mapa.areasColisionPorActualizacion.get(r);

			int origenX = area.x;
			int origenY = area.y + velocidadY * (int) velocidad - 3 * (int) velocidad;

			final Rectangle areaFutura = new Rectangle(origenX, origenY, area.width,
					area.height);

			if (LIMITE_ABAJO.intersects(areaFutura)) {
				return true;
			}
		}

		return false;
	}

	private boolean enColisionIzquierda(int velocidadX) { // in collision left
		for (int r = 0; r < ElementosPrincipales.mapa.areasColisionPorActualizacion.size(); r++) {
			final Rectangle area = ElementosPrincipales.mapa.areasColisionPorActualizacion.get(r);

			int origenX = area.x + velocidadX * (int) velocidad + 3 * (int) velocidad;
			int origenY = area.y;

			final Rectangle areaFutura = new Rectangle(origenX, origenY, area.width,
					area.height);

			if (LIMITE_IZQUIERDA.intersects(areaFutura)) {
				return true;
			}
		}

		return false;
	}

	private boolean enColisionDerecha(int velocidadX) { // in collision right
		for (int r = 0; r < ElementosPrincipales.mapa.areasColisionPorActualizacion.size(); r++) {
			final Rectangle area = ElementosPrincipales.mapa.areasColisionPorActualizacion.get(r);

			int origenX = area.x + velocidadX * (int) velocidad - 3 * (int) velocidad;
			int origenY = area.y;

			final Rectangle areaFutura = new Rectangle(origenX, origenY, area.width,
					area.height);

			if (LIMITE_DERECHA.intersects(areaFutura)) {
				return true;
			}
		}

		return false;
	}

	private boolean fueraMapa(final int velocidadX, final int velocidadY) { // out of the map

		int posicionFuturaX = (int) posicionX + velocidadX * (int) velocidad;
		int posicionFuturaY = (int) posicionY + velocidadY * (int) velocidad;

		final Rectangle bordesMapa = ElementosPrincipales.mapa.obtenerBordes(posicionFuturaX, posicionFuturaY);

		final boolean fuera;

		if (LIMITE_ARRIBA.intersects(bordesMapa) || LIMITE_ABAJO.intersects(bordesMapa)
				|| LIMITE_IZQUIERDA.intersects(bordesMapa) || LIMITE_DERECHA.intersects(bordesMapa)) {
			fuera = false;
		} else {
			fuera = true;
		}

		return fuera; // out
	}

	private void cambiarDireccion(final int velocidadX, final int velocidadY) {
		if (velocidadX == -1) {
			direccion = 3;
		} else if (velocidadX == 1) {
			direccion = 2;
		}

		if (velocidadY == -1) {
			direccion = 1;
		} else if (velocidadY == 1) {
			direccion = 0;
		}
	}

	private void animar() { // animate
		if (!enMovimiento) {
			estado = 0;
			animacion = 0;
		}

		imagenActual = hs.obtenerSprite(direccion, estado).obtenerImagen();
	}

	public void dibujar(Graphics g) { // draw
		final int centroX = Constantes.ANCHO_JUEGO / 2 - Constantes.LADO_SPRITE / 2;
		final int centroY = Constantes.ALTO_JUEGO / 2 - Constantes.LADO_SPRITE / 2;

		DibujoDebug.dibujarImagen(g, imagenActual, centroX, centroY);
		
		/*
        if (!alcanceActual.isEmpty()) {
            g.setColor(Color.red);
            dibujarAlcance(g);
        }
        */
        //DibujoDebug.dibujarString(g, posicionX + "-" + posicionY,  centroX, centroY - 8);
	}

    //private void dibujarAlcance(final Graphics g) {
        //DibujoDebug.dibujarRectanguloRelleno(g, alcanceActual.get(0));
    //}

	public void establecerPosicionX(double posicionX) { // stablish position x
		this.posicionX = posicionX;
	}

	public void establecerPosicionY(double posicionY) {  // stablish position y
		this.posicionY = posicionY;
	}

	public double obtenerPosicionX() { // get position x
		return posicionX;
	}

	public double obtenerPosicionY() { // get position x
		return posicionY;
	}

	public int obtenerPosicionXInt() { // get position x int
		return (int) posicionX;
	}

	public int obtenerPosicionYInt() { // get position y int
		return (int) posicionY;
	}

	public int obtenerAncho() { // get width
		return ANCHO_JUGADOR;
	}

	public int obtenerAlto() { // get height
		return ALTO_JUGADOR;
	}

	public int obtenerResistencia() { // get resistance
		return resistencia;
	}

	public Rectangle obtener_LIMITE_ARRIBA() {
		return LIMITE_ARRIBA;
	}

    public AlmacenEquipo obtenerAlmacenEquipo() { // stock equipment
        return ae;
    }

    public int obtenerDireccion() {
        return direccion;
    }
    
    public Point obtenerPosicion() {
    	return new Point((int)posicionX, (int)posicionY);
    }
    
    public ArrayList<Rectangle> obtenerAlcanceActual() {
    	return  alcanceActual;
    }
}