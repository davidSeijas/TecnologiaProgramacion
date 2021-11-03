//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package Command;

import tp.practica3.Game;

public class ExitCommand extends NoParamsCommand {
	private static String COMMANDTEXT = "exit";
	private static String COMMANDINFO = "[E]xit";
	private static String HELPINFO = "terminates the programm";

	public ExitCommand() {
		super(COMMANDTEXT, COMMANDINFO, HELPINFO);
	}

	public boolean execute(Game game) {
		game.setGameOver(true);
		System.out.println("****** GAME OVER!: User exit ******"); 
		return false;
	}
}
