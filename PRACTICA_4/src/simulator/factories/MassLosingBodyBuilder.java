package simulator.factories;


import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector;
import simulator.model.Body;
import simulator.model.MassLossingBody;


public class MassLosingBodyBuilder extends Builder<Body>{
	private final static String TYPE = "mlb";
	private final static String DESC = "Body that is lossing mass";
			
	public MassLosingBodyBuilder() {
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
			double freq = jObject.getDouble("freq");
			double factor = jObject.getDouble("factor");
			
			Vector acc = new Vector(2);
			
			if(masa < 0.0) {
				throw new Exception("Masa negativa");
			}
			if (freq <= 0.0) {
				throw new Exception("Frecuencia negativa (o cero)");
			}
			if (factor < 0.0 || factor > 1.0) {
				throw new Exception("Factor fuera de rango");
			}
			
			return new MassLossingBody(id, vel, acc, pos, masa, freq, factor);
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
		jData.put("freq", 0.0);
		jData.put("factor", 0.0);
		
		jObject.put("type", type);
		jObject.put("data", jData);
		
		return jObject;
	}
}
