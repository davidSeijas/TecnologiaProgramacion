//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package tp.practica1;

import java.util.Random;

public class Zombie {	
	public static final int VELOCIDAD_Z = 2;
	public static final int DAMAGE = 1;
	private int x;
	private int y;
	private int vida;
	boolean avanzar;
	private Game juego;
	
	
	public Zombie(Game juego) {
		this.x = new Random().nextInt(Game.MAX_FILAS);
		this.y = Game.MAX_COLUMNAS - 1;
		this.vida = 5;
		this.avanzar = false; 
		this.juego = juego;
	}


	public void update() {
		avanzar = !avanzar;
		
		if(this.avanzar) {
			if(juego.comprobarCasilla(x, y - 1)) {
				--y;
			}
			else {
				juego.hacerDanio(x, y - 1, DAMAGE);
				avanzar = false;
			}
		}
		else {
			if(!(juego.comprobarCasilla(x, y - 1))) {
				juego.hacerDanio(x, y - 1, DAMAGE);
			}
		}
	}
	
	
	public void restarVida(int damage) {
		vida -= damage;
	}
	
	
	public String toString() {
		return ("Z [" + vida + "]");
	}
	

	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}


	public int getVida() {
		return vida;
	}
}

