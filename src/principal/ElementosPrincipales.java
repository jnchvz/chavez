package principal;

import principal.entes.Jugador;
import principal.inventario.Inventario;
//import principal.mapas.Mapa;
import principal.mapas.MapaTiled;

public class ElementosPrincipales { //Principal elements
	public static MapaTiled mapa = new MapaTiled("/mapas/mapa-apocaliptico.json"); //Tiled map
	//public static Mapa mapa = new Mapa(Constantes.RUTA_MAPA);
	public static Jugador jugador = new Jugador(); //Player class
	public static Inventario inventario = new Inventario(); // Inventory class
}
