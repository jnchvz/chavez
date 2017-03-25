package principal.inventario;

import principal.inventario.armas.Desarmado;
import principal.inventario.armas.Pistola;
import principal.inventario.consumibles.Consumible;

public class RegistroObjetos { // objects register
	
	public static Objeto obtenerObjeto(final int idObjeto) { // get object

		Objeto objeto = null;

		switch (idObjeto) { // id object
		// 0-499: objects to eat
		case 0:
			objeto = new Consumible(idObjeto, "Manzana roja", "");
			break;
		case 1:
			objeto = new Consumible(idObjeto, "Manzana amarilla", "");
			break;
		case 2:
			objeto = new Consumible(idObjeto, "Zanahoria", "");
			break;
		case 3:
			objeto = new Consumible(idObjeto, "Galleta", "");
			break;
		case 4:
			objeto = new Consumible(idObjeto, "Limón rojizo", "");
			break;
		case 5:
			objeto = new Consumible(idObjeto, "Fruta verde", "");
			break;
		// 500 - 599: weapons
		case 500:
			objeto = new Pistola(idObjeto, "Default Black", "", 1, 3, false, true, 0.4);
			break;
		case 599:
			objeto = new Desarmado(idObjeto, "Desarmado", "", 0, 0);
			break;
		}

		return objeto; // object

	}
}
