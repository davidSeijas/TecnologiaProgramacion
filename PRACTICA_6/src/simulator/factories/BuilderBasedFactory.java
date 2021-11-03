package simulator.factories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONObject;


public class BuilderBasedFactory<T> implements Factory<T>{
	private List<Builder<T>> builders;
	ArrayList<JSONObject> info;
	
	public BuilderBasedFactory(List<Builder<T>> builders) {
		this.builders = builders;
		this.info = new ArrayList<JSONObject>();
		
		for(int i = 0; i < builders.size(); ++i) {
			this.info.add(builders.get(i).getBuilderInfo());
		}
	}

	
	public T createInstance(JSONObject info) throws IllegalArgumentException{
		T constructor = null;
		
		for(int i = 0; i < builders.size() && constructor == null; ++i) {
			constructor = builders.get(i).createInstance(info);
		}
		
		if(constructor == null) {
			throw new IllegalArgumentException("No se reconoce el JSONNObject info");
		}
		
		return constructor;
	}

	
	public List<JSONObject> getInfo() {
		return Collections.unmodifiableList(info);
	}
}
