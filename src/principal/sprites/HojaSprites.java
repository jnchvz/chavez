package principal.sprites;

import java.awt.image.BufferedImage;

import principal.herramientas.CargadorRecursos;

public class HojaSprites { // sprites sheet

	final private int anchoHojaEnPixeles; //width sheet in pixels
	final private int altoHojaEnPixeles; //height sheet in pixels

	final private int anchoHojaEnSprites; //width sheet in sprites
	final private int altoHojaEnSprites; //height sheet in sprites

	final private int anchoSprites; // width sprites
	final private int altoSprites; // height sprites

	final private Sprite[] sprites; // sprites

	public HojaSprites(final String ruta, final int ladoSprites, final boolean hojaOpaca) {
		final BufferedImage imagen;

		if (hojaOpaca) {
			imagen = CargadorRecursos.cargarImagenCompatibleOpaca(ruta); //opaque image
		} else {
			imagen = CargadorRecursos.cargarImagenCompatibleTranslucida(ruta); // translucent image
		}

		anchoHojaEnPixeles = imagen.getWidth();
		altoHojaEnPixeles = imagen.getHeight();

		anchoHojaEnSprites = anchoHojaEnPixeles / ladoSprites; // side sprites
		altoHojaEnSprites = altoHojaEnPixeles / ladoSprites; // side sprites

		anchoSprites = ladoSprites;
		altoSprites = ladoSprites;

		sprites = new Sprite[anchoHojaEnSprites * altoHojaEnSprites];

		rellenarSpritesDesdeImagen(imagen); //fill sprites from image
	}

	public HojaSprites(final String ruta, final int anchoSprites, final int altoSprites, final boolean hojaOpaca) {
		final BufferedImage imagen;

		if (hojaOpaca) {
			imagen = CargadorRecursos.cargarImagenCompatibleOpaca(ruta);
		} else {
			imagen = CargadorRecursos.cargarImagenCompatibleTranslucida(ruta);
		}

		anchoHojaEnPixeles = imagen.getWidth();
		altoHojaEnPixeles = imagen.getHeight();

		anchoHojaEnSprites = anchoHojaEnPixeles / anchoSprites;
		altoHojaEnSprites = altoHojaEnPixeles / altoSprites;

		this.anchoSprites = anchoSprites;
		this.altoSprites = altoSprites;

		sprites = new Sprite[anchoHojaEnSprites * altoHojaEnSprites];

		rellenarSpritesDesdeImagen(imagen);
	}

	private void rellenarSpritesDesdeImagen(final BufferedImage imagen) {
		for (int y = 0; y < altoHojaEnSprites; y++) {
			for (int x = 0; x < anchoHojaEnSprites; x++) {
				final int posicionX = x * anchoSprites;
				final int posicionY = y * altoSprites;

				sprites[x + y * anchoHojaEnSprites] = new Sprite(
						imagen.getSubimage(posicionX, posicionY, anchoSprites, altoSprites));
			}
		}
	}

	public Sprite obtenerSprite(final int indice) { //get sprite
		return sprites[indice];
	}

	public Sprite obtenerSprite(final int x, final int y) {
		return sprites[x + y * anchoHojaEnSprites];
	}
}
