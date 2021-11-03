//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package Object;

public class ZombieCaracubo extends Zombie {
	private static final int VIDA = 8;
	private static final int DAMAGE = 1;
	private static final int VELOCIDAD = 4;
	private static final String NOMBRE = "[Z]ombie Caracubo";
	private static final String SIMBOLO = "W";
	
	
	public ZombieCaracubo() {
		super(VIDA, DAMAGE, VELOCIDAD, NOMBRE, SIMBOLO);
	}
	
	
	public String toStringRelease() {
		return (SIMBOLO + " [" + vida + "]");
	}


	public String toStringDebug() {
		return (SIMBOLO + "[" + "l:" + vida + ",x:" + x + ",y:" + y + ",t:" + (nextTime - game.getNumCiclos()) + "]");
	}
}
