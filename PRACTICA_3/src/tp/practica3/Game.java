//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package tp.practica3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

import Exception.CommandExecuteException;
import Exception.FileContentsException;
import List.GameObjectList;
import Manager.SuncoinManager;
import Manager.ZombieManager;
import Object.Plant;
import Object.Zombie;
import Printer.*;

public class Game {
	public static final int MAX_FILAS = 4;
	public static final int MAX_COLUMNAS = 8;
	public static final String wrongPrefixMsg = "unknown game attribute: ";
	public static final String lineTooLongMsg = "too many words on line commencing: ";
	public static final String lineTooShortMsg = "missing data on line commencing: ";
	private int numCiclos;
	private Random rand;
	private long semilla;
	private Level level;
	private GameObjectList plantList;
	private GameObjectList zombieList;
	private SuncoinManager solesManager;
	private ZombieManager zombieManager;
	private boolean gameOver;
	private GamePrinter gamePrinter;

	public Game (long seed, Level level) {
		this.numCiclos = 0;
		this.semilla = seed;
		this.rand = new Random(semilla);
		this.level = level;
		this.solesManager = new SuncoinManager();
		this.zombieManager = new ZombieManager(this.level);
		this.plantList = new GameObjectList();
		this.zombieList = new GameObjectList(level);
		this.gameOver = false;
	}
	
	
	public Game (long seed) {
		this.rand = new Random(semilla);
		this.solesManager = new SuncoinManager();
		this.gameOver = false;
	}
	
	
	public void update() {
		if(zombieManager.isZombieAdded(rand)) {
			this.anadirZombie();
		}
		
		plantList.update();		//plantList.remove();
		if(this.ganaJugador()) {
			gameOver = true;
		}
		
		zombieList.update();	//zombieList.remove();
		if(this.ganaZombies()) {
			gameOver = true;
		}
		
		plantList.remove();
		zombieList.remove();
		
		++numCiclos;
	}


	public boolean anadirPlanta(Plant planta, int x, int y) throws CommandExecuteException {
		planta.inicializar(this, x, y);
		
		if(this.comprobarCoord(planta.getX(), planta.getY())) {
			if(this.getSoles() >= planta.getCoste()) {
				plantList.anadirGameObject(planta);
				solesManager.restarSoles(planta.getCoste());
			}
			else {
				throw new CommandExecuteException("No tienes soles suficientes para comprar la planta.");
			}
		}
		else {
			throw new CommandExecuteException("Invalid position: (" + x + "," + y + ").");
		}
		
		return true;
	}
	

