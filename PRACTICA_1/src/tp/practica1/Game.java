//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package tp.practica1;

import java.util.Random;
import tp.practica1.GamePrinter;

public class Game {
	public static final int MAX_FILAS = 4;
	public static final int MAX_COLUMNAS = 8;
	private int numCiclos;
	private Random rand;
	private long semilla;
	private Level level;
	private SunflowerList sunflowerList;
	private PeashooterList peashooterList;
	private ZombieList zombieList;
	private SuncoinManager solesManager;
	private ZombieManager zombieManager;
	private GamePrinter printer;

	public Game (long seed, Level nivel) {
		this.numCiclos = 0;
		this.semilla = seed;
		this.rand = new Random(semilla);
		this.level = nivel;
		this.solesManager = new SuncoinManager();
		this.zombieManager = new ZombieManager(this.level);
		this.sunflowerList = new SunflowerList();
		this.peashooterList = new PeashooterList();
		this.zombieList = new ZombieList(this.zombieManager);
		this.printer = new GamePrinter(this, MAX_FILAS, MAX_COLUMNAS);

	}
	
	
	public void update() {
		sunflowerList.update();
		peashooterList.update();
		zombieList.update();
		
		if(zombieManager.isZombieAdded(rand)) {
			this.anadirZombie();
		}
		
		++numCiclos;
	}
	
	
	public boolean anadirSunflower(int x, int y) {
		Sunflower sunflower = new Sunflower(this, x, y);
		
		if(this.comprobarCoord(x, y)) {
			if(this.getSoles() >= Sunflower.COSTE) {
				sunflowerList.anadirSunflower(sunflower);
				solesManager.restarSoles(Sunflower.COSTE);
				return true;
			}
			else {
				System.out.println("No tienes soles suficientes para comprar girasoles\n");
			}
		}
		else {
			System.out.println("Invalid position\n");
		}
		
		return false;
	}
	
	
	public boolean anadirPeashooter(int x, int y) {
		Peashooter peashooter = new Peashooter(this, x, y);
		
		if(this.comprobarCoord(x, y)) {
			if(this.getSoles() >= Peashooter.COSTE) {
				peashooterList.anadirPeashooter(peashooter);
				solesManager.restarSoles(Peashooter.COSTE);
				return true;
			}
			else {
				System.out.println("No tienes soles suficientes para comprar lanzaguisantes\n");
			}
		}
		else {
			System.out.println("Invalid position\n");
		}
		
		return false;
	}
	

	public void anadirZombie() {
		Zombie zombie = new Zombie(this);
		
		if(zombieList.isPositionEmpty(zombie.getX(), zombie.getY())) {
			zombieList.anadirZombie(zombie);
			zombieManager.setNumZombiesRest(zombieManager.getNumZombiesRest() - 1);
		}
		
	}
	
	
	public void reset() {
		this.numCiclos = 0;
		this.solesManager = new SuncoinManager();
		this.zombieManager = new ZombieManager(this.level);
		this.sunflowerList = new SunflowerList();
		this.peashooterList = new PeashooterList();
		this.zombieList = new ZombieList(this.zombieManager);
		this.printer = new GamePrinter(this, MAX_FILAS, MAX_COLUMNAS);
	}
	
	
	public void mostrarTablero () {
		System.out.println("Number of cycles: " + numCiclos);
		System.out.println("Sun coins: " + solesManager.getSoles());
		System.out.println("Remaining zombies: " + zombieManager.getNumZombiesRest());
		
		printer = new GamePrinter(this, MAX_FILAS, MAX_COLUMNAS);
		printer.toString();
		System.out.println(printer);
	}
	
	
	public void dispararPeashooter(int x, int y, int damage) {
		zombieList.restarVida(x, y, damage);
	}
	
	
	public void hacerDanio(int x, int y, int damage) {
		sunflowerList.restarVida(x, y, damage);
		peashooterList.restarVida(x, y, damage);
	}
	
	
	public void sumarSoles() {
		solesManager.sumarSoles(Sunflower.SOLESGENERADOS);
	}
	
	
	public boolean comprobarCoord(int x, int y) {
		return((x >= 0) && (x < 4) && (y >= 0) && (y < 7) && 
				comprobarCasilla(x,y));
	}	
	
	
	public boolean comprobarCasilla(int x, int y) {
		return(sunflowerList.isPositionEmpty(x, y) && 
				peashooterList.isPositionEmpty(x, y) && 
				zombieList.isPositionEmpty(x, y));
	}
	
	
	public boolean ganaJugador(){
		if(zombieManager.getNumZombiesRest() > 0 || zombieList.getNumZombiesVivos() > 0) {
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
	
	
	public String dameString(int i, int j) {
		String string = "";
		string += sunflowerList.toString(i, j);
		string += peashooterList.toString(i, j);
		string += zombieList.toString(i, j);
		
		return string;
	}
	

	public long getSemilla() {
		return semilla;
	}
	

	private int getSoles() {
		return solesManager.getSoles();
	}
}

