//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package Object;

import java.io.BufferedWriter;
import java.io.IOException;
import tp.practica3.Game;

public abstract class GameObject {
	protected int x;
	protected int y;
	protected int vida;
	protected int damage;
	protected int frecuencia;
	protected String objectName;
	protected String simbolo;
	protected int nextTime;
	protected Game game;
	
	
	public GameObject(int vida, int damage, int frecuencia, String objectName, String simbolo) { //constructor Zombies y Plantas
		this.vida = vida;
		this.damage = damage;
		this.frecuencia = frecuencia;
		this.objectName = objectName;
		this.simbolo = simbolo;
	}


	public void inicializar(Game game, int x, int y) { //completa la construccion de la planta 
		this.x = x;
		this.y = y;
		this.nextTime = game.getNumCiclos() + frecuencia;
		this.game = game;
	}


	public void store(BufferedWriter outStream) throws IOException {
		outStream.write(simbolo);
		outStream.write(":");
		outStream.write(Integer.toString(vida));
		outStream.write(":");
		outStream.write(Integer.toString(x));
		outStream.write(":");
		outStream.write(Integer.toString(y));
		outStream.write(":");
		outStream.write(Integer.toString((nextTime - game.getNumCiclos())));
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


	public void setVida(int vida) {
		this.vida = vida;
	}


	public void setNextTime(int nextTime) {
		this.nextTime = nextTime;
	}
}

