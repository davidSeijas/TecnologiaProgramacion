//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package Printer;

import tp.practica3.Game;

public class GameDebugPrinter extends BoardPrinter implements GamePrinter {
	private static final int TAMANOCELDA = 19;

	public GameDebugPrinter(Game game) {
		super(game, 1, game.getNumElemtos());
	}
	
	
	public void encodeGame(Game game) {
		board = new String[1][game.getNumElemtos()];
		
		for(int k = 0; k < game.getNumElemtos(); k++) {	
			board[0][k] = game.dameStringDebug(k);
		}	
	}


	public String printGame(Game game) { 
		dimY = game.getNumElemtos();
		encodeGame(game);
		
		String string = "";
		string += game.dameAtributosDebug();
		string += boardToString(TAMANOCELDA);
		
		return string;
	}
}
