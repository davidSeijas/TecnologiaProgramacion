//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package Command;

import Factory.PlantFactory;
import Object.Plant;
import tp.practica2.Controller;
import tp.practica2.Game;

public class AddCommand extends Command {
	private static String COMMANDTEXT = "add";
	private static String COMMANDINFO = "[A]dd";
	private static String HELPINFO = "add flower";
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

	
	public void execute(Game game, Controller controller) {
		Plant planta = PlantFactory.getPlant(plantName);
		
		if(planta != null) {
			if (game.anadirPlanta(planta, x, y)) {
				Command command = new UpdateCommand();
				command.execute(game, controller);	
			}
		}
	}

	
	public Command parse(String[] commandWords, Controller controller) {
		if(commandWords.length == 4 && (commandWords[0].equals(commandName) || commandWords[0].equals(commandName.substring(0, 1)))) {
			String plantName = commandWords[1];
			int x = Integer.parseInt(commandWords[2]);
			int y = Integer.parseInt(commandWords[3]);
			
			Command command = new AddCommand(plantName, x, y);
			
			return command;
		}
		
		return null;
	}
}
