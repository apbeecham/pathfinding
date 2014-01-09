import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.titanstudios.pathfinding.Grid;

public class PathfindingFrame extends JFrame implements ActionListener {
	
	private Grid grid;
	private JPanel container;
	private JPanel controlPanel;
	private JButton playButton;
	private JButton resetButton;
	private JSpinner stepSpinner;
	private JComboBox algorithmList;
	private JLabel stepSpinnerLabel;
	private JLabel algorithmListLabel;
	public PathfindingFrame(){
		grid = new Grid(400,400,50,50);
		
		container = new JPanel(new BorderLayout());
		controlPanel = new JPanel();
		
		playButton = new JButton("Start");
		playButton.setMnemonic(KeyEvent.VK_S);
	    playButton.setActionCommand("start");
	    playButton.addActionListener(this);
	    
		resetButton = new JButton("Reset");
		resetButton.setMnemonic(KeyEvent.VK_R);
	    resetButton.setActionCommand("reset");
	    resetButton.addActionListener(this);
	    
	    SpinnerNumberModel stepSizeModel = new SpinnerNumberModel(250, 50, 1000, 50);
	    stepSpinner = new JSpinner(stepSizeModel);
	    stepSpinnerLabel = new JLabel("Time per Step (ms):");
	    stepSpinnerLabel.setLabelFor(stepSpinner);
	    
	    String algorithms[] = {"Dijkstra" , "A*"};
	    algorithmList = new JComboBox(algorithms);
	    algorithmListLabel = new JLabel("Search Algorithm:");
	    algorithmListLabel.setLabelFor(algorithmList);
		
		controlPanel.add(playButton);
		controlPanel.add(resetButton);
		controlPanel.add(stepSpinnerLabel);
		controlPanel.add(stepSpinner);
		controlPanel.add(algorithmListLabel);
		controlPanel.add(algorithmList);
		controlPanel.setPreferredSize(new Dimension(400,75));
		
		container.add(grid,BorderLayout.CENTER);
		container.add(controlPanel,BorderLayout.SOUTH);
		
		this.add(container);
		this.setResizable(false);
		this.pack();		
	}
	
	public void actionPerformed(ActionEvent e){
		if("start".equals(e.getActionCommand())){
			
			
			SwingWorker worker = new SwingWorker<Void,Void>(){
				protected Void doInBackground(){
					grid.start((int)stepSpinner.getValue(), algorithmList.getSelectedIndex());
					return null;
				}
			};
			playButton.setEnabled(false);
			worker.execute();
			
		}
		
		if("reset".equals(e.getActionCommand())){
			grid.reset();
			playButton.setEnabled(true);
		}		
	}
}
