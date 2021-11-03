//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package Command;

import tp.practica3.Game;

public class UpdateCommand extends NoParamsCommand {
	private static String COMMANDTEXT = "none";
	private static String COMMANDINFO = "[N]one";
	private static String HELPINFO = "skips cycle";

	public UpdateCommand() {
		super(COMMANDTEXT, COMMANDINFO, HELPINFO);
	}

	
	public boolean execute(Game game) {
		game.update();
		return true;
	}
}