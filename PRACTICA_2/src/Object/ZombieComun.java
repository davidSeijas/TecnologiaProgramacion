//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package Object;

public class ZombieComun extends Zombie{	
	private static final int VIDA = 5;
	private static final int DAMAGE = 1;
	private static final int VELOCIDAD = 2;
	private static final String NOMBRE = "[Z]ombie Comun";
	
	
	public ZombieComun() {
		super(VIDA, DAMAGE, VELOCIDAD, NOMBRE);
	}
	
	
	public String toStringRelease() {
		return ("Z [" + vida + "]");
	}


	public String toStringDebug() {
		return ("Z[" + "l:" + vida + ",x:" + x + ",y:" + y + ",t:" + (nextTime - game.getNumCiclos()) + "]");
	}
}


