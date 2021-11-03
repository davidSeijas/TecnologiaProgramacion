package simulator.viewer;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import simulator.control.Controller;

public class BodiesTable extends JPanel {
	private BodiesTableModel modelo;
	private JTable tabla;
	
	BodiesTable(Controller ctrl) {
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.black, 2),"Bodies", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
		
		modelo = new BodiesTableModel(ctrl);
		tabla = new JTable (modelo);
		tabla.setCellSelectionEnabled(false);
		tabla.setMinimumSize(new Dimension(1000, 175));
		tabla.setMaximumSize(new Dimension(1000, 175));
		tabla.setPreferredSize(new Dimension(1000, 175));
		this.add(new JScrollPane(tabla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		
		this.setBackground(Color.WHITE);
		this.setMinimumSize(new Dimension(1000, 230));
		this.setMaximumSize(new Dimension(1000, 230));
		this.setPreferredSize(new Dimension(1000, 230));
		this.setVisible(true);
	}
}
