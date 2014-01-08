package com.titanstudios.pathfinding;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import com.titanstudios.pathfinding.*;

public class Grid extends JPanel implements MouseMotionListener{
	private int width;
	private int height;
	
	private int rowHeight;
	private int columnWidth;
	
	private int rows;
	private int columns;
	
	private Point selectedCell;
	private Cell cells[][];
	
	private DijkstraPathfinder pathfinder;
		
	public Grid(int width, int height, int rows, int columns){
		
		this.width = width;
		this.height = height;
		
		this.rows = rows;
		this.columns = columns;
		
		rowHeight = height / rows;
		columnWidth = width / columns;
		
		selectedCell = new Point(0,0);
		
		buildGraph();				
		addMouseMotionListener(this);
		
		this.setPreferredSize(new Dimension(width,height));
	}
	
	public void start(){		
		pathfinder.findShortestPath(cells[2][2], cells[rows - 1][columns- 1], this);	
	}
	
	public void update(){
		this.repaint();
	}
	
	public void reset(){
		pathfinder.stop();
		pathfinder = new DijkstraPathfinder();
		buildGraph();
		this.repaint();
	}
	

	private void buildGraph(){
		pathfinder = new DijkstraPathfinder();
		cells = new Cell[rows][columns];
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				cells[i][j] = new Cell(new Point(i * rowHeight,j * columnWidth),columnWidth,rowHeight);
			}
		}
		
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				if(i + 1 < rows)
					cells[i][j].addEdge(new Edge(5, cells[i + 1][j]));
				if(j + 1 < columns)
					cells[i][j].addEdge(new Edge(5, cells[i][j + 1]));
				if(i - 1 >= 0)
					cells[i][j].addEdge(new Edge(5, cells[i - 1][j]));
				if(j - 1 >= 0)
					cells[i][j].addEdge(new Edge(5, cells[i][j - 1]));
			}
		}
	}	
		
	public void paintComponent(Graphics g){
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width, height);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.BLACK);
		
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				cells[i][j].draw(g);
			}
		}
		
	}
	
	 public void mouseMoved(MouseEvent e) {}

	 public void mouseDragged(MouseEvent e) {}	
}
