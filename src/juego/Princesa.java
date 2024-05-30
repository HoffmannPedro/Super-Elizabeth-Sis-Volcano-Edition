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
	private boolean enElSuelo = false;
	public boolean cayendo = false;
	private int velocidad;
	private double velocidadVertical = 0;
	private double velocidadSalto = -15;
	private double gravedad = 0.5;
	
	public Princesa(int x, int y, int ancho, int alto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.posOriginal = y;
		this.velocidad = 3;
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
		this.x += velocidad;
	}
	
	public void moverIzquierda() {
		this.x -= velocidad;
	}
	
	public void saltar() {
		if (enElSuelo) {
            velocidadVertical = velocidadSalto;
            enElSuelo = false;
        }
	}
	
	public void actualizar(Bloque[][] filaBloques) {
		// Aplica gravedad solo si no está en el suelo
        if (!enElSuelo) {
            velocidadVertical += gravedad;
        }

        this.y += (int) velocidadVertical;
        System.out.println("Actualizando posición: y = " + y + ", velocidadVertical = " + velocidadVertical);
        
        enElSuelo = false;  // Asumir que no está en el suelo hasta que se compruebe lo contrario
		
		// Verifica colisiones verticales.
		for (int i = 0; i < filaBloques.length; i++) {
			for (int j = 0; j < filaBloques[i].length; j++) {
				Bloque bloque = filaBloques[i][j];
				
				if (bloque != null && colisionaCon(bloque)) {
					if (velocidadVertical > 0) { // Colisiona desde arriba.
						this.y = bloque.getY() - bloque.getAlto() / 2 - this.alto / 2;
						velocidadVertical = 0;
						enElSuelo = true;
						System.out.println("Colisión desde arriba. Nueva posición y = " + y);
					} else if (velocidadVertical < 0) { // Colisiona desde abajo.
						this.y = bloque.getY() + bloque.getAlto() / 2 + this.alto / 2;
						if (bloque.getRompible()) {
							filaBloques[i][j] = null;							
						}
						velocidadVertical = 0;
						System.out.println("Colisión desde abajo. Nueva posición y = " + y);
					}
				}
			}
		}
		if (velocidadVertical > 0.5) {
			cayendo = true;
		} else {
			cayendo = false;
		}
		
		// Chequea si la princesa está en el suelo.
		if (this.y >= this.posOriginal) {
            this.y = this.posOriginal;
            velocidadVertical = 0;
            enElSuelo = true;
            System.out.println("En el suelo. velocidadVertical = " + velocidadVertical);
        }
	}
	
	public void cayendo() {
		if (velocidadVertical > 0.5) {
			cayendo = true;
		} else {
			cayendo = false;
		}
	}
	
	private boolean colisionaCon(Bloque bloque) {
		int bordeSup = y - (alto / 2);
		int bordeInf = y + (alto / 2);
		int bordeIzq = x - (ancho / 2);
		int bordeDer = x + (ancho / 2);
		
		int techoBloque = bloque.getY() - bloque.getAlto() / 2;
		int baseBloque = bloque.getY() + bloque.getAlto() / 2;
		int bordeIzqBloque = bloque.getX() - bloque.getAncho() / 2;
		int bordeDerBloque = bloque.getX() + bloque.getAncho() / 2;
		
		// Verifica la colisión en el eje Y
	    boolean colisionY = bordeSup < baseBloque &&
	                        bordeInf > techoBloque;

	    // Verifica la colisión en el eje X
	    boolean colisionX = bordeDer > bordeIzqBloque &&
                			bordeIzq < bordeDerBloque;

	    // Retorna true si hay colisión en ambos ejes, de lo contrario retorna false
	    return colisionY && colisionX;
	}
	private boolean colisionaPorDebajo(Bloque bloque) {
		int bordeInf = y + (alto / 2);
		int techoBloque = bloque.getY() - bloque.getAlto() / 2;
		
		boolean colisionDebajo = bordeInf >= techoBloque;
		return colisionDebajo;
	}
	
	private boolean colisionaEnY(Bloque bloque) {
	    int bordeSup = y - (alto / 2);
	    int bordeInf = y + (alto / 2);
	    int techoBloque = bloque.getY() - bloque.getAlto() / 2;
	    int baseBloque = bloque.getY() + bloque.getAlto() / 2;
	    
	    // Verifica la colisión en el eje Y
	    return bordeSup < baseBloque && bordeInf > techoBloque;
	}

	private boolean colisionaEnX(Bloque bloque) {
	    int bordeIzq = x - (ancho / 2);
	    int bordeDer = x + (ancho / 2);
	    int bordeIzqBloque = bloque.getX() - bloque.getAncho() / 2;
	    int bordeDerBloque = bloque.getX() + bloque.getAncho() / 2;
	    
	    // Verifica la colisión en el eje X
	    return bordeDer > bordeIzqBloque && bordeIzq < bordeDerBloque;
	}
	
	public void dibujar(Entorno entorno) {
//		cargarImagen();
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
