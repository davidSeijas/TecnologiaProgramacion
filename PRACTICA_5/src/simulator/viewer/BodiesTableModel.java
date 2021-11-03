package simulator.viewer;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class BodiesTableModel extends AbstractTableModel implements SimulatorObserver {
	private List<Body> _bodies;
	private String[] colNames = { "Id", "Mass", "Position", "Velocity", "Aceleration" };
	
	BodiesTableModel(Controller ctrl) {
		_bodies = new ArrayList<>();
		ctrl.addObserver(this);
	}
	
	
	public int getRowCount() {
		return _bodies.size();
	}
	
	
	public int getColumnCount() {
		return colNames.length;
	}

	
	public String getColumnName(int c) {
		return colNames[c];
	}
	
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object object = null;
		
		switch (columnIndex) {
		case 0:
			object = _bodies.get(rowIndex).getId();
			break;
		case 1:
			object = _bodies.get(rowIndex).getMasa();
			break;
		case 2:
			object = _bodies.get(rowIndex).getPosicion();
			break;
		case 3:
			object = _bodies.get(rowIndex).getVelocidad();
			break;
		case 4:
			object = _bodies.get(rowIndex).getAceleracion();
			break;
		}
		
		return object;
	}
	
	
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		_bodies = bodies;
		fireTableStructureChanged();
	}

	
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		_bodies = bodies;
		fireTableStructureChanged();
	}

	
	public void onBodyAdded(List<Body> bodies, Body b) {
		_bodies = bodies;
		fireTableStructureChanged();
	}

	
	public void onAdvance(List<Body> bodies, double time) {
		_bodies = bodies;
		fireTableStructureChanged();
	}

	
	public void onDeltaTimeChanged(double dt) {
		
	}

	
	public void onGravityLawChanged(String gLawsDesc) {
		
	}

}
