//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package Command;

import Factory.PlantFactory;
import tp.practica2.Controller;
import tp.practica2.Game;

public class ListCommand extends NoParamsCommand {
	private static String COMMANDTEXT = "list";
	private static String COMMANDINFO = "[L]ist";
	private static String HELPINFO = "print the list of available plants";

	public ListCommand() {
		super(COMMANDTEXT, COMMANDINFO, HELPINFO);
	}

	
	public void execute(Game game, Controller controller) {
		controller.setNoPrintGameState();
		System.out.println(PlantFactory.listOfAvilablePlants());
	}
}
