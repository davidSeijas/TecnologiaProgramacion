//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package tp.practica1;

import java.util.Random;
import tp.practica1.Level;

public class ZombieManager {
	private int numZombiesRest;
	private double frecZombies;
	
	public ZombieManager(Level nivel) {
		this.numZombiesRest = nivel.numZombies();
		this.frecZombies = nivel.frecZombies();
	}
	
	
	public boolean isZombieAdded(Random rand) {
		if(numZombiesRest > 0) {
			double r = rand.nextDouble();
			return (frecZombies > r);
		}
		return false;
	}

	
	public int getNumZombiesRest() {
		return numZombiesRest;
	}


	public void setNumZombiesRest(int numZombiesRest) {
		this.numZombiesRest = numZombiesRest;
	}
}
