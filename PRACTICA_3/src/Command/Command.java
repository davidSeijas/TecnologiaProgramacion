//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package Command;

import Exception.CommandExecuteException;
import Exception.CommandParseException;
import tp.practica3.Game;

public abstract class Command {
	public static final String COPIASEGURIDAD = "copiaSeguridad";
	private String helpText;
	private String commandText;
	protected final String commandName;
	
	public Command(String commandText, String commandInfo, String helpInfo){
		this.commandText = commandInfo;
		this.helpText = helpInfo;
		String[] commandInfoWords = commandText.split(" ");
		this.commandName = commandInfoWords[0];
	}
	
	public abstract boolean execute(Game game) throws CommandParseException, CommandExecuteException;
	
	public abstract Command parse(String[] commandWords) throws CommandParseException;
	
	public String helpText(){
		return " " + commandText + ": " + helpText + ".\n";
	}
}
