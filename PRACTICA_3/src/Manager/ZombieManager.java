//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package Manager;

import java.util.Random;

import Factory.ZombieFactory;
import Object.Zombie;
import tp.practica3.Level;

public class ZombieManager {
	private int numZombiesRest;
	private double frecZombies;
	private int numZombiesVivos;
	
	public ZombieManager(Level level) {
		this.numZombiesRest = level.getNumZombies();
		this.frecZombies = level.getFrecZombies(); 
		this.numZombiesVivos = 0;
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
		++this.numZombiesVivos;
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


	public void setNumZombiesRest(int numZombiesRest) {
		this.numZombiesRest = numZombiesRest;
	}


	public void setFrecZombies(double frecZombies) {
		this.frecZombies = frecZombies;
	}


	public void setNumZombiesVivos(int numZombiesVivos) {
		this.numZombiesVivos = numZombiesVivos;
	}
}
