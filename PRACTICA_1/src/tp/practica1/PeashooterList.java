//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package tp.practica1;

public class PeashooterList {
	Peashooter[] listaGuisantes;
	private int numGuisantes;
	

	public PeashooterList() {
		this.listaGuisantes= new Peashooter[32];
		this.numGuisantes = 0;
	}


	public void anadirPeashooter(Peashooter peashooter) {
		listaGuisantes[numGuisantes] = peashooter;
		++numGuisantes;
	}


	public void update() {
		for(int i = 0; i < numGuisantes; ++i) {
			listaGuisantes[i].update();
		}
	}


	public void restarVida(int x, int y, int damage) {
		if(getPosition(x, y) != null) {
			getPosition(x, y).restarVida(damage);
		}
	}
	
	
	public String toString(int i, int j) {
		String string = "";
		
		if(getPosition(i, j) != null) {
			string = getPosition(i, j).toString();
		}
		
		return string;
	}	
	
	
	public boolean isPositionEmpty(int x, int y) {
		Peashooter peashooter = getPosition(x,y);
		
		if(peashooter != null) {
			return false;
		}
		
		return true;
	}
	
	
	private Peashooter getPosition(int x, int y) {
		for(int i = 0; i < numGuisantes; ++i) {	
			if(listaGuisantes[i].getX() == x && listaGuisantes[i].getY() == y && listaGuisantes[i].getVida() > 0) {
				return listaGuisantes[i];	
			}		
		}
		return null;
	}

	
	public int getNumGuisantes() {
		return numGuisantes;
	}


	public void setNumGuisantes(int numGuisantes) {
		this.numGuisantes = numGuisantes;
	}
}

