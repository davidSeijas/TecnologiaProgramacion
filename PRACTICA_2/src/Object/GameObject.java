//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package Object;

import java.util.Random;

import tp.practica2.Game;

public abstract class GameObject {
	protected int x;
	protected int y;
	protected int vida;
	protected int damage;
	protected int frecuencia;
	protected String objectName;
	protected int nextTime;
	protected Game game;
	
	
	public GameObject(int vida, int damage, int frecuencia, String objectName) { //constructor Zombies y Plantas
		this.vida = vida;
		this.damage = damage;
		this.frecuencia = frecuencia;
		this.objectName = objectName;
	}


	public void inicializarPlanta(Game game, int x, int y) { //completa la construccion de la planta 
		this.x = x;
		this.y = y;
		this.nextTime = game.getNumCiclos() + frecuencia;
		this.game = game;
	}
	
	
	public void inicializarZombie(Random rand, Game game) { //completa la construccion del zombie
		this.x = rand.nextInt(Game.MAX_FILAS);
		this.y = Game.MAX_COLUMNAS - 1;
		this.nextTime = game.getNumCiclos() + frecuencia;
		this.game = game;
	}
	
	
	public abstract void update();
		
	public abstract String toStringRelease();
	
	public abstract String toStringDebug();
	
	public abstract String dameMessage();

	public abstract void restarVida(int damage);
		
	
	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}


	public int getVida() {
		return vida;
	}


	public String getObjectName() {
		return objectName;
	}
}

