package principal;

//import java.awt.Font;

//import principal.herramientas.CargadorRecursos;


public class Constantes { //constants
	public static final int LADO_SPRITE = 32;  //Sprite side

	public static int ANCHO_JUEGO = 640; //width game
	public static int ALTO_JUEGO = 360; // height game

	// public static int ANCHO_PANTALLA_COMPLETA = 1920;
	// public static int ALTO_PANTALLA_COMPLETA = 1080;

	public static int ANCHO_PANTALLA_COMPLETA = 1280; // width full screen
	public static int ALTO_PANTALLA_COMPLETA = 720; // height full screen

	// 4:3

	public static double FACTOR_ESCALADO_X = (double) ANCHO_PANTALLA_COMPLETA / (double) ANCHO_JUEGO; //scale x
	public static double FACTOR_ESCALADO_Y = (double) ALTO_PANTALLA_COMPLETA / (double) ALTO_JUEGO; // scale y

	public static int CENTRO_VENTANA_X = ANCHO_JUEGO / 2; //center window
	public static int CENTRO_VENTANA_Y = ALTO_JUEGO / 2; // center window

	public static int MARGEN_X = ANCHO_JUEGO / 2 - LADO_SPRITE / 2; //margin x
	public static int MARGEN_Y = ALTO_JUEGO / 2 - LADO_SPRITE / 2; // margin y

	// paths
	public static String RUTA_MAPA = "/mapas/03.adm"; //map 
	public static String RUTA_ICONO_RATON = "/imagenes/iconos/iconoCursor.png"; // mouse icon
	public static String RUTA_PERSONAJE = "/imagenes/hojasPersonajes/2.png"; // character
	public static String RUTA_PERSONAJE_PISTOLA = "/imagenes/hojasPersonajes/4.png"; // gun
	public static String RUTA_ICONO_VENTANA = "/imagenes/iconos/iconoVentana.png"; //window logo
	public static String RUTA_LOGOTIPO = "/imagenes/iconos/logotipo.png"; // logo
	public static String RUTA_OBJETOS = "/imagenes/hojasObjetos/1.png"; // objects
	public static String RUTA_OBJETOS_ARMAS = "/imagenes/hojasObjetos/armas.png"; // weapons
	public static String RUTA_ENEMIGOS = "/imagenes/hojasEnemigos/"; // enemies
	public static String RUTA_OBJETOS_CURATIVOS = "/imagenes/hojasObjetos/1.png"; // objects healing
	public static String RUTA_ARMAS = "/imagenes/hojasObjetos/armas.png";// weapons

	//public static Font FUENTE_PIXEL = CargadorRecursos.cargarFuente("/fuentes/px10.ttf"); //font
}
