//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package Object;

public class ZombieDeportista extends Zombie {
	private static final int VIDA = 2;
	private static final int DAMAGE = 1;
	private static final int VELOCIDAD = 1;
	private static final String NOMBRE = "[Z]ombie Deportista";
	private static final String SIMBOLO = "X";
	
	
	public ZombieDeportista() {
		super(VIDA, DAMAGE, VELOCIDAD, NOMBRE, SIMBOLO);
	}
	
	
	public String toStringRelease() {
		return (SIMBOLO + " [" + vida + "]");
	}


	public String toStringDebug() {
		return (SIMBOLO + "[" + "l:" + vida + ",x:" + x + ",y:" + y + ",t:" + (nextTime - game.getNumCiclos()) + "]");
	}
}