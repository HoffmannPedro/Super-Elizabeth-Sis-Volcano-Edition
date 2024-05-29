package juego;

import java.awt.Image;
import java.util.Random;


import entorno.Entorno;
import entorno.Herramientas;

public class FilaBloques {
	private Bloque[][] filaBloques;
	private Image imagenBloqueR;
	private Image imagenBloqueI;
	
	public FilaBloques(int numFilas, int numColumnas, int[] posicionesY, int anchoBloque, int altoBloque, int espacioEntreBloques) {
		imagenBloqueR = Herramientas.cargarImagen("assets/block_r.png"); 
		imagenBloqueI = Herramientas.cargarImagen("assets/block_i.png"); 
		
		this.filaBloques = new Bloque [numFilas][numColumnas];
		
		for (int i = 0; i < numFilas; i++) {
			Random rand = new Random();
			int[] indicesRompibles = new int[4];
			
			int initX1 = 23;
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
					initX1 += anchoBloque + espacioEntreBloques;					
				}
				if (i > 0) {
					if (esRompible) {
						this.filaBloques[i][j] = new Bloque(initX1, posicionesY[i], altoBloque, anchoBloque, imagenBloqueR, esRompible);
						initX1 += anchoBloque + espacioEntreBloques;									
					}else if (!esRompible) {
						this.filaBloques[i][j] = new Bloque(initX1, posicionesY[i], altoBloque, anchoBloque, imagenBloqueI, esRompible);
						initX1 += anchoBloque + espacioEntreBloques;									
					}
				}
			}
		}
	}
	
	public void dibujar(Entorno entorno) {
		for (int i = 0; i < this.filaBloques.length; i++) {
			for (int x = 0; x < this.filaBloques[i].length; x++) {
				if (filaBloques[i][x] != null) {
					this.filaBloques[i][x].dibujar(entorno);
				}
			}
		}
	}
	
	public Bloque[][] getFilaBloques() {
		return this.filaBloques;
	}

}
