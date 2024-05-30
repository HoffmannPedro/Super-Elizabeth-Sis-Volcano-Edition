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
	private Tiranosaurio[] tiranosaurios;
	private FilaBloques filaBloques;
	
	// ...

	Juego() {
		Random rand = new Random();
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, " Super Elizabeth Sis, Volcano Edition - Grupo ... - v1", 800, 600);

		fondo = Herramientas.cargarImagen("assets/fondo.jpg");  
		

		// Inicializar lo que haga falta para el juego
		this.princesa = new Princesa(400, 519, 30, 60);
		this.princesa.setPosOriginal(this.princesa.getY());
		
		// Crea las filas de bloques.
		this.filaBloques = new FilaBloques(4, 16, 50, 50, entorno.ancho(), entorno.alto());
		
		// Crear T-rexs
		this.tiranosaurios = new Tiranosaurio[3];
		int xPrimerTrex = 50;
		int yPrimerTrex = 520;
		for (int i = 0; i < this.tiranosaurios.length; i++) {
			int xEspejo = entorno.ancho() - xPrimerTrex * 2;
			
			this.tiranosaurios[i] = new Tiranosaurio(xPrimerTrex, yPrimerTrex, 30, 60);
			xPrimerTrex += xEspejo;
			if (xPrimerTrex >= xEspejo) {
				yPrimerTrex -= 170;
			}
		}
		
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
		
		for (int n = 0; n < this.tiranosaurios.length; n++) {
			this.tiranosaurios[n].dibujar(entorno);
			this.tiranosaurios[n].mover();
			this.tiranosaurios[n].actualizar(this.filaBloques.getFilaBloques());
			
			if (this.tiranosaurios[n].colisionEntorno(entorno)) {
				this.tiranosaurios[n].rebotar();
			}
		}
		
		this.princesa.actualizar(this.filaBloques.getFilaBloques());
		
		if (this.entorno.sePresiono('x')) {
			this.princesa.saltar();
		}
		
		if (!this.princesa.cayendo) {
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
		}

		// ...
		
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
