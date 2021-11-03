package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector;
import simulator.model.Body;


public class BasicBodyBuilder extends Builder<Body>{
	private final static String TYPE = "basic";
	private final static String DESC = "Basic body";
	
	public BasicBodyBuilder() {
		super(TYPE, DESC);
	}
	
	
	public Body createTheInstance(JSONObject jObject) throws IllegalArgumentException{
		try {
			String id = jObject.getString("id");
			
			JSONArray jPos = jObject.getJSONArray("pos"); 
			double[] p = {jPos.getDouble(0), jPos.getDouble(1)};
			Vector pos = new Vector(p);
			
			JSONArray jVel = jObject.getJSONArray("vel"); 
			double[] v = {jVel.getDouble(0), jVel.getDouble(1)};
			Vector vel = new Vector(v);
			
			double masa = jObject.getDouble("mass");
			
			Vector acc = new Vector(2);
			
			if(masa < 0.0) {
				throw new Exception("Masa negativa");
			}
			
			return new Body(id, vel, acc, pos, masa);
		}
		catch (Exception e){
			throw new IllegalArgumentException("Error en argumentos." + e.getMessage()); 
		}
	}
	
	
	protected JSONObject createData() {
		JSONObject jData = new JSONObject();
		JSONObject jObject = new JSONObject();
		double[] array = {0.0, 0.0};
		
		jData.put("id", "");
		jData.put("pos", array);
		jData.put("vel", array);
		jData.put("mass", 0.0);
		
		jObject.put("type", type);
		jObject.put("data", jData);
		
		return jObject;
	}
}
