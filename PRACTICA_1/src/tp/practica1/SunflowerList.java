//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package tp.practica1;

public class SunflowerList {
	Sunflower[] listaGirasoles;
	private int numGirasoles;
	

	public SunflowerList() {
		this.listaGirasoles= new Sunflower[32];
		this.numGirasoles = 0;
	}


	public void anadirSunflower(Sunflower sunflower) {
		listaGirasoles[numGirasoles] = sunflower;
		++numGirasoles;
	}


	public void update() {
		for(int i = 0; i < numGirasoles; ++i) {
			listaGirasoles[i].update();
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
		Sunflower sunflower = getPosition(x,y);
		
		if(sunflower != null) {
			return false;
		}
		
		return true;
	}
	
	
	private Sunflower getPosition(int x, int y) {
		for(int i = 0; i < numGirasoles; ++i) {	
			if(listaGirasoles[i].getX() == x && listaGirasoles[i].getY() == y && listaGirasoles[i].getVida() > 0) {
				return listaGirasoles[i];	
			}		
		}
		return null;
	}

	
	public int getNumGirasoles() {
		return numGirasoles;
	}
}
