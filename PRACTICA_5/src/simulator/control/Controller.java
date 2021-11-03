package simulator.control;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.factories.Factory;
import simulator.model.Body;
import simulator.model.GravityLaws;
import simulator.model.PhysicsSimulator;
import simulator.model.SimulatorObserver;


public class Controller {
	private PhysicsSimulator simulador;
	private Factory<Body> cuerpos;
	private Factory<GravityLaws> leyes;
	
	public Controller(PhysicsSimulator simulador, Factory<Body> cuerpos, Factory<GravityLaws> leyes) {
		this.simulador = simulador;
		this.cuerpos = cuerpos;
		this.leyes = leyes;
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
	
	
	public void run(int n) {
		for(int i = 0; i < n; ++i) {
			simulador.advance();
		}
	}
	
	
	public void reset() {
		simulador.reset();
	}
	
	
	public void setDeltaTime(double dt) {
		simulador.setDeltaTime(dt);
	}
	
	
	public void addObserver(SimulatorObserver o) {
		simulador.addObserver(o);
	}
	
	
	public Factory<GravityLaws> getGravityLawsFactory(){
		return leyes;
	}
	
	
	public void setGravityLaws(JSONObject info) {
		simulador.setGravityLaws(leyes.createInstance(info));
	}
}
