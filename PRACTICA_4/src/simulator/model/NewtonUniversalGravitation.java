package simulator.model;

import java.util.List;
import simulator.misc.Vector;


public class NewtonUniversalGravitation implements GravityLaws{
	private static final double G = 6.67E-11;
	
	public void apply(List<Body> bodies){ // throws ImpossiblePhysicsException
		for(int i = 0; i < bodies.size(); ++i) {
			if(bodies.get(i).getMasa() == 0) {
				bodies.get(i).setAceleracion(new Vector(2));
				bodies.get(i).setVelocidad(new Vector(2));
			}
			else {
				Vector aceleracion = new Vector(2);
				Vector fuerza = new Vector(2);
				
				for(int j = 0; j < bodies.size(); ++j) {
					if(j != i) {
						double numerador = G * (bodies.get(i).getMasa() * bodies.get(j).getMasa());
						double denominador = bodies.get(j).getPosicion().distanceTo(bodies.get(i).getPosicion());
						
//						if(denominador == 0) {
//							throw new ImpossiblePhysicsException("Cuerpos con la misma posicion");
//						}
						
						double f = numerador / (denominador*denominador);
						Vector direccion = bodies.get(j).getPosicion().minus(bodies.get(i).getPosicion()).direction();
						
						fuerza = fuerza.plus(direccion.scale(f));
					}	
				}
				
				aceleracion = fuerza.scale(1 / bodies.get(i).getMasa());
				bodies.get(i).setAceleracion(aceleracion);
			}
		}
	}
}
