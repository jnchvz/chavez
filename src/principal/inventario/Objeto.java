package principal.inventario;


import java.awt.Rectangle;

import principal.sprites.Sprite;

public abstract class Objeto { //object
	
	// public static HojaSprites hojaObjetos = new
		// HojaSprites(Constantes.RUTA_OBJETOS, Constantes.LADO_SPRITE, false);

		protected final int id;
		protected final String nombre; //name
		protected final String descripcion; // description

		protected int cantidad; // quantity
		protected int cantidadMaxima; // maximal quantity

		protected Rectangle posicionMenu; // menu position
		protected Rectangle posicionFlotante; // float position

		public Objeto(final int id, final String nombre, final String descripcion) {
			this.id = id;
			this.nombre = nombre;
			this.descripcion = descripcion;

			cantidad = 0;
			cantidadMaxima = 99;

			posicionMenu = new Rectangle(0, 0, 0, 0);
			posicionFlotante = new Rectangle(0, 0, 0, 0);
		}

		public Objeto(final int id, final String nombre, final String descripcion, final int cantidad) {
			this(id, nombre, descripcion);
			if (cantidad <= cantidadMaxima) {
				this.cantidad = cantidad;
			}
		}

		public abstract Sprite obtenerSprite(); // get sprite

		public boolean incrementarCantidad(final int incremento) { // increment quantity
			boolean incrementado = false;

			if (cantidad + incremento <= cantidadMaxima) {
				cantidad += incremento;
				incrementado = true;
			}

			return incrementado; // incremented
		}

		public boolean reducirCantidad(final int reduccion) { // reduce quantity
			boolean reducido = false;

			if (cantidad - reduccion >= 0) {
				cantidad -= reduccion;
				reducido = true;
			}

			return reducido; // reduced
		}

		public int obtenerCantidad() { // get quantity
			return cantidad;
		}

		public int obtenerId() { // get id
			return id;
		}

		public String obtenerNombre() { // get name
			return nombre;
		}

		public String obtenerDescripcion() { // get description
			return descripcion;
		}

		public Rectangle obtenerPosicionMenu() { // get menu position
			return posicionMenu;
		}

		public Rectangle obtenerPosicionFlotante() { // get float position
			return posicionFlotante;
		}

		public void establecerPosicionMenu(final Rectangle posicionMenu) { // stablish menu posiiton
			this.posicionMenu = posicionMenu;
		}

		public void establecerPosicionFlotante(final Rectangle posicionFlotante) { // stablish float position
			this.posicionFlotante = posicionFlotante;
		}
}
