//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package Object;

public abstract class Plant extends GameObject{
	protected int coste;
	
	public Plant(int vida, int damage, int frecuencia, int coste, String plantName) {
		super(vida, damage, frecuencia, plantName);
		this.coste = coste;
	}
	
	
	public abstract void update();
		
	public abstract String toStringRelease();
	
	public abstract String toStringDebug();
	
	
	public void restarVida(int damage) {
		vida -= damage;
	}
	
	
	public String dameMessage() {
		String string = "";
		
		string = objectName + ": Cost: " + coste + " Harm: " + damage + "\n";
		
		return string;
	}


	public int getCoste() {
		return coste;
	}
}

