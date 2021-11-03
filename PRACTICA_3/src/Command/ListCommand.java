//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package Command;

import Factory.PlantFactory;
import tp.practica3.Game;

public class ListCommand extends NoParamsCommand {
	private static String COMMANDTEXT = "list";
	private static String COMMANDINFO = "[L]ist";
	private static String HELPINFO = "print the list of available plants";

	public ListCommand() {
		super(COMMANDTEXT, COMMANDINFO, HELPINFO);
	}

	
	public boolean execute(Game game) {
		System.out.println(PlantFactory.listOfAvilablePlants());
		return false;
	}
}
