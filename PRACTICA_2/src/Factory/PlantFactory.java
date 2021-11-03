//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package Factory;

import Object.Nuez;
import Object.Peashooter;
import Object.Petacereza;
import Object.Plant;
import Object.Sunflower;

public class PlantFactory {
	private static Plant[] availablePlants = {
			new Sunflower(),
			new Peashooter(),
			new Petacereza(),
			new Nuez(),
	};
	
	
	public static Plant getPlant(String plantName){
		Plant planta = null;
		
		switch(plantName.toLowerCase()) {
		case "sunflower":
		case "s":
			planta = new Sunflower(); 
			break;
		
		case "peashooter":
		case "p":
			planta = new Peashooter(); 
			break;
			
		case "petacereza":
		case "c":
			planta = new Petacereza(); 
			break;
			
		case "nuez":
		case "n":
			planta = new Nuez(); 
			break;
			
		}
		
		return planta;
	}
	
	
	public static String listOfAvilablePlants() {
		StringBuilder listaPlantas = new StringBuilder();
		String string = "";
		
		for(int i = 0; i < availablePlants.length; ++i) {
			listaPlantas.append(availablePlants[i].dameMessage());

			string = listaPlantas.toString();
		}
			
		return string;
	}
}

