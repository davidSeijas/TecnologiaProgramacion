//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package Object;

public class ZombieDeportista extends Zombie {
	private static final int VIDA = 2;
	private static final int DAMAGE = 1;
	private static final int VELOCIDAD = 1;
	private static final String NOMBRE = "[Z]ombie Deportista";
	
	
	public ZombieDeportista() {
		super(VIDA, DAMAGE, VELOCIDAD, NOMBRE);
	}
	
	
	public String toStringRelease() {
		return ("X [" + vida + "]");
	}


	public String toStringDebug() {
		return ("X[" + "l:" + vida + ",x:" + x + ",y:" + y + ",t:" + (nextTime - game.getNumCiclos()) + "]");
	}
}