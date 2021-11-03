//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package Command;

import Exception.CommandParseException;
import Printer.GameDebugPrinter;
import Printer.GameReleasePrinter;
import tp.practica3.Game;

public class PrintModeCommand extends Command {
	private static String COMMANDTEXT = "printmode";
	private static String COMMANDINFO = "[P]rintMode release|debug";
	private static String HELPINFO = "change print mode [Release|Debug]";
	private String modo;
	
	public PrintModeCommand() {
		super(COMMANDTEXT, COMMANDINFO, HELPINFO);
	}
	
	
	public PrintModeCommand(String modo) {
		super(COMMANDTEXT, COMMANDINFO, HELPINFO);
		this.modo = modo;
		
	}
	

	public boolean execute(Game game) throws CommandParseException {
		modo = modo.toLowerCase();
		switch(modo) {
		case "release":
		case "r":
			System.out.println("Modo de pintar: RELEASE\n");
			game.setGamePrinter(new GameReleasePrinter(game));
			return true;
			
		case "debug":
		case "d":
			System.out.println("Modo de pintar: DEBUG\n");
			game.setGamePrinter(new GameDebugPrinter(game));
			return true;
			
		default:
			throw new CommandParseException("ERROOOOR!!! El modo de pintar '" + modo + "' no existe. No se cambia el modo de pintar.");
		}
		
	}
	
	
	public Command parse(String[] commandWords) throws CommandParseException {
		Command command = null;
		
		if((commandWords[0].equals(this.commandName) || commandWords[0].equals(this.commandName.substring(0, 1)))) {
			if(commandWords.length != 2) {
				throw new CommandParseException("Número de argumentos erróneo. Se esperaba " + COMMANDINFO + ".");
			}
				
			command = new PrintModeCommand(commandWords[1]);
		}
		
		return command;
	}
}
