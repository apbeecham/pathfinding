import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.titanstudios.pathfinding.Grid;

public class PathfindingFrame extends JFrame implements ActionListener {
	
	Grid grid;
	JPanel container;
	JPanel controlPanel;
	JButton playButton;
	JButton resetButton;
	
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
		
		controlPanel.add(playButton);
		controlPanel.add(resetButton);
		controlPanel.setPreferredSize(new Dimension(400,50));
		
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
					grid.start();
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
