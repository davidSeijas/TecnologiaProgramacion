package simulator.viewer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.json.JSONObject;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class ControlPanel extends JPanel implements SimulatorObserver{
	private Controller _ctrl;
	private boolean _stopped;
	private JSpinner spinner;
	private JTextField text;
	
	private JButton open;
	private JButton physics;
	private JButton run;
	private JButton stop;
	private JButton exit;
	
	ControlPanel(Controller ctrl) {
		_ctrl = ctrl;
		_stopped = true;
		initGUI();
		_ctrl.addObserver(this);
	}
	
	
	private void initGUI() {
		this.setLayout(new FlowLayout());
		JToolBar barra = new JToolBar();
		barra.setPreferredSize(new Dimension(1000, 40));
		this.add(barra);
		
		open = new JButton();
		open.setActionCommand("open");
		open.setToolTipText("Load a file");
		open.setIcon(new ImageIcon("resources/icons/open.png"));
		open.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event){
				action(event);
			}
		});
		
		physics = new JButton();
		physics.setActionCommand("physics");
		physics.setToolTipText("Select Gravity Law");
		physics.setIcon(new ImageIcon("resources/icons/physics.png"));
		physics.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				action(event);
			}
		});
		
		run = new JButton();
		run.setActionCommand("run");
		run.setToolTipText("Run PhysicsSimulator");
		run.setIcon(new ImageIcon("resources/icons/run.png"));
		run.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				action(event);
			}
		});
		
		stop = new JButton();
		stop.setActionCommand("stop");
		stop.setToolTipText("Stop PhysicsSimulator");
		stop.setIcon(new ImageIcon("resources/icons/stop.png"));
		stop.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				action(event);
			}
		});
		
		exit = new JButton();
		exit.setActionCommand("exit");
		exit.setToolTipText("Exit");
		exit.setIcon(new ImageIcon("resources/icons/exit.png"));
		exit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				action(event);
			}
		});
		
		spinner = new JSpinner(new SpinnerNumberModel(10000, 0, 50000, 100));
		spinner.setPreferredSize(new Dimension(95, 30));
		spinner.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent arg0) {
				System.out.println("Se ha variado el número de pasos");
			}
		});
		
		text = new JTextField("2500");
		text.setPreferredSize(new Dimension(80, 30));
		text.setEditable(true);
		
		JLabel steps = new JLabel("Steps");
		steps.setOpaque(true);
		JLabel dt = new JLabel("Delta-Time");
		dt.setOpaque(true);
		JLabel label = new JLabel("");
		label.setPreferredSize(new Dimension(600, 40));
		label.setOpaque(false);
		
		barra.add(open);
		barra.addSeparator();
		barra.add(physics);
		barra.addSeparator();
		barra.add(run);
		barra.add(stop);
		barra.addSeparator();
		barra.add(steps);
		barra.addSeparator();
		barra.add(spinner);
		barra.addSeparator();
		barra.add(dt);
		barra.addSeparator();
		barra.add(text);
		barra.addSeparator();
		barra.addSeparator();
		barra.add(label);
		barra.add(exit);
		barra.addSeparator();
		
		this.setVisible(true);
	}
	
	
	private void run_sim(int n) {
		if (n > 0 && !_stopped ) {
			try {
				_ctrl.run(1);
			} 
			catch (Exception e) {
				spinner.setEnabled(true);
				text.setEnabled(true);
				open.setEnabled(true);
				physics.setEnabled(true);
				run.setEnabled(true);
				stop.setEnabled(true);
				exit.setEnabled(true);			
				_stopped = true;
				return;
			}
			
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					run_sim(n - 1);
				}
			});
		} 
		else {
			spinner.setEnabled(true);
			text.setEnabled(true);
			open.setEnabled(true);
			physics.setEnabled(true);
			run.setEnabled(true);
			stop.setEnabled(true);
			exit.setEnabled(true);
			_stopped = true;
		}
	}
	
	
	public void action(ActionEvent event) {
		switch(event.getActionCommand()) {
		case "open":
			JFileChooser fc = new JFileChooser("./resources/examples");
			int opc = fc.showOpenDialog(ControlPanel.this);
			InputStream is = null;

			if (opc == JFileChooser.APPROVE_OPTION) {
				try {
					is = new FileInputStream(fc.getSelectedFile());
				}
				catch(FileNotFoundException e) {
					JOptionPane.showMessageDialog(ControlPanel.this, "ERROR! El archivo no existe", "Error", 
							JOptionPane.ERROR_MESSAGE);
					System.out.println(e.getMessage());
				}
				
				_ctrl.reset();
				_ctrl.loadBodies(is);
			} 
			else {
				JOptionPane.showMessageDialog(ControlPanel.this, "No se ha seleccionado archivo", "Message", 
						JOptionPane.INFORMATION_MESSAGE);
			}
			
			break;
			
		case "physics":
			List<JSONObject> leyes = _ctrl.getGravityLawsFactory().getInfo();
			String[] opciones = new String[leyes.size()];
			
			for(int i = 0; i < leyes.size(); ++i) {
				opciones[i] = (String) leyes.get(i).get("desc");
			}
			
			String opcion = (String) JOptionPane.showInputDialog(ControlPanel.this, "Select gravity laws to be used", 
					"Factory Laws Selector", JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
			
			boolean parar = false;
			if(opcion == null) {
				parar = true;
			}
			for(int i = 0; i < leyes.size() && !parar; ++i) {
				if (opcion.equals(opciones[i].toString())){
					_ctrl.setGravityLaws(_ctrl.getGravityLawsFactory().getInfo().get(i));
					parar = true;
				}
			}
			
			break;
			
		case "run":
			try {
				Double deltaTime = Double.parseDouble(text.getText());
				_ctrl.setDeltaTime(deltaTime);

				spinner.setEnabled(false);
				text.setEnabled(false);
				open.setEnabled(false);
				physics.setEnabled(false);
				run.setEnabled(false);
				stop.setEnabled(true);
				exit.setEnabled(false);
				_stopped = false;
				
				run_sim((Integer)spinner.getValue());
			}
			catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(ControlPanel.this, "ERROR! El delta time debe ser un numero decimal", 
						"Error", JOptionPane.ERROR_MESSAGE);
				System.out.println(e.getMessage());
			}
			
			break;
			
		case "stop":
			spinner.setEnabled(true);
			text.setEnabled(true);
			open.setEnabled(true);
			physics.setEnabled(true);
			run.setEnabled(true);
			stop.setEnabled(true);
			exit.setEnabled(true);
			_stopped = true;
			
			break;
			
		case "exit":
			int option = JOptionPane.showOptionDialog(ControlPanel.this, "Are you sure to quit?", "EXIT", 
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			
			if(option == 0) {
				System.exit(0);
			}
			
			break;
		}
	}
	
	
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		text.setText(Double.toString(dt));
	}

	
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		text.setText(Double.toString(dt));
	}

	
	public void onBodyAdded(List<Body> bodies, Body b) {
		_stopped = false;
	}

	
	public void onAdvance(List<Body> bodies, double time) {
		
	}

	
	public void onDeltaTimeChanged(double dt) {
		text.setText(Double.toString(dt));
	}

	
	public void onGravityLawChanged(String gLawsDesc) {	
		
	}
}
