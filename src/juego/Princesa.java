package juego;

import entorno.Entorno;
import entorno.Herramientas;

import java.awt.Color;
import java.awt.Image;

public class Princesa {
	private int x;
	private int y;
	private int ancho;
	private int alto;
	private int posOriginal;
	private Image imagen;
	private boolean miraDerecha = true;
	private double velocidadVertical = 0;
	private double velocidadSalto = -15;
	private double gravedad =0.5;
	
	public Princesa(int x, int y, int ancho, int alto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.posOriginal = y;
	}
	
	private void cargarImagen() {
		if (miraDerecha == true) {
			imagen = Herramientas.cargarImagen("assets/0.png");		
		} 
		if (miraDerecha == false) {
			imagen = Herramientas.cargarImagen("assets/1.png");								
		}
		
	}
	
	public void moverDerecha() {
		this.x += 3;
	}
	
	public void moverIzquierda() {
		this.x -= 3;
	}
	
	public void saltar() {
		velocidadVertical = velocidadSalto;
	}
	
	public void actualizar(Bloque[][] filaBloques) {
		// Aplica gravedad.
		velocidadVertical += gravedad;
		this.y += (int) velocidadVertical;
		
		// Verifica colisiones con bloques.
		for (int i = 0; i < filaBloques.length; i++) {
			for (int j = 0; j < filaBloques[i].length; j++) {
				Bloque bloque = filaBloques[i][j];
				
				if (bloque != null && colisionaCon(bloque)) {
					if (velocidadVertical > 0) { // Colisiona desde arriba.
						this.y = bloque.getY() - this.alto;
						velocidadVertical = 0;
//						this.setPosOriginal(this.y);
					} else if (velocidadVertical < 0) { // Colisiona desde abajo.
						this.y = bloque.getY() + bloque.getAlto() / 2 + this.alto / 2;
						if (bloque.getRompible()) {
							filaBloques[i][j] = null;							
						}
						velocidadVertical = 0;
					}
				}
			}
		}
		
		// Chequea si la princesa esta en el suelo. 
		if (this.y >= this.posOriginal) {
			this.y = this.posOriginal;
			velocidadVertical = 0;
		}
	}
	
	private boolean colisionaCon(Bloque bloque) {
		// Verifica la colisión en el eje Y
	    boolean colisionY = this.y + this.alto / 2 > bloque.getY() - bloque.getAlto() / 2 &&
	                        this.y - this.alto / 2 < (bloque.getY() + bloque.getAlto() / 2) - 15;

	    // Verifica la colisión en el eje X
	    boolean colisionX = this.x  > bloque.getX() - bloque.getAncho() / 2 &&
                			this.x < bloque.getX() + bloque.getAncho() / 2;

	    // Retorna true si hay colisión en ambos ejes, de lo contrario retorna false
	    return colisionY && colisionX;
	}
	
	public void dibujar(Entorno entorno) {
		cargarImagen();
		if (imagen != null) {
			entorno.dibujarImagen(imagen, x, y, 0, 1.5);
		} else {
			entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.RED);
			
		}
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getAncho() {
		return ancho;
	}
	public int getAlto() {
		return alto;
	}
	public int getPosOriginal() {
		return posOriginal;
	}
	
	public void setPosOriginal(int posOriginal) {
		this.posOriginal = posOriginal;
	}
	
	public void setDireccion(boolean bool) {
		this.miraDerecha = bool;
	}
	
	
	
}
