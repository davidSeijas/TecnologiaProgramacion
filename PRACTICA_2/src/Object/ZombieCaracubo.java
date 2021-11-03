//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package Object;

public class ZombieCaracubo extends Zombie {
	private static final int VIDA = 8;
	private static final int DAMAGE = 1;
	private static final int VELOCIDAD = 4;
	private static final String NOMBRE = "[Z]ombie Caracubo";
	
	
	public ZombieCaracubo() {
		super(VIDA, DAMAGE, VELOCIDAD, NOMBRE);
	}
	
	
	public String toStringRelease() {
		return ("W [" + vida + "]");
	}


	public String toStringDebug() {
		return ("W[" + "l:" + vida + ",x:" + x + ",y:" + y + ",t:" + (nextTime - game.getNumCiclos()) + "]");
	}
}
