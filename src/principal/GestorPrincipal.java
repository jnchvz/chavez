package principal;

import principal.control.GestorControles;
import principal.graficos.SuperficieDibujo;
import principal.graficos.Ventana;
import principal.mapas.MapaTiled;
import principal.maquinaestado.GestorEstados;
//import principal.sonido.Sonido;

public class GestorPrincipal { //Lead Manager
	private boolean enFuncionamiento = false;
	private String titulo;
	private int ancho;
	private int alto;

	public static SuperficieDibujo sd; //Surface draw
	private Ventana ventana; // window
	private GestorEstados ge; // states manager

	private static int fps = 0;  //frames per second
	private static int aps = 0; // updates per second
	
	//private Sonido musica = new Sonido("/sonidos/musica.wav"); // sound

	private GestorPrincipal(final String titulo, final int ancho, final int alto) { //lead manager
		this.titulo = titulo;
		this.ancho = ancho;
		this.alto = alto;
	}

	public static void main(String[] args) {  //main
		//Para OpenGL en Mac/Linux
		System.setProperty("sun.java2d.opengl", "True"); // in order to work in every operating system
		
		/*
		 * Para Directx en Windows
		 * System.setProperty("sun.java2d.d3d", "True");
		 * System.setProperty("sun.java2d.ddforcevram", "True");
		 */
		
		//System.setProperty("sun.java2d.transaccel", "True");
		
		//test MapaTiled mt = new MapaTiled("/mapas/mapa-apocaliptico.json");
		
		GestorPrincipal gp = new GestorPrincipal("Chavez RPG", Constantes.ANCHO_PANTALLA_COMPLETA,Constantes.ALTO_PANTALLA_COMPLETA); //window settings

		gp.iniciarJuego(); // start game
		gp.iniciarBuclePrincipal(); // start principal loop
	}

	private void iniciarJuego() { //StartGame
		enFuncionamiento = true;
		inicializar();
		//musica.repetir();
	}

	private void inicializar() { //Initialize
		sd = new SuperficieDibujo(ancho, alto);
		ventana = new Ventana(titulo, sd);
		ge = new GestorEstados(sd);
	}

	private void iniciarBuclePrincipal() {  //Initialize principal loop
		int actualizacionesAcumuladas = 0;
		int framesAcumulados = 0;

		final int NS_POR_SEGUNDO = 1000000000;
		final int APS_OBJETIVO = 60;
		final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO;

		long referenciaActualizacion = System.nanoTime();
		long referenciaContador = System.nanoTime();

		double tiempoTranscurrido;
		double delta = 0;

		while (enFuncionamiento) {  // while isWorking
			final long inicioBucle = System.nanoTime();

			tiempoTranscurrido = inicioBucle - referenciaActualizacion;
			referenciaActualizacion = inicioBucle;

			delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;

			while (delta >= 1) {
				actualizar();
				actualizacionesAcumuladas++;
				delta--;
			}

			dibujar(); //draw
			framesAcumulados++;

			if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO) {

				aps = actualizacionesAcumuladas; // accumulated updates per second
				fps = framesAcumulados; // accumulated frames per second

				actualizacionesAcumuladas = 0;
				framesAcumulados = 0;
				referenciaContador = System.nanoTime();
			}
		}
	}

	private void actualizar() {  //update method
		if (GestorControles.teclado.inventarioActivo) {
			ge.cambiarEstadoActual(1);
		} else {
			ge.cambiarEstadoActual(0);
		}

		ge.actualizar(); //update Controls Manager
		sd.actualizar(); // update Surface Draw
	}

	private void dibujar() {  // draw method
		sd.dibujar(ge);
	}

	public static int obtenerFPS() {  //get FPS
		return fps;
	}

	public static int obtenerAPS() { // getAPS
		return aps;
	}
}
