//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package Command;

import Exception.CommandExecuteException;
import Exception.CommandParseException;
import Factory.PlantFactory;
import Object.Plant;
import tp.practica3.Game;

public class AddCommand extends Command {
	private static String COMMANDTEXT = "add";
	private static String COMMANDINFO = "[A]dd <plant> <x> <y>";
	private static String HELPINFO = "add plant in position (x, y)";
	private String plantName;
	private int x;
	private int y;

	public AddCommand() {
		super(COMMANDTEXT, COMMANDINFO, HELPINFO);
	}
	
	
	public AddCommand(String plantName, int x, int y) {
		super(COMMANDTEXT, COMMANDINFO, HELPINFO);
		this.plantName = plantName;
		this.x = x;
		this.y = y;
	}

	
	public boolean execute(Game game) throws CommandParseException, CommandExecuteException{
		Plant planta = PlantFactory.getPlant(plantName);
		
		if(planta != null) {
			if (game.anadirPlanta(planta, x, y)) {
				Command command = new UpdateCommand();
				command.execute(game);
				return true;
			}
		}
		else {
			throw new CommandParseException("No existe la planta " + plantName + ".");
		}
		
		return false;
	}

	
	public Command parse(String[] commandWords) throws CommandParseException {
		Command command = null;
		
		if (commandWords[0].equals(commandName) || commandWords[0].equals(commandName.substring(0, 1))) {
			if(commandWords.length != 4) {
				throw new CommandParseException ("Número de argumentos erróneo. Se esperaba " + COMMANDINFO + ".");
			}
				
			String plantName = commandWords[1];
				
			try {
				int x = Integer.parseInt(commandWords[2]);
				int y = Integer.parseInt(commandWords[3]);
				
				command = new AddCommand(plantName, x, y);
			
			}catch (NumberFormatException e){
				throw new CommandParseException("Formato de argumentos incorrectos. Se esperaba " + COMMANDINFO + ".");
			}		
		}
		
		return command;
	}
}
