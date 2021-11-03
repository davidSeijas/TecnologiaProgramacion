//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package tp.practica1;

import java.util.Random;
import tp.practica1.Controller;
import tp.practica1.Game;

public class PlantsVsZombies {

	public static void main(String[] args) {
		long seed = (args.length > 1)?Long.parseLong(args[1]):new Random().nextInt(1000);
		Level level;
		
		switch(args[0].toLowerCase()) {
		case "easy":
			level = Level.EASY; break;
		case "hard":
			level = Level.HARD; break;
		case "insane":
			level = Level.INSANE; break;
		default: level = Level.EASY;
		}
		
		Game juego = new Game(seed, level);
		Controller controlador = new Controller(juego);
		controlador.run();

	}

}
