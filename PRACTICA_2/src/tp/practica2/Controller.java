//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package tp.practica2;

import java.util.Scanner;

import Command.Command;
import Command.CommandParser;
import Printer.GamePrinter;
import tp.practica2.Game;


public class Controller{
	private static final String unknownCommandMsg = "Invalid Command\n";
	private Game game;
	private Scanner scanner;
	private boolean salir;
	private boolean noPrint;
	private GamePrinter gamePrinter;

	public Controller (Game game) {
		this.game = game;
		this.scanner = new Scanner(System.in); //Para leer algo de la pantalla: int i = in.nextInt(); String s = in.nextLine();
		this.salir = false;
		this.noPrint = false;
	}
	
	
	public void run() {
		System.out.println("Random seed used: " + game.getSemilla());

		while (!game.finJuego() && !salir) {
			printGame();
			noPrint = false;
			
			System.out.println("Command > ");
			String[] words = scanner.nextLine().toLowerCase().trim().split(" ");
			Command command = CommandParser.parseCommand(words, this);
			
			if (command != null) {
				command.execute(game, this);
			}
			else {
				System.err.println(unknownCommandMsg);
				 noPrint = true;
			}
		}
	}

	
	private void printGame() {
		if(!noPrint) {
			System.out.println(gamePrinter.printGame(game));
		}	
	}

	
	public void setSalir(boolean salir) {
		this.salir = salir;
	}


	public void setNoPrintGameState() {
		this.noPrint = true;
	}


	public void setGamePrinter(GamePrinter gamePrinter) {
		this.gamePrinter = gamePrinter;
	}
}
