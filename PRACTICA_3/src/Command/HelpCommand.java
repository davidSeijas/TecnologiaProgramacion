//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package Command;

import tp.practica3.Game;

public class HelpCommand extends NoParamsCommand {
	private static String COMMANDTEXT = "help";
	private static String COMMANDINFO = "[H]elp";
	private static String HELPINFO = "print this help message";

	public HelpCommand() {
		super(COMMANDTEXT, COMMANDINFO, HELPINFO);
	}

	public boolean execute(Game game) {
		System.out.println(CommandParser.commandHelp());
		return false;
	}
}