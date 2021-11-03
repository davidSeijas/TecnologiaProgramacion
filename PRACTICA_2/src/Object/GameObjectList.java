//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package Object;

import tp.practica2.Game;
import tp.practica2.Level;

public class GameObjectList {
	GameObject[] listaObjetos;
	private int numObjetos;
	

	public GameObjectList() {
		this.listaObjetos = new Plant[32];
		this.numObjetos = 0;
	}
	
	
	public GameObjectList(Level level) {
		this.listaObjetos = new Zombie[level.numZombies()];
		this.numObjetos = 0;
	}


	public void anadirGameObject(GameObject object) {
		if(numObjetos == listaObjetos.length) {
			this.redimensionar();
		}
		
		listaObjetos[numObjetos] = object;
		++numObjetos;
	}


	public void update() {
		for(int i = 0; i < numObjetos; ++i) {
			listaObjetos[i].update();
		}
	}


	public void remove() {
		GameObject[] aux = new GameObject[listaObjetos.length];
		int numObjetos = 0;
		
		for(int i = 0; i < this.numObjetos; ++i) {
			if(listaObjetos[i].getVida() > 0) {
				aux[numObjetos] = listaObjetos[i];
				++numObjetos;
			}
		}
		
		listaObjetos = aux;
		this.numObjetos = numObjetos;
	}
	
	
	public void redimensionar() { //solo se va a redimensionar la lista de las plantas pues la de los zombies tiene el tamaño del numero de zombies que juegan en total de la partida y nunca se van a generan mas zombies que esos
		GameObject[] aux = new GameObject[2 * listaObjetos.length];
		
		for(int i = 0; i < listaObjetos.length; ++i) {
			aux[i] = listaObjetos[i];
		}
		
		listaObjetos = aux;
	}


	public void restarVida(int x, int y, int damage) {
		if(getPosition(x, y) != null) {
			getPosition(x, y).restarVida(damage);
		}
	}
	

	public void recibirDisparo(int x, int y, int damage) {
		boolean ok = false;
	
		for(int i = y + 1; i < Game.MAX_COLUMNAS && !ok; ++i) {
			GameObject objeto = getPosition(x, i);
			if(objeto != null) {
				objeto.restarVida(damage);
				ok = true;
			}
		}
	}


	public void recibirExplosion(int x, int y, int damage) {
		for(int i = x - 1; i <= x + 1; ++i) {
			for(int j = y - 1; j <= y + 1; ++j) {
				if(getPosition(i, j) != null) {
					getPosition(i, j).restarVida(damage);	
				}
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
	
	
	public String toStringRelease(int i, int j) {
		String string = "";
		
		if(getPosition(i, j) != null) {
			string = getPosition(i, j).toStringRelease();
		}
		
		return string;
	}


	public String toStringDebug(int k) {
		String string = "";
		
		string = listaObjetos[k].toStringDebug();
		
		return string;
	}
	
	
	public boolean isPositionEmpty(int x, int y) {
		GameObject objeto = getPosition(x,y);
		
		if(objeto != null) {
			return false;
		}
		
		return true;
	}
	
	
	private GameObject getPosition(int x, int y) {
		for(int i = 0; i < numObjetos; ++i) {	
			if(listaObjetos[i].getX() == x && listaObjetos[i].getY() == y && listaObjetos[i].getVida() > 0) {
				return listaObjetos[i];	
			}		
		}
		return null;
	}


	public int getNumObjetos() {
		return numObjetos;
	}
}
