package principal.control;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//import javax.sound.sampled.Clip;
//import javax.sound.sampled.LineEvent;
//import javax.sound.sampled.LineListener;

//import principal.GestorPrincipal;
//import principal.herramientas.CargadorRecursos;
//import principal.sonido.Sonido;

public class Teclado implements KeyListener { // KEYBOARDS
	//Sonido bang = new Sonido("/sonidos/disparo.wav");
	
	public Tecla arriba = new Tecla(); //KEY UP
	public Tecla abajo = new Tecla(); //KEY DOWN
	public Tecla izquierda = new Tecla(); //KEY LEFT
	public Tecla derecha = new Tecla(); //KEY RIGHT

	public boolean atacando = false; // attacking
	public boolean recogiendo = false; // picking
	public boolean corriendo = false; // running
	public boolean debug = false; // debug
	public boolean inventarioActivo = false; // active inventory

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			arriba.teclaPulsada();
			break;
		case KeyEvent.VK_S:
			abajo.teclaPulsada();
			break;
		case KeyEvent.VK_A:
			izquierda.teclaPulsada();
			break;
		case KeyEvent.VK_D:
			derecha.teclaPulsada();
			break;
		case KeyEvent.VK_E:
			recogiendo = true; // picking
			break;
		case KeyEvent.VK_SHIFT:
			corriendo = true; // running
			break;
		case KeyEvent.VK_F1:
			debug = !debug;
			break;
		case KeyEvent.VK_I:
			inventarioActivo = !inventarioActivo; // active inventory
			break;
		case KeyEvent.VK_SPACE:
			atacando = true; // attacking
			break;
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
		}
	}

	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			arriba.teclaLiberada();
			break;
		case KeyEvent.VK_S:
			abajo.teclaLiberada();
			break;
		case KeyEvent.VK_A:
			izquierda.teclaLiberada();
			break;
		case KeyEvent.VK_D:
			derecha.teclaLiberada();
			break;
		case KeyEvent.VK_E:
			recogiendo = false;
			break;
		case KeyEvent.VK_SHIFT:
			corriendo = false;
			break;
		case KeyEvent.VK_SPACE:
			atacando = false;
			break;
		}
	}

	public void keyTyped(KeyEvent e) {
	}
}
