//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package Command;

import tp.practica3.Game;

public class ResetCommand extends NoParamsCommand {
	private static String COMMANDTEXT = "reset";
	private static String COMMANDINFO = "[R]eset";
	private static String HELPINFO = "resets game";

	public ResetCommand() {
		super(COMMANDTEXT, COMMANDINFO, HELPINFO);
	}

	
	public boolean execute(Game game) {
		game.reset();
		return true;
	}
}
