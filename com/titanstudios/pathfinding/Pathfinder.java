package com.titanstudios.pathfinding;

import java.awt.Color;
import java.awt.Point;
import java.util.*;

public class Pathfinder {
	
	public static final int DIJKSTRA = 0;
	public static final int ASTAR = 1;
	private ArrayList<Cell>closedList;	
	private PriorityQueue<Cell>openList;	
	private boolean isRunning;
	
	public Pathfinder(){
		openList = new PriorityQueue<Cell>();
		closedList = new ArrayList<Cell>();
	}
		
	public ArrayList<Cell> findShortestPath(Cell start, Cell goal, Grid grid, int step, int algorithm){
		
		isRunning = true;
		start.setDistanceFromStart(0);
		if(algorithm == ASTAR)
			start.setTotalCost(euclideanDistance(start.position, goal.position));
		if(algorithm == DIJKSTRA)
			start.setTotalCost(0);
		openList.add(start);
		
		long currentTime = System.currentTimeMillis();		
		
		while(!openList.isEmpty() && isRunning){
			
			if(!isRunning)
				break;
			
			long timeSinceLastStep = System.currentTimeMillis() - currentTime;
			
			if(timeSinceLastStep >= step){
				currentTime = System.currentTimeMillis();
			
				grid.update();
				
				Cell current = openList.poll();
				current.setColor(Color.GREEN);
				
				if(algorithm == ASTAR)
					closedList.add(current);
				
				if(current == start)
					start.setColor(Color.MAGENTA);
				
				if(current == goal){					
					goal.setColor(Color.RED);
					grid.update();
					break;
				}
				for(Edge e : current.getEdges()){
					
					switch(algorithm){
						case DIJKSTRA: {
							Cell next = (Cell) e.getDestination();
							double distanceFromStart = current.getDistanceFromStart() + e.getCost();	
							if(next.getColor() != Color.GREEN && next.getColor() != Color.MAGENTA)
								next.setColor(Color.BLUE);
				
							if(distanceFromStart < next.getDistanceFromStart()){
								openList.remove(next);
								next.setDistanceFromStart(distanceFromStart);
								next.setTotalCost(distanceFromStart);
								next.setPredecessor(current);
								openList.add(next);
							}
							break;
						}
					
						case ASTAR: {
							
							Cell next = (Cell) e.getDestination();
							if(closedList.contains(next))
								continue;
							double distanceFromStart = current.getDistanceFromStart() + e.getCost();	
							double distanceToGoal = euclideanDistance(next.getPosition(), goal.getPosition());
							double estimate = distanceFromStart + distanceToGoal;
							if(next.getColor() != Color.GREEN && next.getColor() != Color.MAGENTA)
								next.setColor(Color.BLUE);
				
							if(!openList.contains(next) || distanceFromStart < next.getDistanceFromStart()){	
								next.setDistanceFromStart(distanceFromStart);
								next.setTotalCost(estimate);
								next.setPredecessor(current);
								openList.add(next);
							}
							break;
						}				
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
	
	public double euclideanDistance(Point p1, Point p2){
		return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
	}
}
