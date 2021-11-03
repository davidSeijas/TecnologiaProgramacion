package simulator.viewer;

import java.awt.*;
import java.util.List;

import javax.swing.*;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class StatusBar extends JPanel implements SimulatorObserver{
	private JLabel _currTime; // for current time
	private JLabel _currLaws; // for gravity laws
	private JLabel _numOfBodies; // for number of bodies
	
	StatusBar(Controller ctrl) {
		initGUI();
		ctrl.addObserver(this);
	}
	
	
	private void initGUI() {
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBorder(BorderFactory.createBevelBorder(1));
		JToolBar barra = new JToolBar();
		
		JLabel time = new JLabel ("Time: ");
		time.setMinimumSize(new Dimension(40, 20));
		time.setMaximumSize(new Dimension(40, 20));
		time.setPreferredSize(new Dimension(40, 20));
		barra.add(time);
		_currTime = new JLabel("0.0");
		_currTime.setMinimumSize(new Dimension(75, 20));
		_currTime.setMaximumSize(new Dimension(75, 20));
		_currTime.setPreferredSize(new Dimension(75, 20));
		barra.add(_currTime);
		barra.addSeparator();
		
		JLabel bodies = new JLabel ("Bodies: ");
		bodies.setMinimumSize(new Dimension(50, 20));
		bodies.setMaximumSize(new Dimension(50, 20));
		bodies.setPreferredSize(new Dimension(50, 20));
		barra.add(bodies);
		_numOfBodies = new JLabel("0.0");
		_numOfBodies.setMinimumSize(new Dimension(30, 20));
		_numOfBodies.setMaximumSize(new Dimension(30, 20));
		_numOfBodies.setPreferredSize(new Dimension(30, 20));
		barra.add(_numOfBodies);
		barra.addSeparator();
		
		JLabel laws = new JLabel ("Laws: ");
		laws.setMinimumSize(new Dimension(40, 20));
		laws.setMaximumSize(new Dimension(40, 20));
		laws.setPreferredSize(new Dimension(40, 20));
		barra.add(laws);
		_currLaws = new JLabel();
		barra.add(_currLaws);

		this.add(barra);
}

	
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				_numOfBodies.setText(Integer.toString(bodies.size()));
				_currTime.setText(Double.toString(time));
				_currLaws.setText(gLawsDesc);
			}
		});
	}
	
	
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				_numOfBodies.setText(Integer.toString(bodies.size()));
				_currTime.setText(Double.toString(time));
				_currLaws.setText(gLawsDesc);
			}
		});
	}
	
	
	public void onBodyAdded(List<Body> bodies, Body b) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				_numOfBodies.setText(Integer.toString(bodies.size()));
			}
		});
	}
	
	
	public void onAdvance(List<Body> bodies, double time) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				_numOfBodies.setText(Integer.toString(bodies.size()));
				_currTime.setText(Double.toString(time));
			}
		});
	}
	
	
	public void onDeltaTimeChanged(double dt) {
		
	}
	
	
	public void onGravityLawChanged(String gLawsDesc) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				_currLaws.setText(gLawsDesc);
			}
		});
	}
}
