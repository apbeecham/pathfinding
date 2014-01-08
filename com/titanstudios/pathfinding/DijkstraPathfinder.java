package com.titanstudios.pathfinding;

import java.awt.Color;
import java.util.*;

import javax.swing.Timer;

public class DijkstraPathfinder {
	
	private PriorityQueue<Cell>solvedVertices;	
	private boolean isRunning;
	
	public DijkstraPathfinder(){
		solvedVertices = new PriorityQueue<Cell>();
	}
		
	public ArrayList<Cell> findShortestPath(Cell start, Cell goal, Grid grid){
		
		isRunning = true;
		start.setDistanceFromStart(0);		
		solvedVertices.add(start);
		
		long now = System.currentTimeMillis();
		long step = 50;		
		
		while(!solvedVertices.isEmpty() && isRunning){
			if(!isRunning)
				break;
			
			long timeSinceLastStep = System.currentTimeMillis() - now;
			
			if(timeSinceLastStep >= step){
				now = System.currentTimeMillis();
			
				grid.update();
				Cell current = solvedVertices.poll();
				current.setColor(Color.GREEN);
				
				if(current == start)
					start.setColor(Color.MAGENTA);
				
				if(current == goal){
					
					goal.setColor(Color.RED);
					grid.update();
					break;
				}
				System.out.println("loop");
				for(Edge e : current.getEdges()){
					
					Cell next = (Cell) e.getDestination();
					double distance = current.getDistanceFromStart() + e.getCost();	
					if(next.getColor() != Color.GREEN && next.getColor() != Color.MAGENTA)
						next.setColor(Color.BLUE);
				
					if(distance < next.getDistanceFromStart()){						
						solvedVertices.remove(next);
						next.setDistanceFromStart(distance);
						next.setPredecessor(current);
						solvedVertices.add(next);
					}
				}
			}
		}
				
		ArrayList<Cell> shortestPath = new ArrayList<Cell>();
		Cell current = goal;
		shortestPath.add(current);	
		
		while(current.getPredecessor()!= null){
			
			shortestPath.add((Cell)current.getPredecessor());
			current = (Cell)current.getPredecessor();
			
			if(current != start)
				current.setColor(Color.CYAN);
		}
		
		grid.update();
		Collections.reverse(shortestPath);	
		
		return shortestPath;
	}	
	
	public void stop(){
		isRunning = false;
	}
}
