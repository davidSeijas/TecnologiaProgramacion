//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package tp.practica3;

import java.util.Random;

import Printer.GameReleasePrinter;
import tp.practica3.Controller;
import tp.practica3.Game;

public class PlantsVsZombies {

	public static void main(String[] args) {
		if(args.length > 2 || args.length == 0) {
			System.out.println("Usage: plantsVsZombies <EASY|HARD|INSANE> [seed]\n");
		}
		else{
			try{
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
					level = null; break;
				}
			
				if(level != null) {
					Game game = new Game(seed, level);
					Controller controller = new Controller(game);
					game.setGamePrinter(new GameReleasePrinter(game));
					controller.run();
				}
				else {
					System.out.println("Usage: plantsVsZombies <EASY|HARD|INSANE> [seed]: level must be one of: EASY, HARD, INSANE\n");
				}	
			}
			catch(NumberFormatException e){
				System.out.println("Usage: plantsVsZombies <EASY|HARD|INSANE> [seed]: the seed must be a number" + e);
			}
		}
	}
}

