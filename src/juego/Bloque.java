package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;

public class Bloque {
	private Integer x;
	private Integer y;
	private Integer ancho;
	private Integer alto;
	private Image imagen;
	private boolean rompible;
	
	public Bloque(int x, int y, int ancho, int alto, Image imagen, boolean rompible) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.imagen = null;
		this.rompible = rompible;
	}
	
	
	public void dibujar(Entorno entorno) {
		if (imagen != null) {
			entorno.dibujarImagen(imagen,x, y, 0, 0.5);
		} else {
			entorno.dibujarRectangulo(getX(), getY(), getAncho(), getAlto(), 0, Color.BLUE);
		}
	}
	
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public int getAlto() {
		return this.alto;
	}
	public int getAncho() {
		return this.ancho;
	}
	public boolean getRompible() {
		return this.rompible;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setAlto(int alto) {
		this.alto = alto;
	}
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

}
