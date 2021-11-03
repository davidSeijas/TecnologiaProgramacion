//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package Command;

import tp.practica2.Controller;
import tp.practica2.Game;

public abstract class NoParamsCommand extends Command {

	public NoParamsCommand(String commandText, String commandInfo, String helpInfo) {
		super(commandText, commandInfo, helpInfo);
	}


	public abstract void execute(Game game, Controller controller);

	
	public Command parse(String[] commandWords, Controller controller) {
		Command command = null;
		
		if(commandWords.length == 1) {
			if(commandWords[0].equals("")){
				commandWords[0] = "none";
			}
			
			if(commandWords[0].equals(commandName) || commandWords[0].equals(commandName.substring(0, 1))) {
				command = this;
			}
		}
		
		return command;
	}

}
