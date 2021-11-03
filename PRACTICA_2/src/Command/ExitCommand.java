//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package Command;

import tp.practica2.Controller;
import tp.practica2.Game;

public class ExitCommand extends NoParamsCommand {
	private static String COMMANDTEXT = "exit";
	private static String COMMANDINFO = "[E]xit";
	private static String HELPINFO = "terminates the programm";

	public ExitCommand() {
		super(COMMANDTEXT, COMMANDINFO, HELPINFO);
	}

	public void execute(Game game, Controller controller) {
		controller.setSalir(true);
		System.out.println("GAME OVER!"); 
	}
}
