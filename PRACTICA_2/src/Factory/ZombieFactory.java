//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package Factory;

import java.util.Random;

import Object.Zombie;
import Object.ZombieCaracubo;
import Object.ZombieComun;
import Object.ZombieDeportista;

public class ZombieFactory {
	private static Zombie[] availableZombies = {
			new ZombieComun(),
			new ZombieCaracubo(),
			new ZombieDeportista(),
	};
	
	
	public static Zombie getZombie(String zombieName){
		Zombie zombie = null;
		
		switch(zombieName.toLowerCase()) {
		case "[z]ombie comun":
		case "zombiecomun":
		case "z":
			zombie = new ZombieComun(); 
			break;
			
		case "[z]ombie caracubo":
		case "zombiecaracubo":
		case "w":
			zombie = new ZombieCaracubo(); 
			break;
			
		case "[z]ombie deportista":
		case "zombiedeportista":
		case "x":
			zombie = new ZombieDeportista(); 
			break;		
		}
		
		return zombie;
	}
	
	
	public static Zombie getZombieAleatorio(Random rand) {
		int r = rand.nextInt(availableZombies.length - 1);
		String zombieName = availableZombies[r].getObjectName();
		Zombie zombie = getZombie(zombieName);
		
		return zombie;
	}
		
		
	public static String listOfAvailableZombies() {
		StringBuilder listaZombies = new StringBuilder();
		String string = "";
		
		for(int i = 0; i < availableZombies.length; ++i) {
			listaZombies.append(availableZombies[i].dameMessage());

			string = listaZombies.toString();
		}
			
		return string;
	}
}