//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package Command;

import Factory.ZombieFactory;
import tp.practica2.Controller;
import tp.practica2.Game;

public class ZombieListCommand extends NoParamsCommand {
	private static String COMMANDTEXT = "zombielist";
	private static String COMMANDINFO = "[Z]ombieList";
	private static String HELPINFO = "print the list of zombies";

	public ZombieListCommand() {
		super(COMMANDTEXT, COMMANDINFO, HELPINFO);
	}

	
	public void execute(Game game, Controller controller) {
		controller.setNoPrintGameState();
		System.out.println(ZombieFactory.listOfAvailableZombies());
	}
}