	public void anadirZombie() {
		Zombie zombie = zombieManager.elegirZombie(rand);
		int x = rand.nextInt(MAX_FILAS);
		int y = MAX_COLUMNAS - 1;
		zombie.inicializar(this, x, y);
		
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
		return (ganaJugador() || ganaZombies() || gameOver);
	}
	
	
	public void store(BufferedWriter outStream) throws IOException, FileContentsException {
		try{
			outStream.write("cycle: " + Integer.toString(numCiclos));
			outStream.newLine();
			outStream.write("sunCoins: " + Integer.toString(solesManager.getSoles()));
			outStream.newLine();
			outStream.write("level: " + level.toString());
			outStream.newLine();
			outStream.write("remZombies: " + Integer.toString(zombieManager.getNumZombiesRest()));
			outStream.newLine();
			outStream.write("plantList: ");
			plantList.store(outStream);
			outStream.newLine();
			outStream.write("zombieList: ");
			zombieList.store(outStream);
			outStream.newLine();
		}
		catch(NumberFormatException e) {
			throw new FileContentsException("Error al convertir los numeros a String");
		}
	}
	
	
	public void load(BufferedReader inSream) throws IOException, FileContentsException {
		try{
			String[] aux;
			this.plantList = new GameObjectList(); 
			//para cuando comprobamos que las coordenadas de la lista de plantas no se solapan en el fichero a cargar
			this.zombieList = new GameObjectList(Level.EASY); 
			//para cuando comprobamos que las coordenadas de la lista de zombies no se solapan en el fichero a cargar
			//el level da igual pues es solo para crear una lista vacía
			
			
			aux = loadLine(inSream, "cycle", false);
			this.numCiclos = Integer.parseInt(aux[0]);
			if(numCiclos < 0) {
				throw new FileContentsException("ERROR: El número de ciclos ha de ser positivo.");
			}
			
			aux = loadLine(inSream, "sunCoins", false);
			this.solesManager.setSoles(Integer.parseInt(aux[0]));
			if(solesManager.getSoles() < 0) {
				throw new FileContentsException("ERROR: El número de soles ha de ser positivo.");
			}
			
			aux = loadLine(inSream, "level", false);
			this.level = Level.parse(aux[0]);
			if(level == null) {
				throw new FileContentsException("ERROR: El nivel ha de ser: " + Level.all("-") + ".");
			}
			this.zombieManager.setFrecZombies(this.level.getFrecZombies());
			
			aux = loadLine(inSream, "remZombies", false);
			this.zombieManager.setNumZombiesRest(Integer.parseInt(aux[0]));
			if(zombieManager.getNumZombiesRest() < 0 || zombieManager.getNumZombiesRest() > this.level.getNumZombies()) {
				throw new FileContentsException("ERROR: El número de zombies restantes ha de ser positivo y coherente al nivel.");
			}
			
			aux = loadLine(inSream, "plantList", true);
			if(aux.length > MAX_FILAS*(MAX_COLUMNAS - 1)) {
				throw new FileContentsException("ERROR: Lista de plantas más larga de lo posible.");
			}
			this.plantList = new GameObjectList(aux, this);
			this.plantList.comprobarLista();//para comprobar que las coordenadas de las distintas plantas son distintas entre sí y no haya dos en la misma posición
			
			aux = loadLine(inSream, "zombieList", true);
			if(aux.length > MAX_FILAS*(MAX_COLUMNAS - 1) - plantList.getNumObjetos()) {
				throw new FileContentsException("ERROR: Lista de zombies más larga de lo posible.");
			}
			this.zombieList = new GameObjectList(aux, this, this.level);
			this.zombieList.comprobarLista(); //para comprobar que las coordenadas de los distintos zombies son distintas entre sí y no haya dos en la misma posición
			this.zombieManager.setNumZombiesVivos(this.zombieList.getNumObjetos());
		}
		catch(NumberFormatException e) {
			throw new FileContentsException("ERROR: El fichero contenía caracteres erróneos.");
		}
	}
	
	
	public String[] loadLine(BufferedReader inStream, String prefix, boolean isList) throws IOException, FileContentsException{
		String line = inStream.readLine().trim();
		
		if (!line.startsWith(prefix + ":")){ 						 // absence of the prefix is invalid
			throw new FileContentsException(wrongPrefixMsg + prefix);// cut the prefix and the following colon off the line
		}															 // then trim it to get the attribute contents
		
		String contentString = line.substring(prefix . length()+1).trim();
		String[] words;
		
		if (!contentString.equals("")) {	// the attribute contents are not empty
			if (!isList){					// split non list attribute contents into words // using 1 or more white spaces as separator
				words = contentString.split("\\s+");
				if (words.length != 1) { 	// a non list attribute with contents of more than one word is invalid
					throw new FileContentsException(lineTooLongMsg + prefix);
				}
			} 
			else { 							// split list attribute contents into words // using command or more white spaces as separator
				words = contentString.split(",\\s*"); // the attribute contents are empty
			}
		} 
		else {								// a non list attribute with empty contents is invalid
			if (!isList) {
				throw new FileContentsException(lineTooShortMsg + prefix);// a list attibute with empty contents is valid;
			}															  // use a zero length array to store its words

			words = new String[0];
		}
		
		return words;
	}


	public void printGame() {
		System.out.println(gamePrinter.printGame(this));
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


	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}


	public void setGamePrinter(GamePrinter gamePrinter) {
		this.gamePrinter = gamePrinter;		
	}
}
