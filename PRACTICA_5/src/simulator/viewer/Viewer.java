package simulator.viewer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import simulator.control.Controller;
import simulator.misc.Vector;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class Viewer extends JPanel implements SimulatorObserver{
	private int _centerX;
	private int _centerY;
	private double _scale;
	private List<Body> _bodies;
	private boolean _showHelp;
	
	Viewer(Controller ctrl) {
		initGUI();
		ctrl.addObserver(this);
	}
	
	
	private void initGUI() {
		this.setLayout(new BorderLayout());
		_bodies = new ArrayList<>();
		_scale = 1.0;
		_showHelp = true;
		addKeyListener(new KeyListener() {
	
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyChar()) {
				case '-':
					_scale = _scale * 1.1;
					break;
					
				case '+':
					_scale = Math.max(1000.0, _scale / 1.1);
					break;
					
				case '=':
					autoScale();
					break;
					
				case 'h':
					_showHelp = !_showHelp;
					break;
					
				default:
				}
				repaint();
			}

			
			public void keyReleased(KeyEvent arg0) {
				
			}

			
			public void keyTyped(KeyEvent arg0) {
				
			}
		});
		
		addMouseListener(new MouseListener() {
	
			public void mouseEntered(MouseEvent e) {
				requestFocus();
			}

			
			public void mouseClicked(MouseEvent arg0) {
				
			}

			
			public void mouseExited(MouseEvent arg0) {
				
			}

			
			public void mousePressed(MouseEvent arg0) {
				
			}

			
			public void mouseReleased(MouseEvent arg0) {
				
			}
		});
	
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Viewer", 
				TitledBorder.LEFT, TitledBorder.TOP));
		this.setSize(1000, 465);
		this.setVisible(true);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gr = (Graphics2D) g;
		gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		gr.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		_centerX = getWidth() / 2;
		_centerY = getHeight() / 2;
		
		Image fondo = new ImageIcon("resources/icons/universe.jpeg").getImage();
		gr.drawImage(fondo, 0, 0, getWidth(), getHeight(), null);
		
		// TODO draw a cross at center
		gr.setColor(Color.RED);
		gr.drawLine(_centerX, _centerY + 10, _centerX, _centerY - 10);
		gr.drawLine(_centerX + 10, _centerY, _centerX - 10, _centerY);
		
		// TODO draw bodies
		gr.setColor(Color.WHITE);
		for(Body b : _bodies) {
			Double x = (Double) b.getPosicion().coordinate(0);
			Double y = (Double) b.getPosicion().coordinate(1);
			gr.fillOval(_centerX + (int) (x/_scale) - 5, _centerY - (int) (y/_scale) - 5, 10, 10);
			gr.drawString(b.getId(), _centerX + (int) (x/_scale) - 7, _centerY - (int) (y/_scale) - 7);
		}
		
		// TODO draw help if _showHelp is true
		if(_showHelp) {
			gr.setColor(Color.RED);
			gr.drawString("h: toggle help, +: zoom in, -:zoom out, =: fit", 5, 25);
			gr.drawString("Scaling ratio: " + Double.toString(_scale), 5, 40);
		}
	}
	
	
	private void autoScale() {
		double max = 1.0;
		for (Body b : _bodies) {
			Vector p = b.getPosicion();
			for (int i = 0; i < p.dim(); i++) {
				max = Math.max(max, Math.abs(b.getPosicion().coordinate(i)));
			}
		}
		
		double size = Math.max(1.0, Math.min((double) getWidth(),(double) getHeight()));
		_scale = max > size ? 4.0 * max / size : 1.0;
	}

	
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		_bodies = bodies;
		autoScale();
		repaint();
	}

	
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		_bodies = bodies;
		autoScale();
		repaint();
	}

	
	public void onBodyAdded(List<Body> bodies, Body b) {
		_bodies = bodies;
		autoScale();
		repaint();
	}

	
	public void onAdvance(List<Body> bodies, double time) {
		repaint();
	}

	
	public void onDeltaTimeChanged(double dt) {
		
	}

	
	public void onGravityLawChanged(String gLawsDesc) {
		
	}
}
