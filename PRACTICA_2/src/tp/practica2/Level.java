//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package tp.practica2;

public enum Level {
	EASY, HARD, INSANE;
	
	public int numZombies() {
		int numZombies;
	
		switch(this) {
		case EASY:
			numZombies = 3;
			break;
		
		case HARD:
			numZombies = 5;
			break;
		
		case INSANE:
			numZombies = 10;
			break;
		
		default:
			numZombies = 0;
			break;		
		}
	
		return numZombies;
	}
	
	public double frecZombies() {
		double frecZombies;
	
		switch(this) {
		case EASY:
			frecZombies = 0.1;
			break;
		
		case HARD:
			frecZombies = 0.2;
			break;
		
		case INSANE:
			frecZombies = 0.3;
			break;
		
		default:
			frecZombies = 0;
			break;		
		}
	
		return frecZombies;
	}
	
	
	public String toString() {
		String level = "";
		
		switch(this) {
		case EASY:
			level = "EASY";
			break;
	
		case HARD:
			level = "HARD";
			break;
	
		case INSANE:
			level = "INSANE";
			break;		
		}
		
		return level;
	}
}
