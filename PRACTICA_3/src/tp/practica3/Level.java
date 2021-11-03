//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package tp.practica3;

public enum Level {
	EASY(3, 0.1), HARD(5, 0.2), INSANE(10, 0.3);
	
	private int numZombies;
	private double frecZombies;
	
	private Level(int numZombies, double frecZombies) {
		this.numZombies = numZombies;
		this.frecZombies = frecZombies;
	}
	
	
	public int getNumZombies() {
		return numZombies;
	}

	
	public double getFrecZombies() {
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
//	
//	
//	public static Level toLevel(String nivel) {
//		Level level;
//		
//		switch(nivel.toLowerCase()) {
//		case "easy":
//			level = EASY;
//			break;
//	
//		case "hard":
//			level = HARD;
//			break;
//	
//		case "insane":
//			level = INSANE;
//			break;
//			
//		default:
//			level = EASY;
//			break;
//		}
//		
//		return level;
//	}
	
	
	public static Level parse(String inputString) {
		for (Level level : Level.values()) {
			if (level.name().equalsIgnoreCase(inputString)) {
				return level;
			}
		}
		return null;
	}
	
	
	public static String all (String separator) {
		StringBuilder sb = new StringBuilder();
		for (Level level : Level. values() )
		sb. append(level.name() + separator);
		String allLevels = sb.toString();
		return allLevels.substring(0, allLevels.length()-separator.length());
		}
}
