//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package Command;

import tp.practica2.Controller;
import tp.practica2.Game;

public class UpdateCommand extends NoParamsCommand {
	private static String COMMANDTEXT = "none";
	private static String COMMANDINFO = "none";
	private static String HELPINFO = "skips cycle";

	public UpdateCommand() {
		super(COMMANDTEXT, COMMANDINFO, HELPINFO);
	}

	
	public void execute(Game game, Controller controller) {
		game.update();
	}
}