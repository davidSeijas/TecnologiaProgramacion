package simulator.model;

import simulator.misc.Vector;

public class MassLossingBody extends Body{
	private double lossFrecuency;
	private double lossFactor;
	private static double c = 0.0;
	
	public MassLossingBody(String id, Vector velocidad, Vector aceleracion, Vector posicion, double masa, double lossFrecuency, double lossFactor) {
		super(id, velocidad, aceleracion, posicion, masa);
		this.lossFrecuency = lossFrecuency;
		this.lossFactor = lossFactor;
	}
	
	
	void move(double t) {
		super.move(t);
		c += t;
		
		if(c >= lossFrecuency) {
			masa *= (1 - lossFactor);
			c = 0.0; 
		}
	}

}
