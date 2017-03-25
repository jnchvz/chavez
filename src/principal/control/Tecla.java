package principal.control;

public class Tecla { // KEY
	private boolean pulsada = false;
	private long ultimaPulsacion = System.nanoTime();

	public void teclaPulsada() { // pulsed key
		pulsada = true;
		ultimaPulsacion = System.nanoTime();
	}

	public void teclaLiberada() {
		pulsada = false;
	}

	public boolean estaPulsada() { // is pulsed
		return pulsada;
	}

	public long obtenerUltimaPulsacion() { // get latest pulsation
		return ultimaPulsacion;
	}
}
