package juego;

import java.awt.Color;

import entorno.Entorno;

public class DisparoTiranosaurio {
	
	private int x;
	private int y;
	private int velocidad;
	private double diametro;
	
	DisparoTiranosaurio(int x, int y, int velocidad, double diametro){
		this.x = x;
		this.y = y;
		this.velocidad = velocidad;
		this.diametro = diametro;
	}
	
	public void dibujar(Entorno entorno){
		entorno.dibujarCirculo(x, y, diametro, Color.RED);
	}
	
	public void mover(){
		this.x += this.velocidad;
	}
	
	public int getX() {
		return x;
	}
	
	public int setX(int x) {
		return this.x = x;
	}

	public int getY() {
		return y;
	}
	
	public int setY(int y) {
		return this.x = y;
	}

	public double getDiametro() {
		return diametro;
	}
}
