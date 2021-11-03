//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package tp.practica2;

import java.util.Random;

import Printer.GameReleasePrinter;
import tp.practica2.Controller;
import tp.practica2.Game;

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
		default: 
			level = Level.EASY;
		}
		
		Game game = new Game(seed, level);
		Controller controller = new Controller(game);
		controller.setGamePrinter(new GameReleasePrinter(game, Game.MAX_FILAS, Game.MAX_COLUMNAS));
		controller.run();

	}

}
