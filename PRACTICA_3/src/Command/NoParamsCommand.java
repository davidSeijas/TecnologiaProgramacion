//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package Command;

import Exception.CommandExecuteException;
import Exception.CommandParseException;
import tp.practica3.Game;

public abstract class NoParamsCommand extends Command {

	public NoParamsCommand(String commandText, String commandInfo, String helpInfo) {
		super(commandText, commandInfo, helpInfo);
	}


	public abstract boolean execute(Game game) throws CommandParseException, CommandExecuteException;

	
	public Command parse(String[] commandWords) throws CommandParseException{
		Command command = null;
		
		if(commandWords[0].equals("")){
			commandWords[0] = "none";
		}
			
		if(commandWords[0].equals(commandName) || commandWords[0].equals(commandName.substring(0, 1))) {
			if(commandWords.length != 1) {
				throw new CommandParseException("Número de argumentos erróneo. Se esperaba 1 argumento.");
			}
			command = this;
		}
		
		
		return command;
	}

}
