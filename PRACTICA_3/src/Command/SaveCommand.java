package Command;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import Exception.CommandExecuteException;
import Exception.CommandParseException;
import Exception.FileContentsException;
import Printer.MyStringUtils;
import tp.practica3.Game;

public class SaveCommand extends Command{
	private static String COMMANDTEXT = "save";
	private static String COMMANDINFO = "[S]ave <filename>";
	private static String HELPINFO = "Save the state of the game to a file";
	private String fileName;
	
	public SaveCommand() {
		super(COMMANDTEXT, COMMANDINFO, HELPINFO);
	}
	
	
	public SaveCommand(String fileName) {
		super(COMMANDTEXT, COMMANDINFO, HELPINFO);
		this.fileName = fileName;
	}

	
	public boolean execute(Game game) throws CommandParseException, CommandExecuteException {
		if (!MyStringUtils.isValidFilename(fileName)) {
			throw new CommandParseException("Invalid Filename: El nombre del fichero tiene caracteres inválidos."); 
		}
		
		try (BufferedWriter outStream = new BufferedWriter(new FileWriter(fileName + ".dat"))){
			outStream.write("Plants Vs Zombies v3.0");
			outStream.newLine();
			outStream.newLine();
			game.store(outStream);
		}
		catch(IOException | FileContentsException e) {
			throw new CommandExecuteException("Error al guardar el estado de la partida. " + e + ".");
		}
		
		System.out.println("Game successfully saved in file " + fileName + ".dat. Use the load command to reload it\n");
		return false;
	}

	
	public Command parse(String[] commandWords) throws CommandParseException {
		Command command = null;
		
		if((commandWords[0].equals(this.commandName) || commandWords[0].equals(this.commandName.substring(0, 1)))) {
			if(commandWords.length != 2) {
				throw new CommandParseException("Número de argumentos erróneo. Se esperaba " + COMMANDINFO + ".");
			}
			if(commandWords[1].equals(COPIASEGURIDAD)) {
				throw new CommandParseException("No se permite guardar nada en ese fichero.");
			}
			
			command = new SaveCommand(commandWords[1]);				
		}
		
		return command;
	}
}