import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class PathfindingMain {
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				PathfindingFrame frame = new PathfindingFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setTitle("Pathfinding Visualiser Version 1.0");
				frame.setVisible(true);
			}			
		});		
	}
	
}
