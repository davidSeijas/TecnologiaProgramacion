//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package tp.practica3;

import java.util.Scanner;

import Command.Command;
import Command.CommandParser;
import Exception.CommandExecuteException;
import Exception.CommandParseException;
import tp.practica3.Game;


public class Controller{
	private static final String unknownCommandMsg = "Invalid Command. Usa 'help' para ver la lista de comandos disponibles\n";
	private static final String prompt = "Command> ";
	private Game game;
	private Scanner scanner;

	public Controller (Game game) {
		this.game = game;
		this.scanner = new Scanner(System.in); //Para leer algo de la pantalla: int i = scanner.nextInt(); String s = scanner.nextLine();
	}
	
	
	public void run() {
		System.out.println("Random seed used: " + game.getSemilla());
		printGame();

		while (!game.finJuego()){
			System.out.print(prompt);
			String[] words = scanner.nextLine().trim().split(" ");
			
			try {
				Command command = CommandParser.parseCommand(words);
				if (command != null) {
					if (command.execute(game)) {
						printGame();
					}
				} 
				else {
					System.out.println(unknownCommandMsg);
				}
			}catch (CommandParseException | CommandExecuteException exception) {
					System.out.format(exception + " %n %n");
					//System.out.println(exception + "\n");
				}
			}

	}

	
	private void printGame() {
		game.printGame();	
	}
}
