//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package Command;

import Printer.GameDebugPrinter;
import Printer.GameReleasePrinter;
import tp.practica2.Controller;
import tp.practica2.Game;

public class PrintModeCommand extends Command {
	private static String COMMANDTEXT = "printmode";
	private static String COMMANDINFO = "[P]rintMode";
	private static String HELPINFO = "change print mode [Release|Debug]";
	private String modo;
	
	public PrintModeCommand() {
		super(COMMANDTEXT, COMMANDINFO, HELPINFO);
	}
	
	
	public PrintModeCommand(String modo) {
		super(COMMANDTEXT, COMMANDINFO, HELPINFO);
		this.modo = modo;
		
	}
	

	public void execute(Game game, Controller controller) {
		modo = modo.toLowerCase();
		switch(modo) {
		case "release":
		case "r":
			System.out.println("Modo de pintar: RELEASE\n");
			controller.setGamePrinter(new GameReleasePrinter(game, Game.MAX_FILAS, Game.MAX_COLUMNAS));
			break;
			
		case "debug":
		case "d":
			System.out.println("Modo de pintar: DEBUG\n");
			controller.setGamePrinter(new GameDebugPrinter(game, 1, game.getNumElemtos()));
			break;
			
		default:
			System.out.println("ERROOOOR!!! Ese modo de pintar no existe\n");
			System.out.println("No se cambia el modo de pintar\n");
			break;
		}
		
	}
	
	
	public Command parse(String[] commandWords, Controller controller) {
		if(commandWords.length == 2 && (commandWords[0].equals(this.commandName) || commandWords[0].equals(this.commandName.substring(0, 1)))) {
			Command command = new PrintModeCommand(commandWords[1]);
			return command;
		}
		
		return null;
	}
}
