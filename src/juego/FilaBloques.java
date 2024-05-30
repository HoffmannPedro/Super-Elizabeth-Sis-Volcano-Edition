package juego;

import java.awt.Image;
import java.util.Random;


import entorno.Entorno;
import entorno.Herramientas;

public class FilaBloques {
	private Bloque[][] filaBloques;
	private Image imagenBloqueR;
	private Image imagenBloqueI;
	
	public FilaBloques(int numFilas, int numColumnas, int anchoBloque, int altoBloque, int anchoPantalla, int altoPantalla) {
		imagenBloqueR = Herramientas.cargarImagen("assets/block_r.png"); 
		imagenBloqueI = Herramientas.cargarImagen("assets/block_i.png"); 
		
		this.filaBloques = new Bloque [numFilas][numColumnas];
		
		
		double espacioBloquesY = altoBloque * numFilas;
		double espacioEntreFilas = (altoPantalla - espacioBloquesY) / numFilas;
		int[] posicionesY = new int[numFilas];
		int posicionX = altoPantalla - (altoBloque / 2);
		for (int x = 0; x < numFilas; x++) {
			posicionesY[x] = posicionX ;
			posicionX -= espacioEntreFilas * 1.7;
		}
		imprimirArray(posicionesY);
		
		for (int i = 0; i < numFilas; i++) {
	        
			Random rand = new Random();
			int[] indicesRompibles = new int[4];
			
			for (int n = 0; n < 4; n++) {
				int indice;
				boolean repetido;
				do {
					indice = rand.nextInt(numColumnas);
					repetido = false;
					for (int m = 0; m < n; m++) {
						if (indice == indicesRompibles[m]) {
							repetido = true;
							break;
						}
					}
				} while (repetido);
				indicesRompibles[n] = indice;
			}
			
			int totalAnchoBloques = numColumnas * anchoBloque;
			int initX1 = (anchoPantalla - totalAnchoBloques) / 2 + (anchoBloque / 2);
			
			for (int j = 0; j < numColumnas; j++) {
				boolean esRompible = false;
				for (int index : indicesRompibles) {
					if (index == j) {
						esRompible = true;
						break;
					}
				}
				if (i == 0) {
					this.filaBloques[i][j] = new Bloque(initX1, posicionesY[i], altoBloque, anchoBloque, imagenBloqueI, false);
					initX1 += anchoBloque;					
				}
				if (i > 0) {
					if (esRompible) {
						this.filaBloques[i][j] = new Bloque(initX1, posicionesY[i], altoBloque, anchoBloque, imagenBloqueR, esRompible);								
					}else if (!esRompible) {
						this.filaBloques[i][j] = new Bloque(initX1, posicionesY[i], altoBloque, anchoBloque, imagenBloqueI, esRompible);
					}
					initX1 += anchoBloque;									
				}
			}
		}
	}
	
	public void dibujar(Entorno entorno) {
		for (int i = 0; i < this.filaBloques.length; i++) {
			for (int x = 0; x < this.filaBloques[i].length; x++) {
				if (filaBloques[i][x] != null) {
//					System.out.println("Coordenadas del bloque: (" + filaBloques[i][x].getX() + ", " + filaBloques[i][x].getY() + ")");
					this.filaBloques[i][x].dibujar(entorno);
				}
			}
		}
	}
	
	public Bloque[][] getFilaBloques() {
		return this.filaBloques;
	}
	
	public void imprimirArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + ", ");
		}
	}

}
