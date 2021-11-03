//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package Object;

public class Peashooter extends Plant{
	private static final int VIDA = 3;
	private static final int DAMAGE = 1;
	private static final int FRECUENCIA = 1;
	private static final int COSTE = 50;
	private static final String NOMBRE = "[P]eashooter";
	private static final String SIMBOLO = "P";
	
	
	public Peashooter() {
		super(VIDA, DAMAGE, FRECUENCIA, COSTE, NOMBRE, SIMBOLO);
	}


	public void update() {
		if(game.getNumCiclos() == this.nextTime) {
			nextTime += FRECUENCIA;
			game.dispararPeashooter(x, y, DAMAGE);
		}
	}
	
	
	public String toStringRelease() {
		return (SIMBOLO + " [" + vida + "]");
	}


	public String toStringDebug() {
		return (SIMBOLO + "[" + "l:" + vida + ",x:" + x + ",y:" + y + ",t:" + (nextTime - game.getNumCiclos()) + "]");
	}
}