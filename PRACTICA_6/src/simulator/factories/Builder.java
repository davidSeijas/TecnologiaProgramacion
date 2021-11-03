package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;


public abstract class Builder<T> {
	protected String type;
	protected String desc;
	
	public Builder(String type, String desc) {
		this.type = type;
		this.desc = desc;
	}

	
	abstract protected T createTheInstance(JSONObject jObject);
	
	
	protected double[] jsonArrayTodoubleArray(JSONArray jArray) {
		double[] array = new double[jArray.length()]; 
		
		for(int i = 0; i < jArray.length(); ++i) {
			array[i] = jArray.getDouble(i);
		}
		
		return array;
	}
	
	
	public T createInstance(JSONObject info) throws IllegalArgumentException{
		if(info.get("type").equals(type)) {
			return createTheInstance(info.getJSONObject("data"));
		}
		
		return null;
	}

	
	public JSONObject getBuilderInfo(){
		JSONObject jObject = createData();
		jObject.put("desc", desc);
		
		return jObject;
	}
	
	
	protected JSONObject createData() {
		JSONObject jObject = new JSONObject();
		jObject.put("type", type);
		jObject.put("data", new JSONObject());
		
		return jObject;
	}
	
	
	
}
