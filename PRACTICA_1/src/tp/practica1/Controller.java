//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package tp.practica1;

import java.util.Scanner;
import tp.practica1.Game;


public class Controller{
	private Game juego;
	private Scanner in;

	public Controller (Game juego) {
		this.juego = juego;
		in = new Scanner(System.in); //Para leer algo de la pantalla: int i = in.nextInt(); String s = in.nextLine();
	}
	
	public void run() {
		System.out.println("Random seed used: " + juego.getSemilla());
		juego.mostrarTablero();
		boolean salir = false;
		
		while(!juego.finJuego() && !salir) { //!this.juegoTerminado()
			System.out.println("Command > ");
			String[]words = in.nextLine().toLowerCase().trim().split(" ");
			String opcion = words[0];
			boolean cambio = false;
				
			switch(opcion) {
			case "add":
			case "a":
				int x = Integer.parseInt(words[2]);
				int y = Integer.parseInt(words[3]);
					
				switch(words[1]) {
				case "S": 
				case "s":
					cambio = juego.anadirSunflower(x, y);
					break;
						
				case "P": 
				case "p":
					cambio = juego.anadirPeashooter(x, y);
					break;
					
				default:
					System.out.println("Invalid plant\n");
					break;
				}
				break;
				
			case "reset": 
			case "r": 
				cambio = true;
				juego.reset();
				break;
			
			case "list": 
			case "l": 
				System.out.println("[S]unflower: Cost: 20 suncoins Harm: 0\r\n" + 
					"[P]eashooter: Cost: 50 suncoins Harm: 1\r\n"); 
				break;
				
			case "help": 
			case "h": 
				System.out.println("Command > help\r\n" + 
					"Add <plant> <x> <y>: Adds a plant in position x, y.\r\n" + 
					"List: Prints the list of available plants.\r\n" + 
					"Reset: Starts a new game.\r\n" + 
					"Help: Prints this help message.\r\n" + 
					"Exit: Terminates the program.\r\n" + 
					"[none]: Skips cycle.\n"); 
				break;
				
			case "exit": 
			case "e": 
				cambio = true;
				salir = true;
				System.out.println("GAME OVER!"); 
				break;
					 
			case "none": 
			case "n":
			case "":
				cambio = true;
				break;
					
			default: 
				System.out.println("Invalid command\n");
				opcion = "ERROR";
				break;
			}
			
			if(!juego.finJuego() && !salir && cambio) {
				if(!(opcion.equals("reset") || opcion.equals("r"))) {
					juego.update();
				}
				juego.mostrarTablero();
			}
		}			
	}
}
