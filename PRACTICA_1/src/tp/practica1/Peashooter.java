//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package tp.practica1;

public class Peashooter {
	public static final int FRECUENCIA_P = 1;
	public static final int COSTE = 50;
	public static final int DAMAGE = 1;
	private int x;
	private int y;
	private int vida;
	private Game juego;
	
	
	public Peashooter(Game juego, int x, int y) {
		this.x = x;
		this.y = y;
		this.vida = 3;
		this.juego = juego;
	}


	public void update() {
		if(this.vida > 0) {
			juego.dispararPeashooter(x, y, DAMAGE);
		}
	}
	
	
	public void restarVida(int damage) {
		vida -= damage;
	}
	
	
	public String toString() {
		return ("P [" + vida + "]");
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