//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package tp.practica1;

public class Sunflower {
	public static final int FRECUENCIA_S = 2;
	public static final int COSTE = 20;
	public static final int SOLESGENERADOS = 10;
	private int x;
	private int y;
	private int vida;;
	private int ciclosJuego;
	private Game juego;
	
	
	public Sunflower(Game juego, int x, int y) {
		this.x = x;
		this.y = y;
		this.vida = 1;
		this.ciclosJuego = 0;
		this.juego = juego;
	}


	public void update() {
		if(this.vida > 0){
			if(ciclosJuego > 0 && ciclosJuego % FRECUENCIA_S == 0) {
				juego.sumarSoles();
			}
			++ciclosJuego;
		}
	}
	
	
	public void restarVida(int damage) {
		vida -= damage;
	}
	
	
	public String toString() {
		return ("S [" + vida + "]");
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
