//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package tp.practica2;

import java.util.Random;

import Factory.ZombieFactory;
import Object.Zombie;
import tp.practica2.Level;


public class ZombieManager {
	private int numZombiesRest;
	private double frecZombies;
	private int numZombiesVivos;
	
	public ZombieManager(Level nivel) {
		this.numZombiesRest = this.numZombiesVivos = nivel.numZombies();
		this.frecZombies = nivel.frecZombies(); 
		this.numZombiesVivos = numZombiesRest;
	}
	
	
	public boolean isZombieAdded(Random rand) {
		if(numZombiesRest > 0) {
			double r = rand.nextDouble();
			return (frecZombies > r);
		}
		return false;
	}
	
	
	public Zombie elegirZombie(Random rand) {
		return (ZombieFactory.getZombieAleatorio(rand));
	}

	
	public void zombieNuevo() {
		--this.numZombiesRest;
	}


	public void zombieMuerto() {
		--this.numZombiesVivos;
	}


	public int getNumZombiesRest() {
		return numZombiesRest;
	}


	public double getFrecZombies() {
		return frecZombies;
	}


	public int getNumZombiesVivos() {
		return numZombiesVivos;
	}
}
