//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package Object;

public abstract class Zombie extends GameObject{
	
	public Zombie(int vida, int damage, int velocidad, String zombieName, String simbolo) {
		super(vida, damage, velocidad, zombieName, simbolo);
	}
	
	
	public abstract String toStringRelease();
	
	public abstract String toStringDebug();
	
	
	public boolean avanzar() {
		if(game.getNumCiclos() == nextTime) {
			nextTime += this.frecuencia;
			return true;
		}
		
		return false;
	}


	public void update() {
		if(vida > 0) {
			if(avanzar()) {
				if(game.comprobarCasilla(x, y - 1)) {
					--y;
				}
				else {
					nextTime = game.getNumCiclos() + 1;
					game.hacerDanio(x, y - 1, damage);
				}
			}
			else {
				if(!(game.comprobarCasilla(x, y - 1))) {
					game.hacerDanio(x, y - 1, damage);
				}
			}
		}
	}
	
	
	public void restarVida(int damage) {
		vida -= damage;
		
		if(vida <= 0) {
			game.zombieMuerto();
		}
	}
	
	
	public String dameMessage() {
		String string = "";
		
		string = objectName + ": Speed: " + frecuencia + " Life: " + vida + "\n";
		
		return string;
	}
}

