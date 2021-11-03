package simulator.control;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.factories.Factory;
import simulator.model.Body;
import simulator.model.PhysicsSimulator;


public class Controller {
	private PhysicsSimulator simulador;
	private Factory<Body> cuerpos;
	
	public Controller(PhysicsSimulator simulador, Factory<Body> cuerpos) {
		this.simulador = simulador;
		this.cuerpos = cuerpos;
	}
	
	
	public void loadBodies(InputStream in) {
		JSONObject jsonInput = new JSONObject(new JSONTokener(in));
		JSONArray jsonArray = jsonInput.getJSONArray("bodies");
		
		for(int i = 0; i < jsonArray.length(); ++i) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			Body body = cuerpos.createInstance(jsonObject);
			simulador.addBody(body);
		}
	}
	
	
	public void run(int n, OutputStream out) {
		PrintStream print = (out == null) ? null: new PrintStream(out);
		
		print.println("{\n\"states\": [");
		
		for(int i = 0; i < n; ++i) {
			print.println(simulador.toString() + ",");
			simulador.advance();
		}
		print.println(simulador.toString());
				
		print.println("]\n}");
		print.close();
	}
}
