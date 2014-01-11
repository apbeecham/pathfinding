import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.apbeecham.pathfinding.Grid;

public class PathfindingFrame extends JFrame implements ActionListener {
	
	//ui components
	private Grid grid;
	private JPanel container;
	private JPanel controlPanel;
	private JPanel buttonPanel;
	private JPanel optionPanel;
	private JButton playButton;
	private JButton resetButton;
	private JSpinner stepSpinner;
	private JComboBox algorithmList;
	private JComboBox gridEditorList;
	private JLabel stepSpinnerLabel;
	private JLabel algorithmListLabel;
	private JLabel gridEditorListLabel;
	
	public PathfindingFrame(){
		
		//build the ui
		grid = new Grid(400,400,50,50);
		
		container = new JPanel(new BorderLayout());
		controlPanel = new JPanel(new BorderLayout());
		
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
	    stepSpinnerLabel = new JLabel("Time per Step (ms): ");
	    stepSpinnerLabel.setLabelFor(stepSpinner);
	    stepSpinnerLabel.setHorizontalAlignment(JLabel.RIGHT);	
	    
	    String algorithms[] = {"Dijkstra" , "A*"};
	    algorithmList = new JComboBox(algorithms);
	    algorithmListLabel = new JLabel("Search Algorithm: ");
	    algorithmListLabel.setLabelFor(algorithmList);
	    algorithmListLabel.setHorizontalAlignment(JLabel.RIGHT);	   
	    
	    String editList[] = {"Start", "Goal"};
	    gridEditorList = new JComboBox(editList);
	    gridEditorList.addActionListener(this);
	    gridEditorListLabel = new JLabel("Place on Grid: ");
	    gridEditorListLabel.setLabelFor(gridEditorList);
	    gridEditorListLabel.setHorizontalAlignment(JLabel.RIGHT);	
	    
	    buttonPanel = new JPanel(new GridLayout(2, 1, 0, 10));
	    buttonPanel.add(playButton);
	    buttonPanel.add(resetButton);
	    controlPanel.add(buttonPanel, BorderLayout.WEST);
		
	    optionPanel = new JPanel(new GridLayout(3, 2, 0, 5));
		optionPanel.add(stepSpinnerLabel);
		optionPanel.add(stepSpinner);
		optionPanel.add(algorithmListLabel);
		optionPanel.add(algorithmList);
		optionPanel.add(gridEditorListLabel);
		optionPanel.add(gridEditorList);
		controlPanel.add(optionPanel,BorderLayout.CENTER);
		
		controlPanel.setPreferredSize(new Dimension(400,75));
		
		container.add(grid,BorderLayout.CENTER);
		container.add(controlPanel,BorderLayout.SOUTH);
		
		this.add(container);
		this.setResizable(false);
		this.pack();		
	}
	
	//listen to the ui components and respond to user input
	public void actionPerformed(ActionEvent e){
		if("start".equals(e.getActionCommand())){			
			
			//pathfinding is a long task. we need to run this in a worker
			//thread so we dont block the EDT.
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
		
		if(e.getSource() == gridEditorList){
			grid.setPositionable(gridEditorList.getSelectedIndex());
		}
		
	}
}
