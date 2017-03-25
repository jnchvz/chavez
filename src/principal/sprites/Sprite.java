package principal.sprites;

import java.awt.image.BufferedImage;

public class Sprite {

	private final BufferedImage imagen; //image

	private final int ancho; //width
	private final int alto; //height

	public Sprite(final BufferedImage imagen) {
		this.imagen = imagen;

		ancho = imagen.getWidth(); // get width of the image
		alto = imagen.getHeight(); // get height of the image
	}

	public BufferedImage obtenerImagen() {
		return imagen;
	}

	public int obtenerAncho() { // get width
		return ancho; // return width
	}

	public int obtenerAlto() { //get height
		return alto; // return height
	}
}