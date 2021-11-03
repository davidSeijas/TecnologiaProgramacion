package simulator.model;

import java.util.List;

import simulator.misc.Vector;

public class FallingToCenterGravity implements GravityLaws{
	private static final double g = 9.81;
	
	public void apply(List<Body> bodies) {
		for(Body body : bodies) {
			Vector direccion = body.getPosicion().direction();
			body.setAceleracion(direccion.scale(-g));
		}
	}
}
