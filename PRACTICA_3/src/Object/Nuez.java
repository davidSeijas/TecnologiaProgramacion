//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package Object;

public class Nuez extends Plant {
	private static final int VIDA = 10;
	private static final int DAMAGE = 0;
	private static final int FRECUENCIA = 0;
	private static final int COSTE = 50;
	private static final String NOMBRE = "[N]uez";
	private static final String SIMBOLO = "N";
	
	
	public Nuez() {
		super(VIDA, DAMAGE, FRECUENCIA, COSTE, NOMBRE, SIMBOLO);
	}
	
	
	public void update() {
	}
	
	
	public String toStringRelease() {
		return (SIMBOLO + " [" + vida + "]");
	}


	public String toStringDebug() {
		return (SIMBOLO + "[" + "l:" + vida + ",x:" + x + ",y:" + y + ",t:" + (nextTime - game.getNumCiclos()) + "]");
	}
}