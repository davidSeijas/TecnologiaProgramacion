//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package Command;

import tp.practica2.Controller;
import tp.practica2.Game;

public class HelpCommand extends NoParamsCommand {
	private static String COMMANDTEXT = "help";
	private static String COMMANDINFO = "[H]elp";
	private static String HELPINFO = "print this help message";

	public HelpCommand() {
		super(COMMANDTEXT, COMMANDINFO, HELPINFO);
	}

	public void execute(Game game, Controller controller) {
		controller.setNoPrintGameState();
		System.out.println(CommandParser.commandHelp());
	}
}