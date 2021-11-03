//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package Command;

import tp.practica2.Controller;
import tp.practica2.Game;

public class ResetCommand extends NoParamsCommand {
	private static String COMMANDTEXT = "reset";
	private static String COMMANDINFO = "[R]eset";
	private static String HELPINFO = "resets game";

	public ResetCommand() {
		super(COMMANDTEXT, COMMANDINFO, HELPINFO);
	}

	
	public void execute(Game game, Controller controller) {
		game.reset();
		//controller.resetGamePrinter();
	}
}
