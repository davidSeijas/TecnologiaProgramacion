//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package tp.practica2;

import java.util.Random;

import Object.GameObjectList;
import Object.Plant;
import Object.Zombie;

public class Game {
	public static final int MAX_FILAS = 4;
	public static final int MAX_COLUMNAS = 8;
	private int numCiclos;
	private Random rand;
	private long semilla;
	private Level level;
	private GameObjectList plantList;
	private GameObjectList zombieList;
	private SuncoinManager solesManager;
	private ZombieManager zombieManager;

	public Game (long seed, Level level) {
		this.numCiclos = 0;
		this.semilla = seed;
		this.rand = new Random(semilla);
		this.level = level;
		this.solesManager = new SuncoinManager();
		this.zombieManager = new ZombieManager(this.level);
		this.plantList = new GameObjectList();
		this.zombieList = new GameObjectList(level);
	}
	
	
	public void update() {
		plantList.update();
		zombieList.update();
		plantList.remove();
		zombieList.remove();
		
		
		if(zombieManager.isZombieAdded(rand)) {
			this.anadirZombie();
		}
		
		++numCiclos;
	}


	public boolean anadirPlanta(Plant planta, int x, int y) {
		planta.inicializarPlanta(this, x, y);
		
		if(this.comprobarCoord(planta.getX(), planta.getY())) {
			if(this.getSoles() >= planta.getCoste()) {
				plantList.anadirGameObject(planta);
				solesManager.restarSoles(planta.getCoste());
				return true;
			}
			else {
				System.err.println("No tienes soles suficientes para comprar la planta\n");
			}
		}
		else {
			System.err.println("Invalid position\n");
		}
		
		return false;
	}
	

	public void anadirZombie() {
		Zombie zombie = zombieManager.elegirZombie(rand);
		zombie.inicializarZombie(rand, this);
		
		if(zombieList.isPositionEmpty(zombie.getX(), zombie.getY())) {
			zombieList.anadirGameObject(zombie);
			zombieManager.zombieNuevo();
		}	
	}
	
	
	public void reset() {
		this.numCiclos = 0;
		this.solesManager = new SuncoinManager();
		this.zombieManager = new ZombieManager(this.level);
		this.plantList = new GameObjectList();
		this.zombieList = new GameObjectList(this.level);
	}


	public void dispararPeashooter(int x, int y, int damage) {
		zombieList.recibirDisparo(x, y, damage);
	}


	public void explotarPetacereza(int x, int y, int damage) {
		zombieList.recibirExplosion(x, y, damage);
	}
	
	
	public void hacerDanio(int x, int y, int damage) {
		plantList.restarVida(x, y, damage);
	}


	public void zombieMuerto() {
		zombieManager.zombieMuerto();
	}
	
	
	public void sumarSoles(int soles) {
		solesManager.sumarSoles(soles);
	}
	
	
	public boolean comprobarCoord(int x, int y) {
		return ((x >= 0) && (x < MAX_FILAS) && (y >= 0) && (y < MAX_COLUMNAS - 1) && 
				comprobarCasilla(x, y));
	}	
	
	
	public boolean comprobarCasilla(int x, int y) {
		return (plantList.isPositionEmpty(x, y) && zombieList.isPositionEmpty(x, y));
	}
	
	
	public boolean ganaJugador(){
		if(zombieManager.getNumZombiesRest() > 0 || zombieManager.getNumZombiesVivos() > 0) {
			return false;
		}
		
		System.out.println("GAME OVER!\nGANASTE. ENHORABUENA!!!!\n");
		return true;
	}
	
	
	public boolean ganaZombies(){
		if(zombieList.ganaZombies()) {
			System.out.println("GAME OVER!\nHAN GANADO LOS ZOMBIES!!\n");
		}
		
		return (zombieList.ganaZombies());
	}

	
	public boolean finJuego(){
		return (ganaJugador() || ganaZombies());
	}
	
	
	public String dameStringRelease(int i, int j) {
		String string = "";
		string += plantList.toStringRelease(i, j);
		string += zombieList.toStringRelease(i, j);
		
		return string;
	}


	public String dameStringDebug(int k) {
		String string = "";
		
		if(k < plantList.getNumObjetos()) {
			string += plantList.toStringDebug(k);
		}
		else {
			k -= plantList.getNumObjetos();
			string += zombieList.toStringDebug(k);
		}
		
		return string;
	}
	
	
	public String dameAtributosRelease() {
		String string = "";
		
		string += ("Number of cycles: " + numCiclos + "\n");
		string += ("Sun coins: " + solesManager.getSoles() + "\n");
		string += ("Remaining zombies: " + zombieManager.getNumZombiesRest() + "\n");	
		
		return string;
	}


	public String dameAtributosDebug() {
		String string = "";
		
		string += ("Number of cycles: " + numCiclos + "\n");
		string += ("Sun coins: " + solesManager.getSoles() + "\n");
		string += ("Remaining zombies: " + zombieManager.getNumZombiesRest() + "\n");
		string += ("Level: " + levelToString() + "\n");
		string += ("Seed: " + semilla + "\n");
		
		return string;
	}
	
	
	public String levelToString() {
		String string = "";
		string = this.level.toString();
		
		return string;
	}


	public long getSemilla() {
		return semilla;
	}
	

	private int getSoles() {
		return solesManager.getSoles();
	}
	
	
	public int getNumCiclos() {
		return numCiclos;
	}
	
	
	public int getNumElemtos() {
		return (plantList.getNumObjetos() + zombieList.getNumObjetos());
	}
}
