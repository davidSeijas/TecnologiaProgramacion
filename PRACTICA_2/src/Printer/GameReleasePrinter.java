//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package Printer;

import tp.practica2.Game;

public class GameReleasePrinter extends BoardPrinter implements GamePrinter{
	private static final int TAMANOCELDA = 7;
	
	public GameReleasePrinter(Game game, int dimX, int dimY) {
		super(game, dimX, dimY);
	}

	public void encodeGame(Game game) {
		board = new String[dimX][dimY];
		
		for(int i = 0; i < dimX; i++) {
			for(int j = 0; j < dimY; j++) {		
				board[i][j] = game.dameStringRelease(i, j);
			}
		}
	}

	
	public String printGame(Game game) {
		encodeGame(game);
		
		String string = "";
		string += game.dameAtributosRelease();
		string += boardToString(TAMANOCELDA);
		
		return string;
	}
}
