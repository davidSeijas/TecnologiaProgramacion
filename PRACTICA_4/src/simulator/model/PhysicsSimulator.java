package simulator.model;

import java.util.ArrayList;
import java.util.List;


public class PhysicsSimulator {
	private double tiempoActual;
	private double tiempoPaso;
	private GravityLaws leyesGravedad;
	private List<Body> bodies;
	
	public PhysicsSimulator(double tPaso, GravityLaws leyes) throws IllegalArgumentException {
		if(tPaso <= 0 || leyes == null) {
			throw new IllegalArgumentException("Parámetros erróneos");
		}
		
		this.tiempoActual = 0.0;
		this.tiempoPaso = tPaso;
		this.leyesGravedad = leyes;
		this.bodies = new ArrayList<Body>();
	}
	
	
	public void advance() { // throws ImpossiblePhysicsException
		leyesGravedad.apply(bodies);
		
		for(Body b : bodies) {
			b.move(tiempoPaso);
		}
		
		tiempoActual += tiempoPaso;
	}
	
	
	public void addBody(Body body) throws IllegalArgumentException{
		for(Body b : bodies) {
			if(b.equals(body)) {
				throw new IllegalArgumentException("El cuerpo ya esta en el simulador");
			}
		}
		
		bodies.add(body);
	}
	
	
	public String toString() {
		StringBuilder string = new StringBuilder();
		
		string.append("{ \"time\": ").append(tiempoActual).append(", \"bodies\": [");
		for(int i = 0; i < bodies.size(); ++i) {
			string.append(bodies.get(i).toString());
			
			if(i != bodies.size() - 1) {
				string.append(", ");
			}
		}
		string.append(" ] }");
		
		return string.toString();
	}
}
