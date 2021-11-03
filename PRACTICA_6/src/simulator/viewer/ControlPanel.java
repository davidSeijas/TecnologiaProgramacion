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
import simulator.launcher.Main;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class ControlPanel extends JPanel implements SimulatorObserver{
	private Controller _ctrl;
	private JSpinner spinnerDelay;
	private JSpinner spinnerSteps;
	private JTextField text;
	private volatile Thread _thread;
	
	private JButton open;
	private JButton physics;
	private JButton run;
	private JButton stop;
	private JButton exit;
	
	ControlPanel(Controller ctrl) {
		_ctrl = ctrl;
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
		
		spinnerDelay = new JSpinner(new SpinnerNumberModel(1, 0, 1000, 1));
		spinnerDelay.setPreferredSize(new Dimension(50, 30));
		spinnerDelay.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent arg0) {
				System.out.println("Se ha variado el delay");
			}
		});
		
		spinnerSteps = new JSpinner(new SpinnerNumberModel(10000, 0, 50000, 100)); //Main.get_steps() en vez de 10000
		spinnerSteps.setPreferredSize(new Dimension(95, 30));
		spinnerSteps.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent arg0) {
				System.out.println("Se ha variado el número de pasos");
			}
		});
		
		text = new JTextField("2500");
		text.setPreferredSize(new Dimension(80, 30));
		text.setEditable(true);
		
		JLabel delay = new JLabel("Delay");
		delay.setOpaque(true);
		JLabel steps = new JLabel("Steps");
		steps.setOpaque(true);
		JLabel dt = new JLabel("Delta-Time");
		dt.setOpaque(true);
		JLabel label = new JLabel("");
		label.setPreferredSize(new Dimension(400, 40));
		label.setOpaque(false);
		
		barra.add(open);
		barra.addSeparator();
		barra.add(physics);
		barra.addSeparator();
		barra.add(run);
		barra.add(stop);
		barra.addSeparator();
		barra.add(delay);
		barra.addSeparator();
		barra.add(spinnerDelay);
		barra.addSeparator();
		barra.add(steps);
		barra.addSeparator();
		barra.add(spinnerSteps);
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
	
	
	private void run_sim(long delay, int n) {
		while(n > 0 && (!Thread.currentThread().isInterrupted())) {
			try {
				_ctrl.run(1);
			} 
			catch (Exception e) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						JOptionPane.showMessageDialog(ControlPanel.this, "ERROR! al ejecutar el simulador", 
								"ERROR", JOptionPane.ERROR_MESSAGE);
					}
				});
				
				setEnabledButtons(true);
				return;
			}
			
			try {
				Thread.sleep(delay);
			} 
			catch (InterruptedException e) {
				setEnabledButtons(true);
				return; //Thread.currentThread().interrupt();
			}
			
			n--;
		}
	}
	
	
	public void setEnabledButtons(boolean b) {
		spinnerDelay.setEnabled(b);
		spinnerSteps.setEnabled(b);
		text.setEnabled(b);
		open.setEnabled(b);
		physics.setEnabled(b);
		run.setEnabled(b);
		stop.setEnabled(b);
		exit.setEnabled(b);	
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

				setEnabledButtons(false);
				stop.setEnabled(true);
				
				_thread = new Thread(new Runnable() {

					public void run() {
						run_sim((Integer)spinnerDelay.getValue(), (Integer)spinnerSteps.getValue());
						setEnabledButtons(true);
						_thread = null;
					}
				});
				
				_thread.start();
			}
			catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(ControlPanel.this, "ERROR! El delta time debe ser un numero decimal", 
						"Error", JOptionPane.ERROR_MESSAGE);
				text.setText(Double.toString(2500));
				System.out.println(e.getMessage());
			}
			
			break;
			
		case "stop":
			if(_thread != null) {
				_thread.interrupt();
			}
			
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
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				text.setText(Double.toString(dt));
			}
		});
	}

	
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				text.setText(Double.toString(dt));
			}
		});
	}

	
	public void onBodyAdded(List<Body> bodies, Body b) {
		
	}

	
	public void onAdvance(List<Body> bodies, double time) {
		
	}

	
	public void onDeltaTimeChanged(double dt) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				text.setText(Double.toString(dt));
			}
		});
	}

	
	public void onGravityLawChanged(String gLawsDesc) {	
		
	}
}
