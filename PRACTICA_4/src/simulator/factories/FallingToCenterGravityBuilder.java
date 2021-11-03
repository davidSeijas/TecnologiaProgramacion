package simulator.factories;

import org.json.JSONObject;

import simulator.model.FallingToCenterGravity;
import simulator.model.GravityLaws;


public class FallingToCenterGravityBuilder extends Builder<GravityLaws>{
	private final static String TYPE = "ftcg";
	private final static String DESC = "Bodies fall to \"Universe centre\"";
	
	public FallingToCenterGravityBuilder() {
		super(TYPE, DESC);
	}
	
	
	public FallingToCenterGravity createTheInstance(JSONObject jObject) {
		return new FallingToCenterGravity();
	}
}
