//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package Command;

import Factory.ZombieFactory;
import tp.practica3.Game;

public class ZombieListCommand extends NoParamsCommand {
	private static String COMMANDTEXT = "zombielist";
	private static String COMMANDINFO = "[Z]ombieList";
	private static String HELPINFO = "print the list of zombies";

	public ZombieListCommand() {
		super(COMMANDTEXT, COMMANDINFO, HELPINFO);
	}

	
	public boolean execute(Game game) {
		System.out.println(ZombieFactory.listOfAvailableZombies());
		return false;
	}
}