//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package Command;

import Exception.CommandParseException;

public class CommandParser {
	private static Command[] availableCommands = {
		new AddCommand(),
		new HelpCommand(),
		new ResetCommand(),
		new ExitCommand(),
		new ListCommand(),
		new ZombieListCommand(),
		new UpdateCommand(),
		new PrintModeCommand(),
		new SaveCommand(),
		new LoadCommand(),
	};

	public static Command parseCommand(String[] commandWords) throws CommandParseException {
		Command command = null;
		for(int i = 0; i < availableCommands.length && command == null; ++i) {
			command = availableCommands[i].parse(commandWords);
		}
		
		return command;
	}
	
	
	public static String commandHelp() {
		String string = "";
		for(int i = 0; i < availableCommands.length; ++i) {
			string += availableCommands[i].helpText();
		}
		return string;
	}
}
