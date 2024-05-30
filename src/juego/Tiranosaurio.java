package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Tiranosaurio {
	
	private int x;
	private int y;
	private int ancho;
	private int alto;
	private int velocidad;
	private double velocidadVertical = 0;
	private double gravedad = 0.5;
	private boolean miraDerecha = true;
	private boolean enAire = false;
	private Image imagen;
	
	public Tiranosaurio(int x, int y, int ancho, int alto) {	
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.velocidad = 2;
	}
	
	public void cargarImagen() {
		if (miraDerecha == true) {
			this.imagen = Herramientas.cargarImagen("assets/dino_derecha.png");
		}
		if (miraDerecha == false) {
			this.imagen = Herramientas.cargarImagen("assets/dino_izquierda.png");			
		}
	}
	public void invertirImagen() {
		if (miraDerecha) {
			miraDerecha = false;
		}else {
			miraDerecha = true;
		}
	}
	public void dibujar(Entorno entorno){
//		cargarImagen();
		if (imagen != null) {
			entorno.dibujarImagen(imagen, x, y, 0, 1.5);			
		} else {
			entorno.dibujarRectangulo(x, y, ancho, alto, 0, Color.GREEN);
		}
	}
	
	public void mover(){
		this.x += velocidad;
	}
	
	public void rebotar() {
		invertirImagen();
		this.velocidad = this.velocidad * (-1);
	}
	
	public void actualizar(Bloque[][] filaBloques) {
		// Verifica si el dino esta en el aire.
		
		boolean sobreBloque = false;
		for (int i = 0; i < filaBloques.length; i++) {
			for (int j = 0; j < filaBloques[i].length; j++) {
				Bloque bloque = filaBloques[i][j];
				
				if (bloque != null && colisionaCon(bloque)) {
					if (velocidadVertical > 0 && this.y + this.alto / 2 <= bloque.getY()) {
						this.y = bloque.getY() - bloque.getAlto() - 5;
						velocidadVertical = 0;						
						sobreBloque = true;
						break;
					}
				}
			}
			if(sobreBloque) break;
		}
		enAire = !sobreBloque;
		if (enAire) {
			velocidadVertical += gravedad;
			this.y += velocidadVertical;
		}
//		System.out.println("Coordenadas del tiranosaurio: (" + this.x + ", " + this.y + ")");
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
	    boolean colisionY = bordeSup <= baseBloque &&
	                        bordeInf >= techoBloque;

	    // Verifica la colisión en el eje X
	    boolean colisionX = bordeDer >= bordeIzqBloque &&
                			bordeIzq <= bordeDerBloque;

	    // Retorna true si hay colisión en ambos ejes, de lo contrario retorna false
	    return colisionY && colisionX;
	}
	public boolean colisionEntorno(Entorno entorno) {
		boolean colision = x + (ancho / 2) > entorno.ancho() || 
				(x - (ancho / 2)) < 0;
		return colision;
	}
	
	
	//public void disparar() {
		//DisparoTiranosaurio disparo = new DisparoTiranosaurio(this.x, this.y, 5, 2.0);
	//}
	
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getAncho() {
		return ancho;
	}
	public int getAlto() {
		return alto;
	}
	public int getVelocidad() {
		return velocidad;
	}
    public int getBordeDerecho() {
        return this.x + this.ancho / 2;
    }
    public int getBordeIzquierdo() {
        return this.x - this.ancho / 2;
    }
    public int getBordeSuperior() {
        return this.y - this.alto / 2;
    }
    public int getBordeInferior() {
        return this.y + this.alto / 2;
    }
}
