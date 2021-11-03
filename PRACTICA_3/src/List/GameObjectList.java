//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package List;

import java.io.BufferedWriter;
import java.io.IOException;

import Exception.FileContentsException;
import Factory.PlantFactory;
import Factory.ZombieFactory;
import Object.GameObject;
import Object.Plant;
import Object.Zombie;
import tp.practica3.Game;
import tp.practica3.Level;

public class GameObjectList {
	GameObject[] listaObjetos;
	private int numObjetos;
	

	public GameObjectList() {
		this.listaObjetos = new Plant[32];
		this.numObjetos = 0;
	}
	
	
	public GameObjectList(Level level) {
		this.listaObjetos = new Zombie[level.getNumZombies()];
		this.numObjetos = 0;
	}


	public GameObjectList(String[] aux, Game game) throws FileContentsException, NumberFormatException{
		this.listaObjetos = new Plant [32];
		this.numObjetos = 0;
		
		for(int i = 0; i < aux.length; ++i) {
			String[] strPlanta = aux[i].trim().split(":");
			Plant planta = PlantFactory.getPlant(strPlanta[0]);
			
			if(planta == null) {
				throw new FileContentsException ("ERROR: el archivo tenia plantas inexistentes.");
			}
			if(strPlanta.length != 5) {
				throw new FileContentsException ("ERROR: el archivo tenia datos incorrectos.");
			}
			if(!game.comprobarCoord(Integer.parseInt(strPlanta[2]), Integer.parseInt(strPlanta[3]))) {
				throw new FileContentsException ("ERROR: el archivo tenia datos incorrectos."); //se deja sin comprobar cuando en el archivo dos plantas tienen las mismas coordenadas
			}
			
			planta.inicializar(game, Integer.parseInt(strPlanta[2]), Integer.parseInt(strPlanta[3]));
			planta.setVida(Integer.parseInt(strPlanta[1]));
			planta.setNextTime(game.getNumCiclos() + Integer.parseInt(strPlanta[4]));
			
			listaObjetos[numObjetos] = planta;
			++numObjetos;
		}
	}


	public GameObjectList(String[] aux, Game game, Level level) throws FileContentsException, NumberFormatException {
		this.listaObjetos = new Zombie [level.getNumZombies()];
		this.numObjetos = 0;
		
		for(int i = 0; i < aux.length; ++i) {
			String[] strZombie = aux[i].trim().split(":");
			Zombie zombie = ZombieFactory.getZombie(strZombie[0]);
			
			if(zombie == null) {
				throw new FileContentsException ("ERROR: el archivo tenia plantas inexistentes.");
			}
			if(strZombie.length != 5) {
				throw new FileContentsException ("ERROR: el archivo tenia datos incorrectos.");
			}
			if(!game.comprobarCoord(Integer.parseInt(strZombie[2]), Integer.parseInt(strZombie[3]))) {
				throw new FileContentsException ("ERROR: el archivo tenia datos incorrectos.");//se deja sin comprobar cuando en el archivo dos zombies tienen las mismas coordenadas
			}
			
			zombie.inicializar(game, Integer.parseInt(strZombie[2]), Integer.parseInt(strZombie[3]));
			zombie.setVida(Integer.parseInt(strZombie[1]));
			zombie.setNextTime(game.getNumCiclos() + Integer.parseInt(strZombie[4]));
			
			listaObjetos[numObjetos] = zombie;
			++numObjetos;
		}
	}


	public void comprobarLista() throws FileContentsException { //para comprobar que las coordenadas de las distintas plantas(zombies) son distintas entre sí y no haya dos en la misma posición
		for(int i = 0; i < numObjetos; ++i) {
			for(int j = 0; j < i; ++j) {
				if((listaObjetos[i].getX() == listaObjetos[j].getX()) && (listaObjetos[i].getY() == listaObjetos[j].getY())) {
					throw new FileContentsException ("ERROR: el archivo tenia datos incorrectos.");
				}
			}
		}	
	}


	public void store(BufferedWriter outStream) throws IOException {
		for(int i = 0; i < numObjetos; ++i) {
			listaObjetos[i].store(outStream);
			if (i < numObjetos - 1) {
				outStream.write(", ");
			}
		}
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
		
		//this.remove();
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
			if(getPosition(x, i) != null) {
				getPosition(x, i).restarVida(damage);
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
