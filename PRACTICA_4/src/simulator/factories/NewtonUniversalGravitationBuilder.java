package simulator.factories;

import org.json.JSONObject;
import simulator.model.GravityLaws;
import simulator.model.NewtonUniversalGravitation;


public class NewtonUniversalGravitationBuilder extends Builder<GravityLaws>{
	private final static String TYPE = "nlug";
	private final static String DESC = "Newton’s law of universal gravitation";
	
	public NewtonUniversalGravitationBuilder() {
		super(TYPE, DESC);
	}
	
	
	public NewtonUniversalGravitation createTheInstance(JSONObject jObject) {
		return new NewtonUniversalGravitation();
	}
}
