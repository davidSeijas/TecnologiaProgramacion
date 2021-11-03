//MIGUEL DERECHO PRIETO
//DAVID SEIJAS PEREZ

package tp.practica2;

public class SuncoinManager {
	private int soles;
	
	public SuncoinManager() {
		this.soles = 50;
	}
	
	public void sumarSoles(int solesGenerados) {
		soles += solesGenerados;		
	}
	
	public void restarSoles(int coste) {
		soles -= coste;
	}

	public int getSoles() {
		return soles;
	}
}
