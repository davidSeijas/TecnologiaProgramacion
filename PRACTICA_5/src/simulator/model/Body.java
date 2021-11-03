package simulator.model;

import simulator.misc.Vector;

public class Body {
	private String id;
	private Vector velocidad;
	private Vector aceleracion;
	private Vector posicion;
	protected double masa;
	
	
	public Body(String id, Vector velocidad, Vector aceleracion, Vector posicion, double masa) {
		this.id = id;
		this.velocidad = velocidad;
		this.aceleracion = aceleracion;
		this.posicion = posicion;
		this.masa = masa;
	}
	
	
	void move(double t) {
		this.posicion = posicion.plus(velocidad.scale(t)).plus(aceleracion.scale(0.5*t*t));
		this.velocidad = velocidad.plus(aceleracion.scale(t));
	}
	
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("{ \"id\": ").append("\"" + this.id + "\"");
		sb.append(", \"mass\": ").append(this.masa);
		sb.append(", \"pos\": ").append(this.posicion);
		sb.append(", \"vel\": ").append(this.velocidad);
		sb.append(", \"acc\": ").append(this.aceleracion);
		sb.append(" }");
		
		return sb.toString();
	}
	
	
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null) {
			return false;
		}
		if(this.getClass() != obj.getClass()) {
			return false;
		}
		
		Body that = (Body) obj;
		
		return this.id.equals(that.id);
	}


	public String getId() {
		return id;
	}


	public Vector getVelocidad() {
		return velocidad;
	}


	public Vector getAceleracion() {
		return aceleracion;
	}


	public Vector getPosicion() {
		return posicion;
	}


	public double getMasa() {
		return masa;
	}


	void setVelocidad(Vector velocidad) {
		this.velocidad = velocidad;
	}


	void setAceleracion(Vector aceleracion) {
		this.aceleracion = aceleracion;
	}


	void setPosicion(Vector posicion) {
		this.posicion = posicion;
	}
}
