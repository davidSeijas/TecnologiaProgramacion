//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package Command;

import tp.practica2.Controller;
import tp.practica2.Game;

public abstract class Command {
	private String helpText;
	private String commandText;
	protected final String commandName;
	
	public Command(String commandText, String commandInfo, String helpInfo){
		this.commandText = commandInfo;
		this.helpText = helpInfo;
		String[] commandInfoWords = commandText.split(" ");
		this.commandName = commandInfoWords[0];
	}
	
	public abstract void execute(Game game, Controller controller);
	
	public abstract Command parse(String[] commandWords, Controller controller);
	
	public String helpText(){
		return " " + commandText + ": " + helpText + ".\n";
	}
}
