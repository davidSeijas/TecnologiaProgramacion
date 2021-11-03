package simulator.factories;

import org.json.JSONObject;

import simulator.model.GravityLaws;
import simulator.model.NoGravity;


public class NoGravityBuilder extends Builder<GravityLaws>{
	private final static String TYPE = "ng";
	private final static String DESC = "No gravity (" + TYPE + ")";
	
	public NoGravityBuilder() {
		super(TYPE, DESC);	
	}
	
	
	public NoGravity createTheInstance(JSONObject jObject) {
		return new NoGravity();
	}
}
