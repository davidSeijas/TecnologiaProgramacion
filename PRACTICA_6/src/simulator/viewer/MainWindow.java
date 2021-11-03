package simulator.viewer;

import java.awt.*;

import javax.swing.*;

import simulator.control.Controller;

public class MainWindow extends JFrame {
	Controller _ctrl;
	
	public MainWindow(Controller ctrl) {
		super("Physics Simulator");
		_ctrl = ctrl;
		initGUI();
	}
	
	
	private void initGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setMinimumSize(new Dimension(1000, 800));
		mainPanel.setMaximumSize(new Dimension(1000, 800));
		mainPanel.setPreferredSize(new Dimension(1000, 800));
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(new BodiesTable(_ctrl));
		panel.add(new Viewer(_ctrl));
		
		mainPanel.add(new ControlPanel(_ctrl), BorderLayout.PAGE_START);
		mainPanel.add(panel, BorderLayout.CENTER);
		mainPanel.add(new StatusBar(_ctrl), BorderLayout.PAGE_END);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(mainPanel);
		this.pack();
		this.setVisible(true);
	}
}
