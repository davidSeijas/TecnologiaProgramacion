package Command;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Exception.CommandExecuteException;
import Exception.CommandParseException;
import Exception.FileContentsException;
import Printer.GameReleasePrinter;
import Printer.MyStringUtils;
import tp.practica3.Game;

public class LoadCommand extends Command{
	private static String COMMANDTEXT = "load";
	private static String COMMANDINFO = "[Lo]ad <filename>";
	private static String HELPINFO = "Load the state of the game from a file";
	private String fileName;
	
	public LoadCommand() {
		super(COMMANDTEXT, COMMANDINFO, HELPINFO);
	}
	
	
	public LoadCommand(String fileName) {
		super(COMMANDTEXT, COMMANDINFO, HELPINFO);
		this.fileName = fileName;
	}

	
	public boolean execute(Game game) throws CommandParseException, CommandExecuteException {
		if(!fileName.equals(COPIASEGURIDAD + ".dat")) {
			try{
				Command command = new SaveCommand(COPIASEGURIDAD);
				command.execute(game);
			}
			catch(CommandExecuteException e) {
				throw new CommandExecuteException(e.getMessage() + " No se ha podido realizar copia de seguridad.");
			}
		}
		
		if (!MyStringUtils.isValidFilename(fileName)) {
			throw new CommandParseException("Invalid Filename: El nombre del fichero tiene caracteres inválidos"); 
		}
		if(!(MyStringUtils.isReadable(fileName) && MyStringUtils.fileExists(fileName))) {
			throw new CommandParseException("File not found: El fichero no existe o no se puede leer");
		}
		
		try (BufferedReader inSream = new BufferedReader(new FileReader(fileName))) {
			inSream.readLine(); //lee la cabecera: Plants Vs Zombies v3.0
			inSream.readLine(); //lee linea en blanco
			game.load(inSream);
		}
		catch(IOException | FileContentsException e) {
			System.out.println("Error al cargar la partida. " + e + "\n");
			
			Command command = new LoadCommand(COPIASEGURIDAD + ".dat");
			command.execute(game);
			game.setGamePrinter(new GameReleasePrinter(game));
			game.printGame();
			
			throw new CommandExecuteException("Se ha recuperado la partida anterior.\n");
		}
		
		System.out.println("Game successfully loaded from file " + fileName + ".\n");
		game.setGamePrinter(new GameReleasePrinter(game));
		return true;
	}

	
	public Command parse(String[] commandWords) throws CommandParseException {
		Command command = null;
		
		if((commandWords[0].equals(this.commandName) || commandWords[0].equals(this.commandName.substring(0, 2)))) {
			if(commandWords.length != 2) {
				throw new CommandParseException("Número de argumentos erróneo. Se esperaba " + COMMANDINFO + ".");
			}
			if(commandWords[1].equals(COPIASEGURIDAD + ".dat")) {
				throw new CommandParseException("No se permite cargar en ese fichero.");
			}
			
			command = new LoadCommand(commandWords[1]);	
		}
		
		return command;
	}
}