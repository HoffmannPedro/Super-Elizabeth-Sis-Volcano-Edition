package juego;


import java.util.Random;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;


public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;	
	private Image fondo;

	// Variables y métodos propios de cada grupo
	private Princesa princesa;
	private FilaBloques filaBloques;
	private int[] posicionesFilasY = {585, 405, 225, 45};
	
	
	// ...

	Juego() {
		Random rand = new Random();
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, " Super Elizabeth Sis, Volcano Edition - Grupo ... - v1", 800, 600);

		fondo = Herramientas.cargarImagen("assets/fondo.jpg");  
		

		// Inicializar lo que haga falta para el juego
		this.princesa = new Princesa(400, 520, 30, 60);
		this.princesa.setPosOriginal(this.princesa.getY());
		
		// Crea las filas de bloques.
		this.filaBloques = new FilaBloques(4, 17, posicionesFilasY, 60, 60, -13);
//
		// ...
		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y por lo
	 * tanto es el método más importante de esta clase. Aquí se debe actualizar el
	 * estado interno del juego para simular el paso del tiempo (ver el enunciado
	 * del TP para mayor detalle).
	 */
	public void tick() {
		// Procesamiento de un instante de tiempo
		
		this.entorno.dibujarImagen(fondo, 400, 300, 0);
		this.filaBloques.dibujar(entorno);
		this.princesa.dibujar(this.entorno);
		
		this.princesa.actualizar(this.filaBloques.getFilaBloques());
		
		if (this.entorno.sePresiono('x')) {
			this.princesa.saltar();
		}

		if (this.entorno.estaPresionada(this.entorno.TECLA_DERECHA) &&
			this.princesa.getX() + this.princesa.getAncho()/2 < this.entorno.ancho()) {
				this.princesa.moverDerecha();
				this.princesa.setDireccion(true);
		}
		if (this.entorno.estaPresionada(this.entorno.TECLA_IZQUIERDA) &&
				this.princesa.getX() + this.princesa.getAncho()/2 > 30) {
			this.princesa.moverIzquierda();
			this.princesa.setDireccion(false);
		}

		// ...
		
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
