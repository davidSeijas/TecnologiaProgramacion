//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package tp.practica1;

import tp.practica1.Zombie;
import tp.practica1.ZombieManager;

public class ZombieList {
	private Zombie[] listaZombies;
	private int numZombies;	
	private int numZombiesVivos;
	
	public ZombieList(ZombieManager zombieM) {
		this.listaZombies = new Zombie[zombieM.getNumZombiesRest()];
		this.numZombies = 0;
		this.numZombiesVivos = 0;
	}
	
	
	public void anadirZombie(Zombie zombie) {
		listaZombies[numZombies] = zombie;
		++numZombies;
		++numZombiesVivos;
	}
	
	
	public void update() {
		for(int i = 0; i < numZombies; ++i) {
			if(listaZombies[i].getVida() > 0) {
				listaZombies[i].update();
			}
		}
	}
	
	
	public void restarVida(int x, int y, int damage) {
		boolean ok = false;
		
		for(int i = y + 1; i < Game.MAX_COLUMNAS && !ok; ++i) {
			Zombie zombie = getPosition(x, i);
			if(zombie != null) {
				zombie.restarVida(damage);
				
				if(zombie.getVida() == 0) {
					--numZombiesVivos;
				}
				
				ok = true;
			}
		}
	}
	
	
	public boolean ganaZombies() {
		boolean ganar = false;
		
		for(int i = 0; i < Game.MAX_FILAS && !ganar; ++i) {
			if(getPosition(i, -1) != null) {
				ganar = true;
			}
		}
		
		return ganar;
	}
	
	
	public String toString(int i, int j) {
		String string = "";
		
		if(getPosition(i, j) != null) {
			string = getPosition(i, j).toString();
		}
		
		return string;
	}
	
	
	public boolean isPositionEmpty(int x, int y) {
		Zombie zombie = getPosition(x,y);
		
		if(zombie != null) {
			return false;
		}
		
		return true;
	}
	
	
	private Zombie getPosition(int x, int y) {
		for(int i = 0; i < numZombies; ++i) {	
			if(listaZombies[i].getX() == x && listaZombies[i].getY() == y && listaZombies[i].getVida() > 0) {
				return listaZombies[i];	
			}		
		}
		return null;
	}


	public int getNumZombies() {
		return numZombies;
	}


	public void setNumZombies(int numZombies) {
		this.numZombies = numZombies;
	}


	public int getNumZombiesVivos() {
		return numZombiesVivos;
	}
}
