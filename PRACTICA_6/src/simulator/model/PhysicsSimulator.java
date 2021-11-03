package simulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PhysicsSimulator {
	private double tiempoActual;
	private double tiempoPaso;
	private GravityLaws gravityLaws;
	private List<Body> bodies;
	private List<Body> bodiesRO;
	private List<SimulatorObserver> observers;
	
	public PhysicsSimulator(double tPaso, GravityLaws leyes) throws IllegalArgumentException {
		if(tPaso <= 0 || leyes == null) {
			throw new IllegalArgumentException("Parámetros erróneos");
		}
		
		this.tiempoActual = 0.0;
		this.tiempoPaso = tPaso;
		this.gravityLaws = leyes;
		this.bodies = new ArrayList<Body>();
		this.bodiesRO = Collections.unmodifiableList(bodies);
		this.observers = new ArrayList<SimulatorObserver>();
	}
	
	
	public void advance() { // throws ImpossiblePhysicsException
		gravityLaws.apply(bodies);
		
		for(Body b : bodies) {
			b.move(tiempoPaso);
		}
		
		tiempoActual += tiempoPaso;
		
		for(SimulatorObserver o : observers) {
			o.onAdvance(bodiesRO, tiempoActual);
		}
	}
	
	
	public void reset() {
		bodies.clear();
		tiempoActual = 0.0;
		
		for(SimulatorObserver o : observers) {
			o.onReset(bodiesRO, tiempoActual, tiempoPaso, gravityLaws.toString());
		}
	}
	
	
	public void setDeltaTime(double dt) throws IllegalArgumentException{
		if(dt <= 0) {
			throw new IllegalArgumentException("Tiempo invalido\n");
		}
		tiempoPaso = dt;
		
		for(SimulatorObserver o : observers) {
			o.onDeltaTimeChanged(tiempoPaso);
		}
	}
	
	
	public void setGravityLaws(GravityLaws gl) throws IllegalArgumentException{
		if(gl == null) {
			throw new IllegalArgumentException("Leyes invalidas\n");
		}
		gravityLaws = gl;
		
		for(SimulatorObserver o : observers) {
			o.onGravityLawChanged(gravityLaws.toString());
		}
	}
	
	
	public void addBody(Body body) throws IllegalArgumentException{
		for(Body b : bodies) {
			if(b.equals(body)) {
				throw new IllegalArgumentException("El cuerpo ya esta en el simulador");
			}
		}
		
		bodies.add(body);
		
		for(SimulatorObserver o : observers) {
			o.onBodyAdded(bodiesRO, body);
		}
	}
	
	
	public void addObserver(SimulatorObserver o) {
		if(!observers.contains(o) == true) {
			observers.add(o);
		}
		o.onRegister(bodiesRO, tiempoActual, tiempoPaso, gravityLaws.toString());
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